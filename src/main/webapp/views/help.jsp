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
<title>Nile Science 英文电子书文献检索平台 帮助中心</title>
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="bookmark icon" href="images/favicon.ico">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ui/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/global.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/help.css" />

<script src="<%=request.getContextPath()%>/js/jquery-1.12.4.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/js/account-dialog-control.js"></script>

<script>
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
	<span id="head-username"></span><div id="welcome">欢迎到<a href="<%=request.getContextPath()%>/gotoIndex.htm"><img id="head-logo" src="<%=request.getContextPath()%>/images/head-logo.png" alt="Nile Science"></a></div>
	<div id="login" class="header-button">登录</div><div id="register" class="header-button">注册</div><div id="logout" class="header-button">注销</div>
	<div id="user-center">个人中心</div>
	<div id="credits"><span id="credits-amount"></span> 积分</div>
	<div id="credits-topup" class="header-button">充值</div>
	<div id="cart">购物车 <b style="color:white" id="cart-amount"></b> 件</div>
	<div id="contact-us"><a>联系我们</a></div>
	<div id="help-me"><a target="_blank" href="./help.htm">帮助中心</a></div>
	<div id="reader-download"><a target="_blank" href="./readerDownload.htm">阅读器下载</a></div>
</div>
	
<div id="page-center">
	<h2>Nile Science 提供什么服务？</h2>
	<p>Nile Science 提供英文电子书、英文文献的付费下载服务。</p>
	
	<h2>电子书及文献是什么格式？</h2>
	<p>电子书的格式绝大部分为".pdf"，也包含少量其他格式，如：".epub"，".mobi" 等。</p>
	
	<h2>电子书及文献如何打开？</h2>
	<p>我们提供<a href="./readerDownload.htm"> 阅读器下载 </a>，您可以根据电子书及文献的格式选择下载相应的软件。</p>
	
	<h2>如何付款？</h2>
	<p>我们支持支付宝付款。</p>
	
	<h2>如何收货？</h2>
	<p>在您完成购买之后，我们会通过邮件发送至您的邮箱。您的账号即为您的收货邮箱。</p>
	<p>为减少可能的网络问题，我们强烈建议您在<a id="help-profile"> 个人资料 </a>中，添加备用邮箱。我们会将您购买的电子书、文献同时发送至您的账号邮箱及备用邮箱中。</p>

	<h2>我已经付款，但网站仍然显示未付款怎么办？</h2>
	<p>您在付款之后，需要填写对应的支付宝流水号，才能完成支付。一般在付款后，需要3-5分钟，才会更新付款状态。</p>
	<p>如果在3-5分钟后，网站仍然显示未付款，可能是由于（1）您输入的订单号有误，请您仔细核对；（2）您支付的金额不足，我们会将您支付的款项退回到您的账户积分中</p>
	
	<h2>如果付款后没有收到邮件怎么办？</h2>
	<p>在您付款之后，我们需要代购、下载、发货，因此需要一定时间。我们保证在您下单之后的12小时内发送到您的邮箱中（也就是您的账号）。</p>
	<p>如您在<a  id="help-profile1"> 个人资料 </a>中添加了备用邮箱，则会将邮件同时发送至您的备用邮箱中。</p>
	<p>如果您在12小时后仍未成功收到邮件，可申请重新发货。我们的工作人员会第一时间予以反馈。如果我们无法发货，会将等值的积分返还到您的账户中。积分可以在购买时抵用现金（10积分 = 1元）。</p>
	
	<h2>积分有何作用？</h2>
	<p>积分在购买时可以直接抵用现金，购买时10积分 = 1元。</p>
	
	<h2>积分如何获得？</h2>
	<p>您可以使用<a  id="help-credits"> 积分充值 </a>功能，对账户进行充值。充值1000积分（100元）即送200积分，更多优惠详见<a href="<%=request.getContextPath()%>/topup.html"> 积分充值 </a>功能。</p>
	
	<h2>是否提供纸质版产品？</h2>
	<p>非常抱歉，我们暂时不提供纸质版产品。</p>
	
	<h2>是否提供发票？</h2>
	<p>非常抱歉，我们暂时无法提供发票。</p>
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