package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Contrato;
import co.edu.usco.inscripciones.model.Persona;

@Component
public class ContratoDaoJDBCTemplateImpl implements ContratoDao {

	@Autowired
	DataSource dataSource;

	@Override
	public Contrato validarContrato(String identificacion) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT p.per_nombre, t.ter_nombre, c.fechaFin, c.fechaInicio, t.ter_codigo, p.per_codigo,"
					+ " COALESCE(p.per_identificacion, t.ter_identificacion) as identificacion,"
					+ " COALESCE(p.per_nombre, t.ter_nombre) as nombre, p.per_apellido, p.per_email, t.ter_email"
					+ " FROM contrato.contrato c "
					+ " LEFT OUTER JOIN dbo.tercero AS t ON t.ter_codigo = c.ter_codigo "
					+ " LEFT OUTER JOIN dbo.persona AS p ON (p.per_identificacion = ?)"
					+ " WHERE CAST(GETDATE() AS DATE) > CAST(c.fechaInicio AS DATE)"
					+ " AND YEAR(c.fechaInicio) = YEAR(getDate())" + " AND c.codTContrato != 9"
					+ " AND (p.per_identificacion = ? OR t.ter_identificacion = ? ) ORDER BY fechaFin DESC";

			System.out.println(sql + " id:" + identificacion);

			List<Contrato> listaContrato = jdbcTemplate.query(sql, new Object[] { identificacion, identificacion, identificacion },
					new RowMapper<Contrato>() {

						public Contrato mapRow(ResultSet rs, int rowNum) throws SQLException {
							Contrato contrato = new Contrato();
							contrato.setFechaInicio(rs.getDate("fechaInicio"));
							contrato.setFechaFin(rs.getDate("fechaFin"));

							Persona persona = new Persona();
							if (rs.getInt("per_codigo") > 0) {
								persona.setCodigo(rs.getInt("per_codigo"));
								persona.setId(rs.getInt("per_codigo"));
								persona.setEmail(rs.getString("per_email"));
								persona.setEmailPreInscripcion(rs.getString("per_email"));
								persona.setTercero(false);
							} else {
								if (rs.getInt("ter_codigo") > 0) {
									persona.setCodigo(rs.getInt("ter_codigo"));
									persona.setId(rs.getInt("ter_codigo"));
									persona.setEmail(rs.getString("ter_email"));
									persona.setEmailPreInscripcion(rs.getString("ter_email"));
									persona.setTercero(true);
								}
							}
							persona.setNombreCompleto(rs.getString("nombre"));
							persona.setNombres(rs.getString("nombre"));
							persona.setApellidos(rs.getString("per_apellido"));
							persona.setIdentificacion(rs.getString("identificacion"));
							persona.setIdentificacionProveedor(rs.getString("identificacion"));
							contrato.setPersona(persona);

							return contrato;
						}
					});
			if (listaContrato.size() > 0) {
				return listaContrato.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
