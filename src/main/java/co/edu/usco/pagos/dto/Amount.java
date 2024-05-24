package co.edu.usco.pagos.dto;

public class Amount {

	private From from;;
	private To to;;

	/**
	 * 
	 */
	public Amount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param from
	 * @param to
	 */
	public Amount(From from, To to) {
		super();
		this.from = from;
		this.to = to;
	}

	/**
	 * @return the from
	 */
	public From getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(From from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public To getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(To to) {
		this.to = to;
	}

}
