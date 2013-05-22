<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="nav">
		<a href="<s:url value="/auditManage/auditList.do"/>" <s:if test="#request.mname=='auditList'">class="current"</s:if>>审核列表</a>
		<s:if test="#session.usersession.priv==1"><a href="<s:url value="/auditManage/initAddTask.do?method=query"/>" <s:if test="#request.mname=='initAddTask'">class="current"</s:if>>提交审核</a></s:if>  
	</div>
