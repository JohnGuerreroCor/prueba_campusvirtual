package co.edu.usco.pagos.dto;

public class Payer {
	private String document;
	private String documentType;
	private String name;
	private String surname;
	private String email;
	private String mobile;
	private Address address;

	/**
	 * 
	 */
	public Payer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param document
	 * @param documentType
	 * @param name
	 * @param surname
	 * @param email
	 * @param mobile
	 * @param address
	 */
	public Payer(String document, String documentType, String name, String surname, String email, String mobile,
			Address address) {
		super();
		this.document = document;
		this.documentType = documentType;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}

	/**
	 * @return the document
	 */
	public String getDocument() {
		return document;
	}

	/**
	 * @param document
	 *            the document to set
	 */
	public void setDocument(String document) {
		this.document = document;
	}

	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType
	 *            the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
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
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
