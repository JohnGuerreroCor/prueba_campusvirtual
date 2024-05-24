package co.edu.usco.pagos.dto;

public class Buyer {

	private String name;
	private String surname;
	private String email;
	private Address address;

	/**
	 * 
	 */
	public Buyer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param surname
	 * @param email
	 * @param address
	 */
	public Buyer(String name, String surname, String email, Address address) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

}
