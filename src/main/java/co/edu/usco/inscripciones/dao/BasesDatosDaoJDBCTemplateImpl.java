package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.BasesDatos;
import co.edu.usco.inscripciones.model.VisitasBasesDatos;

@Component
public class BasesDatosDaoJDBCTemplateImpl implements BasesDatosDao{
	
	@Autowired
	DataSource dataSource;
	@Override
	public List<BasesDatos> crearListaBasesDatos() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT bd_codigo, bd_nombre, bd_area_tema, bd_descripcion, "
				+ "bd_tipo_acceso, bd_link, bd_imagen "
				+ "FROM biblioteca.listado_bases_datos";
				
		List<BasesDatos> listaBasesDatos = jdbcTemplate.query(sql, new RowMapper<BasesDatos>() {

			public BasesDatos mapRow(ResultSet rs, int rowNum) throws SQLException {
				BasesDatos bd = new BasesDatos();
				bd.setCodigo(rs.getInt("bd_codigo"));
				bd.setNombre(rs.getString("bd_nombre"));
				bd.setAreaTema(rs.getString("bd_area_tema"));
				bd.setDescripcion(rs.getString("bd_descripcion"));
				bd.setTipoAcceso(rs.getString("bd_tipo_acceso"));
				bd.setLink(rs.getString("bd_link"));
				bd.setImagen(rs.getString("bd_imagen"));
				return bd;
			}
			
		});

		return listaBasesDatos;
	}
	@Override
	public BasesDatos consultarBaseDato(int codigoBD) {
		try {
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = " SELECT bd_nombre, bd_area_tema, bd_descripcion, "
					+ "bd_tipo_acceso, bd_link, bd_imagen "
					+ "FROM biblioteca.listado_bases_datos"
					+ " WHERE bd_codigo = " + codigoBD ;
			List<BasesDatos> listaBasesDatos = jdbcTemplate.query(sql, new RowMapper<BasesDatos>() {

				public BasesDatos mapRow(ResultSet rs, int rowNum) throws SQLException {
					BasesDatos bd = new BasesDatos();
					bd.setNombre(rs.getString("bd_nombre"));
					bd.setAreaTema(rs.getString("bd_area_tema"));
					bd.setDescripcion(rs.getString("bd_descripcion"));
					bd.setTipoAcceso(rs.getString("bd_tipo_acceso"));
					bd.setLink(rs.getString("bd_link"));
					bd.setImagen(rs.getString("bd_imagen"));
					return bd;
				}
			});
			if (listaBasesDatos.size() > 0) {
				return listaBasesDatos.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean agregarVisita(String usuario, String link, String nombre){
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		String cadenaFecha = formato.format(fechaActual);
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 
		
		String sql = "INSERT INTO biblioteca.visitas_bases_datos (usuario, link, tipo, fecha, servidor, nombre)"
				+ " VALUES (?, ?, 'F', convert(datetime,'" + cadenaFecha + "'), 'Plataforma Virtual', ?)";
		int resultado = jdbcTemplate.update(sql, 
				usuario, link, nombre);
		
		if(resultado > 0){
			return true;
		}
		
		return false;
	}
	@Override
	public List<VisitasBasesDatos> consultarVisitasBasesDatos() {
		// @RolesAllowed("INVITADO")
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT id, usuario, link, tipo, fecha, servidor, nombre "
				+ "FROM biblioteca.visitas_bases_datos "
				+ "WHERE len(usuario) > 0"
				+ "ORDER BY nombre ASC";
		List<VisitasBasesDatos> listaBasesDatos = jdbcTemplate.query(sql, new RowMapper<VisitasBasesDatos>() {

			public VisitasBasesDatos mapRow(ResultSet rs, int rowNum) throws SQLException {
				VisitasBasesDatos vbd = new VisitasBasesDatos();

				vbd.setId(rs.getInt("id"));
				vbd.setUsuario(rs.getString("usuario"));
				vbd.setLink(rs.getString("link"));
				vbd.setTipo(rs.getString("tipo"));
				vbd.setFecha(rs.getDate("fecha"));
				
				if(vbd.getFecha()!=null){
					vbd.setFechaLong(vbd.getFecha().getTime());
				}
				
				vbd.setServidor(rs.getString("servidor"));
				vbd.setNombre(rs.getString("nombre"));
				return vbd;
			}
		});

		return listaBasesDatos;
	}
}
