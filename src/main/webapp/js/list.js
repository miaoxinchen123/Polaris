$(function () {

	var currentUrl = window.location.href;
	var searchText;
	var pageIndex;
	
	if(currentUrl.split("search=").length > 1) {
		searchText = decodeURIComponent(currentUrl.split("search=")[1].split("&")[0]);
		$("#search-input").val(searchText);	
	}
	else {
		$("#search-input").val("");
	}
	
	if(currentUrl.split("page=").length > 1) {
		pageIndex = parseInt(currentUrl.split("page=")[1].split("&")[0]);
	}
	
	
	
});




