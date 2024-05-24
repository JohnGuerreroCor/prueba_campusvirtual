package co.edu.usco.inscripciones.model;

public class PreguntaRespuestas {

	private int codigo;
	private RespuestaOpciones respuestaOpciones;
	private Pregunta pregunta;

	public PreguntaRespuestas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PreguntaRespuestas(int codigo, RespuestaOpciones respuestaOpciones, Pregunta pregunta) {
		super();
		this.codigo = codigo;
		this.respuestaOpciones = respuestaOpciones;
		this.pregunta = pregunta;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public RespuestaOpciones getRespuestaOpciones() {
		return respuestaOpciones;
	}

	public void setRespuestaOpciones(RespuestaOpciones respuestaOpciones) {
		this.respuestaOpciones = respuestaOpciones;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

}
