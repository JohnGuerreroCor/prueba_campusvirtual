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
public class Departamento {
	private int codigo;
	private String nombre;
	private Pais Pais;
	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Departamento(int codigo, String nombre, co.edu.usco.inscripciones.model.Pais pais) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		Pais = pais;
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
	public Pais getPais() {
		return Pais;
	}
	public void setPais(Pais pais) {
		Pais = pais;
	}
	
		
}
