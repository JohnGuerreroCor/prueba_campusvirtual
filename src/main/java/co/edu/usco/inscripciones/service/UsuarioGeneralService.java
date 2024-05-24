/**
 * 
 */
package co.edu.usco.inscripciones.service;

import co.edu.usco.inscripciones.model.Uaa;
import co.edu.usco.inscripciones.model.UsuarioGeneral;

/**
 * @author jankarlos
 *
 */
public interface UsuarioGeneralService {

	public UsuarioGeneral validaUsuarioGral(String usuario);

	public Uaa consultarUaaUsuarioGeneral(int persona, String estudiante, int tipo);

	public UsuarioGeneral validaUsuarioClaveGral(String usuario, String pw);

}
