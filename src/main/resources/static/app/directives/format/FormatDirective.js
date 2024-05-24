(function(){
	'use strict';

	angular.module('format.directive')
		.directive("formatting", function($window) {
			
			    var number = function(scope, element, attributes) {
			    	var that = scope;
			    	if(element[0].id == 'programCost-home-1' || element[0].id == 'programCost-home-2'){
			    		element[0].innerHTML = accounting.formatMoney(parseInt(that.it.valor)) + " <span class='no-text-transform'>COP</span>";
			    	}else if(element[0].id == 'programCost-programa'){
			    		element[0].innerHTML = accounting.formatMoney(parseInt(that.p.oferta.valor)) + " <span class='no-text-transform'>COP</span>";
			    	}else if(element[0].id == 'programCost-oferta'){
			    		element[0].innerHTML = accounting.formatMoney(parseInt(that.it.valor)) + " <span class='no-text-transform'>COP</span>";
			    	}
			    }
			    return number;
			});

})();