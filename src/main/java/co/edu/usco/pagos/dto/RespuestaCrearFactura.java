package co.edu.usco.pagos.dto;

public class RespuestaCrearFactura {

	private String estado;
	private String mensaje;
	private long codigofac;

	/**
	 * 
	 */
	public RespuestaCrearFactura() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param estado
	 * @param mensaje
	 * @param codigofac
	 */
	public RespuestaCrearFactura(String estado, String mensaje, long codigofac) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
		this.codigofac = codigofac;
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
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the codigofac
	 */
	public long getCodigofac() {
		return codigofac;
	}

	/**
	 * @param codigofac
	 *            the codigofac to set
	 */
	public void setCodigofac(long codigofac) {
		this.codigofac = codigofac;
	}

}
