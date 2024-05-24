/**
 * 
 */
package co.edu.usco.inscripciones.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ANDRES-GPIE
 *
 */

@JsonIgnoreProperties
public class Persona {
	private int id;

	private boolean tercero;
	private int codigo;
	private String nombreCompleto;
	private String identificacion;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String barrio;
	private String telefonoMovil;
	private String email;
	private String emailPreInscripcion;
	private String emailInterno;
	private Date fechaNacimiento;
	private Date fechaExpedicion;

	private Long fechaNacimientoLong;
	private Long fechaExpedicionLong;

	private long identificacionCotizante;
	private String genero;

	private LenguaNativa lenguaNativa;
	private EstratoSocioeconomico estrato;
	private TipoIdentificacion tipoIdentificacion;
	private Municipio lugarNacimiento;
	private Municipio lugarExpedicion;
	private Eps eps;

	private EpsTipoAfiliacion tipoAfiliacion;

	private EstadoCivil estadoCivil;
	private GrupoSanguineo grupoSanguineo;
	private InstitucionEducativa institucionEducativa;
	private Pais lugarResidenciaPais;
	private Departamento lugarResidenciaDepartamento;
	private Municipio lugarResidencia;

	private String identificacionProveedor;

	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Persona(int id, boolean tercero, int codigo, String nombreCompleto, String identificacion, String nombres,
			String apellidos, String direccion, String barrio, String telefonoMovil, String email,
			String emailPreInscripcion, String emailInterno, Date fechaNacimiento, Date fechaExpedicion,
			Long fechaNacimientoLong, Long fechaExpedicionLong, long identificacionCotizante, String genero,
			LenguaNativa lenguaNativa, EstratoSocioeconomico estrato, TipoIdentificacion tipoIdentificacion,
			Municipio lugarNacimiento, Municipio lugarExpedicion, Eps eps, EpsTipoAfiliacion tipoAfiliacion,
			EstadoCivil estadoCivil, GrupoSanguineo grupoSanguineo, InstitucionEducativa institucionEducativa,
			Pais lugarResidenciaPais, Departamento lugarResidenciaDepartamento, Municipio lugarResidencia,
			String identificacionProveedor) {
		super();
		this.id = id;
		this.tercero = tercero;
		this.codigo = codigo;
		this.nombreCompleto = nombreCompleto;
		this.identificacion = identificacion;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.barrio = barrio;
		this.telefonoMovil = telefonoMovil;
		this.email = email;
		this.emailPreInscripcion = emailPreInscripcion;
		this.emailInterno = emailInterno;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaExpedicion = fechaExpedicion;
		this.fechaNacimientoLong = fechaNacimientoLong;
		this.fechaExpedicionLong = fechaExpedicionLong;
		this.identificacionCotizante = identificacionCotizante;
		this.genero = genero;
		this.lenguaNativa = lenguaNativa;
		this.estrato = estrato;
		this.tipoIdentificacion = tipoIdentificacion;
		this.lugarNacimiento = lugarNacimiento;
		this.lugarExpedicion = lugarExpedicion;
		this.eps = eps;
		this.tipoAfiliacion = tipoAfiliacion;
		this.estadoCivil = estadoCivil;
		this.grupoSanguineo = grupoSanguineo;
		this.institucionEducativa = institucionEducativa;
		this.lugarResidenciaPais = lugarResidenciaPais;
		this.lugarResidenciaDepartamento = lugarResidenciaDepartamento;
		this.lugarResidencia = lugarResidencia;
		this.identificacionProveedor = identificacionProveedor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isTercero() {
		return tercero;
	}

	public void setTercero(boolean tercero) {
		this.tercero = tercero;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailPreInscripcion() {
		return emailPreInscripcion;
	}

	public void setEmailPreInscripcion(String emailPreInscripcion) {
		this.emailPreInscripcion = emailPreInscripcion;
	}

	public String getEmailInterno() {
		return emailInterno;
	}

	public void setEmailInterno(String emailInterno) {
		this.emailInterno = emailInterno;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public Long getFechaNacimientoLong() {
		return fechaNacimientoLong;
	}

	public void setFechaNacimientoLong(Long fechaNacimientoLong) {
		this.fechaNacimientoLong = fechaNacimientoLong;
	}

	public Long getFechaExpedicionLong() {
		return fechaExpedicionLong;
	}

	public void setFechaExpedicionLong(Long fechaExpedicionLong) {
		this.fechaExpedicionLong = fechaExpedicionLong;
	}

	public long getIdentificacionCotizante() {
		return identificacionCotizante;
	}

	public void setIdentificacionCotizante(long identificacionCotizante) {
		this.identificacionCotizante = identificacionCotizante;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public LenguaNativa getLenguaNativa() {
		return lenguaNativa;
	}

	public void setLenguaNativa(LenguaNativa lenguaNativa) {
		this.lenguaNativa = lenguaNativa;
	}

	public EstratoSocioeconomico getEstrato() {
		return estrato;
	}

	public void setEstrato(EstratoSocioeconomico estrato) {
		this.estrato = estrato;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public Municipio getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(Municipio lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public Municipio getLugarExpedicion() {
		return lugarExpedicion;
	}

	public void setLugarExpedicion(Municipio lugarExpedicion) {
		this.lugarExpedicion = lugarExpedicion;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public EpsTipoAfiliacion getTipoAfiliacion() {
		return tipoAfiliacion;
	}

	public void setTipoAfiliacion(EpsTipoAfiliacion tipoAfiliacion) {
		this.tipoAfiliacion = tipoAfiliacion;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public GrupoSanguineo getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public void setGrupoSanguineo(GrupoSanguineo grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	public InstitucionEducativa getInstitucionEducativa() {
		return institucionEducativa;
	}

	public void setInstitucionEducativa(InstitucionEducativa institucionEducativa) {
		this.institucionEducativa = institucionEducativa;
	}

	public Pais getLugarResidenciaPais() {
		return lugarResidenciaPais;
	}

	public void setLugarResidenciaPais(Pais lugarResidenciaPais) {
		this.lugarResidenciaPais = lugarResidenciaPais;
	}

	public Departamento getLugarResidenciaDepartamento() {
		return lugarResidenciaDepartamento;
	}

	public void setLugarResidenciaDepartamento(Departamento lugarResidenciaDepartamento) {
		this.lugarResidenciaDepartamento = lugarResidenciaDepartamento;
	}

	public Municipio getLugarResidencia() {
		return lugarResidencia;
	}

	public void setLugarResidencia(Municipio lugarResidencia) {
		this.lugarResidencia = lugarResidencia;
	}

	public String getIdentificacionProveedor() {
		return identificacionProveedor;
	}

	public void setIdentificacionProveedor(String identificacionProveedor) {
		this.identificacionProveedor = identificacionProveedor;
	}

}
