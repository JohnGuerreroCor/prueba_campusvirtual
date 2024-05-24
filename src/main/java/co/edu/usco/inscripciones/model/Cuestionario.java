package co.edu.usco.inscripciones.model;

public class Cuestionario {

	private int codigo;
	private String nombre;
	private String insctrucciones;
	private Uaa uaa;

	public Cuestionario(int codigo, String nombre, String insctrucciones, Uaa uaa) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.insctrucciones = insctrucciones;
		this.uaa = uaa;
	}

	public Cuestionario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getInsctrucciones() {
		return insctrucciones;
	}

	public void setInsctrucciones(String insctrucciones) {
		this.insctrucciones = insctrucciones;
	}

	public Uaa getUaa() {
		return uaa;
	}

	public void setUaa(Uaa uaa) {
		this.uaa = uaa;
	}

}
