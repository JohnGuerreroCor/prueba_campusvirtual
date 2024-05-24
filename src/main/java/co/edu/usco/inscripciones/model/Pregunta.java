package co.edu.usco.inscripciones.model;

public class Pregunta {

	private int codigo;
	private String descripcion;
	private Cuestionario cuestionario;
	private String adicional;
	private TipoRespuesta tipoRespuesta;

	public Pregunta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pregunta(int codigo, String descripcion, Cuestionario cuestionario, String adicional,
			TipoRespuesta tipoRespuesta) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.cuestionario = cuestionario;
		this.adicional = adicional;
		this.tipoRespuesta = tipoRespuesta;
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

	public Cuestionario getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(Cuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	public String getAdicional() {
		return adicional;
	}

	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}

	public TipoRespuesta getTipoRespuesta() {
		return tipoRespuesta;
	}

	public void setTipoRespuesta(TipoRespuesta tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

}
