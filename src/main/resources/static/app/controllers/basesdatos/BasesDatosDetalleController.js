(function() {
	'use strict';
	angular.module('basesdatos.controller').controller('BasesDatosController',
			BasesDatosController);

	function BasesDatosController($http, $location, $window, $mdDialog, $mdMedia, $resource, 
			$sce, CONFIG) { //
		var self = this;
		
		//var proyecto = 'http://campusvirtual.usco.edu.co:8180/inscripciones/';
		var proyecto = '/' + CONFIG.PROYECTO_PORTAL;
		
	    self.redireccionar = function (){
	    	$http.get(proyecto + 'consultarBaseDato?codigoBD=' + $location.search().codigoBD).success(function(data) {
				console.log(data);
	    		$window.location.href = data.link;
			}).error(function(data) {
				console.log("Error ");
			});
	    }
	    self.redireccionar();
	}
})();