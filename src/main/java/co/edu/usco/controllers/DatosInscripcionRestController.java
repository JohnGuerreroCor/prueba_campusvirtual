package co.edu.usco.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usco.inscripciones.dao.DatosInscripcionDao;
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

@RestController
public class DatosInscripcionRestController {

	@Autowired
	DataSource dataSourceAcademiaInvitado;
	
	@Autowired
	DatosInscripcionDao datosIncripcionDao;
	
	@RequestMapping(value = "/eps", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Eps> getEps() {
		List<Eps> listOfEps = new ArrayList<Eps>();
		listOfEps = datosIncripcionDao.crearListaEps();
		return listOfEps;
	}
			
	@RequestMapping(value = "/epsTipoAfiliacion", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<EpsTipoAfiliacion> getEpsTipoAfiliacion() {
		List<EpsTipoAfiliacion> listOfEpsTipoAfiliacion = new ArrayList<EpsTipoAfiliacion>();
		listOfEpsTipoAfiliacion = datosIncripcionDao.crearListaEpsTipoAfiliacion();
		return listOfEpsTipoAfiliacion;
	}
	
	@RequestMapping(value = "/grupoSanguineo", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<GrupoSanguineo> getGrupoSanguineo() {
		List<GrupoSanguineo> listOfGrupoSanguineo = new ArrayList<GrupoSanguineo>();
		listOfGrupoSanguineo = datosIncripcionDao.crearListaGrupoSanguineo();
		return listOfGrupoSanguineo;
	}
	
	@RequestMapping(value = "/estadoCivil", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<EstadoCivil> getEstadoCivil() {
		List<EstadoCivil> listOfEstadoCivil = new ArrayList<EstadoCivil>();
		listOfEstadoCivil = datosIncripcionDao.crearListaEstadoCivil();
		return listOfEstadoCivil;
	}
		
	@RequestMapping(value = "/estratoSocioeconomico", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<EstratoSocioeconomico> getEstratoSocioeconomico() {
		List<EstratoSocioeconomico> listOfEstratoSocioeconomico = new ArrayList<EstratoSocioeconomico>();
		listOfEstratoSocioeconomico = datosIncripcionDao.crearListaEstratoSocioeconomico();
		return listOfEstratoSocioeconomico;
	}
	
	@RequestMapping(value = "/lenguaNativa", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<LenguaNativa> getLenguaNativa() {
		List<LenguaNativa> listOfLenguaNativa = new ArrayList<LenguaNativa>();
		listOfLenguaNativa = datosIncripcionDao.crearListaLenguaNativa();
		return listOfLenguaNativa;
	}
	
	@RequestMapping(value = "/institucionEducativa", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<InstitucionEducativa> getInstitucionEducativa() {
		List<InstitucionEducativa> listOfInstitucionEducativa = new ArrayList<InstitucionEducativa>();
		listOfInstitucionEducativa = datosIncripcionDao.crearListaInstitucionEducativa();
		return listOfInstitucionEducativa;
	}
	
	@RequestMapping(value = "/paises", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Pais> getPaises() {
		List<Pais> listOfPais = new ArrayList<Pais>();
		listOfPais = datosIncripcionDao.crearListaPais();
		return listOfPais;
	}
	
	@RequestMapping(value = "/departamentos", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Departamento> getDepartamentos(@RequestParam("idPais") int idPais) {
		List<Departamento> listOfDepartamento = new ArrayList<Departamento>();
		listOfDepartamento = datosIncripcionDao.crearListaDepartamento(idPais);
		return listOfDepartamento;
	}
	
	@RequestMapping(value = "/municipios", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Municipio> getMunicipios(@RequestParam("idDep") int idDep) {
		List<Municipio> listOfMunicipio = new ArrayList<Municipio>();
		listOfMunicipio = datosIncripcionDao.crearListaMunicipio(idDep);
		return listOfMunicipio;
	}
	
	@RequestMapping(value = "/tipoIdentificacion", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<TipoIdentificacion> getTipoIdentificacion() {
		List<TipoIdentificacion> listOfTipoIdentificacion = new ArrayList<TipoIdentificacion>();
		listOfTipoIdentificacion = datosIncripcionDao.crearListaTipoIdentificacion();
		return listOfTipoIdentificacion;
	}
	
	@RequestMapping(value = "/nivelAcademico", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<NivelAcademico> getNivelAcademico() {
		List<NivelAcademico> listOfNivelAcademico = new ArrayList<NivelAcademico>();
		listOfNivelAcademico = datosIncripcionDao.crearListaNivelAcademico();
		return listOfNivelAcademico;
	}
	
}

