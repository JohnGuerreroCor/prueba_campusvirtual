package co.edu.usco.pagos.dto;

import java.util.List;

public class Payment {

	private Status status;
	private String internalReference;
	private String paymentMethod;
	private String paymentMethodName;
	private String issuerName;
	private Amount amount;
	private String authorization;
	private String reference;
	private String receipt;
	private String franchise;
	private String refunded;
	private List<ProcessorFields> processorFields;

	/**
	 * 
	 */
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 * @param internalReference
	 * @param paymentMethod
	 * @param paymentMethodName
	 * @param issuerName
	 * @param amount
	 * @param authorization
	 * @param reference
	 * @param receipt
	 * @param franchise
	 * @param refunded
	 * @param processorFields
	 */
	public Payment(Status status, String internalReference, String paymentMethod, String paymentMethodName,
			String issuerName, Amount amount, String authorization, String reference, String receipt, String franchise,
			String refunded, List<ProcessorFields> processorFields) {
		super();
		this.status = status;
		this.internalReference = internalReference;
		this.paymentMethod = paymentMethod;
		this.paymentMethodName = paymentMethodName;
		this.issuerName = issuerName;
		this.amount = amount;
		this.authorization = authorization;
		this.reference = reference;
		this.receipt = receipt;
		this.franchise = franchise;
		this.refunded = refunded;
		this.processorFields = processorFields;
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
	 * @return the internalReference
	 */
	public String getInternalReference() {
		return internalReference;
	}

	/**
	 * @param internalReference
	 *            the internalReference to set
	 */
	public void setInternalReference(String internalReference) {
		this.internalReference = internalReference;
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
	 * @return the paymentMethodName
	 */
	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	/**
	 * @param paymentMethodName
	 *            the paymentMethodName to set
	 */
	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}

	/**
	 * @return the issuerName
	 */
	public String getIssuerName() {
		return issuerName;
	}

	/**
	 * @param issuerName
	 *            the issuerName to set
	 */
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	/**
	 * @return the amount
	 */
	public Amount getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	/**
	 * @return the authorization
	 */
	public String getAuthorization() {
		return authorization;
	}

	/**
	 * @param authorization
	 *            the authorization to set
	 */
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the receipt
	 */
	public String getReceipt() {
		return receipt;
	}

	/**
	 * @param receipt
	 *            the receipt to set
	 */
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	/**
	 * @return the franchise
	 */
	public String getFranchise() {
		return franchise;
	}

	/**
	 * @param franchise
	 *            the franchise to set
	 */
	public void setFranchise(String franchise) {
		this.franchise = franchise;
	}

	/**
	 * @return the refunded
	 */
	public String getRefunded() {
		return refunded;
	}

	/**
	 * @param refunded
	 *            the refunded to set
	 */
	public void setRefunded(String refunded) {
		this.refunded = refunded;
	}

	/**
	 * @return the processorFields
	 */
	public List<ProcessorFields> getProcessorFields() {
		return processorFields;
	}

	/**
	 * @param processorFields
	 *            the processorFields to set
	 */
	public void setProcessorFields(List<ProcessorFields> processorFields) {
		this.processorFields = processorFields;
	}

}
