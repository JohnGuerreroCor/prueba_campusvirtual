package co.edu.usco.pagos.dto;

public class Address {

	private String street;
	private String city;
	private String country;

	/**
	 * 
	 */
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param street
	 * @param city
	 * @param country
	 */
	public Address(String street, String city, String country) {
		super();
		this.street = street;
		this.city = city;
		this.country = country;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

}
