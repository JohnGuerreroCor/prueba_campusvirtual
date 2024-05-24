/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Uaa;
import co.edu.usco.inscripciones.model.UsuarioGeneral;

/**
 * @author jankarlos
 *
 */
public interface UsuarioGeneralDao {

	public UsuarioGeneral consultarDatosUsuarioGeneral(String usuario);

	public Uaa consultarUaaUsuarioGeneral(int persona, String estudiante, int tipo);
	
	public UsuarioGeneral validaUsuarioClaveGral(String usuario, String pw);
}
