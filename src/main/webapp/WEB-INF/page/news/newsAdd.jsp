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
<link href="${pageContext.request.contextPath}/admin/css/select.css" rel="stylesheet" type="text/css" />
<style  type="text/css">
#thdiv th{text-align: center;} 
#divtr td{text-align: center;}
</style>
<script language="JavaScript" src="${pageContext.request.contextPath}/admin/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/cloud.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/select-ui.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/admin/editor/kindeditor.js"></script> --%>
<!-- 富文本编译器【开始】 -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/admin/kindeditor-4.1.10/themes/default/default.css" />
<script src="${pageContext.servletContext.contextPath}/admin/kindeditor-4.1.10/kindeditor.js"></script>
<script src="${pageContext.servletContext.contextPath}/admin/kindeditor-4.1.10/lang/zh_CN.js"></script>
<!-- 富文本便编译器【结束】 -->
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">平台管理</a></li>
    <li><a href="#">新闻管理</a></li>
    <li><a href="#">新闻录入</a></li>
    </ul>
    </div>
    <form action="${pageContext.servletContext.contextPath}/admin/news/add.json" method="post">
	    <div class="formbody">
	    <div class="formtitle"><span>发现内容</span></div>
	    <ul class="forminfo">
	    <input type="hidden" name="id"  value="${model.id}"/> 
	    <li><label>新闻内容</label><textarea name="news" style="height: 200px; width: 500px; border:solid; border-color:black; background-color:transparent;"></textarea></li>
	     <li>
	         <label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/>
	         <label>&nbsp;</label><input name="" onclick="javascript:history.go(-1)" type="button" class="btn" value="返回"/>
	     </li>
	    </ul>
	    </div>
    </form>
</body>
</html>