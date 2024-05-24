package co.edu.usco.inscripciones.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usco.inscripciones.dao.AdmisionDao;
import co.edu.usco.inscripciones.dao.CalendarioDao;
import co.edu.usco.inscripciones.dao.CursoDao;
import co.edu.usco.inscripciones.dao.EstudianteDao;
import co.edu.usco.inscripciones.dao.EstudioAnteriorDao;
import co.edu.usco.inscripciones.dao.FacturaDao;
import co.edu.usco.inscripciones.dao.InscripcionDao;
import co.edu.usco.inscripciones.dao.MatriculaDao;
import co.edu.usco.inscripciones.dao.OfertaDao;
import co.edu.usco.inscripciones.dao.PersonaDao;
import co.edu.usco.inscripciones.dao.PlanAcademicoAsignaturaDao;
import co.edu.usco.inscripciones.dao.PlanAcademicoDao;
import co.edu.usco.inscripciones.dao.PlanEstudianteDao;
import co.edu.usco.inscripciones.dao.UsuarioEstudiantesDao;
import co.edu.usco.inscripciones.dto.InscripcionDTO;
import co.edu.usco.inscripciones.model.Admision;
import co.edu.usco.inscripciones.model.Calendario;
import co.edu.usco.inscripciones.model.Curso;
import co.edu.usco.inscripciones.model.Estudiante;
import co.edu.usco.inscripciones.model.EstudioAnterior;
import co.edu.usco.inscripciones.model.Factura;
import co.edu.usco.inscripciones.model.Inscripcion;
import co.edu.usco.inscripciones.model.Matricula;
import co.edu.usco.inscripciones.model.NivelAcademico;
import co.edu.usco.inscripciones.model.Oferta;
import co.edu.usco.inscripciones.model.Persona;
import co.edu.usco.inscripciones.model.PlanAcademico;
import co.edu.usco.inscripciones.model.PlanAcademicoAsignatura;
import co.edu.usco.inscripciones.model.PlanEstudiante;
import co.edu.usco.inscripciones.model.UsuarioEstudiante;
import co.edu.usco.inscripciones.model.UsuarioGeneral;
import co.edu.usco.inscripciones.utilidad.Respuesta;

@Service("inscripcionService")
public class InscripcionServiceImpl implements InscripcionService {

	@Autowired
	DataSource dataSourceAcademiaInvitado;

	@Autowired
	PersonaDao personaDao;

	@Autowired
	FacturaDao facturaDao;

	@Autowired
	InscripcionDao inscripcionDao;

	@Autowired
	OfertaDao ofertaDao;

	@Autowired
	AdmisionDao admisionDao;

	@Autowired
	EstudianteDao estudianteDao;

	@Autowired
	PlanAcademicoDao planAcademicoDao;

	@Autowired
	PlanEstudianteDao planEstudianteDao;

	@Autowired
	MatriculaDao matriculaDao;

	@Autowired
	PlanAcademicoAsignaturaDao planAcaAsiDao;

	@Autowired
	CalendarioDao calendarioDao;

	@Autowired
	CursoDao cursoDao;

	@Autowired
	UsuarioEstudiantesDao usuarioEstudiantesDao;

	@Autowired
	UsuarioGeneralService usuarioGeneralService;

	@Autowired
	EstudioAnteriorDao estudioAnteriorDao;

