package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import co.edu.usco.configuration.UscoGrantedAuthority;
import co.edu.usco.configuration.User;
import co.edu.usco.inscripciones.model.Persona;
import co.edu.usco.inscripciones.model.Uaa;

@Component
public class UsuarioDaoJDBCTemplateImpl implements UsuarioDao {

	@Autowired
	DataSource dataSource;
	@Override
	public User obtenerPorNombre(final String username) throws UsernameNotFoundException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT u.usu_codigo, p.per_codigo, p.per_nombre, "
				+ "p.per_apellido, p.per_identificacion, u.usu_estado "
				+ "FROM usuario u "
				+ "INNER JOIN persona p ON p.per_codigo = u.usu_persona "
				+ "WHERE u.usu_login = ?";
		List<User> user = jdbcTemplate.query(sql, new Object[] { username }, new RowMapper<User>() {

			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				Persona persona = new Persona();
				persona.setCodigo(rs.getInt("per_codigo"));
				persona.setNombres(rs.getString("per_nombre"));
				persona.setApellidos(rs.getString("per_apellido"));
				persona.setIdentificacion(rs.getString("per_identificacion"));
				User user = new User(username, "", listarRoles(rs.getInt("usu_codigo")));
				user.setId(rs.getInt("usu_codigo"));
				user.setPersona(persona);
				return user;
			}

		});

		if (user.isEmpty())
			throw new UsernameNotFoundException("Usuario " + username + " no encontrado");

		return user.get(0);
	}
	@Override
	public List<UscoGrantedAuthority> listarRoles(int usuario) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT g.gru_id, u.uaa_codigo, u.uaa_nombre, u.uaa_dependencia "
				+ "FROM grupo g "
				+ "INNER JOIN usuario_grupo ug ON ug.usg_grupo = g.gru_codigo "
				+ "INNER JOIN uaa u ON u.uaa_codigo = ug.usg_uaa WHERE ug.usg_usuario = ?";
		List<UscoGrantedAuthority> roles = jdbcTemplate.query(sql, new Object[] { usuario },
				new RowMapper<UscoGrantedAuthority>() {

					public UscoGrantedAuthority mapRow(ResultSet rs, int arg1) throws SQLException {
						Uaa uaa = new Uaa();
						uaa.setCodigo(rs.getInt("uaa_codigo"));
						uaa.setNombre(rs.getString("uaa_nombre"));
						uaa.setDependencia(rs.getInt("uaa_dependencia"));
						
						UscoGrantedAuthority simpleGrantedAuthority = new UscoGrantedAuthority(
								"ROLE_" + rs.getString("gru_id"), uaa);
						return simpleGrantedAuthority;
					}

				});
		return roles;
	}

}
