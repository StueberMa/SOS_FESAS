package de.mannheim.wifo2.fesas.logicRepository.remoteLogicRepository.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.DefaultCaret;

import de.mannheim.wifo2.fesas.logicRepository.remoteLogicRepository.RemoteLogicRepository;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.CouldNotCreateFileException;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.exceptions.NoJSONFileException;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.SupportedLanguage;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicElementMetadata;
import de.mannheim.wifo2.fesas.logicRepositoryStructure.data.metadata.logic.LogicType;
import de.mannheim.wifo2.fesas.settings.Constants;
import de.mannheim.wifo2.fesas.tools.fileManagement.ZipFileHandler;
import de.mannheim.wifo2.fesas.tools.logging.Logging;
import de.mannheim.wifo2.fesas.tools.parser.jsonParser.JSONParser;

public class RemoteLogicRepositoryManagementUI extends javax.swing.JFrame{

	// just for serialization purposes
	private static final long serialVersionUID = -2617900350041964600L;
	
	private JPanel jRepositoryPanel;
	private final int width = 1200;
	private final int height = 700;
	
	private JTextField jLogicFileTF;
	private JLabel jLogicFileLB;
	
	private JButton jLogicFileChooseBtn;
	private JFileChooser fileChooser;
	
	private JButton jAddLogicBtn;
	
	private JTextField jLogicNameTF;
	private JLabel jLogicNameLB;
	
	private JTextField jLogicDescriptionTF;
	private JLabel jLogicDescriptionLB;
	
	private JComboBox<String> jLogicTypeCB;
	private JLabel jLogicTypeLB;
	
	private JComboBox<String> jLogicLanguageCB;
	private JLabel jLogicLanguageLB;
	
	private JTextArea jLogicDependiciesTA;
	private JLabel jLogicDependiciesLB;
	private JScrollPane jLogicDependiciesSP;
	
	private JTextArea jLogicPropertiesTA;
	private JLabel jLogicPropertiesLB;
	private JScrollPane jLogicPropertiesSP;
	private JButton jLogicPropertiesBtn;
	
	private JTextArea jConsoleTA;
	private JLabel jConsoleLB;
	private JScrollPane jConsoleSP;
	
	private File selectedFile;
	private LogicElementMetadata logicMetadata;
	


	public RemoteLogicRepositoryManagementUI(){
		initComponents();
	}
	
	public void startUI() {
		jLogicFileChooseBtn.setEnabled(true);
		jAddLogicBtn.setEnabled(true);
		jLogicPropertiesBtn.setEnabled(true);
	}

