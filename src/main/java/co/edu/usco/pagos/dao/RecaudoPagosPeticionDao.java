/**
 * 
 */
package co.edu.usco.pagos.dao;

import java.util.List;

import co.edu.usco.pagos.model.Peticion;

/**
 * @author jankarlos
 *
 */
public interface RecaudoPagosPeticionDao {

	/**
	 * Agrega una nueva Peticion
	 * 
	 * @param Peticion
	 *            Datos Peticion a registrar
	 */
	public boolean agregarPeticion(Peticion peticion);

	public Peticion consultarRequestId(int referencia, int requesId);

	public boolean actualizarEstado(int estado, int peticion, String autorizacion);

	public List<Peticion> consultarPeticionesPendiente();
}
