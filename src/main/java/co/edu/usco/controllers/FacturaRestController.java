package co.edu.usco.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usco.inscripciones.dao.FacturaDao;
import co.edu.usco.inscripciones.dao.InscripcionDao;
import co.edu.usco.inscripciones.dao.LiquidacionDao;
import co.edu.usco.inscripciones.dao.OfertaDao;
import co.edu.usco.inscripciones.dao.PreIncripcionDao;
import co.edu.usco.inscripciones.dao.WebParametroDao;
import co.edu.usco.inscripciones.model.Factura;
import co.edu.usco.inscripciones.model.Liquidacion;
import co.edu.usco.inscripciones.model.Oferta;
import co.edu.usco.inscripciones.model.PreInscripcion;
import co.edu.usco.inscripciones.utilidad.Constantes;
import co.edu.usco.inscripciones.utilidad.ObtenerFechaDDMMAAAA;
import co.edu.usco.inscripciones.utilidad.Respuesta;
import co.edu.usco.librerias.EncriptarPalabra;

@RestController
public class FacturaRestController {

	@Autowired
	DataSource dataSourceAcademiaInvitado;

	@Autowired
	FacturaDao facturaDao;

	@Autowired
	InscripcionDao inscripcionDao;

	@Autowired
	WebParametroDao webParametroDao;

	@Autowired
	OfertaDao ofertaDao;

	@Autowired
	LiquidacionDao liquidacionDao;

	@Autowired
	PreIncripcionDao preInscripcionDao;

	@Autowired
	Constantes constantes;

	@RequestMapping(value = "/validarFactura", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> modificar(@RequestBody final Factura factura) {
		Respuesta respuesta = new Respuesta();
		if (!facturaDao.validarFacturaExista(factura.getReferencia())) {
			respuesta.setRespuesta(Respuesta.FACTURA_ERROR);
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.BAD_REQUEST);
		}

		if (inscripcionDao.validarReferenciaInscripcion(factura.getReferencia())) {
			respuesta.setRespuesta(Respuesta.FACTURA_UTILIZADA);
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.BAD_REQUEST);
		}

		if (facturaDao.validarFacturaAutorizada(factura.getReferencia(), factura.getPin())) {
			respuesta.setRespuesta(Respuesta.FACTURA_AUTORIZADA_OK);
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		}

		Factura facturaGenerada = facturaDao.consultarFacturaGenerada(factura.getReferencia());
		Factura facturaPagada = facturaDao.consultarFacturaPagada(factura.getReferencia(), factura.getPin());

		if (facturaGenerada != null && facturaPagada != null
				&& facturaPagada.getFechaRecaudo().before(facturaGenerada.getFechaFacturaFecha())
				&& facturaPagada.getValorFactura() == facturaGenerada.getValorFacturaFecha()) {
			respuesta.setRespuesta(Respuesta.FACTURA_OK);
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		}

		respuesta.setRespuesta(Respuesta.FACTURA_ERROR);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/generarFacturaFisica", method = RequestMethod.GET, headers = "Accept=application/json")
	public void modificar(@RequestParam("cedula") String cedula, @RequestParam("ofa_codigo") int codofa,
			HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
		// validar que la cedula tenga una preInscripcion para esa oferta y la
		// oferta sea paga
		Oferta datosOferta = ofertaDao.consultarDatosOferta(codofa);

		if (datosOferta != null) {
			if (datosOferta.getRequierePago() == 1) { // validar que la oferta
														// sea paga
				PreInscripcion preInscripcionDatos = preInscripcionDao.validarPreInscripcion(cedula, codofa);
				if (preInscripcionDatos != null) {
					if (preInscripcionDatos.getEmailVerificado() == 1) { // verificar
																			// si
																			// la
																			// preInscripcion
																			// esta
																			// verificada
						Factura tipo_cbo = webParametroDao.consultarTipoLiquidacion();

						Factura identificadores = webParametroDao.consultarTokenFactura();
						String[] temp;
						temp = identificadores.getValorToken().toString().split(",");

						String token = "";
						token = temp[0] + "~" + cedula + "~" + ObtenerFechaDDMMAAAA.obtenerFecha() + "~" + codofa + "~"
								+ temp[1];

						int programa_cbo = datosOferta.getPrograma().getCodigoUaa();

						Liquidacion configuracion = liquidacionDao.consultarLiquidacionConfiguracion(
								tipo_cbo.getValorTipoLiquidacion(), programa_cbo,
								datosOferta.getCalendario().getCodigo());
						if (configuracion != null) {
							response.sendRedirect(constantes.RUTA_GENERAR_FACTURA + "?accion=2&config=1&cedula="
									+ cedula + "&tipo_cbo=" + tipo_cbo.getValorTipoLiquidacion() + "&programa_cbo="
									+ programa_cbo + "&config_cbo=" + configuracion.getCodigo() + "&codofa=" + codofa
									+ "&token=" + EncriptarPalabra.SHA1(token));
						} else {
							response.sendRedirect(constantes.RUTA_PORTAL + "/liquidacion_pendiente");
						}
					}
				}
			}
		}
		System.out.println("aaaaaaaaaaaaaa " + constantes.RUTA_GENERAR_FACTURA);
		// response.sendRedirect(constantes.RUTA_GENERAR_FACTURA);
		response.sendRedirect("https://manuel.usco.edu.co/generar_facturas/");
	}

	@RequestMapping(value = "/generarFacturaOnline", method = RequestMethod.GET, headers = "Accept=application/json")
	public void modificar(@RequestParam("cedula") long cedula, @RequestParam("ofa_codigo") int codofa)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Oferta datosOferta = ofertaDao.consultarDatosOferta(codofa);
		if (datosOferta != null) {
			if (datosOferta.getRequierePago() == 1) { // validar que la oferta
														// sea paga
				// PreInscripcion preInscripcionDatos =
				// preInscripcionDao.validarPreInscripcion(1079183997, 2072);
				// if (preInscripcionDatos != null) {
				// if (preInscripcionDatos.getEmailVerificado() == 1) { //
				// verificar
				// si
				// la
				// preInscripcion
				// esta
				// verificada
				Factura tipo_cbo = webParametroDao.consultarTipoLiquidacion();

				Factura identificadores = webParametroDao.consultarTokenFactura();
				String[] temp;
				temp = identificadores.getValorToken().toString().split(",");

				String token = "";
				token = temp[0] + "~" + cedula + "~" + ObtenerFechaDDMMAAAA.obtenerFecha() + "~" + codofa + "~"
						+ temp[1];

				int programa_cbo = datosOferta.getPrograma().getCodigoUaa();

				Liquidacion configuracion = liquidacionDao.consultarLiquidacionConfiguracion(
						tipo_cbo.getValorTipoLiquidacion(), programa_cbo, datosOferta.getCalendario().getCodigo());

				System.out.println("accion=2&config=1&cedula=" + cedula + "&tipo_cbo="
						+ tipo_cbo.getValorTipoLiquidacion() + "&programa_cbo=" + programa_cbo + "&config_cbo="
						+ configuracion.getCodigo() + "&codofa=" + codofa + "&token=" + EncriptarPalabra.SHA1(token));

				// }
				// }
			}
		}
	}

}
