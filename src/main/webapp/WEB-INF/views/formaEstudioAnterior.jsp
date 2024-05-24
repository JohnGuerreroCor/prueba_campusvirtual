<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<md-dialog aria-label="Nuevo Estudio Anterior"  ng-cloak>
<style>
._md-select-menu-container {
    z-index: 9999999;
}
</style>
  <form  name="inscripcionForm">
    <md-toolbar>
      <div class="md-toolbar-tools bg-main-red text-main-white">
        <h2 class="title-subsection">Nuevo Estudio Anterior</h2>
        <span flex></span>
        <md-button class="md-icon-button" ng-click="cancel()">
          <md-icon
			md-svg-src="app/assets/img/icons/ic_close_24px.svg"
			aria-label="Close dialog"></md-icon>
        </md-button>
      </div>
    </md-toolbar>
    <md-dialog-content>
      <div class="md-dialog-content">
        <h2>Añade un nuevo título</h2>
        	<label>&nbsp;</label>
        	<!---- NIVEL EDUCATIVO ----->
        	
										<md-input-container class="md-block"> 
											<label>Seleccione el nivel academico</label> 
											<md-select placeholder="Seleccione el nivel academico"
											ng-model="estudioAnterior.nivelAcademico"> 
												<md-option ng-value="na"
												ng-repeat="na in nivelAcademico">
													{{na.nombre}} 
												</md-option> 
											</md-select> 
										</md-input-container>
        	
        	
			
        	<!-- 
							<md-input-container class="md-block cajas-texto" flex-gt-sm flex="30">																
								<label>Nivel Educativo</label> 
								<input class="cajas-texto" ng-model="estudioAnterior.nivel" 
								name="nivel" minlength="3" maxlength="50" type="text" required /> 
								<div ng-messages="inscripcionForm.nivel.$error" role="alert">
						          	<div ng-message-exp="['required', 'minlength', 'maxlength']">
						            	Ingrese el nombre del nivel educativo
						          	</div>
					        	</div>
							</md-input-container>
        	 -->
        	<!------ INSTITUCION  ------->    							
							<md-input-container class="md-block cajas-texto cajas-texto2" flex-gt-sm flex="30">
								<label>Institución Educativa</label> 
								<input class="cajas-texto" ng-model="estudioAnterior.institucion" 
								name="institucion" minlength="3" maxlength="50" type="text" required /> 
								<div ng-messages="inscripcionForm.institucion.$error" role="alert">
						          	<div ng-message-exp="['required', 'minlength', 'maxlength']">
						            	Ingrese el nombre de la institución Educativa
						          	</div>
					        	</div>
							</md-input-container>
					        
			
			<!------ TITULO--------> 
							<md-input-container class="md-block cajas-texto" flex-gt-sm flex="30">
								<label>Titulo Otorgado</label> 
								<input class="cajas-texto" ng-model="estudioAnterior.titulo" 
								name="titulo" minlength="3" maxlength="50" type="text" required /> 
								<div ng-messages="inscripcionForm.titulo.$error" role="alert">
						          	<div ng-message-exp="['required', 'minlength', 'maxlength']">
						            	Ingrese el nombre del título otorgado
						          	</div>
					        	</div>
							</md-input-container>
							
			<!------ ANIO--------> 
							<md-input-container class="md-block cajas-texto" flex-gt-sm flex="30">
								<label>Año de Grado</label> 
								<input class="cajas-texto" ng-model="estudioAnterior.anio" 
								name="titulo" minlength="3" maxlength="4" type="text" required /> 
								<div ng-messages="inscripcionForm.anio.$error" role="alert">
						          	<div ng-message-exp="['required', 'minlength', 'maxlength']">
						            	Ingrese el año de grado
						          	</div>
					        	</div>
							</md-input-container>
					
      </div>
    </md-dialog-content>
    
    
    <md-dialog-actions layout="row">
      	<md-button  type="submit" data-ng-disabled="inscripcionForm.$invalid" flex="20" class="bg-main-red text-main-white" ng-click="answer('ok')"> {{ accion }} </md-button> 
      <span flex></span>
       	<md-button ng-click="answer('cancelar')" style="margin-right:20px;">Cancelar</md-button> 
    </md-dialog-actions>
  </form>
</md-dialog>

