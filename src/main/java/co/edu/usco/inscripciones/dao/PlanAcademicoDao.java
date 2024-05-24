/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.PlanAcademico;

/**
 * @author ANDRES-GPIE
 *
 */
public interface PlanAcademicoDao {

	/**
	 * 
	 * @param estudiante
	 * @return
	 */
	public PlanAcademico consultarPlanAcademico(int codigoPrograma);
		
}
