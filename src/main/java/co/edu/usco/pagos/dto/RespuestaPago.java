package co.edu.usco.pagos.dto;

public class RespuestaPago {

	private Status status;
	private int requestId;
	private String processUrl;

	/**
	 * 
	 */
	public RespuestaPago() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 * @param requestId
	 * @param processUrl
	 */
	public RespuestaPago(Status status, int requestId, String processUrl) {
		super();
		this.status = status;
		this.requestId = requestId;
		this.processUrl = processUrl;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the requestId
	 */
	public int getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId
	 *            the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the processUrl
	 */
	public String getProcessUrl() {
		return processUrl;
	}

	/**
	 * @param processUrl
	 *            the processUrl to set
	 */
	public void setProcessUrl(String processUrl) {
		this.processUrl = processUrl;
	}

}
