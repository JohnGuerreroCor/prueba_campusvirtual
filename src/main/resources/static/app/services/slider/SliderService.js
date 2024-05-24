(function(){
	'use strict';

	angular
		.module('slider.service')
		.service('SlideService',['$q', 
			function ($q){

				var slides = [
					{
						secuencia: 1,
						url: '/campusvirtual/app/assets/img/slider/1.jpg',
						alt: '¡Campus virtual de la universidad!',
						descripcion: '¡Campus virtual de la universidad!',
						estado: "1"
					},
					{
						secuencia: 2,
						url: '/campusvirtual/app/assets/img/slider/2.jpg',
						alt: 'Este es un prototipo!! Este contenido es de pruebas..',
						descripcion: 'Este es un prototipo!! Este contenido es de pruebas..',
						estado: "1"
					},
					{
						secuencia: 3,
						url: '/campusvirtual/app/assets/img/slider/3.jpg',
						alt: 'Nuestro campus esta en construcción!!',
						descripcion: 'Nuestro campus esta en construcción!!',
						estado: "1"
					},
					{
						secuencia: 4,
						url: '/campusvirtual/app/assets/img/slider/4.jpg',
						alt: 'Próximamente nuestro campus estará¡ terminado',
						descripcion: 'Próximamente nuestro campus estará¡ terminado',
						estado: "1"
					}
				];

				return {
					slides : function() {
						return $q.when(slides);
					}
				}
			}]);

})();