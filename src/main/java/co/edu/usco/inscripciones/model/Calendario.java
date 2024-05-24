/**
 * 
 */
package co.edu.usco.inscripciones.model;

/**
 * @author ANDRES-GPIE
 *
 */
public class Calendario {
	private int codigo;
	private String nombre;
	private String valor;
	public Calendario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Calendario(int codigo, String nombre, String valor) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.valor = valor;
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
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}
