package de.mannheim.wifo2.fesas.settings;

import java.io.File;

public class LogicRepositoryConstants {

	public static final String ZIP_TEMP_PATH = "res" + File.separator + 
			"temp_zip" + File.separator;
	
	public static final String JSON_TEMP_PATH = "res" + File.separator + 
			"temp_json" + File.separator;
	
	public static final String JAVA_TEMP_PATH = "res" + File.separator + 
			"temp_java" + File.separator;
	
	/** Link to the folder containing the logic elements that should be loaded at start of the repository. */
	public static final String REPOSITORY_START_ELEMENTS_FOLDER = "/Users/maximilian/Downloads/Entwicklung/Git/SOS_FESAS/logicElements/";
	
	/** Logic elements that should be loaded at start of the repository. */
	public static final String[] REPOSITORY_START_LOGIC_ELEMENTS = new String[]{
			REPOSITORY_START_ELEMENTS_FOLDER + "AnalyzerLogicDummy_1VarLargerX.zip",
			REPOSITORY_START_ELEMENTS_FOLDER + "ExecutorLogicDummy_Var.zip",
			REPOSITORY_START_ELEMENTS_FOLDER + "KnowledgeLogicWithHashMap.zip",
			REPOSITORY_START_ELEMENTS_FOLDER + "MonitorLogicDummy_1Var.zip",
//			REPOSITORY_START_ELEMENTS_FOLDER + "MonitorLogicDummy_2Var.zip",
//			REPOSITORY_START_ELEMENTS_FOLDER + "MonitorLogicDummy_3Var.zip",
			REPOSITORY_START_ELEMENTS_FOLDER + "PlannerLogicDummy_VarDecentralized.zip",
	};
}
