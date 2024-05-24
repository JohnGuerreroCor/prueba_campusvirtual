package co.edu.usco.inscripciones.service;

import co.edu.usco.inscripciones.model.Inscripcion;
import co.edu.usco.inscripciones.utilidad.Respuesta;

public interface InscripcionService {	
	
	public Respuesta guardarInscripcion(Inscripcion inscripcion);

	public Respuesta guardarInscripcionInterna(Inscripcion inscripcion);

}
