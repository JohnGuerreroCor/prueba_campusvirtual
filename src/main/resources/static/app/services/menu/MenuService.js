(function(){
	'use strict';

	angular
		.module('menu.service')
		.service('menuService',['$q','CONFIG', 
			function ($q, CONFIG){
				var servidor = CONFIG.RUTA_PORTAL;
				var firstMenu = [
					{
						name: 'Regresar a usco.edu.co',
						url: 'https://www.usco.edu.co',
						type: 'link',
						target: '_blank',
						part: 'a'
					}
				];

				var secondMenu = [
					{
						name: 'Inicio',
						url: servidor,
						type: 'link',
						target: '_top',
						part: 'a'
						
					},
					{
						name: 'Biblioteca',
						url: CONFIG.RUTA_BIBLIOTECA,
						type: 'link',
						target: '_blank',
						part: 'b'
						
					},
					{
						name: 'Oferta Académica',
						url: '#',
						type: 'toggle',
						target: '',
						part: 'a',
						content: [
						{
							name: 'Todos los programas académicos',
							type: 'link',
							target: '_blank',
							url: servidor + 'oferta'
						},          
						{
							name: 'Programas gratuitos',
							type: 'link',
							target: '_blank',
							url: servidor + 'oferta?tipo=0'
						},
						{
							name: 'Programas pagos',
							type: 'link',
							target: '_blank',
							url: servidor + 'oferta?tipo=1'
						}]
					},
					{
						
						name: 'Plataforma Educativa',
						url: CONFIG.RUTA_SAKAI + 'login/',
						type: 'link',
						target: '_blank',
						part: 'a'
					},
					{
						name: 'Plataforma Administrativa',
						url: CONFIG.RUTA_ADMIN + '/',
						type: 'link',
						target: '_blank',
						part: 'b'
					}

				];

				var thirdMenu = [
					/*{
						name: 'Reserva Videoconferencia',
						url: servidor + 'reservavideoconferencia',
						type: 'link',
						target: '_top',
						part: 'a'
					},*/
					{
						name: 'Guías y Videotutoriales',
						url: '#',
						type: 'toggle',
						target: '',
						part: 'b',
						content: [
						{
							name: 'Docentes',
							type: 'link',
							target: '_blank',
							url: servidor + 'docentes'
						},          
						{
							name: 'Estudiantes',
							type: 'link',
							target: '_blank',
							url: servidor + 'estudiantes'
						}
						]
					},
					{
						name: 'Aulas Interactivas',
						url: servidor + 'aulasinteligentes',
						type: 'link',
						target: '_top',
						part: 'a'
					}

				];

				return {
					firstMenu : function() {
						return $q.when(firstMenu);
					},
					secondMenu : function() {
						return $q.when(secondMenu);
					},
					thirdMenu : function() {
						return $q.when(thirdMenu);
					}
				}
			}]);

})();