/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Respuesta;

/**
 * @author JANKARLOS-USCO
 *
 */
@Component
public class RespuestasDaoJDBCTemplateImpl implements RespuestasDao {

	@Autowired
	DataSource dataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.usco.inscripciones.dao.RespuestasDao#agregar(co.edu.usco.inscripciones
	 * .model.Respuesta)
	 */
	@Override
	public boolean agregar(Respuesta respuesta) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "INSERT INTO encuestas.respuestas (res_adicional, rcu_codigo, prr_codigo, res_texto) VALUES (?, ?, ?, ?)";
			int resultado = jdbcTemplate.update(sql, respuesta.getAdicional(),
					respuesta.getRspuestaCuestionario().getCodigo(), respuesta.getPreguntaRespuestas().getCodigo(), respuesta.getTexto());

			if (resultado > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
