package co.edu.usco.inscripciones.utilidad;

public class Respuesta {

	public static final int EJECUCION_OK = 1;
	public static final int EJECUCION_ERROR = 0;
	public static final int VERIFICADO_OK = 2;
	public static final int VERIFICADO_EXISTE = 19;
	public static final int VERIFICADO_ERROR = 3;

	public static final int FACTURA_OK = 4;
	public static final int FACTURA_ERROR = 5;
	public static final int FACTURA_AUTORIZADA_OK = 6;
	public static final int FACTURA_UTILIZADA = 7;

	public static final int INSCRIPCION_OK = 8;
	public static final int INSCRIPCION_OK_ADMISION = 33;
	public static final int INSCRIPCION_ERROR = 9;
	public static final int INSCRIPCION_PROGRAMA_ERROR = 11;
	public static final int PREINSCRIPCION_ERROR = 10;
	public static final int PREINSCRIPCION_ESTADO = 13;
	public static final int PREINSCRIPCION_EXISTE = 18;

	public static final int INSCRIPCION_EXISTE = 14;

	public static final int PLAN_ACADEMICO = 15;
	public static final int PLAN_ACADEMICO_ASIGNATURA = 20;
	public static final int PLAN_ESTUDIANTE = 16;
	public static final int MATRICULA_ERROR = 17;

	public static final int REQUIERE_CERTIFICADOS = 12;
	public static final int CURSOS_ASIGNATURA = 21;

	public static final int TIEMPO_EXPIRO = 22;

	public static final int OPERACION_EXITOSA = 23;

	public static final int USUARIO_NO_INTERNO = 24;

	public static final int SIN_PERMISOS = 25;

	public static final String OPERACION_EXITOSA_OK = "Registro guardado exitosamente.";

	public static final String OPERACION_EJECUTADA_EXITOSAMENTE = "Por favor ingrese a su correo y verifique su cuenta.";
	public static final String ERROR_EJECUTAR_OPERACION = "Error al ejecutar la operación.";

	public static final String VERIFICACION_EJECUTADA_EXITOSAMENTE = "Su correo fue verificado exitosamente.";
	public static final String VERIFICACION_EXISTE_EXITOSAMENTE = "Su correo ya se encuentra verificado.";
	public static final String ERROR_VERIFICAR_OPERACION = "Error al verificar su correo, intentelo nuevamente.<br><br> "
			+ "Si el error persiste realice el procedimiento de preInscripcion nuevamente.";

	public static final String FACTURA_CORRECTA = "Los datos son correctos continúe con la inscripcion.";
	public static final String FACTURA_ERROR_OPERACION = "La factura no existe o no ha sido pagada.";
	public static final String FACTURA_AUTORIZADA_OPERACION = "La factura esta autorizada.";
	public static final String FACTURA_UTILIZADA_OPERACION = "La factura ya fue utilizada en una inscripcion.";

	public static final String INSCRIPCION_OPERACION = "La inscripcion fue guardada correctamente. Por favor verifique su correo.";
	public static final String INSCRIPCION_OPERACION_ADMISION = "La inscripcion fue guardada correctamente. Por favor consulte su correo para continuar con el proceso.";
	public static final String ERROR_INSCRIPCION_OPERACION = "Error al guardar la inscripcion, intentelo nuevamente.";
	public static final String ERROR_INSCRIPCION_PROGRAMA_OPERACION = "Error al guardar la inscripcion Programa, intentelo nuevamente.";
	public static final String PREINSCRIPCION_ERROR_OPERACION = "Error al consultar la pre-inscripcion.";
	public static final String PREINSCRIPCION_ERROR_ESTADO = "La oferta se encuentra en Pre-Inscripciones, se le informará cuendo se abran las inscripciones.";
	public static final String PREINSCRIPCION_EXISTE_OPERACION = "Ya realizo una Inscripción para esta oferta, ingrese a su correo y continue con el proceso.";

