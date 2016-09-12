$(function() {
	
	/*
	 * 控制底部footer的margin
	 */
	
	var size = $("#size").val();
	
	otherHeight = 292 + 117 * size;
	$("#order-wrapper").css("height", 50 + 117 * size);
	
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
	});
});

function operateThis(operateThis,orderId){
	if(operateThis == "delete"){
		var url = "removeOrder.htm?orderId="+orderId;
		window.open(url, "_self");
	}else if(operateThis =="detail"){
		 var url="orderDetail.htm?orderId="+orderId; 
		 window.open(url, "_self");
	}else if(operateThis == "reSend"){
		/*
		 * 申请重新发货
		 */
		$("#contact-type-option").val("resend");
		$("#order-id").val(orderId);
		$("[for='contact-detail']").text("您的留言");
		$("#contact-detail").text("如您没有成功收货，建议您在个人中心里添加备用邮箱。如您已添加备用邮箱，建议您在此留言1-2个其他邮箱，以增加收货成功率。");
		
		$("[for='order-id']").css("display", "block");
		$("#order-id").css("display", "block");
		$("#contact-type-option").attr("disabled", "disabled");
    	$("#order-id").attr("disabled", "disabled");
    	
    	dialogContact.dialog("open");

		 
	}else if(operateThis == "orderDetail"){
		 var url="bookDetail.htm?MD5="+orderId; 
		 window.open(url, "_blank");
	}
}





