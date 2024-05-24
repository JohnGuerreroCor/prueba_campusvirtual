/**
 * 
 */
package co.edu.usco.inscripciones.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.edu.usco.pagos.model.BancoCuenta;

/**
 * @author ANDRES-GPIE
 *
 */
@JsonIgnoreProperties
public class Factura {

	private int id;
	private long referencia;
	private String pin;
	private int autorizado;
	private Date fechaRecaudo;
	private Date fechaFacturaFecha;
	private float valorFacturaFecha;
	private float valorFactura;

	private Uaa uaa;
	private Persona persona;
	private Persona tercero;
	private String valorToken;
	private int valorTipoLiquidacion;
	private BancoCuenta BancoCuenta;
	private String Observacion;

	public Factura() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param referencia
	 * @param pin
	 * @param autorizado
	 * @param fechaRecaudo
	 * @param fechaFacturaFecha
	 * @param valorFacturaFecha
	 * @param valorFactura
	 * @param uaa
	 * @param persona
	 * @param tercero
	 * @param valorToken
	 * @param valorTipoLiquidacion
	 * @param bancoCuenta
	 * @param observacion
	 */
	public Factura(int id, long referencia, String pin, int autorizado, Date fechaRecaudo, Date fechaFacturaFecha,
			float valorFacturaFecha, float valorFactura, Uaa uaa, Persona persona, Persona tercero, String valorToken,
			int valorTipoLiquidacion, co.edu.usco.pagos.model.BancoCuenta bancoCuenta, String observacion) {
		super();
		this.id = id;
		this.referencia = referencia;
		this.pin = pin;
		this.autorizado = autorizado;
		this.fechaRecaudo = fechaRecaudo;
		this.fechaFacturaFecha = fechaFacturaFecha;
		this.valorFacturaFecha = valorFacturaFecha;
		this.valorFactura = valorFactura;
		this.uaa = uaa;
		this.persona = persona;
		this.tercero = tercero;
		this.valorToken = valorToken;
		this.valorTipoLiquidacion = valorTipoLiquidacion;
		BancoCuenta = bancoCuenta;
		Observacion = observacion;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the referencia
	 */
	public long getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia
	 *            the referencia to set
	 */
	public void setReferencia(long referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * @param pin
	 *            the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

	/**
	 * @return the autorizado
	 */
	public int getAutorizado() {
		return autorizado;
	}

	/**
	 * @param autorizado
	 *            the autorizado to set
	 */
	public void setAutorizado(int autorizado) {
		this.autorizado = autorizado;
	}

	/**
	 * @return the fechaRecaudo
	 */
	public Date getFechaRecaudo() {
		return fechaRecaudo;
	}

	/**
	 * @param fechaRecaudo
	 *            the fechaRecaudo to set
	 */
	public void setFechaRecaudo(Date fechaRecaudo) {
		this.fechaRecaudo = fechaRecaudo;
	}

	/**
	 * @return the fechaFacturaFecha
	 */
	public Date getFechaFacturaFecha() {
		return fechaFacturaFecha;
	}

	/**
	 * @param fechaFacturaFecha
	 *            the fechaFacturaFecha to set
	 */
	public void setFechaFacturaFecha(Date fechaFacturaFecha) {
		this.fechaFacturaFecha = fechaFacturaFecha;
	}

	/**
	 * @return the valorFacturaFecha
	 */
	public float getValorFacturaFecha() {
		return valorFacturaFecha;
	}

	/**
	 * @param valorFacturaFecha
	 *            the valorFacturaFecha to set
	 */
	public void setValorFacturaFecha(float valorFacturaFecha) {
		this.valorFacturaFecha = valorFacturaFecha;
	}

	/**
	 * @return the valorFactura
	 */
	public float getValorFactura() {
		return valorFactura;
	}

	/**
	 * @param valorFactura
	 *            the valorFactura to set
	 */
	public void setValorFactura(float valorFactura) {
		this.valorFactura = valorFactura;
	}

	/**
	 * @return the uaa
	 */
	public Uaa getUaa() {
		return uaa;
	}

	/**
	 * @param uaa
	 *            the uaa to set
	 */
	public void setUaa(Uaa uaa) {
		this.uaa = uaa;
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona
	 *            the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * @return the tercero
	 */
	public Persona getTercero() {
		return tercero;
	}

	/**
	 * @param tercero
	 *            the tercero to set
	 */
	public void setTercero(Persona tercero) {
		this.tercero = tercero;
	}

	/**
	 * @return the valorToken
	 */
	public String getValorToken() {
		return valorToken;
	}

	/**
	 * @param valorToken
	 *            the valorToken to set
	 */
	public void setValorToken(String valorToken) {
		this.valorToken = valorToken;
	}

	/**
	 * @return the valorTipoLiquidacion
	 */
	public int getValorTipoLiquidacion() {
		return valorTipoLiquidacion;
	}

	/**
	 * @param valorTipoLiquidacion
	 *            the valorTipoLiquidacion to set
	 */
	public void setValorTipoLiquidacion(int valorTipoLiquidacion) {
		this.valorTipoLiquidacion = valorTipoLiquidacion;
	}

	/**
	 * @return the bancoCuenta
	 */
	public BancoCuenta getBancoCuenta() {
		return BancoCuenta;
	}

	/**
	 * @param bancoCuenta
	 *            the bancoCuenta to set
	 */
	public void setBancoCuenta(BancoCuenta bancoCuenta) {
		BancoCuenta = bancoCuenta;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return Observacion;
	}

	/**
	 * @param observacion
	 *            the observacion to set
	 */
	public void setObservacion(String observacion) {
		Observacion = observacion;
	}

}