	public static final String INSCRIPCION_EXISTE_OPERACION = "Ya existe una inscripcion para esta oferta.";

	public static final String MATRICULA_ERROR_OPERACION = "Error al tratar de crear la matricula.";

	public static final String PLAN_ACADEMICO_ERROR = "Error al consultar el plan academico o no existe para la oferta seleccionada.";
	public static final String PLAN_ACADEMICO_ASIGNATURA_ERROR = "Error al consultar el Plan Academico Asignatura.";
	public static final String PLAN_ESTUDIANTE_ERROR = "Error al tratar de crear el plan Estudiante, intentelo de nuevo.";
	public static final String REQUIERE_CERTIFICADOS_OK = "Su inscripcion fue guardada correctamente.";

	public static final String CURSOS_ASIGNATURA_OPERACION = "Error al consultar los cursos de la asignatura";

	public static final String TIEMPO_EXPIRO_OPERACION = "El tiempo para verificar su cuenta ya expiro.";

	public static final String USUARIO_NO_INTERNO_MENSAJE = "Esta oferta no esta dirijida a su tipo de usuario";

	public static final String SIN_PERMISOS_MENSAJE = "Usted no tiene permisos para realizar esta operación";

	int codigo;
	boolean exito;
	String mensaje;
	private Object body;

	public Respuesta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setRespuesta(int codigo, boolean exito, String mensaje) {
		this.codigo = codigo;
		this.exito = exito;
		this.mensaje = mensaje;
	}

	public Respuesta(int codigo, boolean exito, String mensaje, Object body) {
		super();
		this.codigo = codigo;
		this.exito = exito;
		this.mensaje = mensaje;
		this.body = body;
	}

