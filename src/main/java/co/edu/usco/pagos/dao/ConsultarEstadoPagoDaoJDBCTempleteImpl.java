package co.edu.usco.pagos.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.pagos.dto.ConsultaPago;

@Component
public class ConsultarEstadoPagoDaoJDBCTempleteImpl implements ConsultarEstadoPagoDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<ConsultaPago> consultar(long cedula) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT TOP 5 f.fac_codigo,rpp.repp_autorizacion,ps.pas_status,f.fac_fecha,";
			sql = sql + "l.liq_total,t.ter_identificacion,p.per_identificacion";
			sql = sql + " FROM factura f";
			sql = sql + " INNER JOIN recaudo_pagosenlinea_peticion rpp ON f.fac_codigo = rpp.fac_codigo";
			sql = sql + " INNER JOIN liquidacion l ON f.liq_codigo = l.liq_codigo";
			sql = sql + " LEFT OUTER JOIN tercero AS t ON l.ter_codigo = t.ter_codigo";
			sql = sql + " LEFT OUTER JOIN persona AS p ON l.per_codigo = p.per_codigo";
			sql = sql + " INNER JOIN pagosenlinea_status ps ON rpp.pas_codigo = ps.pas_codigo";
			sql = sql
					+ " LEFT JOIN recaudo_pagosenlinea_respuesta rpr ON (rpp.repp_codigo = rpr.repp_status AND rpr.pas_codigo != 3)";
			sql = sql + " LEFT JOIN recaudo_pagosenlinea_aprobado rpa ON rpa.repr_codigo = rpr.repr_codigo";

			if (cedula > 0) {
				sql = sql + " where  t.ter_identificacion = '" + cedula + "' or p.per_identificacion = '" + cedula
						+ "'";
			}
			sql = sql + " ORDER BY f.fac_fecha DESC";

			List<ConsultaPago> listaFactura = jdbcTemplate.query(sql, new RowMapper<ConsultaPago>() {

				public ConsultaPago mapRow(ResultSet rs, int rowNum) throws SQLException {

					ConsultaPago consultaPago = new ConsultaPago();
					consultaPago.setAutorizacion(rs.getString("repp_autorizacion"));
					consultaPago.setEstado(rs.getString("pas_status"));
					consultaPago.setFactura(rs.getString("fac_codigo"));
					consultaPago.setIdentificacion(rs.getLong("per_identificacion"));
					consultaPago.setValor(rs.getFloat("liq_total"));
					consultaPago.setFecha(rs.getString("fac_fecha"));
					return consultaPago;
				}
			});
			return listaFactura;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
