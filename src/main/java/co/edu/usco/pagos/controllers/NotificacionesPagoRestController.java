/**
 * 
 */
package co.edu.usco.pagos.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import co.edu.usco.inscripciones.dao.FacturaDao;
import co.edu.usco.inscripciones.dao.PreIncripcionDao;
import co.edu.usco.inscripciones.model.Factura;
import co.edu.usco.inscripciones.model.PreInscripcion;
import co.edu.usco.inscripciones.utilidad.Constantes;
import co.edu.usco.inscripciones.utilidad.Respuesta;
import co.edu.usco.librerias.EncriptarPalabra;
import co.edu.usco.librerias.TheadEnvioCorreo;
import co.edu.usco.pagos.dto.EstadoPago;
import co.edu.usco.pagos.dto.Notificacion;
import co.edu.usco.pagos.dto.Status;
import co.edu.usco.pagos.model.BancoCuenta;
import co.edu.usco.pagos.model.FacturaFecha;
import co.edu.usco.pagos.model.Peticion;
import co.edu.usco.pagos.model.Recaudo;
import co.edu.usco.pagos.model.RecaudoPagosAprobado;
import co.edu.usco.pagos.model.RecaudoPagosRespuesta;
import co.edu.usco.pagos.service.FacturaFechaService;
import co.edu.usco.pagos.service.PagoStatusServicio;
import co.edu.usco.pagos.service.RecaudoPagosAprobadoServicio;
import co.edu.usco.pagos.service.RecaudoPagosPeticionServicio;
import co.edu.usco.pagos.service.RecaudoPagosRespuestaServicio;
import co.edu.usco.pagos.service.RecaudoServicio;
import co.edu.usco.pagos.utilidad.WSAuthentication;

@RestController
public class NotificacionesPagoRestController {

	@Autowired
	Constantes constantes;

	@Autowired
	RecaudoPagosPeticionServicio recaudoPagosPeticionServicio;

	@Autowired
	PagoStatusServicio pagoStatusServicio;

	@Autowired
	RecaudoPagosRespuestaServicio recaudoPagosRespuestaServicio;

	@Autowired
	RecaudoServicio recaudoServicio;

	@Autowired
	RecaudoPagosAprobadoServicio recaudoPagosAprobadoServicio;

	@Autowired
	FacturaDao facturaDao;

	@Autowired
	FacturaFechaService facturaFechaService;

	@Autowired
	PreIncripcionDao preInscripcionDao;

