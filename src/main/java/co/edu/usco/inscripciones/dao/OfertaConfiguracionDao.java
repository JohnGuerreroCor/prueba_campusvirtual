package co.edu.usco.inscripciones.dao;

import java.util.List;

import co.edu.usco.inscripciones.dto.OfertaConfiguracionDTO;

public interface OfertaConfiguracionDao {

	/**
	 * Listar las OfertaConfiguracion
	 * @return Lista de las OfertaConfiguracion
	 */
	public List<OfertaConfiguracionDTO> listarOfertaConfiguracion(int oferta);

	List<String> listarUaa(String codigo);

	String listarUsuarios(String codigo);
}
