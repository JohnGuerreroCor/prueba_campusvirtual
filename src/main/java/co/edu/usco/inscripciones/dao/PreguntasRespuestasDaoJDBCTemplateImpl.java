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
import co.edu.usco.inscripciones.model.PreguntaRespuestas;
import co.edu.usco.inscripciones.model.RespuestaOpciones;
import co.edu.usco.inscripciones.model.TipoRespuesta;

@Component

public class PreguntasRespuestasDaoJDBCTemplateImpl implements PreguntasRespuestasDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<PreguntaRespuestas> getAllRespuestas(int cuestionario) {
		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "select o.rop_codigo, o.rop_descripcion, pr.prr_codigo, p.pre_codigo,";
			sql = sql + " p.pre_descripcion, p.cue_codigo, p.pre_texto_adicional, t.tre_codigo,";
			sql = sql + " t.tre_nombre from encuestas.respuestas_opciones o";
			sql = sql + " inner join encuestas.preguntas_respuestas pr on o.rop_codigo = pr.rop_codigo";
			sql = sql + " inner join encuestas.preguntas p on pr.pre_codigo = p.pre_codigo";
			sql = sql + " inner join encuestas.tipo_respuestas t on t.tre_codigo = p.tre_codigo";
			sql = sql + " where p.pre_estado = 1 AND o.rop_estado = 1 AND p.cue_codigo = ?";

			List<PreguntaRespuestas> listaPreguntaRespuestas = jdbcTemplate.query(sql, new Object[] { cuestionario },
					new RowMapper<PreguntaRespuestas>() {

						public PreguntaRespuestas mapRow(ResultSet rs, int rowNum) throws SQLException {
							PreguntaRespuestas preguntaRespuestas = new PreguntaRespuestas();
							preguntaRespuestas.setCodigo(rs.getInt("prr_codigo"));

							Pregunta pregunta = new Pregunta();
							pregunta.setCodigo(rs.getInt("pre_codigo"));
							pregunta.setDescripcion(rs.getString("pre_descripcion"));
							pregunta.setAdicional(rs.getString("pre_texto_adicional"));
							preguntaRespuestas.setPregunta(pregunta);

							RespuestaOpciones respuestaOpciones = new RespuestaOpciones();
							respuestaOpciones.setCodigo(rs.getInt("rop_codigo"));
							respuestaOpciones.setDescripcion(rs.getString("rop_descripcion"));
							preguntaRespuestas.setRespuestaOpciones(respuestaOpciones);

							return preguntaRespuestas;
						}
					});
			return listaPreguntaRespuestas;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
