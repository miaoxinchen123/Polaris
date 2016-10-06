/*
 * 我们将对话框作为全局变量
 */
var dialogRegister;
var dialogLogin;
var dialogContact;

$(function() {
	/*
	 *	register dialog
	 */
	var emailRegexRegister = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
	emailRegister = $("#email-register"),
	nameRegister = $("#name-register"),
	invitationRegister = $("#invitation-register"),
	passwordRegister = $("#password-register"),
	passwordRegister2 = $("#password-register-2"),
	allFieldsRegister = $([]).add(emailRegister).add(nameRegister).add(passwordRegister).add(passwordRegister2),
	tipsRegister = $(".validate-tips-register");
	
	
	/**
	 * 定义一些跳转事件
	 */
	bindClickandHref("#user-center", "./user/order.htm");
	bindClickandHref("#cart", "./user/cart.htm");
	bindClickandHref("#credits-topup", "./user/credits.htm");
	bindClickandHref("#footer-order", "./user/order.htm");
	bindClickandHref("#footer-cart", "./user/cart.htm");
	bindClickandHref("#footer-credits", "./user/credits.htm");
	bindClickandHref("#footer-profile", "./user/profile.htm");
	bindClickandHref("#order-menu", "./user/order.htm");
	bindClickandHref("#cart-menu", "./user/cart.htm");
	bindClickandHref("#credits-menu", "./user/credits.htm");
	bindClickandHref("#profile-menu", "./user/profile.htm");
	bindClickandHref("#help-credits", "./user/credits.htm");
	bindClickandHref("#help-profile", "./user/profile.htm");
	bindClickandHref("#help-profile1", "./user/profile.htm");
	
	
	bindClickandOpenContactDialog("#contact-us", "search");
	
	bindClickandOpenContactDialog("#footer-contact-us", "search");
	bindClickandOpenContactDialog("#footer-contact-us-search", "search");
	bindClickandOpenContactDialog("#footer-contact-us-resend", "resend");
	bindClickandOpenContactDialog("#footer-contact-us-suggestion", "suggestion");
	
	
	function updateTipsRegister(t) {
		tipsRegister
	    	.text( t )
	    	.addClass("ui-state-highlight-customized");
	  	setTimeout(function() {
	  		tipsRegister.removeClass("ui-state-highlight-customized", 1500);
	  	}, 500 );
	}
	

	function checkNameLengthRegister(o, min, max) {
		if(o.val().length == 0) {
			o.addClass("ui-state-error");
	    	updateTipsRegister("昵称不能为空");
	    	return false;
		}
		else if(o.val().length > max) {
			o.addClass("ui-state-error");
	    	updateTipsRegister("昵称超度不能超过16个字符");
	    	return false;    		
		} 
	  	else {
	    	return true;
	  	}
	}
	
	function checkLengthRegister(o, min, max) {
		if(o.val().length == 0) {
			o.addClass("ui-state-error");
	    	updateTipsRegister("密码不能为空");
	    	return false;
		}
		else if(o.val().length > max) {
			o.addClass("ui-state-error");
	    	updateTipsRegister("密码超度不能超过16个字符");
	    	return false;    		
		} 
	  	else {
	    	return true;
	  	}
	}
	
	function checkRegexpRegister(o, regexpRegister) {
		if (!(regexpRegister.test(o.val()))) {
	    	o.addClass("ui-state-error");
	    	updateTipsRegister("请输入正确的邮箱地址");
	    	return false;
	  	} 
	  	else {
	    	return true;
	  	}
	}
	
	function checkPasswordSame(p1, p2) {
		if(p1.val() != p2.val()) {
			p2.addClass("ui-state-error");
	    	updateTipsRegister("两次密码输入不一致");
	    	return false;
		}
		else {
			return true;
		}
	}
	
	function addUser() {
	  	var valid = true;
	  	allFieldsRegister.removeClass("ui-state-error");
	  	
	  	valid = valid && checkRegexpRegister(emailRegister, emailRegexRegister);
	  	valid = valid && checkNameLengthRegister(passwordRegister,  1, 16);
	  	valid = valid && checkLengthRegister(passwordRegister, 1, 16);
	  	valid = valid && checkLengthRegister(passwordRegister2, 1, 16);
	  	valid = valid && checkPasswordSame(passwordRegister, passwordRegister2);
	  	
	  	//valid = valid && checkLength( email, "email", 6, 80 );
	  	//valid = valid && checkRegexp( name, /^[a-z]([0-9a-z_\s])+$/i, "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter.");
	  	//valid = valid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9");
	
	  	if(valid) {
	  		//注册Ajax
	  		var userData={"email":emailRegister.val(),"password":passwordRegister.val(),"name":nameRegister.val(),"invitationCode":invitationRegister.val()};
	        $.ajax({  
		        type:"POST",  
		        dataType: 'json',  
		        contentType:"application/json",             
		        data:JSON.stringify(userData),  
		        url:"user/addUser.htm",  
		        error:function(data){  
		            alert("出错了！！:"+data);  
		        },  
		        success:function(data){  
		        	var loginStatus = data.code;
		        	if(loginStatus == "200"){
		        		defaultlogin(data);
		        		dialogRegister.dialog("close");
		        	}else if(loginStatus == "201"){
		        		emailRegister.addClass("ui-state-error");
		    	    	updateTipsRegister(data.meassage);
		        	}else if(loginStatus == "202"){
		        		invitationRegister.addClass("ui-state-error");
		    	    	updateTipsRegister(data.meassage);
		        	}
		        }  
	        });  
	  	}
	  	return valid;
	}
	
	dialogRegister = $("#dialog-register-form").dialog({
	  	autoOpen: false,
	  	height: 425,
	  	width: 410,
	  	modal: true,
	  	buttons: {
	    	"立即注册": addUser,
	    	"取 消": function() {
	      		dialogRegister.dialog("close");
	    	}
	  	},
	  	close: function() {
	    	allFieldsRegister.removeClass("ui-state-error");
	  	}
	});
	
	$("#email-register").focus(function() {
		if($("#email-register").val() == "用于发货, 请您仔细填写") {
			$("#email-register").css("color", "black");
		}
	});
	$("#email-register").blur(function() {
		if($("#email-register").val() == "用于发货, 请您仔细填写" 
				|| $("#email-register").val() == "") {
			$("#email-register").val("用于发货, 请您仔细填写"); 
			$("#email-register").css("color", "rgb(180,180,180)");
		}
	});
	
	function blurEmailLoginInput() {
		if($("#email-register").val() == "" || $("#email-register").val() == "用于发货, 请您仔细填写") {
			$("#email-register").val("用于发货, 请您仔细填写");
			$("#email-register").css("color", "rgb(210,210,210)");
		}
	}
	
	$("#register").on("click", function() {
	  	dialogRegister.dialog("open");
	  	$("#email-register").val("用于发货, 请您仔细填写");
	  	$("#password-register").val("");
	  	$("#password-register2").val("");
	  	$(".validate-tips-register").val("请填写以下信息");
	});
	
	$("#register-now").on("click", function() {
		dialogLogin.dialog("close");
	  	dialogRegister.dialog("open");
	  	$("#email-register").val("用于发货, 请您仔细填写");
	  	$("#password-register").val("");
	  	$("#password-register2").val("");
	  	$(".validate-tips-register").val("请填写以下信息");
	});
	
	
	$("#logout").on("click", function() {
		 //根据Session中的值来判断展示页面
		window.location="logOut.htm";
	});
	
	/*
	 * login dialog
	 */
	var emailRegexLogin = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
	emailLogin = $("#email-login"),
	passwordLogin = $("#password-login"),
	allFieldsLogin = $([]).add(emailLogin).add(passwordLogin),
	tips = $(".validate-tips-login");
	
	function updateTipsLogin(t) {
		tips
	    	.text( t )
	    	.addClass("ui-state-highlight-customized");
	  	setTimeout(function() {
	    	tips.removeClass("ui-state-highlight-customized", 1500 );
	  	}, 500 );
	}
	
	function checkLengthLogin ( o, n, min, max ) {
		if(o.val().length == 0) {
			o.addClass("ui-state-error");
	    	updateTipsLogin("密码不能为空");
	    	return false;
		}
		else if(o.val().length > max) {
			o.addClass("ui-state-error");
	    	updateTipsLogin("密码超度不能超过16个字符");
	    	return false;    		
		} 
	  	else {
	    	return true;
	  	}
	}
	
	function checkRegexpLogin( o, regexp, n) {
	  	if (!(emailRegexLogin.test( o.val()))) {
	    	o.addClass("ui-state-error");
	    	updateTipsLogin("请输入正确的邮箱地址");
	    	return false;
	  	} 
	  	else {
	    	return true;
	  	}
	}
	
	function login() {
	  	var valid = true;
	  	allFieldsLogin.removeClass("ui-state-error");
	  	
	  	valid = valid && checkRegexpLogin(emailLogin, emailRegexLogin);
	  	valid = valid && checkLengthLogin(passwordLogin, "password-login", 1, 16);
	  	
	
	  	if(valid) {
	  	//登陆Ajax
	  		var userData={"email":emailLogin.val(),"password":passwordLogin.val()};
	        $.ajax({  
		        type:"POST",  
		        dataType: 'json',  
		        contentType:"application/json",             
		        data:JSON.stringify(userData),  
		        url:"user/logIn.htm",  
		        error:function(data){  
		            alert("出错了！！:"+data);  
		        },  
		        success:function(data){  
		        	var loginStatus = data.code;
		        	if(loginStatus == "200"){
		        		defaultlogin(data);
		        		dialogLogin.dialog("close");
		        	}else if(loginStatus == "203"){
		        		emailLogin.addClass("ui-state-error");
		    	    	updateTipsLogin("请输入正确用户名密码");
		        	}
		        }  
	        });  
	  	}
	}
	
	dialogLogin = $("#dialog-login-form").dialog({
	  	autoOpen: false,
	  	height: 310,
	  	width: 400,
	  	modal: true,
	  	buttons: {
	    	"登 录": login,
	    	"取 消": function() {
	    		dialogLogin.dialog("close");
	    	}
	  	},
	  	close: function() {
	    	allFieldsLogin.removeClass("ui-state-error");
	  	}
	});
	
	$("#login").on("click", function() {
		dialogLogin.dialog("open");
		$("#email-login").val("");
	  	$("#password-register").val("");
	  	$("#password-register2").val("");
	  	$(".validate-tips-register").val("请填写以下信息")
	});
	
	/*
	 *	contact-us dialog
	 */
	var contactTypeOption = $("#contact-type-option"),
    orderId = $("#order-id"),
    contactDetail = $("#contact-detail");
    allFieldsContact = $([]).add(contactTypeOption).add(orderId).add(contactDetail);
  
    function submitContact() {
    	
    	var data={"contactType":$('#contact-type-option option:selected').val(),
    			"orderId":$('#order-id').val(),"message":$('#contact-detail').val()};
    	
    	$.ajax({  
	        type:"POST",  
	        dataType: 'text',  
	        contentType:"application/json", 
	        data:JSON.stringify(data),  
	        url:"user/saveMessage.htm",  
	        error:function(data){  
	            alert("出错了！！:"+data);  
	        },  
	        success:function(data){  
	        	result = eval(data);
	        	alert(result);
	        	dialogContact.dialog("close");
	        }  
        });  
    }
 
    dialogContact = $("#dialog-contact-us-form").dialog({
      	autoOpen: false,
      	height: 380,
      	width: 400,
      	modal: true,
      	buttons: {
        	"提 交": submitContact,
        	"取 消": function() {
        		dialogContact.dialog("close");
        	}
      	},
      	close: function() {
      		allFieldsContact.removeClass("ui-state-error");
      	}
    });
    
    function fillContactUs(option) {
    	if(option == "search") {
    		$("[for='order-id']").css("display", "none");
        	$("#order-id").css("display", "none");
        	$("[for='contact-detail']").text("我想要");
        	$("#contact-detail").text("如您在Nile Science没有检索到您需要的电子书或文献，请在此留言您需要的电子书及文献标题。我们将全力帮您深度检索，并将检索结果发送到您的邮箱。");
        	$("#contact-type-option").removeAttr("disabled");
        	$("#order-id").removeAttr("disabled");
    	}
    	else if(option == "resend") {
    		$("[for='order-id']").css("display", "block");
    		$("#order-id").css("display", "block");
    		$("#order-id").val("");
    		$("[for='contact-detail']").text("您的留言");
    		$("#contact-detail").text("如您没有成功收货，建议您在个人中心里添加备用邮箱。如您已添加备用邮箱，建议您在此留言1-2个其他邮箱，以增加收货成功率。");
    		$("#contact-type-option").removeAttr("disabled");
        	$("#order-id").removeAttr("disabled");
    	}
    	else if(option == "suggestion") { 
    		$("[for='order-id']").css("display", "none");
    		$("#order-id").css("display", "none");
    		$("[for='contact-detail']").text("您的建议");
    		$("#contact-detail").text("请您在此留下您的宝贵意见。如您的意见得以采纳，我们将赠送您300积分（可抵30元购物现金）。");
    		$("#contact-type-option").removeAttr("disabled");
        	$("#order-id").removeAttr("disabled");
    	}
    }
    
    function openContactDialog(option) {
    	$("#contact-type-option").val(option);
    	fillContactUs(option);
    	dialogContact.dialog("open");	
    }
    
    $("#contact-type-option").on("change", function() {
    	fillContactUs($("#contact-type-option").val());
    });
    
    function openLoginDialog() {
    	dialogLogin.dialog("open");
    	$("#email-login").val("");
      	$("#password-register").val("");
      	$("#password-register2").val("");
      	$(".validate-tips-register").val("请填写以下信息");
    }

    function bindClickandHref(id, target) {
    	$(id).on("click", function() {
    	 $.ajax({  
		        type:"GET",  
		        dataType: 'text',  
		        contentType:"application/json",   
		        url:target,  
		        error:function(data){  
		            alert("出错了！！:"+data);  
		        },  
		        success:function(data){  
		        	result = eval(data);
		        	if(result == "203"){
		        		dialogLogin.dialog("open");
		        		$("#email-login").val("");
		        	  	$("#password-register").val("");
		        	  	$("#password-register2").val("");
		        	}else if(result == "cart"){
		        		 window.open("cart.htm","_self");
		        	}else if(result == "order"){
		        		 window.open("order.htm","_self");
		        	}else if (result =="credits"){
		        		 window.open("loadCredits.htm", "_self");
		        	}else if (result =="profile"){
		        		 window.open("loadProfile.htm", "_self");
		        	}
		        }  
	        });  
    	});
    }
    
    function bindClickandOpenContactDialog(id, target) {
    	$(id).on("click", function() {
	    	openContactDialog(target);
		});	
    }
    
    function bindClickandOpenLoginDialog(id) {
    	$(id).on("click", function() {
    		openLoginDialog();
		});	
    }
    
    //根据Session中的值来判断展示页面
    $.ajax({  
        type:"GET", 
        dataType: 'json',  
        contentType:"application/json",             
        url:"user/queryLoginStatus.htm",  
        error:function(data){  
            alert("出错了！！:"+data.code);  
        },  
        success:function(data){ 
        	var loginStatus = data.code;
        	if(loginStatus == "200"){
        		defaultlogin(data);
        	}else if(loginStatus == "203"){
        		loginOut();
        	}
        }  
    });  
    
    
    //默认界面/登出界面
	function loginOut(){
	    $("#head-username").css("display", "none");
		$("#head-username").text("");
		
		$("#credits").css("display", "none");
		$("#credits-topup").css("display", "none");
		$("#credits-amount").text("");
		$("#cart-amount").text("");
		$("#cart").css("display", "none");
		
		$("#login").css("display", "block");
		$("#register").css("display", "block");
		$("#logout").css("display", "none");
	
	}
    
    
	/*
	 * 登陆
	 */
	function defaultlogin(data){
		var creditsAmounts = data.credits;	//用户积分数量
		var rewardCreditsAmounts = data.rewardCredits;	//用户积分数量
		var cartCnt = data.cartCnt;			//用户购物车内的物品数量
		var username = data.name;	//用户昵称
		
		if(window.location.pathname.split("/")[window.location.pathname.split("/").length - 1] == "credits.html") {
			$("#credits-info").text(creditsAmounts);
		}
		
		$("#head-username").css("display", "block");
		$("#head-username").text(username);
		
		$("#credits").css("display", "block");
		$("#credits-topup").css("display", "block");
		$("#credits-amount").text(creditsAmounts);
		$("#credits-info").text(creditsAmounts);
		$("#reward-credits-info").text(rewardCreditsAmounts);
		$("#cart").css("display", "block");
		$("#cart-amount").text(cartCnt);
		$("#menu-cartCnt").text(cartCnt);
		$("#login").css("display", "none");
		$("#register").css("display", "none");
		$("#logout").css("display", "block");
	}
		
});