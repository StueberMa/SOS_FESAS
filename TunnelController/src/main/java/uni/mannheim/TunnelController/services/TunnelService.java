package uni.mannheim.TunnelController.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uni.mannheim.TunnelController.model.Tunnel;

/**
 * Class TunnelService to provide some basic web-services.
 * 
 * @author SOS @ Uni Mannheim
 * @version 28.10.2015
 */
@Path("/tunnel")
public class TunnelService {
	
	/**
	 * Method retrieveTunnel.
	 * 
	 * @return
	 */
	@GET
	@Path("/controller")
	@Produces(MediaType.APPLICATION_JSON)
	public Tunnel retrieveTunnel() {
		
		Tunnel t1 = null;
		t1 = Tunnel.getTunnel();

		return t1;
	}
}
