(function(angular){
    'use strict';
	
	angular.module('portal',['ngMaterial','ngRoute','ngMessages','ngAnimate','ngResource','menu.directive','slider.directive','menu.controller','slider.controller','oferta.controller','programa.controller','inscripcion.controller', 'confirmarcorreo.controller','respuestapago.controller', 'pse.controller', 'basesdatos.controller','estudiantes.controller','docentes.controller','filters.controller','soporte.controller','portal.constantes','format.directive', 'lightbox.controller','consultarpago.controller','encuesta.controller']);
	angular.module('menu.directive',['ngMaterial']);
	angular.module('menu.controller',['menu.service','ngMaterial','portal.constantes']);
	angular.module('menu.service',['portal.constantes']);
	angular.module('slider.controller',['slider.service','portal.constantes']);
	angular.module('slider.service',['portal.constantes']);
	angular.module('slider.directive',['portal.constantes']);
	angular.module('oferta.controller',['oferta.service','format.directive']);
	angular.module('oferta.service',['portal.constantes']);
	angular.module('programa.controller',['portal.constantes','format.directive']);
	angular.module('inscripcion.controller',['portal.constantes']);
	angular.module('confirmarcorreo.controller',['portal.constantes']);
	angular.module('respuestapago.controller',['portal.constantes']);
	angular.module('pse.controller',['portal.constantes']);
	angular.module('basesdatos.controller',['portal.constantes']);
	angular.module('estudiantes.controller',['portal.constantes']);
	angular.module('docentes.controller',['portal.constantes']);
	angular.module('filters.controller', ['portal.constantes']);
	angular.module('soporte.controller', ['portal.constantes']);
	angular.module('portal.constantes', []);
	angular.module('format.directive', []);
	angular.module('lightbox.controller', ['ngMaterial','portal.constantes']);
	angular.module('consultarpago.controller', ['ngMaterial','portal.constantes']);
	angular.module('encuesta.controller', ['ngMaterial','portal.constantes']);

})(window.angular);