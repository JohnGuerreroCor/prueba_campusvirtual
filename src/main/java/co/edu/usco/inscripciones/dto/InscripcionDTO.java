package co.edu.usco.inscripciones.dto;

public class InscripcionDTO {

	private int cantidadRequisitos;
	private int codigoInscripcion;

	/**
	 * @param cantidadRequisitos
	 * @param codigoInscripcion
	 */
	public InscripcionDTO(int cantidadRequisitos, int codigoInscripcion) {
		super();
		this.cantidadRequisitos = cantidadRequisitos;
		this.codigoInscripcion = codigoInscripcion;
	}
	
	public String toString(){
		return "Cant: " + this.cantidadRequisitos + " Codigo: " + this.codigoInscripcion;
	}

	/**
	 * @return the cantidadRequisitos
	 */
	public int getCantidadRequisitos() {
		return cantidadRequisitos;
	}

	/**
	 * @param cantidadRequisitos
	 *            the cantidadRequisitos to set
	 */
	public void setCantidadRequisitos(int cantidadRequisitos) {
		this.cantidadRequisitos = cantidadRequisitos;
	}

	/**
	 * @return the codigoInscripcion
	 */
	public int getCodigoInscripcion() {
		return codigoInscripcion;
	}

	/**
	 * @param codigoInscripcion
	 *            the codigoInscripcion to set
	 */
	public void setCodigoInscripcion(int codigoInscripcion) {
		this.codigoInscripcion = codigoInscripcion;
	}

}
