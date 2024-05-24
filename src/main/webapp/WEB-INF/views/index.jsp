<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<jsp:include page = "/head"></jsp:include>

<body ng-controller="OfertaController as of" >

	<a name="myAnchor" id="myAnchorHome"></a>
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column"  ng-controller="LightboxController as lb">
		<jsp:include page = "/menu"></jsp:include>
		<div id="slider-container" class="container demo-1" layout="row" >
			<button class="stopSlider" onclick="stopTrigger();"><i class="fa fa-pause-circle" aria-hidden="true"></i></button>
			<button class="playSlider" onclick="playTrigger();"><i class="fa fa-play-circle" aria-hidden="true"></i></button>
			<div class="arrow bounce"><a href="#myAnchor" rel="" id="anchor1" class="anchorLink"><md-tooltip md-visible="true"  md-direction="bottom">Navega el campus virtual</md-tooltip><i class="fa fa-chevron-down" aria-hidden="true"></i></a></div>

            <div id="slider" class="sl-slider-wrapper" layout="row" ng-controller="SliderController as sl">
		
				<div class="sl-slider">
				
					<div id="overlay"></div>
					
					<div class="sl-slide bg-1" ng-repeat="it in sl.slides | orderBy : 'secuencia'" ng-if="it.estado == '1'" data-orientation="horizontal" data-slice1-rotation="-25" data-slice2-rotation="-25" data-slice1-scale="2" data-slice2-scale="2" initslider>
						<div class="sl-slide-inner">	
													
							<img class="bg-slide" src="{{sl.proyecto}}/{{it.url}}" alt="{{it.alt}}" title="{{it.alt}}">
							<p>{{secuencia}}</p>
							<h2 id = "first-text">{{it.descripcion}}</h2>
						</div>
					</div>
					
				</div><!--/sl-slider -->
				
				<nav class="nav-arrows">
					<div class="bg-control-1">
						<span class="nav-arrow-prev">Atrás</span>
					</div>
					<div class="bg-control-2 btnSlideNext">
						<span class="nav-arrow-next">Siguiente</span>
					</div>
				</nav>

				<nav id="nav-dots" class="nav-dots" >
					<span ng-repeat="dot in sl.slides | orderBy : 'secuencia'" ng-class="{'nav-dot-current' : dot.secuencia == 1 }"></span>
				</nav>
								
			</div><!-- /slider-wrapper -->

        </div>
		<a id="myAnchor"></a>
		
		<jsp:include page = "/formPqr"></jsp:include>
		
		<md-content class="subsections bg-main-black-2" layout="column" layout-align="center center">
        	<h1 class="title-subsection text-main-white md-headline" flex>Programas académicos pagos</h1>
        	<div class="vermas-button-container" layout="row" hide-sm  hide-xs>
	        	<span layout="colum" flex></span>
				<md-button ng-click="of.redireccionar('/campusvirtual/oferta?tipo=',1)" class="see-more-course text-main-white bg-main-red mas-programas">Más programas pagos</md-button>
			</div>																				
			<div class="container-subsection text-main-black" flex layout-gt-md="row" layout-xs="column">
				<h2 ng-if="(of.arregloParcialRequierePagoVisual).length == 0" class="text-main-white no-programa">No hay programas para ofertar</h2>
				<md-card ng-repeat="it in of.arregloParcialRequierePagoVisual" class="md-whiteframe-4dp">
					<div class="image-container-subsection">
						<img src="https://campusvirtual.usco.edu.co/administrador_campus/ofertaAcademica/imagen?id={{it.codigo}}" alt="{{it.programa.nombre}}" title="{{it.programa.nombre}}" class="md-card-image">
			        </div>
			        <md-card-title class="cardTitlePc" hide-xs hide-sm>
			          <md-card-title-text>
			            <span class="md-headline title-head" title="{{it.programa.nombre}}">{{it.programa.nombre | limitTo: 70}}{{it.programa.nombre.length > 70 ? '...' : ''}}</span>
			          </md-card-title-text>
			        </md-card-title>
			        <md-card-title class="cardTitleMobile" hide-md hide-lg hide-xl>
			          <md-card-title-text>
			            <span class="md-headline title-head" title="{{it.programa.nombre}}">{{it.programa.nombre | limitTo: 70}}{{it.programa.nombre.length > 70 ? '...' : ''}}</span>
			          </md-card-title-text>
			        </md-card-title>
			        
			        <md-card-content>
			          <p class="text-content-card">
			          	<strong>Unidad académica: </strong><span class="uaa">{{it.programa.nombreUaa}}</span><br>
			          	<strong>Inicio de inscripciones: </strong><span class="lowercase">{{it.fechaIncripcionInicio}}</span><br>
			          	<strong>Finalización de inscripciones: </strong><span class="lowercase">{{it.fechaIncripcionFin}}</span><br>
			          	<strong>Fecha de inicio: </strong><span class="lowercase">{{it.fechaInicio}}</span><br>
			          	<strong>Fecha de finalización: </strong><span class="lowercase">{{it.fechaFin}}</span><br>
			          	<strong>Valor: </strong><span class="lowercase"><span ng-if="it.requierePago == 0" class="no-text-transform">Gratuito</span>
						<span id="programCost-home-1" ng-if="it.requierePago == 1" formatting>{{it.valor}}</span></span>

			          </p>
			        </md-card-content>

			        <md-card-actions layout="row" layout-align="center">
			          <md-button ng-click="of.redireccionar('/campusvirtual/programa?codigo=',it.codigo)" class="see-more-course text-main-white bg-main-red">Más detalles</md-button>

			        </md-card-actions>
			    </md-card>
			</div>

			
			<div class="vermas-button-container-mobile" layout="row" hide-lg  hide-xl hide-md>
	        	<span layout="colum" flex></span>
				<md-button ng-click="of.redireccionar('/campusvirtual/oferta?tipo=',1)" class="see-more-course text-main-white bg-main-red mas-programas" >Más programas pagos</md-button>

			</div>
			
			<small class="text-main-white" hide-xs hide-sm hide-md>Mostrando {{of.start1+1}} a {{of.ultimo1}} de {{of.arregloParcialRequierePago.length}} entradas</small>

			<nav class="nav-arrows" hide-xs hide-sm >
				<div class="bg-control-1 pointer" ng-hide="of.start1<=0" ng-click="of.changeRequierePago(false)">
					<span class="nav-arrow-prev narrow-sections"></span>
				</div>

				<div class="bg-control-2 pointer" ng-hide="(of.start1+of.max1) >= (of.arregloParcialRequierePago.length)" ng-click="of.changeRequierePago(true)">
					<span class="nav-arrow-next narrow-sections"></span>
				</div>
			</nav>
			
		</md-content>	
		
        <md-content name="narrow-down" class="subsections bg-main-white " layout="column" layout-align="center center">
        	
        	<h1 class="title-subsection text-main-red md-headline" flex>Programas académicos <!-- gratuitos --></h1>
	       	
        	<div class="vermas-button-container" layout="row" hide-sm  hide-xs>
				<span layout="colum" flex></span>
				<md-button ng-click="of.redireccionar('/campusvirtual/oferta?tipo=',0)" class="see-more-course text-main-white bg-main-red mas-programas">Más programas gratuitos</md-button>
			</div>
			<div class="container-subsection text-main-white" flex layout-gt-md="row" layout-xs="column">
				<h2 ng-if="(of.arregloParcialNoRequierePagoVisual).length == 0" class="text-main-black no-programa">No hay programas para ofertar</h2>
				<md-card ng-repeat="it in of.arregloParcialNoRequierePagoVisual" class="md-whiteframe-4dp">
					<div class="image-container-subsection">
			        	<img src="https://campusvirtual.usco.edu.co/administrador_campus/ofertaAcademica/imagen?id={{it.codigo}}" alt="{{it.programa.nombre}}" title="{{it.programa.nombre}}" class="md-card-image">
			        </div>
			        <md-card-title class="cardTitlePc" hide-xs hide-sm>
			          <md-card-title-text>
			            <span class="md-headline title-head" title="{{it.programa.nombre}}">{{it.programa.nombre | limitTo: 70}}{{it.programa.nombre.length > 70 ? '...' : ''}}</span>
			          </md-card-title-text>
			        </md-card-title>
			        <md-card-title class="cardTitleMobile" hide-md hide-lg hide-xl>
			          <md-card-title-text>
			            <span class="md-headline title-head" title="{{it.programa.nombre}}">{{it.programa.nombre | limitTo: 70}}{{it.programa.nombre.length > 70 ? '...' : ''}}</span>
			          </md-card-title-text>
			        </md-card-title>
			        <md-card-content>
			          <p class="text-content-card">
			          	<strong>Unidad académica: </strong><span class="uaa">{{it.programa.nombreUaa}}</span><br>
			          	<strong>Inicio de inscripciones: </strong><span class="lowercase">{{it.fechaIncripcionInicio}}</span><br>
			          	<strong>Finalización de inscripciones: </strong><span class="lowercase">{{it.fechaIncripcionFin}}</span><br>
			          	<strong>Fecha de inicio: </strong><span class="lowercase">{{it.fechaInicio}}</span><br>
			          	<strong>Fecha de finalización: </strong><span class="lowercase">{{it.fechaFin}}</span><br>
			          	<strong>Valor: </strong><span class="lowercase"><span ng-if="it.requierePago == 0" class="no-text-transform">
			          	<!-- Gratuito --> 
			          	<i ng-if="it.programa.codigo == 502">
			          		$ 1.815.500
			          	</i>
			          	<!--  --> 
			          	<i ng-if="it.programa.codigo != 502">
			          		Gratuito
			          	</i>
			          	</span>
						<span id="programCost-home-2" ng-if="it.requierePago == 1" formatting>{{it.valor}}</span></span>
			          </p>
			        </md-card-content>

			        <md-card-actions layout="row" layout-align="center">

			          <md-button ng-click="of.redireccionar('/campusvirtual/programa?codigo=',it.codigo)" class="see-more-course text-main-white bg-main-red">Más detalles</md-button>

			        </md-card-actions>
			    </md-card>
			</div>
			
			<div class="vermas-button-container-mobile" layout="row" hide-lg  hide-xl hide-md>
				<span flex></span>
				<md-button ng-click="of.redireccionar('/campusvirtual/oferta?tipo=',0)" class="see-more-course text-main-white bg-main-red mas-programas">Más programas gratuitos</md-button>
			</div>
			
			<small class="text-main-black bg" hide-xs hide-sm hide-md>Mostrando {{of.start+1}} a {{of.ultimo}} de {{of.arregloParcialNoRequierePago.length}} entradas</small>
			
			<nav class="nav-arrows" hide-xs hide-sm>
				<div class="bg-control-1 pointer" ng-hide="of.start<=0" ng-click="of.changeNoRequierePago(false)">
					<span class="nav-arrow-prev narrow-sections"></span>
				</div>

				<div class="bg-control-2 pointer" ng-hide="(of.start+of.max) >= (of.arregloParcialNoRequierePago.length)" ng-click="of.changeNoRequierePago(true)">
					<span class="nav-arrow-next narrow-sections"></span>
				</div>
			</nav>

		</md-content>
		
		<!-- 
		<md-content class="subsections bg-main-black-2" layout="column" layout-align="center center">
        	<h1 class="title-subsection text-main-white md-headline" flex>Programas académicos de apoyo a la presencialidad </h1>
        	<div class="vermas-button-container" layout="row" hide-sm  hide-xs>
				<span flex></span>
			</div>
			<div class="container-subsection text-main-white" flex layout-gt-md="row" layout-xs="column">
				<h3 ng-if="(of.arregloParcialOfertaFormalVirtual).length == 0" class="text-main-white no-programa">No hay programas para ofertar</h3>
				<md-card ng-repeat="it in of.arregloParcialOfertaFormalVirtual" class="md-whiteframe-4dp">
					<div class="image-container-subsection">
			        	<img src="{{of.adminUrl}}ofertaAcademica/imagen?id={{it.codigo}}" alt="{{it.programa.nombre}}" title="{{it.programa.nombre}}" class="md-card-image">
			        </div>
			        <md-card-title class="cardTitlePc" hide-xs hide-sm>
			          <md-card-title-text>
			            <span class="md-headline title-head" title="{{it.programa.nombre}}">{{it.programa.nombre | limitTo: 70}}{{it.programa.nombre.length > 70 ? '...' : ''}}</span>
			          </md-card-title-text>
			        </md-card-title>
			        <md-card-title class="cardTitleMobile" hide-md hide-lg hide-xl>
			          <md-card-title-text>
			            <span class="md-headline title-head" title="{{it.programa.nombre}}">{{it.programa.nombre | limitTo: 70}}{{it.programa.nombre.length > 70 ? '...' : ''}}</span>
			          </md-card-title-text>
			        </md-card-title>
			        
			        <md-card-content>
			          <p class="text-content-card">
			          	<strong>Unidad académica: </strong><span class="uaa">{{it.programa.nombreUaa}}</span><br>
			          	<strong>Inicio de inscripciones: </strong><span class="lowercase">{{it.fechaIncripcionInicio}}</span><br>
			          	<strong>Finalización de inscripciones: </strong><span class="lowercase">{{it.fechaIncripcionFin}}</span><br>
			          	<strong>Fecha de inicio: </strong><span class="lowercase">{{it.fechaInicio}}</span><br>
			          	<strong>Fecha de finalización: </strong><span class="lowercase">{{it.fechaFin}}</span><br>
			          </p>
			        </md-card-content>

			        <md-card-actions layout="row" layout-align="czenter">

			          <md-button ng-href="${constantes.RUTA_SAKAI}/login" class="see-more-course text-main-white bg-main-red">Ingresar</md-button>

			        </md-card-actions>
			    </md-card>
			</div>
			
			<div class="vermas-button-container-mobile" layout="row" hide-lg  hide-xl hide-md>
				<span flex></span>
			</div>
			
			<small class="text-main-white bg" hide-xs hide-sm hide-md>Mostrando {{of.start4+1}} a {{of.ultimo4}} de {{of.ofertasFormalVirtual.length}} entradas</small>

			<nav class="nav-arrows" hide-xs hide-sm>
				<div class="bg-control-1 pointer" ng-hide="of.start4<=0" ng-click="of.changeProgramasFormalVirtual(false)">
					<span class="nav-arrow-prev narrow-sections"></span>
				</div>

				<div class="bg-control-2 pointer" ng-hide="(of.start4+of.max4) >= (of.ofertasFormalVirtual.length)" ng-click="of.changeProgramasFormalVirtual(true)">
					<span class="nav-arrow-next narrow-sections"></span>
				</div>
			</nav>

		</md-content>
		 
		
		<md-content class="subsections bg-main-black-2" layout="column" layout-align="center center">
        	<h1 class="title-subsection text-main-white md-headline" flex>Programas académicos de educación formal</h1>
        	<div class="vermas-button-container" layout="row" hide-sm  hide-xs>
				<span flex></span>
			</div>

			<div class="container-subsection text-main-white" flex layout-gt-md="row" layout-xs="column">
				<h2 ng-if="(of.arregloParcialEducacionFormal).length == 0" class="text-main-black no-programa">No hay programas para ofertar</h2>
				<md-card ng-repeat="it in of.arregloParcialEducacionFormal" class="md-whiteframe-4dp">
					<div class="image-container-subsection">
			        	<img src="https://campusvirtual.usco.edu.co/administrador_campus/ofertaAcademica/imagen?id=2018" alt="{{it.programa.nombre}}" title="{{it.programa.nombre}}" class="md-card-image inactive">
			        </div>
			        <md-card-title class="cardTitlePc" hide-xs hide-sm>
			          <md-card-title-text>
			            <span class="md-headline title-head" title="{{it.programa.nombre}}">{{it.programa.nombre | limitTo: 70}}{{it.programa.nombre.length > 70 ? '...' : ''}}</span>
			          </md-card-title-text>
			        </md-card-title>
			        <md-card-title class="cardTitleMobile" hide-md hide-lg hide-xl>
			          <md-card-title-text>
			            <span class="md-headline title-head" title="{{it.programa.nombre}}">{{it.programa.nombre | limitTo: 70}}{{it.programa.nombre.length > 70 ? '...' : ''}}</span>
			          </md-card-title-text>
			        </md-card-title>
			        
			        <md-card-content>
			          <p class="text-content-card">
			          	<strong>Unidad académica: </strong><span class="uaa">{{it.programa.nombreUaa}}</span><br>
			          	<strong>Inicio de inscripciones: </strong><span class="lowercase">{{it.fechaInicio}}</span><br>
			          	<strong>Finalización de inscripciones: </strong><span class="lowercase">{{it.fechaFin}}</span><br>
			          	<strong>Fecha de inicio: </strong><span class="lowercase">{{it.fechaIncripcionInicio}}</span><br>
			          	<strong>Fecha de finalización: </strong><span class="lowercase">{{it.fechaIncripcionFin}}</span><br>
			          </p>
			        </md-card-content>

			        <md-card-actions layout="row" layout-align="center">
			          <md-button ng-href="${constantes.RUTA_INSCRIPCIONES_FORMAL}" class="see-more-course text-main-white bg-main-red">Inscribirme</md-button>
			        </md-card-actions>
			    </md-card>
			</div>
			
			<div class="vermas-button-container-mobile" layout="row" hide-lg  hide-xl hide-md>
				<span flex></span>
			</div>
			
			<small class="text-main-white" hide-xs hide-sm hide-md>Mostrando {{of.start3+1}} a {{of.ultimo3}} de {{of.ofertasListaEducacionFormal.length}} entradas</small>
			
			<nav class="nav-arrows" hide-xs hide-sm>
				<div class="bg-control-1 pointer" ng-hide="of.start3<=0" ng-click="of.changeProgramasEducacionFormal(false)">
					<span class="nav-arrow-prev narrow-sections"></span>
				</div>

				<div class="bg-control-2 pointer" ng-hide="(of.start3+of.max3) >= (of.ofertasListaEducacionFormal.length)" ng-click="of.changeProgramasEducacionFormal(true)">
					<span class="nav-arrow-next narrow-sections"></span>
				</div>
			</nav>
		</md-content>
		-->
		<jsp:include page = "/footer"></jsp:include>
	</div>
	
	<jsp:include page = "/jslibs"></jsp:include>  
    <!-- Oferta module -->
    <script type="text/javascript" src="app/controllers/oferta/OfertaController.js"></script>
    <script type="text/javascript" src="app/services/oferta/OfertaService.js"></script>
    <script type="text/javascript" src="app/assets/js/narrow-init.js"></script>
</body>
</html>