package co.edu.usco.pagos.dto;

import java.sql.Date;
import java.util.List;

public class Request {

	private String locale;
	private Payer payer;
	private Payer buyer;
	private Payment payment;
	private String subscription;
	private String returnUrl;
	private String paymentMethod;
	private String cancelUrl;
	private String ipAddress;
	private String userAgent;
	private String expiration;
	private boolean captureAddress;
	private boolean skipResult;
	private boolean noBuyerFill;
	private List<Fields> fields;

	/**
	 * 
	 */
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param locale
	 * @param payer
	 * @param buyer
	 * @param payment
	 * @param subscription
	 * @param returnUrl
	 * @param paymentMethod
	 * @param cancelUrl
	 * @param ipAddress
	 * @param userAgent
	 * @param expiration
	 * @param captureAddress
	 * @param skipResult
	 * @param noBuyerFill
	 * @param fields
	 */
	public Request(String locale, Payer payer, Payer buyer, Payment payment, String subscription, String returnUrl,
			String paymentMethod, String cancelUrl, String ipAddress, String userAgent, String expiration,
			boolean captureAddress, boolean skipResult, boolean noBuyerFill, List<Fields> fields) {
		super();
		this.locale = locale;
		this.payer = payer;
		this.buyer = buyer;
		this.payment = payment;
		this.subscription = subscription;
		this.returnUrl = returnUrl;
		this.paymentMethod = paymentMethod;
		this.cancelUrl = cancelUrl;
		this.ipAddress = ipAddress;
		this.userAgent = userAgent;
		this.expiration = expiration;
		this.captureAddress = captureAddress;
		this.skipResult = skipResult;
		this.noBuyerFill = noBuyerFill;
		this.fields = fields;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return the payer
	 */
	public Payer getPayer() {
		return payer;
	}

	/**
	 * @param payer
	 *            the payer to set
	 */
	public void setPayer(Payer payer) {
		this.payer = payer;
	}

	/**
	 * @return the buyer
	 */
	public Payer getBuyer() {
		return buyer;
	}

	/**
	 * @param buyer
	 *            the buyer to set
	 */
	public void setBuyer(Payer buyer) {
		this.buyer = buyer;
	}

	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * @param payment
	 *            the payment to set
	 */
	public void setPayment(Payment payment) {
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

	/**
	 * @return the returnUrl
	 */
	public String getReturnUrl() {
		return returnUrl;
	}

	/**
	 * @param returnUrl
	 *            the returnUrl to set
	 */
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod
	 *            the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the cancelUrl
	 */
	public String getCancelUrl() {
		return cancelUrl;
	}

	/**
	 * @param cancelUrl
	 *            the cancelUrl to set
	 */
	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent
	 *            the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the expiration
	 */
	public String getExpiration() {
		return expiration;
	}

	/**
	 * @param expiration
	 *            the expiration to set
	 */
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	/**
	 * @return the captureAddress
	 */
	public boolean isCaptureAddress() {
		return captureAddress;
	}

	/**
	 * @param captureAddress
	 *            the captureAddress to set
	 */
	public void setCaptureAddress(boolean captureAddress) {
		this.captureAddress = captureAddress;
	}

	/**
	 * @return the skipResult
	 */
	public boolean isSkipResult() {
		return skipResult;
	}

	/**
	 * @param skipResult
	 *            the skipResult to set
	 */
	public void setSkipResult(boolean skipResult) {
		this.skipResult = skipResult;
	}

	/**
	 * @return the noBuyerFill
	 */
	public boolean isNoBuyerFill() {
		return noBuyerFill;
	}

	/**
	 * @param noBuyerFill
	 *            the noBuyerFill to set
	 */
	public void setNoBuyerFill(boolean noBuyerFill) {
		this.noBuyerFill = noBuyerFill;
	}

	/**
	 * @return the fields
	 */
	public List<Fields> getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(List<Fields> fields) {
		this.fields = fields;
	}

}
