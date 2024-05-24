<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<jsp:include page = "/head"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="/campusvirtual/app/assets/css/confirmarCorreo.css" />
<body ng-controller="ConfirmarCorreoController as p">
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		<jsp:include page = "/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>
		<md-content id="subsections-confirmar-correo" class="bg-main-white  _md layout-align-center-center layout-column" layout="column" layout-align="center center">
			
 	        <% if(Long.parseLong(request.getAttribute("diferencia").toString()) <= 1){ %>
 	        	
 	        	<p class="text-post-mail">Señor (a), <b><% out.print(request.getAttribute("persona"));%></b></p>
	            <br>
	 	        <p class="text-post-mail"><strong><% out.print(request.getAttribute("mensaje"));%></strong></p>
		  		<br>
	            <md-content class="bg-main-white" flex layout="column">
	            <% if(request.getAttribute("estado").toString().equals("2")){ %>
		          	<p  style="text-align: justify; max-width: 700px; margin: auto;">El curso, 
		          	<strong class="text-main-black"><% out.print(request.getAttribute("programa"));%></strong>
		          	se encuentra en estado de Pre-Inscripciones, se le enviará un correo electrónico informando que el curso se 
		          	encuentra habilitado para las inscripciones.<br><br> Gracias por su Pre-Inscripción.</p>
		          	
		          	<% }else{ %>
		          		<p  style="text-align: justify;">Para continuar con el proceso de inscripción al curso, <strong class="text-main-black"><% out.print(request.getAttribute("programa"));%></strong>, tenga en cuenta:
			          		<br><br>
				          	 		
					<% if(request.getAttribute("requierePago").toString().equals("1")){%>
							<% if(request.getAttribute("facturaPaga").toString().equals("1")){%> 
								<p  style="text-align: justify;">Ya usted cuenta con una factura paga, los datos son:</p><br>
					          	<p>Referencia Factura: <strong><% out.print(request.getAttribute("factura").toString());%></strong></p>
					          	<p>Pin: <strong><% out.print(request.getAttribute("pin").toString());%></strong></p>
					          	<br><br><strong>Para continuar con la inscripción dar clic <a href="<% out.print(request.getAttribute("rutaInscripcion").toString());%>" target="_blank">aquí</a></strong>
							<% }else{%>
								<br><br>
								<ul class="requirement-list">
						          	<li>Debe realizar el pago de la inscripción</li>
						          	<li>Este tiene un costo de matricula de <strong>$ <% out.print(request.getAttribute("valor"));%></strong> Pesos Colombianos</li>
						          	<li>Si imprime el recibo y paga directamente en el banco, debe esperar 24 horas posterior al pago para poder realizar la inscripción.</li>
						        </ul>
						        
						        <br><br><br>
					          	<p style="text-align: justify;"><strong>Selecciona el modo de pago:</strong></p>
					          	<br><br>
							    <md-radio-group id="selection-method" ng-model="p.modo">
							      <md-radio-button value="ELECTRONICO"><strong>Pago electrónico</strong></md-radio-button>
							      <md-radio-button value="FACTURA"><strong>Imprimir factura</strong></md-radio-button>
							    </md-radio-group>
							    <br>
			
								<table class="receipt text-main-red" >
									<tr class="title-table-receipt">
										<td style="padding-top: 15px;"><strong>Programa</strong></td>
										<td style="padding-top: 15px;"><strong>Modo de pago</strong></td>
										<td style="padding-top: 15px;"><strong>Costo</strong></td>
										<td style="padding-top: 15px;"><strong>Total</strong></td>
									</tr>
									<tr>
										<td style="padding-top: 15px;"><% out.print(request.getAttribute("programa").toString());%></td>
										<td style="padding-top: 15px;">{{ p.modo }}</td>
										<td style="padding-top: 15px;">$ <% out.print(request.getAttribute("valor").toString()); %></td>
										<td style="padding-top: 15px;"><p>$ <%out.print(request.getAttribute("valorTotal").toString()); %></p></td>
									</tr>
									<tr class="reciept-color">
										<td style="padding-top: 15px;"><strong>Total a pagar</strong></td>
										<td style="padding-top: 15px;"></td>
										<td style="padding-top: 15px;"></td>
										<td style="padding-top: 15px;"><p ng-if=" p.modo == 'ELECTRONICO' "><strong>$ <%out.print(request.getAttribute("valorTotal").toString()); %></strong></p>
											<p ng-if=" p.modo == 'FACTURA' "><strong>$ <%out.print(request.getAttribute("valor").toString()); %></strong></p></td>
									</tr>
								</table>
								
								<br><br> 
							<% }%> 
									<% if(!request.getAttribute("facturaPaga").toString().equals("1")){%> 
										<% if(request.getAttribute("requierePago").toString().equals("1")){%>
										<a class="button-online" ng-if=" p.modo == 'FACTURA' " href="${constantes.RUTA_PORTAL}/generarFacturaFisica?cedula=<%=request.getAttribute("cedula").toString()%>&ofa_codigo=<%=request.getAttribute("ofa_codigo").toString()%>" target="blank">
											<img src="/campusvirtual/app/assets/img/imprimir_factura.png" alt="Imprimir factura" title="Imprimir factura">
										</a>
										<a class="button-receitp" ng-if="p.modo == 'ELECTRONICO'" href="https://gaitana.usco.edu.co/generar_facturas/pagosweb_facturas.php" target="_blank" title="Pago electrónico."> <!-- class="fancybox" href="#inline1"  -->
											<img src="/campusvirtual/app/assets/img/pago_electronico.png" alt="Pago electónico" title="Pago electónico"> <!-- ng-click="p.showAdvanced($event)" -->
										</a><br>
										<!--<a class="button-receitp" ng-if="p.modo == 'ELECTRONICO'" href="${constantes.RUTA_PORTAL}/pagoOnline/<%=request.getAttribute("id").toString()%>/<%=request.getAttribute("oferta").toString()%>/<%=request.getAttribute("cedula").toString()%>/" target="_self" title="Pago electrónico."> <!-- class="fancybox" href="#inline1" 
											<img src="/campusvirtual/app/assets/img/pago_electronico.png" alt="Pago electónico" title="Pago electónico"> <!-- ng-click="p.showAdvanced($event)" 
										</a><br>-->
										<a class="button-online" ng-if=" p.modo == 'ELECTRONICO' " href="${constantes.RUTA_PORTAL}/preguntasFrecuentes" target="_blank">Preguntas frecuentes</a>
										<% }else{%>
											<!-- a href="" ng-click="p.redireccionar('${constantes.RUTA_PORTAL}/inscripcion?codigo=','<%=request.getAttribute("oferta").toString()%>','<%=request.getAttribute("id").toString()%>')"><img src="/campusvirtual/app/assets/img/inscribirme.png"></a -->
											<md-button ng-click="p.redireccionar('${constantes.RUTA_PORTAL}/inscripcion?codigo=','<%=request.getAttribute("oferta").toString()%>','<%=request.getAttribute("id").toString()%>')" class="see-more-course text-main-white bg-main-red mas-programas">Inscribirme ahora!</md-button>
										<% }%>
									<% }%> 
						<% }else{%>
							<!-- a href="" ng-click="p.redireccionar('${constantes.RUTA_PORTAL}/inscripcion?codigo=','<%=request.getAttribute("oferta").toString()%>','<%=request.getAttribute("id").toString()%>')"><img src="/campusvirtual/app/assets/img/inscribirme.png"></a -->
							<md-button ng-click="p.redireccionar('${constantes.RUTA_PORTAL}/inscripcion?codigo=','<%=request.getAttribute("oferta").toString()%>','<%=request.getAttribute("id").toString()%>')" class="see-more-course text-main-white bg-main-red mas-programas">Inscribirme ahora!</md-button>
						<% }%>
					<% }%>
				</md-content>

			<% }else{ %>
				<p><strong>Esta inscripción no se encuentra vigente.</strong></p>
			<% }%>
			<br>
		
		</md-content>
	</div>
	<jsp:include page = "/footer"></jsp:include>
	<jsp:include page = "/jslibs"></jsp:include>
    <!-- Oferta module -->
    <script type="text/javascript" src="/campusvirtual/app/controllers/confirmarCorreo/ConfirmarCorreoController.js"></script>
</body>
</html>