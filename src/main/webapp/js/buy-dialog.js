/*
 * 标记用户购买的类型,只可以取以下两种值
 * 1）积分充值：credits-0,credits-1,credits-2,credits-3,credits-4
 * 2）单件商品：md5值
 * 例如，用户选中购物车中的两件商品，点击结算时，buyList=["第一件商品的md5", "第二件商品的md5"]
 */
var buyList = new Array();

var totalPrice = 0;	//一次交易的价格，可能是单件商品，也可能是一个商品的数组，也可以是积分充值。

$(function() {
	function buyWithAlipay() {
		$("#alipay-id").removeClass("ui-state-error");		
		var reg = new RegExp("\^[0-9]*$");
		if(reg.test($("#alipay-id").val())&& $("#alipay-id").val().length == 32) {
			var price = Number($("#price_hidden").val());
			var size = Number($("#size_hidden").val());
			var orderData={"tradeType":$("#tradeType_hidden").val(),"md5": $("#md5_hidden").val(),"title": $("#title_hidden").val(),"price": price,"size":size,"coverUrl": $("#covelUrl_hidden").val(),"bizNo":$("#alipay-id").val()};
			//直接购买Ajax
			$.ajax({
		  		type:"POST",  
		        dataType: 'text',  
		        contentType:"application/json",             
		        data:JSON.stringify(orderData),  
		        url:"user/addCart.htm",  
		        complete: function(data){
		        	alert(eval(data.responseText));
		        	dialogBuy.dialog("close");
		        } 
	        });  
		}
		else {
			$("#alipay-id").addClass("ui-state-error");
	  		$("#alipay-id").val("请输入正确的交易号")
	  		setTimeout(function() {
	  			$("#alipay-id").removeClass("ui-state-error", 1500);
	  		}, 500);
		}
	}
	
	
	function creditsWithAlipay() {
		alert("dada");
		$("#alipay-id").removeClass("ui-state-error");		
		var reg = new RegExp("\^[0-9]*$");
		if(reg.test($("#alipay-id").val())&& $("#alipay-id").val().length == 32) {
			var orderData={"bizNo":$("#alipay-id").val()};
			//直接购买Ajax
			$.ajax({
		  		type:"POST",  
		        dataType: 'text',  
		        contentType:"application/json",             
		        data:JSON.stringify(orderData),  
		        url:"creditsBuy.htm",  
		        complete: function(data){
		        	alert(eval(data.responseText));
		        	dialogCreditsBuy.dialog("close");
		        } 
	        });  
		}
		else {
			$("#alipay-id").addClass("ui-state-error");
	  		$("#alipay-id").val("请输入正确的交易号")
	  		setTimeout(function() {
	  			$("#alipay-id").removeClass("ui-state-error", 1500);
	  		}, 500);
		}
	}
	
	$("#alipay-id").focus(function(){
		if($("#alipay-id").val() == "请输入正确的交易号") {
			$("#alipay-id").val("");
		}
	});
	
	
	dialogCreditsBuy = $("#buy-credits-form").dialog({
      	autoOpen: false,
      	height: 560,
      	width: 500,
      	modal: true,
      	buttons: {
        	"立即确认": creditsWithAlipay,
        	"取 消": function() {
        		dialogCreditsBuy.dialog("close");
        	}
      	},
      	close: function() {
      		$("#alipay-id").removeClass("ui-state-error");
      	}
    });
	$("[aria-describedby='buy-credits-form'] .ui-dialog-titlebar").css("width", "466px");
	$("[aria-describedby='buy-credits-form'] .ui-dialog-buttonpane").css("width", "490px");
	$($("[aria-describedby='buy-credits-form'] .ui-button")[1]).css("margin-left", "56.5px");
	$($("[aria-describedby='buy-credits-form'] .ui-button")[2]).css("margin-left", "113px");
	
	dialogBuy = $("#buy-form").dialog({
      	autoOpen: false,
      	height: 560,
      	width: 500,
      	modal: true,
      	buttons: {
        	"立即确认": buyWithAlipay,
        	"取 消": function() {
        		dialogBuy.dialog("close");
        	}
      	},
      	close: function() {
      		$("#alipay-id").removeClass("ui-state-error");
      	}
    });
	$("[aria-describedby='buy-form'] .ui-dialog-titlebar").css("width", "466px");
	$("[aria-describedby='buy-form'] .ui-dialog-buttonpane").css("width", "490px");
	$($("[aria-describedby='buy-form'] .ui-button")[1]).css("margin-left", "56.5px");
	$($("[aria-describedby='buy-form'] .ui-button")[2]).css("margin-left", "113px");
});