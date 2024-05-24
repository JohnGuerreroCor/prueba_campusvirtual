(function() {
	'use strict';
	angular.module('soporte.controller').controller('SoporteController',
			SoporteController);

	function SoporteController($scope, $http, $mdDialog, $compile, CONFIG){
		var self = this;
		self.rutaPortal= CONFIG.RUTA_PORTAL;
		self.loading = true;
		
		self.submit = function(info){
			var original = $scope.user;
			self.loading = false;
			$http.post('formSoporte', info).success(function(response){
				if(response == true){
					self.loading = true;
					
					$mdDialog.show(
				      $mdDialog.alert()
				        .parent(angular.element(document.querySelector('body')))
				        .clickOutsideToClose(true)
				        .textContent('¡Su mensaje ha sido enviado exitosamente, vamos a revisar el caso, gracias por su aporte!')
				        .ok('Cerrar')
				    );
					
					$scope.info = "";
				    $scope.formSoporte.$setPristine();
					close_panel();
					
				}else{
					
				}
            })
            .error(function (response) {
            	console.log(response);
            });
			
		}
	}
	
	
})();


