package co.edu.usco.inscripciones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Departamento;
import co.edu.usco.inscripciones.model.Eps;
import co.edu.usco.inscripciones.model.EpsTipoAfiliacion;
import co.edu.usco.inscripciones.model.EstadoCivil;
import co.edu.usco.inscripciones.model.EstratoSocioeconomico;
import co.edu.usco.inscripciones.model.GrupoSanguineo;
import co.edu.usco.inscripciones.model.JSONRespuesta;
import co.edu.usco.inscripciones.model.LenguaNativa;
import co.edu.usco.inscripciones.model.Municipio;
import co.edu.usco.inscripciones.model.Oferta;
import co.edu.usco.inscripciones.model.Pais;
import co.edu.usco.inscripciones.model.Persona;
import co.edu.usco.inscripciones.model.PreInscripcion;
import co.edu.usco.inscripciones.model.Programa;
import co.edu.usco.inscripciones.model.TipoIdentificacion;
import co.edu.usco.inscripciones.utilidad.Constantes;
import co.edu.usco.librerias.EncriptarPalabra;
import co.edu.usco.librerias.TheadEnvioCorreo;

@Component
public class PreInscripcionDaoJDBCTemplateImpl implements PreIncripcionDao {

	@Autowired
	DataSource dataSource;

	@Autowired
	Constantes contantes;

	String cedulaIdentificacion;

