
function open_panel()
{
	slideIt();
	var a=document.getElementById("sidebar");
	a.setAttribute("onclick","close_panel()");
}

function slideIt()
{	
	var slidingForm = parseInt($('#sliderForm > #header').css('right'));
	var stopPosition = 0;
	if (slidingForm < stopPosition )
	{
		$('#sliderForm  > #header').css('right','0px');
		$('#sidebar').css('right','260px');
		setTimeout(slideIt, 1);	
	}
}
	
function close_panel(){
	slideIn();
	a=document.getElementById("sidebar");
	a.setAttribute("onclick","open_panel()");
}

function slideIn()
{
	var slidingForm = parseInt($('#sliderForm > #header').css('right'));
	var stopPosition = -260;
	if (slidingForm > stopPosition )
	{
		$('#sliderForm > #header').css('right','-260px');
		$('#sidebar').css('right','0px');
		setTimeout(slideIn, 1);
	}
}

var ua = navigator.userAgent.toLowerCase();
var isAndroid = ua.indexOf("android") > -1; //&& ua.indexOf("mobile");

/*
$('#pqrTextarea').click(function() {
	if(isAndroid) {
		$('#sidebar').css('top','15vh');
		$('#header').css('top','-25vh');
	}
}); 

$('#pqrTextarea').blur(function() {
	if(isAndroid) {
		$('#sidebar').css('top','50vh');
		$('#header').css('top','10vh');
	}
}); 
*/



