(function() {
	'use strict';
	angular.module('consultarpago.controller').filter('unsafe',
			function($sce) {
				return function(val) {
					return $sce.trustAsHtml(val);
				};
			});
	angular.module('consultarpago.controller').controller(
			'ConsultarPagoController', ConsultarPagoController);

	function ConsultarPagoController($http, $location, $window, $mdDialog,
			$mdMedia, $resource, $sce, CONFIG) { //
		var self = this;
		
		self.consultarPagos = function(){
			self.datos = null;
			$http
			.get('consultarEstadoPagos/' + self.cedula+'/').success(
					function(data) {
						self.datos = data;
						if(!data[0]){
							self.msgNot = "No se encontraron resultados.";
						}else{
							self.msgNot = "";
							$('.table-container').show();
						}
					}).error(function(data) {
				console.log('Error: ' + data);
			});
		}
	}
})();