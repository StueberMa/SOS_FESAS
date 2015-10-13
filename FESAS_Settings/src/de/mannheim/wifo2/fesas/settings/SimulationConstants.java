package de.mannheim.wifo2.fesas.settings;

import java.util.Date;

/**
 * Constants for the weather simulator.
 * 
 * @author Krupitzer
 *
 */
public class SimulationConstants {

	/**
	 * This variable describes the amount of time between two simulation steps.
	 */
	
	public static final long SIMULATION_TIME = 60000 * 30; // simulation step is one hour for 60000 * 60
	
	/**
	 * This factor fastens the simulation without changing the simulation time between
	 * two simulation steps.
	 */
	public static final double SIMULATION_FACTOR = 1.0 / (24 * 60); // 1/24 => one hour simulation = one day simulated
																	// 1 / (24*60) => one minute simulation = one day simulated
																	// 1 / (24*6) => ten minutes simulation = one day simulated
	
	@SuppressWarnings("deprecation")
	//year, month, day, hours, min
	public static final Date START_DATE = new Date(2014, 11, 6, 17, 00) ;

}
