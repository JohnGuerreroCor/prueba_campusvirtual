/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import co.edu.usco.inscripciones.model.Factura;

/**
 * @author ANDRES-GPIE
 *
 */
public interface FacturaDao {

	/**
	 * Validar si la factura existe
	 * 
	 * @param referencia
	 * @return
	 */
	public boolean validarFacturaExista(long referencia);

	/**
	 * Validar Si la factura esta autorizada para que se pueda inscribir
	 * 
	 * @param referencia
	 * @param pin
	 * @return Autorizado o no
	 */
	public boolean validarFacturaAutorizada(long referencia, String pin);

	/**
	 * Cosultamos los datos de la factura pagada
	 * 
	 * @param referencia
	 * @param pin
	 * @return
	 */
	public Factura consultarFacturaPagada(long codigoFactura, String pin);

	/**
	 * Cultaltamos los datos de la factura generada
	 * 
	 * @param referencia
	 * @param pin
	 * @return
	 */
	public Factura consultarFacturaGenerada(long codigoFactura);

	public Factura consultarbancoCuentaFactura(int factura);

	public Factura consultarPinFactura(int referencia);

}
