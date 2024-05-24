package co.edu.usco.inscripciones.model;

public class RespuestaOpciones {

	private int codigo;
	private String descripcion;

	public RespuestaOpciones() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RespuestaOpciones(int codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
