package co.edu.usco.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import co.edu.usco.inscripciones.dao.BasesDatosDao;
import co.edu.usco.inscripciones.model.BasesDatos;
import co.edu.usco.inscripciones.model.VisitasBasesDatos;;

@RestController
public class BibliotecaRestController {

	@Autowired	
	DataSource dataSourceAcademiaInvitado;
	
	@Autowired
	BasesDatosDao basesDatosDao;
	
	@RequestMapping(value = "/listadoBasesDatos/", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<BasesDatos> getBasesDatos() {
		List<BasesDatos> listBasesDatos = new ArrayList<BasesDatos>();
		listBasesDatos = basesDatosDao.crearListaBasesDatos();
		return listBasesDatos;
	}
		
	@RequestMapping(value = "/consultarBaseDato", method = RequestMethod.GET, headers = "Accept=application/json")
	public BasesDatos visita(@RequestParam("codigoBD") int codigoBD) {
		BasesDatos bd = basesDatosDao.consultarBaseDato(codigoBD);
		return bd;
	}
	
	@RequestMapping(value = "/consultarVisitasBasesDatos", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<VisitasBasesDatos> visitas() {
		List<VisitasBasesDatos> listaVisitrasDB = new ArrayList<VisitasBasesDatos>();
		listaVisitrasDB = basesDatosDao.consultarVisitasBasesDatos();
		return listaVisitrasDB;
	}
	
	@RequestMapping(value = "/basesdatos_detalle", method = RequestMethod.GET)
	public ModelAndView getAreas(@RequestParam("codigoBD") int codigoBD) {
		ModelAndView mv = new ModelAndView("basesdatos_detalle");
		/*
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
			UscoGrantedAuthority uscoGrantedAuthority = (UscoGrantedAuthority) grantedAuthority;
			uscoGrantedAuthority.getUaa();
		}
		
		BasesDatos bd = basesDatosDao.consultarBaseDato(codigoBD);
		if(bd!=null){
			basesDatosDao.agregarVisita(user.getUsername(), bd.getLink(), bd.getNombre());
		}
		
		ModelAndView mv = new ModelAndView("basesdatos_detalle");
		request.setAttribute("usuario", user);*/
		return mv;
	}
	
}
