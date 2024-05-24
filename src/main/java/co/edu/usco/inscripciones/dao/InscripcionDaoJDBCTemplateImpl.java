package co.edu.usco.inscripciones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Inscripcion;
import co.edu.usco.inscripciones.model.InscripcionPrograma;
import co.edu.usco.inscripciones.model.JSONRespuesta;
import co.edu.usco.inscripciones.model.Oferta;
import co.edu.usco.inscripciones.model.Persona;
import co.edu.usco.inscripciones.model.PreInscripcion;
import co.edu.usco.inscripciones.model.Programa;
import co.edu.usco.librerias.TheadEnvioCorreo;

@Component
public class InscripcionDaoJDBCTemplateImpl implements InscripcionDao {

	@Autowired
	DataSource dataSource;

	/*@Override
	public boolean agregarIncripcionGratis(final Inscripcion inscripcion) {

		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			final String sql = "INSERT INTO inscripcion (per_codigo,ter_codigo,ine_codigo,ins_usuario)"
					+ " VALUES (?, ?, 8809, ?)";

			KeyHolder keyHolder = new GeneratedKeyHolder();
			int resultado = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

					int codPersona = 0;
					int codTercero = 0;
					if (inscripcion.getPersona().isTercero() == true) {
						codTercero = inscripcion.getPersona().getId();
					} else if (inscripcion.getPersona().isTercero() == false) {
						codPersona = inscripcion.getPersona().getId();
					}
					pstm.setInt(1, codPersona);
					pstm.setInt(2, codTercero);
					pstm.setInt(3, inscripcion.getCodigoUsuario());

					return pstm;
				}
			}, keyHolder);

			if (resultado > 0) {
				inscripcion.setId(keyHolder.getKey().intValue());
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}*/
	
	@Override
	public boolean agregarIncripcionGratis(final Inscripcion inscripcion) {

		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			int codPersona = 0;
			int codTercero = 0;
			
			if (inscripcion.getPersona().isTercero() == true) {
				codTercero = inscripcion.getPersona().getId();
			} else if (inscripcion.getPersona().isTercero() == false) {
				codPersona = inscripcion.getPersona().getId();
			}
			
			final String sql = "INSERT INTO inscripcion (per_codigo,ter_codigo,ine_codigo,ins_usuario)"
					+ " VALUES (?, ?, 8809, ?)";

			int resultado = jdbcTemplate.update(sql, codPersona, codTercero, inscripcion.getCodigoUsuario());

			if (resultado > 0) {
				System.out.println("Se agregó en inscripciones " + inscripcion.getCodigo());
				return true;
			} else {
				System.out.println("No se agregó en inscripciones");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean agregarIncripcionPaga(final Inscripcion inscripcion) {

		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			final String sql = "INSERT INTO inscripcion (per_codigo,ter_codigo,ine_codigo,ins_referencia, ins_habeas_data)"
					+ " VALUES (?, ?, 8809, ?, ?)";

			KeyHolder keyHolder = new GeneratedKeyHolder();
			int resultado = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

					int codPersona = 0;
					int codTercero = 0;
					if (inscripcion.getPersona().isTercero() == true) {
						codTercero = inscripcion.getPersona().getId();
					} else if (inscripcion.getPersona().isTercero() == false) {
						codPersona = inscripcion.getPersona().getId();
					}

					pstm.setInt(1, codPersona);
					pstm.setInt(2, codTercero);
					pstm.setLong(3, inscripcion.getFactura().getReferencia());
					pstm.setInt(4, 1);

					return pstm;
				}
			}, keyHolder);

