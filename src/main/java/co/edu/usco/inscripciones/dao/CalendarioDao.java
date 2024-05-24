/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Calendario;

/**
 * @author ANDRES-GPIE
 *
 */
public interface CalendarioDao {

	/**
	 * 
	 * @param estudiante
	 * @return
	 */
	public Calendario consultarCalendario(int codigoCalendario);
		
}
