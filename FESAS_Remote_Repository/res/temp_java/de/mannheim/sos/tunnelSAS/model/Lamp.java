package de.mannheim.wifo2.fesas.logicRepository.dependencies.de.mannheim.sos.tunnelSAS.model;

/**
 * Class Lamp.
 * 
 * @author SOS @ Uni Mannheim
 * @version 28.10.2015
 */
public class Lamp {
	
	// attributes
	private int brightness;
	
	/**
	 * Constructor
	 * 
	 * @param brightness
	 */
	public Lamp(int brightness) {
		this.brightness = brightness;
	}

	/**
	 * GET brightness
	 * 
	 * @return
	 */
	public int getBrightness() {
		return brightness;
	}

	/**
	 * SET brightness
	 * 
	 * @param brightness
	 */
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	
}
