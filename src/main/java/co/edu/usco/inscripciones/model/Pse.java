/**
 * 
 */
package co.edu.usco.inscripciones.model;

/**
 * @author AndresParedes
 *
 */
public class Pse {
	public int id;
	public Persona persona;
	public PreInscripcion preInscripcion;
	public Oferta oferta;
	public Pse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pse(int id, Persona persona, PreInscripcion preInscripcion, Oferta oferta) {
		super();
		this.id = id;
		this.persona = persona;
		this.preInscripcion = preInscripcion;
		this.oferta = oferta;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}
	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	/**
	 * @return the preInscripcion
	 */
	public PreInscripcion getPreInscripcion() {
		return preInscripcion;
	}
	/**
	 * @param preInscripcion the preInscripcion to set
	 */
	public void setPreInscripcion(PreInscripcion preInscripcion) {
		this.preInscripcion = preInscripcion;
	}
	/**
	 * @return the oferta
	 */
	public Oferta getOferta() {
		return oferta;
	}
	/**
	 * @param oferta the oferta to set
	 */
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	
		
	
}
