/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Calendario;
import co.edu.usco.inscripciones.model.Factura;
import co.edu.usco.inscripciones.model.WebParametro;

/**
 * @author ANDRES-GPIE
 *
 */
public interface WebParametroDao {
	/**
	 * Agrega una nueva admision
	 * @param Datos de la incripcion
	 * @return guardado o no
	 */
	public Calendario consultarIncripcionPregradoCalendario();
	
	/**
	 * Consultar inicio y cola token factura
	 * @return
	 */
	public Factura consultarTokenFactura();
	
	/**
	 * Consultar el valor del tipo de liquidacion para la educacion no formal
	 * @return
	 */
	public Factura consultarTipoLiquidacion();
	
	/**
	 * Listar las WebParametros
	 * 
	 * @return Lista de las WebParametros
	 */
	public WebParametro listarWebParametro(String nombre);
		
}
