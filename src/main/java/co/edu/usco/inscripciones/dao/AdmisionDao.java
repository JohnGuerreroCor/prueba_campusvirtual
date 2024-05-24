/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Admision;

/**
 * @author ANDRES-GPIE
 *
 */
public interface AdmisionDao {
	/**
	 * Agrega una nueva admision
	 * @param Datos de la incripcion
	 * @return guardado o no
	 */
	public boolean agregarAdmision(Admision admision);
		
}
