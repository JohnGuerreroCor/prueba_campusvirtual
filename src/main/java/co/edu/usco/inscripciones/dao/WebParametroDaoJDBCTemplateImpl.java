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
import co.edu.usco.inscripciones.model.Factura;
import co.edu.usco.inscripciones.model.WebParametro;


@Component
public class WebParametroDaoJDBCTemplateImpl implements WebParametroDao {

	@Autowired
	DataSource dataSource;
	
	@Override
	public Calendario consultarIncripcionPregradoCalendario() {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			String sql = "SELECT wep_valor "
					+ "FROM web_parametro "
					+ "WHERE wep_codigo = 2";
			List<Calendario> listaCalendario = jdbcTemplate.query(sql, new RowMapper<Calendario>() {

				public Calendario mapRow(ResultSet rs, int rowNum) throws SQLException {
					Calendario calendario = new Calendario();
					calendario.setValor(rs.getString("wep_valor"));
					
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
	@Override
	public Factura consultarTokenFactura() {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			String sql = "SELECT wep_valor "
					+ "FROM web_parametro "
					+ "WHERE wep_nombre = 'EDUCACION_VIRTUAL_IDENTIFICADORES_TOKEN_GENERAR_FACTURA'";
			List<Factura> listaFactura = jdbcTemplate.query(sql, new RowMapper<Factura>() {

				public Factura mapRow(ResultSet rs, int rowNum) throws SQLException {
					Factura factura = new Factura();
					factura.setValorToken(rs.getString("wep_valor"));
					return factura;
				}
			});
			if (listaFactura.size() > 0) {
				return listaFactura.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Factura consultarTipoLiquidacion() {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			String sql = "SELECT wep_valor FROM web_parametro  WHERE wep_nombre = 'EDUCACION_VIRTUAL_TIPO_LIQUIDACION'";
			List<Factura> listaFactura = jdbcTemplate.query(sql, new RowMapper<Factura>() {

				public Factura mapRow(ResultSet rs, int rowNum) throws SQLException {
					Factura factura = new Factura();
					factura.setValorTipoLiquidacion(rs.getInt("wep_valor"));
					return factura;
				}
			});
			if (listaFactura.size() > 0) {
				return listaFactura.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public WebParametro listarWebParametro(String nombre) {		
		try {
			// TODO Auto-generated method stub
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT wep_codigo, wep_nombre, wep_descripcion, wep_valor FROM web_parametro WHERE wep_nombre = '" + nombre + "'";

			List<WebParametro> listaWebParametro = jdbcTemplate.query(sql, new RowMapper<WebParametro>() {

				public WebParametro mapRow(ResultSet rs, int rowNum) throws SQLException {
					WebParametro webParametro = new WebParametro();
					webParametro.setCodigo(rs.getInt("wep_codigo"));
					webParametro.setNombre(rs.getString("wep_nombre"));
					webParametro.setDescripcion(rs.getString("wep_descripcion"));
					webParametro.setValor(rs.getString("wep_valor"));
					return webParametro;
				}
			});
			
			if (listaWebParametro.size() > 0) {
				return listaWebParametro.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
