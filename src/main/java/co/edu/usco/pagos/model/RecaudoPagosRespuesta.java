package co.edu.usco.pagos.model;

import java.sql.Timestamp;

import co.edu.usco.pagos.dto.Status;

public class RecaudoPagosRespuesta {

	private int codigo;
	private Status status;
	private Peticion peticion;
	private Timestamp fecha;

	/**
	 * 
	 */
	public RecaudoPagosRespuesta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codigo
	 * @param status
	 * @param peticion
	 * @param fecha
	 */
	public RecaudoPagosRespuesta(int codigo, Status status, Peticion peticion, Timestamp fecha) {
		super();
		this.codigo = codigo;
		this.status = status;
		this.peticion = peticion;
		this.fecha = fecha;
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
	 * @return the peticion
	 */
	public Peticion getPeticion() {
		return peticion;
	}

	/**
	 * @param peticion
	 *            the peticion to set
	 */
	public void setPeticion(Peticion peticion) {
		this.peticion = peticion;
	}

	/**
	 * @return the fecha
	 */
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

}
