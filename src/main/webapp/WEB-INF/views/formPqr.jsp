<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="form-pqr" ng-controller="SoporteController as f">
	<div id="sliderForm">
		
		<div id="sidebar" onclick="open_panel()">
			<md-tooltip md-visible="true"  md-direction="left">Soporte</md-tooltip>
			<img alt="Formulario PQR" title="Formulario PQR" src="{{f.rutaPortal}}app/assets/img/support.png"/>
		</div>
		<div id="header">
			<form id="formSoporte" name="formSoporte" >
				
				<md-input-container>
					<label for="pqrNombre">Nombre</label><input id="pqrNombre" type="text" name="nombre" ng-model="info.nombre"  maxlength="20"  required/>
					<small class="text-main-red" ng-show="formSoporte.nombre.$dirty && formSoporte.nombre.$invalid">
						<small ng-show="formSoporte.nombre.$error.required">Su nombre es requerido.</small>
					</small>
				</md-input-container>
				
				<md-input-container>
					<label for="pqrEmail">Correo</label><input id="pqrEmail" type="email" name="correo" ng-model="info.correo" maxlength="50" required/>
					<small class="text-main-red" ng-show="formSoporte.correo.$dirty && formSoporte.correo.$invalid">
						<small ng-show="formSoporte.correo.$error.required">Su correo es requerido.</small>
						<small ng-show="formSoporte.correo.$error.email">Este correo no es valido.</small>
					</small>
				</md-input-container>
				<md-input-container><label>Asunto</label>
					<md-select  name="asunto" ng-model="info.asunto" required >
				        <md-option value="Error sakai" selected="selected">Error en Sakai</md-option>
				        <md-option value="Error portal">Error en portal</md-option>
						<md-option value="Comentarios">Comentarios</md-option>
						<md-option value="Otro">Otro</md-option>
					</md-select >
				</md-input-container>
				<br><br>
				<small>Detalle en la caja de texto la información necesaria para poder seguír con su caso.</small><br>
					<i><small>Ejemplo:</small></i><br>
					<ul class="margin-left">
						<li><small>Facultad: Ingeniería</small></li>
						<li><small>Programa: Industrial</small></li>
						<li><small>Código: U1200653</small></li>
					</ul>
				<br>
				<md-input-container>
				 <md-tooltip md-direction="top">Especifique su dependencia, facultad, programa y código.</md-tooltip>
				<label for="pqrTextarea">Mensaje</label><textarea id="pqrTextarea" name="mensaje" ng-model="info.mensaje" maxlength="300" rows="4" md-select-on-focus required md-no-asterisk></textarea>
				<small class="text-main-red" ng-show="formSoporte.mensaje.$dirty && formSoporte.mensaje.$invalid">
						<small ng-show="formSoporte.mensaje.$error.required">Debe escribir un mensaje</small>
					</small>
				</md-input-container>
				<div class="loading" ng-hide="f.loading">
					<i class="fa fa-refresh fa-spin fa-3x fa-fw"></i>
				</div>

				<md-button ng-show="f.loading" ng-disabled="formSoporte.$invalid"  ng-click="f.submit(info)">Enviar</md-button>
				<small class="text-main-red" ng-show="formSoporte.$invalid">Debe llenar todos los campos*</small>
			
			</form>
		</div>
	</div>
</div>