package de.mannheim.wifo2.fesas.settings;

import java.io.File;

public class FesasConstants {

	// Choice of Parser for config file
//	public static final String PARSER_TYPE = "DummyFileParser";
	public static final String PARSER_TYPE = "JSONFileParser";
	
	
	// (Temporary) config files

	/** The split symbol for the temporary config file's information. */
	public static final String REGEX_FOR_CONFIG_FILE_SPLIT = ":";
	/** Link to the config files directory. */
	public static final String CONFIG_FILE_PATH = "C:" + File.separator + "ConfigFiles"
			+ File.separator;

	/** Link to the FESAS description language file. */
	public static final String XML_FILE = "res" + File.separator + "example.xml";
	/** Link to the FESAS description language grammar. */
	public static final String XML_SCHEMA = "res" + File.separator + "fesas_grammar.xsd";
	/** The comment symbol for the temporary config file. */
	public static final String PSEUDO_CONFIG_FILE_COMMENT_SYMBOL = "//";

	/** Link to the location of the logic binaries. */
	public static final String LOGIC_ELEMENTS_PACKAGE_PATH = "de" + File.separator + 
			"mannheim" + File.separator + "wifo2" + File.separator + "fesas"  + 
			File.separator + "logicRepository" + File.separator + "logicElements" + File.separator ; 

//	TODO: write with File seperator
	public static final String JDK_JAVA_HOME = "C:" + File.separator + "Program Files" 
			+ File.separator + "Java" + File.separator 
			+ "jdk1.8.0_31" + File.separator + "jre";
	

	
	/** Link to the location of binaries for the logic data types. */
	public static final String DEPENDENCIES_PACKAGE_PATH = "de" + File.separator + 
			"mannheim" + File.separator + "wifo2" + File.separator + "fesas"  + 
			File.separator + "logicRepository" + File.separator + "dependencies" + File.separator ; 


	
	// System Deployment
	
	/** Timeout between creation of the elements and establishment of connections. */
	public static final long INITIALIZATION_WAITING_TIME = 3000;


	// Logic Repository

	/** IP of the logic repository. */
	//	public static final String LOGIC_REPOSITORY_ADRESS = "rmi://ec2-52-10-63-6.us-west-2.compute.amazonaws.com:1099";
	public static final String LOGIC_REPOSITORY_ADRESS = "127.0.0.1"; //localHost
	//	public static final String LOGIC_REPOSITORY_ADRESS = "52.10.63.6"; //Christian's EC-2
	//	public static final String LOGIC_REPOSITORY_ADRESS = "134.155.51.183";
	//	public static final String LOGIC_REPOSITORY_ADRESS = "134.155.150.161";
	//	public static final byte[] LOGIC_REPOSITORY_IP = {(byte)134, (byte)155, (byte)163, (byte)61};
	/** Port of the logic repository. */
	public static final int LOGIC_REPOSITORY_PORT = 9999;
	/** Logic repository registry RMI identifier. */
	public static final String LOGIC_REPOSITORY_IDENTIFIER = "LogicRepository";
	/** Port of the logic repository registry. */
	public static final int LOGIC_REPOSITORY_REGISTRY_PORT = 1099;

	
	// ALM
	
//	/** IP of the logic repository. */
//	public static final String ALM_ADRESS = "127.0.0.1"; //localHost
//	/** Port of the ALM for registration. */
//	public static final int ALM_REGISTRATION_PORT = 5555;
//	/** Port of the ALM proxy. */
//	public static final int ALM_PROXY_PORT = 7777;
//	/** Logic repository registry RMI identifier. */
//	public static final String ALM_IDENTIFIER = "AdaptationLogicManager";
//	/** Port of the logic repository registry. */
//	public static final int ALM_REGISTRY_PORT = 1097;
	

	// Logging

	/** Enable / Disable logging. */
	public static final boolean USE_LOGGING_IN_FILE = true;
	/** Path to the log files. */
	public static final String LOGGING_IN_FILE_PATH = "C:" + File.separator + "LogFiles" 
			+ File.separator;
			
}
