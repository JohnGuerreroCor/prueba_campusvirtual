package co.edu.usco.pagos.utilidad;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.usco.inscripciones.dao.FacturaDao;
import co.edu.usco.inscripciones.dao.InscripcionDao;
import co.edu.usco.inscripciones.dao.LiquidacionDao;
import co.edu.usco.inscripciones.dao.OfertaDao;
import co.edu.usco.inscripciones.dao.PreIncripcionDao;
import co.edu.usco.inscripciones.dao.WebParametroDao;
import co.edu.usco.inscripciones.model.Factura;
import co.edu.usco.inscripciones.model.Liquidacion;
import co.edu.usco.inscripciones.model.Oferta;
import co.edu.usco.inscripciones.model.PreInscripcion;
import co.edu.usco.inscripciones.utilidad.Constantes;
import co.edu.usco.inscripciones.utilidad.ObtenerFechaDDMMAAAA;
import co.edu.usco.librerias.EncriptarPalabra;

public class pruebaFactura {

	@Autowired
	FacturaDao facturaDao;

	@Autowired
	InscripcionDao inscripcionDao;

	@Autowired
	static WebParametroDao webParametroDao;

	@Autowired
	static OfertaDao ofertaDao;

	@Autowired
	static LiquidacionDao liquidacionDao;

	@Autowired
	static PreIncripcionDao preInscripcionDao;

	@Autowired
	Constantes constantes;

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Oferta datosOferta = ofertaDao.consultarDatosOferta(2072);
		if (datosOferta != null) {
			if (datosOferta.getRequierePago() == 1) { // validar que la oferta
														// sea paga
				// PreInscripcion preInscripcionDatos =
				// preInscripcionDao.validarPreInscripcion(1079183997, 2072);
				// if (preInscripcionDatos != null) {
				// if (preInscripcionDatos.getEmailVerificado() == 1) { //
				// verificar
				// si
				// la
				// preInscripcion
				// esta
				// verificada
				Factura tipo_cbo = webParametroDao.consultarTipoLiquidacion();

				Factura identificadores = webParametroDao.consultarTokenFactura();
				String[] temp;
				temp = identificadores.getValorToken().toString().split(",");

				String token = "";
				token = temp[0] + "~1079183997~" + ObtenerFechaDDMMAAAA.obtenerFecha() + "~2072~" + temp[1];

				int programa_cbo = datosOferta.getPrograma().getCodigoUaa();

				Liquidacion configuracion = liquidacionDao.consultarLiquidacionConfiguracion(
						tipo_cbo.getValorTipoLiquidacion(), programa_cbo, datosOferta.getCalendario().getCodigo());
				System.out.println("accion=2&config=1&cedula=1079183997&tipo_cbo=" + tipo_cbo.getValorTipoLiquidacion()
						+ "&programa_cbo=" + programa_cbo + "&config_cbo=" + configuracion.getCodigo()
						+ "&codofa=2072&token=" + EncriptarPalabra.SHA1(token));

				// }
				// }
			}
		}
	}
}
