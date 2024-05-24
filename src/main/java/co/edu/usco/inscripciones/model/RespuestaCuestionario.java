package co.edu.usco.inscripciones.model;

public class RespuestaCuestionario {

	private int codigo;
	private Cuestionario cuestionario;
	private Persona persona;

	public RespuestaCuestionario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RespuestaCuestionario(int codigo, Cuestionario cuestionario, Persona persona) {
		super();
		this.codigo = codigo;
		this.cuestionario = cuestionario;
		this.persona = persona;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Cuestionario getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(Cuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
