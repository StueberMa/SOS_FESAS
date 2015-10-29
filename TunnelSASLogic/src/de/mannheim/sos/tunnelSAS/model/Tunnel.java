package de.mannheim.sos.tunnelSAS.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Tunnel.
 * 
 * @author SOS @ Uni Mannheim
 * @version 28.10.2015
 */
public class Tunnel{
	
	// attributes
	private static Tunnel tunnel;
	private Environment environment;
	private List<Lamp> lamps;
	
	/**
	 * Constructor
	 * 
	 * @param environment
	 */
	private Tunnel(int brightness) {
		this.environment = new Environment(brightness);
		this.lamps = new ArrayList<Lamp>();
	}
	
	/**
	 * GET tunnel
	 * 
	 * @return
	 */
	public static Tunnel getTunnel() {
		
		// declaration
		int brightness = 0;
		
		// init. if null
		if(tunnel == null) {
			tunnel = new Tunnel(brightness);
			tunnel.addLamp(new Lamp(brightness));
			tunnel.addLamp(new Lamp(brightness));
			tunnel.addLamp(new Lamp(brightness));
			tunnel.addLamp(new Lamp(brightness));
			tunnel.addLamp(new Lamp(brightness));
		}
		
		return tunnel;
	}

	/**
	 * GET environment
	 * 
	 * @return
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * SET environment
	 * 
	 * @param environment
	 */
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	/**
	 * GET lamps
	 * 
	 * @return
	 */
	public List<Lamp> getLamps() {
		return lamps;
	}

	/**
	 * ADD lamps
	 * 
	 * @param lamps
	 */
	public void addLamp(Lamp lamp) {
		this.lamps.add(lamp);
	}
}
