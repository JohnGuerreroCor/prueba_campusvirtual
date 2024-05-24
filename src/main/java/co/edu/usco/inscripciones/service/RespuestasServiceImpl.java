package co.edu.usco.inscripciones.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.dao.CuestionarioDao;
import co.edu.usco.inscripciones.dao.PersonaDao;
import co.edu.usco.inscripciones.dao.RespuestasCuestionariosDao;
import co.edu.usco.inscripciones.dao.RespuestasDao;
import co.edu.usco.inscripciones.dto.RespuestaDTO;
import co.edu.usco.inscripciones.model.Cuestionario;
import co.edu.usco.inscripciones.model.Persona;
import co.edu.usco.inscripciones.model.PreguntaRespuestas;
import co.edu.usco.inscripciones.model.Respuesta;
import co.edu.usco.inscripciones.model.RespuestaCuestionario;
import co.edu.usco.inscripciones.utilidad.Constantes;
import co.edu.usco.librerias.EncriptarPalabra;
import co.edu.usco.librerias.TheadEnvioCorreo;

@Component
public class RespuestasServiceImpl implements RespuestasService {

	@Autowired
	RespuestasDao respuestasDao;

	@Autowired
	PersonaDao personaDao;

	@Autowired
	CuestionarioDao cuestionarioDao;

	@Autowired
	RespuestasCuestionariosDao respuestasCuestionariosDao;

	@Autowired
	Constantes contantes;

	@Override
	public String agregar(RespuestaDTO respuestasDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		RespuestaCuestionario respuestasCuestionario = new RespuestaCuestionario();
		Cuestionario cuestionario = new Cuestionario();
		cuestionario.setCodigo(respuestasDTO.getCuestionario());
		respuestasCuestionario.setCuestionario(cuestionario);

		Persona persona = cuestionarioDao.consultarInscripcion(respuestasDTO.getIdentificacion(),
				respuestasDTO.getCuestionario());

		if (persona != null) {
			respuestasCuestionario.setPersona(persona);
			int returnCuestionario = respuestasCuestionariosDao.agregar(respuestasCuestionario);
			boolean returnInsercion = false;
			for (Respuesta resp : respuestasDTO.getListRespuestas()) {
				Respuesta respuesta = new Respuesta();
				respuesta.setAdicional(resp.getAdicional());

				PreguntaRespuestas preguntaRespuestas = new PreguntaRespuestas();
				preguntaRespuestas.setCodigo(resp.getCodigo());
				respuesta.setPreguntaRespuestas(preguntaRespuestas);

				RespuestaCuestionario respuestaCuestionario = new RespuestaCuestionario();
				respuestaCuestionario.setCodigo(returnCuestionario);
				respuesta.setRspuestaCuestionario(respuestaCuestionario);
				respuesta.setTexto(resp.getTexto());
				returnInsercion = respuestasDao.agregar(respuesta);
			}

			if (returnInsercion) {

				final String idSha1 = EncriptarPalabra.SHA1(persona.getIdentificacion());

				final String cuestionarioSha1 = EncriptarPalabra.SHA1(Integer.toString(respuestasDTO.getCuestionario()));

				String destinatario = persona.getEmail();
				String destinatarioCopia = "";
				if (persona.getEmailInterno() != "" && persona.getEmailInterno() != null) {
					destinatarioCopia = persona.getEmailInterno();
				}

				System.out.println("El DESTINATARIO ES: " + destinatario);
				String footer = "NOTA CONFIDENCIAL:<br>"
						+ "Este mensaje es exclusivamente para el uso de la preInscripcion o entidad a quien está dirigido; La información contenida en este e-mail y en todos sus archivos anexos es completamente confidencial, de igual manera las opiniones expresadas son netamente preInscripcionles, y no necesariamente transmiten el pensamiento de la Universidad Surcolombiana. Si usted no es el destinatario por favor háganoslo saber respondiendo a este correo y por favor destruya cualquier copia del mismo y sus adjuntos, cualquier almacenamiento, distribución, divulgación o copia de este mensaje está estrictamente prohibida y sancionada por la ley."
						+ "Si por error recibe este mensaje, ofrecemos disculpas.<br><br>" + "CONFIDENTIAL NOTE:<br>"
						+ "This message is exclusively for  use of the individual or entity to whom it is forwarded.  The information of this e-mail and all its attachments is completely confidential; likewise, the opinions expressed are purely preInscripcionl and they do not necessarily convey the thought of  Surcolombiana University.  If you are not the addressee, please let us know it by replying to this e-mail and please delete any copy and its attachments.  Any storage, distribution, dissemination or copy of this information is strictly prohibited and punished  by law.";
				String asunto = "Certificado IV CONGRESO INTERNACIONAL DE INVESTIGACIÓN EN SALUD - Universidad Surcolombiana";
				String mensaje = "Cordial saludo, <br><br> ";

				mensaje = mensaje + persona.getNombres().toUpperCase() + " " + persona.getApellidos().toUpperCase();

				mensaje = mensaje + ",<br><br>Para poder descargar el certificado, haga clic en el siguiente "
						+ "enlace: <a href='" + contantes.RUTA_PORTAL + "/generarCertificado/" + idSha1 + "/"
						+ cuestionarioSha1 + "/'>Aquí</a><br><br>" + footer;
				new TheadEnvioCorreo(destinatario, asunto, mensaje, destinatarioCopia).start();

				return "OK";
			} else {
				return "Ocurrio un inconveniente, vuelve a intentarlo.";
			}
		} else {
			return "Usted no participo en el Seminario por lo tanto no puede diligenciar la encuesta, Gracias";
		}
	}

}
