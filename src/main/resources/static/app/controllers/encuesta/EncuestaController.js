(function() {
	'use strict';
	angular.module('encuesta.controller').filter('unsafe',
			function($sce) {
				return function(val) {
					return $sce.trustAsHtml(val);
				};
			});
	angular.module('encuesta.controller').controller(
			'EncuestaController', EncuestaController);

	function EncuestaController($http, $location, $window, $mdDialog,
			$mdMedia, $resource, $sce, CONFIG) { //
		
		var self = this;
		
		self.consultarPreguntas = function(){
			self.datos = null;
			$http.get('preguntas/1').success(
					function(data) {
						self.datos = data;
					}).error(function(data) {
				console.log('Error: ' + data);
			});
		}
		
		self.consultarOpcionesRespuestas = function(){
			self.opcionesRespuestas = null;
			$http.get('opcionesRespuesta/1').success(
					function(data) {
						self.opcionesRespuestas = data;
					}).error(function(data) {
				console.log('Error: ' + data);
			});
		}
		
		self.consultarPreguntas();
		self.consultarOpcionesRespuestas();
	}
})();