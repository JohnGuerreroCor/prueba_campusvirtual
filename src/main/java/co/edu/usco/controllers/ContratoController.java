package co.edu.usco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usco.inscripciones.dao.ContratoDao;
import co.edu.usco.inscripciones.model.Contrato;
import co.edu.usco.inscripciones.utilidad.Respuesta;

@RestController
public class ContratoController {

	@Autowired
	ContratoDao contratoDao;
	
	@RequestMapping(value = "validarProveedor/{identificacion}/{oferta}", method = RequestMethod.GET)
	public ResponseEntity<Respuesta> validarUsuGral(@PathVariable String identificacion, @PathVariable int oferta) {
		String mensaje = "";
		boolean exito = false;
		int codigo = 0;
		Contrato contrato = new Contrato();
		if (oferta > 0 && identificacion.length() > 0) {
			contrato = contratoDao.validarContrato(identificacion);
			if (contrato != null) {
				Respuesta respuesta = new Respuesta(1, true, "OK", contrato);
				return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);

			} else {
				mensaje = "No se encontro el provedor ingresado.";
			}
		} else {
			mensaje = "Los campos son obligatorios.";
		}

		Respuesta respuesta = new Respuesta(codigo, exito, mensaje, contrato);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
	}
}
