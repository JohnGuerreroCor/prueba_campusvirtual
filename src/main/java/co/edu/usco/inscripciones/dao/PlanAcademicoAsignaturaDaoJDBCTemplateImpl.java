package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Asignatura;
import co.edu.usco.inscripciones.model.PlanAcademicoAsignatura;

@Component
public class PlanAcademicoAsignaturaDaoJDBCTemplateImpl implements PlanAcademicoAsignaturaDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<PlanAcademicoAsignatura> buscarPlanAcademicoAsignatura(int pla_codigo) {

		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT paa_codigo,asi_codigo FROM plan_academico_asignatura WHERE pla_codigo = "
					+ pla_codigo;// + " AND paa_semestre = 1";
			
			System.out.println("Plan academico asignatura:" + sql);
			
			List<PlanAcademicoAsignatura> listaPlanAcademicoAsignatura = jdbcTemplate.query(sql,
					new RowMapper<PlanAcademicoAsignatura>() {

						public PlanAcademicoAsignatura mapRow(ResultSet rs, int rowNum) throws SQLException {
							PlanAcademicoAsignatura paa = new PlanAcademicoAsignatura();
							paa.setCodigo(rs.getInt("paa_codigo"));
							Asignatura asignatura = new Asignatura();
							asignatura.setCodigo(rs.getInt("asi_codigo"));
							paa.setAsignatura(asignatura);
							return paa;
						}
					});

			return listaPlanAcademicoAsignatura;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
