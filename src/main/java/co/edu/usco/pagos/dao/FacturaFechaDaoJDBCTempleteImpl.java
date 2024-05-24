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

import co.edu.usco.inscripciones.model.Factura;
import co.edu.usco.inscripciones.model.Persona;
import co.edu.usco.inscripciones.model.Uaa;
import co.edu.usco.pagos.model.FacturaFecha;

/**
 * @author jankarlos
 *
 */
@Component
public class FacturaFechaDaoJDBCTempleteImpl implements FacturaFechaDao {

	@Autowired
	DataSource dataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.usco.pagos.dao.FacturaFechaDao#consultar(long)
	 */
	@Override
	public FacturaFecha consultar(long factura, int uaa, int persona) {

		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "select top 1 f.fac_codigo, f.fac_clave, f.fac_observacion, ff.faf_codigo, ";
			sql = sql + " ff.faf_valor, ff.faf_fecha, l.liq_codigo, l.uaa_codigo, l.per_codigo, l.ter_codigo ";
			sql = sql + " from factura f inner join factura_fecha ff on (f.fac_codigo = ff.fac_codigo)";
			sql = sql + " inner join liquidacion l on l.liq_codigo = f.liq_codigo";
			if (factura > 0) {
				sql = sql + " where ff.fac_codigo = " + factura;
			}
			if (uaa > 0 && persona > 0) {
				sql = sql + " where (l.uaa_codigo = " + uaa + " and l.per_codigo=" + persona + ") or (l.uaa_codigo = "
						+ uaa + " and l.ter_codigo=" + persona + ")";
			}
			sql = sql + " ORDER BY ff.fac_codigo DESC";
			System.out.println("CONSULTA FACTURA");
			System.out.println(sql);
			List<FacturaFecha> listaFactura = jdbcTemplate.query(sql, new RowMapper<FacturaFecha>() {

				public FacturaFecha mapRow(ResultSet rs, int rowNum) throws SQLException {

					FacturaFecha facturaFecha = new FacturaFecha();
					facturaFecha.setCodigo(rs.getLong("faf_codigo"));
					facturaFecha.setFecha(rs.getDate("faf_fecha"));
					facturaFecha.setValor(rs.getFloat("faf_valor"));
					
					Factura factura = new Factura();
					factura.setId(rs.getInt("fac_codigo"));
					factura.setPin(rs.getString("fac_clave"));

					Uaa uaa = new Uaa();
					uaa.setCodigo(rs.getInt("uaa_codigo"));
					factura.setUaa(uaa);

					Persona persona = new Persona();
					persona.setCodigo(rs.getInt("per_codigo"));
					factura.setPersona(persona);

					Persona tercero = new Persona();
					tercero.setCodigo(rs.getInt("ter_codigo"));
					factura.setTercero(tercero);

					facturaFecha.setFactura(factura);

					return facturaFecha;
				}
			});
			if (listaFactura.size() > 0) {
				return listaFactura.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
