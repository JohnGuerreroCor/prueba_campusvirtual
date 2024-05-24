package co.edu.usco.inscripciones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Matricula;

@Component
public class MatriculaDaoJDBCTemplateImpl implements MatriculaDao {

	@Autowired
	DataSource dataSource;

	@Autowired
	WebParametroDao webParametroDao;

	Date fechaActual = new Date();
	SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	String cadenaFecha = formato.format(fechaActual);

	@Override
	public boolean agregarMatricula(final Matricula matricula) {
		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String querySql = "INSERT INTO matricula (est_codigo, cal_codigo, mat_fecha, "
					+ "mat_estado, mat_tipo, sippa_calcodigo";
			
			if (matricula.getUsuario() > 0) {
				querySql = querySql + ", mat_usuario) VALUES (?, ?, convert(datetime,'" + cadenaFecha
						+ "'), ?, ?, ?, ?)";
			} else {
				querySql = querySql + ")VALUES (?, ?, convert(datetime,'" + cadenaFecha + "'), ?, ?, ?)";
			}
			final String sql = querySql;
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int resultado = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					pstm.setString(1, matricula.getInscripcion().getEstudiante().getCodigo());
					pstm.setInt(2, matricula.getOferta().getCalendario().getCodigo());
					pstm.setString(3, "A");
					pstm.setInt(4, 1);
					pstm.setString(5, matricula.getOferta().getCalendario().getNombre());
					if (matricula.getUsuario() > 0) {
						pstm.setInt(6, matricula.getUsuario());
					}
					return pstm;
				}
			}, keyHolder);

			if (resultado > 0) {
				System.out.println("Se agrego la matricula");
				matricula.setId(keyHolder.getKey().intValue());
				return true;
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean agregarMatriculaCursoActual(int matricula, int curso, int paa) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "EXEC CrearMatriculaCursoActualNoFormal ?, ?, ?, ?";
			System.out.println(sql);
			int resultado = jdbcTemplate.update(sql, matricula, curso, paa, cadenaFecha);
			if (resultado > 0) {
				System.out.println("Se agrego la matricula:" + matricula + " curso: " + curso);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.toString());
		}
		return false;
	}

	@Override
	public Matricula validarMatriculaActiva(String usuarioEstudiante) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String calendarioActual = webParametroDao.listarWebParametro("MATRICULA_PERIODO_ACTUAL").getValor();

			String sql = "select est_codigo, cal_codigo from matricula where est_codigo = '"
					+ usuarioEstudiante.replace("u", "") + "' "
					+ " and cal_codigo in (select cal_codigo from calendario where cal_nombre like '" + calendarioActual
					+ "%')";
			System.out.println(sql + usuarioEstudiante.replace("u", ""));
			List<Matricula> listaMatricula = jdbcTemplate.query(sql, new RowMapper<Matricula>() {

				public Matricula mapRow(ResultSet rs, int rowNum) throws SQLException {
					Matricula matricula = new Matricula();

					return matricula;
				}
			});
			if (listaMatricula.size() > 0) {
				return listaMatricula.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
