/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import java.util.List;

import co.edu.usco.inscripciones.model.JSONRespuesta;
import co.edu.usco.inscripciones.model.PreInscripcion;
import co.edu.usco.inscripciones.model.Programa;

/**
 * @author ANDRES-GPIE
 *
 */
public interface PreIncripcionDao {
	/**
	 * Agrega una nueva preinscripcion
	 * 
	 * @param preInscripcion
	 *            Datos preInscripcion a preinscribirse
	 */
	public void agregarPreIncripcion(PreInscripcion preInscripcion);

	/**
	 * Validar si ya existe una preinscripcion activa para esta persona en la
	 * misma oferta
	 * 
	 * @param perCodigo
	 * @return
	 */
	public PreInscripcion validarPreInscripcion(String identificacion, int oferta);

	/**
	 * Consultamos si ya existe una preinscripcion para esta oferta
	 * 
	 * @param identificacion
	 * @return
	 */

	public PreInscripcion consultarPreInscripcion(PreInscripcion preInscripcionn);

	public PreInscripcion convertirTerceroEnPersona(String cedula, String codigoTemporal);

	/**
	 * Consultar datos de la oferta
	 * 
	 * @param codigoOferta
	 * @return
	 */
	public PreInscripcion consultarDatosPreInscripcion(String codigoTemporal);

	/**
	 * Consultar datos de la oferta y inscrito en tercero
	 * 
	 * @param codigoTemporal
	 * @return
	 */
	public PreInscripcion consultarDatosPreInscripcionTercero(String codigoTemporal);

	/**
	 * Validar el correo electronico
	 * 
	 * @param hashCorreo
	 *            Validador para verificar el corero
	 * @return
	 */
	public boolean validarCorreo(PreInscripcion preInscripcion, String hashCorreo, String oferta);

	public boolean validarCorreoProveedor(PreInscripcion preInscripcion, String hashCorreo, String oferta);

	/**
	 * Listar las PreInscripciones
	 * 
	 * @return Lista de las PreInscripciones
	 */
	public List<PreInscripcion> listarPreInscripciones();

	/**
	 * Listar las PreInscripcionUsuario
	 * 
	 * @return Lista de las PreInscripcion
	 */

	public JSONRespuesta listarPreInscripcion1(String search, long codigoOferta, int start, int length, int draw);

	/**
	 * Listado los programas que se han preisncrito
	 * 
	 * @param Buscar
	 * @return programas que tienen preincripcion
	 */

	public List<Programa> ListaProgramasPreInscripcion();

	public PreInscripcion consultarPreinscripcion(int uaa, int persona, int tercero);

}
