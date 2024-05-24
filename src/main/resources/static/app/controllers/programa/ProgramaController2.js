(function() {
	'use strict';
	angular.module('programa.controller').filter('unsafe', function($sce) {
		return function(val) {
			return $sce.trustAsHtml(val);
		};
	});
	angular.module('programa.controller').controller('ProgramaController',ProgramaController);

	function ProgramaController($http, $location, $window, $mdDialog, $mdMedia, $resource, CONFIG, $sce) { //
		var self = this;
		
		var proyecto = '/' + CONFIG.PROYECTO_PORTAL;
		
		self.tempOfertasListaPorUaa = [];
		self.ofertasListaPorUaa = {};
		
		self.listarCursosPorUaa = function(codigoUaa){
			$http.get(proyecto + 'listadoOfertasAcademicas?uaa=' + codigoUaa + '&requierePago=').success(function(data) {
			    self.ofertasListaPorUaa = data;
			    for(var index = 0; index < self.ofertasListaPorUaa.length; index ++){
			    	if(self.ofertasListaPorUaa[index].codigo != $location.search().codigo){
						self.tempOfertasListaPorUaa.push(self.ofertasListaPorUaa[index]);
					}				
				}
			    
			    self.ofertasListaPorUaa = self.tempOfertasListaPorUaa;
				
			}).error(function(data) {
				console.log("Error "+ data);
			});
		}


		self.oferta = {};
		self.loadPrograma = function() {
			$http.get(
					proyecto + 'consultarDatosOferta/' + $location.search().codigo).success(
					function(data) {
						self.oferta = data;
						self.listarCursosPorUaa(self.oferta.programa.codigoUaa);
					}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		self.loadPrograma();

		self.informacion = {};
		self.loadInformacionOferta = function() {
			$http.get(proyecto + 'consultarInformacionOferta/'+ $location.search().codigo).success(
							function(data) {
								self.informacion = data;
							}).error(function(data) {
						console.log('Error: ' + data);
					});
		};
		self.loadInformacionOferta();

		self.requisitos = {};
		self.loadProgramaRequisitos = function() {
			$http
					.get(
							proyecto + 'consultarRequisitosOferta?codigo=' + $location.search().codigo + '&codigoEncriptado=0').success(
							function(data) {
								self.requisitos = data;
							}).error(function(data) {
						console.log('Error: ' + data);
					});
		};
		self.loadProgramaRequisitos();
		
		self.cantidadInscripciones = {};
		self.configuracionesOferta = {};
		
		self.loadCantidadInscripciones = function() {
			$http
					.get(
							proyecto + 'consultarCantidadInscritos/' + $location.search().codigo).success(
							function(data) {
								self.cantidadInscripciones = data;
							}).error(function(data) {
						console.log('Error: ' + data);
					});
		};
		self.loadCantidadInscripciones();

		self.obtenerImagen = function(valor) {
			if (valor == null) {
				return "app/assets/img/not-found.jpg";
			} else {
				return valor;
			}
		}
		
		self.redirectOferta = function(code) {
			$window.location.href = "programa?codigo="+ code;
		}

		
		
		self.tercero = {};
		self.tipoUsuario = "";
	
		$http.get(proyecto + 'ofertaConfiguracionSer/' + $location.search().codigo ).success(
				function(data) {
					
					self.registrar = {
							oferta : {
								codigo : $location.search().codigo,
								usuarios: data
							}
						};
		
				}).error(function(data) {
			console.log('Error: ' + data);
		});
		
		
		self.status = '';
		self.customFullscreen = $mdMedia('xs') || $mdMedia('sm');

		var url = 'preInscripcion/';
		var registrarResource = $resource(url, {
			codigo : '@codigo'
		}, {
			adicionar : {
				url : url,
				method : 'POST',
				params : {}
			}
		});
		

		var url = 'inscripcionUsuarioInterno/';
		var registrarInternoResource = $resource(url, {
			codigo : '@codigo'
		}, {
			adicionar : {
				url : url,
				method : 'POST',
				params : {}
			}
		});
				
		function DialogController($scope, $http, $mdDialog, accion, items) {
			
			$scope.botones = true;
			$scope.loading = false;
			$scope.btnInscribir = true;
			$scope.habilitarClave = false;
			$scope.usuario = {};
			$scope.textoValidacion = "";
			
			$scope.accion = accion;
			$scope.registrar = items;
			$scope.hide = function() {
				$mdDialog.hide();
			};
			
			$scope.tipoUsuario = $scope.registrar.oferta.usuarios;
			
			$scope.cancel = function() {
				$mdDialog.cancel();
			};
			$scope.answer = function(answer) {
				if (accion == "Adicionar") {
					if ($scope.registrar.persona.email == $scope.registrar.persona.emailConfirmar) {
						$scope.botones = false;
						$scope.loading = true;
						var a = {
							result : $scope.registrar,
							answer : answer
						}
						$mdDialog.hide(a);
					} else {
						alert("Los correos no coinciden.");
					}
				}

				if (accion == "InsCerrada") {
					console.log($scope.usuario);
					self.registrar.usuario = $scope.usuario; 
					self.registrar.persona = $scope.usuario.persona; 
					self.registrar.usuario.pw = $scope.registrar.pw; 
					self.registrar.proveedor = $scope.registrar.isproveedor;
					//self.registrar.persona = $scope.usuario.cedula; 
					registrarInternoResource.adicionar({}, self.registrar,
							function(data) {
								mostrarRespuesta(data);
							}, function(response) {
								mostrarRespuesta(response.data);
							});
				}
			};
			
			$scope.loadPreInscripcionDatos = function(id) {				
				if(id!=undefined && id!=null && id!=''){
					$scope.loading_consultar = true;
					$http.get('consultarDatosPersonaPreInscripcion/' + id).success(function(data) {
						self.preInscripcionDatos = data;
						self.registrar.persona = {
							identificacion: id,
							nombres: self.preInscripcionDatos.nombres,
							apellidos: self.preInscripcionDatos.apellidos,
							email: self.preInscripcionDatos.email
						};
						$scope.loading_consultar = false;
			     	}).error(function(data) {
						console.log('Error: ' + data);
					});
				}
			};
			$scope.loadValidadUsu = function(id) {	
				if(id!=undefined && id!=null && id!=''){
					$scope.loading_consultar = true;
					$http.get('validarUsuarioGeneral/' + id +'/'+$location.search().codigo).success(function(data) {
						
						if(data.mensaje == "OK"){
							$scope.btnInscribir = false;
							$scope.usuario = data.body;
							$scope.textoValidacion = "Usuario verificado ingrese su clave y haga clic en Registrame."
							$scope.habilitarClave = true;
						}else{
							$scope.btnInscribir = true;
							$scope.habilitarClave = false;
							$scope.usuario = {};
							$scope.textoValidacion = data.mensaje;//"Usuario no verificado. Por favor verifique que su usuario y vinculación con la universidad se encuentre activo. De no tener un usuario en el sistema de información de la universidad deberá solicitarlo."
						}
						console.log($scope.usuario);
						$scope.loading_consultar = false;
			     	}).error(function(data) {
						console.log('Error: ' + data);
					});
				}
			};
			
			$scope.loadValidadProveedor = function(id) {	
				if(id!=undefined && id!=null && id!=''){
					$scope.loading_consultar = true;
					$http.get('validarProveedor/' + id +'/'+$location.search().codigo).success(function(data) {
						
						if(data.mensaje == "OK"){
							$scope.btnInscribir = false;
							$scope.usuario = data.body;
							$scope.registrar.pw = true;
							$scope.textoValidacion = "Proveedor verificado haga clic en Registrame."
							$scope.habilitarClave = true;
						}else{
							$scope.btnInscribir = true;
							$scope.habilitarClave = false;
							$scope.usuario = {};
							$scope.textoValidacion = data.mensaje;//"Usuario no verificado. Por favor verifique que su usuario y vinculación con la universidad se encuentre activo. De no tener un usuario en el sistema de información de la universidad deberá solicitarlo."
						}
						console.log($scope.usuario);
						$scope.loading_consultar = false;
			     	}).error(function(data) {
						console.log('Error: ' + data);
					});
				}
			};
		}
		
		function mostrarRespuesta(data) {
			$mdDialog.show($mdDialog.alert().title(
					"Formulario de Pre-Inscripcion").textContent(data.mensaje)
					.ariaLabel("Formulario de Pre-Inscripcion").ok('Aceptar'));
		}

		function mostrarProgressBar() {
			$mdDialog.show($mdDialog.alert().title(
					"Formulario de Pre-Inscripcion").textContent(
					"Enviando informacion, por favor espere...").ok(
					'Cargando...'));
		}
		
		
		self.editar = function (accion) {
			var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))
					&& self.customFullscreen;
			var form = null;
			if (accion == "Adicionar") {
				form = 'formaRegistrar';
			}
			if (accion == "InsCerrada") {
				form = 'formaRegistrarInterno';
			}
			self.registrar.usuario =null;
			$mdDialog.show({
				controller : DialogController,
				templateUrl : form + '/',
				parent : angular.element(document.body),
				fullscreen : useFullScreen,
				locals : {
					items : self.registrar,
					accion : accion,
					interna: self.oferta.interna
				}
			}).then(
					function(answer) {
						/*
						if (answer.answer != "cancelar") {
							mostrarProgressBar();
						}
						*/
						if (answer.answer == "ok") {
							if (accion == "Adicionar") {
								registrarResource.adicionar({}, self.registrar,
										function(data) {
											mostrarRespuesta(data);
											self.registrar.persona = null;
											//setTimeout("location.reload()", 2000);
											
										}, function(response) {
											mostrarRespuesta(response.data);
										});
							}
						}
					});
		}


	}
})();

