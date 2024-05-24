package co.edu.usco.inscripciones.model;

public class TipoRespuesta {

	private int codigo;
	private String nombre;

	public TipoRespuesta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoRespuesta(int codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
