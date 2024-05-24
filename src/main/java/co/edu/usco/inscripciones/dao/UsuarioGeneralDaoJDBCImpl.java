/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Persona;
import co.edu.usco.inscripciones.model.Uaa;
import co.edu.usco.inscripciones.model.UsuarioGeneral;

/**
 * @author jankarlos
 *
 */
@Component
public class UsuarioGeneralDaoJDBCImpl implements UsuarioGeneralDao {

	@Autowired
	DataSource dataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.usco.inscripciones.dao.UsuarioGeneralDao#
	 * consultarDatosUsuarioGeneral(int)
	 */
	@Override
	public UsuarioGeneral consultarDatosUsuarioGeneral(String usuario) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT  TOP 1 ug.uid, ug.us, ug.istipo, p.per_identificacion, p.per_codigo, p.per_nombre, p.per_apellido, ";
			sql = sql
					+ " p.per_email, p.per_email_interno FROM usuario_general ug INNER JOIN persona p ON ug.up = p.per_codigo";
			sql = sql + " WHERE  ug.us = ? AND state = 1";

			List<UsuarioGeneral> listaIUD = jdbcTemplate.query(sql, new Object[] { usuario },
					new RowMapper<UsuarioGeneral>() {

						public UsuarioGeneral mapRow(ResultSet rs, int rowNum) throws SQLException {
							UsuarioGeneral usugral = new UsuarioGeneral();
							usugral.setCodigo(rs.getInt("uid"));
							usugral.setUsuario(rs.getString("us"));
							usugral.setTipo(rs.getInt("istipo"));

							Persona persona = new Persona();
							persona.setCodigo(rs.getInt("per_codigo"));
							persona.setIdentificacion(rs.getString("per_identificacion"));
							persona.setId(rs.getInt("per_codigo"));
							persona.setNombres(rs.getString("per_nombre"));
							persona.setApellidos(rs.getString("per_apellido"));
							persona.setEmail(rs.getString("per_email"));
							persona.setEmailInterno(rs.getString("per_email_interno"));
							persona.setTercero(false);
							usugral.setPersona(persona);
							return usugral;
						}
					});
			if (listaIUD.size() > 0) {
				return listaIUD.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Uaa consultarUaaUsuarioGeneral(int persona, String estudiante, int tipo) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "";
			if (tipo == 1 || tipo == 3) {
				sql = "select top 1 u.uaa_dependencia, u.uaa_codigo, u.uaa_nombre from uaa_personal up "
						+ " inner join uaa u on up.uaa_codigo = u.uaa_codigo " + " where up.per_codigo = " + persona
						+ " and ((CAST(GETDATE() AS DATE )  " + " between  CAST(up.uap_fecha_inicio AS DATE ) and "
						+ " CAST(up.uap_fecha_fin AS DATE )) or up.uap_fecha_fin is null) order by up.uap_codigo desc";
				;
			}
			if (tipo == 2) {
				sql = "select top 1 u.uaa_dependencia, u.uaa_codigo, u.uaa_nombre from estudiante e "
						+ "inner join programa p on e.pro_codigo = p.pro_codigo "
						+ "inner join uaa u on p.uaa_codigo = u.uaa_codigo " + "where e.est_codigo = '" + estudiante
						+ "'";
			}

			List<Uaa> listaUaa = jdbcTemplate.query(sql, new RowMapper<Uaa>() {

				public Uaa mapRow(ResultSet rs, int rowNum) throws SQLException {
					Uaa uaa = new Uaa();
					uaa.setCodigo(rs.getInt("uaa_codigo"));
					uaa.setNombre(rs.getString("uaa_nombre"));
					uaa.setDependencia(rs.getInt("uaa_dependencia"));
					return uaa;
				}
			});
			if (listaUaa.size() > 0) {
				return listaUaa.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UsuarioGeneral validaUsuarioClaveGral(String usuario, String pw) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT  TOP 1 ug.uid, ug.up, ug.us, ug.istipo FROM usuario_general_login ug "
					+ " WHERE ug.us = ? and uwd2 = HASHBYTES('MD5', HASHBYTES('SHA1', '" + pw + "')) AND state = 1";
			System.out.println(sql + usuario + " - " + pw);
			List<UsuarioGeneral> listaIUD = jdbcTemplate.query(sql, new Object[] { usuario },
					new RowMapper<UsuarioGeneral>() {

						public UsuarioGeneral mapRow(ResultSet rs, int rowNum) throws SQLException {
							UsuarioGeneral usugral = new UsuarioGeneral();
							usugral.setCodigo(rs.getInt("uid"));
							usugral.setUsuario(rs.getString("us"));
							usugral.setTipo(rs.getInt("istipo"));

							Persona persona = new Persona();
							persona.setCodigo(rs.getInt("up"));
							persona.setTercero(false);
							usugral.setPersona(persona);
							return usugral;
						}
					});
			if (listaIUD.size() > 0) {
				return listaIUD.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
