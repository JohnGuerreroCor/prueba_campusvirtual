/**
 * 
 */
package co.edu.usco.inscripciones.model;

/**
 * @author ANDRES-GPIE
 *
 */
public class Liquidacion {
	
	private int codigo;
	
	private LiquidacionConfiguracion liquidacionConfiguracion;

	public Liquidacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Liquidacion(int codigo, LiquidacionConfiguracion liquidacionConfiguracion) {
		super();
		this.codigo = codigo;
		this.liquidacionConfiguracion = liquidacionConfiguracion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public LiquidacionConfiguracion getLiquidacionConfiguracion() {
		return liquidacionConfiguracion;
	}

	public void setLiquidacionConfiguracion(LiquidacionConfiguracion liquidacionConfiguracion) {
		this.liquidacionConfiguracion = liquidacionConfiguracion;
	}
	
	

}
