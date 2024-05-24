<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<jsp:include page = "/head"></jsp:include>
<style>
md-list-item.md-3-line .md-list-item-text{
padding: 15px;
}
</style>
<link rel="stylesheet" type="text/css" href="/campusvirtual/app/assets/css/confirmarCorreo.css" />
<body>
	<div class="se-pre-con"></div>
	<jsp:include page = "/ieSupport"></jsp:include>
	<div id="master-content" layout="column">
		
		<jsp:include page = "/menu"></jsp:include>
		<jsp:include page = "/formPqr"></jsp:include>
		<md-content id="subsections-confirmar-correo" class="bg-main-white  _md layout-align-center-center layout-column" layout="column" layout-align="center center">
			<h1 class="title-subsection text-main-red md-headline" flex>Preguntas Frecuentes</h1>
			<md-content>
		    <md-list>
		      <md-list-item class="md-3-line">
		        <div class="md-list-item-text">
		          <h3>1. ¿Qué es Place to Pay?</h3>
		          <p>Place to Pay es la plataforma de pagos electrónicos que usa La Universidad Surcolombiana para procesar en línea las transacciones generadas en la tienda virtual con las formas de pago habilitadas para tal fin.  </p>
		        </div>
		        <md-divider></md-divider>
		      </md-list-item>
		      <md-list-item class="md-3-line">
		        <div class="md-list-item-text">
		          <h3>2. ¿Cómo puedo pagar?</h3>
		          <p>En el campus virtual de La Universidad Surcolombiana usted podrá realizar su pago con los medios habilitados para tal fin. Usted, de acuerdo a las opciones de pago escogidas por el comercio, podrá pagar a través de PSE (débitos desde cuentas de ahorros y corrientes en Colombia), tarjetas de crédito Visa, MasterCard, American Express, Credencial y Diners; Tarjeta Éxito, Tarjeta Alkosto,  débitos internacionales a través de Safety Pay o acercándose a un punto de Western Union en cualquier lugar del mundo.
				Además, si el comercio lo dispone así, podrá pagar en efectivo (Cajas de Almacenes Éxito, Carulla, Surtimax y Super Inter), además de PayPal e IVR Place to Pay.</p>
		        </div>
		        <md-divider></md-divider>
		      </md-list-item>
		      <md-list-item class="md-3-line">
		        <div class="md-list-item-text">
		          <h3>3. ¿Es seguro ingresar mis datos bancarios en este sitio web? </h3>
		          <p>Para proteger tus datos La Universidad Surcolombiana delega en Place to Pay la captura de la información sensible. Nuestra plataforma de pagos cumple con los más altos estándares exigidos por la norma internacional PCI DSS de seguridad en transacciones con tarjeta de crédito. Además tiene certificado de seguridad SSL expedido por GeoTrust una compañía Verisign, el cual garantiza comunicaciones seguras mediante la encriptación de todos los datos hacia y desde el sitio; de esta manera te podrás sentir seguro a la hora de ingresar la información de su tarjeta.</p> 
				  <p>Durante el proceso de pago, en el navegador se muestra el nombre de la organización autenticada, la autoridad que lo certifica y la barra de dirección cambia a color verde. Estas características son visibles de inmediato y dan garantía y confianza para completar la transacción en Place to Pay.</p>
				  <p>Place to Pay también cuenta con el monitoreo constante de McAfee Secure y la firma de mensajes electrónicos con Certicámara.</p>
		        </div>
		        <md-divider></md-divider>
		      </md-list-item>
		      <md-list-item class="md-3-line">
		        <div class="md-list-item-text">
		          <h3>4. ¿Puedo realizar el pago cualquier día y a cualquier hora? </h3>
		          <p>Sí, en La Universidad Surcolombiana podrás realizar tus compras en línea los 7 días de la semana, las 24 horas del día a sólo un clic de distancia. </p>
		        </div>
		        <md-divider></md-divider>
		      </md-list-item>
		      <md-list-item class="md-3-line">
		        <div class="md-list-item-text">
		          <h3>5. ¿Puedo cambiar la forma de pago? </h3>
		          <p>Si aún no has finalizado tu pago, podrás volver al paso inicial y elegir la forma de pago que prefieras. Una vez finalizada la compra no es posible cambiar la forma de pago. </p>
		          <br><p><strong>¿COMO HACER EFECTIVA LA DEVOLUCIÒN DEL PAGO CUANDO NO HE RECIBIDO EL SERVICIO?</strong></p>
				  <br><p>-Para hacer efectiva dicha solicitud se debe  radicar ante la oficina de Liquidacion de derechos pecuniarios, con los anexos correspondientes que avalan la operacion y una vez verificada la transacciòn,  se procede a dar tramite a la devolucion del pago el cual debera ser consultado  dentro de los siguientes 30 dias a la fecha de radicaciòn de la solicitud en los siguientes contactos:</p>
				  <br><br><p><strong>Oficina de Liquidacion de derechos pecuniarios:</strong> <a class="text-main-red" href="mailto:liderpecuniarios@usco.edu.co">liderpecuniarios@usco.edu.co</a></p>
                  <a class="text-main-red" href="tel:+5788754780">(057) (8) 8754780</a> </p>
				  <p><strong>Oficina de Tesoreria:</strong><a class="text-main-red" href="mailto:tesoreria@usco.edu.co"> tesoreria@usco.edu.co</a></p>
                  <a class="text-main-red" href="tel:+5788752436">(57) (8) 8752436</a> </p>
				  <br><p><strong>Procedimiento:</strong> <a class="text-main-red" target="_blank" href="https://www.usco.edu.co/contenido/SGC-USCO/11.AP-FIN-GESTION-FINANCIERA/AP-FIN-PR-18%20DEVOLUCIONES.pdf"> aqui </a></p>
		          
		        </div>
		        <md-divider></md-divider>
		      </md-list-item>
		      <md-list-item class="md-3-line">
		        <div class="md-list-item-text">
		          <h3>6. ¿Pagar electrónicamente tiene algún valor para mí como comprador? </h3>
		          <p>No, los pagos electrónicos realizados a través de Place to Pay no generan costos adicionales para el comprador. </p>
		        </div>
		        <md-divider></md-divider>
		      </md-list-item>
		      <md-list-item class="md-3-line">
		        <div class="md-list-item-text">
		          <h3>7. ¿Qué debo hacer si mi transacción no concluyó? </h3>
		          <p>En primera instancia, revisar si llegó un email de confirmación de la transacción a la cuenta de correo electrónico inscrita en el momento de realizar el pago, en caso de no haberlo recibido, deberás contactar a <a class="text-main-red" href="mailto:tesoreria@usco.edu.co"> tesoreria@usco.edu.co</a> para confirmar el estado de la transacción. </p>
		        </div>
		        <md-divider></md-divider>
		      </md-list-item>
		      <md-list-item class="md-3-line">
		        <div class="md-list-item-text">
		          <h3>8. ¿Qué debo hacer si no recibí el comprobante de pago?</h3>
		          <p>Por cada transacción aprobada a través de Place to Pay, recibirás un comprobante del pago con la referencia de compra en la dirección de correo electrónico que indicaste al momento de pagar. </p>
				  <p>Si no lo recibes, podrás contactar a la línea <a class="text-main-red" href="tel:+5788752436">(57) (8) 8752436</a> o al correo electrónico <a class="text-main-red" href="mailto:tesoreria@usco.edu.co"> tesoreria@usco.edu.co</a>, para solicitar el reenvío del comprobante a la misma dirección de correo electrónico registrada al momento de pagar. </p>
		        </div>
		        <md-divider></md-divider>
		      </md-list-item>
		    </md-list>
		  </md-content>
			
		</md-content>

	<jsp:include page = "/footer"></jsp:include>
	</div>
	<jsp:include page = "/jslibs"></jsp:include>
</body>
</html>
