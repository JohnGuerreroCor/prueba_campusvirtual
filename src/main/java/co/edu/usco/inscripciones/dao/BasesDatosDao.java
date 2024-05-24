/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import java.util.List;

import co.edu.usco.inscripciones.model.BasesDatos;
import co.edu.usco.inscripciones.model.VisitasBasesDatos;

/**
 * @author ANDRES-GPIE
 *
 */
public interface BasesDatosDao {
	/**
	 * Crear Lista de las ofertas
	 * @return Lista de todas las ofertas
	 */
	public List<BasesDatos> crearListaBasesDatos();
	
	/**
	 * Agregar visita a las bases de datos
	 * @param preInscripcion
	 */
	public boolean agregarVisita(String usuario, String link, String nombre);
	
	/**
	 * Consultar los datos de una base de datos
	 * @param codigoBD
	 * @return
	 */
	public BasesDatos consultarBaseDato(int codigoBD);
	
	/**
	 * Consultar las visitas de las bases de datos
	 * @return
	 */
	public List<VisitasBasesDatos> consultarVisitasBasesDatos();
	
	
}
