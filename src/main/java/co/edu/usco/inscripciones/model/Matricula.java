/**
 * 
 */
package co.edu.usco.inscripciones.model;

/**
 * @author ANDRES-GPIE
 *
 */
public class Matricula {

	private int id;
	private Inscripcion inscripcion;
	private Oferta oferta;
	private int usuario;

	/**
	 * 
	 */
	/**
	 * 
	 */
	public Matricula() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param inscripcion
	 * @param oferta
	 * @param usuario
	 */
	public Matricula(int id, Inscripcion inscripcion, Oferta oferta, int usuario) {
		super();
		this.id = id;
		this.inscripcion = inscripcion;
		this.oferta = oferta;
		this.usuario = usuario;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the inscripcion
	 */
	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	/**
	 * @param inscripcion
	 *            the inscripcion to set
	 */
	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}

	/**
	 * @return the oferta
	 */
	public Oferta getOferta() {
		return oferta;
	}

	/**
	 * @param oferta
	 *            the oferta to set
	 */
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	/**
	 * @return the usuario
	 */
	public int getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

}
