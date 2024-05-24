package co.edu.usco.pagos.dao;

import co.edu.usco.pagos.model.Recaudo;

public interface RecaudoDao {

	public int agregarRecaudo(Recaudo recaudo);

	public boolean validarRecaudoFactura(int factura);
}
