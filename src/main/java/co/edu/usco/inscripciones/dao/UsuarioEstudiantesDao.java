/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Estudiante;
import co.edu.usco.inscripciones.model.UsuarioEstudiante;

/**
 * @author ANDRES-GPIE
 *
 */
public interface UsuarioEstudiantesDao {

	/**
	 * Consultar el ultimo uid
	 * @return
	 */
	public UsuarioEstudiante consultarUID();
		
	/**
	 * Crear el usuario para el estudiante
	 * @param estudiante
	 * @return
	 */
	public boolean guardarUsuarioEstudiantes(final Estudiante estudiante, boolean proveedor);
	
	/**
	 * Guardar usuario grupo
	 * @param estudiante
	 * @return
	 */
	public boolean guardarUsuarioGrupo(final Estudiante estudiante);
	
	/**
	 * COnsultar si existe el usuario
	 * @param identificacion
	 * @return
	 */
	public UsuarioEstudiante consultarUsuarioEstudiantes(String identificacion);
	
	/**
	 * 
	 * @param uid
	 * @return
	 */
	int obligarCambio(int uid);

	boolean programarCambio(String usuario, String pin);
		
}
