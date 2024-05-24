<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<jsp:include page = "/head"></jsp:include>
<style>
md-list-item.md-3-line .md-list-item-text{
padding: 15px;
}
</style>
<link rel="stylesheet" type="text/css" href="/campusvirtual/app/assets/css/confirmarCorreo.css" />
<body>
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		
		<jsp:include page = "/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>
		<md-content id="subsections-confirmar-correo" class="bg-main-white  _md layout-align-center-center layout-column" layout="column" layout-align="center center">
			<h1 class="title-subsection text-main-red md-headline" flex>Politicas de Privacidad y Seguridad</h1>
						
		</md-content>

	<jsp:include page = "/footer"></jsp:include>
	</div>
	<jsp:include page = "/jslibs"></jsp:include>
</body>
</html>
