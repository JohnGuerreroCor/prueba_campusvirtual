package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Calendario;
import co.edu.usco.inscripciones.model.Oferta;
import co.edu.usco.inscripciones.model.OfertaRequisitos;
import co.edu.usco.inscripciones.model.Programa;

@Component
public class OfertaDaoJDBCTemplateImpl implements OfertaDao {

	@Autowired
	DataSource dataSource;

	@Autowired
	WebParametroDao webParametroDao;

	@Override
	public List<Oferta> crearListaOferta(String uaa, String requierePago) {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT o.ofa_codigo,o.ofa_requiere_pago,o.ofa_imagen, o.ofa_fecha_inscripcion_inicio, ";
		sql = sql + " o.ofa_fecha_inscripcion_fin, o.ofa_fecha_inicio, o.ofa_fecha_fin, o.ofa_cupo_max, o.ofa_valor,";
		sql = sql + " p.pro_codigo,p.pro_titulo_otorgado,u.uaa_codigo,u.uaa_nombre, ";
		sql = sql + " ud.uaa_nombre as nombreDependencia";
		sql = sql + " FROM programa p ";
		sql = sql + " INNER JOIN uaa u ON (p.uaa_codigo=u.uaa_codigo) ";
		sql = sql + " INNER JOIN uaa ud ON u.uaa_dependencia = ud.uaa_codigo ";
		sql = sql + " INNER JOIN oferta_academica o ON (p.pro_codigo=o.pro_codigo) ";
		sql = sql + " WHERE p.mod_codigo = 5 AND o.ofa_estado != 0 ";

		if (!requierePago.equals("") && !requierePago.equals("undefined")) {
			sql = sql + " AND o.ofa_requiere_pago in (" + requierePago + ") ";
		}

		if (!uaa.equals("")) {
			sql = sql + " AND u.uaa_codigo in (" + uaa + ") ";
		} else {
			sql = sql + " AND GETDATE() between o.ofa_fecha_inscripcion_inicio AND o.ofa_fecha_inscripcion_fin ";
		}

		sql = sql + " GROUP BY o.ofa_codigo,o.ofa_requiere_pago,o.ofa_imagen, o.ofa_fecha_inscripcion_inicio, ";
		sql = sql + " o.ofa_fecha_inscripcion_fin, o.ofa_fecha_inicio, o.ofa_fecha_fin, o.ofa_cupo_max, o.ofa_valor,";
		sql = sql + " p.pro_codigo,p.pro_titulo_otorgado,u.uaa_codigo,u.uaa_nombre, ud.uaa_nombre";

		List<Oferta> listaOferta = jdbcTemplate.query(sql, new RowMapper<Oferta>() {

			public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Oferta oferta = new Oferta();
				oferta.setCodigo(rs.getInt("ofa_codigo"));
				oferta.setRequierePago(rs.getInt("ofa_requiere_pago"));
				oferta.setImagen(rs.getString("ofa_imagen"));

				oferta.setFechaIncripcionInicio(rs.getDate("ofa_fecha_inscripcion_inicio"));
				oferta.setFechaIncripcionFin(rs.getDate("ofa_fecha_inscripcion_fin"));
				oferta.setFechaInicio(rs.getDate("ofa_fecha_inicio"));
				oferta.setFechaFin(rs.getDate("ofa_fecha_fin"));
				oferta.setCupoMaximo(rs.getInt("ofa_cupo_max"));
				oferta.setValor(rs.getInt("ofa_valor"));

				Programa programa = new Programa();
				programa.setCodigo(rs.getInt("pro_codigo"));
				programa.setNombre(rs.getString("uaa_nombre"));
				programa.setCodigoUaa(rs.getInt("uaa_codigo"));
				programa.setNombreUaa(rs.getString("nombreDependencia"));
				oferta.setPrograma(programa);
				return oferta;
			}
		});

