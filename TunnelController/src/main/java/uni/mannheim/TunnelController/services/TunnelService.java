package uni.mannheim.TunnelController.services;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import uni.mannheim.TunnelController.model.Tunnel;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Class TunnelService to provide some basic web-services.
 * 
 * @author SOS @ Uni Mannheim
 * @version 28.10.2015
 */
@Path("/tunnel")
public class TunnelService {
	
	/**
	 * Method retrieveStatus.
	 * 
	 * @param simulate
	 * @return
	 */
	@GET
	@Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
	public Tunnel retrieveStatus(@QueryParam("simulate") boolean simulate) {
		
		// declaration
		Tunnel t = null;
		
		// get tunnel
		t = Tunnel.getTunnel();
		
		// simulate env.
		if(simulate) {
			t.simulateEnvironment();
		}

		return t;
	}
	
	/**
	 * Method controlLamps
	 * 
	 * @param brightnessJSON
	 */
	@PUT
	@Path("/control")
	@Produces(MediaType.APPLICATION_JSON)
	public void controlLamps(@QueryParam("brightness") String brightnessJSON) {
		
		// declaration
		JsonParser parser = null;
		JsonArray brightness = null;
		Tunnel t = null;
		
		// get tunnel
		t = Tunnel.getTunnel();
		
		// set new brightness
		parser = new JsonParser();
		brightness = (JsonArray) parser.parse(brightnessJSON);
		
		for(int i = 0; i < brightness.size(); i++) {
			t.setLampBrightness(i, brightness.get(i).getAsInt());
		}
	}
}
