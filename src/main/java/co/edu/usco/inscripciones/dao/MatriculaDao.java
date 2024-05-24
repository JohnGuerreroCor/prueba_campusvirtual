/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Matricula;

/**
 * @author ANDRES-GPIE
 *
 */
public interface MatriculaDao {
	/**
	 * Agrega una nueva admision
	 * @param Datos de la incripcion
	 * @return guardado o no
	 */
	public boolean agregarMatricula(Matricula matricula);
	
	/**
	 * Agregar matricula curso actual 
	 * @param paa
	 * @return 
	 */
	public boolean agregarMatriculaCursoActual(int matricula, int curso, int paa);
	
	public Matricula validarMatriculaActiva(String usuarioEstudiante);
		
}
