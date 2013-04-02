<!-- /T4/WebContent/include/mmsli.jsp -->
<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.wukong.t4.common.Constant" %>
<div id="nav">

		<s:if test="isVip==1">
		<a href="<s:url value="/mmsManage/newMmsTask.do"/>">定制普通任务</a>
		<a href="<s:url value="/mmsManage/newMmsTask.do?isVip=1"/>" <s:if test="#request.mname=='newMmsTask'">class="current"</s:if>>定制VIP任务</a>
		</s:if>
		<s:else>
		<a href="<s:url value="/mmsManage/newMmsTask.do"/>" <s:if test="#request.mname=='newMmsTask'">class="current"</s:if>>定制普通任务</a>
		<a href="<s:url value="/mmsManage/newMmsTask.do?isVip=1"/>">定制VIP任务</a>
		</s:else>
		<a href="<s:url value="/mmsManage/mmsTaskList.do?method=query"/>" <s:if test="#request.mname=='mmsTaskList'">class="current"</s:if>>定制任务管理</a> 
		<a href="<s:url value="/mmsManage/mmsJobList.do?method=query"/>" <s:if test="#request.mname=='mmsJobList'">class="current"</s:if>>调度</a>
		<a href="<s:url value="/mmsManage/testMmsTask.do?method=query"/>" <s:if test="#request.mname=='testMmsTask'">class="current"</s:if>>测试下发</a>
		<a href="<s:url value="/mmsManage/mmsHistoryList.do?method=query"/>" <s:if test="#request.mname=='mmsHistoryList'">class="current"</s:if>>任务历史</a>
		<a href="<s:url value="/optRecord/showOptRecordList.do?taskType=mms"/>" <s:if test="#request.mname=='showOptRecordList'">class="current"</s:if>>操作记录</a> 	
		<a href="<s:url value="/mmsManage/getMmsSendReplyMergeList.do"/>" <s:if test="#request.mname=='getMmsSendReplyMergeList'">class="current"</s:if>>彩信上行回复统计</a> 
		<a href="<s:url value="/mmsManage/getMmsLinkSendReplyMergeList.do"/>" <s:if test="#request.mname=='getMmsLinkSendReplyMergeList'">class="current"</s:if>>彩信链接上行回复统计</a> 

	
	</div>