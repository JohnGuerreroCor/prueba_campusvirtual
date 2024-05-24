(function() {
	'use strict';
	angular.module('inscripcion.controller').controller(
			'InscripcionController', InscripcionController);

	function InscripcionController($scope, $http, $location, $mdDialog, $mdMedia,
			$resource, $window, $sce, $filter, CONFIG) {
		var vm = this;
		vm.adjuntos = [];
		
		var proyecto = '/' + CONFIG.PROYECTO_PORTAL;

		vm.adjuntarArchivos = function() {
			angular.element(document.querySelector('#file'))[0].click();
		}
		
		$scope.filesChanged = function(img) {
			vm.mensaje = '';
			var files = angular.element(document.querySelector('#file'))[0].files;
			for (var i = 0; i < files.length; i++) {
				vm.adjuntos.push(files[i]);
				if(vm.adjuntos.length>vm.requisitosOferta.length){
					vm.adjuntos.splice(vm.adjuntos.indexOf(img), 1);
					vm.mensaje = 'Supero el limite de archivos, maximo: ' + vm.requisitosOferta.length;
					break;
				}
			}
			vm.$apply();			
		}
		
		vm.filesEliminar = function(img){
			vm.adjuntos.splice(vm.adjuntos.indexOf(img), 1);
		}
		
		vm.inscripcion = {
			oferta : {
				codigoEncriptado : $location.search().codigo,
				codigoPreInscripcion : $location.search().id
			},
			persona:null
		};
		
		vm.requisitos = {};
		vm.loadProgramaRequisitos = function() {
			$http
					.get(
							proyecto + 'consultarRequisitosOferta?codigo=0&codigoEncriptado=' + $location.search().codigo).success(
							function(data) {
								vm.requisitos = data;
							}).error(function(data) {
						console.log('Error: ' + data);
					});
		};
		vm.loadProgramaRequisitos();
		
		vm.requisitosOferta = {};
		vm.loadRequisitosOferta = function() {
			$http.get(
					proyecto + 'consultarRequisitosOfertaAdjuntos/' + vm.inscripcion.oferta.codigoEncriptado).success(
					function(data) {
						vm.requisitosOferta = data;
						if(vm.requisitosOferta != ''){
							vm.max = 5;
						}else{
							vm.max = 4;
						}
					}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		vm.loadRequisitosOferta();
		
		
		vm.selectedIndex = 0;

		vm.previousTab = function() {
			vm.mensaje = "";
			var index = (vm.selectedIndex == vm.max) ? 0 : vm.selectedIndex - 1;
			vm.selectedIndex = index;
		}
		
		vm.identificacionCotizante = function(){
			if(vm.inscripcion.persona.tipoAfiliacion.codigo == 1){
				vm.inscripcion.persona.identificacionCotizante = vm.inscripcion.persona.identificacion;
			}else{
				vm.inscripcion.persona.identificacionCotizante = '';
			}
				
		}
		
		vm.nextTab = function() {
			if (vm.selectedIndex == 0) {
				if ((vm.inscripcion.persona.tipoIdentificacion != undefined && vm.inscripcion.persona.tipoIdentificacion.codigo != 0)
					&& (vm.inscripcion.persona.lugarExpedicionPais != undefined && vm.inscripcion.persona.lugarExpedicionPais.codigo != 0)
					
					&& (vm.inscripcion.persona.lugarExpedicionD != undefined && vm.inscripcion.persona.lugarExpedicionD.codigo != 0 || vm.inscripcion.persona.lugarExpedicionPais.codigo != 21)
					&& (vm.inscripcion.persona.lugarExpedicion != undefined && vm.inscripcion.persona.lugarExpedicion.codigo != 0 || vm.inscripcion.persona.lugarExpedicionPais.codigo != 21)
					
					&& (vm.inscripcion.persona.genero != undefined && vm.inscripcion.persona.genero != '' && vm.inscripcion.persona.genero != null)
					&& (vm.inscripcion.persona.estadoCivil != undefined && vm.inscripcion.persona.estadoCivil.codigo != 0 && vm.inscripcion.persona.estadoCivil.codigo != '')
					&& (vm.inscripcion.persona.grupoSanguineo != undefined && vm.inscripcion.persona.grupoSanguineo.codigo != 0)
					&& (vm.inscripcion.persona.lenguaNativa != undefined && vm.inscripcion.persona.lenguaNativa.codigo != 0) 
				) {
					var index = (vm.selectedIndex == vm.max) ? 0
							: vm.selectedIndex + 1;
					vm.selectedIndex = index;
					vm.mensaje = "";
				} else {
					vm.mensaje = "Debe seleccionar e ingresar toda la informacion general...";
				}
			} else if (vm.selectedIndex == 1) {
				if ((vm.inscripcion.persona.lugarNacimientoPais != undefined && vm.inscripcion.persona.lugarNacimientoPais.codigo != 0)
						
					&& (vm.inscripcion.persona.lugarNacimientoD != undefined && vm.inscripcion.persona.lugarNacimientoD.codigo != 0 || vm.inscripcion.persona.lugarNacimientoPais.codigo != 21)
					&& (vm.inscripcion.persona.lugarNacimiento != undefined && vm.inscripcion.persona.lugarNacimiento.codigo != 0 || vm.inscripcion.persona.lugarNacimientoPais.codigo != 21)
					
					&& (vm.inscripcion.persona.fechaNacimiento != undefined && vm.inscripcion.persona.fechaNacimiento != '')
					&& (vm.inscripcion.persona.lugarResidenciaPais != undefined && vm.inscripcion.persona.lugarResidenciaPais.codigo != 0)
					
					&& (vm.inscripcion.persona.lugarResidenciaDepartamento != undefined && vm.inscripcion.persona.lugarResidenciaDepartamento.codigo != 0 || vm.inscripcion.persona.lugarResidenciaPais.codigo != 21)
					&& (vm.inscripcion.persona.lugarResidencia != undefined && vm.inscripcion.persona.lugarResidencia.codigo != 0 || vm.inscripcion.persona.lugarResidenciaPais.codigo != 21)
					
					&& (vm.inscripcion.persona.direccion != undefined && vm.inscripcion.persona.direccion != '')
					&& (vm.inscripcion.persona.barrio != undefined && vm.inscripcion.persona.barrio != '')
					&& (vm.inscripcion.persona.telefonoMovil != undefined && vm.inscripcion.persona.telefonoMovil != 0 && vm.inscripcion.persona.telefonoMovil != '')
					&& (vm.inscripcion.persona.estrato != undefined && vm.inscripcion.persona.estrato.codigo != 0)
					&& (vm.inscripcion.persona.eps != undefined && vm.inscripcion.persona.eps.codigo != 0)
					&& (vm.inscripcion.persona.tipoAfiliacion != undefined && vm.inscripcion.persona.tipoAfiliacion.codigo != 0)
					&& (vm.inscripcion.persona.identificacionCotizante != undefined && vm.inscripcion.persona.identificacionCotizante != 0 && vm.inscripcion.persona.identificacionCotizante != '')) {
					
					var index = (vm.selectedIndex == vm.max) ? 0
							: vm.selectedIndex + 1;
					vm.selectedIndex = index;					
					vm.mensaje = "";
				} else {
					vm.mensaje = "Debe seleccionar e ingresar toda la informacion complementaria...";
				}
			} else if (vm.selectedIndex == 2) {
				var index = (vm.selectedIndex == vm.max) ? 0
						: vm.selectedIndex + 1;
				vm.selectedIndex = index;
			}else if (vm.selectedIndex == 3) {
				if(vm.adjuntos.length==vm.requisitosOferta.length ){//&& vm.adjuntos.length>0 
					var index = (vm.selectedIndex == vm.max) ? 0
							: vm.selectedIndex + 1;
					vm.selectedIndex = index;
					vm.mensaje = "";
				}else{
					vm.mensaje = "Debe adjuntar los archios requeridos...";
				}
			}
		};

		vm.users = [ { codigo : 1, name : 'Bob' }, { codigo : 2, name : 'Alice' }, { codigo : 3, name : 'Steve' } ];
		vm.selectedUser = { codigo : 2};
		vm.first = false;
		
		vm.preInscripcionDatos = {};
		vm.loadPreInscripcionDatos = function() {
			$http.get( proyecto + 'consultarDatosPreInscripcion?codigoEncriptado=' + vm.inscripcion.oferta.codigoPreInscripcion)
				.success( function(data) {
					vm.preInscripcionDatos = data; 
					console.log(vm.preInscripcionDatos);
					if(vm.preInscripcionDatos=='' || vm.preInscripcionDatos==null){
						alert("Ya existe una inscripción para esta oferta.");
						$window.location.href = proyecto;
					}else{
						vm.inscripcion.persona = vm.preInscripcionDatos.persona;
						if(vm.inscripcion.persona.identificacionCotizante==0){
							vm.inscripcion.persona.identificacionCotizante = undefined;
						}
						if (vm.inscripcion.persona.lugarExpedicion != null) {						
							vm.inscripcion.persona.lugarExpedicionPais = vm.inscripcion.persona.lugarExpedicion.departamento.pais;
							vm.inscripcion.persona.lugarExpedicionD = vm.inscripcion.persona.lugarExpedicion.departamento;
						}
						if (vm.inscripcion.persona.lugarNacimiento != null) {						
							vm.inscripcion.persona.lugarNacimientoPais = vm.inscripcion.persona.lugarNacimiento.departamento.pais;
							vm.inscripcion.persona.lugarNacimientoD = vm.inscripcion.persona.lugarNacimiento.departamento;
						}
						vm.inscripcion.persona.fechaExpedicion = new Date(vm.preInscripcionDatos.persona.fechaExpedicionLong);
						vm.inscripcion.persona.fechaNacimiento = new Date(vm.preInscripcionDatos.persona.fechaNacimientoLong);
						vm.inscripcion.persona.tipoAfiliacion = vm.preInscripcionDatos.persona.tipoAfiliacion;
						
						vm.validarFechas();
						
						vm.first = true;
					}
				}).error(function(data) {
					console.log('Error: ' + data);
			});
		};
		vm.loadPreInscripcionDatos();
		
		/*Validar fechas expedicion y nacimiento*/
		vm.validarFechas = function (){ 
			vm.myDateExpedicion = new Date();
			//si el tipo de identificacion es cc la fecha minima de nacimiento y expedicion es 18 años, de lo contratio son 14
			if(vm.inscripcion.persona.tipoIdentificacion.codigo == 5){ 
				vm.minDateExpedicion = new Date( 
					(vm.inscripcion.persona.fechaNacimiento.getFullYear() + 18 ), //la fecha de expedicion debe ser >= a las fecha de nacimiento + 18 años
					vm.myDateExpedicion.getMonth(),
					vm.myDateExpedicion.getDate()
				);
			}else{ 
				vm.minDateExpedicion = new Date( 
					(vm.inscripcion.persona.fechaNacimiento.getFullYear() + 14 ), //la fecha de expedicion debe ser >= a las fecha de nacimiento + 18 años
					vm.myDateExpedicion.getMonth(),
					vm.myDateExpedicion.getDate()
				);
			}
			vm.maxDateExpedicion = new Date(
					vm.myDateExpedicion.getFullYear(),
					vm.myDateExpedicion.getMonth(),
					vm.myDateExpedicion.getDate()
			);
			
			vm.myDateNacimiento = new Date();
			vm.minDateNacimiento = new Date(
					(vm.myDateNacimiento.getFullYear()-70),
					vm.myDateNacimiento.getMonth(),
					vm.myDateNacimiento.getDate()
			);
			vm.maxDateNacimiento = new Date(
					(vm.myDateNacimiento.getFullYear()-18),
					vm.myDateNacimiento.getMonth(),
					vm.myDateNacimiento.getDate()
			);
			
		}		
		/*Fin validar fechas */
		
		
		
		
				
		vm.requiere = {};
		vm.loadRequierePago = function() {
			$http.get(
					proyecto + 'consultarEstadoOferta/' + vm.inscripcion.oferta.codigoEncriptado).success(
					function(data) {
						vm.requiere = data;
						if (vm.requiere == '') {
							alert('Las fechas para inscripciones ya pasaron.');
							$window.location.href = proyecto;
						}
					}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		vm.loadRequierePago();

		vm.tipoId = null;
		vm.tipoIds = [];

		vm.loadTipoIds = function() {
			$http.get(proyecto + 'tipoIdentificacion/').success(function(data) {
				vm.tipoIds = data;
			}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		vm.loadTipoIds();

		vm.eps = null;
		vm.epss = [];

		vm.loadEpss = function() {
			$http.get(proyecto + 'eps/').success(function(data) {
				vm.epss = data;
			}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		vm.loadEpss();
		
		vm.na = null;
		vm.nas = [];

		vm.loadNAs = function() {
			$http.get(proyecto + 'nivelAcademico/').success(function(data) {
				vm.nas = data;
			}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		vm.loadNAs();

		vm.tipoAfiliacion = null;
		vm.tipoAfiliacioness = [];

		vm.loadEpsTipos = function() {
			$http.get(proyecto + 'epsTipoAfiliacion/').success(function(data) {
				vm.tipoAfiliacioness = data;
			}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		vm.loadEpsTipos();
		
		vm.gs = null;
		vm.gss = [];

		vm.loadGrupoSanguineos = function() {
			$http.get(proyecto + 'grupoSanguineo/').success(function(data) {
				vm.gss = data;
			}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		vm.loadGrupoSanguineos();

		vm.ecs = [];
		vm.loadEstadoCivils = function() {
			$http.get(proyecto + 'estadoCivil/').success(function(data) {
				vm.ecs = data;
			}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		vm.loadEstadoCivils();

		vm.paisLugar = [];
		vm.loadPais = function() {
			$http.get(proyecto + 'paises').success(function(data) {
				vm.paisLugar = data;
			}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		vm.loadPais();
		
		//inscripcionCtrl.loadDepartamentos(inscripcionCtrl.inscripcion.persona.lugarExpedicionPais.codigo, 'depsLugarExpedicion')
		
		vm.depsLugarExpedicion = [];
		vm.depsLugarNacimiento = [];
		vm.depsLugarResidencia = [];		
		vm.loadDepartamentos = function(idPais, departamentosCollection) {
			$http.get(proyecto + 'departamentos?idPais=' + idPais).success(
				function(data) {
					vm[departamentosCollection] = data;						
				}).error(function(data) {
				console.log('Error: ' + data);
			});
		};

		setTimeout(function(){ 
			vm.loadDepartamentos(vm.inscripcion.persona.lugarExpedicionPais.codigo,'depsLugarExpedicion'); 
			}, 3000);
		
		setTimeout(function(){ 
			vm.loadDepartamentos(vm.inscripcion.persona.lugarExpedicionPais.codigo,'depsLugarNacimiento'); 
			}, 3000);
		
		setTimeout(function(){ 
			vm.loadDepartamentos(vm.inscripcion.persona.lugarExpedicionPais.codigo,'depsLugarResidencia'); 
			}, 3000);
		
		vm.munsLugarExpedicion = [];
		vm.munsLugarNacimiento = [];
		vm.munsLugarResidencia = [];
		vm.loadMunicipios = function(idDep, municipiosCollection) {
			$http.get(proyecto + 'municipios?idDep=' + idDep).success(
				function(data) {
					vm[municipiosCollection] = data;						
				}).error(function(data) {
				console.log('Error: ' + data);
			});
		};

		this.genero = [ {
			id : 'M',
			name : 'MASCULINO'
		}, {
			id : 'F',
			name : 'FEMENINO'
		} ];

		vm.es = null;
		vm.ess = [];

		vm.loadEstratoSocioeconomicos = function() {
			$http.get(proyecto + 'estratoSocioeconomico/').success(
					function(data) {
						vm.ess = data;
					}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		vm.loadEstratoSocioeconomicos();

		vm.lenguaNativa = [];
		vm.loadLenguaNativas = function() {
			$http.get(proyecto + 'lenguaNativa/').success(function(data) {
				vm.lenguaNativa = data;
			}).error(function(data) {
				console.log('Error: ' + data);
			});
		};
		vm.loadLenguaNativas();

		vm.estudios_anteriores = [];
		
		vm.eliminarEstudioAnterior = function(e) {
			vm.estudios_anteriores.splice(vm.estudios_anteriores.indexOf(e), 1);
		}

		function mostrarRespuesta(data) {
			$mdDialog.show($mdDialog.alert().title("Formulario de Inscripcion")
					.textContent(data.mensaje).ariaLabel(
							"Formulario de Inscripcion").ok('Aceptar'));
		}

		function mostrarProgressBar() {
			$mdDialog.show($mdDialog.alert().title("Formulario de Inscripcion")
					.textContent("Enviando informacion, por favor espere...")
					.ok('Cargando...'));
		}

		function DialogController($scope, $http, $mdDialog, accion, items, nas) {			
			$scope.accion = accion;
			$scope.estudioAnterior = items;
			$scope.nivelAcademico = nas;
			
			$scope.hide = function() {
				$mdDialog.hide();
			};

			$scope.cancel = function() {
				$mdDialog.cancel();
			};

			$scope.answer = function(answer) {
				var a = {
					result : $scope.estudioAnterior,
					answer : answer
				}
				$mdDialog.hide(a);
			};
		}
		
		var url = proyecto + 'inscripcionUsuario/';
		var registrarResource = $resource(url, {
			codigo : '@codigo'
		});

		vm.agregarNuevoEstudioAnterior = function(accion) {
			var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))
					&& vm.customFullscreen;

			if (accion == "Adicionar") {
				vm.nivelAcademico = vm.nas;				
				vm.estudioAnterior = {
					nivelAcademico : null,
					institucion : '',
					titulo : '',
					anio:''
				};
			}

			$mdDialog.show({
				controller : DialogController,
				templateUrl : 'formaEstudioAnterior/',
				parent : angular.element(document.body),
				fullscreen : useFullScreen,
				locals : {
					// items : vm.registrar,
					items : vm.estudioAnterior,
					nas : vm.nivelAcademico,					
					accion : accion
				}
			}).then(function(answer) {
				if (answer.answer == "ok") {
					if (accion == "Adicionar") {
						vm.estudios_anteriores.push(answer.result);
						var data = {
							mensaje : 'Operacion Exitosa'
						}
						// mostrarRespuesta(data);
					}
				}
			});
		}
		
		vm.botones = true;
		vm.loading = false;
		
		vm.submitForm = function() {
			if ((vm.inscripcion.persona.tipoIdentificacion != undefined && vm.inscripcion.persona.tipoIdentificacion.codigo != 0)
				&& (vm.inscripcion.persona.lugarExpedicionPais != undefined && vm.inscripcion.persona.lugarExpedicionPais.codigo != 0)
				
				&& (vm.inscripcion.persona.lugarExpedicionD != undefined && vm.inscripcion.persona.lugarExpedicionD.codigo != 0 || vm.inscripcion.persona.lugarExpedicionPais.codigo != 21)
				&& (vm.inscripcion.persona.lugarExpedicion != undefined && vm.inscripcion.persona.lugarExpedicion.codigo != 0 || vm.inscripcion.persona.lugarExpedicionPais.codigo != 21)
				
				&& (vm.inscripcion.persona.genero != undefined && vm.inscripcion.persona.genero != '' && vm.inscripcion.persona.genero != null)
				&& (vm.inscripcion.persona.estadoCivil != undefined && vm.inscripcion.persona.estadoCivil.codigo != 0 && vm.inscripcion.persona.estadoCivil.codigo != '')
				&& (vm.inscripcion.persona.grupoSanguineo != undefined && vm.inscripcion.persona.grupoSanguineo.codigo != 0)
				&& (vm.inscripcion.persona.lenguaNativa != undefined && vm.inscripcion.persona.lenguaNativa.codigo != 0) ) {

				if ((vm.inscripcion.persona.lugarNacimientoPais != undefined && vm.inscripcion.persona.lugarNacimientoPais.codigo != 0)
						
						&& (vm.inscripcion.persona.lugarNacimientoD != undefined && vm.inscripcion.persona.lugarNacimientoD.codigo != 0 || vm.inscripcion.persona.lugarNacimientoPais.codigo != 21)
						&& (vm.inscripcion.persona.lugarNacimiento != undefined && vm.inscripcion.persona.lugarNacimiento.codigo != 0 || vm.inscripcion.persona.lugarNacimientoPais.codigo != 21)
						
						&& (vm.inscripcion.persona.fechaNacimiento != undefined && vm.inscripcion.persona.fechaNacimiento != '')
						&& (vm.inscripcion.persona.lugarResidenciaPais != undefined && vm.inscripcion.persona.lugarResidenciaPais.codigo != 0)
						
						&& (vm.inscripcion.persona.lugarResidenciaDepartamento != undefined && vm.inscripcion.persona.lugarResidenciaDepartamento.codigo != 0 || vm.inscripcion.persona.lugarResidenciaPais.codigo != 21)
						&& (vm.inscripcion.persona.lugarResidencia != undefined && vm.inscripcion.persona.lugarResidencia.codigo != 0 || vm.inscripcion.persona.lugarResidenciaPais.codigo != 21)
						
						&& (vm.inscripcion.persona.direccion != undefined && vm.inscripcion.persona.direccion != '')
						&& (vm.inscripcion.persona.barrio != undefined && vm.inscripcion.persona.barrio != '')
						&& (vm.inscripcion.persona.telefonoMovil != undefined && vm.inscripcion.persona.telefonoMovil != 0 && vm.inscripcion.persona.telefonoMovil != '')
						&& (vm.inscripcion.persona.estrato != undefined && vm.inscripcion.persona.estrato.codigo != 0)
						&& (vm.inscripcion.persona.eps != undefined && vm.inscripcion.persona.eps.codigo != 0)
						&& (vm.inscripcion.persona.tipoAfiliacion != undefined && vm.inscripcion.persona.tipoAfiliacion.codigo != 0)
						&& (vm.inscripcion.persona.identificacionCotizante != undefined && vm.inscripcion.persona.identificacionCotizante != 0 && vm.inscripcion.persona.identificacionCotizante != '')) {
					
					if(vm.adjuntos.length<vm.requisitosOferta.length && vm.requisitosOferta.length>0){
						alert("Debe adjuntar los archios requeridos...");
					}else{
						// registrarResource.save(vm.inscripcion);
						vm.botones = false;
						vm.loading = true;
						vm.inscripcion.estudioAnterior = vm.estudios_anteriores;
						console.log("===========");
console.log(vm.inscripcion);
						registrarResource.save(
							vm.inscripcion,
							function(data) {
								if (data.body != null) {
									if (data.body.cantidadRequisitos > 0 && data.body.codigoInscripcion > 0) {
										var fd = new FormData();
										for(var i=0; i < vm.adjuntos.length ; i++){
											fd.append('file', vm.adjuntos[i]);
										}
										$http.post(proyecto + 'certificados/' + data.body.cantidadRequisitos + '/' + data.body.codigoInscripcion, fd, {
											headers : {
												'Content-Type' : undefined
											},
											transformRequest : angular.identity
										}).then(function successCallback(response) {
											file = null;
									
											$mdDialog.hide(answer);
											mostrarRespuesta(response.data);
											
										}, function errorCallback(response) {
											$scope.MsgError = response.data.mensaje;
											$scope.showHints = true;
											return false;
										});
									}
								}			
								mostrarRespuesta(data);
								vm.botones = true;
								vm.loading = false;
								}, function(response) {	
									alert('errror');
									mostrarRespuesta(response.data);
									/*setTimeout(function() {
										vm.mostrarAviso()
									}, 6000);*/								
							});
					}
				} else {
					alert('Seleccione todos los datos de la informacion complementaria...');
				}

			} else {
				alert('Seleccione todos los datos de la informacion general...');
			}
		}
		vm.mostrarAviso = function() {
			$window.location.href = CONFIG.RUTA_PORTAL;
		}
	}
})();

angular.module('inscripcion.controller').directive(
		'validNumber',
		function() {
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