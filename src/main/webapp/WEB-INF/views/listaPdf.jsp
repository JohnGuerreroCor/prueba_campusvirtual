<md-dialog>
	<md-toolbar class="bg-main-gray text-main-white">
      <div class="md-toolbar-tools">
        <h2>Módulo {{carpeta.replace("_", " ") | removeSpaces | addSpaces}}</h2>
        <span flex></span>
           

	      <md-button ng-click="cancel()">
	        Cerrar
	      </md-button>
      </div>
    </md-toolbar>
    <md-dialog-content>
      <div class="md-dialog-content">
      	
      	<br>
	      <p>
	      	A continuación están todas las guías que describen el módulo {{carpeta.replace("_", " ")}}, estas pueden ser descargadas en formato PDF dando clic sobre el icono de descarga. 
	      </p>
	      <br>
		  
		<md-list  class="md-dense" flex>
		  	<md-list-item class="md-3-line" layout-align="center centar" ng-repeat="a in archivos"  >
    
				  <div class="md-list-item-text " ng-class="md-offset" ng-if="a.tipo == 'file'">
		            <h3>  {{a.nombre}} </h3>
		          </div>
		          <a layout-align="center centar" class="link_guia" target="_blank" href="guias/sakai/{{tipo}}/{{carpeta}}/{{a.nombre.replace('.pdf','')}}" ng-if="a.tipo == 'file'"><i class="fa fa-download  fa-2x" aria-hidden="true"></i></a>
		        
			          <div class="md-list-item-text " ng-class="md-offset" ng-if="a.tipo == 'folder'">
			           	<br>
			          	<h3>  <strong>{{a.nombre}}</strong> </h3>
			            <md-list  class="md-dense" flex>
			  				<md-list-item class="md-3-line" layout-align="center centar" ng-repeat="b in a.archivos"  >
				           		 <div class="md-list-item-text " ng-class="md-offset" ng-if="b.tipo == 'file'">
						            <h3>  {{b.nombre}} </h3>
						          </div>
						          <a layout-align="center centar" class="link_guia" target="_blank" href="guias/sakai/{{tipo}}/{{carpeta}}/{{a.nombre}}/{{b.nombre.replace('.pdf','')}}" ng-if="b.tipo == 'file'"><i class="fa fa-download  fa-2x" aria-hidden="true"></i></a>
						        
				           	</md-list-item>
			           	</md-list>
			         </div>
			<md-divider ></md-divider> 
        </md-list-item>
      </md-list>
     
    </md-dialog-content>

</md-dialog>