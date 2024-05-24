package co.edu.usco.pagos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import co.edu.usco.pagos.model.Recaudo;

@Component
public class RecaudoDaoJDBCTempleteImpl implements RecaudoDao {

	@Autowired
	DataSource dataSource;

	@Override
	public int agregarRecaudo(final Recaudo recaudo) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			final String sql = "INSERT INTO recaudo (fac_codigo, rec_valor, bac_codigo, rec_fecha) VALUES (?,?,?,GETDATE())";

			KeyHolder keyHolder = new GeneratedKeyHolder();

			int resultado = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

					pstm.setLong(1, recaudo.getFactura().getReferencia());
					pstm.setInt(2, recaudo.getValor());
					pstm.setInt(3, recaudo.getBancoCuenta().getCodigo());
					return pstm;
				}

			}, keyHolder);
			return keyHolder.getKey().intValue();

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean validarRecaudoFactura(int factura) {
		// TODO Auto-generated method stub
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT r.fac_codigo, r.rec_codigo FROM dbo.recaudo r";
		sql = sql + " WHERE r.fac_codigo = ?";

		List<Recaudo> listaRecaudo = jdbcTemplate.query(sql, new Object[] { factura }, new RowMapper<Recaudo>() {

			public Recaudo mapRow(ResultSet rs, int rowNum) throws SQLException {

				Recaudo recaudo = new Recaudo();
				recaudo.setCodigo(rs.getInt("rec_codigo"));
				return recaudo;
			}

		});
		if (listaRecaudo.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

}
