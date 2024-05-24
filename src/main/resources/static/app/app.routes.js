(function(angular){
    'use strict';
	
	angular
		.module('portal')
      	.config(function($mdThemingProvider, $mdIconProvider, $routeProvider, $locationProvider){
      		
            $locationProvider.html5Mode(true);
            
            $mdIconProvider
            	.icon("menu", "/campusvirtual/app/assets/img/menu.svg", 24);

            $mdThemingProvider
            	.definePalette('UscoPalette', {
	                '50': 'FFEBEE',
	                '100': 'FFCDD2',
	                '200': 'EF9A9A',
	                '300': 'E57373',
	                '400': 'EF5350',
	                '500': 'FFFFFF',
	                '600': 'E53935',
	                '700': 'D32F2F',
	                '800': 'C62828',
	                '900': 'B71C1C',
	                'A100': 'FF8A80',
	                'A200': 'FF5252',
	                'A400': 'FF1744',
	                'A700': 'D50000',
	                'contrastDefaultColor': 'light',	// whether, by default, text (contrast)
	                									// on this palette should be dark or light
	                'contrastDarkColors': ['50', '100', //hues which contrast should be 'dark' by default
	                 '200', '300', '400', 'A100'],
	                'contrastLightColors': undefined  
           		});
              
            $mdThemingProvider
            	.theme('default')
                .primaryPalette('UscoPalette')
                /*.primaryPalette('red', {
                	'default': '700' // by default use shade 400 from the pink palette for primary intentions
                })*/
                .accentPalette('red', {
                	'default': '700' // use shade 200 for default, and keep all other shades the same
                });
    	});

})(window.angular);