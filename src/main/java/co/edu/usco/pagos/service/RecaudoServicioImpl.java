/**
 * 
 */
package co.edu.usco.pagos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.usco.pagos.dao.RecaudoDao;
import co.edu.usco.pagos.model.Recaudo;

/**
 * @author jankarlos
 *
 */
@Component
public class RecaudoServicioImpl implements RecaudoServicio {

	@Autowired
	RecaudoDao recaudoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.usco.pagos.service.RecaudoServicio#agregarRecaudo(co.edu.usco.
	 * pagos.model.Recaudo)
	 */
	@Override
	public int agregarRecaudo(Recaudo recaudo) {
		// TODO Auto-generated method stub
		return recaudoDao.agregarRecaudo(recaudo);
	}

	@Override
	public boolean validarRecaudoFactura(int factura) {
		// TODO Auto-generated method stub
		return recaudoDao.validarRecaudoFactura(factura);
	}

}
