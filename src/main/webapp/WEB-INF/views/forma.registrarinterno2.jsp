<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" type="text/css" href="app/assets/css/programa-formularios.css"/>
<md-dialog aria-label="Formulario Registrar" ng-cloak="true">
	<form name="inscripcionInternaForm">
	<md-toolbar class="bg-main-red">
	<div class="md-toolbar-tools">
		<h2 class="text-main-white">Inscripción Usuarios</h2>
		<span flex></span>
		<md-button class="md-icon-button" ng-click="cancel()"> <md-icon
			md-svg-src="app/assets/img/icons/ic_close_24px.svg"
			aria-label="Close dialog"></md-icon> </md-button>
	</div>
	</md-toolbar>
	<md-dialog-content>
		<div class="md-dialog-content">
			<div layout-gt-xs="row">
			Esta oferta solo va dirigida a los siguientes estamentos de la Universidad Surcolombiana:
			</div>
			<div layout-gt-xs="row" class="table-container">
			<table border="1">
				<tr>
					<th>Estamento</th>
					<th>Dependencia</th>
				</tr>
				<tbody ng-repeat="confg in tipoUsuario">
					<tr>
						<td>{{confg.nombreUsuarios}}</td>
						<td><p ng-repeat="uaaNom in confg.uaa">- {{uaaNom}}</p></td>
					</tr>
				</tbody>
			</table>
			</div>
			<br>
			<input type="checkbox" ng-model="registrar.isproveedor" name="checkProveedor" id="checkProveedor"> <label>Es usted Proveedor?</label>
			<div layout-gt-xs="row" ng-if="!registrar.isproveedor">
				<md-input-container class="md-block" flex-gt-sm="100"> 
				<md-icon md-svg-src="app/assets/img/icons/ic_person_24px.svg" class="name"></md-icon>
				<input class="" type="text" id="usuario" name="usuario"
					ng-model="registrar.usuario" placeholder="Ingrese su usuario"
					name="usuario" minlength="3" maxlength="65" ng-required="true">
					<div ng-messages="inscripcionInternaForm.usuario.$error" role="alert">
						<div ng-message-exp="['required']"> Campo requerido.</div>
					</div>
					<!-- <p ng-if="loading_consultar"><img ng-src="app/assets/img/admin/gif-load.gif" width="20"> Consultando...</p> -->
				</md-input-container>
				  <div>
			        <md-button ng-disabled="!registrar.usuario" class="md-raised" ng-click="loadValidadUsu(registrar.usuario)">
			          <md-icon class="material-icons" md-svg-src="app/assets/img/icons/ic_search_white_24px.svg" ></md-icon>
			        </md-button>
			      </div>
			</div>
			
			<div layout-gt-xs="row" ng-if="registrar.isproveedor">
				<md-input-container class="md-block" flex-gt-sm="100"> 
				<md-icon md-svg-src="app/assets/img/icons/ic_person_24px.svg" class="name"></md-icon>
				<input class="" type="text" id="cedula" name="cedula"
					ng-model="registrar.cedula" placeholder="Ingrese su número de cedula o NIT"
					name="usuario" minlength="3" maxlength="65" ng-required="true">
					<div ng-messages="inscripcionInternaForm.cedula.$error" role="alert">
						<div ng-message-exp="['required']"> Campo requerido.</div>
					</div>
					<!-- <p ng-if="loading_consultar"><img ng-src="app/assets/img/admin/gif-load.gif" width="20"> Consultando...</p> -->
				</md-input-container>
				  <div>
			        <md-button ng-disabled="!registrar.cedula" class="md-raised" ng-click="loadValidadProveedor(registrar.cedula)">
			          <md-icon class="material-icons" md-svg-src="app/assets/img/icons/ic_search_white_24px.svg" ></md-icon>
			        </md-button>
			      </div>
			</div>
			
			
			<div layout-gt-xs="row" ng-if="!registrar.isproveedor">
				<md-input-container class="md-block" flex-gt-sm="100"> 
				<md-icon md-svg-src="app/assets/img/icons/ic_vpn_key_black_24px.svg" class="name"></md-icon>
				<input class="" type="password" id="pw" name="pw" ng-disabled="!habilitarClave"
					ng-model="registrar.pw" placeholder="Ingrese su clave"
					name="usuario" minlength="3" maxlength="65" ng-required="true">
					<div ng-messages="inscripcionInternaForm.pw.$error" role="alert">
						<div ng-message-exp="['required']"> Campo requerido.</div>
					</div>
				</md-input-container>
			</div>
			<p ng-if="loading_consultar"><img ng-src="app/assets/img/admin/gif-load.gif" width="20"> Consultando...</p>
			<div layout-gt-xs="row">
				{{textoValidacion}}
			</div>			
		</div>		
	</md-dialog-content>
	<md-dialog-actions layout="row" ng-if="botones"> 
	<md-button ng-disabled="!registrar.pw" type="submit" class="md-raised aceptarPreIns" data-ng-disabled="inscripcionInternaForm.$invalid" ng-click="answer('ok')"> Registrarme </md-button> 
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
