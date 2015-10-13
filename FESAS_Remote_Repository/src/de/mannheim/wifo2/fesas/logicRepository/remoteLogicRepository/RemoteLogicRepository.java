package de.mannheim.wifo2.fesas.logicRepository.remoteLogicRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.ZipFile;

import de.mannheim.wifo2.fesas.logicRepositoryStructure.IRemoteLogicRepository;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.LogicNotFoundException;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.NoJSONFileException;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.Contract;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.contract.ContractProperty;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicElementMetadata;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.settings.Constants;
import de.mannheim.wifo2.fesas.settings.FesasConstants;
import de.mannheim.wifo2.fesas.settings.LogicRepositoryConstants;
import de.mannheim.wifo2.fesas.tools.fileManagement.FileManager;
import de.mannheim.wifo2.fesas.tools.fileManagement.JavaFileManager;
import de.mannheim.wifo2.fesas.tools.fileManagement.ZipFileHandler;
import de.mannheim.wifo2.fesas.tools.logging.Logging;
import de.mannheim.wifo2.fesas.tools.parser.jsonParser.JSONParser;

public class RemoteLogicRepository implements IRemoteLogicRepository {

	/**
	 * The only instance of the repository -> Singleton Pattern.
	 */
	private static RemoteLogicRepository instance;

	/**
	 * One HashMap for each LogicType. A HashMap saves the LogicMetadata and the
	 * corresponding Logic class.
	 */
	private HashMap<LogicType, HashMap<LogicElementMetadata, String>> logicElements;



