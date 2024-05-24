/**
 * 
 */
package co.edu.usco.inscripciones.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ANDRES-GPIE
 *
 */
@JsonIgnoreProperties
public class Programa {
	private int codigo;
	private int codigoUaa;
	private String nombre;
	private String nombreUaa;
	private String horario;
	private String estado;
	
	private OfertaRequisitos requisitos;

	public Programa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Programa(int codigo, int codigoUaa, String nombre, String nombreUaa, String horario, String estado,
			OfertaRequisitos requisitos) {
		super();
		this.codigo = codigo;
		this.codigoUaa = codigoUaa;
		this.nombre = nombre;
		this.nombreUaa = nombreUaa;
		this.horario = horario;
		this.estado = estado;
		this.requisitos = requisitos;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoUaa() {
		return codigoUaa;
	}

	public void setCodigoUaa(int codigoUaa) {
		this.codigoUaa = codigoUaa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUaa() {
		return nombreUaa;
	}

	public void setNombreUaa(String nombreUaa) {
		this.nombreUaa = nombreUaa;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public OfertaRequisitos getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(OfertaRequisitos requisitos) {
		this.requisitos = requisitos;
	}

	
}