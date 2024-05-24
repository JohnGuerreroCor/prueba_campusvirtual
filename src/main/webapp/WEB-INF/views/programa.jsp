<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<jsp:include page = "/head"></jsp:include>
<link rel="stylesheet" type="text/css" href="app/assets/css/programa.css" />

<body ng-controller="ProgramaController as p">
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		<jsp:include page = "/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>
        <md-content id="programa-description" class="bg-main-gray" layout="row" layout-align="center center">
        	
			<div id="imagen-programa" flex  hide-xs hide-sm>
				<img ng-src="https://campusvirtual.usco.edu.co/administrador_campus/ofertaAcademica/imagen?id={{p.oferta.codigo}}" alt="{{p.oferta.programa.nombre}}" title="{{p.oferta.programa.nombre}}"><!-- {{p.obtenerImagen(p.oferta.imagen)}} -->
			</div>
			
			<div id="description-programa" flex layout="column">
				<h2 id="title-subsection-programa" class="text-main-white md-headline" flex hide-sm hide-xs>{{p.oferta.programa.nombre}}</h2>
				<h2 id="title-subsection-programa" class="text-main-white md-headline" flex hide-gt-sm>{{p.oferta.programa.nombre}}</h2>
				<img class="imagen-programa-mobile"  ng-src="https://campusvirtual.usco.edu.co/administrador_campus/ofertaAcademica/imagen?id={{p.oferta.codigo}}" alt="{{p.oferta.programa.nombre}}" title="{{p.oferta.programa.nombre}}" flex hide-gt-sm><br><!-- {{p.obtenerImagen(p.oferta.imagen)}} -->
				<!-- More info -->
	          	<strong class="text-main-white">Unidad académica: <small class="text-content-course text-main-gray">{{p.oferta.programa.nombreUaa}}</small></strong><br>
	          	<strong class="text-main-white">Inicio de inscripciones: <small class="text-content-course text-main-gray">{{p.oferta.fechaIncripcionInicio}}</small></strong><br>
	          	<strong class="text-main-white">Finalización de inscripciones: <small class="text-content-course text-main-gray">{{p.oferta.fechaIncripcionFin}}</small></strong><br>
	          	<strong class="text-main-white">Fecha de inicio: <small class="text-content-course text-main-gray">{{p.oferta.fechaInicio}}</small></strong><br>
	          	<strong class="text-main-white">Fecha de finalización: <small class="text-content-course text-main-gray">{{p.oferta.fechaFin}}</small></strong><br>
	          	<small class="text-main-white" ng-show="p.cantidadInscripciones.cantidadInscritos < p.oferta.cupoMaximo || p.oferta.cupoMaximo == -1"><md-checkbox id="accept-terms" ng-model="checked" > He leido y cumplo con los requerimientos de ingreso 
	          																	al programa y deseo realizar la inscripción.</small>
									<!--  Cantidad Maximo: {{p.oferta.cupoMaximo}}
	          																	Cantidad Inscritos: {{p.cantidadInscripciones.cantidadInscritos}}-->
				<div ng-if="p.fechaVence > p.fechaActual">
					<md-button ng-if="(p.cantidadInscripciones.cantidadInscritos < p.oferta.cupoMaximo || p.oferta.cupoMaximo == -1) && (p.oferta.interna == 0) || (p.oferta.requierePago == 1)" ng-disabled="!checked" hide-sm hide-xs class="bg-main-red text-main-white b-preins" ng-click="p.editar('Adicionar')">Inscripción</md-button>
					<md-button ng-if="(p.cantidadInscripciones.cantidadInscritos < p.oferta.cupoMaximo || p.oferta.cupoMaximo == -1) && (p.oferta.interna == 0) || (p.oferta.requierePago == 1)" ng-disabled="!checked" hide-gt-sm class="bg-main-red text-main-white b-preins-mobile" ng-click="p.editar('Adicionar')">Inscripción</md-button>
					
					<md-button ng-if="(p.cantidadInscripciones.cantidadInscritos < p.oferta.cupoMaximo || p.oferta.cupoMaximo == -1) && (p.oferta.interna == 1 && p.oferta.requierePago == 0)" ng-disabled="!checked" hide-sm hide-xs class="bg-main-red text-main-white b-preins" ng-click="p.editar('InsCerrada')">Inscribirme</md-button>
					<md-button ng-if="(p.cantidadInscripciones.cantidadInscritos < p.oferta.cupoMaximo || p.oferta.cupoMaximo == -1) && (p.oferta.interna == 1 && p.oferta.requierePago == 0)" ng-disabled="!checked" hide-gt-sm class="bg-main-red text-main-white b-preins-mobile" ng-click="p.editar('InsCerrada')">Inscribirme</md-button>
	
					<h2 class="text-main-white md-headline" ng-if="p.cantidadInscripciones.cantidadInscritos >= p.oferta.cupoMaximo && p.oferta.cupoMaximo != -1">¡Cupo lleno!</h2>
				</div>
				<div ng-if="p.fechaVence < p.fechaActual">
				Las fechas de inscripción ya pasarón.
				</div>
			</div>
			
		</md-content>
		
		<md-content>
			<md-tabs md-dynamic-height md-border-bottom id="info-programa">
		      <md-tab label="Descripción del programa">
		        <md-content class="md-padding">
		          	<div flex ng-repeat="informacion in p.informacion" class="text-main-black">
						<h2 class="md-headline">{{informacion.titulo}}</h2>	
						<p ng-bind-html="informacion.contenido | unsafe" class="info-programa"></p>						
					</div>
		        </md-content>
		      </md-tab>
		      <md-tab label="Admisión">
		        <md-content class="md-padding">
					<h2 class="md-headline h-requirements" ng-show="(p.requisitos).length > 0">Para el proceso de admisión debe tener en cuenta los siguientes requisitos:</h2>
					<h2 class="md-headline h-requirements" ng-show="(p.requisitos).length == 0">No hay requisitos para ser admitido en este programa académico.</h2>
					<div ng-repeat="requisitos in p.requisitos" class="requirements-course">
						<p>{{requisitos.ofertaRequisitos.descripcion}} ({{requisitos.ofertaRequisitos.nombreTipo}})</p><br>
					</div>
		        </md-content>
		      </md-tab>
		      <md-tab label="Costo">
		        <md-content class="md-padding">
					<h2 class="md-headline h-requirements" ng-if="p.oferta.requierePago == 0">Este programa académico no tiene ningún costo.</h2>
					<h2 class="md-headline h-requirements" ng-if="p.oferta.requierePago == 1">Costo del programa académico</h2>
					<p ng-if="p.oferta.requierePago == 1">Este programa académico tiene un costo de matricula de <strong id="programCost-programa" formatting>{{p.oferta.valor}}</strong>.</p>
		        </md-content>
		      </md-tab>
		    </md-tabs>
		</md-content>
		
		<md-content>

        <md-subheader class="md-no-sticky">Más programas...</md-subheader>
        <md-divider ></md-divider>
        <md-list-item class="md-3-line" ng-repeat="oferta in p.ofertasListaPorUaa" 
        	ng-click="p.redirectOferta(oferta.codigo)" >
          <img ng-src="${constantes.RUTA_ADMIN}/ofertaAcademica/imagen?id={{p.oferta.codigo}}" class="md-avatar" alt="{{oferta.programa.nombre}}" title="{{oferta.programa.nombre}}"/>
          <div class="md-list-item-text" layout="column">
            <h3>{{oferta.programa.nombre}}</h3>
            <h4>{{oferta.programa.nombreUaa}}</h4>
          </div>
        </md-list-item>
        <md-subheader class="md-no-sticky"  ng-if="(p.ofertasListaPorUaa).length == 0" >No hay programas relacionados...</md-subheader>
        <md-divider ></md-divider>
		</md-content>
		<jsp:include page = "/footer"></jsp:include>
	</div>
	
	<jsp:include page = "/jslibs"></jsp:include>

    <!-- Oferta module -->
    <script type="text/javascript" src="app/controllers/programa/ProgramaController.js"></script>

</body>
</html>