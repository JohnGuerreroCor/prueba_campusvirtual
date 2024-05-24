package co.edu.usco.pagos.dao;

import co.edu.usco.pagos.dto.Status;

public interface PagoStatusDao {

	public Status consultarStatus(String status);

}
