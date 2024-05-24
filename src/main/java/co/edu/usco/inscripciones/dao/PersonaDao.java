/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Persona;

/**
 * @author ANDRES-GPIE
 *
 */
public interface PersonaDao {

	/**
	 * Agregar nueva persona
	 * 
	 * @param persona Datos persona
	 * @return
	 */
	public boolean guardarPersona(Persona persona);

	/**
	 * Agregar nuevo tercero
	 * 
	 * @param persona Datos persona
	 * @return
	 */
	// public boolean guardarTercero(final Persona persona);

	/**
	 * Actualizar informacion tercero
	 * 
	 * @param persona
	 * @return
	 */
	public boolean actualizarTercero(final Persona persona);

	/**
	 * Actualizar datos persona por medio del procedimiento almacenado
	 * 
	 * @param persona
	 * @return
	 */
	public boolean ActualizarPersonaPA(final Persona persona);

	/**
	 * Actualizar persona
	 * 
	 * @param persona
	 * @return
	 */
	public boolean actualizarPersona(final Persona persona);

	/**
	 * Agregar nuevo tercero Pre Inscripcion
	 * 
	 * @param persona Datos persona
	 * @return
	 */

	public boolean guardarTerceroPreInscripcion(final Persona persona);

	/**
	 * Cnsultar si existe la persona
	 * 
	 * @param persona
	 * @return per_codigo
	 */
	public Persona consultarPersona(String identificacion);

	/**
	 * Consultar en los registros de la universidad
	 * 
	 * @param identificacion
	 * @return
	 */
	public Persona consultarPersonaUniversidad(String identificacion);

	public Persona convertirTerceroEnPersona(String cedula);

	/**
	 * Consultar si la persona tiene activa la vinculacion a la universidad
	 * 
	 * @param identificacion
	 * @return
	 */
	public Persona validarVinculacionPersona(String identificacion);

}
