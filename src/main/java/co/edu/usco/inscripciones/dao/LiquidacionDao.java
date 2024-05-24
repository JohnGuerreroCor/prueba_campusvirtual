/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Liquidacion;

/**
 * @author ANDRES-GPIE
 *
 */
public interface LiquidacionDao {
	/**
	 * Consultar la configuracion de la liquidacion
	 * @return codigo de la configuracion
	 */
	public Liquidacion consultarLiquidacionConfiguracion(int lit_codigo, int uaa_codigo, int cal_codigo);
	
		
}
