/**
 * 
 */
package co.edu.usco.inscripciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.dao.UsuarioGeneralDao;
import co.edu.usco.inscripciones.model.Uaa;
import co.edu.usco.inscripciones.model.UsuarioGeneral;

/**
 * @author jankarlos
 *
 */
@Component
public class UsuarioGeneralServiceImpl implements UsuarioGeneralService {

	@Autowired
	UsuarioGeneralDao usuarioGeneralDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.usco.inscripciones.service.UsuarioGeneralService#validaUsuarioGral
	 * (int)
	 */
	@Override
	public UsuarioGeneral validaUsuarioGral(String usuario) {
		// TODO Auto-generated method stub
		return usuarioGeneralDao.consultarDatosUsuarioGeneral(usuario);
	}

	@Override
	public Uaa consultarUaaUsuarioGeneral(int persona, String estudiante, int tipo) {
		// TODO Auto-generated method stub
		return usuarioGeneralDao.consultarUaaUsuarioGeneral(persona, estudiante, tipo);
	}

	@Override
	public UsuarioGeneral validaUsuarioClaveGral(String usuario, String pw) {
		// TODO Auto-generated method stub
		return usuarioGeneralDao.validaUsuarioClaveGral(usuario, pw);
	}

}
