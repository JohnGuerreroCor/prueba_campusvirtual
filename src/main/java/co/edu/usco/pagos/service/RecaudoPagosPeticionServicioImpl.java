/**
 * 
 */
package co.edu.usco.pagos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.usco.pagos.dao.RecaudoPagosPeticionDao;
import co.edu.usco.pagos.model.Peticion;

/**
 * @author jankarlos
 *
 */
@Component
public class RecaudoPagosPeticionServicioImpl implements RecaudoPagosPeticionServicio {

	@Autowired
	RecaudoPagosPeticionDao recaudoPagosPeticionDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.usco.pagos.service.RecaudoPagosPeticionServicio#agregarPeticion(co
	 * .edu.usco.pagos.model.Peticion)
	 */
	@Override
	public boolean agregarPeticion(Peticion peticion) {
		// TODO Auto-generated method stub
		return recaudoPagosPeticionDao.agregarPeticion(peticion);
	}

	@Override
	public Peticion consultarRequestId(int referencia, int requesId) {
		// TODO Auto-generated method stub
		return recaudoPagosPeticionDao.consultarRequestId(referencia, requesId);
	}

	@Override
	public boolean actualizarEstado(int estado, int peticion, String autorizacion) {
		// TODO Auto-generated method stub
		return recaudoPagosPeticionDao.actualizarEstado(estado, peticion, autorizacion);
	}

	@Override
	public List<Peticion> consultarPeticionesPendiente() {
		// TODO Auto-generated method stub
		return recaudoPagosPeticionDao.consultarPeticionesPendiente();

	}

}
