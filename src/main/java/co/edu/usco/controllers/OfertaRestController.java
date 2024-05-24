package co.edu.usco.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usco.inscripciones.dao.OfertaConfiguracionDao;
import co.edu.usco.inscripciones.dao.OfertaDao;
import co.edu.usco.inscripciones.dao.WebParametroDao;
import co.edu.usco.inscripciones.dto.OfertaConfiguracionDTO;
import co.edu.usco.inscripciones.model.Calendario;
import co.edu.usco.inscripciones.model.Oferta;
import co.edu.usco.inscripciones.model.OfertaRequisitos;
import co.edu.usco.librerias.DiferenciaFechas;

@RestController
public class OfertaRestController {

	@Autowired
	DataSource dataSourceAcademiaInvitado;

	@Autowired
	OfertaDao ofertaDao;
	
	@Autowired
	WebParametroDao webParametroDao;
	
	@Autowired
	OfertaConfiguracionDao ofertaConfiguracionDao;

	@RequestMapping(value = "/listadoOfertasAcademicas", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Oferta> getOferta(@RequestParam("uaa") String uaa, @RequestParam("requierePago") String requierePago) {
		List<Oferta> listOfOferta = new ArrayList<Oferta>();
		listOfOferta = ofertaDao.crearListaOferta(uaa, requierePago);
		return listOfOferta;
	}
	
	@RequestMapping(value = "/listadoOfertaFormalVirtual", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Oferta> getOfertaFormalVirtual() {
		List<Oferta> listOfOferta = new ArrayList<Oferta>();
		listOfOferta = ofertaDao.crearListaOfertaFormalVirtual();
		return listOfOferta;
	}
	
	@RequestMapping(value = "/listadoOfertasAcademicasEducacionFormal", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Oferta> getOferta() {
		
		Calendario calendarioInscripcion = webParametroDao.consultarIncripcionPregradoCalendario();
		if(calendarioInscripcion!=null){
			List<Oferta> listOfOferta = new ArrayList<Oferta>();
			listOfOferta = ofertaDao.crearListaOfertaEducacionFormal(calendarioInscripcion.getValor());
			return listOfOferta;
		}		
		return null;
	}
	
	@RequestMapping(value = "/listadoFacultadesOfertasAcademicas", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Oferta> getFacultadesOferta(@RequestParam("requierePago") int requierePago) {
		List<Oferta> listOfOferta = new ArrayList<Oferta>();
		listOfOferta = ofertaDao.crearListaFacultadesOferta(requierePago);
		return listOfOferta;
	}
	
	@RequestMapping(value = "/listadoOfertasAcademicasInactivas/", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Oferta> getOfertaInactiva() {
		List<Oferta> listOfOferta = new ArrayList<Oferta>();
		listOfOferta = ofertaDao.crearListaOfertaInactiva();
		return listOfOferta;
	}
	
	@RequestMapping(value = "/consultarDatosOferta/{codigo}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Oferta modificar(@PathVariable("codigo") int codigo) {
		Oferta ofertaDatos = ofertaDao.consultarDatosOferta(codigo);
		return ofertaDatos;
	}
	
	@RequestMapping(value = "/consultarInformacionOferta/{codigo}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Oferta> informacion(@PathVariable("codigo") int codigo) {
		List<Oferta> ofertaDatos = new ArrayList<Oferta>();
		ofertaDatos = ofertaDao.consultarInformacionOferta(codigo);
		return ofertaDatos;	
	}
	
	@RequestMapping(value = "/consultarRequisitosOferta", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Oferta> getRequisitos(@RequestParam("codigo") int codigo, @RequestParam("codigoEncriptado") String codigoEncriptado) {
		List<Oferta> ofertaRequisitos = new ArrayList<Oferta>();
		ofertaRequisitos = ofertaDao.consultarRequisitosOferta(codigo, codigoEncriptado);
		return ofertaRequisitos;
	}
	
	@RequestMapping(value = "/consultarRequisitosOfertaAdjuntos/{codigo}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<OfertaRequisitos> getRequisitosAdjuntos(@PathVariable("codigo") String codigo) {
		List<OfertaRequisitos> ofertaRequisitos = new ArrayList<OfertaRequisitos>();
		ofertaRequisitos = ofertaDao.consultarRequisitosOfertaAdjuntos(codigo);
		return ofertaRequisitos;
	}
	
	@RequestMapping(value = "/consultarEstadoOferta/{codigoEncriptado}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Oferta modificar(@PathVariable("codigoEncriptado") String codigoEncriptado) {
		Oferta ofertaDatos = ofertaDao.consultarEstadoOferta(0,codigoEncriptado);
		long diferencia = DiferenciaFechas.diferencia(ofertaDatos.getFechaIncripcionFin());
		if(diferencia > 1){
			ofertaDatos = null;
		}
		return ofertaDatos;
	}
	
	@RequestMapping(value = "/consultarCantidadInscritos/{codigo}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Oferta consultar(@PathVariable("codigo") long codigo) {
		Oferta ofertaDatos = ofertaDao.consultarCantidadInscritos(codigo);
		return ofertaDatos;
	}
	
	@RequestMapping(value = "/ofertaConfiguracionSer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<OfertaConfiguracionDTO> getOfertaConfiguracionLista(@PathVariable int id) {
		List<OfertaConfiguracionDTO> listaOfertaInformacion = new ArrayList<OfertaConfiguracionDTO>();
		listaOfertaInformacion = ofertaConfiguracionDao.listarOfertaConfiguracion(id);
		return listaOfertaInformacion;
	}

	
}
