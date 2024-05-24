package co.edu.usco.inscripciones.model;

import java.sql.Date;

public class Contrato {

	private int codigo;
	private Date fechaInicio;
	private Date fechaFin;
	private Persona persona;

	public Contrato() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contrato(int codigo, Date fechaInicio, Date fechaFin, Persona persona) {
		super();
		this.codigo = codigo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.persona = persona;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
