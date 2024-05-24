package co.edu.usco.inscripciones.dao;

import java.util.List;

import co.edu.usco.inscripciones.model.Certificado;

public interface AspiranteDao {	
	
	public void guardarCertificados(List<Certificado> certificados);
}
