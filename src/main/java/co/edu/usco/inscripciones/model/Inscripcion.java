/**
 * 
 */
package co.edu.usco.inscripciones.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ANDRES-GPIE
 *
 */
@JsonIgnoreProperties
public class Inscripcion {

	private int id;
	private int codigo; // no se usa
	private int cantidadCertificados = 0;

	private Persona persona;
	private UsuarioGeneral usuario;
	private Factura factura;
	private Estudiante estudiante;
	private Oferta oferta;
	private InscripcionPrograma inscripcionPrograma;
	private int codigoUsuario;
	private boolean proveedor;

	private List<EstudioAnterior> estudioAnterior;

	public Inscripcion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inscripcion(int id, int codigo, int cantidadCertificados, Persona persona, UsuarioGeneral usuario,
			Factura factura, Estudiante estudiante, Oferta oferta, InscripcionPrograma inscripcionPrograma,
			int codigoUsuario, boolean proveedor, List<EstudioAnterior> estudioAnterior) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.cantidadCertificados = cantidadCertificados;
		this.persona = persona;
		this.usuario = usuario;
		this.factura = factura;
		this.estudiante = estudiante;
		this.oferta = oferta;
		this.inscripcionPrograma = inscripcionPrograma;
		this.codigoUsuario = codigoUsuario;
		this.proveedor = proveedor;
		this.estudioAnterior = estudioAnterior;
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

	public int getCantidadCertificados() {
		return cantidadCertificados;
	}

	public void setCantidadCertificados(int cantidadCertificados) {
		this.cantidadCertificados = cantidadCertificados;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public UsuarioGeneral getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioGeneral usuario) {
		this.usuario = usuario;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public InscripcionPrograma getInscripcionPrograma() {
		return inscripcionPrograma;
	}

	public void setInscripcionPrograma(InscripcionPrograma inscripcionPrograma) {
		this.inscripcionPrograma = inscripcionPrograma;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public boolean isProveedor() {
		return proveedor;
	}

	public void setProveedor(boolean proveedor) {
		this.proveedor = proveedor;
	}

	public List<EstudioAnterior> getEstudioAnterior() {
		return estudioAnterior;
	}

	public void setEstudioAnterior(List<EstudioAnterior> estudioAnterior) {
		this.estudioAnterior = estudioAnterior;
	}

}
