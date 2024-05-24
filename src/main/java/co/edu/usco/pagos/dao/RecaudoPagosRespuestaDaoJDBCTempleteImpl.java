/**
 * 
 */
package co.edu.usco.pagos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import co.edu.usco.pagos.model.RecaudoPagosRespuesta;

/**
 * @author jankarlos
 *
 */
@Component
public class RecaudoPagosRespuestaDaoJDBCTempleteImpl implements RecaudoPagosRespuestaDao {

	@Autowired
	DataSource dataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.usco.pagos.dao.RecaudoPagosRespuestaDao#agregarRespuesta(co.edu.
	 * usco.pagos.model.RecaudoPagosRespuesta)
	 */
	@Override
	public int agregarRespuesta(final RecaudoPagosRespuesta respuesta) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			final String sql = "INSERT INTO dbo.recaudo_pagosenlinea_respuesta (repp_status, pas_codigo, repr_fecha_respuesta) VALUES (?,?,CURRENT_TIMESTAMP)";

			KeyHolder keyHolder = new GeneratedKeyHolder();

			int resultado = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

					pstm.setInt(1, respuesta.getPeticion().getCodigo());
					pstm.setString(2, respuesta.getStatus().getReason());
					//pstm.setTimestamp(3, respuesta.getFecha());
					return pstm;
				}

			}, keyHolder);
			return keyHolder.getKey().intValue();

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