		return listaOferta;
	}

	@Override
	public List<Oferta> crearListaOfertaFormalVirtual() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT o.ofa_codigo,o.ofa_requiere_pago,o.ofa_imagen, o.ofa_fecha_inscripcion_inicio, "
				+ "o.ofa_fecha_inscripcion_fin, o.ofa_fecha_inicio, o.ofa_fecha_fin, o.ofa_cupo_max, o.ofa_valor, "
				+ "p.pro_codigo,p.pro_titulo_otorgado,u.uaa_codigo,u.uaa_nombre, ud.uaa_nombre as nombreDependencia "
				+ "FROM programa p " + "INNER JOIN uaa u ON (p.uaa_codigo=u.uaa_codigo) "
				+ "INNER JOIN uaa ud ON u.uaa_dependencia = ud.uaa_codigo "
				+ "INNER JOIN oferta_academica o ON (p.pro_codigo=o.pro_codigo) " + "WHERE p.mod_codigo = 2 "
				+ "AND u.uat_codigo in (SELECT wep_valor FROM web_parametro WHERE wep_nombre = 'EDUCACION_VIRTUAL_TIPO_UAA_EDUCACION_NO_FORMAL') "
				+ "GROUP BY o.ofa_codigo,o.ofa_requiere_pago,o.ofa_imagen, o.ofa_fecha_inscripcion_inicio, "
				+ "o.ofa_fecha_inscripcion_fin, o.ofa_fecha_inicio, o.ofa_fecha_fin, o.ofa_cupo_max, o.ofa_valor, "
				+ "p.pro_codigo,p.pro_titulo_otorgado,u.uaa_codigo,u.uaa_nombre, ud.uaa_nombre";

		List<Oferta> listaOferta = jdbcTemplate.query(sql, new RowMapper<Oferta>() {

			public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Oferta oferta = new Oferta();
				oferta.setCodigo(rs.getInt("ofa_codigo"));
				oferta.setRequierePago(rs.getInt("ofa_requiere_pago"));
				oferta.setImagen(rs.getString("ofa_imagen"));

				oferta.setFechaIncripcionInicio(rs.getDate("ofa_fecha_inscripcion_inicio"));
				oferta.setFechaIncripcionFin(rs.getDate("ofa_fecha_inscripcion_fin"));
				oferta.setFechaInicio(rs.getDate("ofa_fecha_inicio"));
				oferta.setFechaFin(rs.getDate("ofa_fecha_fin"));
				oferta.setCupoMaximo(rs.getInt("ofa_cupo_max"));
				oferta.setValor(rs.getInt("ofa_valor"));

				Programa programa = new Programa();
				programa.setCodigo(rs.getInt("pro_codigo"));
				programa.setNombre(rs.getString("uaa_nombre"));
				programa.setCodigoUaa(rs.getInt("uaa_codigo"));
				programa.setNombreUaa(rs.getString("nombreDependencia"));
				oferta.setPrograma(programa);
				return oferta;
			}
		});

		return listaOferta;
	}

	@Override
	public List<Oferta> crearListaOfertaEducacionFormal(String calendario) {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT o.ofa_codigo, u.uaa_nombre_corto, p.pro_codigo, p.sed_codigo, o.cal_codigo, s.sed_nombre, cal_nombre, "
				+ "o.ofa_fecha_inscripcion_inicio, o.ofa_fecha_inscripcion_fin, o.ofa_fecha_inicio, o.ofa_fecha_fin, o.ofa_cupo_max, "
				+ "o.ofa_valor, p.pro_titulo_otorgado, u.uaa_codigo, u.uaa_nombre, ud.uaa_nombre as nombreDependencia "
				+ "FROM oferta_academica o " + "INNER JOIN programa p ON o.pro_codigo = p.pro_codigo "
				+ "INNER JOIN uaa u ON p.uaa_codigo = u.uaa_codigo "
				+ "INNER JOIN uaa ud ON u.uaa_dependencia = ud.uaa_codigo "
				+ "INNER JOIN sede s ON p.sed_codigo = s.sed_codigo "
				+ "INNER JOIN calendario c ON c.cal_codigo = o.cal_codigo " + "WHERE  p.nia_codigo in(9, 11) "
				+ "AND o.ofa_estado != 0 "
				+ "AND u.uat_codigo not in (SELECT wep_valor FROM web_parametro WHERE wep_nombre = 'EDUCACION_VIRTUAL_TIPO_UAA_EDUCACION_NO_FORMAL') "
				+ "AND o.cal_codigo in (" + calendario + ") " + "ORDER BY u.uaa_nombre ";

		List<Oferta> listaOferta = jdbcTemplate.query(sql, new RowMapper<Oferta>() {

			public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Oferta oferta = new Oferta();
				oferta.setCodigo(rs.getInt("ofa_codigo"));

				oferta.setFechaIncripcionInicio(rs.getDate("ofa_fecha_inscripcion_inicio"));
				oferta.setFechaIncripcionFin(rs.getDate("ofa_fecha_inscripcion_fin"));
				oferta.setFechaInicio(rs.getDate("ofa_fecha_inicio"));
				oferta.setFechaFin(rs.getDate("ofa_fecha_fin"));
				oferta.setCupoMaximo(rs.getInt("ofa_cupo_max"));
				oferta.setValor(rs.getInt("ofa_valor"));

				Programa programa = new Programa();
				programa.setCodigo(rs.getInt("pro_codigo"));
				programa.setNombre(rs.getString("uaa_nombre"));
				programa.setCodigoUaa(rs.getInt("uaa_codigo"));
				programa.setNombreUaa(rs.getString("nombreDependencia"));
				oferta.setPrograma(programa);
				return oferta;
			}
		});

		return listaOferta;
	}

	@Override
	public List<Oferta> crearListaFacultadesOferta(int requierePago) {
		// @RolesAllowed("INVITADO")
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = " SELECT u.uaa_codigo,UPPER(u.uaa_nombre) as uaa_nombre ";
		sql = sql + "FROM programa p ";
		sql = sql + "INNER JOIN uaa u ON (p.uaa_codigo=u.uaa_codigo) ";
		sql = sql + "INNER JOIN oferta_academica o ON (p.pro_codigo=o.pro_codigo) ";
		sql = sql + "WHERE p.mod_codigo = 5 AND o.ofa_estado != 0 ";
		if (requierePago != 2) {
			sql = sql + " AND o.ofa_requiere_pago = " + requierePago;
		}
		sql = sql + "AND GETDATE() between o.ofa_fecha_inscripcion_inicio AND o.ofa_fecha_inscripcion_fin ";
		sql = sql + "GROUP BY u.uaa_codigo,u.uaa_nombre ";
		sql = sql + "ORDER BY u.uaa_nombre ASC ";

		List<Oferta> listaOferta = jdbcTemplate.query(sql, new RowMapper<Oferta>() {

			public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Oferta oferta = new Oferta();
				Programa programa = new Programa();
				programa.setCodigoUaa(rs.getInt("uaa_codigo"));
				programa.setNombreUaa(rs.getString("uaa_nombre"));
				oferta.setPrograma(programa);
				return oferta;
			}
		});

		return listaOferta;
	}

	@Override
	public List<Oferta> crearListaOfertaInactiva() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT o.ofa_codigo,o.ofa_requiere_pago,o.ofa_imagen, o.ofa_fecha_inscripcion_inicio, "
				+ "o.ofa_fecha_inscripcion_fin, o.ofa_fecha_inicio, o.ofa_fecha_fin, o.ofa_fecha_fin, o.ofa_cupo_max, o.ofa_valor, "
				+ "p.pro_codigo,p.pro_titulo_otorgado,u.uaa_codigo,u.uaa_nombre, ud.uaa_nombre as nombreDependencia "
				+ "FROM programa p " + "INNER JOIN uaa u ON (p.uaa_codigo=u.uaa_codigo) "
				+ "INNER JOIN uaa ud ON u.uaa_dependencia = ud.uaa_codigo "
				+ "INNER JOIN oferta_academica o ON (p.pro_codigo=o.pro_codigo) "
				+ "WHERE p.mod_codigo = 5 AND GETDATE() > o.ofa_fecha_inscripcion_fin " + "AND o.ofa_estado != 0 "
				+ "GROUP BY o.ofa_codigo,o.ofa_requiere_pago,o.ofa_imagen, o.ofa_fecha_inscripcion_inicio, "
				+ "o.ofa_fecha_inscripcion_fin, o.ofa_fecha_inicio, o.ofa_fecha_fin, o.ofa_cupo_max, o.ofa_valor, "
				+ "p.pro_codigo,p.pro_titulo_otorgado,u.uaa_codigo,u.uaa_nombre, ud.uaa_nombre ";

		List<Oferta> listaOferta = jdbcTemplate.query(sql, new RowMapper<Oferta>() {

			public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Oferta oferta = new Oferta();
				oferta.setCodigo(rs.getInt("ofa_codigo"));
				oferta.setRequierePago(rs.getInt("ofa_requiere_pago"));
				oferta.setImagen(rs.getString("ofa_imagen"));

				oferta.setFechaIncripcionInicio(rs.getDate("ofa_fecha_inscripcion_inicio"));
				oferta.setFechaIncripcionFin(rs.getDate("ofa_fecha_inscripcion_fin"));
				oferta.setFechaInicio(rs.getDate("ofa_fecha_inicio"));
				oferta.setFechaFin(rs.getDate("ofa_fecha_fin"));
				oferta.setCupoMaximo(rs.getInt("ofa_cupo_max"));
				oferta.setValor(rs.getInt("ofa_valor"));

				Programa programa = new Programa();
				programa.setCodigo(rs.getInt("pro_codigo"));
				programa.setNombre(rs.getString("uaa_nombre"));
				programa.setCodigoUaa(rs.getInt("uaa_codigo"));
				programa.setNombreUaa(rs.getString("nombreDependencia"));
				oferta.setPrograma(programa);
				return oferta;
			}
		});

		return listaOferta;
	}

	@Override
	public Oferta consultarDatosOferta(int codigoOferta) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT o.ofa_codigo,o.cal_codigo,o.ofa_imagen, o.ofa_fecha_inscripcion_inicio, oe.ofe_codigo as interna,";
			sql = sql + " o.ofa_fecha_inscripcion_fin, o.ofa_fecha_inicio, o.ofa_fecha_fin, o.ofa_requiere_pago,";
			sql = sql + " o.ofa_cupo_max, o.ofa_valor, p.pro_codigo,p.pro_titulo_otorgado,u.uaa_nombre,p.pro_horario,";
			sql = sql
					+ " u.uaa_codigo, ud.uaa_nombre as nombreDependencia, utd.uat_codigo, utd.uat_nombre, oep.ofe_codigo as internaProveedor";
			sql = sql + " FROM programa p ";
			sql = sql + " INNER JOIN uaa u ON (p.uaa_codigo=u.uaa_codigo)";
			sql = sql + " INNER JOIN uaa ud ON u.uaa_dependencia = ud.uaa_codigo";
			sql = sql + " INNER JOIN uaa_tipo utd ON (ud.uat_codigo = utd.uat_codigo)";
			sql = sql + " INNER JOIN oferta_academica o ON (p.pro_codigo=o.pro_codigo) ";
			sql = sql
					+ " LEFT JOIN inscripciones.oferta_estamento oe ON (o.ofa_codigo = oe.ofa_codigo AND oe.ofe_estado = 1)";
			sql = sql
					+ " LEFT JOIN inscripciones.oferta_estamento oep ON (o.ofa_codigo = oep.ofa_codigo AND oep.ofe_estado = 1 and oep.ofe_tipo_usuario like '%6%')";
			sql = sql + " WHERE p.mod_codigo = 5 ";
			sql = sql + " AND o.ofa_codigo = ?";
			sql = sql
					+ " GROUP BY o.ofa_codigo,o.cal_codigo,o.ofa_imagen, o.ofa_fecha_inscripcion_inicio,oe.ofe_codigo,";
			sql = sql + " o.ofa_fecha_inscripcion_fin, o.ofa_fecha_inicio, o.ofa_fecha_fin, o.ofa_requiere_pago,";
			sql = sql + " o.ofa_cupo_max, o.ofa_valor, p.pro_codigo,p.pro_titulo_otorgado,u.uaa_nombre,p.pro_horario,";
			sql = sql + " u.uaa_codigo, ud.uaa_nombre, utd.uat_codigo, utd.uat_nombre, oep.ofe_codigo";

			List<Oferta> listaOferta = jdbcTemplate.query(sql, new Object[] { codigoOferta }, new RowMapper<Oferta>() {

				public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
					Oferta oferta = new Oferta();
					oferta.setCodigo(rs.getInt("ofa_codigo"));

					Calendario calendario = new Calendario();
					calendario.setCodigo(rs.getInt("cal_codigo"));
					oferta.setCalendario(calendario);

					oferta.setImagen(rs.getString("ofa_imagen"));
					oferta.setFechaIncripcionInicio(rs.getDate("ofa_fecha_inscripcion_inicio"));
					oferta.setFechaIncripcionFin(rs.getDate("ofa_fecha_inscripcion_fin"));
					oferta.setFechaInicio(rs.getDate("ofa_fecha_inicio"));
					oferta.setFechaFin(rs.getDate("ofa_fecha_fin"));
					oferta.setRequierePago(rs.getInt("ofa_requiere_pago"));
					oferta.setCupoMaximo(rs.getInt("ofa_cupo_max"));
					oferta.setValor(rs.getInt("ofa_valor"));
					// String tipoUaaNoFormal = webParametroDao
					// .listarWebParametro("EDUCACION_VIRTUAL_FACULTAD_UAA_TIPO_NO_FORMAL").getValor();

					if (rs.getString("interna") != null) {
						oferta.setInterna("1");
					} else {
						oferta.setInterna("0");
					}
					
					if (rs.getString("internaProveedor") != null) {
						oferta.setInternaProveedores("1");
					} else {
						oferta.setInternaProveedores("0");
					}

					Programa programa = new Programa();
					programa.setCodigo(rs.getInt("pro_codigo"));
					programa.setNombre(rs.getString("uaa_nombre"));
					programa.setNombreUaa(rs.getString("nombreDependencia"));
					programa.setHorario(rs.getString("pro_horario"));
					programa.setCodigoUaa(rs.getInt("uaa_codigo"));
					oferta.setPrograma(programa);
					return oferta;
				}

			});
			if (listaOferta.size() > 0) {
				return listaOferta.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Oferta> consultarInformacionOferta(int codigoOferta) {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT ofi_titulo, ofi_contenido " + "FROM inscripciones.oferta_informacion "
				+ "WHERE ofa_codigo = " + codigoOferta + " AND ofi_estado = 1 " + "ORDER BY ofi_orden ASC";

		List<Oferta> listaRequisitos = jdbcTemplate.query(sql, new RowMapper<Oferta>() {

			public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Oferta oferta = new Oferta();
				oferta.setTitulo(rs.getString("ofi_titulo"));
				oferta.setContenido(rs.getString("ofi_contenido"));
				return oferta;
			}

		});

		return listaRequisitos;
	}

	@Override
	public List<Oferta> consultarRequisitosOferta(int codigoOferta, String codigoEncriptado) {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT o.ore_codigo, o.ore_descripcion , ot.otr_descripcion ";
		sql = sql + "FROM inscripciones.oferta_requisito o, inscripciones.oferta_requisito_tipo ot ";
		sql = sql + "WHERE o.otr_codigo=ot.otr_codigo AND o.ore_estado = 1 ";
		if (codigoOferta != 0) {
			sql = sql + "AND o.ofa_codigo = " + codigoOferta;
		} else {
			sql = sql + "AND CONVERT(VARCHAR(255),HASHBYTES('SHA1', CONVERT(VARCHAR(255),o.ofa_codigo))) = 0x"
					+ codigoEncriptado;
		}

		List<Oferta> listaRequisitos = jdbcTemplate.query(sql, new RowMapper<Oferta>() {

			public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Oferta oferta = new Oferta();

				oferta.setCodigo(rs.getInt("ore_codigo"));
				OfertaRequisitos requisitos = new OfertaRequisitos();
				requisitos.setDescripcion(rs.getString("ore_descripcion"));
				requisitos.setNombreTipo(rs.getString("otr_descripcion"));
				oferta.setOfertaRequisitos(requisitos);
				return oferta;
			}

		});

		return listaRequisitos;
	}

	@Override
	public List<OfertaRequisitos> consultarRequisitosOfertaAdjuntos(String codigoOferta) {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT o.ore_codigo, o.ore_descripcion , ot.otr_descripcion "
				+ "FROM inscripciones.oferta_requisito o, inscripciones.oferta_requisito_tipo ot "
				+ "WHERE o.otr_codigo=ot.otr_codigo AND ot.otr_codigo = 1  AND o.ore_estado = 1 "
				+ "AND CONVERT(VARCHAR(255),HASHBYTES('SHA1', CONVERT(VARCHAR(255),o.ofa_codigo))) = 0x" + codigoOferta;

		List<OfertaRequisitos> listaRequisitos = jdbcTemplate.query(sql, new RowMapper<OfertaRequisitos>() {

			public OfertaRequisitos mapRow(ResultSet rs, int rowNum) throws SQLException {
				OfertaRequisitos requisitos = new OfertaRequisitos();
				requisitos.setCodigo(rs.getInt("ore_codigo"));
				requisitos.setDescripcion(rs.getString("ore_descripcion"));
				requisitos.setNombreTipo(rs.getString("otr_descripcion"));
				return requisitos;
			}

		});

		return listaRequisitos;
	}

	@Override
	public Oferta consultarEstadoOferta(long codigoOferta, String codigoEncriptado) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "SELECT o.pro_codigo,p.pro_titulo_otorgado,p.pro_estado,o.cal_codigo,o.ofa_codigo,o.ofa_estado, "
					+ "o.ofa_admision_automatica, o.ofa_requiere_pago, o.ofa_crear_usuario, o.ofa_fecha_inscripcion_inicio, "
					+ "o.ofa_fecha_inscripcion_fin, o.ofa_cupo_max, o.ofa_valor, oe.ofe_codigo,"
					+ "u.uaa_nombre, u.uaa_codigo, ud.uaa_nombre as nombreDependencia, utd.uat_codigo, utd.uat_nombre  "
					+ "FROM oferta_academica o " 
					+ "INNER JOIN programa p ON (o.pro_codigo = p.pro_codigo) "
					+ "INNER JOIN uaa u ON (p.uaa_codigo=u.uaa_codigo) "
					+ "INNER JOIN uaa ud ON u.uaa_dependencia = ud.uaa_codigo "
					+ " INNER JOIN uaa_tipo utd ON (ud.uat_codigo = utd.uat_codigo) ";
			sql = sql
					+ " LEFT JOIN inscripciones.oferta_estamento oe ON (o.ofa_codigo = oe.ofa_codigo AND oe.ofe_estado = 1)";
			if (codigoOferta != 0) {
				sql = sql + " WHERE o.ofa_codigo = " + codigoOferta;
			} else {
				sql = sql + " WHERE CONVERT(VARCHAR(255),HASHBYTES('SHA1', CONVERT(VARCHAR(255),o.ofa_codigo))) = 0x"
						+ codigoEncriptado;
			}
			System.out.println(sql);
			List<Oferta> listaEstadoOferta = jdbcTemplate.query(sql, new RowMapper<Oferta>() {

				public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
					Oferta oferta = new Oferta();
					oferta.setCodigo(rs.getInt("ofa_codigo"));
					oferta.setEstado(rs.getString("ofa_estado"));
					oferta.setAdmisionAutomatica(rs.getInt("ofa_admision_automatica"));
					oferta.setRequierePago(rs.getInt("ofa_requiere_pago"));
					oferta.setCrearUsuario(rs.getInt("ofa_crear_usuario"));
					oferta.setFechaIncripcionInicio(rs.getDate("ofa_fecha_inscripcion_inicio"));
					oferta.setFechaIncripcionFin(rs.getDate("ofa_fecha_inscripcion_fin"));
					oferta.setCupoMaximo(rs.getInt("ofa_cupo_max"));
					oferta.setValor(rs.getInt("ofa_valor"));
					/*
					 * String tipoUaaNoFormal = webParametroDao
					 * .listarWebParametro(
					 * "EDUCACION_VIRTUAL_FACULTAD_UAA_TIPO_NO_FORMAL").getValor
					 * ();
					 * 
					 * if (rs.getString("uat_codigo").equals(tipoUaaNoFormal)) {
					 * oferta.setInterna("0"); } else { oferta.setInterna("1");
					 * }
					 */

					if (rs.getString("ofe_codigo") != null) {
						oferta.setInterna("1");
					} else {
						oferta.setInterna("0");
					}

					Calendario calendario = new Calendario();
					calendario.setCodigo(rs.getInt("cal_codigo"));
					oferta.setCalendario(calendario);
					Programa programa = new Programa();
					programa.setCodigo(rs.getInt("pro_codigo"));
					programa.setNombre(rs.getString("uaa_nombre"));
					programa.setCodigoUaa(rs.getInt("uaa_codigo"));
					programa.setNombreUaa(rs.getString("nombreDependencia"));
					programa.setEstado(rs.getString("pro_estado"));
					oferta.setPrograma(programa);
					return oferta;
				}
			});
			if (listaEstadoOferta.size() > 0) {
				return listaEstadoOferta.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Oferta consultarCantidadInscritos(long codigoOferta) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "SELECT COUNT(*) as cant " + "FROM persona p "
					+ "INNER JOIN inscripcion i ON (p.per_codigo = i.per_codigo) "
					+ "INNER JOIN inscripcion_programa ip ON (i.ins_codigo = ip.ins_codigo) " + "WHERE ip.ofa_codigo = "
					+ codigoOferta;

			List<Oferta> listaEstadoOferta = jdbcTemplate.query(sql, new RowMapper<Oferta>() {

				public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
					Oferta oferta = new Oferta();
					oferta.setCantidadInscritos(rs.getInt("cant"));
					return oferta;
				}
			});
			if (listaEstadoOferta.size() > 0) {
				return listaEstadoOferta.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
