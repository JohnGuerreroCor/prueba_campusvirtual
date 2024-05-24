/**
 * 
 */
package co.edu.usco.pagos.model;

import co.edu.usco.inscripciones.model.Factura;
import co.edu.usco.pagos.dto.Status;

/**
 * @author jankarlos
 *
 */
public class Peticion {

	private int codigo;
	private int requestId;
	private Factura factura;
	private Status status;
	private String autorizacion;

	/**
	 * 
	 */
	public Peticion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codigo
	 * @param requestId
	 * @param factura
	 * @param status
	 * @param autorizacion
	 */
	public Peticion(int codigo, int requestId, Factura factura, Status status, String autorizacion) {
		super();
		this.codigo = codigo;
		this.requestId = requestId;
		this.factura = factura;
		this.status = status;
		this.autorizacion = autorizacion;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	 * @return the factura
	 */
	public Factura getFactura() {
		return factura;
	}

	/**
	 * @param factura
	 *            the factura to set
	 */
	public void setFactura(Factura factura) {
		this.factura = factura;
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
	 * @return the autorizacion
	 */
	public String getAutorizacion() {
		return autorizacion;
	}

	/**
	 * @param autorizacion
	 *            the autorizacion to set
	 */
	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

}
