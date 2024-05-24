(function() {
	'use strict';
	angular.module('respuestapago.controller').filter('unsafe',
			function($sce) {
				return function(val) {
					return $sce.trustAsHtml(val);
				};
			});
	angular.module('respuestapago.controller').controller(
			'RespuestaPagoController', RespuestaPagoController);

	function RespuestaPagoController($http, $location, $window, $mdDialog,
			$mdMedia, $resource, $sce, CONFIG) { //
		var self = this;
		
/*
		var proyecto = '/' + CONFIG.PROYECTO_PORTAL;
		
		self.redireccionar = function(url, codigo, id) {
			$window.location.href = url + codigo + "&id=" + id;
		}
				
		self.modo =  'PSE';
*/
	}
})();