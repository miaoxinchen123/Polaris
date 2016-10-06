<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<meta name="keywords" content="英文电子书,Nile Science,尼罗河,电子书,文献,文献检索,电子书检索,ebook,article"/>
<meta name="description" content="Nile Science -- 尼罗河拥有庞大的电子书及文献数据库，提供一站式外语电子书文献服务：高效检索，便捷购买，高速下载。">
<title>我的积分 Nile Science 英文电子书文献检索平台</title>
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="bookmark icon" href="images/favicon.ico">

<link rel="stylesheet" href="./css/ui/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="./css/global.css" />
<link rel="stylesheet" type="text/css" href="./css/index.css" />
<link rel="stylesheet" type="text/css" href="./css/credits.css" />
<link rel="stylesheet" type="text/css" href="./css/buy-form.css" />

<script src="./js/jquery-1.12.4.js"></script>
<script src="./js/jquery-ui.js"></script>
<script src="./js/footer-margin-control.js"></script>
<script src="./js/account-dialog-control.js"></script>
<script src="./js/buy-dialog.js"></script>

<script>
otherHeight = 715;

$(function () {
	var creditsPrice = new Array(45.9, 85.9, 165.9, 399.9, 779.9);
	
	for(var i = 0; i < $(".credits-buy").length; i++) {
		(function(i) {
			$(".credits-buy")[i].onclick = function() {
				buyList[0] = [i];
				$("#dialog-price-1").text(" ￥" + creditsPrice[i]);
				$("#dialog-price-2").text(" ￥" + creditsPrice[i]);
				dialogCreditsBuy.dialog("open");
			};
		})(i);
	}	
	
	
});

function withdraw(){
	 $.ajax({  
	        type:"GET",  
	        dataType: 'json',  
	        contentType:"application/json",             
	        url:"user/withdraw.htm",  
	        error:function(data){  
	            alert("出错了！！:"+data);  
	        },  
	        success:function(data){  
	        	alert(data);
	        }  
     });  
}
</script>
</head>

<body>
<div id="dialog-contact-us-form" title="联系我们">
  	<form>
    	<fieldset>
      		<label for="contact-type">联系内容</label>
      		<select id="contact-type-option">
   				<option value="search">检索不到我要的</option>
				<option value="resend">申请重新发货</option>
				<option value="suggestion">意见反馈</option>
			</select>
      		<label for="order-id">订单编号</label>
      		<input type="text" name="order-id" id="order-id" value="" class="text ui-widget-content ui-corner-all">
      		<label for="contact-detail">您的留言</label>
      		<textarea name="contact-detail" id="contact-detail" cols="20" rows="5"></textarea>
     </fieldset>
  </form>
</div>

<div id="buy-credits-form" title="积分充值">
<form>
    <fieldset>
		<label for="alipay_id">支付宝交易号1/流水号</label>
      	<input type="text" name="alipay-id" id="alipay-id" value="" class="text ui-widget-content ui-corner-all">
    </fieldset>
     
    <p class="dialog-sub-title">第一步 付款</p>
     
	<div id="dialog-step-1-1">
		<div id="alipay-text">扫一扫二维码，付款<span id="dialog-price-1"></span></div>
     	<div id="alipay_graph"><img src="<%=request.getContextPath()%>/images/alipay_graph.jpg" alt=""/></div>
     </div>
     <div id="dialog-step-1-2">或者</div>
     <div id="dialog-step-1-3">
     	<div id="transfer-text">转账到支付宝付款<span id="dialog-price-2"></span><br>nilescience@qq.com</div>
     	<div id="alipay_transfer"><img src="<%=request.getContextPath()%>/images/alipay_transfer.png" alt=""/></div>
   	</div>
     
   	<p class="dialog-sub-title">第二步 填写交易号/流水号</p>
   	<div id="dialog-step-2">
		<p>款成功后，请填写支付宝的交易号/流水号，点击确认即可。</p>
   		<p>样本：20160402200040022200140041083666</p>
   	</div>
</form>
</div>

<div id="header">
	<span id="head-username"></span><div id="welcome">欢迎到<a href="<%=request.getContextPath()%>/gotoIndex.htm"><img id="head-logo" src="./images/head-logo.png" alt="Nile Science"></a></div>
	<div id="login" class="header-button">登录</div><div id="register" class="header-button">注册</div><div id="logout" class="header-button">注销</div>
	<div id="user-center">个人中心</div>
	<div id="credits"><span id="credits-amount"></span> 积分</div>
	<div id="credits-topup" class="header-button">充值</div>
	<div id="cart">购物车 <b style="color:white" id="cart-amount"></b> 件</div>
	<div id="contact-us">联系我们</div>
	<div id="help-me"><a target="_blank" href="./help.htm">帮助中心</a></div>
	<div id="reader-download"><a target="_blank" href="./readerDownload.htm">阅读器下载</a></div>
