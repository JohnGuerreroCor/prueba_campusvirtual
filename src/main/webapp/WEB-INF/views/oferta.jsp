<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<jsp:include page="/head"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="/campusvirtual/app/assets/css/oferta.css" />
<body ng-controller="OfertaController as of">
	<a name="myAnchor" id="myAnchorHome"></a>
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		<jsp:include page="/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>

		<div id="filters" layout="column" ng-cloak=""
			class="autocompletedemoBasicUsage buscador">
			<md-content class="md-padding bg-main-white">
			<form ng-submit="$event.preventDefault()">
				<h1 class="title-subsection text-main-red md-headline flex">
					Lista de programas académicos <span ng-if="of.tipo == 0">gratuitos</span>
					<span ng-if="of.tipo == 1">pagos</span>
				</h1>
				<br>
				<div layout="row">
					<p flex>Puedes <b>Buscar</b> la oferta <span ng-if="of.tipo == 0">gratuita</span>
					<span ng-if="of.tipo == 1">paga</span> de tu interes.</p>
					<span flex hide-sm hide-xs></span>
					<md-button id="filters-button" ng-click="of.toggleRight()" 
					ng-hide="of.isOpenRight()" class="bg-main-red text-main-white">
			            Filtros de busqueda
			         </md-button>	
				</div>

				<md-autocomplete ng-disabled="of.isDisabled"
					md-no-cache="of.noCache" md-selected-item="of.selectedItem"
					md-search-text-change="of.searchTextChange(of.searchText) "
					md-search-text="of.searchText"
					md-selected-item-change="of.selectedItemChange(item)"
					md-items="item in of.items | filter:of.searchText"
					md-item-text="item.display" md-min-length="0"
					placeholder="Ingrese el nombre de la oferta de su interes">
				<md-item-template> <span
					md-highlight-text="of.searchText" md-highlight-flags="^i">{{item.display}}</span>
				</md-item-template>
				</md-autocomplete>
			</form>
			</md-content>
		</div>

		<div class="oferta-section"
			class="container-subsection text-main-white" flex layout-gt-md="row"
			layout-xs="column"> <!-- ng-if="of.tipo == undefind"  -->
			
			  <md-sidenav id="sidenav-filters" class="md-sidenav-right md-whiteframe-4dp" md-component-id="right">
					<md-subheader class="md-no-sticky bg-main-ocre text-main-red"><h3>Filtros por facultad</h3></md-subheader>
					<md-list>
				        <md-list-item ng-repeat="oferta in of.facultadesOferta" >
							<md-checkbox ng-click="of.filtroAvanzado(oferta.programa.codigoUaa, '')">
								<p>{{oferta.programa.nombreUaa}}</p> 
							</md-checkbox>
							 <md-divider ></md-divider>
						</md-list-item>
       				

					<div ng-if="of.tipo == undefind">
						<md-subheader class="md-no-sticky bg-main-ocre text-main-red"><h3>Filtros por tipo de curso</h3></md-subheader>
						<md-list-item ng-click="null">
							<md-checkbox ng-click="of.filtroAvanzado('' , 0)">
								<p>Programas académicos gratuitos</p>
							</md-checkbox>
						</md-list-item>
						<md-divider ></md-divider>
						<md-list-item ng-click="null">
							<md-checkbox ng-click="of.filtroAvanzado('' , 1)">
								<p>Programas académicos pagos</p>
							</md-checkbox>
						</md-list-item>
					</div>
					
					<md-divider ></md-divider>
					<md-list-item>
						<md-button id="close-filters" ng-click="of.close()" class="bg-main-red text-main-white">Cerrar</md-buttom>
					</md-list-item>
					
					</md-list>
				</md-sidenav>

			<input type="hidden" id="val" name="val" value="0">
		</div>
		
		<div class="subsections oferta-section"
			class="container-subsection text-main-white" flex layout-gt-md="row"
			layout-xs="column">
			<md-card ng-repeat="it in of.ofertaVisual | filter: of.searchText"
				class="md-whiteframe-4dp">
			<div class="image-container-subsection">
				<img ng-src="https://campusvirtual.usco.edu.co/administrador_campus/ofertaAcademica/imagen?id=2018"
					alt="{{it.programa.nombre}}" class="md-card-image"
					title="{{it.programa.nombre}}">
			</div>
			<md-card-title class="cardTitlePc" hide-xs hide-sm> <md-card-title-text>
			<span class="md-headline title-head" title="{{it.programa.nombre}}">{{it.programa.nombre | limitTo: 70}}{{it.programa.nombre.length > 70 ? '...' : ''}}</span> </md-card-title-text> </md-card-title>
			<md-card-title class="cardTitleMobile" hide-md hide-lg hide-xl>
			<md-card-title-text> <span
				class="md-headline title-head" title="{{it.programa.nombre}}">{{it.programa.nombre | limitTo: 70}}{{it.programa.nombre.length > 70 ? '...' : ''}}</span> </md-card-title-text> </md-card-title> <md-card-content>
			<p class="text-content-card">
				<strong>Unidad académica: </strong><span>{{it.programa.nombreUaa}}</span><br>
				<strong>Inicio de inscripciones: </strong><span class="lowercase">{{it.fechaIncripcionInicio}}</span><br>
				<strong>Finalización de inscripciones: </strong><span
					class="lowercase">{{it.fechaIncripcionFin}}</span><br> <strong>Fecha
					de inicio: </strong><span class="lowercase">{{it.fechaInicio}}</span><br>
				<strong>Fecha de finalización: </strong><span class="lowercase">{{it.fechaFin}}</span><br>
				<strong>Valor: </strong><span class="lowercase"><span ng-if="it.requierePago == 0" class="no-text-transform">Gratuito</span>
					<span id="programCost-oferta" ng-if="it.requierePago == 1" formatting>{{it.valor}}</span></span>
			</p>
			</md-card-content> <md-card-actions layout="row" layout-align="center">
			<md-button
				ng-click="of.redireccionar('/campusvirtual/programa?codigo=',it.codigo)"
				class="see-more-course text-main-white bg-main-red">Detalles</md-button>
			</md-card-actions> </md-card>
		</div>

		<jsp:include page="/footer"></jsp:include>
	</div>
	<jsp:include page="/jslibs"></jsp:include>
	<!-- Oferta module -->
	<script type="text/javascript"
		src="/campusvirtual/app/controllers/oferta/OfertaController.js"></script>
	<script type="text/javascript"
		src="/campusvirtual/app/services/oferta/OfertaService.js"></script>
</body>
</html>