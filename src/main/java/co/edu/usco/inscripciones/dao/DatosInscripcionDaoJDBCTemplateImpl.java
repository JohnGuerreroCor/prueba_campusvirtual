package co.edu.usco.inscripciones.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.edu.usco.inscripciones.model.Departamento;
import co.edu.usco.inscripciones.model.Eps;
import co.edu.usco.inscripciones.model.EpsTipoAfiliacion;
import co.edu.usco.inscripciones.model.EstadoCivil;
import co.edu.usco.inscripciones.model.EstratoSocioeconomico;
import co.edu.usco.inscripciones.model.GrupoSanguineo;
import co.edu.usco.inscripciones.model.InstitucionEducativa;
import co.edu.usco.inscripciones.model.LenguaNativa;
import co.edu.usco.inscripciones.model.Municipio;
import co.edu.usco.inscripciones.model.NivelAcademico;
import co.edu.usco.inscripciones.model.Pais;
import co.edu.usco.inscripciones.model.TipoIdentificacion;

@Component
public class DatosInscripcionDaoJDBCTemplateImpl implements DatosInscripcionDao{
	
	@Autowired
	DataSource dataSource;
	@Override
	public List<Eps> crearListaEps() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 

		String sql = "SELECT eps_codigo, UPPER(eps_nombre) AS eps_nombre "
					+ "FROM eps "
					+ "WHERE len(eps_nombre) > 0 ORDER BY eps_nombre";

		List<Eps> listaEps = jdbcTemplate.query(sql, new RowMapper<Eps>() {

			public Eps mapRow(ResultSet rs, int rowNum) throws SQLException {
				Eps eps = new Eps();
				
				eps.setCodigo(rs.getInt("eps_codigo"));
				eps.setNombre(rs.getString("eps_nombre"));
				
				return eps;
			}
		});

