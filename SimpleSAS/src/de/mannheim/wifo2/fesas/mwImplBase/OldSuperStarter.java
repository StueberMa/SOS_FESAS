package de.mannheim.wifo2.fesas.mwImplBase;

//import info.pppc.base.service.ServiceProperties;
//import info.pppc.base.service.ServiceRegistry;
//import info.pppc.base.service.ServiceRegistryException;
//import info.pppc.base.system.InvocationBroker;
//import info.pppc.base.system.PluginManager;
//import info.pppc.base.system.SystemID;
//import info.pppc.basex.plugin.discovery.ProactiveDiscovery;
//import info.pppc.basex.plugin.semantic.RmiSemantic;
//import info.pppc.basex.plugin.serializer.ObjectSerializer;
//import info.pppc.basex.plugin.transceiver.MxIPMulticastTransceiver;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import de.mannheim.wifo2.fesas.fesasMW.almProxy.ALMProxy;
//import de.mannheim.wifo2.fesas.fesasMW.localLogicRepository.LocalLogicRepository;
//import de.mannheim.wifo2.fesas.frameworkLogicStructure.data.IDeviceType;
//import de.mannheim.wifo2.fesas.frameworkLogicStructure.data.expections.InitializationException;
//import de.mannheim.wifo2.fesas.frameworkLogicStructure.data.identifier.FesasDeviceID;
//import de.mannheim.wifo2.fesas.frameworkLogicStructure.data.xml.Property;
//import de.mannheim.wifo2.fesas.frameworkLogicStructure.interfacesForDevices.IFesasSetUp;
//import de.mannheim.wifo2.fesas.logicRepositoryStructure.exceptions.LogicRepositoryNotFoundException;
//import de.mannheim.wifo2.fesas.repository.logicRepository.dataTypes.smartHighway.SmartHighwayDeviceType;
//import de.mannheim.wifo2.fesas.repository.logicRepository.dataTypes.smartHighway.informationTypes.ExtendedInformationCategory;
//import de.mannheim.wifo2.fesas.repository.logicRepository.dataTypes.smartHighway.informationTypes.ExtendedInformationType;
//import de.mannheim.wifo2.fesas.settings.FesasConstants;
//import de.mannheim.wifo2.fesas.tools.logging.Logging;
//import de.mannheim.wifo2.fesasMWImpl.BASEUtilities.IDConversion;
//import de.mannheim.wifo2.fesasMWImpl.BASEUtilities.idRegistry.IDRegistryService;
//import de.mannheim.wifo2.fesasMWImpl.BASEUtilities.idRegistry.IDRegistrySkeleton;
//import de.mannheim.wifo2.fesasMWImpl.BASEUtilities.idRegistry.IIDRegistry;

