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
function safeAdd(){

// alert("${pageContext.request.contextPath}/lend/lendAddPage.json");
window.location.href = "${pageContext.request.contextPath}/admin/safe/safeAddPage.json";
}
</script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">用户管理</a></li>
    <li><a href="#">用户列表展示</a></li>
    </ul>
    </div>
    <form action="${pageContext.request.contextPath}/admin/userManager.json" method="post"  id="fenye" name="fenye">
    <div class="rightinfo">
    <div class="tools">
    <ul class="seachform">
    <li><label>用户编号</label><input name="useridentifier" type="text" value="${userModel.useridentifier}" class="scinput"></li>
    <li><label>手机号</label><input name="phone" type="text" value="${userModel.phone}" class="scinput"></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="scbtn" value="用户查询"></li>
    </ul>
    </div>
    <table class="imgtable">
    
    <thead>
    <tr id="thdiv">
    <th>用户编号</th>
    <th>用户手机号</th>
    <th>用户登录时间</th>
    <th>用户最后一次登录成功时间</th>
    <th>用户最后一次登录失败时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${userList}">
	    <tr id="divtr" style="height: 35px;">
		    <td>${user.useridentifier}</td>
		    <td>${user.phone}</td>
		    <td>
			     <c:if test="${not empty user.signuptime}">
			       <fmt:formatDate value="${user.signuptime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			     </c:if>
		     </td>
		    </td>
		    <td>
			     <c:if test="${not empty user.lastsuccesssignintime}">
			    	 <fmt:formatDate value="${user.lastsuccesssignintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			     </c:if>
		     </td>
		     <td>
			     <c:if test="${not empty user.lastfailedsignintime}">
			    	 <fmt:formatDate value="${user.lastfailedsignintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			     </c:if>
		     </td>
	    </tr>
    </c:forEach>
    </tbody>
    </table>
    <div class="pagin">
    	<div class="message">
    	<%-- <jsp:include page="../../common/pager/page/jsp/webfenye3.jsp"/> --%>
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
<%-- <jsp:include page="../../common/pager/page/jsp/common-js.jsp"/>
<jsp:include page="../../common/pager/page/jsp/common-css.jsp"/> --%>
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