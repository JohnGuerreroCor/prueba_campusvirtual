<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<jsp:include page="/head"></jsp:include>
<link rel="stylesheet" type="text/css" href="/campusvirtual/app/assets/css/reserva.css" />
<body ng-controller="BasesDatosController as b">
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		<jsp:include page="/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>

		
			
		<div class="subsections container-subsection text-main-white" flex layout-gt-md="column" layout-sm="column" layout-xs="column">
			<div layout="row">
				<span layout="column" flex=5></span>
				<h1 class="title-vc-section text-main-red md-headline" flex layout="column" hide-sm hide-xs>Reserva de videoconferencia</h1>
				<h1 class="title-vc-section-mobile text-main-red md-headline" flex layout="column" hide-gt-sm>Reserva de videoconferencia</h1>
				<span layout="column" flex=5></span>
			</div>
			<div class="text-main-black" layout-gt-md="row" layout-md="row" layout-sm="column" layout-xs="column">
					<div flex class="image-container" >
						<img src="/campusvirtual/app/assets/img/reserva/1.jpg"  alt="Comunidad de la USCO en biblioteca" title="Estudiantes en la biblioteca">
					</div>
					<div class="informacion-reserva" flex>
						<h2>Descripción</h2>
						
						<p class="paragraph" >El sistema de reserva de espacios de videoconferencia del campus virtual de la Universidad Surcolombiana pretende facilitar la gestión de reservas de los espacios virtuales del centro de educación. Este sistema permite agilizar el proceso de separación de los espacios virtuales por parte de los docentes o tutores para el desarrollo de sus actividades académicas en sus respectivos horarios.</p>
						<h2>Alcance y funciones por usuario</h2>
						<div class="paragraph">
						
							<h4 class="espacio">Perfil para docente</h4>
							<ol class="list-numbers">
								<li>Accede a la opción en el menú solicitar reserva de espacio videoconferencia.</li>
								<li>Selecciona en el calendario el día en que requiere la sala.</li>
								<li>Diligencia el formulario con los campos requeridos para solicitar la sala.</li>
								<li>Espera notificación en el correo con el link de acceso a la sala virtual de videoconferencia.</li>
							</ol>
							<br>
							
							<h4 class="espacio">Administrador por Facultad</h4>
							<ol class="list-numbers ">
								<li>Configura el sistema (Validaciones para la reserva de espacios. Ej: Número de días con anticipación para hacer la solicitud, Número máximo de reservas que puede hacer un usuario por dia/semana).</li>
								<li>Gestionar solicitudes.</li>
							</ol>
							<br>
							
							<h4 class="espacio">Super Administrador</h4>
							<br>
							<ol class="list-numbers">
								<li>Asigna por facultad el número de horas para usar la sala virtual.</li>
								<li>Gestionar solicitudes.</li>
							</ol>
						</div>
						<br>
						
					</div>
					
			</div>
			<br>
			<br>
			
			<div class="text-main-black" flex layout="column">
				<h2>Proceso</h2>
				<p class="paragraph">El Docente es el encargado de solicitar la reserva del espacio para videoconferencia, y el usuario súper administrador del sistema es quien asigna por un tiempo determinado a cada facultad una de estas salas de videoconferencia, de este modo estas estarán a disposición de los docentes que pertenecen a la facultad, también el usuario administrador por facultad será el encargado de gestionar las solicitudes de reserva de espacios virtuales para las videoconferencias y tendrá la opción de establecer configuraciones en el sistema como: con cuantos días de anticipación se permitirá hacer las solicitudes, máximo cuantas solicitudes puede hacer cada usuario por día o semana.</p>
			</div>
			<br>
			<br>	
			<div class="text-main-black" flex layout="row">
				<div flex class="image-container" >
					<img class="image-block" src="/campusvirtual/app/assets/img/reserva/6.jpg" flex alt="Estudio virtual con iMac">
				</div>
				<div flex class="image-container" >
					<img class="image-block" src="/campusvirtual/app/assets/img/reserva/7.jpg" flex alt="Estudio virtual con una tablet">
				</div>
				<div flex class="image-container" >
					<img class="image-block" src="/campusvirtual/app/assets/img/reserva/8.jpg" flex alt="Estudio virtual con un computador">
				</div>
			</div>

		</div>
		
		<md-content class="subsections bg-main-gray " layout="column" layout-align="center center">
        	<br>
        	<h2 class="subtitle text-main-white md-headline" flex hide-sm hide-xs>Uso y condiciones</h2>
			<h2 class="subtitle text-main-white md-headline" flex hide-gt-sm>Uso y condiciones</h2>

			<div class="uso-container" flex>
				<ol class="paragraph text-main-white">
					<li><strong>Pre-condiciones:</strong>
						<br>
						<br>
						<ul class="list-use">
							<li>El usuario debe estar registrado en el Sistema.</li>
							<li>El usuario debe tener el perfil asignado para poder acceder.</li>
							<li>Para solicitar una sala la facultad a la cual pertenezca el usuario docente debe tener asignada el número de horas y no haber copado ya este límite de horas.</li>
							<li>Para que el docente pueda realizar una reserva de espacio virtual para videoconferencia debe de estar la sala disponible.</li>
							<li>Para poder hacer la reserva de espacio virtual se debe cumplir con las validaciones que establezca el administrador como configuraciones.</li>
						</ul>
					</li>
					<br>
					<br>
					<li><strong>Pos-Condiciones:</strong>
						<br>
						<br>
						<ul class="list-use">
							<li>Al usuario docente cuando realice la reserva de la sala virtual a su correo le llegara el link de acceso a esta.</li>
							<li>El usuario puede hacer uso de la sala una vez se le asignen.</li>
						</ul>
					</li>
				</ol>
			</div>
			<br>

			<br>
			<div class="text-main-white" flex>
				<p class="paragraph"> Para ingresar al sistema de reserva videoconferencia debe dar clic en el siguiente botón</p>
			</div>
			<div id="vermas-button-container" layout="row">
				<span flex></span>
				<md-button target="blank" href="${constantes.RUTA_ADMIN}/" class="text-main-white bg-main-red"   >Reserva Videoconferencia <i class="fa fa-video-camera text-main-white" aria-hidden="true"></i></md-button>
				<span flex></span>
			</div>
			<br>
		</md-content>
		
		
		
		<jsp:include page="/footer"></jsp:include>
	</div>
	<jsp:include page="/jslibs"></jsp:include>
	<!-- Oferta module -->
	<script type="text/javascript" src="/campusvirtual/app/controllers/basesdatos/BasesDatosController.js"></script>

</body>
</html>