<md-bottom-sheet class="md-grid" layout="column" ng-controller="OfertaController as of">
  <div layout="row" layout-align="center center" ng-cloak>
    <!-- h4>Since <code>clickOutsideToClose = false</code>, drag down or press ESC to close</h4-->
  </div>
  <div ng-cloak>
    <md-list flex layout="row" layout-align="center center">
    	<form ng-submit="$event.preventDefault()">
	      <md-list-item ng-repeat="oferta in of.facultadesOferta" >
	        <md-checkbox ng-click="of.filtroAvanzado(oferta.programa.codigoUaa, '')">{{oferta.programa.nombreUaa}}</md-checkbox>
	      </md-list-item>
	    </form>
    </md-list>
  </div>
</md-bottom-sheet>