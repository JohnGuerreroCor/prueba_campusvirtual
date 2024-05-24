package co.edu.usco.inscripciones.dto;

import java.util.List;

import co.edu.usco.inscripciones.model.Respuesta;

public class RespuestaDTO {

	private List<Respuesta> listRespuestas;
	private int cuestionario;
	private String identificacion;

	public RespuestaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RespuestaDTO(List<Respuesta> listRespuestas, int cuestionario, String identificacion) {
		super();
		this.listRespuestas = listRespuestas;
		this.cuestionario = cuestionario;
		this.identificacion = identificacion;
	}

	public List<Respuesta> getListRespuestas() {
		return listRespuestas;
	}

	public void setListRespuestas(List<Respuesta> listRespuestas) {
		this.listRespuestas = listRespuestas;
	}

	public int getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(int cuestionario) {
		this.cuestionario = cuestionario;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

}
