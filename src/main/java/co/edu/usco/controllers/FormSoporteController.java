/**
 * 
 */
package co.edu.usco.controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usco.inscripciones.model.Pqr;

/**
 * @author Daniel Garcia
 *
 */

@RestController
public class FormSoporteController {

	@RequestMapping(value = "/formSoporte", method = RequestMethod.POST, headers = "Accept=application/json")
	public Boolean sendMessage(@RequestBody Pqr info) {

		String nombre = info.getNombre();
		String correo = info.getCorreo();
		String asunto = info.getAsunto();

		String mensaje = "El usuario <strong>" + nombre + "</strong>, con correo:<strong>" + correo
				+ "</strong>, ha enviado el siguiente mensaje,<br><br>";
		mensaje += info.getMensaje();
		String destinatario = "soporte.campusvirtual@usco.edu.co";

		final String emisor_correo = "soporte.campusvirtual@usco.edu.co";
		final String emisor_clave = "123CampusVirtual:.,";

		boolean correo_enviado = false;

		String exito = "";
		String mensaje_tipo = "16";
		String mensaje_texto = "";

		try {
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", emisor_correo);
			props.setProperty("mail.smtp.auth", "true");

			Session sessionCorreo = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emisor_correo, emisor_clave);
				}
			});

			MimeMessage message = new MimeMessage(sessionCorreo);
			message.setHeader("Content-Type", "text/html; charset=UTF-8");
			message.setFrom(new InternetAddress(emisor_correo));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

			message.setSubject(asunto);
			message.setContent(mensaje, "text/html");

			Transport t = sessionCorreo.getTransport("smtp");
			t.connect(emisor_correo, emisor_clave);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			correo_enviado = true;
		} catch (Exception e) {
			correo_enviado = false;
			e.printStackTrace();
		}
		return correo_enviado;

	}
}
