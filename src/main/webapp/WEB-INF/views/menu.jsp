<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<header md-portal-header class="md-portal-header" hide-xs hide-sm >
	<md-toolbar scroll-one layout="row" layout-align="center stretch" class="toolbar-portal-first bg-main-black text-main-white animation">
		 
        <div class="dropdown" ng-repeat="it in ul.firstMenu">
		    <a class="dropbtn text-main-white" ng-href="{{it.url}}" target="{{it.target?it.target:'_top'}}">{{it.name}}</a>
		</div>

		<div class="social-networks">
			<a href="https://www.facebook.com/universidadsurcolombianaoficial/" target="_blank" title="Facebook de la USCO"><i class="fa fa-facebook-official" aria-hidden="true"></i></a>
			<a href="https://twitter.com/uscooficial" target="_blank" title="Twitter de la USCO"><i class="fa fa-twitter-square" aria-hidden="true"></i></a>
		</div>

	</md-toolbar>

	<md-toolbar scroll-two layout="row" class="toolbar-portal-second animation">
	
		<div layout="colum" flex="40" layout-align="space-around center">

			<div class="dropdown" ng-repeat="it in ul.secondMenu" ng-if="it.part == 'a'">
			  <button class="dropbtn hvr-underline-from-left no-cursor" ng-if="it.type == 'toggle'">{{it.name}}</button>	
			  <button class="dropbtn hvr-underline-from-left" ng-if="it.type == 'link'" ng-click="ul.redirectMenu(it.url, it.target)">{{it.name}}</button>		  
			  <div class="dropdown-content left-dropdown" ng-if="it.type == 'toggle'">
			    <a ng-repeat="subIt in it.content" ng-click="ul.redirectMenu(subIt.url, subIt.target)">{{subIt.name}}</a>
			  </div>
			</div>

		</div>

		<span layout="colum" flex=20>			
			<div class="logo-content" >
				
				<md-button class="logo-button" aria-label="Usco-logo" ng-click="ul.redirectMenu(ul.rutaPortal,'_top')">
					<md-tooltip md-visible="false"  md-direction="bottom">Volver al inicio</md-tooltip>
					<img src="/campusvirtual/app/assets/img/logo-institucional.png" alt="Universidad Surcolombiana" title="Universidad Surcolombiana"/>
				</md-button>
			</div>
		</span>

		<div layout="colum" flex="30" layout-align="space-around center">
		
			<div class="dropdown" ng-repeat="it in ul.secondMenu" ng-if="it.part == 'b'">
			  <button class="dropbtn hvr-underline-from-right no-cursor" ng-if="it.type == 'toggle'">{{it.name}}</button>
			  <button class="dropbtn hvr-underline-from-right" ng-if="it.type == 'link'" ng-click="ul.redirectMenu(it.url,it.target)">{{it.name}}</button>
			  <div class="dropdown-content right-dropdown" ng-if="it.type == 'toggle'">
			    <a ng-repeat="subIt in it.content" ng-click="ul.redirectMenu(subIt.url,subIt.target)">{{subIt.name}}</a>
			  </div>
			</div>
		</div>
		
		<div class="escudo-container" layout="colum" flex="10" layout-align="space-around center">
			<img class="current-demo escudo-colombia" hide-xs hide-sm src="/campusvirtual/app/assets/img/escudo-colombia.png" alt="Republica de Colombia" title="Republica de Colombia"/>
        </div>
	</md-toolbar>

	<md-toolbar scroll-three layout="row" class="md-whiteframe-1dp toolbar-portal-third animation">

		<div layout="colum" flex="40" layout-align="space-around center">
		
			<div class="dropdown" ng-repeat="it in ul.thirdMenu" ng-if="it.part == 'a'">
			  <button class="dropbtn hvr-underline-from-left no-cursor" ng-if="it.type == 'toggle'">{{it.name}}</button>
			  <button class="dropbtn hvr-underline-from-left" ng-if="it.type == 'link'" ng-click="ul.redirectMenu(it.url,it.target)">{{it.name}}</button>
			  <div class="dropdown-content left-dropdown" ng-if="it.type == 'toggle'">
			    <a ng-repeat="subIt in it.content" ng-click="ul.redirectMenu(subIt.url,subIt.target)">{{subIt.name}}</a>
			  </div>
			</div>
			
		</div>

		<span layout="colum" flex=20></span>

		<div layout="colum" flex="30" layout-align="space-around center">

			<div class="dropdown" ng-repeat="it in ul.thirdMenu" ng-if="it.part == 'b'">
			  <button class="dropbtn hvr-underline-from-right no-cursor" ng-if="it.type == 'toggle'">{{it.name}}</button>
			  <button class="dropbtn hvr-underline-from-right" ng-if="it.type == 'link'" ng-click="ul.redirectMenu(it.url,it.target)">{{it.name}}</button>
			  <div class="dropdown-content right-dropdown" ng-if="it.type == 'toggle'">
			    <a ng-repeat="subIt in it.content" ng-click="ul.redirectMenu(subIt.url,subIt.target)">{{subIt.name}}</a>
			  </div>
			</div>
			
		</div>
		<span layout="colum" flex=10 class="bg-main-ocre"></span>
	</md-toolbar>

	<md-toolbar scroll-four layout="row" class="md-whiteframe-1dp toolbar-portal-fourth hiding animation">
		<div layout="colum" flex="40" layout-align="space-around center">
		
			<div class="dropdown" ng-repeat="it in ul.secondMenu" ng-if="it.part == 'a'">
			  <button class="dropbtn hvr-underline-from-left no-cursor" ng-if="it.type == 'toggle'">{{it.name}}</button>
			  <button class="dropbtn hvr-underline-from-left" ng-if="it.type == 'link'" ng-click="ul.redirectMenu(it.url,it.target)">{{it.name}}</button>
			  <div class="dropdown-content left-dropdown" ng-if="it.type == 'toggle'">
			    <a ng-repeat="subIt in it.content" ng-click="ul.redirectMenu(subIt.url,subIt.target)">{{subIt.name}}</a>
			  </div>
			</div>
			
		</div>

		<span flex=20>
			
			<div class="logo-content">
				<md-button class="logo-button" aria-label="Usco-logo" ng-click="ul.redirectMenu(ul.rutaPortal,'_top')">
					<md-tooltip md-visible="false"  md-direction="bottom">Volver al inicio</md-tooltip>
					<img src="/campusvirtual/app/assets/img/logo.png" alt="Universidad Surcolombiana" title="Universidad Surcolombiana"/>
				</md-button>
			</div>

		</span>

		<div layout="colum" flex="40" layout-align="space-around center">
		
			<div class="dropdown" ng-repeat="it in ul.secondMenu" ng-if="it.part == 'b'">
			  <button class="dropbtn hvr-underline-from-left no-cursor" ng-if="it.type == 'toggle'">{{it.name}}</button>
			  <button class="dropbtn hvr-underline-from-left" ng-if="it.type == 'link'" ng-click="ul.redirectMenu(it.url,it.target)">{{it.name}}</button>
			  <div class="dropdown-content left-dropdown" ng-if="it.type == 'toggle'">
			    <a ng-repeat="subIt in it.content" ng-click="ul.redirectMenu(subIt.url,subIt.target)">{{subIt.name}}</a>
			  </div>
			</div>
			
		</div>
	</md-toolbar>

	<mycreation></mycreation>

