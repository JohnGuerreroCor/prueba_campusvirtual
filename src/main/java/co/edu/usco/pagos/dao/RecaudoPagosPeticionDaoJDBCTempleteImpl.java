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

import co.edu.usco.inscripciones.dao.WebParametroDao;
import co.edu.usco.inscripciones.model.Factura;
import co.edu.usco.inscripciones.utilidad.Constantes;
import co.edu.usco.pagos.dto.Status;
import co.edu.usco.pagos.model.Peticion;

/**
 * @author jankarlos
 *
 */
@Component
public class RecaudoPagosPeticionDaoJDBCTempleteImpl implements RecaudoPagosPeticionDao {

	@Autowired
	DataSource dataSource;

	@Autowired
	Constantes constantes;

	@Autowired
	WebParametroDao webParametroDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.usco.pagos.dao.RecaudoPagosPeticionDao#agregarPeticion(co.edu.usco
	 * .pagos.model.Peticion)
	 */
	@Override
	public boolean agregarPeticion(Peticion peticion) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			int estado = Integer
					.parseInt(webParametroDao.listarWebParametro(constantes.PAGOSENLINEA_ESTADO_PENDIENTE).getValor());

			String sql = "INSERT INTO dbo.recaudo_pagosenlinea_peticion (repp_requestid, fac_codigo, pas_codigo) ";
			sql = sql + " VALUES (?,?,?)";

			int resultado = jdbcTemplate.update(sql, peticion.getRequestId(), peticion.getFactura().getReferencia(),
					estado);
			if (resultado > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public Peticion consultarRequestId(int referencia, int requesId) {
		// TODO Auto-generated method stub

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Object[] obj = new Object[] {};

		String sql = "SELECT rp.repp_autorizacion, rp.repp_codigo, rp.repp_requestid, rp.fac_codigo, rp.pas_codigo FROM dbo.recaudo_pagosenlinea_peticion rp";
		sql = sql + " WHERE rp.fac_codigo = ?";
		obj = new Object[] { referencia };

		if (requesId > 0) {
			sql = sql + " AND rp.repp_requestid = ?";
			obj = new Object[] { referencia, requesId };
		}
		sql = sql + " ORDER BY rp.repp_codigo DESC";

		List<Peticion> listaPeticion = jdbcTemplate.query(sql, obj, new RowMapper<Peticion>() {

			public Peticion mapRow(ResultSet rs, int rowNum) throws SQLException {

				Peticion peticion = new Peticion();
				peticion.setCodigo(rs.getInt("repp_codigo"));
				peticion.setRequestId(rs.getInt("repp_requestid"));
				peticion.setAutorizacion(rs.getString("repp_autorizacion"));

				Factura factura = new Factura();
				factura.setReferencia(rs.getLong("fac_codigo"));
				peticion.setFactura(factura);

				Status status = new Status();
				status.setReason(rs.getString("pas_codigo"));
				peticion.setStatus(status);
				return peticion;
			}

		});
		if (listaPeticion.size() > 0) {
			return listaPeticion.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean actualizarEstado(int estado, int peticion, String autorizacion) {
		// TODO Auto-generated method stub
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		// update

		String sql = "UPDATE recaudo_pagosenlinea_peticion SET pas_codigo = ?, repp_autorizacion = ?";
		sql = sql + " WHERE repp_codigo=?";

		int resultado = jdbcTemplate.update(sql, estado, autorizacion, peticion);

		if (resultado > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Peticion> consultarPeticionesPendiente() {
		// TODO Auto-generated method stub
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String estado = webParametroDao.listarWebParametro(constantes.PAGOSENLINEA_ESTADO_PENDIENTE).getValor();

		String sql = "SELECT rp.repp_codigo, rp.repp_requestid, rp.fac_codigo, rp.pas_codigo FROM dbo.recaudo_pagosenlinea_peticion rp";
		sql = sql + " WHERE rp.pas_codigo in (2," + estado + ") ";
		sql = sql + " ORDER BY rp.repp_codigo DESC";

		List<Peticion> listaPeticion = jdbcTemplate.query(sql, new RowMapper<Peticion>() {

			public Peticion mapRow(ResultSet rs, int rowNum) throws SQLException {

				Peticion peticion = new Peticion();
				peticion.setCodigo(rs.getInt("repp_codigo"));
				peticion.setRequestId(rs.getInt("repp_requestid"));

				Factura factura = new Factura();
				factura.setReferencia(rs.getLong("fac_codigo"));
				peticion.setFactura(factura);

				Status status = new Status();
				status.setReason(rs.getString("pas_codigo"));
				peticion.setStatus(status);
				return peticion;
			}

		});
		if (listaPeticion.size() > 0) {
			return listaPeticion;
		} else {
			return null;
		}
	}

}
