package co.edu.usco.pagos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usco.pagos.dao.ConsultarEstadoPagoDao;
import co.edu.usco.pagos.dto.ConsultaPago;

@RestController
public class ConsultarEstadoPagoRestController {

	@Autowired
	ConsultarEstadoPagoDao consultarEstadoPagoDao;

	@RequestMapping(value = "/consultarEstadoPagos/{cedula}/", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<ConsultaPago> consultarPago(@PathVariable long cedula) {
		List<ConsultaPago> lista = consultarEstadoPagoDao.consultar(cedula);
		return lista;
	}
}