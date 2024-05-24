package co.edu.usco.pagos.model;

import co.edu.usco.inscripciones.model.Factura;

public class Recaudo {

	private int codigo;
	private Factura factura;
	private int valor;
	private BancoCuenta bancoCuenta;

	/**
	 * 
	 */
	public Recaudo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codigo
	 * @param factura
	 * @param valor
	 * @param bancoCuenta
	 */
	public Recaudo(int codigo, Factura factura, int valor, BancoCuenta bancoCuenta) {
		super();
		this.codigo = codigo;
		this.factura = factura;
		this.valor = valor;
		this.bancoCuenta = bancoCuenta;
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
	 * @return the valor
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}

	/**
	 * @return the bancoCuenta
	 */
	public BancoCuenta getBancoCuenta() {
		return bancoCuenta;
	}

	/**
	 * @param bancoCuenta
	 *            the bancoCuenta to set
	 */
	public void setBancoCuenta(BancoCuenta bancoCuenta) {
		this.bancoCuenta = bancoCuenta;
	}

}
