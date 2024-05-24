package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Cuestionario;
import co.edu.usco.inscripciones.model.Persona;
import co.edu.usco.inscripciones.model.RespuestaCuestionario;

@Component
public class CuestionarioDaoJDBCTemplateImpl implements CuestionarioDao {

	@Autowired
	DataSource dataSource;

	@Override
	public Cuestionario consultar(int uaa) {
		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT * FROM encuestas.cuestionarios WHERE uaa_codigo = ?";

			List<Cuestionario> listaCuestionario = jdbcTemplate.query(sql, new Object[] { uaa },
					new RowMapper<Cuestionario>() {

						public Cuestionario mapRow(ResultSet rs, int rowNum) throws SQLException {
							Cuestionario cuestionario = new Cuestionario();
							cuestionario.setCodigo(rs.getInt("cue_codigo"));
							cuestionario.setNombre(rs.getString("cue_nombre"));
							cuestionario.setInsctrucciones(rs.getString("cue_instrucciones"));
							return cuestionario;
						}
					});
			if (listaCuestionario.size() > 0) {
				return listaCuestionario.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Persona consultarInscripcion(String identificacion, int cuestionario) {
		// TODO Auto-generated method stub
		try {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "select p.per_codigo, p.per_identificacion, p.per_email, p.per_email_interno, p.per_nombre, p.per_apellido from matricula m ";
				sql = sql + "inner join estudiante e ON m.est_codigo = e.est_codigo ";
				sql = sql + "inner join programa pr ON e.pro_codigo = pr.pro_codigo "; 
				sql = sql + "inner join uaa u ON pr.uaa_codigo = u.uaa_codigo ";
				sql = sql + "inner join encuestas.cuestionarios c on c.uaa_codigo = u.uaa_codigo ";
				sql = sql + "inner join persona p ON e.per_codigo = p.per_codigo "; 
				sql = sql + "WHERE p.per_identificacion = ? AND c.cue_codigo = ?";
				
				List<Persona> listaPersona = jdbcTemplate.query(sql, new Object[] { identificacion, cuestionario },
						new RowMapper<Persona>() {

						public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
							Persona persona = new Persona();
							persona.setCodigo(rs.getInt("per_codigo"));
							persona.setIdentificacion(rs.getString("per_identificacion"));
							persona.setEmail(rs.getString("per_email"));
							persona.setEmailInterno(rs.getString("per_email_interno"));
							persona.setApellidos(rs.getString("per_apellido"));
							persona.setNombres(rs.getString("per_nombre"));
								return persona;
							}
						});
				if (listaPersona.size() > 0) {
					return listaPersona.get(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public RespuestaCuestionario consultarSha(String identificacion, String cuestionario) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			String sql = "select c.cue_codigo, p.per_codigo, p.per_identificacion, p.per_email, p.per_email_interno, p.per_nombre, p.per_apellido from matricula m ";
					sql = sql + "inner join estudiante e ON m.est_codigo = e.est_codigo ";
					sql = sql + "inner join programa pr ON e.pro_codigo = pr.pro_codigo "; 
					sql = sql + "inner join uaa u ON pr.uaa_codigo = u.uaa_codigo ";
					sql = sql + "inner join encuestas.cuestionarios c on c.uaa_codigo = u.uaa_codigo ";
					sql = sql + "inner join persona p ON e.per_codigo = p.per_codigo "; 
					sql = sql + "WHERE HASHBYTES('SHA1', p.per_identificacion) = "+identificacion+" AND CONVERT(VARCHAR(255),HASHBYTES('SHA1', CONVERT(VARCHAR(255), c.cue_codigo)))= "+cuestionario;
					System.out.println(sql);
					List<RespuestaCuestionario> listaRespuestaCuestionario = jdbcTemplate.query(sql, new RowMapper<RespuestaCuestionario>() {

							public RespuestaCuestionario mapRow(ResultSet rs, int rowNum) throws SQLException {
								RespuestaCuestionario respuestaCuestionario = new RespuestaCuestionario();
								respuestaCuestionario.setCodigo(rs.getInt("cue_codigo"));
								Persona persona = new Persona();
								persona.setIdentificacion(rs.getString("per_identificacion"));
								respuestaCuestionario.setPersona(persona);
									return respuestaCuestionario;
								}
							});
					if (listaRespuestaCuestionario.size() > 0) {
						return listaRespuestaCuestionario.get(0);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
	}

}
