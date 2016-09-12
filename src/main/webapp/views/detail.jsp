<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<meta name="keywords" content="closer, look, biology, microbiology, cell"/>
<meta name="description" content="Ebook Highway -- 拥有庞大的电子书数据库，提供一站式外语电子书服务：高效检索，便捷购买，高速下载。A Closer Look at Biology, Microbiology, and the Cell。">
<title>A Closer Look at Biology, Microbiology, and the Cell</title>
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="bookmark icon" href="images/favicon.ico">

<link rel="stylesheet" href="./css/ui/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="./css/global.css" />
<link rel="stylesheet" type="text/css" href="./css/index.css" />
<link rel="stylesheet" type="text/css" href="./css/detail.css" />

<script src="./js/jquery-1.12.4.js"></script>
<script src="./js/jquery-ui.js"></script>
<script src="./js/footer-margin-control.js"></script>
<script src="./js/account-dialog-control.js"></script>

<script>
	otherHeight = 695;
</script>

</head>

<body>
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

<div class="book-item-detail">
	<div class="book-file-detail">
		<div class="book-cover" style="background-image:url(./images/cover-1.jpg);"></div>
		
		<div class="info-detail-other-div">
			<span class="info-detail-left">电子书格式</span>
			<img src="./images/pdf_icon.jpg" alt="" width="15" height="15">
			<span class="info-detail-right">PDF</span>
			<a target="_blank" href="./reader-download.html"><span class="reader-downloader"> (阅读器下载)</span></a>
		</div>
		<div class="info-detail-other-div"><span class="info-detail-left">电子书大小</span><span class="info-detail-right">${book.size}MB</span></div>
	</div>
	
	<div class="book-info-detail">
		<div class="info-detail-title">${book.title}</div>
		<div class="info-detail-other-div"><span class="info-detail-left">作者</span><span class="info-detail-right">${book.authors}</span></div>
		<div class="info-detail-other-div"><span class="info-detail-left">出版时间</span><span class="info-detail-right">${book.year}年</span></div>
		<div class="info-detail-other-div"><span class="info-detail-left">出版社</span><span class="info-detail-right">${book.publisher}</span></div>
		<div class="info-detail-other-div"><span class="info-detail-left">语言</span><span class="info-detail-right">${book.language}</span></div>
		<div class="info-detail-other-div"><span class="info-detail-left">页数</span><span class="info-detail-right">${book.pages}页</span></div>
		<div class="info-detail-other-div"><span class="info-detail-left">系列</span><span class="info-detail-right">${book.series}</span></div>
		<div class="info-detail-other-div"><span class="info-detail-left">周期</span><span class="info-detail-right">${book.publisher}</span></div>
		<div class="info-detail-other-div"><span class="info-detail-left">城市</span><span class="info-detail-right"></span></div>
		<div class="info-detail-other-div"><span class="info-detail-left">版次</span><span class="info-detail-right">第 ${book.edition}版</span></div>
		<div class="info-detail-other-div"><span class="info-detail-left">ISBN</span><span class="info-detail-right">${book.isbn}</span></div>
		
		<div class="price-div">
			<div class="price-div-left">
				<div class="origin-price-div"><span class="info-detail-left">价格</span><span class="info-detail-right" style="text-decoration:line-through">¥ ${book.price}</span></div>
				<div class="lower-price-div">
					<span class="info-detail-left" style="font-weight:700">促销价</span><span class="info-detail-right" style="font-size:20px;color:rgb(128,15,37);">¥ ${book.realPrice}</span> (该书可立即下载)
				</div>
			</div>
		</div>
		<div class="buy-div">
			<div class="buy-now-button">立即购买</div>
			<div class="add-to-cart-button">加入购物车</div>
			<div id="pay-problem">支付遇到问题 ?</div>
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