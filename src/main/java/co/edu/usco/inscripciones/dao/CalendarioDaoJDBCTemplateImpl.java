package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Calendario;


@Component
public class CalendarioDaoJDBCTemplateImpl implements CalendarioDao {

	@Autowired
	DataSource dataSource;
	@Override
	public Calendario consultarCalendario(int codigoCalendario) {
		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			String sql = "SELECT TOP 1 cal_codigo, cal_nombre "
					+ "FROM calendario "
					+ "where cal_codigo = " + codigoCalendario;
			
			List<Calendario> listaCalendario = jdbcTemplate.query(sql, new RowMapper<Calendario>() {

				public Calendario mapRow(ResultSet rs, int rowNum) throws SQLException {
					Calendario calendario = new Calendario();
					calendario.setCodigo(rs.getInt("cal_codigo"));
					calendario.setNombre(rs.getString("cal_nombre"));
					return calendario;
				}
			});
			if (listaCalendario.size() > 0) {
				return listaCalendario.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
