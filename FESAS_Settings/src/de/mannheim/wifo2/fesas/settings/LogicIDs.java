package de.mannheim.wifo2.fesas.settings;

/**
 * IDs of the logics.
 * 
 * Just a temporary solution. Later, the metadata is described in another way (XML, JSON, whatever?).
 * 
 * @author Krupitzer
 * @deprecated Just a temporary solution. Later, the metadata is described in another way (XML, JSON, whatever?).
 */
@Deprecated
public class LogicIDs {

	public static final String LOGICID_ANALYZE_ADD = "Analyzer_ADD";
	public static final String LOGICID_ANALYZE_SUBTRACT = "Analyzer_SUBTRACT";
	public static final String LOGICID_ANALYZE_DIVIDE = "Analyzer_DIVIDE";
	public static final String LOGICID_ANALYZE_MULTIPLY = "Analyzer_MULTIPLY";
	
	// Logic implementation
	public static final String TRAFFIC_LOGIC = "Traffic";
	public static final String WEATHER_LOGIC = "Weather";
	public static final String SENSOR_CAMERA_LOGIC = "Camera";
	public static final String SENSOR_WEATHER_LOGIC = "Weather";
	public static final String REGIONAL_PLANNER = "Regional";
	
	public static final String SERVER_LOGIC = "Server";
	
	// Logic implementation - SimpleSAS
	public static final String MonitorLogicDummy_1Var = "MonitorLogicDummy_1Var";
	public static final String MonitorLogicDummy_2Var = "MonitorLogicDummy_2Var";
	public static final String MonitorLogicDummy_3Var = "MonitorLogicDummy_3Var";
	public static final String AnalyzerLogicDummy_1VarLargerX = "AnalyzerLogicDummy_1VarLargerX";
	public static final String AnalyzerLogicDummy_2VarLargerX = "AnalyzerLogicDummy_2VarLargerX";
	public static final String AnalyzerLogicDummy_3VarLargerX = "AnalyzerLogicDummy_3VarLargerX";
	public static final String PlannerLogicDummy_VarDecentralized = "PlannerLogicDummy_VarDecentralized";
	public static final String PlannerLogicDummy_VarRP = "PlannerLogicDummy_VarRP";
	public static final String ExecutorLogicDummy_Var = "ExecutorLogicDummy_Var";
	
	// Logic implementation - ALM SimpleSAS
	public static final String MonitorLogic_ALM = "MonitorLogic_ALM";
	public static final String AnalyzerLogic_ALM = "AnalyzerLogic_ALM";
	public static final String PlannerLogic_ALM = "PlannerLogic_ALM";
	public static final String ExecutorLogic_ALM = "ExecutorLogic_ALM";
	public static final String Sensor_Logic_ALM = "Sensor_Logic_ALM";
	public static final String Effector_Logic_ALM = "Effector_Logic_ALM";

}
