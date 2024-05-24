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
public class Eps {
	private int codigo;
	private String nombre;
	public Eps() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Eps(int codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
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

	
}