public class OldSuperStarter {

//	public static FesasSetUpService fesasServ; 
//	public static ALMProxy almProxyServ;
//
//	public static ArrayList<Property> properties = new ArrayList<Property>();
//	
////	private static final String DEVICE_ID = "fesasID-123_0_000";
//	private static String DEVICE_ID;
//	
//	private static IDRegistryService idRegistry;
//	private static ServiceRegistry serviceRegistry;
//	
//	private static HashMap<String, Integer> actuators;
//	
//	private static String ip;
//	private static int port;
//	private static int actuatorPort;
//	
//	private static String scenarioName;
//	
//
//	/**
//	 * Method for starting a new BASE device (such as an adaptation logic element,
//	 * an environmental sensor, or an actuator).
//	 * 
//	 * @param args 
//	 * args[0] = DEVICE_ID;
//	 * args[1] = AL config file
//	 * args{2] = communication config file
//	 * args[3] = scenario name (= folder for config and log files)
//	 * args[4] = delay before initialization of AL in ms
//	 */
//	public static void main(String args[]) {
//
//		try {
//		FesasDeviceID fesasID;
//		IDeviceType deviceType;
//
//		DEVICE_ID = args[0]; 
//		String configFilePath = args[1];
//		String commFilePath = args[2];
//		scenarioName = args[3];
//		int delayBeforeInitializingCommunication= Integer.parseInt(args[4]);
//		
//		// initialize fesas ID
//		fesasID = new FesasDeviceID(DEVICE_ID);
//		// load fesas ID as hex number string
//		String fesasIDAsNumber = args[0].substring(args[0].indexOf('-') + 1)
//				.replaceAll("_", "");
//		// set device type to ADAPTATIONLOGIC_ADAPTATIONLOGIC
//		deviceType = SmartHighwayDeviceType.getDeviceTypeByNumber(0);
//
//		String sysIDInHex = setUpBASE(fesasIDAsNumber);
//		Logging.logIntoFile(OldSuperStarter.class, "BASE device started:",
//				SystemID.SYSTEM.toString(), DEVICE_ID, SystemID.SYSTEM.toString());
//		
//		setUpFESAS(fesasID, deviceType, sysIDInHex);
//		
//		// setup adaptation logic
//		setUpAL(FesasConstants.CONFIG_FILE_PATH + scenarioName + File.separator + configFilePath);
//		
//		Thread.sleep(delayBeforeInitializingCommunication);
//
//		// setup connection to the managed resources
//		setUpMRConnection();
//
//		setUpCommunication(FesasConstants.CONFIG_FILE_PATH + scenarioName + File.separator + commFilePath);
//		
//		startAdaptationLogic();
//		
//		} catch(Exception e) {
//			e.printStackTrace();
//			Logging.logIntoFile(OldSuperStarter.class, e.getClass().getSimpleName(), 
//					"NULL", DEVICE_ID, e.getMessage());
//		}
//
//	}
//
//
//
//
//	/**
//	 * Private method for setting up the BASE instance.
//	 */
//	private static String setUpBASE(String fesasID) {
//
//		System.setProperty(InvocationBroker.PROPERTY_DEVICE_IDENTIFIER, 
//				"0x" + fesasID);
//
//		//Logging.setFileName(args[0], args[1], args [2]);
//		Logging.setVerbosity(Logging.MAXIMUM_VERBOSITY);
//		if (FesasConstants.USE_LOGGING_IN_FILE) {
//			Logging.setFile(scenarioName + File.separator + fesasID + "_" + System.currentTimeMillis());
//		}
//
//		// add plugins default to the broker
//		InvocationBroker invocationBroker = InvocationBroker.getInstance();
//		PluginManager pluginManager = invocationBroker.getPluginManager();
//		pluginManager.addPlugin(new MxIPMulticastTransceiver());
//		pluginManager.addPlugin(new ProactiveDiscovery());
//		pluginManager.addPlugin(new RmiSemantic());
//		pluginManager.addPlugin(new ObjectSerializer());
//		
//		//load service registry
//		serviceRegistry = ServiceRegistry.getInstance();
//		
//		//		System.out.println("SysID in hex : " + IDConversion.transformSysIDToHexString(SystemID.SYSTEM));
//
//		IDRegistrySkeleton skel = new IDRegistrySkeleton();
//		idRegistry = IDRegistryService.getInstance();
//		skel.setImplementation(idRegistry);
//		
//		// register service at local service registry
//		try {
//			serviceRegistry.export(
//					"IDRegistry_" + SystemID.SYSTEM, 
//					new String[] { IIDRegistry.class.getName()},
//					new ServiceProperties(),
//					skel,
//					idRegistry
//					);
//		} catch (ServiceRegistryException e) {
//			Logging.errorIntoFile(OldSuperStarter.class, "Could not register service.", e, 
//					SystemID.SYSTEM.toString(), DEVICE_ID, null);
//			System.exit(1);
//		}
//		
//		try {
//			Thread.sleep(FesasConstants.INITIALIZATION_WAITING_TIME);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//
//		return IDConversion.transformSysIDToHexString(SystemID.SYSTEM);
//	}
//
//	
//	private static void setUpFESAS(FesasDeviceID fesasID, IDeviceType deviceType, String sysIDInHex) {
//		
//		// First: start FesasSetUpService
//		FesasSetUpSkeleton skelSetUp = new FesasSetUpSkeleton();
//		
//		fesasServ = FesasSetUpService.getInstance(fesasID, 
//				deviceType, sysIDInHex);
//
//		if (fesasServ != null) {
//			skelSetUp.setImplementation(fesasServ);
//
//			// register service at local service registry
//				try {
//					serviceRegistry.export(
//							sysIDInHex + "-FESAS", 
//							new String[] {IFesasSetUp.class.getName()},
//							new ServiceProperties(),
//							skelSetUp,
//							fesasServ
//							);
//				} catch (ServiceRegistryException e) {
//					e.printStackTrace();
//				}	
//				// implementAdaptationLogicElement(String alType, String logic, 
//				// String name, String id)
//		} else {
//			Logging.logIntoFile(OldSuperStarter.class, 
//					"Could not start FESAS service on ", SystemID.SYSTEM.toString(), DEVICE_ID, SystemID.SYSTEM.toString());
//		}
//		
//		
//		//
//		// Second: "start" LocalLogicRepository
//		// 
//		LocalLogicRepository.getInstance();
//		
//		
//		//
//		// Third: start ALMProxyService
//		//
////		ALMProxySkeleton skelProxy = new ALMProxySkeleton();
////		almProxyServ = null;
////		almProxyServ = ALMProxy.getInstance();
////
////		if (almProxyServ != null) {
//////			skelProxy.setImplementation(almProxyServ);
////
////			// register service at local service registry
////				try {
////					serviceRegistry.export(
////							sysIDInHex + "-ALMProxy", 
////							new String[] {IALMProxy.class.getName()},
////							new ServiceProperties(),
////							skelProxy,
////							almProxyServ
////							);
////				} catch (ServiceRegistryException e) {
////					e.printStackTrace();
////				}	
//				// implementAdaptationLogicElement(String alType, String logic, 
//				// String name, String id)
////		} else {
////			Logging.logIntoFile(OldSuperStarter.class, 
////					"Could not start ALMProxy service on ", SystemID.SYSTEM.toString(), DEVICE_ID, SystemID.SYSTEM.toString());
////		}
//	}
//	
//	/**
//	 * Setting up the FESAS startup service.
//	 * 
//	 * @param fesasID
//	 * @param deviceType
//	 * @param sysIDInHex
//	 */
//	private static void setUpAL(String configFilePath) {
//		
//		actuators = new HashMap<String, Integer>();
//
//		try {
//			System.out.println(configFilePath);
//			File file = new File(configFilePath);
//			FileReader reader = new FileReader(file);
//			BufferedReader in = new BufferedReader(reader);
//
//			String line = null;
//			while ((line = in.readLine()) != null) {
//				if (line.startsWith(FesasConstants.PSEUDO_CONFIG_FILE_COMMENT_SYMBOL)) {
//					continue;
//				}
//				System.out.println("Read: " + line);
//				String[] parameters = line.split(FesasConstants.REGEX_FOR_CONFIG_FILE_SPLIT); //type:elementFesasID:logicID:name
//				if (parameters[0].equals("ACTUATOR")) {
//					actuators.put(parameters[1], Integer.parseInt(parameters[2]));
//				} else {
//					if (parameters[0].equals("SENSOR") || parameters[0].equals("EFFECTOR")) {  
//						port = Integer.parseInt(parameters[4]);
//						ip = parameters[5];
//					}
//					if (parameters[0].equals("EFFECTOR")) {
//						actuatorPort = Integer.parseInt(parameters[6]);
//					}
//					try {
////						parameters[0] = Type (MONITOR, ANALYZER, PLANNER, EXECUTOR, ...)
////						parameters[1] = Fesas ID
////						parameters[2] = Logic ID
////						parameters[3] = Name
////						parameters[4] = Information Type
////						parameters[5] = Information Category
//						startElement(parameters[0], parameters[1], parameters[2], parameters[3],
//								parameters[4], parameters[5]);
//					} catch (InstantiationException | IllegalAccessException | LogicRepositoryNotFoundException
//							| InitializationException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			in.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//
//
//	}
//
//	private static void startElement(String type, String elementFesasID, String logicID, String name,
//			String infoType, String infoCategory) throws InstantiationException, IllegalAccessException, InitializationException, LogicRepositoryNotFoundException {
//		String objectID;
//
//		if (type.equals("CONTEXTMANAGER")) {
//			objectID = fesasServ.implementContextManager(logicID, name, elementFesasID, actuators);
//		} else if (type.equals("SENSOR")) {
//			objectID = fesasServ.startSensor(logicID, name, elementFesasID, port, ip);
//		} else if (type.equals("EFFECTOR")) {
//			objectID = fesasServ.startEffector(logicID, name, elementFesasID, port, ip, actuatorPort);
//		} else {
////			IFesasSetUp.implementAdaptationLogicElement(String alType, String logic, 
////					String infoType, String infoCategory, String name, String id)
//			objectID = fesasServ.implementAdaptationLogicElement(type, logicID, 
//					infoType, infoCategory, name, elementFesasID);
//		}
//
//		idRegistry.addID(elementFesasID, 
//				IDConversion.transformStringToObjectID(objectID));
//		
//	}
//	
//	private static void setUpMRConnection() {
//		// TODO: Implement the connection to the sensor/actuators
//	}
//
//
//	private static void setUpCommunication(String configFilePath) {
//		try {
//			BufferedReader in = new BufferedReader(new FileReader(configFilePath));
//			String line = null;
//			while ((line = in.readLine()) != null) {
//				if (line.startsWith("//")) {
//					continue;
//				}
//				System.out.println("Read: " + line);
////				public void implementCommunicationStructure(String type, String receiver, 
////				String sender, IInformationType informationType, IInformationCategory category) {
//			// for PubSub: publisher = sender, subscriber = receiver!
//			// type: type of the communication, e.g., PUBSUB
//			// informationType: type of information, e.g., ANALYZING
//				String[] parameters = line.split(FesasConstants.REGEX_FOR_CONFIG_FILE_SPLIT);
//				fesasServ.implementCommunicationStructure(parameters[0], 
//					IDConversion.transformObjIDToString(idRegistry.getObjectID(parameters[1])),
//					IDConversion.transformObjIDToString(idRegistry.getObjectID(parameters[2])),
//					ExtendedInformationType.getInformationTypeByString(parameters[3]),
//					ExtendedInformationCategory.getInformationCategoryByString(parameters[4]));
//			}
//			in.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	private static void startAdaptationLogic() {
//		fesasServ.startAdaptationLogic();
//		
//	}
}
