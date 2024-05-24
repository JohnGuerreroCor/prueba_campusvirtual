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
public class Pais {
	private int codigo;
	private String nombre;
	private String acronimo;

	/**
	 * 
	 */
	public Pais() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codigo
	 * @param nombre
	 * @param acronimo
	 */
	public Pais(int codigo, String nombre, String acronimo) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.acronimo = acronimo;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the acronimo
	 */
	public String getAcronimo() {
		return acronimo;
	}

	/**
	 * @param acronimo
	 *            the acronimo to set
	 */
	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

}
