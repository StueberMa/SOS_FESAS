package uni.mannheim.TunnelController.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uni.mannheim.TunnelController.model.Person;

/**
 * Class HelloService to provide some basic web-services.
 * 
 * @author Maximilian Stüber
 * @version 09.02.2015
 */
@Path("/hello")
public class HelloService {
	
	/**
	 * Method sayPlainTextHello.
	 * 
	 * @return
	 */
	@GET
	@Path("/sayPlainTextHello")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}
	
	/**
	 * Method sayObjectHello.
	 * 
	 * @return
	 */
	@GET
	@Path("/sayObjectHello")
	@Produces(MediaType.APPLICATION_JSON)
	public Person sayObjectHello() {
		
		// declaration
		Person p1 = null;
		
		// create dummy person
		p1 = new Person("Mustermann", "Max", "123456 Musterhausen");

		return p1;
	}
}