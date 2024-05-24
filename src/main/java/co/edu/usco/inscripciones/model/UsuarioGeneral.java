/**
 * 
 */
package co.edu.usco.inscripciones.model;

/**
 * @author jankarlos
 *
 */
public class UsuarioGeneral {

	private int codigo;
	private Persona persona;
	private String usuario;
	private String pw;
	private int tipo;

	/**
	 * 
	 */
	public UsuarioGeneral() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioGeneral(int codigo, Persona persona, String usuario, String pw, int tipo) {
		super();
		this.codigo = codigo;
		this.persona = persona;
		this.usuario = usuario;
		this.pw = pw;
		this.tipo = tipo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}