	@Override
	public void agregarPreIncripcion(final PreInscripcion preInscripcion) {
		try {

			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			String cadenaFecha = formato.format(fechaActual);

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			final String sql = "INSERT INTO inscripciones.registroTemporal (tem_cod_oferta, tem_per_codigo, "
					+ "tem_ter_codigo, tem_fecha_registro, tem_identificacion, tem_nombres, "
					+ "tem_apellidos, tem_email) VALUES (?, ?, ?, convert(datetime,'" + cadenaFecha + "'), ?, ?, ?, ?)";

			System.out.println("OFERTA:" + preInscripcion.getOferta().getCodigo() + " TERCERO:"
					+ preInscripcion.getPersona().getId());
			System.out.println("ID" + preInscripcion.getPersona().getIdentificacion());
			System.out.println("NOMBRE" + preInscripcion.getPersona().getNombres().toUpperCase());

			KeyHolder keyHolder = new GeneratedKeyHolder();
			int resultado = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					pstm.setInt(1, preInscripcion.getOferta().getCodigo());

					int codPersona = 0;
					int codTercero = 0;
					if (preInscripcion.getPersona().isTercero() == true) {
						codTercero = preInscripcion.getPersona().getId();
					} else if (preInscripcion.getPersona().isTercero() == false) {
						codPersona = preInscripcion.getPersona().getId();
					}

					pstm.setInt(2, codPersona);
					pstm.setInt(3, codTercero);

					pstm.setString(4, preInscripcion.getPersona().getIdentificacion());
					if (preInscripcion.getPersona().getNombres() != null) {
						if (preInscripcion.getPersona().getNombres().length() > 50) {
							pstm.setString(5, preInscripcion.getPersona().getNombres().toUpperCase().substring(0, 50));
						} else {
							pstm.setString(5, preInscripcion.getPersona().getNombres().toUpperCase());
						}
					}
					if (preInscripcion.getPersona().getNombreCompleto() != null) {
						if (preInscripcion.getPersona().getNombreCompleto().length() > 50) {
							pstm.setString(5,
									preInscripcion.getPersona().getNombreCompleto().toUpperCase().substring(0, 50));
						} else {
							pstm.setString(5, preInscripcion.getPersona().getNombreCompleto().toUpperCase());
						}
					}
					if (preInscripcion.getPersona().getApellidos() != null) {
						pstm.setString(6, preInscripcion.getPersona().getApellidos().toUpperCase());
					} else {
						pstm.setString(6, "");
					}
					pstm.setString(7, preInscripcion.getPersona().getEmail());
					return pstm;
				}
			}, keyHolder);

			if (resultado > 0) {
				preInscripcion.setId(keyHolder.getKey().intValue());

				final String codigo;
				codigo = EncriptarPalabra.SHA1(Integer.toString(preInscripcion.getId()));

				final String oferta;
				oferta = EncriptarPalabra.SHA1(Integer.toString(preInscripcion.getOferta().getCodigo()));

				String destinatario = preInscripcion.getPersona().getEmail();
				System.out.println("El DESTINATARIO ES: " + destinatario);
				String footer = "NOTA CONFIDENCIAL:<br>"
						+ "Este mensaje es exclusivamente para el uso de la preInscripcion o entidad a quien está dirigido; La información contenida en este e-mail y en todos sus archivos anexos es completamente confidencial, de igual manera las opiniones expresadas son netamente preInscripcionles, y no necesariamente transmiten el pensamiento de la Universidad Surcolombiana. Si usted no es el destinatario por favor háganoslo saber respondiendo a este correo y por favor destruya cualquier copia del mismo y sus adjuntos, cualquier almacenamiento, distribución, divulgación o copia de este mensaje está estrictamente prohibida y sancionada por la ley."
						+ "Si por error recibe este mensaje, ofrecemos disculpas.<br><br>" + "CONFIDENTIAL NOTE:<br>"
						+ "This message is exclusively for  use of the individual or entity to whom it is forwarded.  The information of this e-mail and all its attachments is completely confidential; likewise, the opinions expressed are purely preInscripcionl and they do not necessarily convey the thought of  Surcolombiana University.  If you are not the addressee, please let us know it by replying to this e-mail and please delete any copy and its attachments.  Any storage, distribution, dissemination or copy of this information is strictly prohibited and punished  by law.";
				String asunto = "Confirmar correo - Inscripciones Universidad Surcolombiana";
				String mensaje = "Cordial saludo ";

				if (preInscripcion.getPersona().getNombreCompleto() == null) {
					mensaje = mensaje + preInscripcion.getPersona().getNombres().toUpperCase() + " "
							+ preInscripcion.getPersona().getApellidos().toUpperCase();
				} else {
					mensaje = mensaje + preInscripcion.getPersona().getNombreCompleto().toUpperCase();
				}
				mensaje = mensaje + ",<br><br>Para poder continuar con el proceso de "
						+ "inscripción debe confirmar su correo electronico, haciendo clic en el siguiente "
						+ "enlace: <a href='" + contantes.RUTA_PORTAL + "/preInscripcionValidar/" + codigo + "/"
						+ oferta + "/'>Aquí</a><br><br>" + footer;
				new TheadEnvioCorreo(destinatario, asunto, mensaje, "").start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public PreInscripcion validarPreInscripcion(String identificacion, int oferta) {
		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = " SELECT tem_codigo, tem_fecha_registro, tem_email_verificado "
					+ " FROM inscripciones.registroTemporal " + " WHERE tem_cod_oferta = " + oferta
					+ " AND tem_identificacion = '" + identificacion + "'";
			List<PreInscripcion> listaPreInscripcion = jdbcTemplate.query(sql, new RowMapper<PreInscripcion>() {

				public PreInscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
					PreInscripcion preInscripcion = new PreInscripcion();
					preInscripcion.setId(rs.getInt("tem_codigo"));
					preInscripcion.setFechaRegistro(rs.getDate("tem_fecha_registro"));
					preInscripcion.setEmailVerificado(rs.getInt("tem_email_verificado"));
					return preInscripcion;
				}
			});
			if (listaPreInscripcion.size() > 0) {
				return listaPreInscripcion.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PreInscripcion consultarPreInscripcion(PreInscripcion preInscripcion) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT tem_codigo " + "FROM inscripciones.registroTemporal " + "WHERE tem_identificacion = '"
					+ preInscripcion.getPersona().getIdentificacion() + "'" + "AND tem_cod_oferta = "
					+ preInscripcion.getOferta().getCodigo();

			List<PreInscripcion> listaPreInscripcion = jdbcTemplate.query(sql, new RowMapper<PreInscripcion>() {

				public PreInscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
					PreInscripcion preInscripcion = new PreInscripcion();
					preInscripcion.setCodigo(rs.getInt("tem_codigo"));
					return preInscripcion;
				}
			});
			if (listaPreInscripcion.size() > 0) {
				return listaPreInscripcion.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PreInscripcion consultarDatosPreInscripcion(String codigoTemporal) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT u.uaa_codigo,rt.tem_cod_oferta, rt.tem_codigo, rt.tem_identificacion, rt.tem_nombres, rt.tem_apellidos, "
					+ "rt.tem_email, rt.tem_email_verificado, rt.tem_fecha_registro, rt.tem_nombres, rt.tem_apellidos, rt.tem_autorizado, "
					+ "p.per_codigo, p.tii_codigo, p.per_genero, p.per_telefono_movil, p.per_estado_civil, p.per_barrio, p.per_id_cotizante, "
					+ "p.grs_codigo, p.per_direccion_residencia, pRP.pai_acronimo, p.per_identificacion,"
					+ "p.eps_codigo, p.eta_codigo, p.per_estrato, p.per_pais_residencia, p.per_departamento_residencia, "
					+ "p.per_lugar_residencia, mLR.mun_nombre AS municipio_recidencia, p.per_lugar_expedicion, p.per_lugar_nacimiento, p.per_fecha_expedicion, "
					+ "p.per_fecha_nacimiento, p.lenguaNativa, pr.pro_estado, "
					+ "mLE.dep_codigo AS depLE, dLE.pai_codigo AS paisLE, mLN.dep_codigo AS depLN, dLN.pai_codigo AS paisLN "
					+ "FROM inscripciones.registroTemporal rt "
					+ "INNER JOIN persona p ON rt.tem_per_codigo = p.per_codigo "
					+ "LEFT JOIN municipio mLE ON p.per_lugar_expedicion = mLE.mun_codigo "
					+ "LEFT JOIN municipio mLR ON p.per_lugar_residencia = mLR.mun_codigo "
					+ "LEFT JOIN departamento dLE ON mLE.dep_codigo = dLE.dep_codigo "
					+ "LEFT JOIN municipio mLN ON p.per_lugar_nacimiento = mLN.mun_codigo "
					+ "LEFT JOIN departamento dLN ON mLN.dep_codigo = dLN.dep_codigo "
					+ "LEFT JOIN pais pRP ON p.per_pais_residencia = pRP.pai_codigo "
					+ "INNER JOIN oferta_academica o ON (rt.tem_cod_oferta = o.ofa_codigo) "
					+ "INNER JOIN programa pr ON (o.pro_codigo = pr.pro_codigo) "
					+ "INNER JOIN uaa u ON (pr.uaa_codigo = u.uaa_codigo) "
					+ "WHERE CONVERT(VARCHAR(255),HASHBYTES('SHA1', CONVERT(VARCHAR(255),rt.tem_codigo))) = 0x"
					+ codigoTemporal;
			System.out.println("Pre per: " + sql);
			List<PreInscripcion> listaPreInscripcion = jdbcTemplate.query(sql, new RowMapper<PreInscripcion>() {

				public PreInscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
					PreInscripcion preInscripcion = new PreInscripcion();
					preInscripcion.setCodigo(rs.getInt("tem_codigo"));
					preInscripcion.setEmailVerificado(rs.getInt("tem_email_verificado"));
					preInscripcion.setFechaRegistro(rs.getDate("tem_fecha_registro"));
					preInscripcion.setAutorizado(rs.getInt("tem_autorizado"));

					Oferta oferta = new Oferta();
					oferta.setCodigo(rs.getInt("tem_cod_oferta"));
					preInscripcion.setOferta(oferta);

					Persona persona = new Persona();
					persona.setId(rs.getInt("per_codigo"));
					persona.setGenero(rs.getString("per_genero"));
					persona.setIdentificacion(rs.getString("tem_identificacion"));
					persona.setIdentificacionProveedor(rs.getString("per_identificacion"));
					persona.setNombres(rs.getString("tem_nombres"));
					persona.setApellidos(rs.getString("tem_apellidos"));
					persona.setEmail(rs.getString("tem_email"));
					persona.setDireccion(rs.getString("per_direccion_residencia"));
					persona.setTelefonoMovil(rs.getString("per_telefono_movil"));
					persona.setBarrio(rs.getString("per_barrio"));
					// persona.setIdentificacionCotizante(rs.getLong("per_id_cotizante"));
					persona.setFechaExpedicion(rs.getDate("per_fecha_expedicion"));
					persona.setFechaNacimiento(rs.getDate("per_fecha_nacimiento"));
					persona.setTercero(false);

					if (persona.getFechaExpedicion() != null) {
						persona.setFechaExpedicionLong(persona.getFechaExpedicion().getTime());
					}

					/*
					 * if (persona.getFechaNacimiento() != null) {
					 * persona.setFechaNacimientoLong(persona.getFechaNacimiento ().getTime()); }
					 */

					TipoIdentificacion tid = new TipoIdentificacion();
					tid.setCodigo(rs.getInt("tii_codigo"));
					EstadoCivil estadoCivil = new EstadoCivil();
					estadoCivil.setCodigo(rs.getInt("per_estado_civil"));
					GrupoSanguineo grupoSanguineo = new GrupoSanguineo();
					grupoSanguineo.setCodigo(rs.getInt("grs_codigo"));
					Eps eps = new Eps();
					eps.setCodigo(rs.getInt("eps_codigo"));

					EpsTipoAfiliacion tipoAfiliacion = new EpsTipoAfiliacion();
					tipoAfiliacion.setCodigo(rs.getInt("eta_codigo"));

					EstratoSocioeconomico estrato = new EstratoSocioeconomico();
					estrato.setCodigo(rs.getInt("per_estrato"));
					Pais lugarResidenciaPais = new Pais();
					lugarResidenciaPais.setCodigo(rs.getInt("per_pais_residencia"));
					lugarResidenciaPais.setAcronimo(rs.getString("pai_acronimo"));

					Departamento lugarResidenciaDepartamento = new Departamento();
					lugarResidenciaDepartamento.setCodigo(rs.getInt("per_departamento_residencia"));
					Municipio lugarResidencia = new Municipio();
					lugarResidencia.setCodigo(rs.getInt("per_lugar_residencia"));
					lugarResidencia.setNombre(rs.getString("municipio_recidencia"));

					Municipio lugarNacimiento = new Municipio();
					lugarNacimiento.setCodigo(rs.getInt("per_lugar_nacimiento"));
					Departamento departamentoLN = new Departamento();
					departamentoLN.setCodigo(rs.getInt("depLN"));
					Pais paisLN = new Pais();
					paisLN.setCodigo(rs.getInt("paisLN"));
					departamentoLN.setPais(paisLN);
					lugarNacimiento.setDepartamento(departamentoLN);

					Municipio lugarExpedicion = new Municipio();
					lugarExpedicion.setCodigo(rs.getInt("per_lugar_expedicion"));
					Departamento departamentoLE = new Departamento();
					departamentoLE.setCodigo(rs.getInt("depLE"));
					Pais paisLE = new Pais();
					paisLE.setCodigo(rs.getInt("paisLE"));
					departamentoLE.setPais(paisLE);
					lugarExpedicion.setDepartamento(departamentoLE);

					LenguaNativa lenguaNativa = new LenguaNativa();
					lenguaNativa.setCodigo(rs.getInt("lenguaNativa"));

					Programa programa = new Programa();
					programa.setEstado(rs.getString("pro_estado"));
					programa.setCodigoUaa(rs.getInt("uaa_codigo"));
					oferta.setPrograma(programa);

					persona.setTipoIdentificacion(tid);
					persona.setEstadoCivil(estadoCivil);
					persona.setGrupoSanguineo(grupoSanguineo);
					persona.setEps(eps);
					persona.setTipoAfiliacion(tipoAfiliacion);
					persona.setEstrato(estrato);
					persona.setLugarResidenciaPais(lugarResidenciaPais);
					persona.setLugarResidenciaDepartamento(lugarResidenciaDepartamento);
					persona.setLugarResidencia(lugarResidencia);
					persona.setLugarExpedicion(lugarExpedicion);
					persona.setLugarNacimiento(lugarNacimiento);
					persona.setLenguaNativa(lenguaNativa);

					preInscripcion.setOferta(oferta);
					preInscripcion.setPersona(persona);
					return preInscripcion;
				}
			});

			if (listaPreInscripcion.size() > 0) {
				return listaPreInscripcion.get(0);
			} else {
				return consultarDatosPreInscripcionTercero(codigoTemporal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PreInscripcion convertirTerceroEnPersona(String cedula, String codigoTemporal) {
		System.out.println("CEDULA;" + cedula);
		System.out.println("TEMPORAL: " + codigoTemporal);
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sqlTercero = "UPDATE tercero set";
			sqlTercero += " ter_email = (SELECT TOP 1 tem_email FROM inscripciones.registroTemporal WHERE tem_identificacion = '"
					+ cedula + "' ORDER BY tem_codigo desc),";
			sqlTercero += " ter_nombre = (SELECT TOP 1 (tem_nombres+' '+tem_apellidos) as nombre FROM inscripciones.registroTemporal WHERE tem_identificacion = '"
					+ cedula + "' ORDER BY tem_codigo desc)";
			sqlTercero += " WHERE ter_identificacion = '" + cedula + "'";
			int resultadoUpdateTercero = jdbcTemplate.update(sqlTercero);

			String sql = "EXEC CrearPersonaContratacion '" + cedula + "'";
			int resultado = jdbcTemplate.update(sql);
			System.out.println("Resultado es:" + resultado);
			if (resultado > 0) {

				String sqlUpdate = "UPDATE inscripciones.registroTemporal SET "
						+ " tem_per_codigo = (SELECT TOP 1 per_codigo FROM persona WHERE per_identificacion = '"
						+ cedula + "')"
						+ " WHERE CONVERT(VARCHAR(255),HASHBYTES('SHA1', CONVERT(VARCHAR(255),tem_codigo))) = 0x"
						+ codigoTemporal;

				int resultadoUpdate = jdbcTemplate.update(sqlUpdate);

				return consultarDatosPreInscripcion(codigoTemporal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.toString());
		}
		return null;
	}

	@Override
	public PreInscripcion consultarDatosPreInscripcionTercero(String codigoTemporal) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "SELECT u.uaa_codigo,rt.tem_cod_oferta, rt.tem_codigo, rt.tem_identificacion, rt.tem_nombres, rt.tem_apellidos, rt.tem_autorizado,  "
					+ "rt.tem_email, rt.tem_email_verificado, rt.tem_fecha_registro, t.ter_genero , t.ter_direccion, "
					+ "t.ter_codigo, t.tii_codigo, t.ter_telefono_movil, t.ter_estado_civil , t.ter_barrio, t.ter_id_cotizante, t.grs_codigo, "
					+ "t.eps_codigo, t.eta_codigo, t.ter_estrato, t.ter_pais_residencia, t.ter_departamento_residencia, "
					+ "t.ter_lugar_residencia, t.ter_lugar_expedicion, t.ter_lugar_nacimiento, t.ter_fecha_expedicion, "
					+ "t.ter_fecha_nacimiento, t.lenguaNativa, pr.pro_estado, t.ter_identificacion,"
					+ "mLE.dep_codigo AS depLE, dLE.pai_codigo AS paisLE, mLN.dep_codigo AS depLN, dLN.pai_codigo AS paisLN "
					+ "FROM inscripciones.registroTemporal rt "
					+ "INNER JOIN tercero t ON rt.tem_ter_codigo = t.ter_codigo "
					+ "LEFT JOIN municipio mLE ON t.ter_lugar_expedicion = mLE.mun_codigo "
					+ "LEFT JOIN departamento dLE ON mLE.dep_codigo = dLE.dep_codigo "
					+ "LEFT JOIN municipio mLN ON t.ter_lugar_nacimiento = mLN.mun_codigo "
					+ "LEFT JOIN departamento dLN ON mLN.dep_codigo = dLN.dep_codigo "
					+ "INNER JOIN oferta_academica o ON (rt.tem_cod_oferta = o.ofa_codigo) "
					+ "INNER JOIN programa pr ON (o.pro_codigo = pr.pro_codigo) "
					+ "INNER JOIN uaa u ON (pr.uaa_codigo = u.uaa_codigo) "
					+ "WHERE CONVERT(VARCHAR(255),HASHBYTES('SHA1', CONVERT(VARCHAR(255),rt.tem_codigo))) = 0x"
					+ codigoTemporal;
			System.out.println("iNS tER: " + sql);
			List<PreInscripcion> listaPreInscripcion = jdbcTemplate.query(sql, new RowMapper<PreInscripcion>() {

				public PreInscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
					cedulaIdentificacion = rs.getString("ter_identificacion");

					PreInscripcion preInscripcion = new PreInscripcion();
					preInscripcion.setCodigo(rs.getInt("tem_codigo"));
					preInscripcion.setEmailVerificado(rs.getInt("tem_email_verificado"));
					preInscripcion.setFechaRegistro(rs.getDate("tem_fecha_registro"));
					preInscripcion.setAutorizado(rs.getInt("tem_autorizado"));

					Persona persona = new Persona();
					persona.setIdentificacionProveedor(rs.getString("ter_identificacion"));

					persona.setId(rs.getInt("ter_codigo"));
					persona.setGenero(rs.getString("ter_genero"));
					persona.setIdentificacion(rs.getString("tem_identificacion"));

					persona.setNombres(rs.getString("tem_nombres"));
					persona.setApellidos(rs.getString("tem_apellidos"));
					persona.setEmail(rs.getString("tem_email"));
					persona.setDireccion(rs.getString("ter_direccion"));
					persona.setTelefonoMovil(rs.getString("ter_telefono_movil"));
					persona.setBarrio(rs.getString("ter_barrio"));
					persona.setIdentificacionCotizante(rs.getLong("ter_id_cotizante"));
					persona.setFechaExpedicion(rs.getDate("ter_fecha_expedicion"));
					persona.setFechaNacimiento(rs.getDate("ter_fecha_nacimiento"));
					persona.setTercero(true);

					if (persona.getFechaExpedicion() != null) {
						persona.setFechaExpedicionLong(persona.getFechaExpedicion().getTime());
					}
					if (persona.getFechaNacimiento() != null) {
						persona.setFechaNacimientoLong(persona.getFechaNacimiento().getTime());
					}
					TipoIdentificacion tid = new TipoIdentificacion();
					tid.setCodigo(rs.getInt("tii_codigo"));
					EstadoCivil estadoCivil = new EstadoCivil();
					estadoCivil.setCodigo(rs.getInt("ter_estado_civil"));
					GrupoSanguineo grupoSanguineo = new GrupoSanguineo();
					grupoSanguineo.setCodigo(rs.getInt("grs_codigo"));
					Eps eps = new Eps();
					eps.setCodigo(rs.getInt("eps_codigo"));
					EpsTipoAfiliacion tipoAfiliacion = new EpsTipoAfiliacion();
					tipoAfiliacion.setCodigo(rs.getInt("eta_codigo"));
					EstratoSocioeconomico estrato = new EstratoSocioeconomico();
					estrato.setCodigo(rs.getInt("ter_estrato"));
					Pais lugarResidenciaPais = new Pais();
					lugarResidenciaPais.setCodigo(rs.getInt("ter_pais_residencia"));
					Departamento lugarResidenciaDepartamento = new Departamento();
					lugarResidenciaDepartamento.setCodigo(rs.getInt("ter_departamento_residencia"));
					Municipio lugarResidencia = new Municipio();
					lugarResidencia.setCodigo(rs.getInt("ter_lugar_residencia"));

					Municipio lugarNacimiento = new Municipio();
					lugarNacimiento.setCodigo(rs.getInt("ter_lugar_nacimiento"));
					Departamento departamentoLN = new Departamento();
					departamentoLN.setCodigo(rs.getInt("depLN"));
					Pais paisLN = new Pais();
					paisLN.setCodigo(rs.getInt("paisLN"));
					departamentoLN.setPais(paisLN);
					lugarNacimiento.setDepartamento(departamentoLN);

					Municipio lugarExpedicion = new Municipio();
					lugarExpedicion.setCodigo(rs.getInt("ter_lugar_expedicion"));
					Departamento departamentoLE = new Departamento();
					departamentoLE.setCodigo(rs.getInt("depLE"));
					Pais paisLE = new Pais();
					paisLE.setCodigo(rs.getInt("paisLE"));
					departamentoLE.setPais(paisLE);
					lugarExpedicion.setDepartamento(departamentoLE);

					LenguaNativa lenguaNativa = new LenguaNativa();
					lenguaNativa.setCodigo(rs.getInt("lenguaNativa"));
					Programa programa = new Programa();
					programa.setEstado(rs.getString("pro_estado"));
					programa.setCodigoUaa(rs.getInt("uaa_codigo"));

					Oferta oferta = new Oferta();
					oferta.setCodigo(rs.getInt("tem_cod_oferta"));
					oferta.setPrograma(programa);

					persona.setTipoIdentificacion(tid);
					persona.setEstadoCivil(estadoCivil);
					persona.setGrupoSanguineo(grupoSanguineo);
					persona.setEps(eps);
					persona.setTipoAfiliacion(tipoAfiliacion);
					persona.setEstrato(estrato);
					persona.setLugarResidenciaPais(lugarResidenciaPais);
					persona.setLugarResidenciaDepartamento(lugarResidenciaDepartamento);
					persona.setLugarResidencia(lugarResidencia);
					persona.setLugarExpedicion(lugarExpedicion);
					persona.setLugarNacimiento(lugarNacimiento);
					persona.setLenguaNativa(lenguaNativa);

					preInscripcion.setOferta(oferta);
					preInscripcion.setPersona(persona);

					return preInscripcion;
				}
			});
			if (listaPreInscripcion.size() > 0) {
				return listaPreInscripcion.get(0);
				// return convertirTerceroEnPersona(cedulaIdentificacion, codigoTemporal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean validarCorreo(PreInscripcion preInscripcion, String hashCorreo, String oferta) {
		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "UPDATE inscripciones.registroTemporal SET tem_email_verificado=1 "
					+ "WHERE CONVERT(VARCHAR(255),HASHBYTES('SHA1', CONVERT(VARCHAR(255),tem_codigo))) = 0x"
					+ hashCorreo;

			int resultado = jdbcTemplate.update(sql);

			if (resultado > 0) {
				String mensaje = "";
				String destinatario = preInscripcion.getPersona().getEmail();
				String footer = "NOTA CONFIDENCIAL:<br>"
						+ "Este mensaje es exclusivamente para el uso de la preInscripcion o entidad a quien está dirigido; La información contenida en este e-mail y en todos sus archivos anexos es completamente confidencial, de igual manera las opiniones expresadas son netamente preInscripcionles, y no necesariamente transmiten el pensamiento de la Universidad Surcolombiana. Si usted no es el destinatario por favor háganoslo saber respondiendo a este correo y por favor destruya cualquier copia del mismo y sus adjuntos, cualquier almacenamiento, distribución, divulgación o copia de este mensaje está estrictamente prohibida y sancionada por la ley."
						+ "Si por error recibe este mensaje, ofrecemos disculpas.<br><br>" + "CONFIDENTIAL NOTE:<br>"
						+ "This message is exclusively for  use of the individual or entity to whom it is forwarded.  The information of this e-mail and all its attachments is completely confidential; likewise, the opinions expressed are purely preInscripcionl and they do not necessarily convey the thought of  Surcolombiana University.  If you are not the addressee, please let us know it by replying to this e-mail and please delete any copy and its attachments.  Any storage, distribution, dissemination or copy of this information is strictly prohibited and punished  by law.";
				String asunto = "Correo confirmado exitosamente - Inscripciones Universidad Surcolombiana";
				if (preInscripcion.getOferta().getPrograma().getEstado().equals("06")) {
					mensaje = "Cordial saludo " + preInscripcion.getPersona().getNombres().toUpperCase() + " "
							+ preInscripcion.getPersona().getApellidos().toUpperCase() + ",<br><br> "
							+ "Este curso se encuentra en estado de Pre-Inscripciones, se le enviará un correo electrónico "
							+ "informando que el curso se encuentra habilitado para las inscripciones."
							+ "<br><br>Gracias por su Pre-Inscripción. ";
				} else {
					mensaje = "Cordial saludo " + preInscripcion.getPersona().getNombres().toUpperCase() + " "
							+ preInscripcion.getPersona().getApellidos().toUpperCase()
							+ ",<br><br>Para poder continuar con el proceso de "
							+ "inscripción debe ingresar en el siguiente " + "enlace: <a href='" + contantes.RUTA_PORTAL
							+ "/inscripcion?codigo=" + oferta + "&id=" + hashCorreo + "'>Inscribirme</a><br><br>"
							+ footer;
				}
				new TheadEnvioCorreo(destinatario, asunto, mensaje, "").start();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<PreInscripcion> listarPreInscripciones() {
		// @RolesAllowed("INVITADO")
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT tem_codigo, tem_identificacion, tem_nombres, tem_apellidos, tem_email "
				+ "FROM inscripciones.registroTemporal " + "WHERE len(tem_nombres) > 0 ORDER BY tem_nombres";
		List<PreInscripcion> listaPreInscripcion = jdbcTemplate.query(sql, new RowMapper<PreInscripcion>() {

			public PreInscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
				PreInscripcion preInscripcion = new PreInscripcion();

				preInscripcion.setCodigo(rs.getInt("tem_codigo"));
				Persona persona = new Persona();
				persona.setIdentificacion(rs.getString("tem_identificacion"));
				persona.setNombreCompleto(rs.getString("tem_nombres") + " " + rs.getString("tem_apellidos"));
				persona.setNombres(rs.getString("tem_nombres"));
				persona.setApellidos(rs.getString("tem_apellidos"));
				persona.setEmail(rs.getString("tem_email"));
				preInscripcion.setPersona(persona);
				return preInscripcion;
			}
		});

		return listaPreInscripcion;
	}

	@Override
	public JSONRespuesta listarPreInscripcion1(String search, long codigoOferta, int start, int length, int draw) {
		// TODO Auto-generated method stub
		JSONRespuesta respuesta = new JSONRespuesta();

		if (start == 0) {
			start = 1;
		}

		int fin = start + length - 1;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT count(*) FROM inscripciones.registroTemporal rt "
				+ "INNER JOIN oferta_academica o ON (rt.tem_cod_oferta = o.ofa_codigo) "
				+ "INNER JOIN programa p ON (o.ofa_codigo = p.pro_codigo) "
				+ "WHERE rt.tem_email_verificado = 1 AND p.pro_estado = '06' ";

		if (codigoOferta > 0) {
			sql = sql + "AND o.ofa_codigo = " + codigoOferta;
		}

		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		int filtrados = count;

		if (search.length() > 0) {
			sql = sql + " AND rt.tem_nombres + ' ' + rt.tem_apellidos like '%" + search + "%'";
			filtrados = jdbcTemplate.queryForObject(sql, Integer.class);
		}

		sql = "SELECT tem_identificacion, tem_nombres, tem_apellidos, pro_titulo_otorgado";
		sql = sql + " FROM (select row_number() over(order by rt.tem_identificacion ASC) AS RowNumber,";
		sql = sql + " rt.tem_identificacion, rt.tem_nombres, rt.tem_apellidos, p.pro_titulo_otorgado";
		sql = sql + " FROM inscripciones.registroTemporal rt ";
		sql = sql + "INNER JOIN oferta_academica o ON (rt.tem_cod_oferta = o.ofa_codigo) ";
		sql = sql + "INNER JOIN programa p ON (o.pro_codigo = p.pro_codigo) ";
		sql = sql + "WHERE rt.tem_email_verificado = 1 AND p.pro_estado = '06' ";
		if (search.length() > 0) {
			sql = sql + " AND rt.tem_nombres + ' ' + rt.tem_apellidos like '%" + search + "%'";
		}

		if (codigoOferta > 0) {
			sql = sql + " AND o.ofa_codigo = " + codigoOferta;
		}

		sql = sql + ") AS tabla";
		sql = sql + " WHERE tabla.RowNumber between " + start + " AND " + fin;

		List<PreInscripcion> listaUsu = jdbcTemplate.query(sql, new RowMapper<PreInscripcion>() {

			public PreInscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
				PreInscripcion usuariosPreinscritos = new PreInscripcion();

				Programa programa = new Programa();
				Oferta oferta = new Oferta();
				Persona persona = new Persona();

				persona.setIdentificacion(rs.getString("tem_identificacion"));
				persona.setNombres(rs.getString("tem_nombres"));
				persona.setApellidos(rs.getString("tem_apellidos"));
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
	public List<Programa> ListaProgramasPreInscripcion() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT o.ofa_codigo,p.pro_titulo_otorgado,u.uaa_nombre " + "FROM programa p "
				+ "INNER JOIN uaa u ON (p.uaa_codigo=u.uaa_codigo) "
				+ "INNER JOIN oferta_academica o ON (p.pro_codigo=o.pro_codigo) "
				+ "INNER JOIN inscripciones.registroTemporal rt ON (o.ofa_codigo = rt.tem_cod_oferta) "
				+ "WHERE p.mod_codigo = 5 AND rt.tem_email_verificado = 1 AND p.pro_estado = '06' "
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
	public PreInscripcion consultarPreinscripcion(int uaa, int persona, int tercero) {
		// TODO Auto-generated method stub
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			String sql = "SELECT TOP 1 rt.tem_cod_oferta, rt.tem_codigo, rt.tem_nombres,rt.tem_apellidos";
			sql = sql + " FROM inscripciones.registroTemporal rt";
			sql = sql + " LEFT OUTER JOIN dbo.tercero AS t ON t.ter_codigo = rt.tem_ter_codigo";
			sql = sql + " LEFT OUTER JOIN dbo.persona AS p ON p.per_codigo = rt.tem_per_codigo";
			sql = sql + " INNER JOIN oferta_academica o ON (rt.tem_cod_oferta = o.ofa_codigo)";
			sql = sql + " INNER JOIN programa pr ON (o.pro_codigo = pr.pro_codigo)";
			sql = sql + " INNER JOIN uaa u ON (pr.uaa_codigo = u.uaa_codigo)";
			sql = sql + " WHERE u.uaa_codigo = " + uaa;

			if (persona > 0) {
				sql = sql + " AND p.per_codigo = " + persona;
			}

			if (tercero > 0) {
				sql = sql + " AND t.ter_codigo = " + tercero;
			}
			sql = sql + " ORDER BY rt.tem_codigo DESC";

			List<PreInscripcion> listaPreInscripcion = jdbcTemplate.query(sql, new RowMapper<PreInscripcion>() {

				public PreInscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {

					PreInscripcion preInscripcion = new PreInscripcion();
					preInscripcion.setCodigo(rs.getInt("tem_codigo"));

					Oferta oferta = new Oferta();
					oferta.setCodigo(rs.getInt("tem_cod_oferta"));
					preInscripcion.setOferta(oferta);

					return preInscripcion;
				}
			});

			if (listaPreInscripcion.size() > 0) {
				return listaPreInscripcion.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean validarCorreoProveedor(PreInscripcion preInscripcion, String hashCorreo, String oferta) {
		// TODO Auto-generated method stub
		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "UPDATE inscripciones.registroTemporal SET tem_email_verificado=1 "
					+ "WHERE CONVERT(VARCHAR(255),HASHBYTES('SHA1', CONVERT(VARCHAR(255),tem_codigo))) = 0x"
					+ hashCorreo;

			int resultado = jdbcTemplate.update(sql);

			/*
			 * if (resultado > 0) { String mensaje = ""; String destinatario =
			 * preInscripcion.getPersona().getEmail();
			 * 
			 * if (preInscripcion.getPersona().getEmailInterno() != null) { destinatario =
			 * "," + preInscripcion.getPersona().getEmailInterno(); }
			 * 
			 * String footer = "NOTA CONFIDENCIAL:<br>" +
			 * "Este mensaje es exclusivamente para el uso de la preInscripcion o entidad a quien está dirigido; La información contenida en este e-mail y en todos sus archivos anexos es completamente confidencial, de igual manera las opiniones expresadas son netamente preInscripcionles, y no necesariamente transmiten el pensamiento de la Universidad Surcolombiana. Si usted no es el destinatario por favor háganoslo saber respondiendo a este correo y por favor destruya cualquier copia del mismo y sus adjuntos, cualquier almacenamiento, distribución, divulgación o copia de este mensaje está estrictamente prohibida y sancionada por la ley."
			 * + "Si por error recibe este mensaje, ofrecemos disculpas.<br><br>" +
			 * "CONFIDENTIAL NOTE:<br>" +
			 * "This message is exclusively for  use of the individual or entity to whom it is forwarded.  The information of this e-mail and all its attachments is completely confidential; likewise, the opinions expressed are purely preInscripcionl and they do not necessarily convey the thought of  Surcolombiana University.  If you are not the addressee, please let us know it by replying to this e-mail and please delete any copy and its attachments.  Any storage, distribution, dissemination or copy of this information is strictly prohibited and punished  by law."
			 * ; String asunto =
			 * "Correo confirmado exitosamente - Inscripciones Universidad Surcolombiana" ;
			 * 
			 * mensaje = "Cordial saludo "; if
			 * (preInscripcion.getPersona().getNombreCompleto() == null) { mensaje = mensaje
			 * + preInscripcion.getPersona().getNombres().toUpperCase() + " " +
			 * preInscripcion.getPersona().getApellidos().toUpperCase() + ",<br><br> "; }
			 * else { mensaje = mensaje +
			 * preInscripcion.getPersona().getNombreCompleto().toUpperCase() + ",<br><br> ";
			 * }
			 * 
			 * mensaje = mensaje + "Para poder continuar con el proceso de " +
			 * "inscripción debe ingresar en el siguiente " + "enlace: <a href='" +
			 * contantes.RUTA_PORTAL + "/inscripcion?codigo=" + oferta + "&id=" + hashCorreo
			 * + "'>Inscribirme</a><br><br>" + footer;
			 * 
			 * new TheadEnvioCorreo(destinatario, asunto, mensaje, "").start(); return true;
			 * }
			 */
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