	/**
	 * Private Constructor for a LogicRepository element. Singleton Pattern!
	 */
	private RemoteLogicRepository() {

		System.setProperty("java.rmi.server.hostname", FesasConstants.LOGIC_REPOSITORY_ADRESS);
		logicElements = new HashMap<LogicType, HashMap<LogicElementMetadata, String>>();

		for (LogicType lt : LogicType.values()) {
			logicElements.put(lt, new HashMap<LogicElementMetadata, String>());
		}

		try {
			initializeRepositoryWithExampleLogics();
		} catch (IOException | NoJSONFileException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Delivers the only instance of a LogicRepository.
	 * 
	 * @return
	 */
	public static synchronized RemoteLogicRepository getInstance() {
		if (instance == null) {
			instance = new RemoteLogicRepository();
		}
		return instance;
	}

	/**
	 * Initializes the repository at the start of it
	 * @throws IOException 
	 * @throws NoJSONFileException 
	 */
	private void initializeRepositoryWithExampleLogics() throws IOException, NoJSONFileException {

		for (String s : LogicRepositoryConstants.REPOSITORY_START_LOGIC_ELEMENTS) {
//			FileInputStream stream = new FileInputStream(s);
//			File selectedFile = File.createTempFile(s, "tmp");
//			String fileContent = FileManager.readFile(stream);
//			File toRead = FileManager.writeFile(selectedFile.getAbsolutePath(), fileContent);
			ZipFile zipFile = new ZipFile(s);
			
			// load JSON file with metadata
			String json = ZipFileHandler.getInstance().loadJSONFromZipFile(zipFile);
			LogicElementMetadata logicMetadata = 
					JSONParser.generateLogicMetadataFromString(json);
			
			// create FileInputStream for zip file 
			File file = new File(s);
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();
			
			this.addLogicToRepository(logicMetadata, file);
		}
	}
	


	/*
	 * 
	 * 
	 * 
	 * Load an existing element from the repository.
	 */

	/* Find a good contract. */
	@Override
	public String findLogicElement(String contractString) {
		
		// candidates: having the same LogicType and programming language
		HashMap<LogicElementMetadata, Double> candidates = new HashMap<LogicElementMetadata, Double>();
		// the best suitable logic
		LogicElementMetadata mdFound = null;
		
		// load the contract
		Contract contract = JSONParser.generateContractFromString(contractString);
		if(Constants.DEBUG_REMOTE_LOGIC_REPOSITORY) System.out.println("Contract received : " + contract.toString());
		
		// load the LogicElementMetadata with the specified LogicType
		HashMap<LogicElementMetadata, String> logics = logicElements.get(contract.getLogicType());
		if(Constants.DEBUG_REMOTE_LOGIC_REPOSITORY) System.out.println("Length : " + logics.size());
		
		
		// load all element having the same LogicType and programming language
		for (LogicElementMetadata lmd : logics.keySet()) {
			if (lmd.getProgrammingLanguage() == contract.getProgrammingLanguage()) {
				candidates.put(lmd, 0.0);
			}
		}
		
		// search for the best logic element
		for (LogicElementMetadata lmd : candidates.keySet()) {
			double utility = 0.0;
			
			for(ContractProperty cp : contract.getProperties()) {
				String currentKey = cp.getKey();
				String logicValue = lmd.getProperties().get(currentKey);
				if (logicValue != null &&
						cp.getValue().trim().toLowerCase().equals(
						logicValue.trim().toLowerCase())) {
					utility += cp.getUtility();
				}
			}
			
			candidates.put(lmd, utility);
		}
		
		if (candidates.size() == 0) {
			return null;
		} else {
			LogicElementMetadata[] keys = candidates.keySet().toArray(new LogicElementMetadata[candidates.keySet().size()]);
			for(LogicElementMetadata lmd : keys) {
				if(mdFound == null) {
					mdFound = lmd;
				} else {
					if(Constants.DEBUG_REMOTE_LOGIC_REPOSITORY) System.out.println("utility : " + candidates.get(lmd) + " vs. " + candidates.get(mdFound));
					if (candidates.get(mdFound) < candidates.get(lmd)) {
						mdFound = lmd;
					}
				}
			}
		}
		
		if(Constants.DEBUG_REMOTE_LOGIC_REPOSITORY) System.out.println("mdfound : " + mdFound.toString());
		
		String result = JSONParser.generateJSONStringFromMetadataElement(mdFound);
		return result;
	}
	
	/* Return the class path for a given class name. */
	@Override
	public String getPathToClass(String className, String type) {
		String logicPath = "";
		if (!(type.equals("") || type == null)) {
			// asked for a logic element
			logicPath += FesasConstants.LOGIC_ELEMENTS_PACKAGE_PATH 
					+ type.toLowerCase() + File.separator + 
					JavaFileManager.substituteDotsWithFileSeperator(className);
		} else {
			// asked for a dependency element
			logicPath += FesasConstants.DEPENDENCIES_PACKAGE_PATH 
					+ JavaFileManager.substituteDotsWithFileSeperator(className);
		}
		return logicPath;
	}

	/* Load a logic element. */
	@Override
	public byte[] loadLogicFromRepository(String jsonString) throws RemoteException,
	LogicNotFoundException {

		// get name of logic file
		LogicElementMetadata md = 
				JSONParser.generateLogicMetadataFromString(jsonString);
		Logging.logIntoFile(getClass(), "Received request for ID : " + md.getId(), 
				"null", "null", null);
		byte[] data = null;
		
		String classname = getPathToClass(
				md.getName(), 
				md.getLogicType().toString());
		
		try {
			data = marshalFile(classname);
		} catch (IOException e) {
			Logging.logIntoFile(getClass(), "Could not marshall logic file : " + md.getName(), 
					"null", "null", e.toString());
			e.printStackTrace();
			throw new LogicNotFoundException(", file not found!");
		}
		
		Logging.logIntoFile(getClass(), "Send class for ID: " + md.getId(), 
				"null", "null", "Class path is : " + classname);
		return data;
	}

	/* Load a dependency element. */
	@Override
	public byte[] loadDependencyFromRepository(String className) throws RemoteException, LogicNotFoundException {
		String classToLoad = this.getPathToClass(className, "");
		byte[] data = null;
		
		try {
			data = marshalFile(classToLoad);
		} catch (IOException e) {
			Logging.logIntoFile(getClass(), "Could not marshall dependency file : " + className, 
					"null", "null", e.toString());
			e.printStackTrace();
		}
		
		Logging.logIntoFile(getClass(), "Send class for dependency: " + className, 
				"null", "null", "Class path is : " + classToLoad);
		return data;
	}




	/*
	 * 
	 * 
	 * 
	 * Add a new element to the repository.
	 */


	@Override
	public synchronized boolean addLogicToRepository(LogicElementMetadata metadata, File file) 
			throws RemoteException, LogicNotFoundException {

		// clean the temp directories
		emptyTempDirectories();

		//intitialize ArrayLists for zipfile and java files
		ArrayList<File> allZipfiles;
		ArrayList<File> javaFiles = new ArrayList<File>();
		//		File logic = null;
		int logicFile = -1;

		//		1) extract zip file
		allZipfiles = ZipFileHandler.getInstance().extractZipFile(file);

		//		2) save .java files in ArrayList
		for(int i = 0; i < allZipfiles.size(); i++) {
			File f = allZipfiles.get(i);
			if (f.getName().contains(".java")) {
				javaFiles.add(f);
			}
		}

		//		3) change the source code files:
		//		change the package declaration of all java files
		//		and change the imports of the logic file
		for(int i = 0; i < javaFiles.size(); i++) {
			File f = javaFiles.get(i);

			// (i) load the current file
			String javaCode = JavaFileManager.loadJavaFile(f);

			// (ii) change the package declaration
			String type = "";
			if (JavaFileManager.compareJavaFileNameWithJSONName(f.getName(),metadata.getName())) {
				//Logic file only has a type
				type = metadata.getLogicType().toString().toLowerCase();
				logicFile = i;
			}
			javaCode = JavaFileManager.changePackageDeclaration(javaCode, type);

			// (iii) Logic file only: change the imports
			if (JavaFileManager.compareJavaFileNameWithJSONName(f.getName(),metadata.getName())) {
				javaCode = JavaFileManager.changeImportsInLogicFileCode(javaCode, metadata);
			}

			// (iv.a) save the updated java file - load the path
			String path = null;
			// this is the logic
			if (JavaFileManager.compareJavaFileNameWithJSONName(f.getName(),metadata.getName())) {
				path = JavaFileManager.substituteDotsWithFileSeperator(metadata.getName()) + 
						".java";
			// this is some dependency
			} else {
				String name = searchJavaClassNameForDependency(f.getName(), metadata.getDependencies()); 
				if (name != null)  {
					path = JavaFileManager.substituteDotsWithFileSeperator(name) + ".java";
				} else {
					path = null;
				}
			}

			// (iv.b) save the updated java file
			if (path != null) {
				f = JavaFileManager.writeToJavaFile(path, javaCode);
				javaFiles.set(i, f);
			} else {
				javaFiles.remove(f);
			}
		}

		// in case that there was no logic file found -> error!
		if (logicFile == -1) {
			if(Constants.DEBUG_REMOTE_LOGIC_REPOSITORY) System.out.println("Logic is null");
			throw new LogicNotFoundException("No corresponding .java file is specified by the JSON information.");
		}


		//		4) Generate class files

		// (i) compile the dependency files 
		for (String d : metadata.getDependencies()) {
			String depedencyClassFile = d.substring(d.lastIndexOf(".") + 1) + ".java";
			depedencyClassFile.trim();
			
			for(int i = 0; i < javaFiles.size(); i++) {
				if (i == logicFile) {
					continue;
				}
				File f = javaFiles.get(i);
//				System.out.println("compare : " + depedencyClassFile + " - with : " + f.getName());
				if (f.getName().trim().equals(depedencyClassFile)) {
//					System.out.println("YES!");
					Logging.logIntoFile(getClass(), "Compile", "null", "null", f.getAbsolutePath());
					try {
						JavaFileManager.generateClassFile(f, "bin" + File.separator);
						Logging.logIntoFile(getClass(), "Dependent class added to bin", "null", "null", f.getName());
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
						e.printStackTrace();
						Logging.logIntoFile(getClass(), "Could not compile class : " + f.getName(), 
								"null", "Remote Repository", e.getClass() + " - " + e.getMessage());
					}
				}
			}
		}

		// (ii) compile the logic files
		String logicPath = null;
		try {
			logicPath = "bin" + File.separator;
			boolean result = JavaFileManager.generateClassFile(javaFiles.get(logicFile), logicPath);
			if (result == true) {
				logicPath += getPathToClass(metadata.getName(), metadata.getLogicType().toString());
				Logging.logIntoFile(getClass(), "Logic class added to bin", "null", "null", javaFiles.get(logicFile).getName());
			} else {
				logicPath = null;
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
			return false;
		}



		//		5) add to the HashMap with logics
		if (logicPath != null) {
			
			metadata.setId("");
			HashMap<LogicElementMetadata, String> typeHM = logicElements.get(metadata.getLogicType());
			LogicElementMetadata toDelete = null;
			
			// (i.a) in case of update: delete old entry in HashMap
			for(LogicElementMetadata lmd : typeHM.keySet()) {
				if (lmd.getName().trim().toLowerCase().equals(
						metadata.getName().trim().toLowerCase())) {
					// use the old ID
					metadata.setId(lmd.getId());
					// delete old entry
					toDelete = lmd;
					break;
				}
			}
			
			if (toDelete != null) {
				typeHM.remove(toDelete);
			}
			
			// (i.b) in case of new element: assign an id
			if (metadata.getId().equals("")) {
				metadata.setId(assignID (metadata.getLogicType().toString()));
			}
			
			// (ii) save metadata to hashmap
			typeHM.put(metadata, logicPath);
			
			if (toDelete == null) {
				Logging.logIntoFile(getClass(), "Logic saved in repository - ID: " + metadata.getId(), 
						"null", "null", metadata.toString());
			} else {
				Logging.logIntoFile(getClass(), "Logic updated in repository - ID: " + metadata.getId(), 
						"null", "null", metadata.toString());
			}
		
			return true;
			
		} else {
			// only, if logic path is null => logic could not be compiled
			return false;
		}

	}



	/*
	 * 
	 * 
	 * 
	 * Removes an existing element from the repository.
	 */

	@Override
	public boolean removeLogic() throws RemoteException {
		// TODO: implement removal of logics later
		return false;
	}








	/*
	 * 
	 * 
	 * Private Methods for loading an existing logic file.
	 */

	
	
	
	/**
	 * Used for serializing a class file to prepare it for sending.
	 * 
	 * Expects a path to the .class file without bin at the beginning and 
	 * without .class at the end.
	 * 
	 */
	private byte[] marshalFile(String className) throws RemoteException, IOException {
		byte[] data = null;

		String current = new java.io.File(".").getCanonicalPath();
		current += File.separator + "bin" + File.separator + "";

		Path path = Paths.get(current + className + ".class");
		if(Constants.DEBUG_REMOTE_LOGIC_REPOSITORY) System.out.println("Path to marshall : " + path);
		data = Files.readAllBytes(path);

		return data;
	}



	/*
	 * 
	 * 
	 * Private methods for adding a new logic.
	 */

	/**
	 * Deletes all temp files.
	 */
	private void emptyTempDirectories() {
		FileManager.emptyDir(LogicRepositoryConstants.ZIP_TEMP_PATH);
		FileManager.emptyDir(LogicRepositoryConstants.JSON_TEMP_PATH);
		FileManager.emptyDir(LogicRepositoryConstants.JAVA_TEMP_PATH);
	}

	/**
	 * This method returns the full class name as specified in the JSON file for
	 * a .java class.
	 * 
	 * @param name
	 *            The .java class name (without package).
	 * @param dependencies
	 *            The list of dependencies.
	 * @return The corresponding class name as specified in the list of
	 *         dependencies
	 */
	private String searchJavaClassNameForDependency(String name, String[] dependencies) {
		String classname = name.substring(0, name.indexOf("."));
		String temp;
		for (String s : dependencies) {
			temp = s.substring(s.lastIndexOf(".") + 1);
			if (temp.equals(classname)) {
				return s;
			}
		}
		return null;
	}


	/**
	 * This method generates a new ID.
	 * @param type
	 * @return
	 */
	private synchronized String assignID(String type) {
		String id = type + "_" + UUID.randomUUID().toString();
		return id;
	}









}
