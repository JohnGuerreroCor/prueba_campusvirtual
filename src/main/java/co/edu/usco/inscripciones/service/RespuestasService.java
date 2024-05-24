package co.edu.usco.inscripciones.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import co.edu.usco.inscripciones.dto.RespuestaDTO;

public interface RespuestasService {

	public String agregar(RespuestaDTO RespuestasDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException;

}
