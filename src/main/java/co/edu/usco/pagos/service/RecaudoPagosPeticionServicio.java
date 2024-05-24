package co.edu.usco.pagos.service;

import java.util.List;

import co.edu.usco.pagos.model.Peticion;

public interface RecaudoPagosPeticionServicio {

	public boolean agregarPeticion(Peticion peticion);

	public Peticion consultarRequestId(int referencia, int requesId);

	public boolean actualizarEstado(int estado, int peticion, String autorizacion);

	public List<Peticion> consultarPeticionesPendiente();
}
