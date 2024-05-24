/**
 * 
 */
package co.edu.usco.inscripciones.model;

/**
 * @author ANDRES-GPIE
 *
 */
public class Asignatura {
	private int codigo;
	private String nombre;
	private String nombreCorto;
	private String nombreImpresion;
	private int uaa;
	/**
	 * 
	 */
	public Asignatura() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param codigo
	 * @param nombre
	 * @param nombreCorto
	 * @param nombreImpresion
	 * @param uaa
	 */
	public Asignatura(int codigo, String nombre, String nombreCorto, String nombreImpresion, int uaa) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.nombreCorto = nombreCorto;
		this.nombreImpresion = nombreImpresion;
		this.uaa = uaa;
	}
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
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
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the nombreCorto
	 */
	public String getNombreCorto() {
		return nombreCorto;
	}
	/**
	 * @param nombreCorto the nombreCorto to set
	 */
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	/**
	 * @return the nombreImpresion
	 */
	public String getNombreImpresion() {
		return nombreImpresion;
	}
	/**
	 * @param nombreImpresion the nombreImpresion to set
	 */
	public void setNombreImpresion(String nombreImpresion) {
		this.nombreImpresion = nombreImpresion;
	}
	/**
	 * @return the uaa
	 */
	public int getUaa() {
		return uaa;
	}
	/**
	 * @param uaa the uaa to set
	 */
	public void setUaa(int uaa) {
		this.uaa = uaa;
	}
	
	
}
