/**
 * 
 */
package co.edu.usco.pagos.controllers;

import java.io.IOException;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

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
import co.edu.usco.librerias.EncriptarPalabra;
import co.edu.usco.pagos.dto.EstadoPago;
import co.edu.usco.pagos.dto.RespuestaCrearFactura;
import co.edu.usco.pagos.dto.RespuestaPago;
import co.edu.usco.pagos.model.FacturaFecha;
import co.edu.usco.pagos.model.Peticion;
import co.edu.usco.pagos.service.FacturaFechaService;
import co.edu.usco.pagos.service.RecaudoPagosPeticionServicio;
import co.edu.usco.pagos.utilidad.WSAuthentication;

/**
 * @author jankarlos
 *
 */
@RestController
public class PagoOnlineRestController {

	@Autowired
	Constantes constantes;

	@Autowired
	OfertaDao ofertaDao;

	@Autowired
	PreIncripcionDao preInscripcionDao;

	@Autowired
	RecaudoPagosPeticionServicio recaudoPagosPeticionServicio;

	@Autowired
	WebParametroDao webParametroDao;

	@Autowired
	LiquidacionDao liquidacionDao;

	@Autowired
	FacturaFechaService facturaFechaService;

	@Autowired
	NotificacionesPagoRestController notificarFuncion;

	@RequestMapping(value = "/pagoOnline/{id}/{oferta}/{cedula}/", method = RequestMethod.GET, headers = "Accept=application/json")
	public String realizarPago(@PathVariable String id, @PathVariable String oferta, @PathVariable long cedula,
			HttpServletResponse httpResponse, @RequestHeader("User-Agent") String userAgent)
			throws IOException, ParseException, NoSuchAlgorithmException {

		Oferta datosOferta = ofertaDao.consultarEstadoOferta(0, oferta);
		PreInscripcion preInscripcionDatos = preInscripcionDao.consultarDatosPreInscripcion(id);

		if (datosOferta != null) {
			if (datosOferta.getRequierePago() == 1) { // validar que la oferta
														// sea paga
				if (preInscripcionDatos != null) {
					if (preInscripcionDatos.getEmailVerificado() == 1) { // verificar
																			// si
																			// la
																			// preInscripcion
																			// esta
																			// verificada

						FacturaFecha ff = generarFactura(cedula, datosOferta);
						if (ff.getFecha() != null) {

							System.out.println("fecha sola" + ff.getFecha());

							Date fechaExpiracion = ff.getFecha();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

							Calendar calendar = Calendar.getInstance();
							calendar.setTime(fechaExpiracion);
							calendar.add(Calendar.MINUTE, 1439);

							System.out.println("FECHAAAA 11" + formatter.format(calendar.getTime()));

							WSAuthentication auth = new WSAuthentication(constantes.LOGIN, constantes.TRANKEY);

							OkHttpClient client = new OkHttpClient();

							MediaType mediaType = MediaType.parse("application/json");
							String direccionIP = InetAddress.getLocalHost().getHostAddress();
							int referenciaPago = ff.getFactura().getId();
							String json = "{";
							json = json + "\"auth\": {";
							json = json + "\"login\": \"" + auth.getLogin() + "\",";
							json = json + "\"seed\": \"" + auth.getSeed() + "\",";
							json = json + "\"nonce\": \"" + auth.getNonce() + "\",";
							json = json + "\"tranKey\": \"" + auth.getTranKey() + "\"";
							json = json + "},";
							json = json + "\"locale\": \"es_CO\",";
							json = json + "\"buyer\": {";
							json = json + "\"document\": \"" + preInscripcionDatos.getPersona().getIdentificacion()
									+ "\",";
							json = json + "\"documentType\": \"CC\",";
							json = json + "\"name\": \"" + preInscripcionDatos.getPersona().getNombres() + "\",";
							json = json + "\"surname\": \"" + preInscripcionDatos.getPersona().getApellidos() + "\",";
							json = json + "\"email\": \"" + preInscripcionDatos.getPersona().getEmail() + "\"";

							if (preInscripcionDatos.getPersona().getLugarResidenciaPais().getAcronimo() != ""
									&& preInscripcionDatos.getPersona().getDireccion() != ""
									&& preInscripcionDatos.getPersona().getLugarResidencia().getNombre() != ""
									&& preInscripcionDatos.getPersona().getLugarResidenciaPais().getAcronimo() != null
									&& preInscripcionDatos.getPersona().getDireccion() != null
									&& preInscripcionDatos.getPersona().getLugarResidencia().getNombre() != null) {
								json = json + ",";
								json = json + "\"address\": {";
								json = json + "\"street\": \"" + preInscripcionDatos.getPersona().getDireccion()
										+ "\",";
								json = json + "\"city\": \""
										+ preInscripcionDatos.getPersona().getLugarResidencia().getNombre() + "\",";
								json = json + "\"country\": \""
										+ preInscripcionDatos.getPersona().getLugarResidenciaPais().getAcronimo()
										+ "\"";
								json = json + "}";
							}
							json = json + "},";
							json = json + "\"payment\": {";
							json = json + "\"reference\": \"" + referenciaPago + "\",";
							json = json + "\"description\": \"Pago de: " + datosOferta.getPrograma().getNombre()
									+ " - Pin:" + ff.getFactura().getPin() + "\",";
							json = json + "\"amount\": {";
							json = json + "\"currency\": \"COP\",";
							json = json + "\"total\": \"" + ff.getValor() + "\"";
							json = json + "},";
							json = json + "\"allowPartial\": false";
							json = json + "},";
							json = json + "\"expiration\": \"" + formatter.format(calendar.getTime()) + "\",";
							json = json + "\"returnUrl\": \"" + constantes.RUTA_PORTAL + "/respuestaPago?referencia="
									+ referenciaPago + "\",";
							json = json + "\"userAgent\": \"" + userAgent + "\",";
							json = json + "\"ipAddress\": \"" + direccionIP + "\"";
							json = json + "}";

							RequestBody body = RequestBody.create(mediaType, json);
							Request request = new Request.Builder().url(constantes.ENDPOINT + "api/session").post(body)
									.addHeader("content-type", "application/json")
									.addHeader("cache-control", "no-cache")
									.addHeader("postman-token", "8c07f928-a5c6-cdef-376f-8fbc0f91c6bf").build();

							Response response = client.newCall(request).execute();

							final String jsonRespuesta = response.body().string();
							final Gson gson = new Gson();
							final RespuestaPago respuestaPago = gson.fromJson(jsonRespuesta, RespuestaPago.class);

							if (respuestaPago.getStatus().getStatus().equals("OK")) {
								Peticion peticion = new Peticion();
								peticion.setRequestId(respuestaPago.getRequestId());
								Factura factura = new Factura();
								factura.setReferencia(referenciaPago);
								peticion.setFactura(factura);

								boolean returnPeticion = recaudoPagosPeticionServicio.agregarPeticion(peticion);

								if (returnPeticion) {
									httpResponse.sendRedirect(respuestaPago.getProcessUrl());
									return "OK";
								} else {
									return "Ocurrio un error al intentar guardar el request id";
								}
							} else {
								return "Ocurrio un inconveniente, vuelve a intentarlo mas tarde."
										+ respuestaPago.getStatus().getMessage();
							}
						} else {
							return "No se pudo generar la factura. Falta configurar la liquidacion. Vuelve e intente mas tarde!";
						}
					} else {
						return "No esta verificada la preinscripción";
					}
				} else {
					return "No se encontrarón los datos de la persona";
				}
			} else {
				return "Esta oferta no requiere pago";
			}
		} else {
			return "No se encontro la oferta";
		}

	}

