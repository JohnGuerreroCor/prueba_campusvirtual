package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.PlanAcademico;


@Component
public class PlanAcademicoDaoJDBCTemplateImpl implements PlanAcademicoDao {

	@Autowired
	DataSource dataSource;
	@Override
	public PlanAcademico consultarPlanAcademico(int codigoPrograma) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			String sql = "SELECT TOP 1 pla_codigo, pla_nombre "
					+ " FROM plan_academico "
					+ " WHERE pro_codigo = " + codigoPrograma + " AND pla_total_horas != 0 "
					+ " ORDER BY pla_codigo DESC";
			List<PlanAcademico> listaInscripcion = jdbcTemplate.query(sql, new RowMapper<PlanAcademico>() {

				public PlanAcademico mapRow(ResultSet rs, int rowNum) throws SQLException {
					PlanAcademico planAcademico = new PlanAcademico();
					planAcademico.setCodigo(rs.getInt("pla_codigo"));
					planAcademico.setNombre(rs.getString("pla_nombre"));
					return planAcademico;
				}
			});
			if (listaInscripcion.size() > 0) {
				return listaInscripcion.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
