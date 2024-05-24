
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" type="text/css" href="app/assets/css/programa-formularios.css"/>
<md-dialog aria-label="Formulario Registrar" ng-cloak>
<form name="preInscripcionForm">
	<md-toolbar class="bg-main-red">
	<div class="md-toolbar-tools">
		<h2 class="text-main-white">Pre-Inscripción</h2>
		<span flex></span>
		<md-button class="md-icon-button" ng-click="cancel()"> <md-icon
			md-svg-src="app/assets/img/icons/ic_close_24px.svg"
			aria-label="Close dialog"></md-icon> </md-button>
	</div>
	</md-toolbar>
	<md-dialog-content>
	<div class="md-dialog-content">
		<div layout-gt-xs="row">
			<md-input-container class="md-block cajas-texto" flex-gt-sm flex="30"> <md-icon
				md-svg-src="app/assets/img/icons/ic_person_24px.svg" class="name"></md-icon>
			<input class="cajas-texto" type="text" id="identificacion" name="identificacion"
				ng-model="registrar.persona.identificacion" placeholder="Identificacion"
				data-ng-blur="loadPreInscripcionDatos(registrar.persona.identificacion)"
				name="identificacion" minlength="7" maxlength="15" ng-required="true" valid-number>
				<div ng-messages="preInscripcionForm.identificacion.$error" role="alert">
					<div ng-message-exp="['required']">
						Ingrese su número de identificación.</div>
				</div>
				<p ng-if="loading_consultar"><img ng-src="app/assets/img/admin/gif-load.gif" width="20"> Consultando...</p>
			</md-input-container>
		</div>
		<div layout-gt-xs="row">
			<md-input-container class="md-block cajas-texto" flex-gt-sm flex="30"> <md-icon
				md-svg-src="app/assets/img/icons/ic_person_24px.svg" class="name"></md-icon>
				<input class="cajas-texto" type="text" ng-model="registrar.persona.nombres" id="nombres"
				name="nombres" minlength="3" maxlength="50" ng-pattern="/^[a-zA-Z\s]+$/" placeholder="Nombres" ng-required="true">
				<div ng-messages="preInscripcionForm.nombres.$error" role="alert">
					<div ng-message-exp="['required', 'minlength', 'maxlength','pattern]">
						Ingrese su nombre.</div>
				</div>
			</md-input-container>
			<md-input-container class="md-block cajas-texto" flex-gt-sm flex="30"> <md-icon
				md-svg-src="app/assets/img/icons/ic_person_24px.svg" class="name"></md-icon>
				<input class="cajas-texto" type="text" ng-model="registrar.persona.apellidos" id="apellidos"
				name="apellido" minlength="3" maxlength="50" ng-pattern="/^[a-zA-Z\s]+$/"  placeholder="Apellidos" ng-required="true">
				<div ng-messages="preInscripcionForm.apellido.$error" role="alert">
					<div ng-message-exp="['required', 'minlength', 'maxlength','pattern']">
						Ingrese su apellido.</div>
				</div>
			</md-input-container>
		</div>
		<div layout-gt-xs="row">
		
			<md-input-container class="md-block cajas-texto" flex-gt-sm flex="30">
				<md-icon md-svg-src="app/assets/img/icons/ic_email_24px.svg" class="name"></md-icon>
				<input class="cajas-texto" type="email" placeholder="E-mail" ng-model="registrar.persona.email"
				name="email" minlength="10" maxlength="100" ng-pattern="/^.+@.+\..+$/" required />
				<div ng-messages="preInscripcionForm.email.$error" role="alert">
					<div ng-message-exp="['required', 'minlength', 'maxlength', 'pattern']">
						Ingrese un email valido.
					</div>
				</div>
			</md-input-container>
			
			<md-input-container class="md-block cajas-texto" flex-gt-sm flex="30">
				<md-icon md-svg-src="app/assets/img/icons/ic_email_24px.svg" class="name"></md-icon>
				<input class="cajas-texto" type="email" placeholder="Confirmar E-mail" ng-model="registrar.persona.emailConfirmar"
				name="emailConfirmar" minlength="10" maxlength="100" ng-pattern="/^.+@.+\..+$/" required />
				<div ng-messages="preInscripcionForm.emailConfirmar.$error" role="alert">
					<div ng-message-exp="['required', 'minlength', 'maxlength', 'pattern']">
						Debe confirmar el email.
					</div>
				</div>
			</md-input-container>
		</div>
	</md-dialog-content>
	<md-dialog-actions layout="row" ng-if="botones"> 
	<md-button id="aceptarPreIns" type="submit" class="md-raised aceptarPreIns" data-ng-disabled="preInscripcionForm.$invalid" ng-click="answer('ok')"> {{ accion }} </md-button> 
	<md-button ng-click="cancel()" class="bg-main-red text-main-white boton aceptarPreInsCancelar" style="margin-right:20px;">Cancelar </md-button> </md-dialog-actions>
	
	<div layout="row" flex="100" layout-xs="column">
		<div layout="column" flex layout-align="center center" ng-if="loading">
			<img ng-src="app/assets/img/admin/gif-load.gif" width="40">
			<p>Guardando, por favor espere...</p>
		</div>
	</div>
	
	
</form>
</md-dialog>

<jsp:include page = "/jslibs"></jsp:include>
