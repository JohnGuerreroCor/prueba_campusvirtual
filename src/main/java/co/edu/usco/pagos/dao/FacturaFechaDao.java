package co.edu.usco.pagos.dao;

import co.edu.usco.pagos.model.FacturaFecha;

public interface FacturaFechaDao {

	public FacturaFecha consultar(long factura, int uaa, int persona);
}
