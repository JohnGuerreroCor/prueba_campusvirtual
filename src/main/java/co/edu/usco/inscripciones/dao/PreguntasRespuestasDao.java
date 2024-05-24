package co.edu.usco.inscripciones.dao;

import java.util.List;

import co.edu.usco.inscripciones.model.Pregunta;
import co.edu.usco.inscripciones.model.PreguntaRespuestas;

public interface PreguntasRespuestasDao {

	List<PreguntaRespuestas> getAllRespuestas(int cuestionario);

}
