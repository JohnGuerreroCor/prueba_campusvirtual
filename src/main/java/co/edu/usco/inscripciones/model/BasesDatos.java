/**
 * 
 */
package co.edu.usco.inscripciones.model;

/**
 * @author ANDRES-GPIE
 *
 */
public class BasesDatos {	
	private int id;
	private int codigo;
	private String nombre;
	private String areaTema;
	private String descripcion;
	private String tipoAcceso;
	private String link;
	private String imagen;
	
	public BasesDatos() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BasesDatos(int id, int codigo, String nombre, String areaTema, String descripcion, String tipoAcceso,
			String link, String imagen) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.areaTema = areaTema;
		this.descripcion = descripcion;
		this.tipoAcceso = tipoAcceso;
		this.link = link;
		this.imagen = imagen;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getAreaTema() {
		return areaTema;
	}
	public void setAreaTema(String areaTema) {
		this.areaTema = areaTema;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipoAcceso() {
		return tipoAcceso;
	}
	public void setTipoAcceso(String tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
}
