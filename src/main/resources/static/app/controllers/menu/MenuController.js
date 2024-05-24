(function(){
	'use strict';

	angular
		.module('menu.controller')
		.controller('MenuController',MenuController);
	
			function MenuController(menuService,$mdSidenav,$mdBottomSheet, $timeout, $log, $location, $window, CONFIG){
				
				var self = this;
				
				self.rutaPortal = CONFIG.RUTA_PORTAL;
				self.selected = null;
				self.menuMobile = [];
				self.firstMenu = [];
				self.secondMenu = [];
				self.thirdMenu = [];
				self.selectMenu = selectMenu;
				self.toggleMenu   = toggleMenuList;
				self.redirectMenu   = redirectMenu;
				
				menuService
				.firstMenu()
				.then( function(firstMenu){
					self.firstMenu = [].concat(firstMenu);
					self.menuMobile = [].concat(firstMenu);
				});
				
				menuService
					.secondMenu()
					.then( function(secondMenu){
						self.secondMenu = [].concat(secondMenu);
						self.menuMobile = self.menuMobile.concat(secondMenu);
					});

				menuService
					.thirdMenu()
					.then( function(thirdMenu){
						self.thirdMenu = [].concat(thirdMenu);
						self.menuMobile = self.menuMobile.concat(thirdMenu);
					});
				
				

				function toggleMenuList() {
			      $mdSidenav('left').toggle();
			    }

			    function selectMenu ( link ) {
			      self.selected = angular.isNumber(link) ? $scope.menu[link] : link;
			    }
			    
			    function redirectMenu(url,target){
			    	window.open(url,target);
			    }
	    
			}

})();