	/**
	 * Url a enviar a place to pay para que la configuren y notifiquen el cambio
	 * de estado de una peticion
	 * 
	 * @param notificacion
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/notificarPagoFactura", method = RequestMethod.POST, headers = "Accept=application/json")
	public Respuesta notificacion(@RequestBody Notificacion notificacion)
			throws NoSuchAlgorithmException, ParseException, IOException {

		Respuesta respuesta = new Respuesta();

		String codificacion = sha1String(notificacion.getRequestId() + notificacion.getStatus().getStatus()
				+ notificacion.getStatus().getDate() + constantes.TRANKEY);
		System.out.println(codificacion);
		
		if (codificacion.equals(notificacion.getSignature())) {
			if (notificacion.getStatus().getStatus().equals("APPROVED")) {
				Peticion returnPeticion = recaudoPagosPeticionServicio.consultarRequestId(notificacion.getReference(),
						notificacion.getRequestId());
				if (returnPeticion != null) {
					return realizarPago(notificacion.getRequestId(), returnPeticion.getCodigo(),
							notificacion.getReference(),0);
				} else {
					respuesta.setRespuesta(1, true, "No se encontro la referencia.");
				}
			} else {
				respuesta.setRespuesta(1, true, notificacion.getStatus().getMessage());
			}
		} else {
			respuesta.setRespuesta(Respuesta.SIN_PERMISOS);
		}
		return respuesta;
	}

	static String sha1String(String input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public void notificarPago(int referencia, EstadoPago estadoPago)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// Factura factura = facturaDao.consultarPinFactura(referencia);
		FacturaFecha ff = facturaFechaService.consultar(referencia, 0, 0);
		// consulto datos de la preinscripcion de la factura paga
		PreInscripcion datosPreInscripcion = preInscripcionDao.consultarPreinscripcion(
				ff.getFactura().getUaa().getCodigo(), ff.getFactura().getPersona().getCodigo(),
				ff.getFactura().getTercero().getCodigo());

		final String codigo;
		codigo = EncriptarPalabra.SHA1(Integer.toString(datosPreInscripcion.getCodigo()));

		final String oferta;
		oferta = EncriptarPalabra.SHA1(Integer.toString(datosPreInscripcion.getOferta().getCodigo()));

		String mensaje = "";
		String destinatario = estadoPago.getRequest().getBuyer().getEmail();
		String footer = "NOTA CONFIDENCIAL:<br>"
				+ "Este mensaje es exclusivamente para el uso de la persona o entidad a quien está dirigido; La información contenida en este e-mail y en todos sus archivos anexos es completamente confidencial, de igual manera las opiniones expresadas son netamente personales, y no necesariamente transmiten el pensamiento de la Universidad Surcolombiana. Si usted no es el destinatario por favor háganoslo saber respondiendo a este correo y por favor destruya cualquier copia del mismo y sus adjuntos, cualquier almacenamiento, distribución, divulgación o copia de este mensaje está estrictamente prohibida y sancionada por la ley."
				+ "Si por error recibe este mensaje, ofrecemos disculpas.<br><br>" + "CONFIDENTIAL NOTE:<br>"
				+ "This message is exclusively for  use of the individual or entity to whom it is forwarded.  The information of this e-mail and all its attachments is completely confidential; likewise, the opinions expressed are purely personal and they do not necessarily convey the thought of  Surcolombiana University.  If you are not the addressee, please let us know it by replying to this e-mail and please delete any copy and its attachments.  Any storage, distribution, dissemination or copy of this information is strictly prohibited and punished  by law.";
		String asunto = "Notificacion código de referencia y pin - Inscripciones Universidad Surcolombiana";

		mensaje = "Cordial saludo " + estadoPago.getRequest().getBuyer().getName().toUpperCase() + " "
				+ estadoPago.getRequest().getBuyer().getSurname().toUpperCase()
				+ ",<br><br>El pago de su factura fue exitoso. "
				+ "<br><br>Tenga en cuenta que el código de referencia de su pago es: <strong>" + referencia
				+ "</strong> y el pin es: <strong>" + ff.getFactura().getPin() + "<strong>"
				+ "<br><br>Recuerde que el código de referencia y el pin son obligatorios para continuar con el proceso de inscripción en el link del correo que se le envio anteriormente. "
				+ "<br><br><strong>Para continuar con la inscripción dar clic <a href=\"" + constantes.RUTA_PORTAL
				+ "/inscripcion?codigo=" + oferta + "&id=" + codigo + "\" target=\"_blank\">aquí</a></strong>"
				+ "<br><br>" + footer;

		new TheadEnvioCorreo(destinatario, asunto, mensaje, "").start();
	}

	/**
	 * Metodo para consultar los datos de un pago mediante el requestId en place
	 * to pay
	 * 
	 * @param requestId
	 * @return
	 * @throws IOException
	 */
	public EstadoPago consultarDatosPago(int requestId) throws IOException {
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
		com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(mediaType, json);
		Request requestDatos = new Request.Builder().url(constantes.ENDPOINT + "api/session/" + requestId).post(body)
				.addHeader("content-type", "application/json").addHeader("cache-control", "no-cache")
				.addHeader("postman-token", "8c07f928-a5c6-cdef-376f-8fbc0f91c6bf").build();

		Response responseDatos = client.newCall(requestDatos).execute();

		final String jsonRespuesta = responseDatos.body().string();
		final Gson gson = new Gson();
		final EstadoPago estadoPago = gson.fromJson(jsonRespuesta, EstadoPago.class);

		return estadoPago;

	}
	public Respuesta realizarPago(int requestId, int codigoPeticion, int referenciaCodigo, int estadoCodigo)
			throws IOException, NoSuchAlgorithmException {
		Respuesta respuesta = new Respuesta();

		EstadoPago estadoPago = consultarDatosPago(requestId);
		
		if (estadoPago.getPayment() != null) {
			Status returnStatus = pagoStatusServicio.consultarStatus(estadoPago.getPayment().get(0).getStatus().getStatus());
			
			RecaudoPagosRespuesta recaudoPagosRespuesta = new RecaudoPagosRespuesta();
			Status status = new Status();
			status.setReason(returnStatus.getReason());
			recaudoPagosRespuesta.setStatus(status);
			Peticion peticion = new Peticion();
			peticion.setCodigo(codigoPeticion);
			recaudoPagosRespuesta.setPeticion(peticion);
			
			int returnRespuesta = 0; 
			
			if(estadoCodigo != 2){
				returnRespuesta = recaudoPagosRespuestaServicio.agregarRespuesta(recaudoPagosRespuesta);
			}else{
				recaudoPagosPeticionServicio.actualizarEstado(Integer.parseInt(returnStatus.getReason()),
						codigoPeticion, estadoPago.getPayment().get(0).getAuthorization());
			}
			
			if(returnRespuesta>0){
				recaudoPagosPeticionServicio.actualizarEstado(Integer.parseInt(returnStatus.getReason()),
						codigoPeticion, estadoPago.getPayment().get(0).getAuthorization());
				
				if (returnStatus.getStatus().equals("APPROVED")) {
					if (!recaudoServicio.validarRecaudoFactura(referenciaCodigo)) {
						Recaudo recaudo = new Recaudo();
						Factura facturaRecaudo = new Factura();
						facturaRecaudo.setReferencia(referenciaCodigo);
						recaudo.setFactura(facturaRecaudo);
						BancoCuenta bancoCuenta = new BancoCuenta();

						Factura facturaBancoCuenta = facturaDao.consultarbancoCuentaFactura(referenciaCodigo);
						bancoCuenta.setCodigo(facturaBancoCuenta.getBancoCuenta().getCodigo());
						recaudo.setBancoCuenta(bancoCuenta);
						recaudo.setValor(
								Integer.parseInt(estadoPago.getPayment().get(0).getAmount().getFrom().getTotal()));

						int returnRecaudo = recaudoServicio.agregarRecaudo(recaudo);
						if (returnRecaudo > 0) {
							RecaudoPagosAprobado recaudoPagosAprobado = new RecaudoPagosAprobado();
							Recaudo recaudoAprobado = new Recaudo();
							recaudoAprobado.setCodigo(returnRecaudo);
							RecaudoPagosRespuesta recaudoRespuestaAprobado = new RecaudoPagosRespuesta();
							recaudoRespuestaAprobado.setCodigo(returnRespuesta);

							recaudoPagosAprobado.setRecaudo(recaudoAprobado);
							recaudoPagosAprobado.setRecaudoPagosRespuesta(recaudoRespuestaAprobado);

							boolean returnAprobado = recaudoPagosAprobadoServicio.agregarAprobado(recaudoPagosAprobado);
							if (returnAprobado) {
								notificarPago(referenciaCodigo, estadoPago);
								respuesta.setRespuesta(1, true, " Ok ");
							} else {
								respuesta.setRespuesta(1, true, " Ocurrio un error al tratar de guardar el aprobado.");
							}
						} else {
							respuesta.setRespuesta(1, true, " Ocurrio un error al tratar de guardar el recaudo.");
						}
					} else {
						respuesta.setRespuesta(1, true, " Ya existe el recaudo.");
					}
				}else{
					respuesta.setRespuesta(1, true, " Se actualizo el estado a: "+returnStatus.getStatus());
				}
			}else{
				respuesta.setRespuesta(1, true, " No se pudo agregar la respuesta: "+returnStatus.getStatus());
			}
		}else{
			recaudoPagosPeticionServicio.actualizarEstado(4, codigoPeticion, null);
			respuesta.setRespuesta(1, true, " La referencia de pago: "+requestId+" no tiene pago. ");
		}

		return respuesta;
	}
	
