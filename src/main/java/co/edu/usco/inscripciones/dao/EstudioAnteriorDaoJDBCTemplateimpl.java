/**
 * 
 */
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

import co.edu.usco.inscripciones.model.EstudioAnterior;

/**
 * @author jankarlos
 *
 */
@Component
public class EstudioAnteriorDaoJDBCTemplateimpl implements EstudioAnteriorDao {

	@Autowired
	DataSource dataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.usco.inscripciones.dao.EstudioAnteriorDao#agregarEstudioAnterior(
	 * co.edu.usco.inscripciones.model.EstudioAnterior)
	 */
	@Override
	public boolean agregarEstudioAnterior(final EstudioAnterior estudioAnterior) {
		// TODO Auto-generated method stub
		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			final String sql = "INSERT INTO inscripciones.historial_academico (ter_codigo, per_codigo, nia_codigo, "
					+ "hia_titulo, hia_institucion, hia_anio) " + "VALUES (?, ?, ?, ?, ?, ?)";

			KeyHolder keyHolder = new GeneratedKeyHolder();
			int resultado = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					pstm.setInt(1, estudioAnterior.getTercero());
					pstm.setInt(2, estudioAnterior.getPersona());
					pstm.setInt(3, estudioAnterior.getNivelAcademico().getCodigo());
					pstm.setString(4, estudioAnterior.getTitulo());
					pstm.setString(5, estudioAnterior.getInstitucion());
					pstm.setString(6, estudioAnterior.getAnio());
					return pstm;
				}
			}, keyHolder);

			if (resultado > 0) {
				System.out.println("Se agrego estudio anterior");
				return true;
			} else {
				System.out.println("No se agrego estudio anterior");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