	@RequestMapping(value = "/respuestaPago", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView respuestaPago(@RequestParam("referencia") int referencia)
			throws IOException, NoSuchAlgorithmException {

		Peticion peticion = recaudoPagosPeticionServicio.consultarRequestId(referencia, 0);
		ModelAndView mav = new ModelAndView("respuestaPago");
		if (peticion != null) {
			WSAuthentication auth = new WSAuthentication(constantes.LOGIN, constantes.TRANKEY);

			OkHttpClient client = new OkHttpClient();

			MediaType mediaType = MediaType.parse("application/json");

			String json = "{";
			json = json + "\"auth\": {";
			json = json + "\"login\": \"" + auth.getLogin() + "\",";
			json = json + "\"seed\": \"" + auth.getSeed() + "\",";
			json = json + "\"nonce\": \"" + auth.getNonce() + "\",";
			json = json + "\"tranKey\": \"" + auth.getTranKey() + "\"";
			json = json + "}";
			json = json + "}";
			RequestBody body = RequestBody.create(mediaType, json);
			Request request = new Request.Builder().url(constantes.ENDPOINT + "api/session/" + peticion.getRequestId())
					.post(body).addHeader("content-type", "application/json").addHeader("cache-control", "no-cache")
					.addHeader("postman-token", "8c07f928-a5c6-cdef-376f-8fbc0f91c6bf").build();

			Response response = client.newCall(request).execute();
			final String jsonRespuesta = response.body().string();
			final Gson gson = new Gson();
			final EstadoPago estadoPago = gson.fromJson(jsonRespuesta, EstadoPago.class);
			// REJECTED
			String nombre = estadoPago.getRequest().getBuyer().getName();
			String apellido = estadoPago.getRequest().getBuyer().getSurname();

			Peticion returnPeticion = recaudoPagosPeticionServicio.consultarRequestId(referencia,
					estadoPago.getRequestId());
			if (returnPeticion != null) {
				if (returnPeticion.getStatus().getReason() != "1" && returnPeticion.getStatus().getReason() != "5") {
					System.out.println(
							estadoPago.getRequestId() + "-" + returnPeticion.getCodigo() + "-" + referencia + "-" + 0);
					notificarFuncion.realizarPago(estadoPago.getRequestId(), returnPeticion.getCodigo(), referencia, 0);
				}
			}

			if (estadoPago.getStatus().getStatus().equals("APPROVED")) {
				// consulto los datos de la factura
				FacturaFecha ff = facturaFechaService.consultar(referencia, 0, 0);
				// consulto datos de la preinscripcion de la factura paga
				PreInscripcion datosPreInscripcion = preInscripcionDao.consultarPreinscripcion(
						ff.getFactura().getUaa().getCodigo(), ff.getFactura().getPersona().getCodigo(),
						ff.getFactura().getTercero().getCodigo());

				final String codigo;
				codigo = EncriptarPalabra.SHA1(Integer.toString(datosPreInscripcion.getCodigo()));

				final String oferta;
				oferta = EncriptarPalabra.SHA1(Integer.toString(datosPreInscripcion.getOferta().getCodigo()));

				mav.addObject("encontrado", "1");
				mav.addObject("pin", ff.getFactura().getPin());
				mav.addObject("rutaInscripcion",
						constantes.RUTA_PORTAL + "/inscripcion?codigo=" + oferta + "&id=" + codigo);

			} else {
				mav.addObject("encontrado", "2");
			}
			mav.addObject("referencia", referencia);
			mav.addObject("persona", nombre.substring(0, 1).toUpperCase() + nombre.substring(1) + " "
					+ apellido.substring(0, 1).toUpperCase() + apellido.substring(1));
			if (estadoPago.getPayment() != null) {
				mav.addObject("estadoMensaje", estadoPago.getPayment().get(0).getStatus().getMessage());
				mav.addObject("estado", estadoPago.getPayment().get(0).getStatus().getStatus());
			} else {
				mav.addObject("encontrado", "3");
			}
		} else {
			mav.addObject("encontrado", "0");
		}
		return mav;
	}

	public FacturaFecha generarFactura(long cedula, Oferta datosOferta)
			throws NoSuchAlgorithmException, IOException, ParseException {

		Factura tipo_cbo = webParametroDao.consultarTipoLiquidacion();

		Factura identificadores = webParametroDao.consultarTokenFactura();
		String[] temp;
		temp = identificadores.getValorToken().toString().split(",");

		String token = "";
		token = temp[0] + "~" + cedula + "~" + ObtenerFechaDDMMAAAA.obtenerFecha() + "~" + datosOferta.getCodigo() + "~"
				+ temp[1];

		int programa_cbo = datosOferta.getPrograma().getCodigoUaa();

		FacturaFecha ff = new FacturaFecha();

		Liquidacion configuracion = liquidacionDao.consultarLiquidacionConfiguracion(tipo_cbo.getValorTipoLiquidacion(),
				programa_cbo, datosOferta.getCalendario().getCodigo());
		if (configuracion != null) {
			System.out.println("accion=2&config=1&cedula=" + cedula + "&tipo_cbo=" + tipo_cbo.getValorTipoLiquidacion()
					+ "&programa_cbo=" + programa_cbo + "&config_cbo=" + configuracion.getCodigo() + "&codofa="
					+ datosOferta.getCodigo() + "&token=");

			OkHttpClient client = new OkHttpClient();

			MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
			RequestBody body = RequestBody.create(mediaType,
					"accion=2&config=1&cedula=" + cedula + "&tipo_cbo=" + tipo_cbo.getValorTipoLiquidacion()
							+ "&programa_cbo=" + programa_cbo + "&config_cbo=" + configuracion.getCodigo() + "&codofa="
							+ datosOferta.getCodigo() + "&token=" + EncriptarPalabra.SHA1(token));
			Request request = new Request.Builder().url(constantes.RUTA_GENERAR_FACTURA_ONLINE).post(body)
					.addHeader("content-type", "application/x-www-form-urlencoded")
					.addHeader("cache-control", "no-cache")
					.addHeader("postman-token", "2783ff12-97af-849c-cdc2-8f0ba15939b8").build();

			Response response = client.newCall(request).execute();

			final String jsonRespuesta = response.body().string();
			final Gson gson = new Gson();
			final RespuestaCrearFactura respuestaCrearFactura = gson.fromJson(jsonRespuesta,
					RespuestaCrearFactura.class);
			ff = facturaFechaService.consultar(respuestaCrearFactura.getCodigofac(), 0, 0);
		}

		// Date fechaExpiracion = ff.getFecha();
		// SimpleDateFormat formatter = new
		// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

		return ff;
	}
}
