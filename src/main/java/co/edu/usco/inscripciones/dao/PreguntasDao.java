package co.edu.usco.inscripciones.dao;

import java.util.List;

import co.edu.usco.inscripciones.model.Pregunta;

public interface PreguntasDao {

	List<Pregunta> getAllbyCuestionario(int cuestionario);
}
