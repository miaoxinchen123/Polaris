<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<html>
<head>
<meta charset="UTF-8">
<title>搜索结果</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico">
<link rel="bookmark icon" href="<%=request.getContextPath()%>/images/favicon.ico">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ui/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/global.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/list.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/buy-form.css" />

<script src="<%=request.getContextPath()%>/js/jquery-1.12.4.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/js/account-dialog-control.js"></script>
<script src="<%=request.getContextPath()%>/js/list.js"></script>
<script src="<%=request.getContextPath()%>/js/buy-dialog.js"></script>
<script>


	$(function () {
		$("#search-button-index").on("click", function() {
			var searchText = $("#search-input").val();
			if(searchText.length != 0 &&  searchText != "search in Nile Science") {
				$("#f_ation").submit();
			}
		});
	});
	/*
	 * 获取详情js
	 */
	 function bookDetail(MD5){
		 var url="<%=request.getContextPath()%>/bookDetail.htm?MD5="+MD5; 
		 window.open(url, "_blank");
	 }

	/*
	 * 分页查询js
	 */
	function pageQuery(pageSize){
		$("#pageNow").val(pageSize);
		$("#f_ation").submit();
	}
	
	/*
	 * 立即购买
	 */
	 function buyNow(md5,title,authors,price,size,covelUrl){
		 $("#md5_hidden").val(md5);
		 $("#title_hidden").val(title);
		 $("#authors_hidden").val(authors);
		 $("#price_hidden").val(price);
		 $("#size_hidden").val(size);
		 $("#covelUrl_hidden").val(covelUrl);
		 $("#tradeType_hidden").val("directBuy");
		 dialogBuy.dialog("open");
	 }
	
	 /*
	 * 添加购物车
	 */
	 function addCart(md5,title,authors,price,size,covelUrl){
		 
		 var orderData={"tradeType":"addCart","md5": md5,"title":title,"authors":authors,"price": Number(price),"size":Number(size),"coverUrl":covelUrl};
		 $.ajax({  
		        type:"POST",  
		        dataType: 'text',  
		        contentType:"application/json",             
		        data:JSON.stringify(orderData),  
		        url:"user/addCart.htm",  
		        error:function(data){  
		            alert("出错了！！:"+data);  
		        },  
		        success:function(data){
		        	var result = eval(data);
		        	//修改购物车上数量
		        	if(result == "203"){
		        		dialogLogin.dialog("open");
		        		$("#email-login").val("");
		        	  	$("#password-register").val("");
		        	  	$("#password-register2").val("");
		        	}else if(result == "successAdd"){
		        		alert("成功添加")
			        	var cartCount = parseInt($("#cart-amount").text())+1;
			        	$("#cart-amount").text(cartCount)
		        	}else{
		        		alert(result);
		        	}
		        }
	        });   
	 }
	
	
	
	
</script>
</head>

<body>
<div id="mydiv"></div>
<div id="buy-form" title="积分充值">
<form>
    <fieldset>
		<label for="alipay-id">支付宝交易号/流水号</label>
      	<input type="text" name="alipay-id" id="alipay-id" value="" class="text ui-widget-content ui-corner-all">
    </fieldset>
     
    <p class="dialog-sub-title">第一步 付款</p>
     
	<div id="dialog-step-1-1">
		<div id="alipay-text">扫一扫二维码，付款<span id="dialog-price-1"></span></div>
     	<div id="alipay-graph">	<img src="<%=request.getContextPath()%>/images/alipay_graph.jpg" alt=""/></div>
     </div>
     <div id="dialog-step-1-2">或者</div>
     <div id="dialog-step-1-3">
     	<div id="transfer-text">转账到支付宝付款<span id="dialog-price-2"></span><br>nilescience@qq.com</div>
     	<div id="alipay-transfer"><img src="<%=request.getContextPath()%>/images/alipay_transfer.png" alt=""/></div>
   	</div>
     
   	<p class="dialog-sub-title">第二步 填写交易号/流水号</p>
   	<div id="dialog-step-2">
		<p>款成功后，请填写支付宝的交易号/流水号，点击确认即可。</p>
   		<p>样本：20160402200040022200140041083666</p>
   	</div>
   <input type="hidden" id="md5_hidden" value=""> 
   <input type="hidden" id="title_hidden" value=""> 
   <input type="hidden" id="authors_hidden" value=""> 
   <input type="hidden" id="size_hidden" value=""> 
   <input type="hidden" id="price_hidden" value=""> 
   <input type="hidden" id="covelUrl_hidden" value=""> 
 	<input type="hidden" id="tradeType_hidden" value=""> 
