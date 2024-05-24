package co.edu.usco.inscripciones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.RespuestaCuestionario;

@Component
public class RespuestasCuestionariosDaoJDBCTemplateImpl implements RespuestasCuestionariosDao {

	@Autowired
	DataSource dataSource;
	
	@Override
	public int  agregar(final RespuestaCuestionario respuestaCuestionario) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			final String sql = "INSERT INTO encuestas.respuestas_cuestionarios (cue_codigo, per_codigo) VALUES (?, ?)";
			//int resultado = jdbcTemplate.update(sql,respuestaCuestionario.getCuestionario().getCodigo(), respuestaCuestionario.getPersona().getCodigo());
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int resultado = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					pstm.setInt(1, respuestaCuestionario.getCuestionario().getCodigo());
					pstm.setInt(2, respuestaCuestionario.getPersona().getCodigo());
					return pstm;
				}
			}, keyHolder);

			if (resultado > 0) {
				return keyHolder.getKey().intValue();
			}else {
				return 0;
			}
			
		} catch (Exception e) {
			return 0;
		}
	}

}
