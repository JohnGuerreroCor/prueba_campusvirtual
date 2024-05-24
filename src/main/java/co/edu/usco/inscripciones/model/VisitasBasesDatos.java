package co.edu.usco.inscripciones.model;

import java.util.Date;

public class VisitasBasesDatos {
	private int id;
	private String usuario;
	private String link;
	private String tipo;
	private Date fecha;
	private Long fechaLong;
	private String servidor;
	private String nombre;
	public VisitasBasesDatos() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VisitasBasesDatos(int id, String usuario, String link, String tipo, Date fecha, Long fechaLong,
			String servidor, String nombre) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.link = link;
		this.tipo = tipo;
		this.fecha = fecha;
		this.fechaLong = fechaLong;
		this.servidor = servidor;
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Long getFechaLong() {
		return fechaLong;
	}
	public void setFechaLong(Long fechaLong) {
		this.fechaLong = fechaLong;
	}
	public String getServidor() {
		return servidor;
	}
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
