<md-dialog aria-label="Proceso de inscripci�n" id="initMessage">
  <form ng-cloak>

    <md-dialog-content>
      <div ng-cloak>
		  <md-content>
		    <md-tabs class="tabsInscripcion" md-dynamic-height md-selected="selectedIndex">
		      <md-tab label="Oferta acad�mica">
		        <md-content class="md-padding">
		          <h3 class="md-headline">Ingrese a la secci�n de oferta acad�mica</h3><br>
		          <div width="100%"  align="center"><img class="gif" src="/campusvirtual/app/assets/img/ingresar_oferta.gif" alt="Ingrese a la secci�n de oferta acad�mica"></div>
         		  <div width="100%" align="center">
         		  	<md-button  class="md-raised md-primary" ng-click="selectedIndex=1" ng-disabled="true"><i class="fa fa-arrow-left" aria-hidden="true"></i></md-button>
		          	<md-button  class="md-raised md-primary" ng-click="selectedIndex=1"><i class="fa fa-arrow-right" aria-hidden="true"></i></md-button>
         		  </div>
		        </md-content>
		      </md-tab>
		      <md-tab label="Seleccione el programa acad�mico">
		        <md-content class="md-padding">
		          <h3 class="md-headline">Filtre y seleccione el programa acad�mico</h3><br>
		          <div width="100%" align="center"><img class="gif" src="/campusvirtual/app/assets/img/selecciona_programa_academico.gif" alt="Filtre y selecciona el programa acad�mico"></div>
		          <div width="100%" align="center">
		          	<md-button  class="md-raised md-primary" ng-click="selectedIndex=0"><i class="fa fa-arrow-left" aria-hidden="true"></i></md-button>
		          	<md-button  class="md-raised md-primary" ng-click="selectedIndex=2"><i class="fa fa-arrow-right" aria-hidden="true"></i></md-button>
		          </div>
		        </md-content>
		      </md-tab>
		      <md-tab label="Inscr�base">
		        <md-content class="md-padding">
		          <h3 class="md-headline">Revise los detalles del programa e inscr�base</h3><br>
		          <div width="100%" align="center"><img class="gif" src="/campusvirtual/app/assets/img/inscripcion.gif" alt="Revise los detalles del programa e inscr�base"></div>
		          <div width="100%" align="center">	
		          	<md-button  class="md-raised md-primary" ng-click="selectedIndex=1"><i class="fa fa-arrow-left" aria-hidden="true"></i></md-button>
		          	<md-button  class="md-raised md-primary" ng-disabled="true"><i class="fa fa-arrow-right" aria-hidden="true"></i></md-button>
		          </div>          
		        </md-content>
		      </md-tab>
		    </md-tabs>
		  </md-content>
	  </div>
    </md-dialog-content>

    <md-dialog-actions layout="row">
      <md-button  class="md-raised md-primary" ng-click="answer(true)">
        Entendido
      </md-button>
    </md-dialog-actions>
  </form>
</md-dialog>