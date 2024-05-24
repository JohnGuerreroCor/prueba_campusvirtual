package co.edu.usco.pagos.model;

import java.sql.Date;

import co.edu.usco.inscripciones.model.Factura;

public class FacturaFecha {

	private long codigo;
	private Date fecha;
	private float valor;
	private Factura factura;

	/**
	 * 
	 */
	public FacturaFecha() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codigo
	 * @param fecha
	 * @param valor
	 * @param factura
	 */
	public FacturaFecha(long codigo, Date fecha, float valor, Factura factura) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.valor = valor;
		this.factura = factura;
	}

	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the valor
	 */
	public float getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(float valor) {
		this.valor = valor;
	}

	/**
	 * @return the factura
	 */
	public Factura getFactura() {
		return factura;
	}

	/**
	 * @param factura the factura to set
	 */
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	
}