angular.module('programa.controller').directive('validNumber', function() {
    return {
        require : '?ngModel',
        link : function(scope, element, attrs, ngModelCtrl) {
            if (!ngModelCtrl) {
                return;
            }

            ngModelCtrl.$parsers.push(function(val) {

                if (angular.isUndefined(val)) {
                    var val = '';
                }

                var clean = val.replace(/[^0-9]/g, '');
                
                var negativeCheck = clean.split('-');
                var decimalCheck = clean.split('.');
                if (!angular.isUndefined(negativeCheck[1])) {
                    negativeCheck[1] = negativeCheck[1].slice(0,
                            negativeCheck[1].length);
                    clean = negativeCheck[0] + '-' + negativeCheck[1];
                    if (negativeCheck[0].length > 0) {
                        clean = negativeCheck[0];
                    }

                }

                if (!angular.isUndefined(decimalCheck[1])) {
                    decimalCheck[1] = decimalCheck[1].slice(0, 2);
                    clean = decimalCheck[0] + '.' + decimalCheck[1];
                }

                if (val !== clean) {
                    ngModelCtrl.$setViewValue(clean);
                    ngModelCtrl.$render();
                }
            

                return parseFloat(clean);
            });

            element.bind('keypress', function(event) {

                if (event.keyCode === 32) {

                    event.preventDefault();
                }
            });

        }
    };
});