package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Pregunta;
import co.edu.usco.inscripciones.model.TipoRespuesta;

@Component
public class PreguntasDaoJDBCTemplateImpl implements PreguntasDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<Pregunta> getAllbyCuestionario(int cuestionario) {
		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "select pre_codigo, pre_descripcion, cue_codigo, pre_texto_adicional, tre_codigo " + 
					" from encuestas.preguntas where pre_estado = 1 AND cue_codigo = ?";

			List<Pregunta> listaPregunta = jdbcTemplate.query(sql, new Object[] { cuestionario },
					new RowMapper<Pregunta>() {

						public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {

							Pregunta pregunta = new Pregunta();
							pregunta.setCodigo(rs.getInt("pre_codigo"));
							pregunta.setDescripcion(rs.getString("pre_descripcion"));
							pregunta.setAdicional(rs.getString("pre_texto_adicional"));
							TipoRespuesta tipoRespuesta = new TipoRespuesta();
							tipoRespuesta.setCodigo(rs.getInt("tre_codigo"));
							pregunta.setTipoRespuesta(tipoRespuesta);

							return pregunta;
						}
					});
			return listaPregunta;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
