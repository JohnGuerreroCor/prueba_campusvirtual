<md-dialog class="dialog-box">
	<md-toolbar class="bg-main-gray text-main-white">
      <div class="md-toolbar-tools">
        <h2>VIDEO {{nombre.replace('_',' ').replace('.mp4','')}}</h2>
        <span flex></span>
           

	      <md-button ng-click="cancel()">
	        CERRAR
	      </md-button>
      </div>
    </md-toolbar>
    <md-dialog-content >
      <div id="video-container" class="md-dialog-content" align="center" style="padding:0;">
 
		<video class="video-tutorial" controls autoplay>
		  <source src="{{ruta}}" type="video/mp4">
		</video>
     
    </md-dialog-content>

</md-dialog>