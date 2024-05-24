<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/head"></jsp:include>
<link rel="stylesheet" type="text/css" href="/campusvirtual/app/assets/css/docentes.css" />
<body ng-controller="DocentesController as e">
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		<jsp:include page="/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>
		<div id="scene" class="image-student scene border fill" flex hide-sm hide-xs>
			<div id="overlay">
			</div>
			<h1 class="title-vc-section text-main-black" flex>Guías y videotutoriales para docentes</h1>
			<img class="layer expand-width" data-depth="0.10" src="/campusvirtual/app/assets/img/docentes/teachers.jpg" alt="Sección guías y video tutoriales para docentes usco campus virtual" title="Docente con traje">
		</div>
		
		<div id="scene2" class="image-student-mobile scene border fill" flex hide-gt-sm>
			<div id="overlay">
			</div>
			<h1 class="title-vc-section-mobile text-main-black md-headline" flex hide-gt-sm>Guías y videotutoriales para docentes</h1>
			<img class="layer expand-width" data-depth="0.10" src="/campusvirtual/app/assets/img/docentes/teachers.jpg" alt="Sección guías y video tutoriales para docentes usco campus virtual" title="Docente con traje">
		</div>
		
		<div class="subsections container-subsection text-main-white" flex layout="column" >
			<br>
			<br>
			<p class="text-main-black text-description" flex>En esta sección encontrará toda la información necesaria para el adecuado uso de las diferentes herramientas y módulos del campus virtual que están dirigidos a los docentes.</p>
			<br>
			<br>
		  	<h2 class="text-main-black subtitle" flex hide-sm hide-xs>Manuales y guías de la plataforma educativa</h2>
			<h2 class="text-main-black subtitle" flex hide-gt-sm>Manuales y guías de la plataforma educativa</h2>
	
			<p class="text-main-black text-description space">Las guías y manuales de la plataforma educativa están organizadas por módulo, estas pueden ser descargadas y visualizadas dando clic sobre cada uno de los elementos a continuación:</p>
			<br>
			
			<md-grid-list class="gridListdemoBasicUsage"
		        md-cols="2" md-cols-sm="4" md-cols-md="4" md-cols-gt-md="6"
		        md-row-height="1:1" 
		        md-gutter="12px" md-gutter-gt-sm="8px" >
		        
		        <md-grid-tile class="gray modulo-guia" ng-repeat="c in e.guias" ng-if="c.tipo == 'folder'" ng-click="e.showAdvanced($event, c.nombre, c.archivos)">
			      <i class="fa fa-book fa-5x" aria-hidden="true"></i>
			      <md-grid-tile-footer class="text-main-white bg-main-black">
			        <h3>{{c.nombre.replace("_", " ") | removeSpaces | addSpaces}}</h3>
			      </md-grid-tile-footer>
			    </md-grid-tile>
			
			    
		
		  	</md-grid-list>
		  	
		  	<br>
		  	<br>
		  	<h2 class="text-main-black subtitle" flex hide-sm hide-xs>Videos tutoriales de la plataforma educativa</h2>
			<h2 class="text-main-black subtitle" flex hide-gt-sm>Videos tutoriales de la plataforma educativa</h2>
			
			<p class="text-main-black text-description space">Los tutoriales están organizadas por módulos, cada una de las pestañas a continuación corresponde a uno de ellos, esta pestañas además contienen número específico de videos.</p>
			
		      <br>
		      <md-tabs class="list-sub-videos" md-dynamic-height md-border-bottom>
			      <md-tab ng-repeat="a in e.videos" label="{{a.nombre | removeSpaces}}" ng-if="a.tipo == 'folder'">
			        <md-content class="md-padding">
				        <md-grid-list class="gridListdemoBasicUsage"
					        md-cols="2" md-cols-sm="4" md-cols-md="4" md-cols-gt-md="6"
					        md-row-height="1:1" 
					        md-gutter="12px" md-gutter-gt-sm="8px" >
					        
					        <md-grid-tile class="gray modulo-guia" ng-repeat="b in a.archivos | orderBy:'nombre'" ng-if="b.tipo == 'file'" ng-click="e.showVideo($event, b.nombre | removeSpaces,'videos/sakai/docentes/'+ a.nombre + '/' + b.nombre.replace('.mp4',''))">
						      <i class="fa fa-play fa-5x" aria-hidden="true" ng-class-odd="'text-main-white'" ng-class-even="'text-main-white'"></i>
						      <md-grid-tile-footer class="text-align text-main-white bg-main-black">
						        <h3 class="margin">{{b.nombre.replace(".mp4", " ") | removeSpaces | addSpaces}}</h3>
						      </md-grid-tile-footer>
						    </md-grid-tile>
					  	</md-grid-list>
			        </md-content>
			      </md-tab>
			  </md-tabs>
		  	
		
		</div>
		
		<jsp:include page="/footer"></jsp:include>
	</div>
	
	<jsp:include page="/jslibs"></jsp:include>
	<script type="text/javascript" src="/campusvirtual/app/controllers/docentes/DocentesController.js"></script>
	<script type="text/javascript" src="/campusvirtual/app/assets/js/parallax.js"></script>
	
		
	<script>
		// Pretty simple huh?
		var scene = document.getElementById('scene');
		var parallax = new Parallax(scene);
	
		var scene2 = document.getElementById('scene2');
		var parallax2 = new Parallax(scene2);
	</script>
</body>
</html>