package co.edu.usco.inscripciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usco.inscripciones.dao.AspiranteDao;
import co.edu.usco.inscripciones.model.Certificado;


@Service("aspiranteService")
public class AspiranteServiceImpl implements AspiranteService {
	
	@Autowired
	AspiranteDao aspiranteDAO;
	
	@Override
	public void guardarCertificados(List<Certificado> certificados) {
		aspiranteDAO.guardarCertificados(certificados);		
	}
	
}
