(function(){
	'use strict';

	angular
		.module('oferta.controller')
		.controller('OfertaController',['OfertaService', '$http', '$location', '$window', '$mdSidenav', '$log', 'CONFIG',
			function (OfertaService, $http, $location, $window, $mdSidenav, $log, CONFIG){
				
				var self = this;
				
				//var proyecto = 'http://campusvirtual.usco.edu.co:8180/inscripciones/';
				var proyecto = '/' + CONFIG.PROYECTO_PORTAL;
				
				self.adminUrl = CONFIG.RUTA_ADMIN;
				
				self.oferta = [];
				
				self.tipo = $location.search().tipo;
			    self.arregloParcialNoRequierePagoVisual = [];
			    self.arregloParcialRequierePagoVisual = [];
			    			    
			    self.start = 0;
			    self.max = 3;
			    self.ultimo = 3;			   
			    
			    self.start1 = 0;
			    self.max1 = 3;
			    self.ultimo1 = 3;
			    
			    var codigoUaa = '';
			    self.ofertaVisual = [];
			    
			    self.listarOfertasAcademicas = function(codigoUaa, requierePago){
			    	self.arregloParcialNoRequierePago = [];
				    self.arregloParcialRequierePago = [];
			    	$http.get(proyecto + 'listadoOfertasAcademicas?uaa=' + codigoUaa + '&requierePago=' + requierePago).success(function(data) {
			    		self.oferta = data;
			    		self.arregloParcialNoRequierePago = [];
					    self.arregloParcialRequierePago = [];
						for(var index = 0; index < self.oferta.length; index ++){
							if(self.oferta[index].requierePago==0){
								self.arregloParcialNoRequierePago.push(self.oferta[index]);
							}else{
								self.arregloParcialRequierePago.push(self.oferta[index]);
							}
						}
						
						if($location.search().tipo==0){
							self.ofertaVisual = self.arregloParcialNoRequierePago;
						}else if($location.search().tipo==1){
							self.ofertaVisual = self.arregloParcialRequierePago;
						}else{
							self.ofertaVisual = self.oferta;
							self.arregloParcialNoRequierePagoVisual = self.arregloParcialNoRequierePago.slice(self.start, self.max);
							self.arregloParcialRequierePagoVisual = self.arregloParcialRequierePago.slice(self.start1, self.max1);
						}
					}).error(function(data) {
						console.log("Error: " + data);
					});
			    }
			    self.listarOfertasAcademicas(codigoUaa);
			    			
			    self.start3 = 0;
			    self.max3 = 3;
			    self.ultimo3 = 3;
			    
				listarCursosEducacionFormal();
				self.arregloParcialEducacionFormal = [];
				self.ofertasListaEducacionFormal = {};
				
			    function listarCursosEducacionFormal(){
			    	$http.get(proyecto + 'listadoOfertasAcademicasEducacionFormal/').success(function(data) {
						self.ofertasListaEducacionFormal = data;
						self.arregloParcialEducacionFormal = self.ofertasListaEducacionFormal.slice(self.start3, self.max3);
					}).error(function(data) {
						console.log("Error ");
					});
			    }
			    			    
			    self.changeProgramasEducacionFormal = changeProgramasEducacionFormal;			    
			    function changeProgramasEducacionFormal(estado){
				    if(estado == true){
				    	self.start3 = self.start3 + self.max3;
				    	if((self.start3 + self.max3)>self.ofertasListaEducacionFormal.length){
				    		self.ultimo3 = self.ofertasListaEducacionFormal.length;
				    	}else{
				    		self.ultimo3 = self.start3 + self.max3;
				    	}
				    }else if(estado == false){
				    	self.start3 = self.start3 - self.max3;
				    	if(self.start3<=0){    			
				    		self.start3=0;
				    	}
				    	self.ultimo3 = self.start3 + self.max3;
				    }

				    self.arregloParcialEducacionFormal = self.ofertasListaEducacionFormal.slice(self.start3, (self.start3+self.max3));
			    }
			    
			    self.start4 = 0;
			    self.max4 = 3;
			    self.ultimo4 = 3;
			    
			    listadoOfertaFormalVirtual();
				self.arregloParcialOfertaFormalVirtual = [];
				self.ofertasFormalVirtual = {};
			    
			    function listadoOfertaFormalVirtual(){
				   	$http.get(proyecto + 'listadoOfertaFormalVirtual/').success(function(data) {
				   		self.ofertasFormalVirtual = data;
				   		console.log('Apoyo');
				   		console.log(self.ofertasFormalVirtual);
						self.arregloParcialOfertaFormalVirtual = self.ofertasFormalVirtual.slice(self.start4, self.max4);
				   	}).error(function(data) {
						console.log("Error ");
					});
				}
			    
			    self.changeProgramasFormalVirtual = changeProgramasFormalVirtual;			    
			    function changeProgramasFormalVirtual(estado){
				    if(estado == true){
				    	self.start4 = self.start4 + self.max4;
				    	if((self.start4 + self.max4)>self.ofertasFormalVirtual.length){
				    		self.ultimo4 = self.ofertasFormalVirtual.length;
				    	}else{
				    		self.ultimo4 = self.start4 + self.max4;
				    	}
				    }else if(estado == false){
				    	self.start4 = self.start4 - self.max4;
				    	if(self.start4<=0){    			
				    		self.start4=0;
				    	}
				    	self.ultimo4 = self.start4 + self.max4;
				    }

				    self.arregloParcialOfertaFormalVirtual = self.ofertasFormalVirtual.slice(self.start4, (self.start4+self.max4));
			    }
			    			    
			    self.listarFacultadesOfertasAcademicas = function(){
			    	if($location.search().tipo==undefined){ $location.search().tipo = 2;}
			    	$http.get(proyecto + 'listadoFacultadesOfertasAcademicas?requierePago=' + $location.search().tipo).success(function(data) {
			    		self.facultadesOferta = data;
					}).error(function(data) {
						console.log("Error: " + data);
					});
			    }
			    self.listarFacultadesOfertasAcademicas();
				
			    
				self.obtenerImagen = function (valor){
			    	if(valor == null){
			    		return proyecto + "app/assets/img/not-found.jpg";
			    		//return "https://campusvirtual.usco.edu.co/administrador_campus/ofertaAcademica/imagen?id=2018";
			    	}else{
			    		return valor;
			    	}
			    }
				
				self.changeNoRequierePago = changeNoRequierePago;
				self.changeRequierePago = changeRequierePago;
				self.changeProgramasInactivos = changeProgramasInactivos;
				
				function changeNoRequierePago(estado){
			    	if(estado == true){
			    		self.start = self.start + self.max;
			    		if((self.start + self.max)>self.arregloParcialNoRequierePago.length){
			    			self.ultimo = self.arregloParcialNoRequierePago.length;
			    		}else{
			    			self.ultimo = self.start + self.max;
			    		}
			    	}else if(estado == false){
			    		self.start = self.start - self.max;
			    		if(self.start<=0){    			
			    			self.start=0;
			    		}
			    		self.ultimo = self.start + self.max;
			    	}
			    	self.arregloParcialNoRequierePagoVisual = self.arregloParcialNoRequierePago.slice(self.start, (self.start+self.max));
			    }
			    
			    function changeRequierePago(estado){
			    	if(estado == true){
			    		self.start1 = self.start1 + self.max1;
			    		if((self.start1 + self.max1)>self.arregloParcialRequierePago.length){
			    			self.ultimo1 = self.arregloParcialRequierePago.length;
			    		}else{
			    			self.ultimo1 = self.start1 + self.max1;
			    		}
			    	}else if(estado == false){
			    		self.start1 = self.start1 - self.max1;
			    		if(self.start1<=0){    			
			    			self.start1=0;
			    		}
			    		self.ultimo1 = self.start1 + self.max1;
			    	}
			    	self.arregloParcialRequierePagoVisual = self.arregloParcialRequierePago.slice(self.start1, (self.start1+self.max1));
			    }
			    
			    self.start2 = 0;
			    self.max2 = 3;
			    self.ultimo2 = 3;
			    
				listarCursosInactiva();
				self.arregloParcialInactiva = [];
				self.ofertasListaInactiva = {};
				
			    function listarCursosInactiva(){
			    	$http.get(proyecto + 'listadoOfertasAcademicasInactivas/').success(function(data) {
						self.ofertasListaInactiva = data;
						self.arregloParcialInactiva = self.ofertasListaInactiva.slice(self.start2, self.max2);
						
					}).error(function(data) {
						console.log("Error ");
					});
			    }
			    
			    function changeProgramasInactivos(estado){
				    if(estado == true){
				    	self.start2 = self.start2 + self.max2;
				    	if((self.start2 + self.max2)>self.ofertasListaInactiva.length){
				    		self.ultimo2 = self.ofertasListaInactiva.length;
				    	}else{
				    		self.ultimo2 = self.start2 + self.max2;
				    	}
				    }else if(estado == false){
				    	self.start2 = self.start2 - self.max2;
				    	if(self.start2<=0){    			
				    		self.start2=0;
				    	}
				    	self.ultimo2 = self.start2 + self.max2;
				    }

				    self.arregloParcialInactiva = self.ofertasListaInactiva.slice(self.start2, (self.start2+self.max2));
			    }	

				//=========Buscador
			    
			    self.searchTextChange = [];
			    self.items = [];
			    self.searchText = '';
			    
			    self.searchTextChange = function(text) {
			    	$http.get(proyecto + 'listadoOfertasAcademicas?uaa=0').success(function(data) {
			    		
			    		self.items = data.map(function(asig) {
			    			return {
			    				value : asig.programa.codigo,
			    				display : asig.programa.nombre
			    			};
			    		});
			    	}).error(function(data) {
			    		console.log('Error: ' + data);
			    	});			    	
				}
			    
			    self.redireccionar = function (url, id){
			    	$window.location.href = url + id;
			    }
			    
			    
			    self.listFacultades = [];
			    self.listPagoGratuito = [];
			    self.filtroAvanzado = function(facultad, requierePago){ 	
			    	var idx = self.listFacultades.indexOf(facultad);
			    	if (idx > -1) {
			    		self.listFacultades.splice(idx, 1);
			        }else if(facultad!=''){
			        	self.listFacultades.push(facultad); 
				    }
			    	
			    	var idx2 = self.listPagoGratuito.indexOf(requierePago);
			    	if (idx2 > -1) {
			    		self.listPagoGratuito.splice(idx2, 1);
			        }else if(parseInt(requierePago)==1 || parseInt(requierePago)==0){
			        	self.listPagoGratuito.push(requierePago); 
				    }
			    	
			    	self.listarOfertasAcademicas(self.listFacultades, self.listPagoGratuito);
			    }

			    /*Sidenav filters*/
			    
			    self.toggleRight = buildToggler('right');
			    
			    self.isOpenRight = function(){
			      return $mdSidenav('right').isOpen();
			    };
			    
			    function buildToggler(navID) {
			        return function() {
			          // Component lookup should always be available since we are not using `ng-if`
			          $mdSidenav(navID)
			            .toggle()
			            .then(function () {
			              $log.debug("toggle " + navID + " is done");
			            });
			        }
			    }

			    self.close = function () {
			        // Component lookup should always be available since we are not using `ng-if`
			        $mdSidenav('right').close()
			          .then(function () {
			            $log.debug("close RIGHT is done");
			          });
			      };
	    
			}]);
	
})();