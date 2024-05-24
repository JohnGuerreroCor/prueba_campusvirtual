(function() {
	'use strict';
	angular.module('estudiantes.controller').controller('EstudiantesController',
			EstudiantesController);

	function EstudiantesController($mdDialog,$location, $window,  $http, CONFIG){
		var self = this;
		
		var proyecto = CONFIG.RUTA_PORTAL;
		
		self.status = '  ';
		
		self.customFullscreen = false;
		
		self.guias = [];
			
		self.getGuiasEstudiantesSakai = function(){
			$http({			 
				method:  'GET',
		        url:     proyecto + 'manualesSakaiEstudiantes'}).success(function(data) {
	    		self.guias = data;
	    		
			}).error(function(data) {
				console.log("Error " + data);
			});	
		}
		self.getGuiasEstudiantesSakai();
		
		self.getVideosEstudiantesSakai = function(){
			$http({			 
				method:  'GET',
		        url:     proyecto + 'videosSakaiEstudiantes'}).success(function(data) {
	    		self.videos = data;
	    		
			}).error(function(data) {
				console.log("Error " + data);
			});	
		}
		self.getVideosEstudiantesSakai();
		
		self.showAdvanced = function(ev,carpeta,archivos) {
			
		    $mdDialog.show({
		      locals:{carpeta: carpeta,archivos: archivos, tipo:"estudiantes"},    
		      controller: self.DialogController,
		      templateUrl: 'listaPdf',
			  parent: angular.element(document.body),
			  targetEvent: ev,
			  clickOutsideToClose:true, 
			  fullscreen: self.customFullscreen
			}).then(function(answer) {
				self.status = 'You said the information was "' + answer + '".';
			}, function() {
				self.status = 'You cancelled the dialog.';
			});
		};
		
		self.DialogController = function($scope, $mdDialog, carpeta, archivos,tipo) {
			$scope.carpeta = carpeta;
			$scope.archivos = archivos;
			$scope.tipo = tipo; 
		
		    $scope.hide = function() {
		      $mdDialog.hide();
		    };

		    $scope.cancel = function() {
		      $mdDialog.cancel();
		    };

		    $scope.answer = function(answer) {
		      $mdDialog.hide(answer);
		    };
		 }
		
		self.showVideo = function(ev,nombre,ruta) {
			
		    $mdDialog.show({
		      locals:{nombre:nombre,ruta: ruta},    
		      controller: self.DialogVideoController,
		      templateUrl: 'videoTutoriales',
			  parent: angular.element(document.body),
			  targetEvent: ev,
			  clickOutsideToClose:true, 
			  fullscreen: self.customFullscreen // Only for -xs, -sm breakpoints.
			}).then(function(answer) {
				self.status = 'You said the information was "' + answer + '".';
			}, function() {
				self.status = 'You cancelled the dialog.';
			});
		};
		
		self.DialogVideoController = function ($scope, $mdDialog, nombre, ruta) {
			$scope.nombre = nombre;
			$scope.ruta = ruta;
		    $scope.hide = function() {
		      $mdDialog.hide();
		    };

		    $scope.cancel = function() {
		      $mdDialog.cancel();
		    };

		    $scope.answer = function(answer) {
		      $mdDialog.hide(answer);
		    };
		 }
		
	}
	
	
	
})();

