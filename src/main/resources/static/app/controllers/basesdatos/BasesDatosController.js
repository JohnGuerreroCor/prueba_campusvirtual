(function() {
	'use strict';
	angular.module('basesdatos.controller').controller('BasesDatosController',
			BasesDatosController);

	function BasesDatosController($http, $location, $window, $mdDialog, $mdMedia, $resource, 
			$sce, CONFIG) { //
		var self = this;
		
		//var proyecto = 'http://campusvirtual.usco.edu.co:8180/inscripciones/';
		var proyecto = '/' + CONFIG.PROYECTO_PORTAL;
		
		self.listarBasesDatos =[];
		self.listadoBasesDatos = {};
		
	    self.listarBasesDatos = function(){
	    	$http.get(proyecto + 'listadoBasesDatos/').success(function(data) {
				self.listadoBasesDatos = data;
			}).error(function(data) {
				console.log("Error ");
			});
	    }
	    self.listarBasesDatos();
	    
	    self.redirect = function (codigo){
	    	$window.location.href = proyecto + 'basesdatos_detalle?codigoBD=' + codigo;
	    }
	    
	    self.cuttingParagraph = function(paragraph){
	    	var yourString = paragraph; //replace with your string.
	    	var maxLength = 90 // maximum number of characters to extract

	    	//trim the string to the maximum length
	    	var trimmedString = yourString.substr(0, maxLength);

	    	//re-trim if we are in the middle of a word
	    	trimmedString = trimmedString.substr(0, Math.min(trimmedString.length, trimmedString.lastIndexOf(" ")))
	    	
	    	return trimmedString;
	    }
	    
	    self.showingParagraph = function (id){
	    	
	    	var contentCard = document.getElementById('content-container-'+id);
	    	var paragraphDescription = document.getElementById('description-'+id);
	    	var expandButton = document.getElementById('expand-'+id);
	    	var dif = contentCard.scrollHeight - paragraphDescription.scrollHeight;
	    	
	    	var contentHeight = contentCard.clientHeight;
	    	var paragraphHeight = paragraphDescription.clientHeight;
	    	
	    	
	    	if(paragraphHeight > contentHeight || dif > 0){
	    		contentCard.style.height = (paragraphHeight+70).toString() + "px";
	    		expandButton.innerHTML = "<i class='fa fa-minus-square' aria-hidden='true'></i>";
	    	}
	    	
	    	if(contentHeight > 152){
	    		contentCard.style.height = "152px";
	    		expandButton.innerHTML = "<i class='fa fa-plus-square' aria-hidden='true'></i>";
	    	}
	    }
	    
	    self.verifyingParagraph = function (id){
	    	var contentCard = document.getElementById('content-container-'+id);
	    	var paragraphDescription = document.getElementById('description-'+id);
	    	
	    	var contentHeight = contentCard.clientHeight;
	    	var paragraphHeight = paragraphDescription.clientHeight;
	    	 
	    	if((paragraphHeight + 34) < 152){
	    		return false;
	    	}else{
	    		return true;
	    	}
    	}
	    
	}
})();