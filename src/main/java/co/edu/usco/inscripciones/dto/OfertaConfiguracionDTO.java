package co.edu.usco.inscripciones.dto;

import java.util.List;

import co.edu.usco.inscripciones.model.Oferta;

public class OfertaConfiguracionDTO {

	private int codigo;
	private List<String> usuarios;
	private List<String> uaa;
	private String nombreUsuarios;
	private Oferta oferta;
	private String codigosUaa;
	private String codigosUsuarios;

	public OfertaConfiguracionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OfertaConfiguracionDTO(int codigo, List<String> usuarios, List<String> uaa, String nombreUsuarios,
			Oferta oferta, String codigosUaa, String codigosUsuarios) {
		super();
		this.codigo = codigo;
		this.usuarios = usuarios;
		this.uaa = uaa;
		this.nombreUsuarios = nombreUsuarios;
		this.oferta = oferta;
		this.codigosUaa = codigosUaa;
		this.codigosUsuarios = codigosUsuarios;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<String> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<String> usuarios) {
		this.usuarios = usuarios;
	}

	public List<String> getUaa() {
		return uaa;
	}

	public void setUaa(List<String> uaa) {
		this.uaa = uaa;
	}

	public String getNombreUsuarios() {
		return nombreUsuarios;
	}

	public void setNombreUsuarios(String nombreUsuarios) {
		this.nombreUsuarios = nombreUsuarios;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public String getCodigosUaa() {
		return codigosUaa;
	}

	public void setCodigosUaa(String codigosUaa) {
		this.codigosUaa = codigosUaa;
	}

	public String getCodigosUsuarios() {
		return codigosUsuarios;
	}

	public void setCodigosUsuarios(String codigosUsuarios) {
		this.codigosUsuarios = codigosUsuarios;
	}

}
