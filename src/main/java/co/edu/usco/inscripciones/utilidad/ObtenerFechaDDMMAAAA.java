/**
 * 
 */
package co.edu.usco.inscripciones.utilidad;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @author ANDRES-GPIE
 *
 */
public class ObtenerFechaDDMMAAAA {
	public static String obtenerFecha(){
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String cadenaFecha = formato.format(fechaActual);
		return cadenaFecha;
	}
}
