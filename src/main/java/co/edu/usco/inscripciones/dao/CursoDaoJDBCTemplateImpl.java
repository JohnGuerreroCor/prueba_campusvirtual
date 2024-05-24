package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Curso;

@Component
public class CursoDaoJDBCTemplateImpl implements CursoDao {

	@Autowired
	DataSource dataSource;

	@Override
	public Curso buscarCurso(int asi_codigo, int pla_codigo, int cal_codigo) {
		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT cur_codigo " + "FROM curso " + "WHERE asi_codigo=" + asi_codigo + " AND pla_codigo = "
					+ pla_codigo + " " + "AND cal_codigo = " + cal_codigo;
			
			
			System.out.println("buscar curso:" + sql);
			List<Curso> listaCurso = jdbcTemplate.query(sql, new RowMapper<Curso>() {

				public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
					Curso curso = new Curso();
					curso.setCodigo(rs.getInt("cur_codigo"));
					return curso;
				}

			});

			if (listaCurso.size() > 0) {
				return listaCurso.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
