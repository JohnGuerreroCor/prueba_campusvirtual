/**
 * 
 */
package co.edu.usco.inscripciones.model;

import java.util.Date;

/**
 * @author ANDRES-GPIE
 *
 */
public class Oferta {
	private int id;

	private int codigo;
	private String codigoEncriptado;
	private Date fechaIncripcionInicio;
	private Date fechaIncripcionFin;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;
	private int admisionAutomatica;
	private int requierePago;
	private int crearUsuario;
	private String imagen;
	private int cupoMaximo;
	private int cantidadInscritos;
	private float valor;
	private String interna;
	private String internaProveedores;

	private String titulo;
	private String contenido;

	private Programa programa;
	private OfertaRequisitos ofertaRequisitos;
	private Calendario calendario;

	public Oferta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Oferta(int id, int codigo, String codigoEncriptado, Date fechaIncripcionInicio, Date fechaIncripcionFin,
			Date fechaInicio, Date fechaFin, String estado, int admisionAutomatica, int requierePago, int crearUsuario,
			String imagen, int cupoMaximo, int cantidadInscritos, float valor, String interna,
			String internaProveedores, String titulo, String contenido, Programa programa,
			OfertaRequisitos ofertaRequisitos, Calendario calendario) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.codigoEncriptado = codigoEncriptado;
		this.fechaIncripcionInicio = fechaIncripcionInicio;
		this.fechaIncripcionFin = fechaIncripcionFin;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.admisionAutomatica = admisionAutomatica;
		this.requierePago = requierePago;
		this.crearUsuario = crearUsuario;
		this.imagen = imagen;
		this.cupoMaximo = cupoMaximo;
		this.cantidadInscritos = cantidadInscritos;
		this.valor = valor;
		this.interna = interna;
		this.internaProveedores = internaProveedores;
		this.titulo = titulo;
		this.contenido = contenido;
		this.programa = programa;
		this.ofertaRequisitos = ofertaRequisitos;
		this.calendario = calendario;
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

	public String getCodigoEncriptado() {
		return codigoEncriptado;
	}

	public void setCodigoEncriptado(String codigoEncriptado) {
		this.codigoEncriptado = codigoEncriptado;
	}

	public Date getFechaIncripcionInicio() {
		return fechaIncripcionInicio;
	}

	public void setFechaIncripcionInicio(Date fechaIncripcionInicio) {
		this.fechaIncripcionInicio = fechaIncripcionInicio;
	}

	public Date getFechaIncripcionFin() {
		return fechaIncripcionFin;
	}

	public void setFechaIncripcionFin(Date fechaIncripcionFin) {
		this.fechaIncripcionFin = fechaIncripcionFin;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getAdmisionAutomatica() {
		return admisionAutomatica;
	}

	public void setAdmisionAutomatica(int admisionAutomatica) {
		this.admisionAutomatica = admisionAutomatica;
	}

	public int getRequierePago() {
		return requierePago;
	}

	public void setRequierePago(int requierePago) {
		this.requierePago = requierePago;
	}

	public int getCrearUsuario() {
		return crearUsuario;
	}

	public void setCrearUsuario(int crearUsuario) {
		this.crearUsuario = crearUsuario;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getCupoMaximo() {
		return cupoMaximo;
	}

	public void setCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}

	public int getCantidadInscritos() {
		return cantidadInscritos;
	}

	public void setCantidadInscritos(int cantidadInscritos) {
		this.cantidadInscritos = cantidadInscritos;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getInterna() {
		return interna;
	}

	public void setInterna(String interna) {
		this.interna = interna;
	}

	public String getInternaProveedores() {
		return internaProveedores;
	}

	public void setInternaProveedores(String internaProveedores) {
		this.internaProveedores = internaProveedores;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public OfertaRequisitos getOfertaRequisitos() {
		return ofertaRequisitos;
	}

	public void setOfertaRequisitos(OfertaRequisitos ofertaRequisitos) {
		this.ofertaRequisitos = ofertaRequisitos;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

}
