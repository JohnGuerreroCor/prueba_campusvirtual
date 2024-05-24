<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<jsp:include page = "/head"></jsp:include>
<link rel="stylesheet" type="text/css" href="/campusvirtual/app/assets/css/confirmarCorreo.css" />
<style type="text/css">

.table-container table {
	border-collapse: collapse;
	width: 100%;
}

.table-container th, td {
	text-align: left;
	padding: 3px;
}

.table-container td .btn {
	background: rgba(76, 175, 80, 0.0);
	width: 100%;
	height: 100%;
}

.table-container tr:nth-child(even) {
	background-color: #d8cea3
}

.table-container th {
	background-color: #8D191D;
	font-size: 85%;
	text-align: center;
	color: white;
}

.table-container table td:hover {
	background-color: #421213;
	color: #ffffff;
}

.table-container {
	overflow-x: auto;
}
</style>
<body ng-controller="ConsultarPagoController as p">
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		
		<jsp:include page = "/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>
		<md-content id="subsections-confirmar-correo" class="bg-main-white  _md layout-align-center-center layout-column" layout="column" layout-align="center center">
			<form name="frmConsulta" novalidate>
				<h1 class="template-title">Consultar estados de pago</h1><br><br>
				<md-subheader class="md-warn hint" ng-if="showHints">{{MsgError}} </md-subheader>
					<div layout-gt-xs="row">
						<md-input-container class="md-block" flex-gt-sm="100">
						<label class="md-warm">Número de documento</label> 
						<input data-ng-model="p.cedula" name="cedula" id="cedula" required
							ng-maxlength="100">
						<div data-ng-messages="frmConsulta.cedula.$error">
							<div data-ng-message="required">Campo requerido.</div>
							<div data-ng-message="maxlength">Máximo 100 caracteres.</div>
						</div>
						</md-input-container>
						<md-button 
						class="md-raised md-primary md-button" 
						ng-click="p.consultarPagos()" style="height:42px"
						ng-disabled="frmConsulta.$invalid">
						Buscar </md-button>
				</div>
			</form>
			<div><p>{{p.msgNot}}</p></div>
			<div class="table-container" style="display:none;">
				<table border="1">
					<tr>
						<th>Fecha y Hora</th>
						<th>Referencia</th>
						<th>Autorizacion / CUS</th>
						<th>Estado</th>
						<th>Valor</th>
					</tr>
					<tr data-ng-repeat="row in p.datos">
						<td>{{row.fecha}}</td>
						<td>{{row.factura}}</td>
						<td>{{row.autorizacion}}</td>
						<td>{{row.estado}}</td>
						<td>{{row.valor | currency}}</td>
					</tr>
				</table>
			</div>
		</md-content>
	<jsp:include page = "/footer"></jsp:include>
	</div>
	<jsp:include page = "/jslibs"></jsp:include>
    <!-- Oferta module -->
    <script type="text/javascript" src="/campusvirtual/app/controllers/consultarPagos/ConsultarPagoController.js"></script>
</body>
</html>
