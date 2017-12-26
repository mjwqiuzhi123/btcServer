<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="pageDiv">
  &nbsp;&nbsp;总记录数:${pageView.rowCount}条 |每页显示:${pageView.pageSize}条 | 总页数:${pageView.pageCount}页
						                 &nbsp;&nbsp;
						                  <a  onclick="pageNow('1');">
						                  	首页
						                  </a>
						                  
						                  
						                  <a  onclick="return pageNow('${pageView.pageNow - 1}')">
						                  	上一页
						                  </a>
						                  <c:forEach begin="${pageView.pageindex.startindex}" end="${pageView.pageindex.endindex}" var="key">
												<c:if test="${pageView.pageNow==key}">
													&nbsp;<span class="current" style="color: red;font-size: 20px;"> ${key}</span>
												</c:if>
												<c:if test="${pageView.pageNow!=key}">
													&nbsp;<a  onclick="pageNow('${key}')">${key}</a>
												</c:if>
											</c:forEach>&nbsp;
						               
						                 
						                  <a  onclick="pageNow('${pageView.pageNow + 1}')">
						                  	下一页
						                  </a>
						                
						                
						                  <a  onclick="pageNow('${pageView.pageCount}')">
						                 	尾页
						                  </a>
						                   &nbsp;&nbsp;</div>