/**
 * 
 */
package co.edu.usco.pagos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.usco.pagos.dao.PagoStatusDao;
import co.edu.usco.pagos.dto.Status;

/**
 * @author jankarlos
 *
 */
@Component
public class PagoStatusServicioImpl implements PagoStatusServicio {

	@Autowired
	PagoStatusDao pagoStatusDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.usco.pagos.service.PagoStatusServicio#consultarStatus(java.lang.
	 * String)
	 */
	@Override
	public Status consultarStatus(String status) {
		// TODO Auto-generated method stub
		return pagoStatusDao.consultarStatus(status);
	}

}