	@Override
	@Transactional
	public Respuesta guardarInscripcion(Inscripcion inscripcion) {
		Respuesta respuesta = new Respuesta();

		Inscripcion codigoOferta = inscripcionDao
				.consultarOfertaSeleccionada(inscripcion.getOferta().getCodigoEncriptado());
		
		System.out.println("ID PERSONAAAAAAAAAAAAAA" + inscripcion.getPersona().getId());
		System.out.println("CODIGO PERSONAAAAAAAAAAAAAA" + inscripcion.getPersona().getCodigo());
		int codigoInscripcion = inscripcionDao.constultarInscripcionCodigo(inscripcion.getPersona().getId());
		inscripcion.setId(codigoInscripcion);
		System.out.println("---codigo inscripcion en serviceimpl:::" + codigoInscripcion);
				
		if (codigoOferta != null) {
			Oferta estadoOferta = ofertaDao.consultarEstadoOferta(codigoOferta.getOferta().getCodigo(), "");
			if (estadoOferta.getEstado() != "2") {

				Persona personaExiste = personaDao.consultarPersona(inscripcion.getPersona().getIdentificacion());

				if (personaExiste != null && personaExiste.isTercero() == true) {
					inscripcion.getPersona().setId(personaExiste.getId());
					inscripcion.getPersona().setTercero(personaExiste.isTercero());
					personaDao.actualizarTercero(inscripcion.getPersona());
					System.out.println("LLego persona tercero");
				} else {
					inscripcion.getPersona().setId(personaExiste.getId());
					inscripcion.getPersona().setTercero(personaExiste.isTercero());
					personaDao.ActualizarPersonaPA(inscripcion.getPersona());
					System.out.println("Llego Persona ");
				}

				if (estadoOferta.getRequierePago() == 0) {
					inscripcion.setOferta(estadoOferta);
					if (inscripcionDao.validarInscripcion(inscripcion)) {
						System.out.println("Llego validar inscripcion: " + inscripcion);
						respuesta.setRespuesta(Respuesta.INSCRIPCION_EXISTE);
						return respuesta;
					}
				}

				if (estadoOferta.getRequierePago() == 1) {

					if (!facturaDao.validarFacturaExista(inscripcion.getFactura().getReferencia())) {
						respuesta.setRespuesta(Respuesta.FACTURA_ERROR);
						return respuesta;
					}

					if (inscripcionDao.validarReferenciaInscripcion(inscripcion.getFactura().getReferencia())) {
						respuesta.setRespuesta(Respuesta.FACTURA_UTILIZADA);
						return respuesta;
					}

					if (facturaDao.validarFacturaAutorizada(inscripcion.getFactura().getReferencia(),
							inscripcion.getFactura().getPin())) {
						System.out.println("Se valido la factura autorizada");
						Factura facturaGenerada = facturaDao
								.consultarFacturaGenerada(inscripcion.getFactura().getReferencia());
						Factura facturaPagada = facturaDao.consultarFacturaPagada(
								inscripcion.getFactura().getReferencia(), inscripcion.getFactura().getPin());
						if (facturaGenerada != null && facturaPagada != null
								&& (facturaPagada.getFechaRecaudo().before(facturaGenerada.getFechaFacturaFecha())
										|| facturaPagada.getFechaRecaudo()
												.equals(facturaGenerada.getFechaFacturaFecha()))
								&& facturaPagada.getValorFactura() > 0) {
							System.out.println("La factura esta paga todo bn");
						} else {
							System.out.println("La factura no esta paga :(");
							respuesta.setRespuesta(Respuesta.FACTURA_ERROR);
							return respuesta;
						}
					} else {
						System.out.println("No se encontro factura autorizada");
						respuesta.setRespuesta(Respuesta.FACTURA_ERROR);
						return respuesta;
					}
				}

				if (estadoOferta.getRequierePago() == 0) {
					//inscripcion.setCodigo(codigoInscripcion);
					if (!inscripcionDao.agregarIncripcionGratis(inscripcion)) {
						respuesta.setRespuesta(Respuesta.INSCRIPCION_ERROR);
						System.out.println("No se agrego inscripcion gratis");
						return respuesta;
					} else {
						System.out.println("Se agregi inscripcion gratis");
						System.out.println("INSCRIPCIÓN---1" + inscripcion.getId());
					}
				} else {
					if (!inscripcionDao.agregarIncripcionPaga(inscripcion)) {
						respuesta.setRespuesta(Respuesta.INSCRIPCION_ERROR);
						System.out.println("No agrego inscripcion paga");
						return respuesta;
					} else {
						System.out.println("Se agrego inscripcion paga");
					}
				}

				System.out.println("INSCRIPCIÓN---2" + inscripcion.getId());
				
				Inscripcion cantCertificados = inscripcionDao
						.consultarCantidadRequisitos(codigoOferta.getOferta().getCodigo());

				if (inscripcionDao.agregarIncripcionPrograma(inscripcion, codigoOferta.getOferta().getCodigo())) {
					// 1- Manual 0-Auto
					System.out.println("Admision Automatica:" + estadoOferta.getAdmisionAutomatica());
					System.out.println("Cantidad Certificados:" + cantCertificados.getCantidadCertificados());

					System.out.println("*****************************************************");
					System.out.println("Tipo Admision:" + estadoOferta.getAdmisionAutomatica());
					System.out.println("*****************************************************");

					if (inscripcion.getEstudioAnterior().size() > 0) {
						for (EstudioAnterior estAnt : inscripcion.getEstudioAnterior()) {
							EstudioAnterior estudioAnterior = new EstudioAnterior();
							estudioAnterior.setAnio(estAnt.getAnio());
							estudioAnterior.setTitulo(estAnt.getTitulo());
							estudioAnterior.setInstitucion(estAnt.getInstitucion());

							NivelAcademico nivelAcademico = new NivelAcademico();
							nivelAcademico.setCodigo(estAnt.getNivelAcademico().getCodigo());
							estudioAnterior.setNivelAcademico(nivelAcademico);

							if (personaExiste.isTercero() == true) {
								estudioAnterior.setPersona(0);
								estudioAnterior.setTercero(personaExiste.getId());
							} else {
								estudioAnterior.setPersona(personaExiste.getId());
								estudioAnterior.setTercero(0);
							}

							estudioAnteriorDao.agregarEstudioAnterior(estudioAnterior);
						}
					}

					if (estadoOferta.getAdmisionAutomatica() == 1) {
						respuesta.setRespuesta(Respuesta.INSCRIPCION_OK_ADMISION);
						return respuesta;
					}

					System.out.println("admiSION:::::::::::");
					Admision admision = new Admision();
					admision.setInscripcion(inscripcion);
					admisionDao.agregarAdmision(admision);

					inscripcion.setOferta(estadoOferta);

					Estudiante estudiante = new Estudiante();
					estudiante.setInscripcion(inscripcion);
					estudiante.setAdmision(admision);
					estudiante.setPersona(personaExiste);

					if (estudianteDao.guardarEstudiante(estudiante)) {
						System.out.println("Estudiante creado");
					} else {
						System.out.println("No se creo estudiante");
					}

					inscripcion.setEstudiante(estudiante);
					inscripcion.getEstudiante().setCodigo(estudiante.getCodigo());

					PlanAcademico planAcademicoExiste = planAcademicoDao
							.consultarPlanAcademico(inscripcion.getOferta().getPrograma().getCodigo());
					if (planAcademicoExiste == null) {
						respuesta.setRespuesta(Respuesta.PLAN_ACADEMICO);
						System.out.println("No encontro el plan academico");
						return respuesta;
					} else {
						System.out.println("Se encontro el plan academico");
					}

					PlanEstudiante planEstudiante = new PlanEstudiante();
					planEstudiante.setInscripcion(inscripcion);
					planEstudiante.setPlanAcademico(planAcademicoExiste);
					if (!planEstudianteDao.agregarPlanEstudiante(planEstudiante)) {
						System.out.println("No se agrego plan academico estudiante");
						respuesta.setRespuesta(Respuesta.PLAN_ESTUDIANTE);
						return respuesta;
					} else {
						System.out.println("Se agrego plan academico estudiante");
					}

					Calendario calendario = calendarioDao.consultarCalendario(estadoOferta.getCalendario().getCodigo());

					Matricula matricula = new Matricula();
					matricula.setInscripcion(inscripcion);
					Oferta oferta = new Oferta();
					oferta.setCalendario(calendario);
					matricula.setOferta(oferta);
					if (!matriculaDao.agregarMatricula(matricula)) {
						respuesta.setRespuesta(Respuesta.MATRICULA_ERROR);
						System.out.println("No se agrego la matricula");
						return respuesta;
					} else {
						System.out.println("Se agrego la matricula");
					}

					List<PlanAcademicoAsignatura> paa = planAcaAsiDao
							.buscarPlanAcademicoAsignatura(planAcademicoExiste.getCodigo());
					if (paa == null) {
						respuesta.setRespuesta(Respuesta.PLAN_ACADEMICO_ASIGNATURA);
						return respuesta;
					}

					for (PlanAcademicoAsignatura paa1 : paa) {
						Curso curso = cursoDao.buscarCurso(paa1.getAsignatura().getCodigo(),
								planAcademicoExiste.getCodigo(), estadoOferta.getCalendario().getCodigo());
						if (curso != null) {
							if (!matriculaDao.agregarMatriculaCursoActual(matricula.getId(), curso.getCodigo(),
									paa1.getCodigo())) {
								respuesta.setRespuesta(Respuesta.MATRICULA_ERROR);
								System.out.println("No se agrego el curso");
								return respuesta;
							} else {
								System.out.println("Se agrego matricula curso actual");
							}
						}
					}

					if (estadoOferta.getCrearUsuario() == 1) {
						// consultar si ya existe el usuario
						UsuarioEstudiante usuario = usuarioEstudiantesDao
								.consultarUsuarioEstudiantes(estudiante.getPersona().getIdentificacion());
						if (usuario == null) {
							UsuarioEstudiante usuarioEstudiante = usuarioEstudiantesDao.consultarUID();
							estudiante.setUsuarioestudiante(usuarioEstudiante);
							if (usuarioEstudiantesDao.guardarUsuarioEstudiantes(estudiante, false)) {
								System.out.println("Se guardo usuario estudiante");
							} else {
								System.out.println("No se guardo estudiante");
							}

							Oferta uaa = ofertaDao.consultarDatosOferta(codigoOferta.getOferta().getCodigo());
							inscripcion.setOferta(uaa);
							estudiante.setInscripcion(inscripcion);
							if (usuarioEstudiantesDao.guardarUsuarioGrupo(estudiante)) {
								System.out.println("Se agrego usuario grupo");
							} else {
								System.out.println("No se agrego usuario grupo");
							}
						}
					}

					if (cantCertificados.getCantidadCertificados() > 0) {
						respuesta.setRespuesta(Respuesta.REQUIERE_CERTIFICADOS);
						respuesta.setBody(
								new InscripcionDTO(cantCertificados.getCantidadCertificados(), inscripcion.getId()));
						return respuesta;
					}

					/*
					 * if (estadoOferta.getAdmisionAutomatica() == 1) { if
					 * (cantCertificados.getCantidadCertificados() > 0) { respuesta.setBody(new
					 * InscripcionDTO(cantCertificados.getCantidadCertificados() ,
					 * inscripcion.getId())); respuesta.setRespuesta(Respuesta.INSCRIPCION_OK);
					 * return respuesta; } }
					 */

					respuesta.setRespuesta(Respuesta.INSCRIPCION_OK);
					return respuesta;
				}
				System.out.println("No se creo la inscripcion programa");
				respuesta.setRespuesta(Respuesta.INSCRIPCION_PROGRAMA_ERROR);
				return respuesta;

			}
			respuesta.setRespuesta(Respuesta.PREINSCRIPCION_ESTADO);
			return respuesta;
		}
		respuesta.setRespuesta(Respuesta.PREINSCRIPCION_ERROR);
		return respuesta;
	}