</header>

<header md-portal-header-mobile class="md-portal-header-mobile" hide-gt-sm>

	<md-toolbar id="mobile-toolbar-admin" layout="row" class="md-whiteframe-1dp mobile-toolbar-admin">
		<img  class="logo-mobile" src="/campusvirtual/app/assets/img/universidad-surcolombiana-mobile.png" alt="Universidad Surcolombiana" title="Universidad Surcolombiana" ng-click="ul.redirectMenu(ul.rutaPortal,'_top')"/>
		<span layout="colum" flex ></span>
		<img class="current-demo escudo-colombia-mobile" hide-md hide-lg hide-xl src="/campusvirtual/app/assets/img/escudo-colombia.png" alt="Republica de Colombia" title="Republica de Colombia"/>
       	<md-button class="menu" ng-click="ul.toggleMenu()"  aria-label="Show Menu List">
        	<md-icon md-svg-icon="menu" ></md-icon>
      	</md-button>
    </md-toolbar>
</header>

<md-sidenav id="sidenav-portal" md-component-id="left"  md-is-locked-open="$mdMedia('gt-sm-md-lg')" md-component-id="left" class="md-whiteframe-1dp sidenav-usco" layout="column" flex>  
	<div layout="row" id="option-sidenav-portal">
	   	<p id="menu-sidenav-portal" flex="90" >Menú</p>
	   	<div class="social-networks">
			<a href="https://www.facebook.com/universidadsurcolombianaoficial/" target="_blank" title="Facebook de la USCO"><i class="fa fa-facebook-official" aria-hidden="true"></i></a>
			<a href="https://twitter.com/uscooficial" target="_blank" title="Twitter de la USCO"><i class="fa fa-twitter-square" aria-hidden="true"></i></a>
		</div>
	</div>

	<!-- input layout="row" type="text" class="searcher-insidenav" name="searcher" placeholder="Que esta buscando?" ng-model="search"-->

	<md-list>
	
		<md-list-item ng-repeat="it in ul.menuMobile | filter:search" layout="column" >
     
			<md-button ng-click="ul.selectMenu(it);ul.toggleMenu();ul.redirectMenu(it.url,'_top');"  class="menu-button" ng-class="{'selected' : it === ul.selected }" ng-if="it.type == 'link'" >
				<i class="{{it.icon}}" aria-hidden="true"></i>
				{{it.name}}
			</md-button>

			<md-button ng-click="it.show?it.show=false:it.show=true;" class="menu-button" ng-class="{'selected' : it === ul.selected }" ng-if="it.type == 'toggle'" >
				<i class="{{it.icon}}" aria-hidden="true"></i>
				{{it.name}}
				<i class="fa fa-plus-circle more-links" aria-hidden="true"  ng-if="it.type == 'toggle' && it.show== false || it.show == undefined" ></i>
				<i class="fa fa-minus-circle more-links" aria-hidden="true" ng-show="it.show"></i>
			</md-button>

			<md-list id="sub-menu-portal" class="sub-menu-portal" ng-if="it.type == 'toggle'" ng-show="it.show" ng-animate="animate">
				<md-list-item id="subItem" ng-repeat="subIt in it.content" flex >
					<md-button ng-click="ul.selectMenu(subIt);ul.toggleMenu();ul.redirectMenu(subIt.url,'_top');" class="menu-button" ng-class="{'selected' : subIt === ul.selected }">
						<i class="{{subIt.icon}}" aria-hidden="true" ></i>
						{{subIt.name}}
					</md-button>
				</md-list-item>
			</md-list>

     </md-list-item>
     
   </md-list>
</md-sidenav>