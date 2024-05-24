/**
 * 
 */
package co.edu.usco.pagos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.usco.pagos.dao.RecaudoPagosAprobadoDao;
import co.edu.usco.pagos.model.RecaudoPagosAprobado;

/**
 * @author jankarlos
 *
 */
@Component
public class RecaudoPagosAprobadoServicioImpl implements RecaudoPagosAprobadoServicio {

	@Autowired
	RecaudoPagosAprobadoDao recaudoPagosAprobadoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.usco.pagos.service.RecaudoPagosAprobadoServicio#agregarAprobado(co.
	 * edu.usco.pagos.model.RecaudoPagosAprobado)
	 */
	@Override
	public boolean agregarAprobado(RecaudoPagosAprobado recaudoPagosAprobado) {
		// TODO Auto-generated method stub
		return recaudoPagosAprobadoDao.agregarAprobado(recaudoPagosAprobado);
	}

}