		return listaEps;
	}
	@Override
	public List<EpsTipoAfiliacion> crearListaEpsTipoAfiliacion() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 

		String sql = "SELECT eta_codigo, UPPER(eta_nombre) AS eta_nombre "
				+ "FROM eps_tipo_afiliacion "
				+ "WHERE len(eta_nombre) > 0 AND eta_nombre != 'desconocido' "
				+ "ORDER BY eta_nombre";

		List<EpsTipoAfiliacion> listaEpsTipoAfiliacion = jdbcTemplate.query(sql, new RowMapper<EpsTipoAfiliacion>() {

			public EpsTipoAfiliacion mapRow(ResultSet rs, int rowNum) throws SQLException {
				EpsTipoAfiliacion epst = new EpsTipoAfiliacion();
				
				epst.setCodigo(rs.getInt("eta_codigo"));
				epst.setNombre(rs.getString("eta_nombre"));
				
				return epst;
			}
		});

		return listaEpsTipoAfiliacion;
	}
	@Override
	public List<GrupoSanguineo> crearListaGrupoSanguineo() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 

		String sql = "SELECT grs_codigo, grs_nombre "
				+ "FROM grupo_sanguineo "
				+ "WHERE len(grs_nombre) > 0 "
				+ "ORDER BY grs_nombre";

		List<GrupoSanguineo> listaGrupoSanguineo = jdbcTemplate.query(sql, new RowMapper<GrupoSanguineo>() {

			public GrupoSanguineo mapRow(ResultSet rs, int rowNum) throws SQLException {
				GrupoSanguineo gs = new GrupoSanguineo();
				
				gs.setCodigo(rs.getInt("grs_codigo"));
				gs.setNombre(rs.getString("grs_nombre"));
				
				return gs;
			}
		});

		return listaGrupoSanguineo;
	}
	@Override
	public List<EstadoCivil> crearListaEstadoCivil() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 

		String sql = "SELECT esc_codigo, UPPER(esc_nombre) AS esc_nombre "
				+ "FROM estado_civil "
				+ "WHERE len(esc_nombre) > 0 AND esc_codigo > 0 AND esc_codigo != 4 "
				+ "ORDER BY esc_nombre";

		List<EstadoCivil> listaEstadoCivil = jdbcTemplate.query(sql, new RowMapper<EstadoCivil>() {

			public EstadoCivil mapRow(ResultSet rs, int rowNum) throws SQLException {
				EstadoCivil ec = new EstadoCivil();
				
				ec.setCodigo(rs.getInt("esc_codigo"));
				ec.setNombre(rs.getString("esc_nombre"));
				
				return ec;
			}
		});

		return listaEstadoCivil;
	}
	@Override
	public List<EstratoSocioeconomico> crearListaEstratoSocioeconomico() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 

		String sql = "SELECT ses_codigo, ses_descripcion "
				+ "FROM snies_estrato "
				+ "WHERE len(ses_descripcion) > 0 "
				+ "ORDER BY ses_descripcion";

		List<EstratoSocioeconomico> listaEstratoSocioeconomico = jdbcTemplate.query(sql, new RowMapper<EstratoSocioeconomico>() {

			public EstratoSocioeconomico mapRow(ResultSet rs, int rowNum) throws SQLException {
				EstratoSocioeconomico ig = new EstratoSocioeconomico();
				
				ig.setCodigo(rs.getInt("ses_codigo"));
				ig.setNombre(rs.getString("ses_descripcion"));
				
				return ig;
			}
		});

		return listaEstratoSocioeconomico;
	}
	@Override
	public List<LenguaNativa> crearListaLenguaNativa() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 

		String sql = "SELECT sid_codigo, sid_descripcion "
				+ "FROM snies_idioma "
				+ "WHERE len(sid_descripcion) > 0 "
				+ "ORDER BY sid_descripcion";

		List<LenguaNativa> listaLenguaNativa = jdbcTemplate.query(sql, new RowMapper<LenguaNativa>() {

			public LenguaNativa mapRow(ResultSet rs, int rowNum) throws SQLException {
				LenguaNativa ln = new LenguaNativa();
				
				ln.setCodigo(rs.getInt("sid_codigo"));
				ln.setNombre(rs.getString("sid_descripcion"));
				
				return ln;
			}
		});

		return listaLenguaNativa;
	}
	@Override
	public List<InstitucionEducativa> crearListaInstitucionEducativa() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 

		String sql = "SELECT ine_codigo, ine_nombre "
				+ "FROM institucion_educativa "
				+ "WHERE len(ine_nombre) > 0 "
				+ "ORDER BY ine_nombre";

		List<InstitucionEducativa> listaInstitucionEducativa = jdbcTemplate.query(sql, new RowMapper<InstitucionEducativa>() {

			public InstitucionEducativa mapRow(ResultSet rs, int rowNum) throws SQLException {
				InstitucionEducativa ie = new InstitucionEducativa();
				
				ie.setCodigo(rs.getInt("ine_codigo"));
				ie.setNombre(rs.getString("ine_nombre"));
				
				return ie;
			}
		});

		return listaInstitucionEducativa;
	}
	@Override
	public List<Departamento> crearListaDepartamento(int idPais) {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 

		String sql = "SELECT dep_codigo,UPPER(dep_nombre) AS dep_nombre, pai_codigo "
				+ "FROM departamento "
				+ "WHERE len(dep_nombre) > 0 ";
		if(idPais>0){
			sql = sql + " AND pai_codigo = " + idPais + "";
		}
		sql = sql + " ORDER BY dep_nombre ASC";

		List<Departamento> listaDepartamento = jdbcTemplate.query(sql, new RowMapper<Departamento>() {

			public Departamento mapRow(ResultSet rs, int rowNum) throws SQLException {
				Departamento dpt = new Departamento();
				
				dpt.setCodigo(rs.getInt("dep_codigo"));
				dpt.setNombre(rs.getString("dep_nombre"));
				
				return dpt;
			}
		});

		return listaDepartamento;
	}
	@Override
	public List<Pais> crearListaPais() {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 

		String sql = "SELECT pai_codigo, UPPER(pai_nombre) AS pai_nombre "
				+ "FROM pais "
				+ "WHERE len(pai_nombre) > 0 "
				+ "GROUP BY pai_codigo, pai_nombre "
				+ "ORDER BY pai_nombre ASC";

		List<Pais> listaPais = jdbcTemplate.query(sql, new RowMapper<Pais>() {

			public Pais mapRow(ResultSet rs, int rowNum) throws SQLException {
				Pais pai = new Pais();
				
				pai.setCodigo(rs.getInt("pai_codigo"));
				pai.setNombre(rs.getString("pai_nombre"));
				
				return pai;
			}
		});

		return listaPais;
	}
	@Override
	public List<Municipio> crearListaMunicipio(int idDep) {
		// @RolesAllowed("INVITADO")

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 

		String sql = "SELECT mun_codigo,UPPER(mun_nombre) AS mun_nombre,dep_codigo "
				+ "FROM municipio "
				+ "WHERE len(mun_nombre) > 0 ";
		if(idDep>0){
			sql = sql + " AND dep_codigo  = " + idDep;
		}
		sql = sql + "order by mun_nombre";

		List<Municipio> listaMunicipio = jdbcTemplate.query(sql, new RowMapper<Municipio>() {

			public Municipio mapRow(ResultSet rs, int rowNum) throws SQLException {
				Municipio mun = new Municipio();
				
				mun.setCodigo(rs.getInt("mun_codigo"));
				mun.setNombre(rs.getString("mun_nombre"));
				
				return mun;
			}
		});

		return listaMunicipio;
	}
	@Override
	public List<TipoIdentificacion> crearListaTipoIdentificacion() {
		// @RolesAllowed("INVITADO")
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 

		String sql = "SELECT  tii_codigo, UPPER(tii_nombre) AS tii_nombre, tii_nombre_corto "
				+ "FROM tipo_id "
				+ "WHERE len(tii_nombre) > 0 "
				+ "ORDER BY tii_nombre";

		List<TipoIdentificacion> listaTipoId = jdbcTemplate.query(sql, new RowMapper<TipoIdentificacion>() {

			public TipoIdentificacion mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				TipoIdentificacion tid = new TipoIdentificacion();				
				tid.setCodigo(rs.getInt("tii_codigo"));
				tid.setNombre(rs.getString("tii_nombre"));
				//tid.setPse(rs.getString("pse"));
				return tid;
			}
		});

		return listaTipoId;
	}
	
	@Override
	public List<NivelAcademico> crearListaNivelAcademico() {
		// @RolesAllowed("INVITADO")
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource); 

		String sql = "SELECT nia_codigo, nia_nombre "
				+ "FROM nivel_academico "
				+ "WHERE nia_orden != 0";

		List<NivelAcademico> listaNivelAcademico = jdbcTemplate.query(sql, new RowMapper<NivelAcademico>() {

			public NivelAcademico mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				NivelAcademico na = new NivelAcademico();				
				na.setCodigo(rs.getInt("nia_codigo"));
				na.setNombre(rs.getString("nia_nombre"));
				
				return na;
			}
		});

		return listaNivelAcademico;
	}

}
