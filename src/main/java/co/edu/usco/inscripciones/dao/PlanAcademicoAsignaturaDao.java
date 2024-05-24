/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import java.util.List;

import co.edu.usco.inscripciones.model.PlanAcademicoAsignatura;

/**
 * @author ANDRES-GPIE
 *
 */
public interface PlanAcademicoAsignaturaDao {
	
	/**
	 * Buscar el plan academico para el plan academico seleccionado
	 * @param pla_codigo
	 * @return
	 */
	public List<PlanAcademicoAsignatura> buscarPlanAcademicoAsignatura(int pla_codigo);
		
}
