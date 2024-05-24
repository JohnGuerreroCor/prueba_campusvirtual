<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<jsp:include page = "/head"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="/campusvirtual/app/assets/css/pse.css" />
<body ng-controller="PseController as pse">
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		
		<jsp:include page = "/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>
		<md-content id="subsections-confirmar-correo" class="bg-main-white  _md layout-align-center-center layout-column" layout="column" layout-align="center center">
			
 	        <% if(Long.parseLong(request.getAttribute("diferencia").toString()) <= 1){ %>
 	        	
 	        	<p class="text-post-mail"><h2><b>Pago de Facturas a través de PSE</b></h2></p>
	            <br>
	            <md-content class="bg-main-white" flex layout="column">
					
					<p id="page-content">
					
					
						<div id="wrapper">
							<article id="column" class="reciept-color text-main-red-pse"><strong>Información de tu cuenta por pagar</strong></article>							
							<section>
								<article id="column1">Número de Factura</article>
								<article id="column2" class="subir">123456</article>
							</section>
							<section>
								<article id="column1">Nombre del Cliente</article>
								<article id="column2" class="subir"><% out.print(request.getAttribute("persona"));%></article>
							</section>
							<section>
								<article id="column1">Oferta</article>
								<article id="column2" class="subir"><% out.print(request.getAttribute("programa"));%></article>
							</section>
							<section>
								<article id="column1">Valor a Pagar</article>
								<article id="column2" class="subir">$ <%out.print(request.getAttribute("valorTotal").toString()); %></article>
							</section>
							
							<article id="column" class="reciept-color text-main-red-pse"><strong>Información del pagador</strong></article>							
							<section>
								<article id="column1">Nombre del Pagador</article>
								<article id="column2"><input type="text" value="<% out.print(request.getAttribute("persona"));%>"></article>
							</section>
							<section>
								<article id="column1">Tipo de Doc. de Identificación</article>
								<article id="column2"><select>
										<option ng-repeat="tid in pse.tipoIds" value="CC">{{tid.nombre}}</option>
									</select></article>
							</section>
							<section>
								<article id="column1">Número de identificación</article>
								<article id="column2"><input type="text" value="<% out.print(request.getAttribute("cedula"));%>"></article>
							</section>
							<section>
								<article id="column1">Teléfono ó celular</article>
								<article id="column2"><input type="text" value="<% out.print(request.getAttribute("telefono"));%>"></article>
							</section>
							<section>
								<article id="column1">E-mail</article>
								<article id="column2"><input type="text" value="<% out.print(request.getAttribute("email"));%>"></article>
							</section>
							
							<article id="column" class="reciept-color text-main-red-pse"><strong>Seleccionar</strong></article>
							<section>
								<article id="column1">Tipo de Cliente</article>
								<article id="column2"><select>
										<option ng-repeat="tp in pse.tipoPersona" value="CC">{{tp.nombre}}</option>
									</select></article>
							</section>
							<section>
								<article id="column1">Banco</article>
								<article id="column2"><select>
										<option value="0" selected="selected">-- Seleccione un Banco --</option>
										<option ng-repeat="b in pse.bancos" value="CC">{{b.nombre}}</option>
									</select></article>
							</section>
							
							<article id="column">
								<div class="column-full">
									<md-dialog-actions layout="row">
								      <md-button class="bg-main-red text-main-white b-preins md-button ng-scope md-ink-ripple hide-xs hide-sm" ng-click="pse.enviar()" target="_blank" md-autofocus>
								        Aceptar
								      </md-button>
								      <md-button class="bg-main-red text-main-white b-preins md-button ng-scope md-ink-ripple hide-xs hide-sm" onClick="parent.window.close()"><!-- onClick="parent.window.close()" -->
								       Cancelar
								      </md-button>
								    </md-dialog-actions>
								</div>
							</article>
						</div>							
										
					</p>
		          	
				</md-content>

			<% }else{ %>
				<p><strong>Esta inscripción no se encuentra vigente.</strong></p>
			<% }%>
			<br>
		
		</md-content>

	<jsp:include page = "/footer"></jsp:include>
	</div>
	<jsp:include page = "/jslibs"></jsp:include>
    <!-- Oferta module -->
    <script type="text/javascript" src="/campusvirtual/app/controllers/pse/PseController.js"></script>
</body>
</html>
