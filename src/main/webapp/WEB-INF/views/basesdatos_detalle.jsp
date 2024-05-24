<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="org.springframework.security.core.GrantedAuthority"%>
<%@ page import="co.edu.usco.configuration.UscoGrantedAuthority"%>
<%@ page import="co.edu.usco.configuration.User" %>
<%
User user = (User) request.getAttribute("usuario");
%>
<jsp:include page="/head"></jsp:include>
<link rel="stylesheet" type="text/css" href="/campusvirtual/app/assets/css/basesdatos.css" />
<body ng-controller="BasesDatosController as b">
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		<jsp:include page="/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>
		<div id="subsections" class="oferta-section" class="container-subsection text-main-white" flex layout-gt-md="row" layout-xs="column">
			
			<div class="bases_datos">
				
				<center><h2 class="title-subsection text-main-black md-headline flex alto">Bases de datos</h2></center>
				<%
				String rol = null;
				for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
					UscoGrantedAuthority uscoGrantedAuthority = (UscoGrantedAuthority) grantedAuthority;
					rol = uscoGrantedAuthority.getRole();
				}
				rol=rol.replace("_"," ");
				rol=rol.replace("ROLE","");
				%>
				<%= rol %>
				<br><br>
				<%= user.getUsername() %>
				<br><br>
				hola, <%= user.getPersona().getNombres() + " " + user.getPersona().getApellidos() %>
				
				
			</div>
					
		</div>
		
		<jsp:include page="/footer"></jsp:include>
	</div>
	<jsp:include page="/jslibs"></jsp:include>
	<!-- Oferta module -->
	<script type="text/javascript" src="/campusvirtual/app/controllers/basesdatos/BasesDatosDetalleController.js"></script>

</body>
</html>