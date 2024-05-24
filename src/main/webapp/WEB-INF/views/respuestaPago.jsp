<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<jsp:include page = "/head"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="/campusvirtual/app/assets/css/confirmarCorreo.css" />
<body ng-controller="RespuestaPagoController as p">
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		
		<jsp:include page = "/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>
		<md-content id="subsections-confirmar-correo" class="bg-main-white  _md layout-align-center-center layout-column" layout="column" layout-align="center center">
			<br><br><br><br><br>
			<p class="text-post-mail">Señor (a), <b><% out.print(request.getAttribute("persona"));%></b></p>
			<br>
			<% if(request.getAttribute("encontrado").toString()=="1"){%>
	 	    <p class="text-post-mail">El estado de su transación es: <strong><% out.print(request.getAttribute("estadoMensaje"));%></strong></p>
		  	<br>
		  	<% if(request.getAttribute("estado").toString().equals("APPROVED")){ %>
		  	<p class="text-post-mail">La referencia de su pago es: <strong><% out.print(request.getAttribute("referencia"));%></strong> y el pin es: <strong><% out.print(request.getAttribute("pin"));%></strong></p>
		  	<p class="text-post-mail">Recuerde guardar el código de la referencia, ya que con este podra seguir el proceso de la inscripción.</strong></p>
		  	<br><br><strong>Para continuar con la inscripción dar clic <a href="<% out.print(request.getAttribute("rutaInscripcion"));%>" target="_blank ">aquí</a></strong>
 	       <% 
 	       		}
			}else{
				if(request.getAttribute("encontrado").toString()=="2"){
			%>
				<p class="text-post-mail"><strong><% if(request.getAttribute("estado").equals("REJECTED")){ out.print("RECHAZADA"); }else{ if(request.getAttribute("estado").equals("PENDING")){ out.print("PENDIENTE"); }else{ if(request.getAttribute("estado").equals("APPROVED")){ out.print("APROBADA"); }else{ out.print(request.getAttribute("estado")); } } }%></strong></p>
				<p class="text-post-mail">El estado de su transación es: <strong><% out.print(request.getAttribute("estadoMensaje"));%></strong></p>
				<p class="text-post-mail">La referencia de su pago es: <strong><% out.print(request.getAttribute("referencia"));%></strong></p>
				
			<% }else{ %>
			<p class="text-post-mail">La referencia de su factura es: <strong><% out.print(request.getAttribute("referencia"));%></strong></p>
				<p class="text-post-mail">Petición no encontrada o en estado Pendiente.</p>
			<% }
			} %>
			<br><br><br><br><br>
		
		</md-content>

	<jsp:include page = "/footer"></jsp:include>
	</div>
	<jsp:include page = "/jslibs"></jsp:include>
    <!-- Oferta module -->
    <script type="text/javascript" src="/campusvirtual/app/controllers/respuestaPago/RespuestaPagoController.js"></script>
</body>
</html>
