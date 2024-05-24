package co.edu.usco.pagos.dto;

public class From {

	private String currency;
	private String total;

	/**
	 * 
	 */
	public From() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param currency
	 * @param total
	 */
	public From(String currency, String total) {
		super();
		this.currency = currency;
		this.total = total;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

}