</form>
</div>

<div id="dialog-register-form" title="注册成为新用户">
  <p class="validate-tips-register">请填写以下信息</p>
  <form>
    <fieldset>
      <label for="email-register">邮箱</label>
      <input type="text" name="email-register" id="email-register" value="用于发货, 请您仔细填写" class="text ui-widget-content ui-corner-all" onfocus="focusEmailLoginInput()" onblur="blurEmailLoginInput()">
      <label for="password-register">密码</label>
      <input type="password" name="password-register" id="password-register" value="" class="text ui-widget-content ui-corner-all">
      <label for="password-register-2">确认密码</label>
      <input type="password" name="password-register-2" id="password-register-2" value="" class="text ui-widget-content ui-corner-all">
     </fieldset>
  </form>
</div>

<div id="dialog-login-form" title="登录 Nile Science">
  <p class="validate-tips-login">请填写以下信息</p>
  <form>
    <fieldset>
      <label for="email-login">邮箱</label>
      <input type="text" name="email-login" id="email-login" value="" class="text ui-widget-content ui-corner-all">
      <label for="password-login">密码</label>
      <input type="password" name="password-login" id="password-login" value="" class="text ui-widget-content ui-corner-all">
      <div class="register-small-button" id="forget-password">忘记密码</div>
	  <div class="register-small-button" id="register-now">立即注册</div>
     </fieldset>
  </form>
</div>

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

<div id="header">
	<span id="head-username"></span><div id="welcome">欢迎到<a href="<%=request.getContextPath()%>/gotoIndex.htm"><img id="head-logo" src="<%=request.getContextPath()%>/images/head-logo.png" alt="Nile Science"></a></div>
	<div id="login" class="header-button">登录</div><div id="register" class="header-button">注册</div><div id="logout" class="header-button">注销</div>
	<div id="user-center">个人中心</div>
	<div id="credits"><span id="credits-amount"></span> 积分</a></div>
	<div id="credits-topup" class="header-button">充值</div>
	<div id="cart">购物车 <b style="color:white" id="cart-amount"></b> 件</div>
	<div id="contact-us"><a>联系我们</a></div>
	<div id="help-me"><a target="_blank" href="./help.htm">帮助中心</a></div>
	<div id="reader-download"><a target="_blank" href="./readerDownload.htm">阅读器下载</a></div>
</div>

<div id="search-header">
	<a href="<%=request.getContextPath()%>/gotoIndex.htm"><img id="search-header-logo" src="<%=request.getContextPath()%>/images/logo-small-on-list-page.png" alt="Nile Science"></img></a>
	<div class="search-box">
		<form id="f_ation" name="f_ation" action="indexSearch.htm" method="post">
		<select class="search-option" id="search-option" name="search-option">
		    <option value="title">电子书标题</option>
			<option value="authors">电子书作者</option>
			<option value="isbn">ISBN 号码</option>
		</select>
		<input type="text" id="search-input" name="search-input" class="search-input" value="search in Nile Science"/>
		<div class="search-button" id="search-button-index">搜索</div>
		</form>
	</div>
</div>

<div id="result-overview">
	<div id="fh" class="n_l">
		<span>以下呈现的是“${searchinput}”的精选结果</span>
	</div>
