/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Estudiante;

/**
 * @author ANDRES-GPIE
 *
 */
public interface EstudianteDao {
	
	/**
	 * Agrega una nueva inscripcion
	 * @param persona Datos persona a inscribirse
	 * @return 
	 */
	public boolean guardarEstudiante(Estudiante estudiante);
	
	/**
	 * Cnsultar si existe la persona
	 * @param persona
	 * @return per_codigo
	 */
	public Estudiante consultarEstudiante(long perCodigo);	
		
}
