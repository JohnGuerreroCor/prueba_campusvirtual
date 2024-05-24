/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.ArchivosAdjuntos;

/**
 * @author jankarlos
 *
 */
public interface ArchivosAdjuntosDao {
	/**
	 * Listar las Asignaturas
	 * @return Lista de las Asignaturas
	 */
	public ArchivosAdjuntos consultarRegistro(String nombreEncriptado);
	

}
