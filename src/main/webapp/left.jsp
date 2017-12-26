<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%-- ${pageContext.request.contextPath} --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>left</title>
<link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/admin/js/jquery.js"></script>
<script type="text/javascript"> 
// if (top.location !== self.location) { 
//     top.location=self.location; 
// } 
</script>
<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>模块导航</div>
    
    <dl class="leftmenu">
        <!-- 第三方录入 【开始】-->
         <dd><div class="title"><span><img src="${pageContext.request.contextPath}/admin/images/leftico03.png" /></span>新闻管理</div>
		    <ul class="menuson">
<%-- 		        <li><cite></cite><a href="${pageContext.request.contextPath}/admin/safe/safeManager.json" target="rightFrame">保险管理</a><i></i></li>
		        <li><cite></cite><a href="${pageContext.request.contextPath}/admin/lend/lendManager.json" target="rightFrame">借贷管理</a><i></i></li>
		        <li><cite></cite><a href="${pageContext.request.contextPath}/admin/credit/creditManager.json" target="rightFrame">信用卡管理</a><i></i></li>
		        <li><cite></cite><a href="${pageContext.request.contextPath}/admin/home/homeMessageManager.json" target="rightFrame">首页信息管理</a><i></i></li> --%>
		        <li><cite></cite><a href="${pageContext.request.contextPath}/admin/news/manager.json" target="rightFrame">新闻录入</a><i></i></li>
		    </ul>
    	 </dd>
        <!-- 第三方录入【结束】 -->
        
		<!-- 用户管理【开始】 -->
		<dd>
			<div class="title">
				<span><img src="${pageContext.request.contextPath}/admin/images/leftico01.png" /></span>用户管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="${pageContext.request.contextPath}/admin/userManager.json" target="rightFrame">用户信息查询</a><i></i></li>
			</ul>
		</dd>
		<!-- 用户管理【结束】 -->
		
		<dd style="display: none;">
			<div class="title">
				<span><img src="${pageContext.request.contextPath}/admin/images/leftico02.png" /></span>运营
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="${pageContext.request.contextPath}/cbdc/businessManager.json" target="rightFrame">运营管理</a></li>
			</ul>
		</dd>

		<dd style="display: none;">
			<div class="title">
				<span><img src="${pageContext.request.contextPath}/admin/images/leftico04.png" /></span>订单管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="${pageContext.request.contextPath}/admin/order/orderManager.json" target="rightFrame">借贷查询</a><i></i></li>
			</ul>
		</dd>
	</dl>
</body>
</html>
