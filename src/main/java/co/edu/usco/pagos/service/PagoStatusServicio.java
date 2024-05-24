package co.edu.usco.pagos.service;

import co.edu.usco.pagos.dto.Status;

public interface PagoStatusServicio {

	public Status consultarStatus(String status);
}
