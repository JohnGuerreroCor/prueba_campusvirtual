package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Liquidacion;


@Component
public class LiquidacionDaoJDBCTemplateImpl implements LiquidacionDao {

	@Autowired
	DataSource dataSource;
	@Override
	public Liquidacion consultarLiquidacionConfiguracion(int lit_codigo, int uaa_codigo, int cal_codigo) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			String sql = "SELECT lic_codigo "
					+ " FROM liquidacion_configuracion"
					+ " WHERE lit_codigo = " + lit_codigo
					+ " AND uaa_codigo = " + uaa_codigo
					//+ " AND cal_codigo = " + cal_codigo
					+ " GROUP BY lic_codigo";
			
			System.out.println("LIQUIDACIONN CONFIGURACION");
			System.out.println(sql);
			List<Liquidacion> listaLiquidacion = jdbcTemplate.query(sql, new RowMapper<Liquidacion>() {

				public Liquidacion mapRow(ResultSet rs, int rowNum) throws SQLException {
					Liquidacion liquidacion = new Liquidacion();
					liquidacion.setCodigo(rs.getInt("lic_codigo"));
					return liquidacion;
				}
			});
			if (listaLiquidacion.size() > 0) {
				return listaLiquidacion.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
