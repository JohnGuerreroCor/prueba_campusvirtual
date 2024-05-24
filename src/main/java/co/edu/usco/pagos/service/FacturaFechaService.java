package co.edu.usco.pagos.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import co.edu.usco.inscripciones.model.Oferta;
import co.edu.usco.pagos.model.FacturaFecha;

public interface FacturaFechaService {

	public FacturaFecha consultar(long factura, int uaa, int persona);
	
	public FacturaFecha generarFactura(String cedula, Oferta datosOferta) throws NoSuchAlgorithmException, IOException, ParseException;
}
