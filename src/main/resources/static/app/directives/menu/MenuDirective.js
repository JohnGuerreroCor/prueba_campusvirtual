(function(){
	'use strict';

	angular.module('menu.directive')
		.directive("mycreation", function ($window) {
			//Remember that a directive does not fucking work with a name in capitals letter
		    var linkFunction = function(scope, element, attributes) {

		    	//Just in case ;)
		    	var container = document.querySelector('#master-content');

		    	var header= document.querySelector('[md-portal-header]');

		    	var scrollOne = angular.element(document.querySelector('[scroll-one]'));
		    	var scrollTwo = angular.element(document.querySelector('[scroll-two]'));
		    	var scrollThree = angular.element(document.querySelector('[scroll-three]'));
		    	var scrollFour = angular.element(document.querySelector('[scroll-four]'));

		    	var slider = angular.element(document.querySelector('#slider-container'));
		    	var programaDescription = angular.element(document.querySelector('#programa-description'));
		    	
		    	var students = angular.element(document.querySelector('#scene'));

		    	var baseDimensions = header.getBoundingClientRect();

		    	function handleMenu(dim) {
		    		//console.log(dim.top);
			        if( dim.top < 0){
			        	scrollOne.addClass('hiding');
			        	scrollTwo.addClass('hiding');
			        	scrollThree.addClass('hiding');
			        	
			        	scrollFour.removeClass('hiding');
			        	/*Adding extra margin*/
			        	slider.addClass('extra-margin');
			        	programaDescription.addClass('extra-margin');
			        	students.addClass('extra-margin');
	
			        }else{
			        	scrollOne.removeClass('hiding');
			        	scrollTwo.removeClass('hiding');
			        	scrollThree.removeClass('hiding');
			        	
			        	scrollFour.addClass('hiding');

			        	slider.removeClass('extra-margin');
			        	programaDescription.removeClass('extra-margin');
			        	students.removeClass('extra-margin');

			        }

			        if( dim.top > -1){
			        	scrollOne.removeClass('hiding');
			        	scrollTwo.removeClass('hiding');
			        	scrollThree.removeClass('hiding');
			        	scrollFour.addClass('hiding');
			        }
			    }

		    	handleMenu(baseDimensions);

		    	angular.element($window).bind("scroll", function() {
					var dimensions = header.getBoundingClientRect();
        			handleMenu(dimensions);
		    			
		    	});
		    }

		    return linkFunction;
		});

})();