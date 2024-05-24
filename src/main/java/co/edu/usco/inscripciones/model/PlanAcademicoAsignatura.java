/**
 * 
 */
package co.edu.usco.inscripciones.model;

/**
 * @author ANDRES-GPIE
 *
 */
public class PlanAcademicoAsignatura {
	private int codigo;
	private Asignatura asignatura;
	/**
	 * 
	 */
	public PlanAcademicoAsignatura() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param codigo
	 * @param asignatura
	 */
	public PlanAcademicoAsignatura(int codigo, Asignatura asignatura) {
		super();
		this.codigo = codigo;
		this.asignatura = asignatura;
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
	 * @return the asignatura
	 */
	public Asignatura getAsignatura() {
		return asignatura;
	}
	/**
	 * @param asignatura the asignatura to set
	 */
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	
	
	
}
