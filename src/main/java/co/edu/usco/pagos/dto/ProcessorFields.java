package co.edu.usco.pagos.dto;

public class ProcessorFields {

	private String keyword;
	private String value;
	private String displayOn;

	/**
	 * 
	 */
	public ProcessorFields() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param keyword
	 * @param value
	 * @param displayOn
	 */
	public ProcessorFields(String keyword, String value, String displayOn) {
		super();
		this.keyword = keyword;
		this.value = value;
		this.displayOn = displayOn;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the displayOn
	 */
	public String getDisplayOn() {
		return displayOn;
	}

	/**
	 * @param displayOn
	 *            the displayOn to set
	 */
	public void setDisplayOn(String displayOn) {
		this.displayOn = displayOn;
	}

}
