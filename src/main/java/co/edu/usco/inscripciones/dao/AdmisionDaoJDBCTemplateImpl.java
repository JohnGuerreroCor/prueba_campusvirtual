package co.edu.usco.inscripciones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Admision;

@Component
public class AdmisionDaoJDBCTemplateImpl implements AdmisionDao {

	@Autowired
	DataSource dataSource;

	@Override
	public boolean agregarAdmision(final Admision admision) {
		try {

			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			String cadenaFecha = formato.format(fechaActual);

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			final String sql = "INSERT INTO admision_resultado_lcms (adt_codigo, inp_codigo, adr_metodo, "
					+ "adr_puesto_proceso, adr_estado, adr_fecha, cliente) "
					+ "VALUES (?, ?, ?, ?, ?, convert(datetime,'" + cadenaFecha + "'),?)";
			System.out.println(
					"Se agrego la admision:" + sql + " " + admision.getInscripcion().getInscripcionPrograma().getId());
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int resultado = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					pstm.setInt(1, 1);
					pstm.setInt(2, admision.getInscripcion().getInscripcionPrograma().getId());
					pstm.setInt(3, 1);
					pstm.setInt(4, 0);
					pstm.setInt(5, 0);
					pstm.setString(6, "modulo admisiones no formal");
					return pstm;
				}
			}, keyHolder);

			if (resultado > 0) {
				System.out.println("Se agrego admision");
				admision.setId(keyHolder.getKey().intValue());
				return true;
			} else {
				System.out.println("No se agrego admision");
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

}
