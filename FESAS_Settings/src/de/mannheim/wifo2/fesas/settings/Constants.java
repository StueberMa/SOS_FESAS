package de.mannheim.wifo2.fesas.settings;



public class Constants {

	// BASE
	
	/** Timeout between creation of the BASE structure and establishment of connections. */
	public static final int DEVICE_TIMEOUT = 5000;
	/**
	 * The amount of time that the service will wait after
	 * it has been started (BASE).
	 */
	public static final long PERIOD_INIT = 3000;
	/**
	 * The amount of time that the service will wait between
	 * the lookup calls (BASE).
	 */
	public static final long PERIOD_CYCLE = 1500;
	/**
	 * The amount of time that the pub/sub service will wait between
	 * the lookup calls (BASE).
	 */
	public static final long COMMUNICATION_PERIOD_CYCLE = 500;
	
	public static final boolean LOCAL_ALM_DEBUG_MODE = true;

	
	// Settings of the adaptation logic
	
	/**
	 * The amount of time between two requests of the sensor to the MRs.
	 */
	public static final long ENV_SENSOR_PERIOD_CYCLE = 1000;
	
	
	// Debugging
	
	/** Debug the initialization of the adaptation logic. */
	public static final boolean DEBUG_FESASSETUP = true;
	/** Debug the saving and getting of knowledge. */
	public static final boolean DEBUG_KNOWLEDGE = false;
	/** Debug the communication and pub sub component. */
	public static final boolean DEBUG_COMMUNICATION = false;
	/** Debug the ID conversion. */
	public static final boolean DEBUG_IDCONVERSION = false;
	/** Debug the logic element. */
	public static final boolean DEBUG_LOGIC = true;
	/** Debug the remote logic repository. */
	public static final boolean DEBUG_REMOTE_LOGIC_REPOSITORY = true;
	/** Debug the GUI of the remote logic repository. */
	public static final boolean DEBUG_REMOTE_LOGIC_REPOSITORY_GUI = true;
	/** Debug the logic logic repository. */
	public static final boolean DEBUG_LOCAL_LOGIC_REPOSITORY = true;
	/** Debug the result of the input for a logic (in callLogic()). */
	public static final boolean DEBUG_LOGIC_RESULT = false;
	/** Debug the result of the JSON parser. */
	public static final boolean DEBUG_JSON_PARSER = true;
	/** Debug the ALM. */
	public static final boolean DEBUG_ALM = true;
	

}
