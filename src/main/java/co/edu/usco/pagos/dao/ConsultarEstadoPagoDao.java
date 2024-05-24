package co.edu.usco.pagos.dao;

import java.util.List;

import co.edu.usco.pagos.dto.ConsultaPago;

public interface ConsultarEstadoPagoDao {

	public List<ConsultaPago> consultar(long cedula);
}
