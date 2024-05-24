package co.edu.usco.inscripciones.model;

public class Respuesta {

	private int codigo;
	private String adicional;
	private String texto;
	private RespuestaCuestionario rspuestaCuestionario;
	private PreguntaRespuestas preguntaRespuestas;

	public Respuesta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Respuesta(int codigo, String adicional, String texto, RespuestaCuestionario rspuestaCuestionario,
			PreguntaRespuestas preguntaRespuestas) {
		super();
		this.codigo = codigo;
		this.adicional = adicional;
		this.texto = texto;
		this.rspuestaCuestionario = rspuestaCuestionario;
		this.preguntaRespuestas = preguntaRespuestas;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getAdicional() {
		return adicional;
	}

	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public RespuestaCuestionario getRspuestaCuestionario() {
		return rspuestaCuestionario;
	}

	public void setRspuestaCuestionario(RespuestaCuestionario rspuestaCuestionario) {
		this.rspuestaCuestionario = rspuestaCuestionario;
	}

	public PreguntaRespuestas getPreguntaRespuestas() {
		return preguntaRespuestas;
	}

	public void setPreguntaRespuestas(PreguntaRespuestas preguntaRespuestas) {
		this.preguntaRespuestas = preguntaRespuestas;
	}

}
