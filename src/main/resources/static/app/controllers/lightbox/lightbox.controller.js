angular.module('lightbox.controller').controller('LightboxController', LightboxController);

function LightboxController($scope, $mdDialog, CONFIG){
	$scope.status = false;
	$scope.customFullscreen = false;
	$scope.cookieExist = false;
	checkCookie();
	
	//If lightbox cookie does not exist, show the lightbox
	if(!$scope.cookieExist && $scope.status == false){
		$mdDialog.show({
			  controller : DialogController,
		      templateUrl: 'procesoInscripcion',
		      parent: angular.element(document.body),
		      clickOutsideToClose:true,
		      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
	    })
	    .then(function(answer) {
	      $scope.status =  answer;
	      console.log($scope.status);
	    }, function() {
	      $scope.status = false;
	    });
	}
	
	function DialogController($scope, $mdDialog) {
		$scope.rutaPortal = CONFIG.RUTA_PORTAL;
		$scope.selectedIndex=0;
	    $scope.hide = function() {
	      $mdDialog.hide();
	    };

	    $scope.cancel = function() {
	      $mdDialog.cancel();
	    };

	    $scope.answer = function(answer) {
	      $mdDialog.hide(answer);
	    };
	  }
	
	  	function checkCookie(){
	      var cockieLightbox = getCookie("lightbox");
	      console.log(cockieLightbox);
	      if(cockieLightbox){
	        //Do not show lightbox
	        $scope.cookieExist = true;
	        
	      }else{
	        //Show lightbox and set a cookie
	    	//Name of element, value, day to expires
	        setCookie("lightbox",true,15);
	        $scope.cookieExist = false;
	      }
	    }

	    function setCookie(cname, cvalue, exdays){
	      var d = new Date();
	      d.setTime(d.getTime() + (exdays*24*60*60*1000));
	      var expires = "expires=" + d.toGMTString();
	      document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
	    }

	    function getCookie(cname) {
	        var name = cname + "=";
	        var decodedCookie = decodeURIComponent(document.cookie);
	        var ca = decodedCookie.split(';');
	        for(var i = 0; i < ca.length; i++) {
	        	var c = ca[i];
	        	  var c = ca[i];
	              while (c.charAt(0) == ' ') {
	                  c = c.substring(1);
	              }
	              if (c.indexOf(name) == 0) {
	                  return true;
	              }
	        }
	        return false;
	    }

}