	public void setRespuesta(int tipo) {
		if (tipo == OPERACION_EXITOSA) {
			this.codigo = OPERACION_EXITOSA;
			this.exito = true;
			this.mensaje = OPERACION_EXITOSA_OK;
		}

		if (tipo == SIN_PERMISOS) {
			this.codigo = SIN_PERMISOS;
			this.exito = false;
			this.mensaje = SIN_PERMISOS_MENSAJE;
		}

		if (tipo == EJECUCION_OK) {
			this.codigo = EJECUCION_OK;
			this.exito = true;
			this.mensaje = OPERACION_EJECUTADA_EXITOSAMENTE;
		}

		if (tipo == EJECUCION_ERROR) {
			this.codigo = EJECUCION_ERROR;
			this.exito = false;
			this.mensaje = ERROR_EJECUTAR_OPERACION;
		}

		if (tipo == VERIFICADO_OK) {
			this.codigo = VERIFICADO_OK;
			this.exito = true;
			this.mensaje = VERIFICACION_EJECUTADA_EXITOSAMENTE;
		}

		if (tipo == VERIFICADO_EXISTE) {
			this.codigo = VERIFICADO_EXISTE;
			this.exito = true;
			this.mensaje = VERIFICACION_EXISTE_EXITOSAMENTE;
		}

		if (tipo == VERIFICADO_ERROR) {
			this.codigo = EJECUCION_ERROR;
			this.exito = false;
			this.mensaje = ERROR_VERIFICAR_OPERACION;
		}

		if (tipo == FACTURA_OK) {
			this.codigo = FACTURA_OK;
			this.exito = true;
			this.mensaje = FACTURA_CORRECTA;
		}

		if (tipo == FACTURA_ERROR) {
			this.codigo = FACTURA_ERROR;
			this.exito = false;
			this.mensaje = FACTURA_ERROR_OPERACION;
		}

		if (tipo == FACTURA_AUTORIZADA_OK) {
			this.codigo = FACTURA_AUTORIZADA_OK;
			this.exito = true;
			this.mensaje = FACTURA_AUTORIZADA_OPERACION;
		}

		if (tipo == FACTURA_UTILIZADA) {
			this.codigo = FACTURA_UTILIZADA;
			this.exito = false;
			this.mensaje = FACTURA_UTILIZADA_OPERACION;
		}

		if (tipo == INSCRIPCION_OK) {
			this.codigo = INSCRIPCION_OK;
			this.exito = true;
			this.mensaje = INSCRIPCION_OPERACION;
		}

		if (tipo == INSCRIPCION_OK_ADMISION) {
			this.codigo = INSCRIPCION_OK_ADMISION;
			this.exito = true;
			this.mensaje = INSCRIPCION_OPERACION_ADMISION;
		}

		if (tipo == INSCRIPCION_ERROR) {
			this.codigo = INSCRIPCION_ERROR;
			this.exito = false;
			this.mensaje = ERROR_INSCRIPCION_OPERACION;
		}

		if (tipo == PREINSCRIPCION_ERROR) {
			this.codigo = PREINSCRIPCION_ERROR;
			this.exito = false;
			this.mensaje = PREINSCRIPCION_ERROR_OPERACION;
		}

		if (tipo == PREINSCRIPCION_ESTADO) {
			this.codigo = PREINSCRIPCION_ESTADO;
			this.exito = false;
			this.mensaje = PREINSCRIPCION_ERROR_ESTADO;
		}

		if (tipo == INSCRIPCION_PROGRAMA_ERROR) {
			this.codigo = INSCRIPCION_PROGRAMA_ERROR;
			this.exito = false;
			this.mensaje = ERROR_INSCRIPCION_PROGRAMA_OPERACION;
		}

		if (tipo == USUARIO_NO_INTERNO) {
			this.codigo = USUARIO_NO_INTERNO;
			this.exito = false;
			this.mensaje = USUARIO_NO_INTERNO_MENSAJE;
		}

		if (tipo == REQUIERE_CERTIFICADOS) {
			this.codigo = REQUIERE_CERTIFICADOS;
			this.exito = true;
			this.mensaje = REQUIERE_CERTIFICADOS_OK;
		}

		if (tipo == INSCRIPCION_EXISTE) {
			this.codigo = INSCRIPCION_EXISTE;
			this.exito = true;
			this.mensaje = INSCRIPCION_EXISTE_OPERACION;
		}

		if (tipo == PLAN_ACADEMICO) {
			this.codigo = PLAN_ACADEMICO;
			this.exito = true;
			this.mensaje = PLAN_ACADEMICO_ERROR;
		}

		if (tipo == PLAN_ACADEMICO_ASIGNATURA) {
			this.codigo = PLAN_ACADEMICO_ASIGNATURA;
			this.exito = true;
			this.mensaje = PLAN_ACADEMICO_ASIGNATURA_ERROR;
		}

		if (tipo == PLAN_ESTUDIANTE) {
			this.codigo = PLAN_ESTUDIANTE;
			this.exito = true;
			this.mensaje = PLAN_ESTUDIANTE_ERROR;
		}

		if (tipo == MATRICULA_ERROR) {
			this.codigo = MATRICULA_ERROR;
			this.exito = false;
			this.mensaje = MATRICULA_ERROR_OPERACION;
		}

		if (tipo == PREINSCRIPCION_EXISTE) {
			this.codigo = PREINSCRIPCION_EXISTE;
			this.exito = false;
			this.mensaje = PREINSCRIPCION_EXISTE_OPERACION;
		}

		if (tipo == CURSOS_ASIGNATURA) {
			this.codigo = CURSOS_ASIGNATURA;
			this.exito = false;
			this.mensaje = CURSOS_ASIGNATURA_OPERACION;
		}

		if (tipo == TIEMPO_EXPIRO) {
			this.codigo = TIEMPO_EXPIRO;
			this.exito = false;
			this.mensaje = TIEMPO_EXPIRO_OPERACION;
		}
	}

	public Respuesta(int ejecucionOk) {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public boolean isExito() {
		return exito;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the body
	 */
	public Object getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(Object body) {
		this.body = body;
	}
}