/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Curso;

/**
 * @author ANDRES-GPIE
 *
 */
public interface CursoDao {
	
	/**
	 * Buscar el plan academico para el plan academico seleccionado
	 * @param pla_codigo
	 * @return
	 */
	public Curso buscarCurso(int asi_codigo, int pla_codigo, int cal_codigo);
		
}
