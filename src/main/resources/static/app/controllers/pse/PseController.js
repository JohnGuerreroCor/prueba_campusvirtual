(function() {
	'use strict';
	angular.module('pse.controller').filter('unsafe',
			function($sce) {
				return function(val) {
					return $sce.trustAsHtml(val);
				};
			});
	angular.module('pse.controller').controller(
			'PseController', PseController);

	function PseController($http, $location, $window, $mdDialog,
			$mdMedia, $resource, $sce, CONFIG) { //
		var self = this;

		var proyecto = '/' + CONFIG.PROYECTO_PORTAL;
		
		self.redireccionar = function(url, codigo, id) {
			$window.location.href = url + codigo + "&id=" + id;
		}
		
		self.tipoPersona = [{codigo: "01", nombre: "Persona Natural"},{codigo: "02", nombre: "Persona Jurídica"}];
		self.bancos = [ {codigo: "1040", nombre: "BANCO AGRARIO"}, 
						{codigo: "1052", nombre: "BANCO AV VILLAS"}, 
						{codigo: "1013", nombre: "BANCO BBVA COLOMBIA S.A."}, 
						{codigo: "1032", nombre: "BANCO CAJA SOCIAL"}, 
						{codigo: "1019", nombre: "BANCO COLPATRIA"}, 
						{codigo: "1066", nombre: "BANCO COOPERATIVO COOPCENTRAL"}, 
						{codigo: "1006", nombre: "BANCO CORPBANCA S.A."}, 
						{codigo: "1051", nombre: "BANCO DAVIVIENDA"}, 
						{codigo: "1001", nombre: "BANCO DE BOGOTA"}, 
						{codigo: "1023", nombre: "BANCO DE OCCIDENTE"}, 
						{codigo: "1062", nombre: "BANCO FALABELLA"}, 
						{codigo: "1012", nombre: "BANCO GNB SUDAMERIS"}, 
						{codigo: "1060", nombre: "BANCO PICHINCHA S.A."}, 
						{codigo: "1002", nombre: "BANCO POPULAR"}, 
						{codigo: "1058", nombre: "BANCO PROCREDIT"}, 
						{codigo: "1007", nombre: "BANCOLOMBIA"}, 
						{codigo: "1061", nombre: "BANCOOMEVA S.A."}, 
						{codigo: "1009", nombre: "CITIBANK"}, 
						{codigo: "1014", nombre: "ITAU"}, 
						{codigo: "1507", nombre: "NEQUI"} ];
		
		self.tipoId = null;
		self.tipoIds = [];

		self.loadTipoIds = function() {
			$http.get(proyecto + 'tipoIdentificacion/').success(function(data) {
				self.tipoIds = data;
			}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		self.loadTipoIds();
		
		
		self.enviar = function(){
			
			
			var xml = "";
			

			xml += "<soapenv:Envelope";
			xml += "xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/'";
			xml += "xmlns:com='http://service.pasarela.fasttrack.com.co/comercios'>";
			xml += "<soapenv:Header/>";
			xml += "<soapenv:Body>";
				xml += "<com:procesarPago>";
					xml += "<PagoRequest>";
						xml += "<usr_apps>SQA_USCO</usr_apps>";
						xml += "<pwd_apps>1ZE035ND</pwd_apps>";
						xml += "<id_comercio>2591</id_comercio>";
						xml += "<id_producto>498</id_producto>";
						xml += "<usr_cliente>cl002</usr_cliente>";
						xml += "<fecha_transaccion>13/10/2016 08:06:30</fecha_transaccion>";
						xml += "<fecha_canal>13/10/2016 08:06:20</fecha_canal>";
						xml += "<url_comercio>http://www.comercio.com.co/pagos/</url_comercio>";
						xml += "<ip_cliente>0:0:0:0:0:0:0:1</ip_cliente>";
						xml += "<total_transaccion>1000000</total_transaccion>";
						xml += "<!--Optional:-->";
						xml += "<tercero>";
							xml += "<tipo_documento>1</tipo_documento>";
							xml += "<nro_documento>113606457</nro_documento>";
							xml += "<nombre1>Carlos</nombre1>";
							xml += "<nombre2>Paulo</nombre2>";
							xml += "<apellido1>Lopez</apellido1>";
							xml += "<apellido2>Ospina</apellido2>";
							xml += "<correo_electronico>cpaulo@gmail.com</correo_electronico>";
							xml += "<telefono>3055267</telefono>";
							xml += "<celular>3177652956</celular>";
							xml += "<direccion>Carrera 89 16 16</direccion>";
							xml += "<pais>COL</pais>";
							xml += "<departamento>Valle del Cauca</departamento>";
							xml += "<ciudad>Cali</ciudad>";
						xml += "</tercero>";
						xml += "<!--1 or more repetitions:-->";
						xml += "<transaccion>";
							xml += "<id_transaccion>153705</id_transaccion>";
							xml += "<!--1 or more repetitions:-->";
							xml += "<registro>";
								xml += "<id_producto>1</id_producto>";
								xml += "<detalle>pago servicio 01</detalle>";
								xml += "<fecha_vencimiento>21/09/2017 09:06:30</fecha_vencimiento>";
								xml += "<referencia01>45657786</referencia01>";
								xml += "<referencia02>65478</referencia02>";
								xml += "<estado>1</estado>";
								xml += "<valor>1000000</valor>";
								xml += "<valor_iva>000</valor_iva>";
								xml += "<identificador>133558</identificador>";
							xml += "</registro>";
						xml += "</transaccion>";
					xml += "</PagoRequest>";
				xml += "</com:procesarPago>";
			xml += "</soapenv:Body>";
		xml += "</soapenv:Envelope>";
			
			//alert(xml);
			console.log(xml);
			
			
		
		
		
		self.modo =  'PSE';
		}
	}
})();