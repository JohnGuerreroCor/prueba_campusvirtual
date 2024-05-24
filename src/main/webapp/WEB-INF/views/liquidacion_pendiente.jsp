<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				
 	        	<p class="text-post-mail">Señor usuario, <b>Intentelo mas tarde, </b></p>
	            <br>
	 	        <p class="text-post-mail">La oferta esta en proceso de creación de la Liquidación.</p>
		  		<br>
		</md-content>

	<jsp:include page = "/footer"></jsp:include>
	</div>
	<jsp:include page = "/jslibs"></jsp:include>
    <!-- Oferta module -->
    <script type="text/javascript" src="/campusvirtual/app/controllers/confirmarCorreo/ConfirmarCorreoController.js"></script>
</body>
</html>