			if (resultado > 0) {
				inscripcion.setId(keyHolder.getKey().intValue());
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean agregarIncripcionPrograma(final Inscripcion inscripcion, final int codigoOferta) {

		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			final String sql = "INSERT INTO inscripcion_programa (ins_codigo,ofa_codigo,inp_opcion, inp_nota_prueba_especial)"
					+ " VALUES (?, ?, 1, 0)";

			KeyHolder keyHolder = new GeneratedKeyHolder();
			int resultado = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

					pstm.setInt(1, inscripcion.getId());
					pstm.setInt(2, codigoOferta);

					return pstm;
				}
			}, keyHolder);

			System.out.println("PROVEEDOR:" + inscripcion.isProveedor());

			if (resultado > 0) {
				String mensaje = "";
				String destinatario = inscripcion.getPersona().getEmail();
				String destinatarioC = "";
				if (inscripcion.getPersona().getEmailInterno() != null) {
					destinatarioC = inscripcion.getPersona().getEmailInterno();
				}

				String footer = "NOTA CONFIDENCIAL:<br>"
						+ "Este mensaje es exclusivamente para el uso de la persona o entidad a quien está dirigido; La información contenida en este e-mail y en todos sus archivos anexos es completamente confidencial, de igual manera las opiniones expresadas son netamente personales, y no necesariamente transmiten el pensamiento de la Universidad Surcolombiana. Si usted no es el destinatario por favor háganoslo saber respondiendo a este correo y por favor destruya cualquier copia del mismo y sus adjuntos, cualquier almacenamiento, distribución, divulgación o copia de este mensaje está estrictamente prohibida y sancionada por la ley."
						+ "Si por error recibe este mensaje, ofrecemos disculpas.<br><br>" + "CONFIDENTIAL NOTE:<br>"
						+ "This message is exclusively for  use of the individual or entity to whom it is forwarded.  The information of this e-mail and all its attachments is completely confidential; likewise, the opinions expressed are purely personal and they do not necessarily convey the thought of  Surcolombiana University.  If you are not the addressee, please let us know it by replying to this e-mail and please delete any copy and its attachments.  Any storage, distribution, dissemination or copy of this information is strictly prohibited and punished  by law.";
				String asunto = "Inscripción correcta - Inscripciones Universidad Surcolombiana";
				if (inscripcion.getOferta().getAdmisionAutomatica() == 1) {
					mensaje = "Cordial saludo " + inscripcion.getPersona().getNombres().toUpperCase() + " "
							+ inscripcion.getPersona().getApellidos().toUpperCase()
							+ ",<br><br>Bienvenido a la Universidad Surcolombiana"
							+ "<br><br>Su inscripcion fue realizada correctamente, se encuentra en proceso de admisión y se le notificará vía e-mail si fue admitido. "
							+ "<br><br>" + footer;
				} else {
					mensaje = "Cordial saludo " + inscripcion.getPersona().getNombres().toUpperCase() + " ";
					if (inscripcion.getPersona().getApellidos() != null
							&& inscripcion.getPersona().getApellidos() != "") {
						mensaje = mensaje + inscripcion.getPersona().getApellidos().toUpperCase();
					}
					mensaje = mensaje + ",<br><br>Bienvenido a la Universidad Surcolombiana "
							+ "<br><br>Su inscripcion fue realizada correctamente. ";
					if (inscripcion.getOferta().getCrearUsuario() == 1) {
						mensaje = mensaje
								+ "<br><br>En los próximos minutos podrá ingresar a la plataforma. Para ingresar al curso dirigirse al portal de la campus virtual <a href='https://campusvirtual.usco.edu.co/campusvirtual/'>https://campusvirtual.usco.edu.co/campusvirtual/</a> y loguearse en la opción plataforma educativa con su usuario y clave. ";
					}
					mensaje = mensaje + "<br><br>" + footer;
				}

				if (inscripcion.isProveedor()) {
					System.out.println("Es Proveedor no se envia Correo");
					new TheadEnvioCorreo(destinatario, asunto, mensaje, destinatarioC).start();
				} else {
					System.out.println("No es proveedor se envia Correo");
					new TheadEnvioCorreo(destinatario, asunto, mensaje, destinatarioC).start();
				}

				InscripcionPrograma inscripcionPrograma = new InscripcionPrograma();
				inscripcionPrograma.setId(keyHolder.getKey().intValue());
				inscripcion.setInscripcionPrograma(inscripcionPrograma);

				return true;
			} else {
				InscripcionPrograma inscripcionPrograma = new InscripcionPrograma();
				inscripcionPrograma.setId(keyHolder.getKey().intValue());
				inscripcion.setInscripcionPrograma(inscripcionPrograma);

				return true;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public Inscripcion consultarCantidadRequisitos(int codigoOferta) {

		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "SELECT COUNT(ore_codigo) AS cant " + "FROM inscripciones.oferta_requisito "
					+ "WHERE ofa_codigo = " + codigoOferta + " AND otr_codigo = 1";
			List<Inscripcion> listaCantidadCertificados = jdbcTemplate.query(sql, new RowMapper<Inscripcion>() {
				public Inscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
					Inscripcion inscripcion = new Inscripcion();
					inscripcion.setCantidadCertificados(rs.getInt("cant"));
					return inscripcion;
				}
			});
			if (listaCantidadCertificados.size() > 0) {
				return listaCantidadCertificados.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean validarReferenciaInscripcion(long referencia) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "SELECT ins_codigo " + " FROM inscripcion " + "WHERE ins_referencia = '" + referencia + "'";
			List<Inscripcion> listaInscripcion = jdbcTemplate.query(sql, new RowMapper<Inscripcion>() {

				public Inscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
					Inscripcion inscripcion = new Inscripcion();
					inscripcion.setId(rs.getInt("ins_codigo"));
					return inscripcion;
				}
			});
			if (listaInscripcion.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean validarInscripcion(Inscripcion inscripcion) {
		try {
			System.out.println("Es tercero: " + inscripcion.getPersona().isTercero());
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = " SELECT i.ins_codigo  " + "FROM inscripcion i "
					+ "INNER JOIN inscripcion_programa ip ON i.ins_codigo = ip.ins_codigo " + "WHERE ip.ofa_codigo = "
					+ inscripcion.getOferta().getCodigo();

			if (inscripcion.getPersona().isTercero() == true) {
				sql = sql + " AND i.ter_codigo = " + inscripcion.getPersona().getId();
			} else if (inscripcion.getPersona().isTercero() == false) {
				sql = sql + " AND i.per_codigo = " + inscripcion.getPersona().getId();
			}
			System.out.println("Inscripcionnnnnn : " + sql);
			List<Inscripcion> listaInscripcionInscripcion = jdbcTemplate.query(sql, new RowMapper<Inscripcion>() {

				public Inscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
					Inscripcion inscripcion = new Inscripcion();
					inscripcion.setId(rs.getInt("ins_codigo"));
					return inscripcion;
				}
			});
			if (listaInscripcionInscripcion.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Inscripcion consultarOfertaSeleccionada(String oferta) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "SELECT tem_cod_oferta " + "FROM inscripciones.registroTemporal "
					+ "WHERE CONVERT(VARCHAR(255),HASHBYTES('SHA1', CONVERT(VARCHAR(255),tem_cod_oferta))) = 0x"
					+ oferta;
			List<Inscripcion> listaOfertaPreInscripcion = jdbcTemplate.query(sql, new RowMapper<Inscripcion>() {

				public Inscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
					Inscripcion inscripcion = new Inscripcion();
					Oferta oferta = new Oferta();
					oferta.setCodigo(rs.getInt("tem_cod_oferta"));
					inscripcion.setOferta(oferta);
					return inscripcion;
				}

			});
			if (listaOfertaPreInscripcion.size() > 0) {
				return listaOfertaPreInscripcion.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONRespuesta listaUsuariosInscritos(String search, long codigoOferta, int start, int length, int draw) {
		// TODO Auto-generated method stub
		JSONRespuesta respuesta = new JSONRespuesta();

		if (start == 0) {
			start = 1;
		}

		int fin = start + length - 1;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT COUNT(*) " + "FROM persona p "
				+ "INNER JOIN inscripcion i ON (p.per_codigo = i.per_codigo) "
				+ "INNER JOIN inscripcion_programa ip ON (i.ins_codigo = ip.ins_codigo) "
				+ "INNER JOIN oferta_academica o ON (ip.ofa_codigo = o.ofa_codigo) "
				+ "INNER JOIN programa pr ON (o.pro_codigo = pr.pro_codigo)"
				+ "WHERE pr.mod_codigo = 5 AND o.ofa_admision_automatica=1 ";

		if (codigoOferta > 0) {
			sql = sql + "AND o.ofa_codigo = " + codigoOferta;
		}

		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		int filtrados = count;

		if (search.length() > 0) {
			sql = sql + " AND p.per_nombre + ' ' + p.per_apellido like '%" + search + "%'";
			filtrados = jdbcTemplate.queryForObject(sql, Integer.class);
		}

		sql = "SELECT per_identificacion, per_nombre, per_apellido, pro_titulo_otorgado " + "FROM ("
				+ "SELECT row_number() over(order by p.per_nombre ASC) AS RowNumber, p.per_identificacion, "
				+ "p.per_nombre, p.per_apellido, pr.pro_titulo_otorgado " + "FROM persona p "
				+ "INNER JOIN inscripcion i ON (p.per_codigo = i.per_codigo) "
				+ "INNER JOIN inscripcion_programa ip ON (i.ins_codigo = ip.ins_codigo) "
				+ "INNER JOIN oferta_academica o ON (ip.ofa_codigo = o.ofa_codigo) "
				+ "INNER JOIN programa pr ON (o.pro_codigo = pr.pro_codigo) "
				+ "WHERE pr.mod_codigo = 5 AND o.ofa_admision_automatica=1 ";
		if (search.length() > 0) {
			sql = sql + " and p.per_nombre + ' ' + p.per_apellido like '%" + search + "%'";
		}

		if (codigoOferta > 0) {
			sql = sql + " AND o.ofa_codigo = " + codigoOferta;
		}

		sql = sql + ") as tabla";
		sql = sql + " where tabla.RowNumber between " + start + " and " + fin;

		List<PreInscripcion> listaUsu = jdbcTemplate.query(sql, new RowMapper<PreInscripcion>() {

			public PreInscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
				PreInscripcion usuariosPreinscritos = new PreInscripcion();

				Programa programa = new Programa();
				Oferta oferta = new Oferta();
				Persona persona = new Persona();

				persona.setIdentificacion(rs.getString("per_identificacion"));
				persona.setNombres(rs.getString("per_nombre"));
				persona.setApellidos(rs.getString("per_apellido"));
				programa.setNombre(rs.getString("pro_titulo_otorgado"));

				usuariosPreinscritos.setPersona(persona);
				oferta.setPrograma(programa);
				usuariosPreinscritos.setOferta(oferta);

				return usuariosPreinscritos;
			}

		});

		respuesta.setDraw(draw);
		respuesta.setRecordsFiltered(filtrados);
		respuesta.setRecordsTotal(count);
		respuesta.setData(listaUsu);

		return respuesta;
	}

	@Override
	public List<Programa> ListaProgramasInscripciones() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT o.ofa_codigo,p.pro_titulo_otorgado,u.uaa_nombre " + "FROM programa p "
				+ "INNER JOIN uaa u ON (p.uaa_codigo=u.uaa_codigo) "
				+ "INNER JOIN oferta_academica o ON (p.pro_codigo=o.pro_codigo) "
				+ "INNER JOIN inscripcion_programa ip ON (o.ofa_codigo = ip.ofa_codigo) "
				+ "INNER JOIN inscripcion i ON (ip.ins_codigo = i.ins_codigo) "
				+ "WHERE p.mod_codigo = 5 AND o.ofa_admision_automatica=1 " // 1-manual
																			// 0-Automatica
				+ "AND GETDATE() between o.ofa_fecha_inscripcion_inicio AND o.ofa_fecha_fin "
				+ "GROUP BY o.ofa_codigo,p.pro_titulo_otorgado,u.uaa_nombre";

		List<Programa> listaProgramas = jdbcTemplate.query(sql, new RowMapper<Programa>() {

			public Programa mapRow(ResultSet rs, int rowNum) throws SQLException {
				Programa programa = new Programa();
				programa.setCodigo(rs.getInt("ofa_codigo"));
				programa.setNombre(rs.getString("pro_titulo_otorgado"));
				programa.setNombreUaa(rs.getString("uaa_nombre"));
				return programa;
			}

		});

		return listaProgramas;
	}

	@Override
	public boolean validarInscritoPersona(int oferta, String identificacion) {
		// TODO Auto-generated method stub
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = " select i.ins_codigo from inscripcion i "
				+ " inner join inscripcion_programa inp on i.ins_codigo = inp.ins_codigo "
				+ " inner join persona p on i.per_codigo = p.per_codigo "
				+ " where inp.ofa_codigo = ? and p.per_identificacion =? ";
		System.out.println("VALIDACION INSCRITO:" + sql + " OFA:" + oferta + " IDENTIFICACION:" + identificacion);
		List<Inscripcion> listaInscripcion = jdbcTemplate.query(sql, new Object[] { oferta, identificacion },
				new RowMapper<Inscripcion>() {

					public Inscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
						Inscripcion inscripcion = new Inscripcion();
						inscripcion.setCodigo(rs.getInt("ins_codigo"));
						return inscripcion;
					}

				});

		if (listaInscripcion.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int constultarInscripcionCodigo(long codPersona) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "select top 1 ins_codigo from inscripcion where per_codigo =  ? order by ins_fecha desc";
			List<Inscripcion> listaInscripcion = jdbcTemplate.query(sql, new Object[] { codPersona }, new RowMapper<Inscripcion>() {

				public Inscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
					Inscripcion inscripcion = new Inscripcion();
					inscripcion.setId(rs.getInt("ins_codigo"));
					return inscripcion;
				}

			});
			if (listaInscripcion.size() > 0) {
				return listaInscripcion.get(0).getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