	/*
	 public Respuesta realizarPago(int requestId, int codigoPeticion, int referenciaCodigo)
			throws IOException, NoSuchAlgorithmException {
		Respuesta respuesta = new Respuesta();

		EstadoPago estadoPago = consultarDatosPago(requestId);
		Status returnStatus = pagoStatusServicio.consultarStatus(estadoPago.getStatus().getStatus());
		if (returnStatus != null) {

			RecaudoPagosRespuesta recaudoPagosRespuesta = new RecaudoPagosRespuesta();
			
			Status status = new Status();
			status.setReason(returnStatus.getReason());
			recaudoPagosRespuesta.setStatus(status);
			Peticion peticion = new Peticion();
			peticion.setCodigo(codigoPeticion);
			recaudoPagosRespuesta.setPeticion(peticion);

			String autorizacion = "00";
			Status returnStatusPago = null;
			if (estadoPago.getPayment() != null) {
				autorizacion = estadoPago.getPayment().get(0).getAuthorization();
				returnStatusPago = pagoStatusServicio
						.consultarStatus(estadoPago.getPayment().get(0).getStatus().getStatus());
			}

			int returnRespuesta = 0;

			if (estadoPago.getStatus().getStatus().equals("APPROVED") && returnStatusPago.getStatus() != "REJECTED") {
				returnRespuesta = recaudoPagosRespuestaServicio.agregarRespuesta(recaudoPagosRespuesta);
			}
			
			if (autorizacion == "00") {
				System.out.println("SI");
				recaudoPagosPeticionServicio.actualizarEstado(4, codigoPeticion, null);
			} else {
				System.out.println("NO");
				recaudoPagosPeticionServicio.actualizarEstado(Integer.parseInt(returnStatus.getReason()),
						codigoPeticion, autorizacion);
			}
			System.out.println("EL VALOR DE LA AUTORIZACION ES:" + autorizacion);
			if (returnRespuesta > 0) {

				// if (estadoPago.getStatus().getStatus().equals("APPROVED") &&
				// returnStatusPago.getStatus().equals("APPROVED")) {
				if (returnStatusPago.getStatus().equals("APPROVED")) {
					if (!recaudoServicio.validarRecaudoFactura(referenciaCodigo)) {
						Recaudo recaudo = new Recaudo();
						Factura facturaRecaudo = new Factura();
						facturaRecaudo.setReferencia(referenciaCodigo);
						recaudo.setFactura(facturaRecaudo);
						BancoCuenta bancoCuenta = new BancoCuenta();

						Factura facturaBancoCuenta = facturaDao.consultarbancoCuentaFactura(referenciaCodigo);
						bancoCuenta.setCodigo(facturaBancoCuenta.getBancoCuenta().getCodigo());
						recaudo.setBancoCuenta(bancoCuenta);
						recaudo.setValor(
								Integer.parseInt(estadoPago.getPayment().get(0).getAmount().getFrom().getTotal()));

						int returnRecaudo = recaudoServicio.agregarRecaudo(recaudo);
						if (returnRecaudo > 0) {
							RecaudoPagosAprobado recaudoPagosAprobado = new RecaudoPagosAprobado();
							Recaudo recaudoAprobado = new Recaudo();
							recaudoAprobado.setCodigo(returnRecaudo);
							RecaudoPagosRespuesta recaudoRespuestaAprobado = new RecaudoPagosRespuesta();
							recaudoRespuestaAprobado.setCodigo(returnRespuesta);

							recaudoPagosAprobado.setRecaudo(recaudoAprobado);
							recaudoPagosAprobado.setRecaudoPagosRespuesta(recaudoRespuestaAprobado);

							boolean returnAprobado = recaudoPagosAprobadoServicio.agregarAprobado(recaudoPagosAprobado);
							if (returnAprobado) {
								notificarPago(referenciaCodigo, estadoPago);
								respuesta.setRespuesta(1, true, "Ok");
							} else {
								respuesta.setRespuesta(1, true, "Ocurrio un error al tratar de guardar el aprobado.");
							}
						} else {
							respuesta.setRespuesta(1, true, "Ocurrio un error al tratar de guardar el recaudo.");
						}
					} else {
						respuesta.setRespuesta(1, true, "Ya existe el recaudo.");
					}
				} else {
					respuesta.setRespuesta(1, true,
							"---- El estado de la solicitud es: " + estadoPago.getStatus().getStatus() + " "
									+ estadoPago.getStatus().getMessage() + " La referencia es:" + referenciaCodigo);
				}
			} else {
				respuesta.setRespuesta(1, true,
						"No se guarda la respuesta. el estado de la session es:" + estadoPago.getStatus().getStatus());
			}
		} else {
			respuesta.setRespuesta(1, true, "No se encontro el estado de la referencia.");
		}
		return respuesta;
	}
	*/

	/**
	 * metodo realizarPagoCron con el cual se correra como un cron job para
	 * ejecutarlo a la media noche revisando las peticiones que lleguen a estar
	 * pendientes
	 * 
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/realizarPagoCron", method = RequestMethod.GET, headers = "Accept=application/json")
	public String respuestaPrueba() throws IOException, NoSuchAlgorithmException {
		String mensaje = "";
		List<Peticion> listaPeticiones = recaudoPagosPeticionServicio.consultarPeticionesPendiente();
		if (listaPeticiones != null) {
			for (Peticion a : listaPeticiones) {
				mensaje = mensaje + realizarPago(a.getRequestId(), a.getCodigo(), (int) a.getFactura().getReferencia(), Integer.parseInt(a.getStatus().getReason()))
						.getMensaje();
			}
		}
		return mensaje;
	}
}
