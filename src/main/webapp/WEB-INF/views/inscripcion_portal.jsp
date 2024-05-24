<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	Date dNow = new Date();
	SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
	String currentDate = ft.format(dNow);
%>
<style type="text/css">
/*
.md-datepicker-input-container {
	width: 85%;
	margin: auto;
}

.md-datepicker-input {
	width: 100%;
}
*/
.label-date {
	font-size: 11px;
	margin-left: 64px;
	color: #7d7d7d;
}
</style>
<jsp:include page="/head"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="app/assets/css/inscripciones.css" />

<body ng-controller="InscripcionController as inscripcionCtrl">

	<div class="se-pre-con"></div>
	<jsp:include page="/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		<jsp:include page="/menu"></jsp:include>
		<jsp:include page="/formPqr"></jsp:include>
		<!-- -------INSCRIPCIONES---------- -->

		<md-content class="bg-main-white subsections" layout="column"
			layout-align="center center" flex>

		<h1 class="title-subsection text-main-red md-headline" flex
			layout="row">Formulario de Inscripción a oferta Académica</h1>

		<div class="container-subsection text-main-white" layout-gt-md="row"
			layout-xs="column">

			<form id="projectForm" " name="projectForm">

				<md-tabs md-dynamic-height="" md-border-bottom=""
					md-selected="inscripcionCtrl.selectedIndex"> <md-tab
					label="Informacion General"> <md-content
					class="md-padding">

				<div ng-show="inscripcionCtrl.requiere.requierePago==1">
					<div layout="row" flex>
						<div layout="column"></div>
						<div layout="column">
							<h2>Validar pago</h2>
						</div>
						<div layout="column"></div>
					</div>
					<div layout="row">
						<div layout="column">&nbsp;</div>
					</div>
					<div layout="row">
						<div layout="column">
							<md-input-container class="md-block" flex-gt-sm>
							<label>Referencia </label> <input
								ng-model="inscripcionCtrl.inscripcion.factura.referencia"
								name="referencia" minlength="7" maxlength="10" type="text"
								valid-number />
							<div ng-messages="projectForm.referencia.$error" role="alert">
								<div ng-message-exp="['minlength', 'maxlength']">Debe
									ingresar el número de referencia.</div>
							</div>
							</md-input-container>
						</div>
						<!-- <div layout="column">
							<md-input-container class="md-block" flex-gt-sm>
							<label>PIN</label> <input
								ng-model="inscripcionCtrl.inscripcion.factura.pin" name="pin"
								minlength="10" maxlength="20" type="text" />
							<div ng-messages="projectForm.pin.$error" role="alert">
								<div ng-message-exp="['minlength', 'maxlength']">Debe
									ingresar el PIN de la factura.</div>
							</div>
							</md-input-container>
						</div> -->
						<input ng-model="inscripcionCtrl.inscripcion.factura.pin" name="pin" value= "000" type="hidden" />
						<div layout="column"></div>
					</div>
				</div>

				<div layout="row" flex>
					<div layout="column" flex="23"></div>
					<div layout="column" flex="54">
						<h2 class="titulo-seccion-formulario">Datos Personales</h2>
					</div>
					<div layout="column" flex="23"></div>
				</div>


				<!-- ------ DATOS PERSONALES -------- -->

				<div class="carddemoBasicUsage" class="md-inline-form">
					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<div flex-gt-sm>
						<!---- TIPO DE DOCUMENTO ----->
						<md-input-container class="md-block"> <label>Tipo
							de identificacion</label> <md-select placeholder="Tipo de identificacion"
							ng-model="inscripcionCtrl.inscripcion.persona.tipoIdentificacion"
							name="tipoIdentificacion" id="tipoIdentificacion"
							ng-change="inscripcionCtrl.validarFechas()"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="tipoIdentificacion"
							ng-repeat="tipoIdentificacion in inscripcionCtrl.tipoIds">
						{{tipoIdentificacion.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					<div flex-gt-sm>
						<!------ NUMERO DE DOCUMENTO ------->
						<md-input-container class="md-block"> <label>Numero
							de Identificación</label> <input
							ng-model="inscripcionCtrl.inscripcion.persona.identificacion"
							name="identificacion" minlength="7" maxlength="15" type="text"
							readonly="readonly" valid-number required>
						<div ng-messages="projectForm.identificacion.$error" role="alert">
							<div ng-message-exp="['required', 'minlength', 'maxlength']">
								Ingrese su número de identificación.</div>
						</div>
						</md-input-container>
					</div>
					<div id="fechaExpedicionInscripcion" flex>
						<!------ FECHA DE EXPEDICION -------->
						<label class="label-date">Fecha de Expedición</label>
						<md-datepicker
							ng-model="inscripcionCtrl.inscripcion.persona.fechaExpedicion"
							md-placeholder="Fecha Expedicion"
							md-min-date="inscripcionCtrl.minDateExpedicion"
							ng-change="inscripcionCtrl.validarFechas()"
							md-max-date="inscripcionCtrl.maxDateExpedicion" flex>

						<div ng-messages="projectForm.fechaExpedicion.$error" role="alert">
							<div ng-show="projectForm.fechaExpedicion.$error.max">La
								fecha excede la fecha permitida.</div>
							<div ng-show="projectForm.fechaExpedicion.$error.min">La
								fecha es menor a la fecha permitida.</div>
							<div ng-message-exp="['required', 'minlength', 'maxlength']">
								Ingrese la fecha de expedición.</div>
						</div>
						</md-datepicker>
						<!-- md-input-container  flex>
												  <label>Fecha Expedicion</label>
												  <md-datepicker ng-model="inscripcionCtrl.inscripcion.persona.fechaExpedicion"
													 md-min-date="inscripcionCtrl.minDate" 
													 md-max-date="inscripcionCtrl.maxDate"></md-datepicker>
													 <div ng-messages="projectForm.fechaExpedicion.$error" role="alert">
														<div ng-show="projectForm.fechaExpedicion.$error.max">
															La fecha excede la fecha permitida.</div>
														<div ng-show="projectForm.fechaExpedicion.$error.min">
															La fecha es menor a la fecha permitida.</div>
														<div ng-message-exp="['required', 'minlength', 'maxlength']">
															Ingrese la fecha de expedición.</div>
													 </div>
												</md-input-container -->
					</div>
					</md-content>
					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<div flex-gt-sm>
						<!---- PAIS LUGAR DE EXPEDICION ------>
						<md-input-container class="md-block"> <label>Lugar
							de Expedición (Pais)</label> <md-select
							placeholder="Lugar de Expedición (Pais)"
							ng-model="inscripcionCtrl.inscripcion.persona.lugarExpedicionPais"
							ng-change="inscripcionCtrl.loadDepartamentos(inscripcionCtrl.inscripcion.persona.lugarExpedicionPais.codigo, 'depsLugarExpedicion')"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="paisLugarExpedicion"
							ng-repeat="paisLugarExpedicion in inscripcionCtrl.paisLugar">
						{{paisLugarExpedicion.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					<div flex-gt-sm>
						<!---- DEPARTAMENTO LUGAR DE EXPEDICION ------>
						<md-input-container class="md-block"> <label>Lugar
							de Expedición (Departamento) </label> <!----> <md-select
							ng-disabled="inscripcionCtrl.inscripcion.persona.lugarExpedicionPais.codigo != 21"
							placeholder="Lugar de Expedición (Departamento)"
							ng-model="inscripcionCtrl.inscripcion.persona.lugarExpedicionD"
							ng-change="inscripcionCtrl.loadMunicipios(inscripcionCtrl.inscripcion.persona.lugarExpedicionD.codigo, 'munsLugarExpedicion')"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="depsLugarExpedicion"
							ng-repeat="depsLugarExpedicion in inscripcionCtrl.depsLugarExpedicion">
						{{depsLugarExpedicion.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					<div flex-gt-sm>
						<!---- MUNICIPIO LUGAR DE EXPEDICION ------>
						<md-input-container class="md-block"> <label>Lugar
							de Expedición (Ciudad)</label> <md-select
							ng-disabled="inscripcionCtrl.inscripcion.persona.lugarExpedicionPais.codigo != 21"
							placeholder="Lugar de Expedición (Ciudad)"
							ng-model="inscripcionCtrl.inscripcion.persona.lugarExpedicion"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="munsLugarExpedicion"
							ng-repeat="munsLugarExpedicion in inscripcionCtrl.munsLugarExpedicion">
						{{munsLugarExpedicion.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					</md-content>
					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<div flex-gt-sm>
						<!------ NOMBRES ------->
						<md-input-container class="md-block"> <label>Nombres</label>
						<input ng-model="inscripcionCtrl.inscripcion.persona.nombres"
							name="nombres" minlength="3" maxlength="50" type="text"
							readonly="readonly" required>
						<div ng-messages="projectForm.nombres.$error" role="alert">
							<div ng-message-exp="['required', 'minlength', 'maxlength']">
								Ingrese sus nombres</div>
						</div>
						</md-input-container>
					</div>
					<div flex-gt-sm>
						<!------- APELLIDOS --------->
						<md-input-container class="md-block"> <label>Apellidos</label>
						<input ng-model="inscripcionCtrl.inscripcion.persona.apellidos"
							name="apellidos" minlength="3" maxlength="50" type="text"
							readonly="readonly" required />
						<div ng-messages="projectForm.apellidos.$error" role="alert">
							<div ng-message-exp="['required', 'minlength', 'maxlength']">
								Ingrese sus apellidos</div>
						</div>
						</md-input-container>
					</div>
					</md-content>
					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<div flex-gt-sm>
						<!----------- GENERO ---------->
						<md-input-container class="md-block"> <label>Genero</label>
						<md-select ng-model="inscripcionCtrl.inscripcion.persona.genero">
						<md-option ng-repeat="genero in inscripcionCtrl.genero"
							value="{{genero.id}}"><!-- ng-disabled="$index === 1" -->
						{{genero.name}} </md-option> </md-select> </md-input-container>
					</div>
					<div flex-gt-sm>
						<!------ ESTADO CIVIL ------->
						<md-input-container class="md-block"> <label>Estado
							Civil</label> <md-select placeholder="Estado Civil"
							ng-model="inscripcionCtrl.inscripcion.persona.estadoCivil"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="ec" ng-repeat="ec in inscripcionCtrl.ecs">
						{{ec.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					<div flex-gt-sm>
						<!-------  GRUPO SANGUINEO ------->
						<md-input-container class="md-block"> <label>Grupo
							Sanguineo</label> <md-select placeholder="Grupo Sanguineo"
							ng-model="inscripcionCtrl.inscripcion.persona.grupoSanguineo"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="gs" ng-repeat="gs in inscripcionCtrl.gss">
						{{gs.nombre}}</md-option> </md-select> </md-input-container>
					</div>
					</md-content>
					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column" layout-xs="column">
					<div flex-gt-sm>
						<!------- CORREO ELECTRONICO --------->
						<md-input-container class="md-block"> <label>Correo
							Electronico</label> <input required type="email"
							ng-model="inscripcionCtrl.inscripcion.persona.email" name="email"
							minlength="10" maxlength="100" readonly="readonly"
							ng-pattern="/^.+@.+\..+$/" />
						<div ng-messages="projectForm.email.$error" role="alert">
							<div
								ng-message-exp="['required', 'minlength', 'maxlength', 'pattern']">
								Ingrese un email valido.</div>
						</div>
						</md-input-container>
					</div>
					<div flex-gt-sm>
						<!------ LENGUA NATIVA ------->
						<md-input-container class="md-block"> <label>Lengua
							Nativa</label> <md-select placeholder="Lengua Nativa"
							ng-model="inscripcionCtrl.inscripcion.persona.lenguaNativa"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="lenguaNativa"
							ng-repeat="lenguaNativa in inscripcionCtrl.lenguaNativa">
						{{lenguaNativa.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					</md-content>
				</div>


				<div class="carddemoBasicUsage">
					<md-content class="md-padding" layout="row" layout-wrap=""
						layout-sm="column">
					<div id="content-buttons">
						<p id="error-inscription" class="text-main-red">{{inscripcionCtrl.mensaje}}</p>
						<md-card-content> <md-button
							ng-disabled="inscripcionCtrl.selectedIndex<=0">Anterior</md-button>
						</md-card-content>
						<md-card-content> <md-button id="color-rojo"
							type="submit" class="bg-main-red text-main-white"
							ng-click="inscripcionCtrl.nextTab()">Siguiente</md-button> </md-card-content>
					</div>
					</md-content>
				</div>

				</md-content> </md-tab> <!-- ------ INFORMACION COMPLEMENTARIA -------- --> <md-tab
					label="Informacion Complementaria">
				<div class="carddemoBasicUsage" class="md-inline-form">
					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<h2 flex-gt-sm layout="row">Lugar y Fecha de Nacimiento</h2>
					</md-content>

					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<div flex-gt-sm>
						<!---- PAIS LUGAR DE EXPEDICION ------>
						<md-input-container class="md-block"> <label>Lugar
							de Nacimiento (Pais)</label> <md-select
							placeholder="Lugar de Nacimiento (Pais)"
							ng-model="inscripcionCtrl.inscripcion.persona.lugarNacimientoPais"
							ng-change="inscripcionCtrl.loadDepartamentos(inscripcionCtrl.inscripcion.persona.lugarNacimientoPais.codigo, 'depsLugarNacimiento')"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="paisLugarNacimiento"
							ng-repeat="paisLugarNacimiento in inscripcionCtrl.paisLugar">
						{{paisLugarNacimiento.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					<div flex-gt-sm>
						<!---- DEPARTAMENTO LUGAR DE EXPEDICION ------>
						<md-input-container class="md-block"> <label>Lugar
							de Nacimiento (Departamento)</label> <md-select
							ng-disabled="inscripcionCtrl.inscripcion.persona.lugarNacimientoPais.codigo != 21"
							placeholder="Lugar de Nacimiento (Departamento)"
							ng-model="inscripcionCtrl.inscripcion.persona.lugarNacimientoD"
							ng-change="inscripcionCtrl.loadMunicipios(inscripcionCtrl.inscripcion.persona.lugarNacimientoD.codigo, 'munsLugarNacimiento')"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="depsLugarNacimiento"
							ng-repeat="depsLugarNacimiento in inscripcionCtrl.depsLugarNacimiento">
						{{depsLugarNacimiento.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					<div flex-gt-sm>
						<!---- MUNICIPIO LUGAR DE EXPEDICION ------>
						<md-input-container class="md-block"> <label>Lugar
							de Nacimiento (Ciudad)</label> <md-select
							ng-disabled="inscripcionCtrl.inscripcion.persona.lugarNacimientoPais.codigo != 21"
							placeholder="Lugar de Nacimiento (Ciudad)"
							ng-model="inscripcionCtrl.inscripcion.persona.lugarNacimiento"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="munsLugarNacimiento"
							ng-repeat="munsLugarNacimiento in inscripcionCtrl.munsLugarNacimiento">
						{{munsLugarNacimiento.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					</md-content>
					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<div flex-gt-sm>
						<!--------- FECHA DE NaCIMIENTO --------->
						<label class="label-date">Fecha de Nacimiento</label>
						<md-datepicker
							ng-model="inscripcionCtrl.inscripcion.persona.fechaNacimiento"
							md-placeholder="Fecha Nacimiento"
							ng-change="inscripcionCtrl.validarFechas()"
							md-min-date="inscripcionCtrl.minDateNacimiento"
							md-max-date="inscripcionCtrl.maxDateNacimiento">

						<div ng-messages="projectForm.fechaNacimiento.$error" role="alert">
							<div ng-show="projectForm.fechaNacimiento.$error.max">La
								fecha excede la fecha permitida.</div>
							<div ng-show="projectForm.fechaNacimiento.$error.min">La
								fecha es menor a la fecha permitida.</div>
							<div ng-message-exp="['required', 'minlength', 'maxlength']">
								Ingrese la fecha de expedición.</div>
						</div>
						</md-datepicker>

						<!-- 
											<md-input-container class="md-block">
												<label>Fecha de nacimiento </label> 
												<input
													ng-model="inscripcionCtrl.inscripcion.persona.fechaNacimiento"
													name="fechaNacimiento" minlength="10" maxlength="10"
													type="date" min="1900-01-01" max="<%=currentDate%>" required />
		
												<div ng-messages="projectForm.fechaNacimiento.$error"
													role="alert">
													<div ng-show="projectForm.fechaNacimiento.$error.max"
														style="font-size: 12px; opacity: 0; padding-top: 5px; padding-right: 5px; padding-left: 0;">La
														fecha excede la fecha permitida.</div>
													<div ng-show="projectForm.fechaNacimiento.$error.min"
														style="font-size: 12px; opacity: 0; padding-top: 5px; padding-right: 5px; padding-left: 0;">La
														fecha es menor a la fecha permitida.</div>
													<div ng-message-exp="['required', 'minlength', 'maxlength']">
														Ingrese su fecha de nacimiento.</div>
												</div>
											</md-input-container>
											-->
					</div>
					<div flex-gt-sm></div>
					<div flex-gt-sm></div>
					</md-content>

					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<h2 flex-gt-sm layout="row">Lugar donde reside actualmente</h2>
					</md-content>

					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">

					<div flex-gt-sm>
						<!---- PAIS LUGAR DE RESIDENCIA ------>
						<md-input-container class="md-block"> <label>Seleccione
							el Pais</label> <md-select placeholder="Seleccione el Pais"
							ng-model="inscripcionCtrl.inscripcion.persona.lugarResidenciaPais"
							ng-change="inscripcionCtrl.loadDepartamentos(inscripcionCtrl.inscripcion.persona.lugarResidenciaPais.codigo, 'depsLugarResidencia')"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="paisLugarResidencia"
							ng-repeat="paisLugarResidencia in inscripcionCtrl.paisLugar">
						{{paisLugarResidencia.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					<div flex-gt-sm>
						<!---- DEPARTAMENTO LUGAR DE RESIDENCIA ------>
						<md-input-container class="md-block"> <label>Seleccione
							el Departamento</label> <md-select
							ng-disabled="inscripcionCtrl.inscripcion.persona.lugarResidenciaPais.codigo != 21"
							placeholder="Seleccione el Departamento"
							ng-model="inscripcionCtrl.inscripcion.persona.lugarResidenciaDepartamento"
							ng-change="inscripcionCtrl.loadMunicipios(inscripcionCtrl.inscripcion.persona.lugarResidenciaDepartamento.codigo, 'munsLugarResidencia')"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="depsLugarResidencia"
							ng-repeat="depsLugarResidencia in inscripcionCtrl.depsLugarResidencia">
						{{depsLugarResidencia.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					<div flex-gt-sm>
						<!---- MUNICIPIO LUGAR DE RESIDENCIA ------>
						<md-input-container class="md-block"> <label>Seleccione
							la Ciudad</label> <md-select
							ng-disabled="inscripcionCtrl.inscripcion.persona.lugarResidenciaPais.codigo != 21"
							placeholder="Seleccione la Ciudad"
							ng-model="inscripcionCtrl.inscripcion.persona.lugarResidencia"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="munsLugarResidencia"
							ng-repeat="munsLugarResidencia in inscripcionCtrl.munsLugarResidencia">
						{{munsLugarResidencia.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					</md-content>
					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<div flex-gt-sm>
						<!------ DIRECCION ------->
						<md-input-container class="md-block"> <label>Dirección</label>
						<input ng-model="inscripcionCtrl.inscripcion.persona.direccion"
							name="direccion" minlength="4" maxlength="255" type="text"
							required />
						<div ng-messages="projectForm.direccion.$error" role="alert">
							<div ng-message-exp="['required', 'minlength', 'maxlength']">
								Ingrese su dirección de residencia.</div>
						</div>
						</md-input-container>
					</div>
					<div flex-gt-sm>
						<!------- BARRIO --------->
						<md-input-container class="md-block" flex-gt-sm flex>
						<label>Barrio</label> <input
							ng-model="inscripcionCtrl.inscripcion.persona.barrio"
							name="barrio" minlength="4" maxlength="255" type="text" required />
						<div ng-messages="projectForm.barrio.$error" role="alert">
							<div ng-message-exp="['required', 'minlength', 'maxlength']">
								Ingrese el barrio de residencia.</div>
						</div>
						</md-input-container>
					</div>
					<div flex-gt-sm>
						<!----------- TELEFONOS ---------->
						<md-input-container class="md-block" flex-gt-sm flex>
						<label>Teléfono</label> <input
							ng-model="inscripcionCtrl.inscripcion.persona.telefonoMovil"
							name="telefonoMovil" minlength="6" maxlength="50" type="text"
							required valid-number />
						<div ng-messages="projectForm.telefonoMovil.$error" role="alert">
							<div ng-message-exp="['required', 'minlength', 'maxlength']">
								Ingrese su número de telefono movil.</div>
						</div>
						</md-input-container>
					</div>
					</md-content>
					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<div flex-gt-sm>
						<!----------- ESTRATO SOCIOECONOMICO	 ---------->
						<md-input-container class="md-block"> <label>Seleccione
							el estrato socioeconomico</label> <md-select
							placeholder="Seleccione el estrato socioeconomico"
							ng-model="inscripcionCtrl.inscripcion.persona.estrato"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="estratoSocioeconomico"
							ng-repeat="estratoSocioeconomico in inscripcionCtrl.ess">
						{{estratoSocioeconomico.nombre}}</md-option> </md-select> </md-input-container>
					</div>
					</md-content>

					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<h2 flex-gt-sm layout="row">Afiliación EPS</h2>
					</md-content>

					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<div flex-gt-sm>
						<!----------- EPS ---------->
						<md-input-container class="md-block"> <label>Seleccione
							la Eps</label> <md-select placeholder="Seleccione la Eps"
							ng-model="inscripcionCtrl.inscripcion.persona.eps"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="eps" ng-repeat="eps in inscripcionCtrl.epss">
						{{eps.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					<div flex-gt-sm>
						<!------- EPS TIPO -->
						<md-input-container class="md-block"> <label>Seleccione
							el Tipo de Afiliacion</label> <md-select
							placeholder="Seleccione el Tipo de Afiliacion"
							ng-model="inscripcionCtrl.inscripcion.persona.tipoAfiliacion"
							ng-change="inscripcionCtrl.identificacionCotizante()"
							ng-model-options="{trackBy: '$value.codigo'}"> <md-option
							ng-value="tipoAfiliacion"
							ng-repeat="tipoAfiliacion in inscripcionCtrl.tipoAfiliacioness">
						{{tipoAfiliacion.nombre}} </md-option> </md-select> </md-input-container>
					</div>
					<div flex-gt-sm>
						<!----------- TELEFONOS ---------->
						<md-input-container class="md-block"> <label>Número
							identificación cotizante </label> <input
							ng-disabled="inscripcionCtrl.inscripcion.persona.tipoAfiliacion.codigo != 2"
							ng-model="inscripcionCtrl.inscripcion.persona.identificacionCotizante"
							name="identificacionCotizante" minlength="7" maxlength="15"
							type="text" required valid-number />
						<div ng-messages="projectForm.identificacionCotizante.$error"
							role="alert">
							<div ng-message-exp="['required', 'minlength', 'maxlength']">
								Ingrese la identificación del cotizante.</div>
						</div>
						</md-input-container>
					</div>
					</md-content>

				</div>

				<div class="carddemoBasicUsage">
					<md-content class="md-padding" layout="row" layout-wrap=""
						layout-sm="column">
					<div id="content-buttons">
						<p id="error-inscription" class="text-main-red">{{inscripcionCtrl.mensaje}}</p>
						<md-card-content> <md-button
							class="bg-main-red text-main-white"
							ng-click="inscripcionCtrl.previousTab()">Anterior</md-button> </md-card-content>
						<md-card-content> <md-button id="color-rojo"
							type="submit" class="bg-main-red text-main-white"
							ng-click="inscripcionCtrl.nextTab()">Siguiente</md-button> </md-card-content>
					</div>
					</md-content>
				</div>


				</md-tab> <!-- ------- ESTUDIOS ANTERIORES ----------- --> <md-tab
					label="Estudios Anteriores">
				<div class="carddemoBasicUsage" class="md-inline-form">
					<md-content class="md-padding" layout-gt-xs="row"
						layout-sm="column">
					<div flex-gt-sm>
						<p align="left">Aquí puede ingresar los estudios anteriores en
							caso de tenerlos, y de ser necesario el soporte enformato pdf,
							sera direccioando para adjuntarlos al momento de guardar la
							informacion.</p>
						<p
							ng-repeat="estudioAnterior in inscripcionCtrl.estudios_anteriores">
						<h3>{{estudioAnterior.titulo}}</h3>
						</p>
						<br> <br>
					</div>
					</md-content>
					<div flex-gt-sm="100" flex>
						<md-toolbar layout="row" class="md-hue-8">
						<div class="md-toolbar-tools bg-main-red text-main-white">
							<span>Listado de Estudios Anteriores</span> <span flex></span>
							<md-button class="md-icon-button"
								ng-click="inscripcionCtrl.agregarNuevoEstudioAnterior('Adicionar')">
							<md-icon aria-label="Añadir"> <i class="fa fa-plus"
								aria-hidden="true"></i></md-icon> </md-button>
						</div>
						</md-toolbar>
						<md-content>

						<p
							ng-repeat="estudioAnterior in inscripcionCtrl.estudios_anteriores"
							width="100%">
						<table width="100%">
							<tr>
								<td width="40px" rowspan="3"><md-icon aria-label="titulo">
									<img
										src="http://image.flaticon.com/icons/png/512/182/182325.png"
										width="30px" height="35px" /> </md-icon></td>
								<td>{{ estudioAnterior.titulo }}</td>
								<td width="50px" rowspan="3"><md-button
										class="md-icon-button"
										ng-click="inscripcionCtrl.eliminarEstudioAnterior(estudioAnterior)">
									<md-icon aria-label="Eliminar"> <img
										src="http://image.flaticon.com/icons/png/512/121/121117.png"
										width="30px" height="30px" /> </md-icon> </md-button></td>
							</tr>
							<tr>
								<td>{{ estudioAnterior.institucion }}</td>
							</tr>
							<tr>
								<td>{{ estudioAnterior.nivel.nombre }}</td>
							</tr>
							<tr>
								<td>{{ estudioAnterior.anio }}</td>
							</tr>
							<tr>
								<td colspan='3'><hr></td>
							</tr>
						</table>


						</p>

						<!-- 
														<md-list ng-repeat="estudioAnterior in inscripcionCtrl.estudios_anteriores" flex> 
															<md-list-item class="md-3-line" ng-click="null"> 
																<md-icon aria-label="titulo">
																	<img src="http://image.flaticon.com/icons/png/512/182/182325.png" width="30px" height="35px"/>
																</md-icon>
																<div class="md-list-item-text" layout="column">
																	<h3>{{ estudioAnterior.titulo }}</h3>
																	<h4>{{ estudioAnterior.institucion }}</h4>
																	<p>{{ estudioAnterior.nivel.nombre }}</p>
																</div>
																<md-button class="md-icon-button" ng-click="inscripcionCtrl.eliminarEstudioAnterior(estudioAnterior)">
																	<md-icon aria-label="Eliminar">
																		<img src="http://image.flaticon.com/icons/png/512/121/121117.png" width="30px" height="30px"/>
																	</md-icon> 
																</md-button> 
															</md-list-item> 
														</md-list>
														 --> </md-content>
					</div>
					<br>
					<p class="text-main-red">Si ha realizado algun estudio por
						favor ingresarlo en el signo mas(+)...</p>
					<br>

					<div class="carddemoBasicUsage">
						<md-content class="md-padding" layout="row" layout-wrap=""
							layout-sm="column">
						<div id="content-buttons">

							<md-card-content> <md-button
								class="bg-main-red text-main-white"
								ng-click="inscripcionCtrl.previousTab()">Anterior</md-button> </md-card-content>
							<md-card-content> <md-button id="color-rojo"
								class="bg-main-red text-main-white"
								ng-click="inscripcionCtrl.nextTab()">Siguiente</md-button> </md-card-content>
						</div>
						</md-content>
					</div>
		</md-content>
	</div>
	</md-content>
	</md-tab>

	<!------------ Archivos --------------- -->
	<md-tab label="Archivos"
		ng-if="inscripcionCtrl.requisitosOferta.length>0"> <md-content
		class="md-padding">
	<div layout="column" flex>

		Recuerde que debe cargar los siguientes documentos:<br> <br>
		<table class="table table-striped">
			<tr>
				<td><b>DOCUMENTO REQUERIDO</b></td>
				<td><b>TIPO</b></td>
			</tr>
			<tr ng-repeat="docs in inscripcionCtrl.requisitosOferta">
				<td align="left">{{docs.descripcion}}</td>
				<td align="center">(pdf)</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<div id="btnCargue" class="options"
			data-ng-click="inscripcionCtrl.adjuntarArchivos()">Seleccionar
			Documentos</div>
		<input type="file" name="imagen" id="file" accept="application/pdf"
			data-ng-model="imagen" file-model="imagen"
			onchange="angular.element(this).scope().filesChanged(this)" multiple
			required="required"> <span
			ng-if="inscripcionCtrl.adjuntos.length>0"><br>Archivos
			seleccionados:</span> <br>

		<table class="table table-striped"
			ng-if="inscripcionCtrl.adjuntos.length>0">
			<tr>
				<td><b>NOMBRE DEL ARCHIVO</b></td>
				<td><b>ELIMINAR</b></td>
			</tr>
			<tr data-ng-repeat="adj in inscripcionCtrl.adjuntos">
				<td align="left">{{adj.name}}</td>
				<td align="center"><md-icon aria-label="Eliminar"> <img
						alt="flat bin" title="flat bin"
						src="http://image.flaticon.com/icons/png/512/121/121117.png"
						ng-click="inscripcionCtrl.filesEliminar(adj)" width="25px"
						height="20px" /></md-icon></td>
			</tr>
		</table>

		<div class="carddemoBasicUsage">
			<md-content class="md-padding" layout="row" layout-wrap=""
				layout-sm="column">
			<div id="content-buttons">
				<p id="error-inscription" class="text-main-red">{{inscripcionCtrl.mensaje}}</p>
				<md-card-content> <md-button
					class="bg-main-red text-main-white"
					ng-click="inscripcionCtrl.previousTab()">Anterior</md-button> </md-card-content>
				<md-card-content> <md-button id="color-rojo"
					class="bg-main-red text-main-white"
					ng-click="inscripcionCtrl.nextTab()">Siguiente</md-button> </md-card-content>
			</div>
			</md-content>
		</div>
	</md-content>
	</div>
	</md-content> </md-tab>


	<!------------ MOTIVACION --------------- -->
	<md-tab label="Motivación"> <md-content class="md-padding">
	<div layout="column" flex>

		<md-card-title> <md-card-title-text>
		<span class="md-subhead">Sigue los pasos para inscribirte en
			nuestros cursos.</span> <br>
		<br>
		<br>
		</md-card-title-text> </md-card-title>
		<md-card-content>
		<p>
			<b>Paso 1:</b>Todos los datos suministrados en el anterior formulario
			son reales.
		</p>
		<br>
		<p>
			<b>Paso 2:</b>Autorizo para que los datos ingresados sean utilizados
			para fines estadisticos por parte de la Universidad Surcolombiana.
		</p>
		<br>
		<p>
			<b>Paso 3:</b> Autorizo de manera voluntaria, previa, explicita, informada e inequívoca a la Universidad Surcolombiana para tratar mis datos personales para los fines relacionados con su Misión y en especial para     acceder a servicios académicos, educativos en los términos de la Ley 1581 de 2012 y de la Sentencia C748 de 2011.   Consiento que el tratamiento de mis datos se encuentra reglado, pues se encuentra definido en las normas sobre las funciones de las Entidades de Educación Superior.  Los datos personales se encuentran protegidos bajo el principio de finalidad y circulación restringida.
		</p>
		<br>
		<p ng-if="inscripcionCtrl.requisitosOferta.length>0">
			<b>Recuerde:</b> Los documentos adjuntos son reales y corresponden a
			los solicitado:<br> <br>
		<ul class="listRequerimientos">
			<li class="text-main-red"
				ng-repeat="requisitos in inscripcionCtrl.requisitosOferta"><span></span>{{requisitos.descripcion}}</span>
				<span>({{requisitos.nombreTipo}})</span></li>
		</ul>
		</p>
		</md-card-content>
		<!--  </md-card>-->
		<br> <br>

		<div>
			<md-checkbox ng-model="checked" required> He leido la
			motivación y acepto los terminos y condiciones. </md-checkbox>
		</div>

		<div ng-cloak="" class="carddemoBasicUsage">
			<md-content layout-align="end center" class="md-padding" layout="row"
				layout-wrap="" layout-align="center start" layout-xs="column">
			<div flex="100" class="ng-scope ancho" style="text-align: center;"
				ng-if="inscripcionCtrl.botones">
				<md-card-content flex> <md-button
					class="bg-main-red text-main-white md-button md-ink-ripple"
					ng-click="inscripcionCtrl.previousTab()">Anterior</md-button></md-card-content>
				<md-card-content flex> <md-button id="color-rojo"
					type="submit" class="bg-main-red text-main-white"
					ng-click="inscripcionCtrl.submitForm()"
					data-ng-disabled="projectForm.$invalid">GUARDAR</md-button></md-card-content>
			</div>

			<div layout="row" flex="100" layout-xs="column">
				<div layout="column" flex layout-align="center center"
					ng-if="inscripcionCtrl.loading">
					<img ng-src="app/assets/img/admin/gif-load.gif" width="40">
					<p>Guardando, por favor espere...</p>
				</div>
			</div>

			</md-content>
		</div>

	</div></md-tab>

	</md-tabs>

	</form>
	</div>
	</md-content>

	<p id="pagenumber">Paso {{inscripcionCtrl.selectedIndex+1}} de
		{{inscripcionCtrl.max}}</p>
	<br>
	<br>

	<jsp:include page="/footer"></jsp:include>

	</div>

	<jsp:include page="/jslibs"></jsp:include>

	<!-- Inscripciones module -->
	<script type="text/javascript" src="app/directives/fileModel.js"></script>
	<script type="text/javascript"
		src="app/controllers/inscripcion/InscripcionController.js"></script>

</body>
</html>