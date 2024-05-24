<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/head"></jsp:include>
<link rel="stylesheet" type="text/css" href="/campusvirtual/app/assets/css/basesdatos.css" />

<body ng-controller="BasesDatosController as b">
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		<jsp:include page="/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>
		<h2 class="title-db-section text-main-black md-headline" flex hide-sm hide-xs>Bases de datos bibliográfica</h2>
		<h2 class="title-db-section-mobile text-main-black md-headline" flex hide-gt-sm>Bases de datos bibliográfica</h2>
		
		<div id="subsections" class="container-subsection text-main-white" flex layout-gt-md="column" layout-xs="column">
				
			<md-tabs md-dynamic-height md-border-bottom flex>
				<md-tab label="Recurso privado">
					<div class="container-subsection text-main-white" flex>
						<md-card id="basededatos"  class="md-whiteframe-3dp" ng-repeat="bd in b.listadoBasesDatos" ng-if="bd.tipoAcceso == 'Privado'" flex>
					        <md-card-header>
					          <md-card-avatar class="escudo-basedatos-biblioteca">
					            <img src="app/assets/img/logo-escudo.png" alt ="escudo base de datos de la biblioteca"/>
					          </md-card-avatar>
					          <md-card-header-text>
					            <span class="md-title"><strong><h2>{{bd.nombre}}</h2></strong></span>
					          </md-card-header-text>
					        </md-card-header>
					        <img ng-src="app/assets/img/database/{{bd.imagen}}" class="md-card-image" alt="{{bd.nombre}}" title="{{bd.nombre}}">
					        <md-card-content id="content-container-{{bd.codigo}}" class="show-animation">
					       	 	<p><strong>Área/Tema: </strong>{{bd.areaTema}}</p>
					        	<p><strong>Tipo de acceso: </strong>{{bd.tipoAcceso}}</p>
					          	<p id="description-{{bd.codigo}}"><strong>Descripción:</strong>{{bd.descripcion}}</p>
					        </md-card-content>
					        
					         <p id="expand-{{bd.codigo}}" class="expand space text-main-black" ng-click="b.showingParagraph(bd.codigo)" ng-show = "b.verifyingParagraph(bd.codigo)"><i class="fa fa-plus-square" aria-hidden="true"></i></p>
					         <p id="expand" class="expand text-main-white" ng-hide = "b.verifyingParagraph(bd.codigo)">space</p>
					        <md-card-actions layout="row" layout-align="center">
					          <md-button ng-click="b.redirect(bd.codigo)" class="see-more-course text-main-white bg-main-red">Ingresar</md-button>
					        </md-card-actions>
					      </md-card>
					</div>
				</md-tab>
				<md-tab label="Recurso público">
					<div class="container-subsection text-main-white" flex>
						<md-card id="basededatos"  class="md-whiteframe-3dp" ng-repeat="bd in b.listadoBasesDatos" ng-if="bd.tipoAcceso == 'Publico'" flex>
					        <md-card-header>
					          <md-card-avatar class="escudo-basedatos-biblioteca">
					            <img src="app/assets/img/logo-escudo.png"/>
					          </md-card-avatar>
					          <md-card-header-text>
					            <span class="md-title"><strong><h2>{{bd.nombre}}</h2></strong></span>
					          </md-card-header-text>
					        </md-card-header>
					        <img ng-src="app/assets/img/database/{{bd.imagen}}" class="md-card-image" alt="{{bd.nombre}}" title="{{bd.nombre}}">
					        <md-card-content id="content-container-{{bd.codigo}}" class="show-animation">
					       	 	<p id="area-{{bd.codigo}}"><strong>Área/Tema: </strong>{{bd.areaTema}}</p>
					        	<p id="tipo-{{bd.codigo}}"><strong>Tipo de acceso: </strong>{{bd.tipoAcceso}}</p>
					          	<p id="description-{{bd.codigo}}"><strong>Descripción:</strong>{{bd.descripcion}}</p>
					        </md-card-content>
					        
					         <p id="expand-{{bd.codigo}}" class="expand space text-main-black" ng-click="b.showingParagraph(bd.codigo)" ng-show = " window.onload = b.verifyingParagraph(bd.codigo)"><i class="fa fa-plus-square" aria-hidden="true"></i></p>
					         <p id="expand" class="expand text-main-white" ng-hide = "b.verifyingParagraph(bd.codigo)">space</p>
					        <md-card-actions layout="row" layout-align="center">
					          <md-button ng-click="b.redirect(bd.codigo)" class="see-more-course text-main-white bg-main-red">Ingresar</md-button>
					        </md-card-actions>
					      </md-card>
					</div>
				</md-tab>
			</md-tabs>
		
		</div>
		
		<jsp:include page="/footer"></jsp:include>
	</div>
	<jsp:include page="/jslibs"></jsp:include>
	<!-- Oferta module -->
	<script type="text/javascript" src="/campusvirtual/app/controllers/basesdatos/BasesDatosController.js"></script>

</body>
</html>