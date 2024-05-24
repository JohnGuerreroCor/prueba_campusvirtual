package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.dto.OfertaConfiguracionDTO;

@Component
public class OfertaConfiguracionDaoJDBCTempleteImpl implements OfertaConfiguracionDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<OfertaConfiguracionDTO> listarOfertaConfiguracion(int oferta) {
		// TODO Auto-generated method stub
		Object[] obj = new Object[] {};

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select e.ofe_codigo, e.ofa_codigo, e.ofe_tipo_usuario, e.uaa_codigo, e.ofe_estado from inscripciones.oferta_estamento e where e.ofe_estado = 1";

		if (oferta != 0) {
			sql = sql + " and e.ofa_codigo = ? ";
			obj = new Object[] { oferta };
		}
		sql = sql + " order by e.ofe_codigo";
		System.out.println(oferta);
		System.out.println(sql);

		List<OfertaConfiguracionDTO> listaOfertaConfiguracion = jdbcTemplate.query(sql, obj,
				new RowMapper<OfertaConfiguracionDTO>() {

					public OfertaConfiguracionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

						OfertaConfiguracionDTO ofertaConfiguracion = new OfertaConfiguracionDTO();

						ofertaConfiguracion.setCodigo(rs.getInt("ofe_codigo"));
						ofertaConfiguracion.setUaa(listarUaa(rs.getString("uaa_codigo")));
						ofertaConfiguracion.setCodigosUaa(rs.getString("uaa_codigo"));
						ofertaConfiguracion.setNombreUsuarios(listarUsuarios(rs.getString("ofe_tipo_usuario")));
						ofertaConfiguracion.setCodigosUsuarios(rs.getString("ofe_tipo_usuario"));
						return ofertaConfiguracion;
					}

				});
		return listaOfertaConfiguracion;
	}

	@Override
	public List<String> listarUaa(String codigo) {
		// TODO Auto-generated method stub
		Object[] obj = new Object[] {};

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select uaa_nombre from uaa where uaa_codigo in (" + codigo + ")";

		List<String> listaUaaNombres = jdbcTemplate.query(sql, obj, new RowMapper<String>() {

			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				String listaUaa = rs.getString("uaa_nombre");
				return listaUaa;
			}

		});
		return listaUaaNombres;
	}

	@Override
	public String listarUsuarios(String codigo) {
		// TODO Auto-generated method stub
		String usuarios = "";

		String usu = codigo;
		String usuArray[] = usu.split(",");

		for (String us : usuArray) {

			switch (Integer.parseInt(us)) {
			case 1:
				usuarios = usuarios + "Administrativos, ";
				break;
			case 2:
				usuarios = usuarios + "Estudiante, ";
				break;
			case 3:
				usuarios = usuarios + "Docentes, ";
				break;
			case 6:
				usuarios = usuarios + "Proveedores, ";
				break;
			case 0:
				usuarios = usuarios + "Todos, ";
				break;
			}
		}

		return usuarios.substring(0, usuarios.length() - 2);
	}

}
