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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/editor/kindeditor.js"></script>
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
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
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
<script type="text/javascript">
function newsAdd(){

// alert("${pageContext.request.contextPath}/lend/lendAddPage.json");
window.location.href = "${pageContext.request.contextPath}/admin/news/addPage.json";
}
</script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">新闻管理</a></li>
    <li><a href="#">新闻列表</a></li>
    </ul>
    </div>
    <form action="${pageContext.request.contextPath}/admin/credit/creditManager.json" method="post"  id="fenye" name="fenye">
    <div class="rightinfo">
    <div class="tools">
    <ul class="seachform">
    <li><label>&nbsp;</label><input name="" type="button" class="scbtn" value="新闻添加" onclick="newsAdd()"/></li>
  	</ul> 
    </div>
    <div>
    	<c:if test="${flag eq 1}">alert("更新成功")</c:if>
    	<c:if test="${flag eq 2}">alert("删除成功")</c:if>
    </div>
    <table class="imgtable">
    
    <thead>
	    <tr id="thdiv">
		    <th>新闻内容</th>
		    <th>更新操作</th>
		    <th>删除操作</th>
	    </tr>
    </thead>
    <tbody>
    <c:forEach var="news" items="${newsList}">
	    <tr id="divtr">
		   <td style="height: 50px; width: 80%">${news.news}</td>
		   <td style="height: 50px"><a href="${pageContext.request.contextPath}/admin/news/update.json?id=${news.id}" class="tablelink">更新</a></td>
		   <td style="height: 50px"><a href="${pageContext.request.contextPath}/admin/news/delete.json?id=${news.id}" class="tablelink">删除</a></td>
	    </tr>
    </c:forEach>
    </tbody>
    </table>
    <div class="pagin">
    	<div class="message">
    	<jsp:include page="../../common/pager/page/jsp/webfenye3.jsp"/>
    	</div>
    </div>
  <div>
  </div>
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="${pageContext.request.contextPath}/admin/images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
  </form>  
    
    
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="${pageContext.request.contextPath}/admin/images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
<jsp:include page="../../common/pager/page/jsp/common-js.jsp"/>
<jsp:include page="../../common/pager/page/jsp/common-css.jsp"/>
<script type="text/javascript">
	$('.imgtable tbody tr:odd').addClass('odd');
	</script>
		<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>