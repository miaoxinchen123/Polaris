$(function() {
	$("#search-input").focus(function(){
		if($("#search-input").val() == "search in Nile Science") {
			$("#search-input").val("");
		}
		$("#search-input").css("color", "black");	
	});
});

$(function() {
	$("#search-input").blur(function(){
		if($("#search-input").val() == "") {
			$("#search-input").val("search in Nile Science");
			$("#search-input").css("color", "rgb(180,180,180)");
		}
	});
});