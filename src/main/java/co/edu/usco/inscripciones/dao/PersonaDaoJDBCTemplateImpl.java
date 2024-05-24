package co.edu.usco.inscripciones.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Persona;
import co.edu.usco.inscripciones.model.PreInscripcion;
import co.edu.usco.librerias.DividirNombresApellidos;

@Component
public class PersonaDaoJDBCTemplateImpl implements PersonaDao {

	@Autowired
	DataSource dataSource;

	@Override
	public boolean guardarPersona(final Persona persona) {

		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			final String sql = "INSERT INTO persona (per_identificacion,tii_codigo,per_fecha_expedicion,per_lugar_expedicion,"
					+ "per_nombre,per_apellido,per_genero,per_estado_civil,grs_codigo, per_email, per_fecha_nacimiento, "
					+ "per_pais_residencia, per_departamento_residencia,per_lugar_nacimiento, per_direccion_residencia, "
					+ "per_barrio, per_telefono_movil, per_estrato, eps_codigo, eta_codigo, per_id_cotizante, lenguaNativa)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// System.out.println(sql);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int resultado = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					pstm.setString(1, persona.getIdentificacion());
					pstm.setInt(2, persona.getTipoIdentificacion().getCodigo());
					pstm.setDate(3, (Date) persona.getFechaExpedicion());
					pstm.setInt(4, persona.getLugarExpedicion().getCodigo());

					pstm.setString(5, persona.getNombres());
					pstm.setString(6, persona.getApellidos());
					pstm.setString(7, persona.getGenero());
					pstm.setInt(8, persona.getEstadoCivil().getCodigo());
					pstm.setInt(9, persona.getGrupoSanguineo().getCodigo());
					pstm.setString(10, persona.getEmail());
					pstm.setDate(11, (Date) persona.getFechaNacimiento());

					pstm.setInt(12, persona.getLugarResidenciaPais().getCodigo());
					pstm.setInt(13, persona.getLugarResidenciaDepartamento().getCodigo());
					pstm.setInt(14, persona.getLugarResidencia().getCodigo());
					pstm.setString(15, persona.getDireccion());

					pstm.setString(16, persona.getBarrio());
					pstm.setString(17, persona.getTelefonoMovil());
					pstm.setInt(18, persona.getEstrato().getCodigo());
					pstm.setInt(19, persona.getEps().getCodigo());
					pstm.setInt(20, persona.getTipoAfiliacion().getCodigo());

					pstm.setLong(21, persona.getIdentificacionCotizante());
					pstm.setLong(21, persona.getLenguaNativa().getCodigo());

					return pstm;
				}
			}, keyHolder);

			if (resultado > 0) {
				persona.setId(keyHolder.getKey().intValue());
				persona.setTercero(false);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean actualizarTercero(final Persona persona) {

		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			String fe = formato.format(persona.getFechaExpedicion());
			String fn = formato.format(persona.getFechaNacimiento());

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			// update
			String sql = "UPDATE tercero ";
			sql = sql + " SET tii_codigo = ?,ter_fecha_expedicion = '" + fe + "', ter_lugar_expedicion = ?, ";
			sql = sql + " ter_genero = ?, ter_estado_civil = ?,grs_codigo = ?, ter_pais_residencia = ?, ";
			sql = sql + " ter_departamento_residencia = ?, ter_lugar_residencia = ?, ";
			sql = sql + " ter_fecha_nacimiento = '" + fn
					+ "', ter_lugar_nacimiento = ?, ter_direccion = ?, ter_barrio = ?, ";
			sql = sql
					+ " ter_telefono_movil = ?, ter_estrato = ?, eps_codigo = ?, eta_codigo = ?, ter_id_cotizante = ?, lenguaNativa = ? ";
			sql = sql + " WHERE ter_identificacion = '" + persona.getIdentificacion() + "'";

			int resultado = jdbcTemplate.update(sql, persona.getTipoIdentificacion().getCodigo(),
					persona.getLugarExpedicion().getCodigo(), persona.getGenero(), persona.getEstadoCivil().getCodigo(),
					persona.getGrupoSanguineo().getCodigo(), persona.getLugarResidenciaPais().getCodigo(),
					persona.getLugarResidenciaDepartamento().getCodigo(), persona.getLugarResidencia().getCodigo(),
					persona.getLugarNacimiento().getCodigo(), persona.getDireccion(), persona.getBarrio(),
					persona.getTelefonoMovil(), persona.getEstrato().getCodigo(), persona.getEps().getCodigo(),
					persona.getTipoAfiliacion().getCodigo(), persona.getIdentificacionCotizante(),
					persona.getLenguaNativa().getCodigo());
			if (resultado > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean ActualizarPersonaPA(final Persona persona) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "EXEC ActualizarPersonaEducacionNoFormal ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
			int resultado = jdbcTemplate.update(sql, persona.getId(), persona.getGenero(),
					persona.getLugarExpedicion().getCodigo(), persona.getFechaExpedicion(),
					persona.getFechaNacimiento(), persona.getTelefonoMovil(), persona.getEstadoCivil().getCodigo(),
					persona.getLugarNacimiento().getCodigo(), persona.getEps().getCodigo(),
					persona.getTipoAfiliacion().getCodigo(), persona.getIdentificacionCotizante(),
					persona.getGrupoSanguineo().getCodigo(), persona.getLenguaNativa().getCodigo(),
					persona.getDireccion(), persona.getEstrato().getCodigo(), persona.getBarrio());

			System.out.println(persona.getId() + " - " + persona.getGenero() + " - "
					+ persona.getLugarExpedicion().getCodigo() + " - " + persona.getFechaExpedicion() + " - "
					+ persona.getFechaNacimiento() + " - " + persona.getTelefonoMovil() + " - "
					+ persona.getEstadoCivil().getCodigo() + " - " + persona.getLugarNacimiento().getCodigo() + " - "
					+ persona.getEps().getCodigo() + " - " + persona.getTipoAfiliacion().getCodigo() + " - "
					+ persona.getIdentificacionCotizante() + " - " + persona.getGrupoSanguineo().getCodigo() + " - "
					+ persona.getLenguaNativa().getCodigo() + " - " + persona.getDireccion() + " - "
					+ persona.getEstrato().getCodigo() + " - " + persona.getBarrio());
			if (resultado > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.toString());
		}
		return false;
	}

	@Override
	public boolean actualizarPersona(final Persona persona) {

		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			String fe = formato.format(persona.getFechaExpedicion());
			String fn = formato.format(persona.getFechaNacimiento());

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "UPDATE persona " + "SET tii_codigo = ?,per_fecha_expedicion = ?, per_lugar_expedicion = ?, "
					+ "per_genero = ?, per_estado_civil = ?,grs_codigo = ?, per_pais_residencia = ?, "
					+ "per_departamento_residencia = ?, per_lugar_residencia = ?, per_fecha_nacimiento = ?, "
					+ "per_lugar_nacimiento = ?, per_direccion_residencia = ?, per_barrio = ?, "
					+ "per_telefono_movil = ?, per_estrato = ?, eps_codigo = ?, eta_codigo = ?, per_id_cotizante = ?, "
					+ "lenguaNativa = ? WHERE per_identificacion = '" + persona.getIdentificacion() + "'";
			int resultado = jdbcTemplate.update(sql, persona.getTipoIdentificacion().getCodigo(),
					persona.getFechaExpedicion(), persona.getLugarExpedicion().getCodigo(), persona.getGenero(),
					persona.getEstadoCivil().getCodigo(), persona.getGrupoSanguineo().getCodigo(),
					persona.getLugarResidenciaPais().getCodigo(), persona.getLugarResidenciaDepartamento().getCodigo(),
					persona.getLugarResidencia().getCodigo(), persona.getFechaNacimiento(),
					persona.getLugarNacimiento().getCodigo(), persona.getDireccion(), persona.getBarrio(),
					persona.getTelefonoMovil(), persona.getEstrato().getCodigo(), persona.getEps().getCodigo(),
					persona.getTipoAfiliacion().getCodigo(), persona.getIdentificacionCotizante(),
					persona.getLenguaNativa().getCodigo());
			if (resultado > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean guardarTerceroPreInscripcion(final Persona persona) {

		try {

			final String[] nombres;
			final String[] apellidos;

			nombres = DividirNombresApellidos.dividir(persona.getNombres().toUpperCase());
			apellidos = DividirNombresApellidos.dividir(persona.getApellidos().toUpperCase());

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			final String sql = "INSERT INTO tercero (ter_identificacion,ter_nombre, ter_email, "
					+ "ter_apellido1, ter_apellido2, ter_nombre1, ter_nombre2, tii_codigo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int resultado = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					pstm.setString(1, persona.getIdentificacion());
					pstm.setString(2, persona.getNombres().toUpperCase() + ' ' + persona.getApellidos().toUpperCase());
					pstm.setString(3, persona.getEmail());
					pstm.setString(4, apellidos[0]);
					pstm.setString(5, apellidos[1]);
					pstm.setString(6, nombres[0]);
					pstm.setString(7, nombres[1]);
					pstm.setInt(8, 5);

					return pstm;
				}
			}, keyHolder);

			if (resultado > 0) {
				persona.setId(keyHolder.getKey().intValue());
				persona.setTercero(true);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Persona consultarPersona(String identificacion) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT TOP 1 p.per_codigo, p.per_identificacion, p.per_nombre, p.per_apellido, p.per_email, rt.tem_email as emailPre "
					+ "FROM persona p "
					+ "INNER JOIN inscripciones.registroTemporal rt ON p.per_codigo = rt.tem_per_codigo "
					+ "WHERE p.per_identificacion = '" + identificacion + "' " + "ORDER BY rt.tem_codigo DESC";
			List<Persona> listaPersona = jdbcTemplate.query(sql, new RowMapper<Persona>() {

				public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
					Persona persona = new Persona();
					persona.setId(rs.getInt("per_codigo"));
					persona.setIdentificacion(rs.getString("per_identificacion"));
					persona.setNombres(rs.getString("per_nombre"));
					persona.setApellidos(rs.getString("per_apellido"));
					persona.setEmail(rs.getString("per_email"));
					persona.setEmailPreInscripcion(rs.getString("emailPre"));
					persona.setTercero(false);
					return persona;
				}
			});
			if (listaPersona.size() > 0) {
				return listaPersona.get(0);
			} else {
				return consultarTercero(identificacion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Persona consultarTercero(String identificacion) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT TOP 1 t.ter_codigo, t.ter_identificacion, t.ter_nombre1 + ' ' + t.ter_nombre2 AS per_nombre, "
					+ "t.ter_apellido1 + ' ' + t.ter_apellido2 AS per_apellido, t.ter_email, rt.tem_email as emailPre "
					+ "FROM tercero t "
					+ "INNER JOIN inscripciones.registroTemporal rt ON t.ter_codigo = rt.tem_ter_codigo "
					+ "WHERE t.ter_identificacion = '" + identificacion + "' " + "ORDER BY rt.tem_codigo DESC ";
			List<Persona> listaPersona = jdbcTemplate.query(sql, new RowMapper<Persona>() {
				public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
					Persona persona = new Persona();
					persona.setId(rs.getInt("ter_codigo"));
					persona.setIdentificacion(rs.getString("ter_identificacion"));
					persona.setNombres(rs.getString("per_nombre"));
					persona.setApellidos(rs.getString("per_apellido"));
					persona.setEmail(rs.getString("ter_email"));
					persona.setEmailPreInscripcion(rs.getString("emailPre"));
					persona.setTercero(true);
					return persona;
				}
			});
			if (listaPersona.size() > 0) {
				// return listaPersona.get(0);
				return convertirTerceroEnPersona(identificacion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Persona convertirTerceroEnPersona(String cedula) {
		System.out.println("CEDULA;" + cedula);

		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sqlTercero = "UPDATE tercero set";
			sqlTercero += " ter_email = (SELECT TOP 1 tem_email FROM inscripciones.registroTemporal WHERE tem_identificacion = '"
					+ cedula + "' ORDER BY tem_codigo desc),";
			sqlTercero += " ter_nombre = (SELECT TOP 1 (tem_nombres+' '+tem_apellidos) as nombre FROM inscripciones.registroTemporal WHERE tem_identificacion = '"
					+ cedula + "' ORDER BY tem_codigo desc)";
			sqlTercero += " WHERE ter_identificacion = '" + cedula + "'";

			int resultadoUpdateTercero = jdbcTemplate.update(sqlTercero);

			String sql = "EXEC CrearPersonaContratacion '" + cedula + "'";
			int resultado = jdbcTemplate.update(sql);
			System.out.println("Resultado es:" + resultado);

			if (resultado > 0) {

				String sqlUpdate = "UPDATE inscripciones.registroTemporal SET "
						+ " tem_per_codigo = (SELECT TOP 1 per_codigo FROM persona WHERE per_identificacion = '"
						+ cedula + "')" + " WHERE tem_identificacion = '" + cedula + "'";
				int resultadoUpdate = jdbcTemplate.update(sqlUpdate);

				return consultarPersona(cedula);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.toString());
		}
		return null;
	}

	@Override
	public Persona consultarPersonaUniversidad(String identificacion) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT TOP 1 per_codigo, per_nombre, per_apellido, per_email " + "FROM persona "
					+ "WHERE per_identificacion = '" + identificacion + "'";

			List<Persona> listaPersona = jdbcTemplate.query(sql, new RowMapper<Persona>() {

				public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
					Persona persona = new Persona();
					persona.setId(rs.getInt("per_codigo"));
					persona.setNombres(rs.getString("per_nombre"));
					persona.setApellidos(rs.getString("per_apellido"));
					persona.setEmail(rs.getString("per_email"));
					persona.setEmailPreInscripcion(rs.getString("per_email"));
					persona.setTercero(false);
					return persona;
				}
			});
			if (listaPersona.size() > 0) {
				return listaPersona.get(0);
			} else {
				return consultarTerceroUniversidad(identificacion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Persona consultarTerceroUniversidad(String identificacion) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT TOP 1 ter_codigo, ter_nombre1 + ' ' + ter_nombre2 AS per_nombre, "
					+ "ter_apellido1 + ' ' + ter_apellido2 AS per_apellido, ter_email " + "FROM tercero "
					+ "WHERE ter_identificacion = '" + identificacion + "' ";
			List<Persona> listaPersona = jdbcTemplate.query(sql, new RowMapper<Persona>() {
				public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
					Persona persona = new Persona();
					persona.setId(rs.getInt("ter_codigo"));
					persona.setNombres(rs.getString("per_nombre"));
					persona.setApellidos(rs.getString("per_apellido"));
					persona.setEmail(rs.getString("ter_email"));
					persona.setEmailPreInscripcion(rs.getString("ter_email"));
					persona.setTercero(true);
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
	public Persona validarVinculacionPersona(String identificacion) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "EXEC vinculacion_actual " + identificacion + ", ''";

			List<Persona> listaPersona = jdbcTemplate.query(sql, new RowMapper<Persona>() {

				public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
					Persona persona = new Persona();
					persona.setId(rs.getInt("per_codigo"));
					persona.setIdentificacion(rs.getString("per_identificacion"));
					persona.setNombres(rs.getString("per_nombre"));
					persona.setTercero(false);
					return persona;
				}
			});
			if (listaPersona.size() > 0) {
				return listaPersona.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
