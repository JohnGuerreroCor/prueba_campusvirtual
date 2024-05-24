(function() {
	'use strict';
	angular.module('docentes.controller').controller('DocentesController',
			DocentesController);
	
	function DocentesController($mdDialog,$location, $window, $http, CONFIG){
		var self = this;
		
		var proyecto = CONFIG.RUTA_PORTAL;
		
		self.status = '  ';
		
		self.customFullscreen = true;
		
		self.guias = [];
		
		self.videos = [];
			
		self.getGuiasDocentesSakai = function(){
			$http({			 
				method:  'GET',
		        url:     proyecto + 'manualesSakaiDocentes'}).success(function(data) {
	    		self.guias = data;
	    		
			}).error(function(data) {
				console.log("Error " + data);
			});	
		}
		self.getGuiasDocentesSakai();
		
		self.getVideosDocentesSakai = function(){
			$http({			 
				method:  'GET',
		        url:     proyecto + 'videosSakaiDocentes'}).success(function(data) {
	    		self.videos = data;
	    		
			}).error(function(data) {
				console.log("Error " + data);
			});	
		}
		self.getVideosDocentesSakai();
		
		self.showAdvanced = function(ev,carpeta,archivos) {
			
		    $mdDialog.show({
		      locals:{carpeta: carpeta,archivos: archivos,tipo: "docentes"},    
		      controller: self.DialogController,
		      templateUrl: 'listaPdf',
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

		
		self.DialogController = function ($scope, $mdDialog, carpeta, archivos, tipo) {
			$scope.carpeta = carpeta;
			$scope.archivos = archivos; 
			$scope.tipo = tipo
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

