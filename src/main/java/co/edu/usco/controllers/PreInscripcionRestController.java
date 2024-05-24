package co.edu.usco.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import co.edu.usco.inscripciones.dao.FacturaDao;
import co.edu.usco.inscripciones.dao.InscripcionDao;
import co.edu.usco.inscripciones.dao.OfertaDao;
import co.edu.usco.inscripciones.dao.PersonaDao;
import co.edu.usco.inscripciones.dao.PreIncripcionDao;
import co.edu.usco.inscripciones.model.Factura;
import co.edu.usco.inscripciones.model.Inscripcion;
import co.edu.usco.inscripciones.model.Oferta;
import co.edu.usco.inscripciones.model.Persona;
import co.edu.usco.inscripciones.model.PreInscripcion;
import co.edu.usco.inscripciones.utilidad.Constantes;
import co.edu.usco.inscripciones.utilidad.Respuesta;
import co.edu.usco.librerias.DiferenciaFechas;
import co.edu.usco.pagos.model.FacturaFecha;
import co.edu.usco.pagos.service.FacturaFechaService;;

@RestController
public class PreInscripcionRestController {

	@Autowired
	DataSource dataSourceAcademiaInvitado;

	@Autowired
	PreIncripcionDao preInscripcionDao;

	@Autowired
	PersonaDao personaDao;

	@Autowired
	OfertaDao ofertaDao;

	@Autowired
	Constantes constantes;

	@Autowired
	InscripcionDao inscripcionDao;

	@Autowired
	FacturaFechaService facturaFechaService;

	@Autowired
	FacturaDao facturaDao;

