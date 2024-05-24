package co.edu.usco.pagos.dto;

import java.sql.Timestamp;

public class Status {

	private String status;
	private String reason;
	private String message;
	private String date;

	/**
	 * 
	 */
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 * @param reason
	 * @param message
	 * @param date
	 */
	public Status(String status, String reason, String message, String date) {
		super();
		this.status = status;
		this.reason = reason;
		this.message = message;
		this.date = date;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

}
