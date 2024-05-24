/**
 * 
 */
package co.edu.usco.inscripciones.model;

/**
 * @author jankarlos
 *
 */
public class Uaa {
	private int codigo;
	private String nombre;
	private int dependencia;
	private String nombreCorto;
	private String nombreImpresion;
	private String email;
	private String emailPqr;
	private String pagina;
	private String telefono;
	private String fax;
	private String direccion;
	private String centroCostos;
	private String acronimo;
	private int estado;
	private int ventanilla;
	private String codigoRetencion;
	private String nombreDependencia;
	private int codigoTipoUaaDep;
	
	private UaaTipo uaaTipo;
	private Municipio municipio;
	private Departamento departamento;
	private Sede sede;
	private Programa programa;
	public Uaa() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Uaa(int codigo, String nombre, int dependencia, String nombreCorto, String nombreImpresion, String email,
			String emailPqr, String pagina, String telefono, String fax, String direccion, String centroCostos,
			String acronimo, int estado, int ventanilla, String codigoRetencion, String nombreDependencia,
			int codigoTipoUaaDep, UaaTipo uaaTipo, Municipio municipio, Departamento departamento, Sede sede,
			Programa programa) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.dependencia = dependencia;
		this.nombreCorto = nombreCorto;
		this.nombreImpresion = nombreImpresion;
		this.email = email;
		this.emailPqr = emailPqr;
		this.pagina = pagina;
		this.telefono = telefono;
		this.fax = fax;
		this.direccion = direccion;
		this.centroCostos = centroCostos;
		this.acronimo = acronimo;
		this.estado = estado;
		this.ventanilla = ventanilla;
		this.codigoRetencion = codigoRetencion;
		this.nombreDependencia = nombreDependencia;
		this.codigoTipoUaaDep = codigoTipoUaaDep;
		this.uaaTipo = uaaTipo;
		this.municipio = municipio;
		this.departamento = departamento;
		this.sede = sede;
		this.programa = programa;
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
	public int getDependencia() {
		return dependencia;
	}
	public void setDependencia(int dependencia) {
		this.dependencia = dependencia;
	}
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public String getNombreImpresion() {
		return nombreImpresion;
	}
	public void setNombreImpresion(String nombreImpresion) {
		this.nombreImpresion = nombreImpresion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailPqr() {
		return emailPqr;
	}
	public void setEmailPqr(String emailPqr) {
		this.emailPqr = emailPqr;
	}
	public String getPagina() {
		return pagina;
	}
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCentroCostos() {
		return centroCostos;
	}
	public void setCentroCostos(String centroCostos) {
		this.centroCostos = centroCostos;
	}
	public String getAcronimo() {
		return acronimo;
	}
	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getVentanilla() {
		return ventanilla;
	}
	public void setVentanilla(int ventanilla) {
		this.ventanilla = ventanilla;
	}
	public String getCodigoRetencion() {
		return codigoRetencion;
	}
	public void setCodigoRetencion(String codigoRetencion) {
		this.codigoRetencion = codigoRetencion;
	}
	public String getNombreDependencia() {
		return nombreDependencia;
	}
	public void setNombreDependencia(String nombreDependencia) {
		this.nombreDependencia = nombreDependencia;
	}
	public int getCodigoTipoUaaDep() {
		return codigoTipoUaaDep;
	}
	public void setCodigoTipoUaaDep(int codigoTipoUaaDep) {
		this.codigoTipoUaaDep = codigoTipoUaaDep;
	}
	public UaaTipo getUaaTipo() {
		return uaaTipo;
	}
	public void setUaaTipo(UaaTipo uaaTipo) {
		this.uaaTipo = uaaTipo;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	public Programa getPrograma() {
		return programa;
	}
	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	
		
}
