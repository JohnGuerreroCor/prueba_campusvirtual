/**
 * 
 */
package co.edu.usco.pagos.dto;

import java.util.List;

/**
 * @author jankarlos
 *
 */
public class EstadoPago {

	private int requestId;
	private Status status;
	private Request request;
	private List<Payment> payment;
	private String subscription;

	/**
	 * 
	 */
	public EstadoPago() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param requestId
	 * @param status
	 * @param request
	 * @param payment
	 * @param subscription
	 */
	public EstadoPago(int requestId, Status status, Request request, List<Payment> payment, String subscription) {
		super();
		this.requestId = requestId;
		this.status = status;
		this.request = request;
		this.payment = payment;
		this.subscription = subscription;
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
	 * @return the request
	 */
	public Request getRequest() {
		return request;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * @return the payment
	 */
	public List<Payment> getPayment() {
		return payment;
	}

	/**
	 * @param payment
	 *            the payment to set
	 */
	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	/**
	 * @return the subscription
	 */
	public String getSubscription() {
		return subscription;
	}

	/**
	 * @param subscription
	 *            the subscription to set
	 */
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

}