	private void initComponents() {

		jRepositoryPanel = new javax.swing.JPanel(new SpringLayout());
		
		// the file path
		jLogicFileTF = new javax.swing.JTextField(20);
		jLogicFileTF.setEditable(false);
		jLogicFileLB = new javax.swing.JLabel();
		jLogicFileLB.setText("Logic source File: ");
	    jLogicFileLB.setLabelFor(jLogicFileTF);
	    
	    // the file choose button
		jLogicFileChooseBtn = new javax.swing.JButton();
		jLogicFileChooseBtn.setEnabled(false);
		jLogicFileChooseBtn.setText("Select a logic package");
		jLogicFileChooseBtn.addActionListener(new java.awt.event.ActionListener() {
		      public void actionPerformed(java.awt.event.ActionEvent evt) {
		        try {
					chooseLogicFile(evt);
					JOptionPane.showMessageDialog(null,"New file zip loaded.");
				} catch (NoJSONFileException e) {
					Logging.logIntoFile(getClass(), "No JSON file Exception", "Repository Console", 
							"Repository Console", e.toString());
					JOptionPane.showMessageDialog(null,e.toString()); 
					e.printStackTrace();
				}
		      }
		    });
		
		fileChooser = new JFileChooser();
	
		 // the add logic to repository button
		jAddLogicBtn = new javax.swing.JButton(); 
		jAddLogicBtn.setEnabled(false);
	    jAddLogicBtn.setText("Add logic to repository");
	    jAddLogicBtn.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	          try {
				boolean result = addLogic(evt);
				if (result) {
					JOptionPane.showMessageDialog(null,"Logic saved in repository."); 
				} else {
					JOptionPane.showMessageDialog(null,"Logic could not be saved in repository.");
				}
			} catch (CouldNotCreateFileException e) {
				Logging.logIntoFile(getClass(), "CouldNotCreateFileException", "Repository Console", 
						"Repository Console", e.toString());
				JOptionPane.showMessageDialog(null,e.toString()); 
				e.printStackTrace();
			} catch (RemoteException e) {
				Logging.logIntoFile(getClass(), "RemoteException", "Repository Console", 
						"Repository Console", "Could not connect to Remote Repository.");
				JOptionPane.showMessageDialog(null,"Could not connect to Remote Repository."); 
				e.printStackTrace();
			}
	        }
	      });
	    
	    
		// the full class name
		jLogicNameTF = new javax.swing.JTextField(20);
		jLogicNameTF.setEditable(false);
		jLogicNameLB = new javax.swing.JLabel();
		jLogicNameLB.setText("Logic Name (full): ");
		jLogicNameLB.setLabelFor(jLogicNameTF);
		
		// the description
		jLogicDescriptionTF = new javax.swing.JTextField(20);
		jLogicDescriptionLB = new javax.swing.JLabel();
		jLogicDescriptionLB.setText("Logic Description: ");
	    jLogicDescriptionLB.setLabelFor(jLogicDescriptionTF);

	    // the logic type
	    jLogicTypeCB = new JComboBox<String>();
	    jLogicTypeCB.addItem("");
	    for(LogicType lt : LogicType.values()) {
	    	jLogicTypeCB.addItem(lt.toString());
	    }
	    jLogicTypeCB.setSelectedIndex(0);
		jLogicTypeLB = new JLabel();
		jLogicTypeLB.setText("Logic Type: ");
	    jLogicTypeLB.setLabelFor(jLogicTypeCB);
		
	    // the programming language
	    jLogicLanguageCB = new JComboBox<String>();
	    jLogicLanguageCB.addItem("");
	    for(SupportedLanguage st : SupportedLanguage.values()) {
	    	jLogicLanguageCB.addItem(st.toString());
	    }
	    jLogicLanguageCB.setSelectedIndex(0);
		jLogicLanguageLB = new JLabel();
		jLogicLanguageLB.setText("Logic Language: ");
	    jLogicLanguageLB.setLabelFor(jLogicLanguageCB);
		
	    // the dependicies
	    jLogicDependiciesTA = new JTextArea();
	    jLogicDependiciesTA.setEditable(false);
	    DefaultCaret caretDependcies = (DefaultCaret)jLogicDependiciesTA.getCaret();
	    caretDependcies.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		jLogicDependiciesLB = new JLabel();
		jLogicDependiciesLB.setText("Dependencies: ");
		jLogicDependiciesLB.setLabelFor(jLogicDependiciesTA);
		jLogicDependiciesSP = new JScrollPane(jLogicDependiciesTA, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jLogicDependiciesSP.setMaximumSize(new Dimension(width, 50));
		
		// the properties
		jLogicPropertiesTA = new JTextArea();
		jLogicPropertiesTA.setEditable(false);
	    DefaultCaret caretProperties = (DefaultCaret)jLogicPropertiesTA.getCaret();
	    caretProperties.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	    jLogicPropertiesLB = new JLabel();
	    jLogicPropertiesLB.setText("Properties: ");
	    jLogicPropertiesLB.setLabelFor(jLogicPropertiesTA);
	    jLogicPropertiesSP = new JScrollPane(jLogicPropertiesTA, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    jLogicPropertiesSP.setMaximumSize(new Dimension(width, 50));
		
	    jLogicPropertiesBtn = new javax.swing.JButton(); 
	    jLogicPropertiesBtn.setEnabled(false);
	    jLogicPropertiesBtn.setText("Add Property");
	    jLogicPropertiesBtn.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	JTextField key = new JTextField();
	        	JTextField value = new JTextField();
	        	Object[] message = {"Key", key, 
	        			"Value", value};
	        	JOptionPane pane = new JOptionPane( message, 
	        			JOptionPane.PLAIN_MESSAGE, 
	        			JOptionPane.OK_CANCEL_OPTION);
	        	pane.createDialog(null, "Add Property").setVisible(true);
	        	updateProperties(key.getText(), value.getText());
	        }
	      });
		
	    
		//console output
		jConsoleTA = new JTextArea();
		DefaultCaret caretConsole = (DefaultCaret)jConsoleTA.getCaret();
		caretConsole.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		jConsoleTA.setEnabled(true);
		jConsoleTA.setEditable(false);
		System.setOut(new PrintStream(new JTextAreaOutputStream(jConsoleTA)));
		System.setErr(new PrintStream(new JTextAreaOutputStream(jConsoleTA)));
		jConsoleLB = new JLabel();
		jConsoleLB.setText("Console output:");
		jConsoleLB.setLabelFor(jConsoleTA);
		jConsoleSP = new JScrollPane(jConsoleTA, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
	    // titel of the window and close button
	    setTitle("Repository Management Console");
	    addWindowListener(new java.awt.event.WindowAdapter() {
	      public void windowClosing(java.awt.event.WindowEvent evt) {
	        exitForm(evt);
	      }
	    });
	
	    
	    // set layout
	    GroupLayout layout = new GroupLayout(jRepositoryPanel);
	    jRepositoryPanel.setLayout(layout);
	    
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    
	    layout.setHorizontalGroup(layout.createSequentialGroup()
	    		   	  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		   			.addComponent(jLogicFileLB)
	    		   			.addComponent(jLogicNameLB)
	    		           .addComponent(jLogicDescriptionLB)
	    		           .addComponent(jLogicTypeLB)
	    		           .addComponent(jLogicLanguageLB)
	    		           .addComponent(jLogicPropertiesLB)
	    		           .addComponent(jLogicDependiciesLB)
	    		           .addComponent(jConsoleLB)
	    		       )
	    		       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		    		.addComponent(jLogicFileTF)
	    		    		.addComponent(jLogicFileChooseBtn)
	    		    		.addComponent(jLogicNameTF)
	    		    		.addComponent(jLogicDescriptionTF)
	    		           .addComponent(jLogicTypeCB)
	    		           .addComponent(jLogicLanguageCB)
	    		           .addComponent(jLogicDependiciesSP)
	    		           .addComponent(jLogicPropertiesSP)
	    		        	.addComponent(jAddLogicBtn)
	    		           .addComponent(jConsoleSP)
	    		       )
	    		       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		    		.addComponent(jLogicPropertiesBtn)
	    		       )
	    		);
	    layout.setVerticalGroup(
	    		   layout.createSequentialGroup()
	    		   	  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		           .addComponent(jLogicFileLB)
	    		           .addComponent(jLogicFileTF))
	    		      .addComponent(jLogicFileChooseBtn)
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		           .addComponent(jLogicNameLB)
	    		           .addComponent(jLogicNameTF))
	    		       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		           .addComponent(jLogicDescriptionLB)
	    		           .addComponent(jLogicDescriptionTF))
	    		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		           .addComponent(jLogicTypeLB)
	    		           .addComponent(jLogicTypeCB))
	    		       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		           .addComponent(jLogicLanguageLB)
	    		           .addComponent(jLogicLanguageCB))
	    		       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		           .addComponent(jLogicDependiciesLB)
	    		           .addComponent(jLogicDependiciesSP))
