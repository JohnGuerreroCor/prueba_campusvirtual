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
public class Municipio {
	private int codigo;
	private String nombre;
	private Departamento Departamento;
	public Municipio() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Municipio(int codigo, String nombre, co.edu.usco.inscripciones.model.Departamento departamento) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		Departamento = departamento;
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
	public Departamento getDepartamento() {
		return Departamento;
	}
	public void setDepartamento(Departamento departamento) {
		Departamento = departamento;
	}

	
}
