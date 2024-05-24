package co.edu.usco.controllers;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import co.edu.usco.inscripciones.dao.CuestionarioDao;
import co.edu.usco.inscripciones.dao.PreguntasDao;
import co.edu.usco.inscripciones.dao.PreguntasRespuestasDao;
import co.edu.usco.inscripciones.dto.RespuestaDTO;
import co.edu.usco.inscripciones.model.Cuestionario;
import co.edu.usco.inscripciones.model.Pregunta;
import co.edu.usco.inscripciones.model.PreguntaRespuestas;
import co.edu.usco.inscripciones.model.RespuestaCuestionario;
import co.edu.usco.inscripciones.service.RespuestasService;
import co.edu.usco.inscripciones.utilidad.Respuesta;

@RestController
public class EncuestaController {

	@Autowired
	PreguntasRespuestasDao preguntasRespuestasDao;

	@Autowired
	PreguntasDao preguntasDao;

	@Autowired
	CuestionarioDao cuestionarioDao;

	@Autowired
	RespuestasService respuestasService;

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/encuesta", method = RequestMethod.GET)
	public ModelAndView encuesta() {
		ModelAndView mv = new ModelAndView("encuesta");

		Cuestionario cuestionario = cuestionarioDao.consultar(777);
		List<PreguntaRespuestas> preguntasRespuestas = preguntasRespuestasDao
				.getAllRespuestas(cuestionario.getCodigo());

		mv.addObject("cuestionario", cuestionario);
		mv.addObject("preguntas", preguntasRespuestas);

		return mv;
	}

	@RequestMapping(value = "/preguntas/{cuestionario}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Pregunta> getPreguntas(@PathVariable("cuestionario") int cuestionario) {
		return preguntasDao.getAllbyCuestionario(cuestionario);
	}

	@RequestMapping(value = "/opcionesRespuesta/{cuestionario}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<PreguntaRespuestas> getOpciones(@PathVariable("cuestionario") int cuestionario) {
		return preguntasRespuestasDao.getAllRespuestas(cuestionario);
	}

	@RequestMapping(value = "/encuesta", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> adicionar(@RequestBody RespuestaDTO respuestaDTO)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		if (respuestaDTO.getListRespuestas().get(0).getCodigo() > 0
				&& respuestaDTO.getListRespuestas().get(1).getCodigo() > 0
				&& respuestaDTO.getListRespuestas().get(2).getCodigo() > 0
				&& respuestaDTO.getListRespuestas().get(3).getCodigo() > 0
				&& respuestaDTO.getListRespuestas().get(4).getCodigo() > 0
				&& respuestaDTO.getListRespuestas().get(5).getCodigo() > 0
				&& respuestaDTO.getListRespuestas().get(6).getCodigo() > 0
				&& respuestaDTO.getListRespuestas().get(7).getCodigo() > 0
				&& respuestaDTO.getListRespuestas().get(8).getCodigo() > 0
				&& respuestaDTO.getListRespuestas().get(9).getCodigo() > 0
				&& respuestaDTO.getListRespuestas().get(10).getCodigo() > 0 && respuestaDTO.getIdentificacion() != null
				&& respuestaDTO.getIdentificacion() != "") {

			String returnInsercion = respuestasService.agregar(respuestaDTO);

			if (returnInsercion.equals("OK")) {
				Respuesta respuesta = new Respuesta(1, true,
						"Operación Exitosa, por favor verifique su correo en donde le llegara el certificado del seminario. Gracias por su participación.",
						null);
				return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
			} else {
				Respuesta respuesta = new Respuesta(0, false, returnInsercion, null);
				return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
			}
		} else {
			Respuesta respuesta = new Respuesta(0, false, "Los campos son obligatorios.", null);
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/generarCertificado/{identificacion}/{cuestionario}/", method = RequestMethod.GET)
	public ModelAndView generarCertificado(HttpServletRequest request, @PathVariable String identificacion,
			@PathVariable String cuestionario) {

		RespuestaCuestionario cuestionariorespuesta = cuestionarioDao.consultarSha("0x" + identificacion,
				"0x" + cuestionario);
		System.out.println("ID" + cuestionariorespuesta.getPersona().getIdentificacion());
		System.out.println("Cuestio:" + cuestionariorespuesta.getCodigo());

		String ubicacionReporte = request.getServletContext().getRealPath("/WEB-INF/reports/") + File.separator;

		String ubicacionLogo = request.getServletContext().getRealPath("/WEB-INF/classes/images/")
				+ File.separator + "fondocongreso.jpg";

		ModelAndView model = new ModelAndView("certificado/congreso_report");
		model.addObject("format", "pdf");
		model.addObject("REPORT_LOCALE", new Locale("es", "CO"));
		model.addObject("RUTA_IMAGEN", ubicacionLogo);
		model.addObject("per_identificacion", cuestionariorespuesta.getPersona().getIdentificacion());
		model.addObject("cuestinario", cuestionariorespuesta.getCodigo());

		return model;
	}
	
	@RequestMapping(value = "/generarCertificadoprueba/{identificacion}/{cuestionario}/", method = RequestMethod.GET)
	public ModelAndView generarCertificadoPrueba(HttpServletRequest request, @PathVariable String identificacion,
			@PathVariable String cuestionario) {

		
		String ubicacionReporte = request.getServletContext().getRealPath("/WEB-INF/reports/") + File.separator;

		String ubicacionLogo = request.getServletContext().getRealPath("/WEB-INF/classes/images/")
				+ File.separator + "fondocongreso.jpg";

		ModelAndView model = new ModelAndView("certificado/congreso2_report");
		model.addObject("format", "pdf");
		model.addObject("REPORT_LOCALE", new Locale("es", "CO"));
		model.addObject("RUTA_IMAGEN", ubicacionLogo);
		model.addObject("per_identificacion", "1079183997");
		model.addObject("cuestinario", 1);

		return model;
	}
}