//	    		        .addComponent(jAddLogicBtn)
	    		        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		        		.addComponent(jLogicPropertiesLB)
	    		        		.addComponent(jLogicPropertiesSP)
	    		        		.addComponent(jLogicPropertiesBtn))
	    		        .addComponent(jAddLogicBtn)
	    		       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    		           .addComponent(jConsoleLB)
	    		           .addComponent(jConsoleSP))
	    		);
	    
	    getContentPane().add(jRepositoryPanel);
	     
	    // refresh for layout and set window sizes and location
	    pack();
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	    setSize(new java.awt.Dimension(width, height));
	    setLocation((screenSize.width-width)/2,(screenSize.height-548)/2);
		
	}
	
	private boolean addLogic(ActionEvent evt) throws CouldNotCreateFileException, RemoteException {
	
		updataMetadata();
		
		RemoteLogicRepository rep = RemoteLogicRepository.getInstance();
		return rep.addLogicToRepository(logicMetadata, selectedFile);
		
	}

	private void chooseLogicFile(ActionEvent evt) throws NoJSONFileException {
		int returnVal = fileChooser.showOpenDialog(RemoteLogicRepositoryManagementUI.this);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
//          1) Load zip File
			selectedFile = fileChooser.getSelectedFile();
            if(Constants.DEBUG_REMOTE_LOGIC_REPOSITORY_GUI) System.out.println("Opening: " + selectedFile.getName());
            jLogicFileTF.setText(selectedFile.getAbsolutePath());
            
//          2) Open zip file and load json file
            String json = null;
            try {
				json = ZipFileHandler.getInstance().loadJSONFromZipFile(new ZipFile(selectedFile));
			} catch (ZipException e) {
				Logging.logIntoFile(getClass(), "Zip Exception", "Repository Console", 
						"Repository Console", "The file that should be loaded is not a zip file.");
				e.printStackTrace();
			} catch (IOException e) {
				Logging.logIntoFile(getClass(), "IO Exception", "Repository Console", 
						"Repository Console", "Could not read the file that should be loaded.");
				e.printStackTrace();
			} 
            


            if (json != null) {
//              3) Read the JSON file
            	logicMetadata = JSONParser.generateLogicMetadataFromString(json);
            
//          	4) Update the values in the UI
            	updateUI(logicMetadata);
            }
        }
		
	}

	private void updateProperties(String key, String value) {
		logicMetadata.addProperty(key, value);
		 String properties = "";
		 for (String s : logicMetadata.getProperties().keySet()) {
			 properties += "Key : " + s + " - Value : " +
					 logicMetadata.getProperties().get(s) + "\n";
		 }
		 jLogicPropertiesTA.setText(properties);
		
	}
	
	private void updateUI(LogicElementMetadata logicMetadata) {
		
		jLogicNameTF.setText(logicMetadata.getName());
		jLogicDescriptionTF.setText(logicMetadata.getDescription());
		System.out.println("SL : " + logicMetadata.getProgrammingLanguage().toString());
		jLogicLanguageCB.setSelectedItem(logicMetadata.getProgrammingLanguage().toString());
		jLogicTypeCB.setSelectedItem(logicMetadata.getLogicType().toString());

		String dependencies = "";
		for (int i = 0; i < logicMetadata.getDependencies().length; i++) {
			dependencies += logicMetadata.getDependencies()[i] + "\n";
		}
	    jLogicDependiciesTA.setText(dependencies);
	    
	    String properties = "";
		for (String s : logicMetadata.getProperties().keySet()) {
			properties += "Key : " + s + " - Value : " +
					logicMetadata.getProperties().get(s) + "\n";
		}
	    jLogicPropertiesTA.setText(properties);
		
	}
	
	private void updataMetadata() {
		
		logicMetadata.setName(jLogicNameTF.getText());
		logicMetadata.setDescription(jLogicDescriptionTF.getText());
		logicMetadata.setLogicType(
				LogicType.valueOf((String)jLogicTypeCB.getSelectedItem()));
		logicMetadata.setProgrammingLanguage(
				SupportedLanguage.getSupportedLanguage(
						(String)jLogicLanguageCB.getSelectedItem()));
		// Dependencies cannot be changed by the gui -> omitted here
		if(Constants.DEBUG_REMOTE_LOGIC_REPOSITORY_GUI) System.out.println(logicMetadata);

	}
	

	private void exitForm(java.awt.event.WindowEvent evt) { 
		System.exit(0);
	}
	
	//inner class for the console output
	private class JTextAreaOutputStream extends OutputStream {

		JTextArea ta;

		public JTextAreaOutputStream(JTextArea t){
			super();
			ta = t;
		}

		public void write(int i){
			ta.append(Character.toString((char)i));
		}

		@SuppressWarnings("unused")
		public void write(char[] buf, int off, int len) {
			String s = new String(buf, off, len);
			ta.append(s);
		}
	}
	

}
