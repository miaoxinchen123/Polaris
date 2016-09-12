<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<meta name="keywords" content="英文电子书,电子书,订单,用户中心"/>
<meta name="description" content="Ebook Highway -- 拥有庞大的电子书数据库，提供一站式外语电子书服务：高效检索，便捷购买，高速下载。">
<title>我的订单 Nile Science 英文电子书文献检索平台</title>
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="bookmark icon" href="images/favicon.ico">

<link rel="stylesheet" href="./css/ui/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="./css/global.css" />
<link rel="stylesheet" type="text/css" href="./css/index.css" />
<link rel="stylesheet" type="text/css" href="./css/order.css" />

<script src="./js/jquery-1.12.4.js"></script>
<script src="./js/jquery-ui.js"></script>
<script src="./js/account-dialog-control.js"></script>
<script src="./js/order.js"></script>
<script src="./js/footer-margin-control.js"></script>
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

<div id="order-wrapper">
	<div id="user-center-navigator">
		<div class="user-center-head"><span>个人中心</span></div>
		<div id="cart-menu" class="user-center-menu "><a>购物车 <b style="color:rgb(128,15,37); text-decoration:underline;" id="menu-cartCnt"></b></a></div>
		<div id="order-menu" class="user-center-menu user-center-menu-selected"><a>我的订单</a></div>
		<div id="credits-menu" class="user-center-menu"><a>我的积分</a></div>
		<div id="profile-menu" class="user-center-menu"><a>个人资料</a></div>
	</div>
	
	
	<c:choose>
			<c:when  test="${ not empty isDetail }">
				<c:forEach var="orderDetail" varStatus="s" items="${orderDetails}">
				<div class="cart-item">
					<div class="cart-book-cover" style="background-image: url(&quot;./images/covers/932C32FD8BD04F4B826063FEDE61222E.jpg&quot;);"></div><div class="cart-book-info">
					<div class="cart-title">${orderDetail.title}</div><span class="cart-authors">${orderDetail.authors}</span></div>
					<div class="cart-file-info"><img alt="" class="file-format-icon" src="./images/pdf_icon.jpg">
					<span class="info-simple-right">pdf文件</span><span class="reader-downloader-small"> 
					</span><br><span>文件大小为 ${orderDetail.size}KB</span></div>
					<div class="cart-price-info">¥ ${orderDetail.price}</div>
					<div class="cart-item-operation">
						<div class="cart-item-operation-wrapper">
							<p onclick="operateThis('orderDetail','${orderDetail.md5}')">详细信息</p>
						</div>
					</div>
				</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div id="order-list">
					<input type="hidden" id="size" value="${size}"/>
					<div id="order-head">
						<div ><input type="checkbox" id="checkbox-all-div"></input></div>
						<span>全选</span>
						<span id="order-remove-span" class="order-head-operation">删除</span>
					</div>
					<c:choose>
					  	<c:when  test="${ not empty emptyMessage }">
					  		<div class="cart-item">${emptyMessage}</div>
						</c:when>
						<c:otherwise>
							<c:forEach var="order" varStatus="s" items="${orders}">
							<div class="order-item" id="order-item">
								<div class="checkbox-item-div"><input name="orderCheckbox" type="checkbox"></div>
								<div class="order-book-info">
								
								<span>购买于${order.createTime}</span><br>订单编号为 ${order.orderId}</div>
								<div class="order-file-info"><div class="order-price-info">¥ ${order.price}</div>
									<div class="order-item-operation"><div class="order-item-operation-wrapper">
									<p  onclick="operateThis('delete','${order.orderId}')">删除</p>
									<p  onclick="operateThis('detail','${order.orderId}')">详细信息</p>
									<p  onclick="operateThis('reSend','${order.orderId}')">申请重新发货</p></div>
									</div>
								</div>
							</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</c:otherwise>
	
	</c:choose>
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