package co.edu.usco.pagos.dto;

public class Notificacion {

	private Status status;
	private int requestId;
	private int reference;
	private String signature;

	/**
	 * @param status
	 * @param requestId
	 * @param reference
	 * @param signature
	 */
	public Notificacion(Status status, int requestId, int reference, String signature) {
		super();
		this.status = status;
		this.requestId = requestId;
		this.reference = reference;
		this.signature = signature;
	}

	/**
	 * 
	 */
	public Notificacion() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the reference
	 */
	public int getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(int reference) {
		this.reference = reference;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

}
