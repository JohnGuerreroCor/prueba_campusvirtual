<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="co.edu.usco.inscripciones.model.Cuestionario"%>
<%@ page isELIgnored="false"%>
<%
	Cuestionario cuestionario = (Cuestionario) request.getAttribute("cuestionario");
%>

<jsp:include page="/head"></jsp:include>
<!--  -->
<link rel="stylesheet" type="text/css"
	href="/campusvirtual/app/assets/css/confirmarCorreo.css" />
<style type="text/css">
.contenido-encuesta {
	width: 70%;
}

.preguntas {
	margin-bottom: 20px;
	border-top: 1px solid #999;
	margin-top: 10px;
	padding-top: 10px;
}

.form-control {
	width: 100%;
}

.text-center {
	text-align: center;
}
</style>
<body ng-controller="EncuestaController as p">
	<div class="se-pre-con"></div>
	<jsp:include page="/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		<jsp:include page="/menu"></jsp:include>
		<jsp:include page="/formPqr"></jsp:include>

		<md-content id="subsections-confirmar-correo"
			class="bg-main-white  _md layout-align-center-center layout-column"
			layout="column" layout-align="center center">
		<h3 class="template-title"><%=cuestionario.getNombre()%></h3>
		<h4>“Aportes de salud para el desarrollo sostenible”</h4>
		<h4>Neiva, 04 al 06 de Septiembre de 2019.</h4>
		<br>
		<br>
		<h3 class="template-title">EVALUACION</h3>
		<br>
		<br>
		<div class="contenido-encuesta">
			<%=cuestionario.getInsctrucciones()%>
		</div>
		<br>
		<div class="contenido-encuesta">
			<div layout-gt-xs="row">
				<md-input-container class="md-block" flex-gt-sm="50">
				<label>Ingrese aqui su Número de Identificación</label> <input name="identificacion"
					data-ng-model="respuestaDTO.identificacion" id="identificacion"
					required="" ng-maxlength="12">
				<div data-ng-messages="frmEncuesta.identificacion.$error">
					<div data-ng-message="required">Campo requerido.</div>
					<div data-ng-message="maxlength">Máximo 12 caracteres.</div>
				</div>
				</md-input-container>
				<!-- <md-input-container class="md-block" flex-gt-sm="50">
				<label class="md-warm">Nombre</label> <input name="nombre"
					id="nombre" required readonly="readonly" ng-maxlength="100">
				</md-input-container> -->
			</div>

			<ul data-ng-repeat="row in p.datos">
				<li class="preguntas">{{row.codigo}}) {{row.descripcion}}
					<div ng-if="row.tipoRespuesta.codigo == 1">
						<div data-ng-repeat="opciones in p.opcionesRespuestas"
							ng-if="row.codigo == opciones.pregunta.codigo">
							<input type="radio" name="pregunta{{row.codigo}}"
								value="{{opciones.codigo}}"> {{
							opciones.respuestaOpciones.descripcion}}<br>
						</div>
					</div>
					<div ng-if="row.tipoRespuesta.codigo == 2">
						<div data-ng-repeat="opciones in p.opcionesRespuestas"
							ng-if="row.codigo == opciones.pregunta.codigo">
							
							<md-input-container class="md-block" flex-gt-sm="100">
								<label class="md-warm">{{opciones.respuestaOpciones.descripcion}}</label> 
								<input type="hidden"
								name="pregunta{{row.codigo}}" 
								id="pregunta{{row.codigo}}" 
								value="{{opciones.codigo}}">
								<textarea
							name="texto{{row.codigo}}" id="texto{{row.codigo}}"
							required ng-maxlength="100"></textarea>
								
							<div data-ng-messages="frmEncuesta.pregunta.$error">
								<div data-ng-message="required">Campo requerido.</div>
								<div data-ng-message="maxlength">Máximo 100 caracteres.</div>
							</div>
							</md-input-container>
							
							
						</div>
					</div>
					<div ng-if="row.adicional">
						<br>
						<md-input-container class="md-block" flex-gt-sm="100">
						<label class="md-warm">{{row.adicional}}</label> <textarea
							name="adicional{{row.codigo}}" id="adicional{{row.codigo}}"
							required ng-maxlength="100"></textarea>
						<div data-ng-messages="">
							<div data-ng-message="required">Campo requerido.</div>
							<div data-ng-message="maxlength">Máximo 100 caracteres.</div>
						</div>
						</md-input-container>
					</div>
				</li>
			</ul>
			<div class="text-center">
				<button class="md-raised md-primary md-button"
					onClick="guardarInfo()" style="height: 42px">Guardar</button>
			</div>

			<br>
		</div>


		</md-content>


		<jsp:include page="/footer"></jsp:include>
	</div>
	<jsp:include page="/jslibs"></jsp:include>
	<!-- Oferta module -->
	<script type="text/javascript"
		src="/campusvirtual/app/controllers/encuesta/EncuestaController.js"></script>
	<script>
		var getUrl = window.location;
		var base_url = getUrl.protocol + "//" + getUrl.host + "/"
				+ getUrl.pathname.split('/')[1] + "/";

		function guardarInfo() {
			event.preventDefault();
			
			var formData = {
				cuestionario : 1,
				identificacion : $('#identificacion').val(),
				listRespuestas : [{
					codigo : $('input[name=pregunta1]:checked').val(),
					adicional : null,
					texto : null,
					rspuestaCuestionario : null,
					preguntaRespuestas : null,
				},{
					codigo : $('input[name=pregunta2]:checked').val(),
					adicional : null,
					rspuestaCuestionario : null,
					preguntaRespuestas : null,
				}
				,{
					codigo : $('input[name=pregunta3]:checked').val(),
					adicional : null,
					texto : null,
					rspuestaCuestionario : null,
					preguntaRespuestas : null,
				},{
					codigo : $('input[name=pregunta4]:checked').val(),
					adicional : null,
					texto : null,
					rspuestaCuestionario : null,
					preguntaRespuestas : null,
				},{
					codigo : $('input[name=pregunta5]:checked').val(),
					adicional : null,
					texto : null,
					rspuestaCuestionario : null,
					preguntaRespuestas : null,
				},{
					codigo : $('input[name=pregunta6]:checked').val(),
					adicional : null,
					texto : null,
					rspuestaCuestionario : null,
					preguntaRespuestas : null,
				},{
					codigo : $('input[name=pregunta7]:checked').val(),
					adicional : null,
					texto : null,
					rspuestaCuestionario : null,
					preguntaRespuestas : null,
				},{
					codigo : $('input[name=pregunta8]:checked').val(),
					adicional : $('#adicional8').val(),
					texto : null,
					rspuestaCuestionario : null,
					preguntaRespuestas : null,
				},{
					codigo : $('#pregunta9').val(),
					adicional : null,
					texto : $('#texto9').val(),
					rspuestaCuestionario : null,
					preguntaRespuestas : null,
				},{
					codigo : $('input[name=pregunta10]:checked').val(),
					adicional : $('#adicional10').val(),
					texto : null,
					rspuestaCuestionario : null,
					preguntaRespuestas : null,
				},{
					codigo : $('#pregunta11').val(),
					adicional : null,
					texto : $('#texto11').val(),
					rspuestaCuestionario : null,
					preguntaRespuestas : null,
				}]
			}

			/*
			
			*/
			console.log(formData);
			// DO POST
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : base_url + "encuesta",
				data : JSON.stringify(formData),
				dataType : 'json',
				success : function(result) {
					console.log(result);
					if (result.exito == false) {
						alert(result.mensaje);
					} else {
						alert(result.mensaje);
						location.reload();
						// setTimeout("location.reload()", 500);
					}
				},
				error : function(e) {
					//msjError("Ocurrio un inconveniente, vuelve a intentarlo!");
				}
			});
		}
	</script>
</body>
</html>
