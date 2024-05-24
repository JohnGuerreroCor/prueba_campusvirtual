/**
 * 
 */
package co.edu.usco.pagos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.pagos.dto.Status;

/**
 * @author jankarlos
 *
 */
@Component
public class PagoStatusDaoJDBCTempleteImpl implements PagoStatusDao {

	@Autowired
	DataSource dataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.usco.pagos.dao.PagoStatusDao#consultarStatus(java.lang.String)
	 */
	@Override
	public Status consultarStatus(String status) {
		// TODO Auto-generated method stub
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT ps.pas_codigo, ps.pas_status, ps.pas_mensaje FROM dbo.pagosenlinea_status ps";
		sql = sql + " WHERE ps.pas_status = ?";

		List<Status> listaStatus = jdbcTemplate.query(sql, new Object[] { status }, new RowMapper<Status>() {

			public Status mapRow(ResultSet rs, int rowNum) throws SQLException {

				Status status = new Status();
				status.setStatus(rs.getString("pas_status"));
				status.setMessage(rs.getString("pas_mensaje"));
				status.setReason(rs.getString("pas_codigo"));
				return status;
			}

		});
		if (listaStatus.size() > 0) {
			return listaStatus.get(0);
		} else {
			return null;
		}
	}

}
