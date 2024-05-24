package co.edu.usco.pagos.service;

import co.edu.usco.pagos.model.Recaudo;

public interface RecaudoServicio {

	public int agregarRecaudo(Recaudo recaudo);

	public boolean validarRecaudoFactura(int factura);

}
