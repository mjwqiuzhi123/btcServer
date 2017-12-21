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
<title>欢迎登录后台管理系统</title>
<link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/admin/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 
<script language="javaScript">
	$(document).ready(function(){  
		 $(function(){  
		   $('input:reset').click(function(){  
		     $('.input').val("");  
		    });  
		  });  
		  }); 
</script>
</head>

<body style="background-color:#1c77ac; background-image:url(${pageContext.request.contextPath}/admin/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>


	<div class="logintop">
		<span>欢迎登录btc系统管理平台</span>
		<ul>
			<li><a href="#">回首页</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>

	<div class="loginbody">
    
    <span class="systemlogo"></span> 
    <div><center><c:if test="${not empty error}"><span style="color: red;font-size: 20px;font-weight: bold;">${error}</span></c:if></center></div>
    <form action="${pageContext.request.contextPath}/admin/loginOn.json" method="post">
	    <div class="loginbox">
		    <ul>
			    <li><input name="adminName" id="adminName" type="text" class="loginuser" value="" onclick="JavaScript:this.value=''"/></li>
			    <li><input name="adminPwd" id="adminPwd" type="password" class="loginpwd" value="" onclick="JavaScript:this.value=''"/></li>
			    <li>
			       <input name="" type="submit" class="loginbtn" value="登录"/>
			       <input name="" id="reset" type="reset" class="loginbtn" value="重置" style="margin-left: 10%;"/>
			    </li>
		    </ul>
	    </div>
    </form>
    
    </div>
    <div class="loginbm">版权所有  2017  <a href="#">btc</a> </div>
</body>

</html>