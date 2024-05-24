package co.edu.usco.controllers;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usco.inscripciones.dao.ContratoDao;
import co.edu.usco.inscripciones.dao.OfertaDao;
import co.edu.usco.inscripciones.dao.PreIncripcionDao;
import co.edu.usco.inscripciones.model.Contrato;
import co.edu.usco.inscripciones.model.Inscripcion;
import co.edu.usco.inscripciones.model.Oferta;
import co.edu.usco.inscripciones.model.PreInscripcion;
import co.edu.usco.inscripciones.model.UsuarioGeneral;
import co.edu.usco.inscripciones.service.InscripcionService;
import co.edu.usco.inscripciones.service.UsuarioGeneralService;
import co.edu.usco.inscripciones.utilidad.Respuesta;

@RestController
public class InscripcionRestController {

	@Autowired
	InscripcionService inscripcionService;

	@Autowired
	UsuarioGeneralService usuarioGeneralService;

	@Autowired
	ContratoDao contratoDao;

	@Autowired
	PreIncripcionDao preInscripcionDao;

	@Autowired
	OfertaDao ofertaDao;

	@Autowired
	DataSource dataSource;

	@RequestMapping(value = "/inscripcionUsuario", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> agregar(@RequestBody Inscripcion inscripcion) {
		Respuesta respuesta = inscripcionService.guardarInscripcion(inscripcion);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}

	@RequestMapping(value = "/inscripcionUsuarioInterno", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> agregarInterno(@RequestBody Inscripcion inscripcion) {
		if (inscripcion.isProveedor()) {
			Contrato contrato = new Contrato();
			if (inscripcion.getPersona().getIdentificacionProveedor().length() > 0) {
				contrato = contratoDao.validarContrato(inscripcion.getPersona().getIdentificacionProveedor());
				if (contrato != null) {

					PreInscripcion preIns = preInscripcionDao.validarPreInscripcion(
							inscripcion.getPersona().getIdentificacion(), inscripcion.getOferta().getCodigo());
					if (preIns == null) {
						PreInscripcion preInscripcion = new PreInscripcion();
						preInscripcion.setOferta(inscripcion.getOferta());
						preInscripcion.setPersona(inscripcion.getPersona());

						preInscripcionDao.agregarPreIncripcion(preInscripcion);
						/*
						 * respuesta.setRespuesta(Respuesta.EJECUCION_OK);
						 * return new ResponseEntity<Respuesta>(respuesta,
						 * HttpStatus.OK);
						 */

						Respuesta respuesta = new Respuesta(1, true,
								"Operación Exitosa, por favor revise su correo para continuar con el proceso!", null);
						return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
					} else {
						Respuesta respuesta = new Respuesta(0, false,
								"Ya tiene una pre-inscripcion previa, revise su correo.", null);
						return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
					}

				} else {
					Respuesta respuesta = new Respuesta(0, false, "No se encontro el provedor ingresado.", null);
					return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
				}
			} else {
				Respuesta respuesta = new Respuesta(0, false, "Los campos son obligatorios.", null);
				return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
			}

		} else {
			if (inscripcion.getUsuario().getUsuario() != "" && inscripcion.getUsuario().getPw() != "") {
				UsuarioGeneral ug = usuarioGeneralService.validaUsuarioClaveGral(inscripcion.getUsuario().getUsuario(),
						inscripcion.getUsuario().getPw());
				if (ug != null) {
					if (ug.getPersona().getCodigo() > 0) {
						Respuesta respuesta = inscripcionService.guardarInscripcionInterna(inscripcion);
						return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
					} else {
						Respuesta respuesta = new Respuesta(0, false,
								"Autenticación incorrecta, por favor verifique su usuario y clave!", null);
						return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
					}
				} else {
					Respuesta respuesta = new Respuesta(0, false,
							"Autenticación incorrecta, por favor verifique su usuario y clave.", null);
					return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
				}
			} else {
				Respuesta respuesta = new Respuesta(0, false, "Todos los campos son obligatorios.", null);

				return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
			}
		}
	}

	@RequestMapping(value = "inscripcionProv/{id}/{oferta}", method = RequestMethod.GET)
	public ResponseEntity<Respuesta> inscribirProveedor(@PathVariable String id, @PathVariable String oferta) {
		Oferta estadoOferta = ofertaDao.consultarEstadoOferta(0, oferta);
		PreInscripcion preInscripcionDatos = preInscripcionDao.consultarDatosPreInscripcion(id);

		Contrato contrato = new Contrato();

		if (preInscripcionDatos.getPersona().getIdentificacionProveedor().length() > 0) {
			contrato = contratoDao.validarContrato(preInscripcionDatos.getPersona().getIdentificacionProveedor());

			if (contrato != null) {
				Inscripcion inscripcion = new Inscripcion();
				inscripcion.setOferta(estadoOferta);
				inscripcion.setProveedor(true);
				inscripcion.setPersona(preInscripcionDatos.getPersona());

				Respuesta respuesta = inscripcionService.guardarInscripcionInterna(inscripcion);
				return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);

			} else {
				Respuesta respuesta = new Respuesta(0, false, "No se encontro el provedor ingresado.", null);
				return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
			}

		} else {
			Respuesta respuesta = new Respuesta(0, false, "Los campos son obligatorios.", null);
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		}

	}

	/*
	 * @RequestMapping(value = "inscripcionProvServiambiental", method =
	 * RequestMethod.GET) public String inscribirProveedorServiambeintal() {
	 * 
	 * String pin = UtilGenerarPinClave.generarPin();
	 * 
	 * //int estado = obligarCambio(134410);//real int estado =
	 * obligarCambio(133613);
	 * 
	 * String footer = "NOTA CONFIDENCIAL:<br>" +
	 * "Este mensaje es exclusivamente para el uso de la persona o entidad a quien está dirigido; La información contenida en este e-mail y en todos sus archivos anexos es completamente confidencial, de igual manera las opiniones expresadas son netamente personales, y no necesariamente transmiten el pensamiento de la Universidad Surcolombiana. Si usted no es el destinatario por favor háganoslo saber respondiendo a este correo y por favor destruya cualquier copia del mismo y sus adjuntos, cualquier almacenamiento, distribución, divulgación o copia de este mensaje está estrictamente prohibida y sancionada por la ley."
	 * + "Si por error recibe este mensaje, ofrecemos disculpas.<br><br>" +
	 * "CONFIDENTIAL NOTE:<br>" +
	 * "This message is exclusively for  use of the individual or entity to whom it is forwarded.  The information of this e-mail and all its attachments is completely confidential; likewise, the opinions expressed are purely personal and they do not necessarily convey the thought of  Surcolombiana University.  If you are not the addressee, please let us know it by replying to this e-mail and please delete any copy and its attachments.  Any storage, distribution, dissemination or copy of this information is strictly prohibited and punished  by law."
	 * ;
	 * 
	 * String asunto = "Datos acceso al portal - Universidad Surcolombiana";
	 * String mensaje = "";
	 * 
	 * if (estado == 1) {
	 * 
	 * String usuarioCambio = "830510145-9";
	 * 
	 * boolean programado = programarCambio(usuarioCambio, pin);
	 * 
	 * if (programado == true) {
	 * 
	 * mensaje = mensaje +
	 * "<div style='margin: 0 auto; padding: 30px; width: 80%; background-color: #f1f1f1;'>"
	 * ; mensaje = mensaje + "<center>"; mensaje = mensaje +
	 * "<img alt='Universidad Surcolombiana' class='img-responsive' src='https://www.usco.edu.co/imagen-institucional/logo/universidad-surcolombiana.png'>"
	 * ; mensaje = mensaje +
	 * "<h3>Universidad Surcolombiana - Restablecer Clave</h3>"; mensaje =
	 * mensaje + "</center>"; mensaje = mensaje +
	 * "<div style='padding: 20px; background-color: #FFFFFF' class='w3-code notranslate htmlHigh'>"
	 * ; mensaje = mensaje +
	 * "<p><span style='color: black'>Se&ntilde;or(a):</span> SERVIAMBIENTAL EMPRESA"
	 * ; mensaje = mensaje + "</p>"; mensaje = mensaje +
	 * "<p>Para la asignación de su clave de click en el boton cambiar clave para continuar con el proceso.</p>"
	 * ; mensaje = mensaje + "</div><br><br>"; mensaje = mensaje +
	 * "<a href='https://www.usco.edu.co/cambio_clave/ticket/" + pin +
	 * "' target='_blank'	style='padding: 5px; color: #FFFFFF; text-decoration: none; background-color: #B81726'>Cambiar la clave</a>"
	 * ; mensaje = mensaje + "</div>"; } else { return "OCURRIO ERROR"; } } else
	 * { return "ALGO MAL"; }
	 * 
	 * mensaje = mensaje + footer; // System.out.println(destinatario + " : " +
	 * asunto + " : " + // mensaje); new
	 * TheadEnvioCorreo("jankarlos.diaz@gmail.com", asunto, mensaje,
	 * "").start(); return mensaje; }
	 * 
	 * public int obligarCambio(int uid) {
	 * 
	 * JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	 * 
	 * String sql =
	 * "UPDATE usuario_estudiantes_virtual SET ischange = ? WHERE uid = ?;"; //
	 * String sql = "UPDATE usuario_estudiantes_virtual SET ischange = ?, //
	 * us_encriptado = HASHBYTES('SHA1', us) WHERE uid = ?;";
	 * jdbcTemplate.update(sql, new Object[] { 1, uid });
	 * 
	 * sql = "SELECT ischange FROM usuario_estudiantes_virtual WHERE uid = ?;";
	 * 
	 * return jdbcTemplate.queryForObject(sql, new Object[] { uid },
	 * Integer.class); }
	 * 
	 * public boolean programarCambio(final String usuario, final String pin) {
	 * 
	 * JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	 * 
	 * final String sql =
	 * "INSERT INTO recuperar_clave(rec_usuario, rec_clave, rec_istipo, rec_estado) VALUES (?, ?, ?, ?);"
	 * ;
	 * 
	 * KeyHolder keyHolder = new GeneratedKeyHolder(); int resultado =
	 * jdbcTemplate.update(new PreparedStatementCreator() {
	 * 
	 * @Override public PreparedStatement createPreparedStatement(Connection
	 * con) throws SQLException { PreparedStatement pstm =
	 * con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	 * pstm.setString(1, "0x" + Encriptar.toSha1String(usuario).toUpperCase());
	 * pstm.setBytes(2, Encriptar.toSha1Byte(pin)); pstm.setInt(3, 5);
	 * pstm.setInt(4, 1); return pstm; } }, keyHolder);
	 * 
	 * return resultado > 0 ? true : false; }
	 * 
	 */
}