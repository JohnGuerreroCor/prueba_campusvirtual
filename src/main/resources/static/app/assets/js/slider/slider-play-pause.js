
var timer;

var autoTrigger = function(){
	$('.btnSlideNext').trigger('click');
	timer = setTimeout(autoTrigger, 6000);
};

var playTrigger = function(){
	$('.playSlider').hide();
	$('.stopSlider').show();
	timer = setTimeout(autoTrigger, 6000);
}

var stopTrigger = function(){
	$('.playSlider').show();
	$('.stopSlider').hide();
	clearTimeout(timer);
	timer = null;
}

playTrigger();
