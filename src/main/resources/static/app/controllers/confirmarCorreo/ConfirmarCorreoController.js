(function() {
	'use strict';
	angular.module('confirmarcorreo.controller').filter('unsafe',
			function($sce) {
				return function(val) {
					return $sce.trustAsHtml(val);
				};
			});
	angular.module('confirmarcorreo.controller').controller(
			'ConfirmarCorreoController', ConfirmarCorreoController);

	function ConfirmarCorreoController($http, $location, $window, $mdDialog,
			$mdMedia, $resource, $sce, CONFIG) { //
		
		var self = this;
		
		self.btnDisableProv = false;
		self.msgResp = "";
		self.loading_consultar = false;

		var proyecto = '/' + CONFIG.PROYECTO_PORTAL;

		self.redireccionar = function(url, codigo, id) {
			$window.location.href = url + codigo + "&id=" + id;
		}

		self.redireccionarProv = function(url, codigo, id) {
			self.btnDisableProv = true;
			self.loading_consultar = true;
			$http.get(url + '/' + id + '/'+codigo).success(function(data) {
				self.msgResp = data.mensaje;
			    if(!data.exito){
			    	self.btnDisableProv = false;	
			    }	
			    self.loading_consultar = false;
			}).error(function(data) {
				self.loading_consultar = false;
				self.btnDisableProv = false;
				console.log("Error "+ data);
			});
			//$window.location.href = url + "/" + id + "/" + codigo;
		}

		self.modo = 'ELECTRONICO';

	}
})();