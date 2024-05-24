/**
 * 
 */
package co.edu.usco.pagos.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.edu.usco.pagos.model.RecaudoPagosAprobado;

/**
 * @author jankarlos
 *
 */
@Component
public class RecaudoPagosAprobadoDaoJDBCTempleteImpl implements RecaudoPagosAprobadoDao {

	@Autowired
	DataSource dataSource;

	@Override
	public boolean agregarAprobado(RecaudoPagosAprobado recaudoPagosAprobado) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "INSERT INTO dbo.recaudo_pagosenlinea_aprobado (repr_codigo, rec_codigo) ";
			sql = sql + " VALUES (?,?)";

			int resultado = jdbcTemplate.update(sql, recaudoPagosAprobado.getRecaudoPagosRespuesta().getCodigo(),
					recaudoPagosAprobado.getRecaudo().getCodigo());

			if (resultado > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
