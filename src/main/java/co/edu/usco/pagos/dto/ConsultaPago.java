package co.edu.usco.pagos.dto;

public class ConsultaPago {

	private String factura;
	private String autorizacion;
	private String estado;
	private String fecha;
	private float valor;
	private long identificacion;

	/**
	 * 
	 */
	public ConsultaPago() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param factura
	 * @param autorizacion
	 * @param estado
	 * @param fecha
	 * @param valor
	 * @param identificacion
	 */
	public ConsultaPago(String factura, String autorizacion, String estado, String fecha, float valor,
			long identificacion) {
		super();
		this.factura = factura;
		this.autorizacion = autorizacion;
		this.estado = estado;
		this.fecha = fecha;
		this.valor = valor;
		this.identificacion = identificacion;
	}

	/**
	 * @return the factura
	 */
	public String getFactura() {
		return factura;
	}

	/**
	 * @param factura
	 *            the factura to set
	 */
	public void setFactura(String factura) {
		this.factura = factura;
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

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the valor
	 */
	public float getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(float valor) {
		this.valor = valor;
	}

	/**
	 * @return the identificacion
	 */
	public long getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion
	 *            the identificacion to set
	 */
	public void setIdentificacion(long identificacion) {
		this.identificacion = identificacion;
	}

}
