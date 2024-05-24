/**
 * 
 */
package co.edu.usco.inscripciones.model;

import java.util.Date;

/**
 * @author ANDRES-GPIE
 *
 */
public class Admision {
	private int id;
	
	private Date fecha;

	private Inscripcion inscripcion;

	public Admision() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admision(int id, Date fecha, Inscripcion inscripcion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.inscripcion = inscripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}

	
}