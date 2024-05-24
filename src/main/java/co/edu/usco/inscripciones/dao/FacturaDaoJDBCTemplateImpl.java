package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Factura;
import co.edu.usco.pagos.model.BancoCuenta;

@Component
public class FacturaDaoJDBCTemplateImpl implements FacturaDao {

	@Autowired
	DataSource dataSource;

	@Autowired
	WebParametroDao webParametroDao;

	@Override
	public boolean validarFacturaExista(long referencia) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "SELECT fac_codigo FROM factura WHERE fac_codigo = " + referencia;

			List<Factura> listaFacturaInscripcion = jdbcTemplate.query(sql, new RowMapper<Factura>() {

				public Factura mapRow(ResultSet rs, int rowNum) throws SQLException {
					Factura factura = new Factura();
					factura.setId(rs.getInt("fac_codigo"));
					return factura;
				}
			});
			if (listaFacturaInscripcion.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean validarFacturaAutorizada(long referencia, String pin) {
		try {
			Factura tipo_cbo = webParametroDao.consultarTipoLiquidacion();
			//Se agrega codigo 89 tipo liquidacion congreo en salud
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "SELECT f.fac_clave, f.fac_codigo, f.fac_total_abonos, f.liq_codigo, lt.lit_nombre, f.fac_autorizacion "
					+ "FROM factura f, liquidacion l, liquidacion_tipo lt "
					+ "WHERE f.liq_codigo=l.liq_codigo AND l.lit_codigo=lt.lit_codigo AND l.lit_codigo in ("
					+ tipo_cbo.getValorTipoLiquidacion() + ",89) " + " AND f.fac_codigo = " + referencia;
					//+ tipo_cbo.getValorTipoLiquidacion() + ",89) " + " AND f.fac_clave != '' AND f.fac_codigo = " + referencia
					//+ " AND f.fac_clave='" + pin + "'";
			// + "AND f.fac_autorizacion = 1",
			sql = sql + " ORDER BY f.fac_clave DESC ";
			System.out.println(sql);
			List<Factura> listaFacturaAutorizado = jdbcTemplate.query(sql, new RowMapper<Factura>() {

				public Factura mapRow(ResultSet rs, int rowNum) throws SQLException {
					Factura factura = new Factura();
					factura.setPin(rs.getString("fac_clave"));
					factura.setReferencia(rs.getInt("fac_codigo"));
					factura.setAutorizado(rs.getInt("fac_autorizacion"));
					return factura;
				}
			});
			if (listaFacturaAutorizado.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Factura consultarFacturaPagada(long codigoFactura, String pin) {
		try {
			Factura tipo_cbo = webParametroDao.consultarTipoLiquidacion();
			//Se agrega codigo 89 tipo liquidacion congreo en salud
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "SELECT r.rec_valor, f.fac_autorizacion, f.fac_clave,f.fac_codigo,f.fac_total_abonos,r.rec_fecha,f.liq_codigo,lt.lit_nombre,f.fac_autorizacion "
					+ "FROM factura f, recaudo r, liquidacion l, liquidacion_tipo lt "
					+ "WHERE f.fac_codigo=r.fac_codigo AND f.liq_codigo=l.liq_codigo AND l.lit_codigo=lt.lit_codigo "
					+ "AND l.lit_codigo in (" + tipo_cbo.getValorTipoLiquidacion()
					+ ",89) AND f.fac_codigo = '" + codigoFactura + "'";
					//+ ",89) AND f.fac_clave!='' AND f.fac_codigo = '" + codigoFactura + "'";
			//if (pin.length() > 0) {
				//sql = sql + " AND f.fac_clave = '" + pin + "'";
			//}
			sql = sql + "ORDER BY f.fac_clave DESC";
			System.out.println(":::::::FACTURA PAGA::::::");
			System.out.println(sql);
			List<Factura> listaFacturaPagada = jdbcTemplate.query(sql, new RowMapper<Factura>() {

				public Factura mapRow(ResultSet rs, int rowNum) throws SQLException {
					Factura factura = new Factura();
					factura.setId(rs.getInt("fac_codigo"));
					factura.setPin(rs.getString("fac_clave"));
					factura.setFechaRecaudo(new Date(rs.getDate("rec_fecha").getTime()));
					factura.setValorFactura(rs.getFloat("rec_valor"));
					factura.setAutorizado(rs.getInt("fac_autorizacion"));
					return factura;
				}
			});
			if (listaFacturaPagada.size() > 0) {
				return listaFacturaPagada.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Factura consultarFacturaGenerada(long codigoFactura) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT faf_fecha, faf_valor ,itf_codigo FROM factura_fecha WHERE fac_codigo = "
					+ codigoFactura;
			System.out.println("Factura generada:" + sql);
			List<Factura> listaFacturaGenerada = jdbcTemplate.query(sql, new RowMapper<Factura>() {

				public Factura mapRow(ResultSet rs, int rowNum) throws SQLException {
					Factura factura = new Factura();
					factura.setFechaFacturaFecha(new Date(rs.getDate("faf_fecha").getTime()));
					factura.setValorFacturaFecha(rs.getInt("faf_valor"));

					return factura;
				}
			});
			if (listaFacturaGenerada.size() > 0) {
				return listaFacturaGenerada.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Factura consultarPinFactura(int referencia) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "SELECT fac_codigo, fac_clave FROM factura WHERE fac_codigo = " + referencia;

			List<Factura> listaFacturaInscripcion = jdbcTemplate.query(sql, new RowMapper<Factura>() {

				public Factura mapRow(ResultSet rs, int rowNum) throws SQLException {
					Factura factura = new Factura();
					factura.setId(rs.getInt("fac_codigo"));
					factura.setPin(rs.getString("fac_clave"));
					return factura;
				}
			});
			if (listaFacturaInscripcion.size() > 0) {
				return listaFacturaInscripcion.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}

	@Override
	public Factura consultarbancoCuentaFactura(int factura) {
		// TODO Auto-generated method stub
		System.out.println("La factura es:" + factura);
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "SELECT f.fac_codigo, f.fac_clave, bc.bac_codigo";
			sql = sql + " FROM dbo.factura f";
			sql = sql + " INNER JOIN dbo.bancoxgrupo bg ON f.bag_codigo = bg.bag_codigo";
			sql = sql + " INNER JOIN dbo.banco_cuenta bc ON bg.bac_codigo = bc.bac_codigo ";
			sql = sql + " WHERE f.fac_codigo = " + factura;

			List<Factura> listaFactura = jdbcTemplate.query(sql, new RowMapper<Factura>() {

				public Factura mapRow(ResultSet rs, int rowNum) throws SQLException {
					Factura factura = new Factura();
					factura.setId(rs.getInt("fac_codigo"));
					factura.setPin(rs.getString("fac_clave"));
					BancoCuenta bancoCuenta = new BancoCuenta();
					bancoCuenta.setCodigo(rs.getInt("bac_codigo"));
					factura.setBancoCuenta(bancoCuenta);
					return factura;
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
