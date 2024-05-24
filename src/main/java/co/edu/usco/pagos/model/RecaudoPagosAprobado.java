package co.edu.usco.pagos.model;

public class RecaudoPagosAprobado {

	private int codigo;
	private Recaudo recaudo;
	private RecaudoPagosRespuesta recaudoPagosRespuesta;

	/**
	 * 
	 */
	public RecaudoPagosAprobado() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param codigo
	 * @param recaudo
	 * @param recaudoPagosRespuesta
	 */
	public RecaudoPagosAprobado(int codigo, Recaudo recaudo, RecaudoPagosRespuesta recaudoPagosRespuesta) {
		super();
		this.codigo = codigo;
		this.recaudo = recaudo;
		this.recaudoPagosRespuesta = recaudoPagosRespuesta;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the recaudo
	 */
	public Recaudo getRecaudo() {
		return recaudo;
	}

	/**
	 * @param recaudo
	 *            the recaudo to set
	 */
	public void setRecaudo(Recaudo recaudo) {
		this.recaudo = recaudo;
	}

	/**
	 * @return the recaudoPagosRespuesta
	 */
	public RecaudoPagosRespuesta getRecaudoPagosRespuesta() {
		return recaudoPagosRespuesta;
	}

	/**
	 * @param recaudoPagosRespuesta
	 *            the recaudoPagosRespuesta to set
	 */
	public void setRecaudoPagosRespuesta(RecaudoPagosRespuesta recaudoPagosRespuesta) {
		this.recaudoPagosRespuesta = recaudoPagosRespuesta;
	}

}