</div>

<div id="credits-wrapper">
	<div id="user-center-navigator">
		<div class="user-center-head"><span>个人中心</span></div>
		<div id="cart-menu" class="user-center-menu "><a>购物车 <b style="color:rgb(128,15,37); text-decoration:underline;" id="menu-cartCnt"></b></a></div>
		<div id="order-menu" class="user-center-menu"><a>我的订单</a></div>
		<div id="credits-menu" class="user-center-menu user-center-menu-selected"><a>我的积分</a></div>
		<div id="profile-menu" class="user-center-menu"><a>个人资料</a></div>
	</div>
	
	<div id="credits-list">
		<div id="credits-head">
			<span>我的积分</span>
		</div>
				
		<div id="credits-info-div">
			<div id="credits-info-label">您目前的积分为：</div>
			<div id="credits-info"></div>
			
			<div id="credits-info-label">您目前的奖励积分为：</div>
			<div id="reward-credits-info"></div>
			<div id="how-to-use-credits" onclick="withdraw()">提现奖励积分</div>		
		</div>
		
		
		<div id="credits-item-wrapper">
			<div class="credits-item" id="credits-item-1">
				<div class="credits-img" id="credits-img-1"></div>
				<div class="credits-price" id="credits-price-1">￥ 45.9</div>
				<div class="credits-buy" id="credits-buy-1">立即充值</div>
			</div>
			<div class="credits-item" id="credits-item-2">
				<div class="credits-img" id="credits-img-2"></div>
				<div class="credits-price" id="credits-price-2">￥ 85.9</div>
				<div class="credits-buy" id="credits-buy-2">立即充值</div>
			</div>
			<div class="credits-item" id="credits-item-3">
				<div class="credits-img" id="credits-img-3"></div>
				<div class="credits-price" id="credits-price-3">￥ 165.9</div>
				<div class="credits-buy" id="credits-buy-3">立即充值</div>
			</div>
			<div class="credits-item" id="credits-item-4">
				<div class="credits-img" id="credits-img-4"></div>
				<div class="credits-price" id="credits-price-4">￥ 399.9</div>
				<div class="credits-buy" id="credits-buy-4">立即充值</div>
			</div>
			<div class="credits-item" id="credits-item-5">
				<div class="credits-img" id="credits-img-5"></div>
				<div class="credits-price" id="credits-price-5">￥ 779.9</div>
				<div class="credits-buy" id="credits-buy-5">立即充值</div>
			</div>
		</div>
	</div>
</div>


<div id="footer">
	<div id="footer-wrapper">
		<div style="width:780px; margin-left:auto; margin-right:auto;">
			<div class="footer-navigator-wrapper">
				<div class="footer-navigator-head"><span id="footer-user-center">个人中心</span></div>
				<div class="footer-navigator-menu"><span id="footer-cart">购物车</span></div>
				<div class="footer-navigator-menu"><span id="footer-order">我的订单</span></div>
				<div class="footer-navigator-menu"><span id="footer-credits">我的积分</span></div>
				<div class="footer-navigator-menu"><span id="footer-profile">个人资料</span></div>
			</div>
			<div class="footer-navigator-wrapper">
				<div class="footer-navigator-head">阅读器下载</div>
				<div class="footer-navigator-menu"><a href="./readerDownload.htm">Adobe Reader</a></div>
				<div class="footer-navigator-menu"><a href="./readerDownload.htm">Calibre</a></div>
				<div class="footer-navigator-menu"><a href="./readerDownload.htm">Digital Editions</a></div>
				<div class="footer-navigator-menu"><a href="./readerDownload.htm">iReader</a></div>
			</div>
			<div class="footer-navigator-wrapper">
				<div class="footer-navigator-head">帮助中心</div>
				<div class="footer-navigator-menu"><a href="./help.htm">支付问题</a></div>
				<div class="footer-navigator-menu"><a href="./help.htm">电子书文件问题</a></div>
				<div class="footer-navigator-menu"><a href="./help.htm">其他问题</a></div>
			</div>
			<div class="footer-navigator-wrapper">
				<div class="footer-navigator-head" id="footer-contact-us"><span>联系我们</span></div>
				<div class="footer-navigator-menu" id="footer-contact-us-search"><span>检索不到我要的</span></div>
				<div class="footer-navigator-menu" id="footer-contact-us-resend"><span>申请重新发货</span></div>
				<div class="footer-navigator-menu" id="footer-contact-us-suggestion"><span>意见反馈</span></div>
			</div>
			<div class="footer-right-wrapper">
				<div id="footer-mobile-graph"></div>
				<div class="footer-graph-text"><p><b>手机打开</b></p><p><b>快人一步</b></p></div>
				<div id="footer-copyright">
					<p><span>©2016 Nile Science</span></p>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>