	@RequestMapping(value = "/preInscripcion2", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<PreInscripcion> getPreInscripcion2() {
		List<PreInscripcion> listOfPreInscripcion = new ArrayList<PreInscripcion>();
		listOfPreInscripcion = preInscripcionDao.listarPreInscripciones();
		return listOfPreInscripcion;
	}

	@RequestMapping(value = "/preInscripcion", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> adicionar(@RequestBody final PreInscripcion preInscripcion) {

		Respuesta respuesta = new Respuesta();
		// Buscar si tiene algun registro en educacion no formal
		Persona persona = personaDao.consultarPersona(preInscripcion.getPersona().getIdentificacion());
		if (persona != null) {
			preInscripcion.getPersona().setId(persona.getId());
			preInscripcion.getPersona().setTercero(persona.isTercero());
		} else {
			// Buscar en persona y tercero de la base de datos de la universidad
			persona = personaDao.consultarPersonaUniversidad(preInscripcion.getPersona().getIdentificacion());
			if (persona != null) {
				preInscripcion.getPersona().setId(persona.getId());
				preInscripcion.getPersona().setTercero(persona.isTercero());
			} else {
				personaDao.guardarTerceroPreInscripcion(preInscripcion.getPersona());
			}
		}
		PreInscripcion preInscripcionDatos = preInscripcionDao.validarPreInscripcion(
				preInscripcion.getPersona().getIdentificacion(), preInscripcion.getOferta().getCodigo());
		if (preInscripcionDatos != null) {
			long diferencia = DiferenciaFechas.diferencia(preInscripcionDatos.getFechaRegistro());
			if (diferencia <= 1) {
				if (preInscripcionDatos.getId() > 0) {
					respuesta.setRespuesta(Respuesta.PREINSCRIPCION_EXISTE);
					return new ResponseEntity<Respuesta>(respuesta, HttpStatus.BAD_REQUEST);
				}
			}
		}
		preInscripcionDao.agregarPreIncripcion(preInscripcion);
		respuesta.setRespuesta(Respuesta.EJECUCION_OK);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);

	}

	@RequestMapping(value = "/preInscripcionValidar/{id}/{oferta}/", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView modificar(@PathVariable String id, @PathVariable String oferta)
			throws NoSuchAlgorithmException, IOException, ParseException {

		Oferta estadoOferta = ofertaDao.consultarEstadoOferta(0, oferta);

		ModelAndView mav;

		if (!estadoOferta.getInterna().equals("1")) {
			mav = new ModelAndView("mostrarConfirmacionCorreo");
		} else {
			mav = new ModelAndView("mostrarConfirmacionCorreoPro");
		}

		mav.addObject("constantes", constantes);
		mav.addObject("id", id);
		mav.addObject("oferta", oferta);
		mav.addObject("facturaPaga", "0");

		if (estadoOferta.getCodigo() != 2323 && estadoOferta.getCodigo() != 2324 && estadoOferta.getCodigo() != 2325 && estadoOferta.getCodigo() != 2326 && estadoOferta.getCodigo() != 2327 && estadoOferta.getCodigo() != 2328 && estadoOferta.getCodigo() != 2329) {
			mav.addObject("requierePago", estadoOferta.getRequierePago());
		} else {
			mav.addObject("requierePago", 0);
		}

		PreInscripcion preInscripcionDatos = preInscripcionDao.consultarDatosPreInscripcion(id);
		long diferencia = DiferenciaFechas.diferencia(preInscripcionDatos.getFechaRegistro());
		// VALIDAR Q YA NO ESTE INSCRITO
		if (!estadoOferta.getInterna().equals("1")) {
			System.out.println("EL CODGIO DE LA OFERTA ES::::::: " + estadoOferta.getCodigo());
			if (estadoOferta.getRequierePago() == 1 && estadoOferta.getCodigo() != 2323 && estadoOferta.getCodigo() != 2324 && estadoOferta.getCodigo() != 2325 && estadoOferta.getCodigo() != 2326 && estadoOferta.getCodigo() != 2327 && estadoOferta.getCodigo() != 2328 && estadoOferta.getCodigo() != 2329) { // consulto datos de
																							// lapreinscripcion de la
																							// factura paga
				FacturaFecha ff = facturaFechaService.consultar(0,
						preInscripcionDatos.getOferta().getPrograma().getCodigoUaa(),
						preInscripcionDatos.getPersona().getId());

				boolean inscritoConPin = inscripcionDao.validarInscritoPersona(estadoOferta.getCodigo(),
						preInscripcionDatos.getPersona().getIdentificacion());

				if (inscritoConPin) {
					if (ff != null) {
						System.out.println("EXISTE FACTURA");
						if (facturaDao.validarFacturaAutorizada(ff.getFactura().getId(), ff.getFactura().getPin())) {

							Factura facturaGenerada = facturaDao.consultarFacturaGenerada(ff.getFactura().getId());
							Factura facturaPagada = facturaDao.consultarFacturaPagada(ff.getFactura().getId(),
									ff.getFactura().getPin());

							if (facturaGenerada != null && facturaPagada != null
									&& (facturaPagada.getFechaRecaudo().before(facturaGenerada.getFechaFacturaFecha())
											|| facturaPagada.getFechaRecaudo()
													.equals(facturaGenerada.getFechaFacturaFecha()))
									&& (facturaPagada.getValorFactura() == facturaGenerada.getValorFacturaFecha())) {

								System.out.println("LA FACTURA ESTA PAGA");
								mav.addObject("factura", ff.getFactura().getId());
								mav.addObject("pin", ff.getFactura().getPin());
								mav.addObject("facturaPaga", "1");
								mav.addObject("rutaInscripcion",
										constantes.RUTA_PORTAL + "/inscripcion?codigo=" + oferta + "&id=" + id);
							} else {
								System.out.println("LA FACTURA NO ESTA PAGA");
								mav.addObject("factura", ff.getFactura().getId());
								mav.addObject("facturaPaga", "2");
							}
						}
					} else {
						FacturaFecha facturaGenerada = facturaFechaService
								.generarFactura(preInscripcionDatos.getPersona().getIdentificacion(), estadoOferta);
						System.out.println("Factura creada: " + facturaGenerada.getFactura().getId());
						System.out.println("No existe factura!");
					}
				} else {
					mav.addObject("factura", "Ya fue usada en la inscripción.");
					mav.addObject("pin", "Ya fue usada en la inscripción.");
					mav.addObject("facturaPaga", "1");
					mav.addObject("rutaInscripcion",
							constantes.RUTA_PORTAL + "/inscripcion?codigo=" + oferta + "&id=" + id);
					System.out.println("YA ESTA INSCRITO");
				}
			}

			if (preInscripcionDatos.getEmailVerificado() == 1) {
				if (preInscripcionDatos.getAutorizado() == 1) {
					preInscripcionDao.validarCorreo(preInscripcionDatos, id, oferta);
				}
				mav.addObject("diferencia", 0);
				mav.addObject("mensaje", Respuesta.VERIFICACION_EXISTE_EXITOSAMENTE);
			} else {
				mav.addObject("diferencia", diferencia);
				// validar que despues de 24 horas ya no quede activo
				if (diferencia > 1) {
					mav.addObject("mensaje", Respuesta.TIEMPO_EXPIRO_OPERACION);
				} else {
					if (preInscripcionDao.validarCorreo(preInscripcionDatos, id, oferta)) {
						mav.addObject("mensaje", Respuesta.VERIFICACION_EJECUTADA_EXITOSAMENTE);
					} else {
						mav.addObject("mensaje", Respuesta.ERROR_VERIFICAR_OPERACION);
					}
				}
			}
		} else {
			if (preInscripcionDatos.getEmailVerificado() == 1) {
				mav.addObject("diferencia", 0);
				mav.addObject("mensaje", Respuesta.VERIFICACION_EXISTE_EXITOSAMENTE);
			} else {
				mav.addObject("diferencia", diferencia);
				// validar que despues de 24 horas ya no quede activo
				if (diferencia > 1) {
					mav.addObject("mensaje", Respuesta.TIEMPO_EXPIRO_OPERACION);
				} else {
					if (preInscripcionDao.validarCorreoProveedor(preInscripcionDatos, id, oferta)) {
						mav.addObject("mensaje", Respuesta.VERIFICACION_EJECUTADA_EXITOSAMENTE);
					} else {
						mav.addObject("mensaje", Respuesta.ERROR_VERIFICAR_OPERACION);
					}
				}
			}
		}
		mav.addObject("estado", estadoOferta.getEstado());

		if (preInscripcionDatos.getPersona().getNombreCompleto() == null) {
			mav.addObject("persona", preInscripcionDatos.getPersona().getNombres().toUpperCase() + " "
					+ preInscripcionDatos.getPersona().getApellidos().toUpperCase());
		} else {
			mav.addObject("persona", preInscripcionDatos.getPersona().getNombreCompleto().toUpperCase());
		}

		mav.addObject("cedula", preInscripcionDatos.getPersona().getIdentificacion());
		mav.addObject("ofa_codigo", estadoOferta.getCodigo());

		DecimalFormat decimales = new DecimalFormat("000,000.00");
		mav.addObject("programa", estadoOferta.getPrograma().getNombre());
		mav.addObject("valor", decimales.format(estadoOferta.getValor()));
		mav.addObject("valorTotal", decimales.format((estadoOferta.getValor())));

		return mav;
	}

	@RequestMapping(value = "/consultarDatosPreInscripcion", method = RequestMethod.GET, headers = "Accept=application/json")
	public PreInscripcion modificar(@RequestParam("codigoEncriptado") String codigoEncriptado) {

		PreInscripcion preInscripcion = preInscripcionDao.consultarDatosPreInscripcion(codigoEncriptado);
		// Validar si ya existe una inscripcion
		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setOferta(preInscripcion.getOferta());
		inscripcion.setPersona(preInscripcion.getPersona());
		if (inscripcionDao.validarInscripcion(inscripcion)) {
			return null;
		}
		return preInscripcion;
	}

	@RequestMapping(value = "/consultarDatosPersonaPreInscripcion/{identificacion}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Persona modificarPersona(@PathVariable("identificacion") String identificacion) {
		Persona persona = personaDao.consultarPersona(identificacion);
		if (persona == null) {
			persona = personaDao.consultarPersonaUniversidad(identificacion);
		}
		return persona;
	}

}
