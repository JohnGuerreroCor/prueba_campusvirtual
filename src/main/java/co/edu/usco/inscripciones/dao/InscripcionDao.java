/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import java.util.List;

import co.edu.usco.inscripciones.model.Inscripcion;
import co.edu.usco.inscripciones.model.JSONRespuesta;
import co.edu.usco.inscripciones.model.Programa;

/**
 * @author ANDRES-GPIE
 *
 */
public interface InscripcionDao {
	/**
	 * Agrega una nueva preinscripcion con referencia de pago
	 * 
	 * @param persona Datos persona a preinscribirse
	 * @return
	 */
	public boolean agregarIncripcionPaga(Inscripcion inscripcion);

	/**
	 * Agregar inscripcion sin tener en cuenta la referencia
	 * 
	 * @param inscripcion
	 * @return
	 */
	public boolean agregarIncripcionGratis(Inscripcion inscripcion);

	/**
	 * Consultamos la cantidad de requisitos para la inscripcion
	 * 
	 * @param programa
	 * @return cantidad de archivos para adjuntar
	 */
	public Inscripcion consultarCantidadRequisitos(int codigoOferta);

	/**
	 * Validar si la referencia ya fue utilizada en alguna inscripcion
	 * 
	 * @param referencia numero de la factura
	 * @return Lista
	 */
	public boolean validarReferenciaInscripcion(long referencia);

	/**
	 * En el caso que la oferta sea gratuita verificamos si ya existe una
	 * inscripcion
	 * 
	 * @param referencia
	 * @return Existe o no
	 */
	public boolean validarInscripcion(Inscripcion inscripcion);

	/**
	 * Agreagr Inscripcion Programa
	 * 
	 * @param inscripcion
	 * @return agrego o no agrego
	 */
	public boolean agregarIncripcionPrograma(final Inscripcion inscripcion, final int codigoOferta);

	/**
	 * Consultar el codigo de la oferta seleccionada al momento de realizar la
	 * preInscripcion
	 * 
	 * @param codigoFactura
	 * @return
	 */
	public Inscripcion consultarOfertaSeleccionada(String oferta);

	/**
	 * Listar los usuarios inscritos
	 * 
	 * @return Lista de usuarios inscritos
	 */

	public JSONRespuesta listaUsuariosInscritos(String search, long codigoOferta, int start, int length, int draw);

	/**
	 * Listado los programas que se han inscrito
	 * 
	 * @param Buscar
	 * @return programas que tienen incripciones
	 */

	public List<Programa> ListaProgramasInscripciones();

	public boolean validarInscritoPersona(int oferta, String identificacion);
	
	/**
	 * Consultar el ins_codigo por el código de la persona
	 * 
	 * @param codPersona
	 * @return
	 */
	public int constultarInscripcionCodigo(long codPersona);

}
