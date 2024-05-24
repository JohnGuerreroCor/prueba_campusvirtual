<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
	<jsp:include page = "/head"></jsp:include>
	<link rel="stylesheet" type="text/css" href="/campusvirtual/app/assets/css/confirmarCorreo.css" />
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
		            <p style="text-align: justify;">Para continuar con el proceso de inscripción al curso, <strong class="text-main-black"><% out.print(request.getAttribute("programa"));%></strong> dar clic en el siguiente boton:
				    <br><br>
				    <md-button data-ng-disabled="p.btnDisableProv" ng-click="p.redireccionarProv('${constantes.RUTA_PORTAL}/inscripcionProv','<%=request.getAttribute("oferta").toString()%>','<%=request.getAttribute("id").toString()%>')" class="see-more-course text-main-white bg-main-red mas-programas">Inscribirme ahora!</md-button>
				    <p ng-if="p.loading_consultar"><img ng-src="/campusvirtual/app/assets/img/admin/gif-load.gif" width="20"> Enviando...</p>
				    <br><br>
				    <p>{{p.msgResp}}</p>
					</md-content>
				<% }else{ %>
					<p><strong>Esta inscripción no se encuentra vigente.</strong></p>
				<% }%>
				<br><br><br>
			
			</md-content>
		</div>
		<jsp:include page = "/footer"></jsp:include>
		<jsp:include page = "/jslibs"></jsp:include>
	    <!-- Oferta module -->
	    <script type="text/javascript" src="/campusvirtual/app/controllers/confirmarCorreo/ConfirmarCorreoController.js"></script>
	</body>
</html>