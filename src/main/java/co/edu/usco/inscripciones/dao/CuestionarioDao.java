package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Cuestionario;
import co.edu.usco.inscripciones.model.Persona;
import co.edu.usco.inscripciones.model.RespuestaCuestionario;

public interface CuestionarioDao {
	
	public Cuestionario consultar(int uaa);
	
	public Persona consultarInscripcion(String identificacion, int uaa);
	
	public RespuestaCuestionario consultarSha(String identificacion, String cuestionario);

}