</div>

<c:forEach var="pojo" varStatus="s" items="${products}">
	<div class="book-item">
	<a  href="javascript:void(0)" onclick="bookDetail('${pojo.md5}')"><div class="book-cover-small" style="background-image:url(<%=request.getContextPath()%>/images/cover-1.jpg);"></div></a>
	<div class="book-info-simple">
		<div class="info-simple-title"><a href="javascript:void(0)" onclick="bookDetail('${pojo.md5}')">${pojo.title}</a></div>
		<div class="info-simple-other-div"><span class="info-simple-left">作者</span><span class="info-simple-right">${pojo.authors}</span></div>
		<div class="info-simple-other-div"><span class="info-simple-left">出版时间</span><span class="info-simple-right">${pojo.year}年</span></div>
		<div class="info-simple-other-div">
			<span class="info-simple-left">电子书格式</span>
			<img src="<%=request.getContextPath()%>/images/pdf_icon.jpg" alt="" class="file-format-icon"/>
			<span class="info-simple-right">PDF</span>
		</div>
		<div class="price-div">
			<div class="price-div-left">
				<div class="origin-price-div"><span class="info-simple-left">价格</span><span class="info-simple-right" style="text-decoration:line-through">¥ ${pojo.price}</span></div>
				<div class="lower-price-div">
					<span class="info-simple-left" style="font-weight:700">促销价</span><span class="info-simple-right" style="font-size:20px;color:rgb(128,15,37);">¥  ${pojo.realPrice}</span> (该书可立即下载)
				</div>
				
			</div>
			<div class="price-div-right">
				<div class="buy-now-button" onclick="buyNow('${pojo.md5}','${pojo.srcTitle}','${pojo.authors}','${pojo.realPrice}','${pojo.size}','${pojo.coverUrl}')">立即购买</div>
				<div class="add-to-cart-button" onclick="addCart('${pojo.md5}','${pojo.srcTitle}','${pojo.authors}','${pojo.realPrice}','${pojo.size}','${pojo.coverUrl}')">加入购物车</div>
			</div>
		</div>
	</div>
	</div>
</c:forEach>


<div id="result-overview" style="margin-top:20px">
	<!-- 分页功能 start -->
	<div align="center">
	<form id="f_ation" name="f_ation" action="indexSearch.htm" method="post">
		<input type="hidden" name="search-option" id="search-option"   value="${searchOption}"/>
		<input type="hidden" name="search-input"  id="search-input"  value="${searchinput}"/>
		<input type="hidden" name="pageNow" id="pageNow"/>
		<font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第
			${page.pageNow} 页</font> <a href="javascript:void(0)" onclick="pageQuery(1)">首页</a>
		<c:choose>
			<c:when test="${page.pageNow - 1 > 0}">
				<a href="javascript:void(0)" onclick="pageQuery(${page.pageNow - 1})">上一页</a>
			</c:when>
			<c:when test="${page.pageNow - 1 <= 0}">
				<a href="javascript:void(0)" onclick="pageQuery(1)">上一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${page.totalPageCount==0}">
				<a href="javascript:void(0)" onclick="pageQuery(${page.pageNow})">下一页</a>
			</c:when>
			<c:when test="${page.pageNow + 1 < page.totalPageCount}">
				<a href="javascript:void(0)" onclick="pageQuery(${page.pageNow + 1})">下一页</a>
			</c:when>
			<c:when test="${page.pageNow + 1 >= page.totalPageCount}">
				<a href="javascript:void(0)" onclick="pageQuery(${page.totalPageCount})">下一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${page.totalPageCount==0}">
				<a href="javascript:void(0)" onclick="pageQuery(${page.pageNow})">尾页</a>
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0)" onclick="pageQuery(${page.totalPageCount})">尾页</a>
			</c:otherwise>
		</c:choose>
	</form>
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