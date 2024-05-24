/**
 * 
 */
package co.edu.usco.inscripciones.model;

/**
 * @author ANDRES-GPIE
 *
 */
public class PlanAcademico {
	private int id;
	private int codigo;
	private String nombre;
	/**
	 * 
	 */
	public PlanAcademico() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param codigo
	 * @param nombre
	 */
	public PlanAcademico(int id, int codigo, String nombre) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	

	
}
