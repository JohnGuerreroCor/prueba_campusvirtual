(function(){
	'use strict';

	angular
		.module('oferta.service')
		.service('OfertaService',['$q',
			function ($q){
			
				var oferta = [
					{
						title: 'Amazonas, el lugar donde nace la vida',	
						imgUrl: 'app/assets/img/oferta/1.jpg',
						imgAlt: 'Amazonas, el lugar donde nace la vida',
						url: '#',
						description: 'El Amazonas es tierra para nacer, para morir y para renacer. Los misterios que esconde este bosque tropical, considerado más extenso del mundo, son celosamente guardados por un río que permite y niega la vida a su natural antojo.',
					},
					{
						title: 'Finalistas de competencia global de innovación',
						imgUrl: 'app/assets/img/oferta/2.jpg',
						imgAlt: 'Finalistas de competencia global de innovación',
						url: '#',
						description: 'Dos start ups (o iniciativas) colombianas fueron seleccionados esta semana como finalistas de la Competencia Mundial de Ciencia, Innovación y Tecnología GIST Tech-1 2016, un evento que hace parte de la Cumbre Global de Emprendedores (CGE) que se realizará en Silicon Valley (California) a finales de junio de este año y que es promovido por la Casa Blanca.',
					},
					{
						title: 'Acuerdo final de paz',
						imgUrl: 'app/assets/img/oferta/3.jpg',
						imgAlt: 'Acuerdo final de paz',
						url: '#',
						description: 'Tal como había anunciado en la noche del pasado miércoles, el presidente de la República, Juan Manuel Santos, hizo público en la mañana de este jueves el texto "completo y definitivo" de los acuerdos de la negociación entre el Gobierno y las Farc.',
					},
					{
						title: 'Amazonas, el lugar donde nace la vida',	
						imgUrl: 'app/assets/img/oferta/1.jpg',
						imgAlt: 'Amazonas, el lugar donde nace la vida',
						url: '#',
						description: 'El Amazonas es tierra para nacer, para morir y para renacer. Los misterios que esconde este bosque tropical, considerado más extenso del mundo, son celosamente guardados por un río que permite y niega la vida a su natural antojo.',
					},
					{
						title: 'Finalistas de competencia global de innovación',
						imgUrl: 'app/assets/img/oferta/2.jpg',
						imgAlt: 'Finalistas de competencia global de innovación',
						url: '#',
						description: 'Dos start ups (o iniciativas) colombianas fueron seleccionados esta semana como finalistas de la Competencia Mundial de Ciencia, Innovación y Tecnología GIST Tech-1 2016, un evento que hace parte de la Cumbre Global de Emprendedores (CGE) que se realizará en Silicon Valley (California) a finales de junio de este año y que es promovido por la Casa Blanca.',
					},
					{
						title: 'Acuerdo final de paz',
						imgUrl: 'app/assets/img/oferta/3.jpg',
						imgAlt: 'Acuerdo final de paz',
						url: '#',
						description: 'Tal como había anunciado en la noche del pasado miércoles, el presidente de la República, Juan Manuel Santos, hizo público en la mañana de este jueves el texto "completo y definitivo" de los acuerdos de la negociación entre el Gobierno y las Farc.',
					}
				];

				return {
					oferta : function() {
						return $q.when(oferta);
					}
				}
			}]);

})();