/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.PlanEstudiante;

/**
 * @author ANDRES-GPIE
 *
 */
public interface PlanEstudianteDao {
	/**
	 * Agrega una nueva admision
	 * @param Datos de la incripcion
	 * @return guardado o no
	 */
	public boolean agregarPlanEstudiante(PlanEstudiante planEstudiante);
		
}
