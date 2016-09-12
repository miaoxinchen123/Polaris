var otherHeight;

$(function() {
	/*
	 * 控制底部的footer部分一直在最底部显示
	 */
	if(document.documentElement.clientHeight > otherHeight) {
		document.getElementById("footer").style.marginTop = (document.documentElement.clientHeight - otherHeight) + "px";	
	}
	else {
		document.getElementById("footer").style.marginTop = "0px";
	}
	$(window).on("resize", function() {
		if(document.documentElement.clientHeight > otherHeight) {
			document.getElementById("footer").style.marginTop = (document.documentElement.clientHeight - otherHeight) + "px";	
		}
		else {
			document.getElementById("footer").style.marginTop = "0px";
		}
    });
});