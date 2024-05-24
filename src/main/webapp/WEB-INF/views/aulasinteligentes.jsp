<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/head"></jsp:include>
<link rel="stylesheet" type="text/css" href="/campusvirtual/app/assets/css/aulas.css" />
<body ng-controller="BasesDatosController as b">
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		<jsp:include page="/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>
		
		<div class="subsections container-subsection text-main-white" flex layout-gt-md="column" layout-sm="column" layout-xs="column">
		
			<div layout="row">
				<span layout="column" flex=5></span>
				<h1 class="title-vc-section text-main-red" flex hide-sm hide-xs>Aulas interactivas</h1>
				<h1 class="title-vc-section-mobile text-main-red" flex hide-gt-sm>Aulas interactivas</h1>
				<span layout="column" flex=5></span>
			</div>
	
			<div class="text-main-black" layout-gt-md="row" layout-md="row" layout-sm="column" layout-xs="column">
					<div flex class="image-container" >
						<div class="text-main-black" flex layout="row">
							<div flex class="image-container" >
								<img class="image-block" src="/campusvirtual/app/assets/img/aulas/1.jpg" alt="Tablet para las aulas interactivas" title="Tablets para aulas" flex >
							</div>
							<div flex class="image-container">
								<img class="image-block" src="/campusvirtual/app/assets/img/aulas/2.jpg" alt="Camara para las aulas interactivas" title="Camaras para aulas" flex >
							</div>
							<div flex class="image-container" >
								<img class="image-block" src="/campusvirtual/app/assets/img/aulas/3.jpg"  alt="Adecuación para las aulas interactivas" title="Adecuación de aulas" flex >
							</div>
						</div>
					</div>
					<div flex>
						<div class="paragraph" >
							<p>Las ocho aulas inteligentes serán distribuidas de la siguiente manera:</p>
							<br>
							<ol>
								<li class="list-use">
									<strong>Neiva:</strong><br>
									<ul class="list-use">
										<li>2 aulas en el bloque central A-14-138 y A-14-140.</li> 
										<li>1 aula en biblioteca virtual tercer piso</li>
									</ul>
									<p><strong>Lugar: </strong>Sede principal Avenida Pastrana Borrero - Carrera 1,  A-14-136 <br>(AULAS – BLOQUE AULAS DOS), A-14-136 (AULAS – BLOQUE AULAS DOS)</p>
								</li>
								<br>
	
								<li class="list-use">
									<strong>Sede el Altíco:</strong><br>
									<ul class="list-use">
										<li>1 aula Inteligente A-42-202</li>
									</ul>
									<p><strong>Lugar: </strong>A-42-202 (AULA SEGUNDO PISO – SOBRE COCINA)</p>
								</li>
								<br>
					
								<li class="list-use">
									<strong>Facultad de salud:</strong><br>
									<ul class="list-use">
										<li>1 Aula Inteligente en Aula Externa</li>
									</ul>
									<p><strong>Lugar: </strong> AULA EXTERNA (SIN NOMENCLATURA)</p>
								</li>
								<br>
						
								<li class="list-use">
									<strong>SEDE GARZÓN:</strong><br>
									<ul class="list-use">
										<li>1 Aula Inteligente L-N111</li>
									</ul>
									<p><strong>Lugar: </strong> L-N111 (AULA DE DANZAS - BLOQUE LABORATORIOS)</p>
								</li>
								<br>
							
								<li class="list-use">
									<strong>SEDE LA PLATA:</strong><br>
									<ul class="list-use">
										<li>1 Aula Inteligente A-B-149</li>
									</ul>
									<p><strong>Lugar: </strong> A-B-149 (AULA – BLOQUE A, FRENTE COORDINACIÓN)</p>
								</li>
								<br>
						
								<li class="list-use">
									<strong>SEDE PITALITO:</strong><br>
									<ul class="list-use">
										<li>1 Aula Inteligente S-A-210</li>
									</ul>
									<p><strong>Lugar: </strong> S-A-210 (AUDITORIO PISO 2 – BLOQUE AULAS)</p>
								</li>
								
							</ol>
						</div>
						
					</div>
					
			</div>
			<br>
			<br>

		</div>
		<div class="subsections bg-main-gray paragraph text-main-white" flex layout-gt-md="row" layout-sm="column" layout-xs="column">
				<div flex>
					<br>
					<p>	Acorde a los lineamientos del proyecto, las 8 aulas interactivas tendrán los siguientes componentes:</p>
					<br>
					<div class="list-use">
						<ul class="list-use">
							<li>Puntos de datos</li>
							<br>
							<li>Puntos eléctricos</li>
							<br>
							<li>160 Tablets HP Pro Tablet 10"</li>
							<br>
							<li>160 Licencias Class Room Manager</li>
							<br>
							<li>8 Tableros Acrílicos para proyección de 1.20 x 2.00 mt</li>
							<br>
							<li>8 Portátiles para docentes</li>
							<br>
							<li>8 Video proyectores interactivos Bright Link 575Wi+</li>
							<br>
							<li>8 Sistemas de altavoces logitech Z906 5.1</li>
							<br>
							<li>8 Camara conference GROUP LOGITECH incluye micrófono.</li>
							<br>
							<li>8 Access Point.</li>
						</ul>
					</div>
				</div>
				<br>
				<div flex class="image-container" >
					<img class="image-block" src="/campusvirtual/app/assets/img/aulas/4.jpeg" alt="Adecuación, camaras, tables para las aulas interactivas" title="Adecuación, camaras, tables para las aulas interactivas" flex >
				</div>
		</div>
		
		
		<jsp:include page="/footer"></jsp:include>
	</div>
	<jsp:include page="/jslibs"></jsp:include>
	<!-- Oferta module -->
	<script type="text/javascript" src="/campusvirtual/app/controllers/basesdatos/BasesDatosController.js"></script>

</body>
</html>