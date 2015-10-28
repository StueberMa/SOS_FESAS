package uni.mannheim.TunnelController.model;

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
	private boolean dim;
	
	/**
	 * Constructor
	 * 
	 * @param environment
	 */
	private Tunnel(int brightness) {
		this.environment = new Environment(brightness);
		this.lamps = new ArrayList<Lamp>();
		this.dim = false;
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
	 * Method simulateEnvironment
	 */
	public void simulateEnvironment() {
		
		// declaration
		int brightness = 0;
		
		brightness = environment.getBrightness();
		
		// determine dim
		if(brightness >= 100)
			dim = true;
		else if (brightness <= 0)
			dim = false;
		
		// compute brightness
		if(dim)
			brightness = brightness - 10;
		else
			brightness = brightness + 10;
		
		environment.setBrightness(brightness);
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
	 * SET brightness
	 * 
	 * @param index
	 * @param brightness
	 */
	public void setLampBrightness(int index, int brightness) {
		lamps.get(index).setBrightness(brightness);
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
