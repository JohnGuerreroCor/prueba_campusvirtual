/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Contrato;

/**
 * @author jankarlos
 *
 */
public interface ContratoDao {
	
	public Contrato validarContrato(String identificacion);
}