	@Override
	public Respuesta guardarInscripcionInterna(Inscripcion inscripcion) {
		// TODO Auto-generated method stub
		Respuesta respuesta = new Respuesta();

		if (inscripcion.getOferta().getCodigo() > 0) {
			Oferta estadoOferta = ofertaDao.consultarEstadoOferta(inscripcion.getOferta().getCodigo(), "");
			if (estadoOferta.getEstado() != "2") {

				UsuarioGeneral usuGral = new UsuarioGeneral();

				if (!inscripcion.isProveedor()) {
					usuGral = usuarioGeneralService.validaUsuarioGral(inscripcion.getUsuario().getUsuario());
				}
				/*
				 * Persona returnVinculacion = personaDao
				 * .validarVinculacionPersona(usuGral.getPersona(). getIdentificacion());
				 * 
				 * if (returnVinculacion != null) {
				 */

				if (estadoOferta.getRequierePago() == 0) {
					inscripcion.setOferta(estadoOferta);
					if (inscripcionDao.validarInscripcion(inscripcion)) {
						respuesta.setRespuesta(Respuesta.INSCRIPCION_EXISTE);
						return respuesta;
					}
					System.out.println("Se valido la inscripcion");
				}

				if (estadoOferta.getRequierePago() == 0) {
					if (!inscripcion.isProveedor()) {
						inscripcion.setCodigoUsuario(usuGral.getCodigo());
					}
					if (!inscripcionDao.agregarIncripcionGratis(inscripcion)) {
						respuesta.setRespuesta(Respuesta.INSCRIPCION_ERROR);
						return respuesta;
					}
					System.out.println("Se agrego la inscripcion gratis");
				} else {
					if (!inscripcionDao.agregarIncripcionPaga(inscripcion)) {
						respuesta.setRespuesta(Respuesta.INSCRIPCION_ERROR);
						return respuesta;
					}
				}

				if (inscripcionDao.agregarIncripcionPrograma(inscripcion, estadoOferta.getCodigo())) {
					System.out.println("Se agrego la inscripcion programa: inscripcion:" + inscripcion.getId()
							+ " Oferta:" + estadoOferta.getCodigo());
					/*
					 * if(inscripcion.getEstudioAnterior().size()>0){ for (EstudioAnterior estAnt :
					 * inscripcion.getEstudioAnterior()) { EstudioAnterior estudioAnterior = new
					 * EstudioAnterior(); estudioAnterior.setAnio(estAnt.getAnio());
					 * estudioAnterior.setTitulo(estAnt.getTitulo());
					 * estudioAnterior.setInstitucion(estAnt.getInstitucion());
					 * 
					 * NivelAcademico nivelAcademico = new NivelAcademico();
					 * nivelAcademico.setCodigo(estAnt.getNivelAcademico(). getCodigo());
					 * estudioAnterior.setNivelAcademico(nivelAcademico);
					 * 
					 * estudioAnterior.setPersona(inscripcion.getPersona(). getCodigo());
					 * estudioAnterior.setTercero(inscripcion.getPersona().getId ());
					 * 
					 * estudioAnteriorDao.agregarEstudioAnterior(estudioAnterior ); } }
					 */

					// 1- Manual 0-Auto
					System.out.println("*****************************************************");
					System.out.println("Tipo Admision:" + estadoOferta.getAdmisionAutomatica());
					System.out.println("*****************************************************");

					if (estadoOferta.getAdmisionAutomatica() == 1) {
						respuesta.setRespuesta(Respuesta.INSCRIPCION_OK_ADMISION);
						return respuesta;
					}

					Admision admision = new Admision();
					admision.setInscripcion(inscripcion);
					admisionDao.agregarAdmision(admision);

					inscripcion.setOferta(estadoOferta);

					Estudiante estudiante = new Estudiante();
					estudiante.setInscripcion(inscripcion);
					estudiante.setAdmision(admision);
					estudiante.setPersona(inscripcion.getPersona());
					estudianteDao.guardarEstudiante(estudiante);

					inscripcion.setEstudiante(estudiante);
					inscripcion.getEstudiante().setCodigo(estudiante.getCodigo());

					PlanAcademico planAcademicoExiste = planAcademicoDao
							.consultarPlanAcademico(inscripcion.getOferta().getPrograma().getCodigo());
					if (planAcademicoExiste == null) {
						respuesta.setRespuesta(Respuesta.PLAN_ACADEMICO);
						return respuesta;
					}

					PlanEstudiante planEstudiante = new PlanEstudiante();
					planEstudiante.setInscripcion(inscripcion);
					planEstudiante.setPlanAcademico(planAcademicoExiste);
					if (!planEstudianteDao.agregarPlanEstudiante(planEstudiante)) {
						respuesta.setRespuesta(Respuesta.PLAN_ESTUDIANTE);
						return respuesta;
					}

					Calendario calendario = calendarioDao.consultarCalendario(estadoOferta.getCalendario().getCodigo());

					Matricula matricula = new Matricula();
					matricula.setInscripcion(inscripcion);
					Oferta oferta = new Oferta();
					oferta.setCalendario(calendario);
					matricula.setOferta(oferta);
					matricula.setUsuario(usuGral.getCodigo());

					if (!matriculaDao.agregarMatricula(matricula)) {
						respuesta.setRespuesta(Respuesta.MATRICULA_ERROR);
						return respuesta;
					}

					List<PlanAcademicoAsignatura> paa = planAcaAsiDao
							.buscarPlanAcademicoAsignatura(planAcademicoExiste.getCodigo());
					if (paa == null) {
						respuesta.setRespuesta(Respuesta.PLAN_ACADEMICO_ASIGNATURA);
						return respuesta;
					}

					for (PlanAcademicoAsignatura paa1 : paa) {
						Curso curso = cursoDao.buscarCurso(paa1.getAsignatura().getCodigo(),
								planAcademicoExiste.getCodigo(), estadoOferta.getCalendario().getCodigo());
						if (curso != null) {
							if (!matriculaDao.agregarMatriculaCursoActual(matricula.getId(), curso.getCodigo(),
									paa1.getCodigo())) {
								respuesta.setRespuesta(Respuesta.MATRICULA_ERROR);
								return respuesta;
							}
						}
					}

					if (estadoOferta.getCrearUsuario() == 1) {
						System.out.println("Si crear usuario");
						if (inscripcion.isProveedor()) {
							UsuarioEstudiante usuario = usuarioEstudiantesDao
									.consultarUsuarioEstudiantes(estudiante.getPersona().getIdentificacionProveedor());
							if (usuario == null) {
								System.out.println("No existe usuario");
								UsuarioEstudiante usuarioEstudiante = usuarioEstudiantesDao.consultarUID();
								estudiante.setUsuarioestudiante(usuarioEstudiante);

								if (usuarioEstudiantesDao.guardarUsuarioEstudiantes(estudiante, true)) {
									System.out.println("Se guardo usuario estudiante");
								} else {
									System.out.println("No se guardo estudiante");
								}

								Oferta uaa = ofertaDao.consultarDatosOferta(estadoOferta.getCodigo());
								inscripcion.setOferta(uaa);
								estudiante.setInscripcion(inscripcion);
								if (usuarioEstudiantesDao.guardarUsuarioGrupo(estudiante)) {
									System.out.println("Se agrego usuario grupo");
								} else {
									System.out.println("No se agrego usuario grupo");
								}
							}
						} else {
							System.out.println("No crear usuario");
							Oferta uaa = ofertaDao.consultarDatosOferta(estadoOferta.getCodigo());
							inscripcion.setOferta(uaa);
							estudiante.setInscripcion(inscripcion);

							UsuarioEstudiante usuarioEstudiante = new UsuarioEstudiante();
							usuarioEstudiante.setUid(inscripcion.getUsuario().getCodigo());
							estudiante.setUsuarioestudiante(usuarioEstudiante);

							usuarioEstudiantesDao.guardarUsuarioGrupo(estudiante);
						}
					}

					respuesta.setRespuesta(Respuesta.INSCRIPCION_OK);
					return respuesta;
				}
				respuesta.setRespuesta(Respuesta.INSCRIPCION_PROGRAMA_ERROR);
				return respuesta;
				/*
				 * } else { respuesta.setRespuesta(Respuesta.USUARIO_NO_INTERNO); return
				 * respuesta; }
				 */
			}
			respuesta.setRespuesta(Respuesta.PREINSCRIPCION_ESTADO);
			return respuesta;
		}
		respuesta.setRespuesta(Respuesta.PREINSCRIPCION_ERROR);
		return respuesta;

	}

}
