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
public class TipoIdentificacion {
	private int codigo;
	private String nombre;
	private String nombreCorto;
	private String pse;
	/**
	 * 
	 */
	public TipoIdentificacion() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param codigo
	 * @param nombre
	 * @param nombreCorto
	 * @param pse
	 */
	public TipoIdentificacion(int codigo, String nombre, String nombreCorto, String pse) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.nombreCorto = nombreCorto;
		this.pse = pse;
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
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public String getPse() {
		return pse;
	}
	public void setPse(String pse) {
		this.pse = pse;
	}
	
	
}
