(function(){
	'use strict';

	angular
	.module('slider.controller')
	.controller('SliderController',[
		'SlideService','$http', '$location', '$window', '$log', 'CONFIG',
		function (SlideService, $http, $location, $window, $log, CONFIG){
			var self = this;
			self.slides = [];

			self.proyecto = CONFIG.RUTA_ADMIN;		
			
			/*SlideService
			.slides()
			.then( function(slides){
				self.slides = [].concat(slides);
			});*/	

			self.getSliderElements = function(){
				$http({			 
					method:  'GET',
			        url: self.proyecto + 'getSliderElements'}).success(function(data) {
		    		self.slides = data;
		    		
				}).error(function(data) {
					console.log("Error " + data);
				});	
			}
			self.getSliderElements();
			
		}]);
	
	

})();