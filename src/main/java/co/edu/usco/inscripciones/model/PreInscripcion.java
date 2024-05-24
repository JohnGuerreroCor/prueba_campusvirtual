/**
 * 
 */
package co.edu.usco.inscripciones.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ANDRES-GPIE
 *
 */
@JsonIgnoreProperties
public class PreInscripcion {
	private int id;
	private int codigo;
	private int emailVerificado;
	private Date fechaRegistro;
	private int autorizado;
	
	private Persona persona;
	private Oferta oferta;
	public PreInscripcion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PreInscripcion(int id, int codigo, int emailVerificado, Date fechaRegistro, int autorizado, Persona persona,
			Oferta oferta) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.emailVerificado = emailVerificado;
		this.fechaRegistro = fechaRegistro;
		this.autorizado = autorizado;
		this.persona = persona;
		this.oferta = oferta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getEmailVerificado() {
		return emailVerificado;
	}
	public void setEmailVerificado(int emailVerificado) {
		this.emailVerificado = emailVerificado;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public int getAutorizado() {
		return autorizado;
	}
	public void setAutorizado(int autorizado) {
		this.autorizado = autorizado;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Oferta getOferta() {
		return oferta;
	}
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	
		
}
