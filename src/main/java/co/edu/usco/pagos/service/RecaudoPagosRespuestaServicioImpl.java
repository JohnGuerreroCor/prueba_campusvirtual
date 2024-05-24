/**
 * 
 */
package co.edu.usco.pagos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.usco.pagos.dao.RecaudoPagosRespuestaDao;
import co.edu.usco.pagos.model.RecaudoPagosRespuesta;

/**
 * @author jankarlos
 *
 */
@Component
public class RecaudoPagosRespuestaServicioImpl implements RecaudoPagosRespuestaServicio {

	@Autowired
	RecaudoPagosRespuestaDao recaudoPagosRespuestaDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.usco.pagos.service.RecaudoPagosRespuestaServicio#agregarRespuesta(
	 * co.edu.usco.pagos.model.RecaudoPagosRespuesta)
	 */
	@Override
	public int agregarRespuesta(RecaudoPagosRespuesta recaudoPagosRespuesta) {
		// TODO Auto-generated method stub
		return recaudoPagosRespuestaDao.agregarRespuesta(recaudoPagosRespuesta);
	}

}
