$(function() {
	function addOrderWithAlipay() {
		$("#alipay-id").removeClass("ui-state-error");		
		var reg = new RegExp("\^[0-9]*$");
		if(reg.test($("#alipay-id").val())&& $("#alipay-id").val().length == 32) {
			dialogBuy.dialog("close");
			var orderDetailId = $("#orderDetail_hidden").val();
			var bizNo = $("#alipay-id").val();
			var orderData={"orderDetailId":orderDetailId,"bizNo":bizNo};
			//直接购买Ajax
			$.ajax({
		  		type:"POST",  
		        dataType: 'text',  
		        contentType:"application/json",             
		        data:JSON.stringify(orderData),  
		        url:"cartDirectBuy.htm",  
		        complete: function(data){
		        	var url="/cart.htm";
		        	window.open("cart.htm","_self");
		        	alert(eval(data.responseText));
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
		
	
	var size = $("#size").val();
	
	/*
	 * 控制底部footer的margin
	 */
	otherHeight = 292 + 117 * size;
	$("#cart-wrapper").css("height", 50 + 117 * size);

	$("#checkbox-all-div").on("click", function(){
		
		var   CheckBox=document.getElementsByName("orderCheckbox");
		if(this.checked){   
			for(i=0;i<CheckBox.length;i++){
                CheckBox[i].checked=true;
           };
	    }else{   
	    	for(i=0;i<CheckBox.length;i++){
                CheckBox[i].checked=false;
           }; 
	    }
		
		changeTotal();
	});
	
	$("#cart-remove-span").on("click", function(){
		var orderDetailId="";
		$("input:checkbox[name='orderCheckbox']:checked").each(function(){
			orderDetailId =orderDetailId+ $(this).next().val()+",";
		});
		if(orderDetailId != ""){
			var url = "remove.htm?orderDetailId="+orderDetailId;
			window.open(url, "_self");
		}
	});
	
	$("#cart-buy-button").on("click", function(){
		var orderDetailId="";
		$("input:checkbox[name='orderCheckbox']:checked").each(function(){
			orderDetailId =orderDetailId+ $(this).next().val()+",";
		});
		if(orderDetailId != ""){
			 $("#orderDetail_hidden").val(orderDetailId);
			 dialogCartBuy.dialog("open");
		}
	});
	
	
	
	dialogCartBuy = $("#buy-form").dialog({
      	autoOpen: false,
      	height: 560,
      	width: 500,
      	modal: true,
      	buttons: {
        	"立即确认": addOrderWithAlipay,
        	"取 消": function() {
        		dialogBuy.dialog("close");
        	}
      	},
      	close: function() {
      		$("#alipay-id").removeClass("ui-state-error");
      	}
    });
	$("[aria-describedby='cart-form'] .ui-dialog-titlebar").css("width", "466px");
	$("[aria-describedby='cart-form'] .ui-dialog-buttonpane").css("width", "490px");
	$($("[aria-describedby='cart-form'] .ui-button")[1]).css("margin-left", "56.5px");
	$($("[aria-describedby='cart-form'] .ui-button")[2]).css("margin-left", "113px");
	
});




function checkOne(){
	changeTotal();
}


function changeTotal(){
	var total = 0 ;
	$("input:checkbox[name='orderCheckbox']:checked").each(function(){
		total +=Number($(this).val());
     });
	$("#cart-total-price").text(total);
}

function operateThis(operateThis,md5,orderDetailId){
	if(operateThis == "delete"){
		var url = "remove.htm?orderDetailId="+orderDetailId;
		window.open(url, "_self");
	}else if(operateThis =="detail"){
		 var url="bookDetail.htm?MD5="+md5; 
		 window.open(url, "_blank");
	}else if(operateThis == "buy"){
		 $("#orderDetail_hidden").val(orderDetailId);
		 dialogCartBuy.dialog("open");
	}
}

