<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%-- ${pageContext.request.contextPath} --%>
<%
	String path = request.getContextPath();
	String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	System.out.println(basePath);
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
<script src="${pageContext.servletContext.contextPath}/ueditor/ueditor.config.js"></script>
<script src="${pageContext.servletContext.contextPath}/ueditor/ueditor.all.js"></script>
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
    <form action="${pageContext.servletContext.contextPath}/admin/news/add.json" method="post" enctype="multipart/form-data">
	    <div class="formbody">
	    <div class="formtitle"><span>新闻信息</span></div>
	    <ul class="forminfo">
	    <li><label></label>
		    <c:choose> 
				<c:when test="${not empty model.id}"><input type="hidden" name="id"  value="${model.id}"/></c:when> 
			</c:choose>
	    </li>
	    <li><label>新闻标题</label>
		    <c:choose> 
				<c:when test="${not empty model.title}"><input name="title" type="text" class="dfinput" value="${model.title}"/></c:when> 
	  			<c:otherwise><input name="title" type="text" class="dfinput"/></c:otherwise> 
			</c:choose>
		</li>
	    <li><label>新闻图标</label>
<%-- 		    <c:choose> 
				<c:when test="${!empty model.picLocation}"><input type="file" name="file" value="${model.picLocation}"/></c:when> 
	  			<c:otherwise><input type="file" name="file"/></c:otherwise> 
			</c:choose> --%>
			<input type="file" name="file"/>
	    </li>
	    <li><label>新闻URL地址</label>
	    	<c:choose> 
				<c:when test="${!empty model.newsUrl}"><input name="newsUrl" type="text" class="dfinput" value="${model.newsUrl}"/></c:when> 
	  			<c:otherwise><input name="newsUrl" type="text" class="dfinput"/></c:otherwise> 
			</c:choose>
	    </li> 
	    <li><label>新闻内容</label>
	    	<c:choose> 
				<c:when test="${!empty model.news}"><textarea id="newsEditor" name="news" style="background-color:transparent;">${model.news}</textarea></c:when> 
	  			<c:otherwise><textarea id="newsEditor" name="news" style="background-color:transparent;"></textarea></c:otherwise> 
			</c:choose>
			<script type="text/javascript">
					var editor = new UE.ui.Editor();
					editor.render("newsEditor");
					//1.2.4以后可以使用一下代码实例化编辑器 
					//UE.getEditor('newsEditor')
			</script>
	   </li>
		<li>
	        <label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/>
	        <label>&nbsp;</label><input name="" onclick="javascript:history.go(-1)" type="button" class="btn" value="返回"/>
	    </li>
	    </ul>
	    </div>
    </form>
</body>
</html>