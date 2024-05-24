package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Estudiante;

@Component
public class EstudianteDaoJDBCTemplateImpl implements EstudianteDao {

	@Autowired
	DataSource dataSource;

	@Override
	public boolean guardarEstudiante(final Estudiante estudiante) {

		try {
			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			String cadenaFecha = formato.format(fechaActual);

			SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
			String anio = formatoAnio.format(fechaActual);

			String codigoEstudiante = anio + "N" + estudiante.getInscripcion().getId();

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			int codPersona = 0;
			int codTercero = 0;

			if (estudiante.getInscripcion().getPersona().isTercero() == false) {
				codPersona = estudiante.getInscripcion().getPersona().getId();
			} else {
				codTercero = estudiante.getInscripcion().getPersona().getId();
			}

			String sql = "INSERT INTO estudiante (est_codigo, per_codigo, ter_codigo, est_fecha_ingreso, "
					+ "pro_codigo, ese_codigo, ins_codigo, est_registro_egresado)"
					+ " VALUES (?, ?, ?, getDate(), ?, ?, ?, ?)";
			int resultado = jdbcTemplate.update(sql, codigoEstudiante, codPersona, codTercero,
					estudiante.getInscripcion().getOferta().getPrograma().getCodigo(), 1,
					estudiante.getInscripcion().getId(), 0);

			if (resultado > 0) {
				System.out.println("Se agrego estudiante codigo:" + codigoEstudiante);
				estudiante.setCodigo(codigoEstudiante);
				return true;
			} else {
				System.out.println("No se creo estudiante");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Estudiante consultarEstudiante(long perCodigo) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT est_codigo " + "FROM estudiante " + "WHERE per_codigo = " + perCodigo;

			List<Estudiante> listaEstudiante = jdbcTemplate.query(sql, new RowMapper<Estudiante>() {

				public Estudiante mapRow(ResultSet rs, int rowNum) throws SQLException {
					Estudiante estudiante = new Estudiante();
					estudiante.setCodigo(rs.getString("est_codigo"));
					return estudiante;
				}
			});
			if (listaEstudiante.size() > 0) {
				return listaEstudiante.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
