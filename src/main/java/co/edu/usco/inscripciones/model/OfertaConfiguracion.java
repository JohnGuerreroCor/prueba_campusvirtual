package co.edu.usco.inscripciones.model;

public class OfertaConfiguracion {

	private int codigo;
	private String tipoUsuario;
	private Oferta oferta;
	private String uaa;

	public OfertaConfiguracion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OfertaConfiguracion(int codigo, String tipoUsuario, Oferta oferta, String uaa) {
		super();
		this.codigo = codigo;
		this.tipoUsuario = tipoUsuario;
		this.oferta = oferta;
		this.uaa = uaa;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public String getUaa() {
		return uaa;
	}

	public void setUaa(String uaa) {
		this.uaa = uaa;
	}

}
