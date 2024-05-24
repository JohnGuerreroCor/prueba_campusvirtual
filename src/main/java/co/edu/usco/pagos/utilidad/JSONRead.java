package co.edu.usco.pagos.utilidad;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import co.edu.usco.pagos.dto.RespuestaPago;

public class JSONRead {
	public static void main(String[] args) throws UnknownHostException {

		// String json =
		// "{\"status\":{},\"requestId\":\"Miguel\",\"processUrl\":\"Autentia\"}";

		String json = "{";
		json = json + "\"status\": {";
		json = json + "\"status\": \"OK\",";
		json = json + "\"reason\": \"PC\",";
		json = json + "\"message\": \"La petición se ha procesado correctamente\",";
		json = json + "\"date\": \"2017-10-12T16:01:20-05:00\"";
		json = json + "},";
		json = json + "\"requestId\": 23941,";
		json = json
				+ "\"processUrl\": \"https://test.placetopay.com/redirection/session/23941/d937ff041652a99b8fe00e1a0fcdaad6\"";
		json = json + "}";

		final Gson gson = new Gson();
		final RespuestaPago respuestaPago = gson.fromJson(json, RespuestaPago.class);

		String thisIp = InetAddress.getLocalHost().getHostAddress();
		System.out.println("IP:" + thisIp);

		System.out.println(respuestaPago.getStatus().getMessage());
		// assertEquals("", status.getStatus());
		// assertEquals("Miguel", status.getRequestId());
		// assertEquals("Autentia", status.getProcessUrl());
	}
}
