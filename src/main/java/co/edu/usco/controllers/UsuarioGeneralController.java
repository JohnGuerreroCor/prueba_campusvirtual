/**
 * 
 */
package co.edu.usco.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usco.inscripciones.dao.MatriculaDao;
import co.edu.usco.inscripciones.dao.OfertaConfiguracionDao;
import co.edu.usco.inscripciones.dao.PersonaDao;
import co.edu.usco.inscripciones.dto.OfertaConfiguracionDTO;
import co.edu.usco.inscripciones.model.Matricula;
import co.edu.usco.inscripciones.model.Persona;
import co.edu.usco.inscripciones.model.Uaa;
import co.edu.usco.inscripciones.model.UsuarioGeneral;
import co.edu.usco.inscripciones.service.UsuarioGeneralService;
import co.edu.usco.inscripciones.utilidad.Respuesta;

/**
 * @author jankarlos
 *
 */
@RestController
public class UsuarioGeneralController {

	@Autowired
	UsuarioGeneralService usuarioGeneralService;

	@Autowired
	PersonaDao personaDao;

	@Autowired
	MatriculaDao matriculaDao;

	@Autowired
	OfertaConfiguracionDao ofertaConfiguracionDao;

	@RequestMapping(value = "validarUsuarioGeneral/{usuario}/{oferta}", method = RequestMethod.GET)
	public ResponseEntity<Respuesta> validarUsuGral(@PathVariable String usuario, @PathVariable int oferta) {
		String mensaje = "";
		boolean exito = false;
		int codigo = 0;
		UsuarioGeneral usuGral = new UsuarioGeneral();
		if (oferta > 0 && usuario.length() > 0) {
			// Listo de la oferta el tipo de usuarios y uaa a los que esta
			// ofertado
			List<OfertaConfiguracionDTO> listaOfertaInformacion = new ArrayList<OfertaConfiguracionDTO>();
			listaOfertaInformacion = ofertaConfiguracionDao.listarOfertaConfiguracion(oferta);

			// Consultar los datos del usuario en la vista usuario_general

			if (listaOfertaInformacion != null) {
				usuGral = usuarioGeneralService.validaUsuarioGral(usuario);
				if (usuGral != null) {
					for (OfertaConfiguracionDTO listaOferta : listaOfertaInformacion) {

						System.out.println("Primeros codigos:" + listaOferta.getCodigosUsuarios());

						String resp = listarUsuarios(listaOferta, usuGral);

						if (resp.equals("OK")) {
							Respuesta respuesta = new Respuesta(1, true, "OK", usuGral);
							return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
						} else {
							if (resp.equals("0") || resp.equals("1") || resp.equals("2") || resp.equals("3")
									|| resp.equals("4") || resp.equals("6")) {
								mensaje = msgUsuarios(resp);
								System.out.println("El mesnaje igaul a numero es:" + resp);
								Respuesta respuesta = new Respuesta(0, false, mensaje, usuGral);
								return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
							} else {
								System.out.println("El mesnaje diferente a numero es:" + resp);
								mensaje = resp;
							}
						}

					}
					/*
					 * Persona returnVinculacion = personaDao
					 * .validarVinculacionPersona(usuGral.getPersona().
					 * getIdentificacion()); if (returnVinculacion != null) {
					 * mensaje = "OK"; exito = true; codigo = 1; } else {
					 * mensaje =
					 * "El usuario no tiene vinculación activa con la universidad"
					 * ; }
					 */
				} else {
					mensaje = "No se encontro el usuario ingresado.";
				}
			} else
				mensaje = "No se encontraron los datos de la oferta.";
		} else {
			mensaje = "Los campos son obligatorios.";
		}

		Respuesta respuesta = new Respuesta(codigo, exito, mensaje, usuGral);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	public String listarUsuarios(OfertaConfiguracionDTO ofaConfig, UsuarioGeneral usuGral) {
		// TODO Auto-generated method stub
		int tipo = usuGral.getTipo();
		String cedula = usuGral.getPersona().getIdentificacion();
		String codigo = ofaConfig.getCodigosUsuarios();
		String respuesta = "";

		String usu = codigo;
		String usuArray[] = usu.split(",");

		for (String us : usuArray) {
			System.out.println("EL TIPO DE USUARIO ES:" + us);
			System.out.println("El tipo es:" + tipo);
			switch (Integer.parseInt(us)) {
			case 1:
				// Administrativos
				if (us.equals(String.valueOf(tipo))) {
					System.out.println("ENTRO 1 = 1");
					Persona returnVinculacion = personaDao.validarVinculacionPersona(cedula);
					if (returnVinculacion != null) {
						System.out.println("HAY VINCULACION");
						if (ofaConfig.getCodigosUaa() != null) {
							Uaa uaa = usuarioGeneralService.consultarUaaUsuarioGeneral(usuGral.getPersona().getCodigo(),
									"", tipo);
							int resp = validarUaa(uaa.getCodigo(), uaa.getDependencia(), ofaConfig.getCodigosUaa());
							if (resp == 1) {
								respuesta = "OK";
								return respuesta;
							} else {
								respuesta = "El usuario no pertenece a la dependencia requerida, pertenece a: "
										+ uaa.getNombre();
								return respuesta;
							}
						} else {
							respuesta = "OK";
							return respuesta;
						}
					} else {
						System.out.println("NO HAY VINCULACION");
						respuesta = "1";
						return respuesta;
					}
				} else {
					respuesta = "Este usuario no pertenece a ninguno de los tipos de usuarios requeridos para esta oferta.";
				}
				break;
			case 2:
				// Estudiante
				if (us.equals(String.valueOf(tipo))) {
					Matricula returnVinculacion = matriculaDao.validarMatriculaActiva(usuGral.getUsuario());
					if (returnVinculacion != null) {
						if (ofaConfig.getCodigosUaa() != null) {
							Uaa uaa = usuarioGeneralService.consultarUaaUsuarioGeneral(0,
									usuGral.getUsuario().replace("u", ""), tipo);
							System.out.println("La uaa del estudiante es:" + uaa.getCodigo() + " Uaa codigos"
									+ ofaConfig.getCodigosUaa());
							int resp = validarUaa(uaa.getCodigo(), uaa.getDependencia(), ofaConfig.getCodigosUaa());
							if (resp == 1) {
								respuesta = "OK";
								return respuesta;
							} else {
								respuesta = "El usuario no pertenece a la dependencia requerida, pertenece a: "
										+ uaa.getNombre();
								return respuesta;
							}
						} else {
							respuesta = "OK";
							return respuesta;
						}
					} else {
						respuesta = "2";
						return respuesta;
					}
				} else {
					respuesta = "Este usuario no pertenece a ninguno de los tipos de usuarios requeridos para esta oferta.";
				}
				break;
			case 3:
				// Docentes
				if (us.equals(String.valueOf(tipo))) {
					Persona returnVinculacion = personaDao.validarVinculacionPersona(cedula);
					if (returnVinculacion != null) {
						if (ofaConfig.getCodigosUaa() != null) {
							Uaa uaa = usuarioGeneralService.consultarUaaUsuarioGeneral(usuGral.getPersona().getCodigo(),
									"", tipo);
							int resp = validarUaa(uaa.getCodigo(), uaa.getDependencia(), ofaConfig.getCodigosUaa());
							if (resp == 1) {
								respuesta = "OK";
								return respuesta;
							} else {
								respuesta = "El Docente no pertenece a la dependencia requerida, pertenece a: "
										+ uaa.getNombre();
								return respuesta;
							}
						} else {
							respuesta = "OK";
							return respuesta;
						}
					} else {
						respuesta = "3";
						return respuesta;
					}
				} else {
					respuesta = "Este usuario no pertenece a ninguno de los tipos de usuarios requeridos para esta oferta.";
				}
				break;
			case 6:
				// Proveedores
				if (us.equals(String.valueOf(tipo))) {
					Persona returnVinculacion = personaDao.validarVinculacionPersona(cedula);
					if (returnVinculacion != null) {
						respuesta = "OK";
						return respuesta;
					} else {
						respuesta = "6";
						return respuesta;
					}
				} else {
					respuesta = "Este usuario no pertenece a ninguno de los tipos de usuarios requeridos para esta oferta.";
				}
				break;
			case 0:
				// Todos
				if (us.equals(String.valueOf(tipo))) {
					Persona returnVinculacion = personaDao.validarVinculacionPersona(cedula);
					if (returnVinculacion != null) {
						respuesta = "OK";
						return respuesta;
					} else {
						respuesta = "0";
						return respuesta;
					}
				} else {
					respuesta = "Este usuario no pertenece a ninguno de los tipos de usuarios requeridos para esta oferta.";
				}
				break;
			}
		}

		return respuesta;
	}

	public String msgUsuarios(String codigo) {
		// TODO Auto-generated method stub
		String msj = "";
		switch (Integer.parseInt(codigo)) {
		case 1:
			// Administrativos
			msj = "Por favor verifique que su vinculación con la universidad se encuentre activa.";
			break;
		case 2:
			// Estudiante
			msj = "El estudiante no se encuentra activo.";
			break;
		case 3:
			// Docentes
			msj = "Por favor verifique que su vinculación como docente con la universidad se encuentre activa.";
			break;
		case 6:
			// Proveedores
			msj = "El proveedor no se encuentra activo";
			break;
		case 0:
			// Todos
			msj = "Todos no se encuentran";
			break;
		}
		return msj;
	}

	public int validarUaa(int uaaUsuario, int uaaDep, String uaaOferta) {

		String usuOfa[] = uaaOferta.split(",");
		int respuesta = 0;
		for (String uaaf : usuOfa) {
			if (uaaf.equals(String.valueOf(uaaUsuario)) || uaaf.equals(String.valueOf(uaaDep))) {
				respuesta = 1;
			}
		}
		return respuesta;
	}
}
