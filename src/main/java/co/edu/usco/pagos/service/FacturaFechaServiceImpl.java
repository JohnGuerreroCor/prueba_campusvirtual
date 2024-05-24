package co.edu.usco.pagos.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import co.edu.usco.inscripciones.dao.LiquidacionDao;
import co.edu.usco.inscripciones.dao.WebParametroDao;
import co.edu.usco.inscripciones.model.Factura;
import co.edu.usco.inscripciones.model.Liquidacion;
import co.edu.usco.inscripciones.model.Oferta;
import co.edu.usco.inscripciones.utilidad.Constantes;
import co.edu.usco.inscripciones.utilidad.ObtenerFechaDDMMAAAA;
import co.edu.usco.librerias.EncriptarPalabra;
import co.edu.usco.pagos.dao.FacturaFechaDao;
import co.edu.usco.pagos.dto.RespuestaCrearFactura;
import co.edu.usco.pagos.model.FacturaFecha;

@Component
public class FacturaFechaServiceImpl implements FacturaFechaService {

	@Autowired
	FacturaFechaDao facturaFechaDao;

	@Autowired
	WebParametroDao webParametroDao;

	@Autowired
	LiquidacionDao liquidacionDao;

	@Autowired
	Constantes constantes;

	@Override
	public FacturaFecha consultar(long factura, int uaa, int persona) {
		// TODO Auto-generated method stub
		return facturaFechaDao.consultar(factura, uaa, persona);
	}

	@Override
	public FacturaFecha generarFactura(String cedula, Oferta datosOferta)
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
					+ datosOferta.getCodigo() + "&token="+EncriptarPalabra.SHA1(token));
System.out.println("RUTA:"+constantes.RUTA_GENERAR_FACTURA_ONLINE);


			OkHttpClient client = new OkHttpClient();

			MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
			RequestBody body = RequestBody.create(mediaType,
					"accion=2&config=1&cedula=" + cedula + "&tipo_cbo=" + tipo_cbo.getValorTipoLiquidacion()
							+ "&programa_cbo=" + programa_cbo + "&config_cbo=" + configuracion.getCodigo() + "&codofa="
							+ datosOferta.getCodigo() + "&token=" + EncriptarPalabra.SHA1(token));
			Request request = new Request.Builder().url(constantes.RUTA_GENERAR_FACTURA_ONLINE).post(body)
					.addHeader("content-type", "application/x-www-form-urlencoded")
					.addHeader("cache-control", "no-cache")
					.addHeader("postman-token", "a0b90eb0-2ed7-4339-b6d3-c4295b73a12c").build();

			Response response = client.newCall(request).execute();

			final String jsonRespuesta = response.body().string();
			final Gson gson = new Gson();
			final RespuestaCrearFactura respuestaCrearFactura = gson.fromJson(jsonRespuesta,
					RespuestaCrearFactura.class);
			ff = consultar(respuestaCrearFactura.getCodigofac(), 0, 0);
		}

		// Date fechaExpiracion = ff.getFecha();
		// SimpleDateFormat formatter = new
		// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

		return ff;
	}

}
