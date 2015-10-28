package uni.mannheim.TunnelController.model;

/**
 * Class Person.
 * 
 * @author Maximilian Stüber
 * @version 26.02.2015
 */
public class Person {
	
	// attributes
	private String lastname;
	private String firstname;
	private String address;
	
	/**
	 * Constructor
	 * 
	 * @param lastname
	 * @param firstname
	 * @param address
	 */
	public Person(String lastname, String firstname, String address) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.address = address;
	}
	
	/**
	 * Constructor
	 */
	public Person() {
		this.lastname = "";
		this.firstname = "";
		this.address = "";
	}

	/**
	 * GET lastname
	 * 
	 * @return lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * SET lastname
	 * 
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * GET firstname
	 * 
	 * @return firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * SET firstname
	 * 
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * GET address
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * SET address
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}