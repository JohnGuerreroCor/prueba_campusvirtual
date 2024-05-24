package co.edu.usco.inscripciones.model;

public class Pqr {
	private String nombre;
	private String correo;
	private String asunto;
	private String mensaje;
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/**
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}
	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @param nombre
	 * @param correo
	 * @param asunto
	 * @param mensaje
	 */
	public Pqr(String nombre, String correo, String asunto, String mensaje) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}
	/**
	 * 
	 */
	public Pqr() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
