package co.edu.usco.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.edu.usco.inscripciones.dao.BasesDatosDao;
import co.edu.usco.inscripciones.utilidad.Constantes;

@Controller
public class VistasController {

	@Autowired
	BasesDatosDao basesDatosDao;

	@Autowired
	Constantes constantes;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getAreas(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("constantes", constantes);
		return mv;
	}

	@RequestMapping("/liquidacion_pendiente")
	public ModelAndView liquidacion_pendiente() {
		ModelAndView mv = new ModelAndView("liquidacion_pendiente");
		return mv;
	}

	@RequestMapping("/programa")
	public ModelAndView programa() {
		ModelAndView mv = new ModelAndView("programa");
		mv.addObject("constantes", constantes);
		return mv;
	}

	@RequestMapping("/formaRegistrar")
	public ModelAndView formaregistrar() {
		return new ModelAndView("forma.registrar");
	}

	@RequestMapping("/formaRegistrarInterno")
	public ModelAndView formaregistrarinterno() {
		return new ModelAndView("forma.registrarinterno");
	}

	@RequestMapping("/inscripcion")
	public ModelAndView inscripcion() {
		return new ModelAndView("inscripcion_portal");
	}

	@RequestMapping("/formaEstudioAnterior")
	public ModelAndView formaEstudioAnterior() {
		return new ModelAndView("formaEstudioAnterior");
	}

	@RequestMapping("/head")
	public ModelAndView head() {
		return new ModelAndView("head");
	}

	@RequestMapping("/menu")
	public ModelAndView menu() {
		return new ModelAndView("menu");
	}

	@RequestMapping("/footer")
	public ModelAndView footer() {
		return new ModelAndView("footer");
	}

	@RequestMapping("/jslibs")
	public ModelAndView jslibs() {
		return new ModelAndView("jslibs");
	}

	@RequestMapping("/oferta")
	public ModelAndView oferta() {
		ModelAndView mv = new ModelAndView("oferta");
		mv.addObject("constantes", constantes);
		return mv;
	}

	@RequestMapping("/basesdatos")
	public ModelAndView basesdatos() {
		return new ModelAndView("basesdatos");
	}

	@RequestMapping("/forma_pse")
	public ModelAndView dialog1() {
		return new ModelAndView("forma_pse");
	}

	@RequestMapping("/filters")
	public ModelAndView filters() {
		return new ModelAndView("filters");
	}

	@RequestMapping("/reservavideoconferencia")
	public ModelAndView reservavideoconferencia() {
		ModelAndView mv = new ModelAndView("reservavideoconferencia");
		mv.addObject("constantes", constantes);
		return mv;
	}

	@RequestMapping("/estudiantes")
	public ModelAndView estudiantes() {
		return new ModelAndView("estudiantes");
	}

	@RequestMapping("/docentes")
	public ModelAndView docentes() {
		return new ModelAndView("docentes");
	}

	@RequestMapping("/aulasinteligentes")
	public ModelAndView aulasinteligentes() {
		return new ModelAndView("aulasinteligentes");
	}

	@RequestMapping("/listaPdf")
	public ModelAndView listaPdf() {
		return new ModelAndView("listaPdf");
	}

	@RequestMapping("/videoTutoriales")
	public ModelAndView videoTutoriales() {
		return new ModelAndView("videoTutoriales");
	}

	@RequestMapping("/formPqr")
	public ModelAndView formPqr() {
		return new ModelAndView("formPqr");
	}

	@RequestMapping("/ieSupport")
	public ModelAndView ieSupport() {
		return new ModelAndView("ieSupport");
	}

	@RequestMapping("/procesoInscripcion")
	public ModelAndView procesoInscripcion() {
		return new ModelAndView("procesoInscripcion");
	}

	@RequestMapping("/preguntasFrecuentes")
	public ModelAndView preguntasFrecuentes() {
		return new ModelAndView("preguntasFrecuentes");
	}

	@RequestMapping("/consultarPagos")
	public ModelAndView consultarPagos() {
		return new ModelAndView("consultar_pagos");
	}

	@RequestMapping("/politicasPrivacidadApp")
	public ModelAndView politicasPrivacidadApp() {
		return new ModelAndView("politicasPrivacidadApp");
	}
	
	
}
