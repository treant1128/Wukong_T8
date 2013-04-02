<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="nav">
		<s:if test="isVip==1">
		<a href="<s:url value="/smsManage/newSmsTask.do"/>">定制普通任务</a>
		<a href="<s:url value="/smsManage/newSmsTask.do?isVip=1"/>" <s:if test="#request.mname=='newSmsTask'">class="current"</s:if>>定制VIP任务</a>
		</s:if>
		<s:else>
		<a href="<s:url value="/smsManage/newSmsTask.do"/>" <s:if test="#request.mname=='newSmsTask'">class="current"</s:if>>定制普通任务</a>
		<a href="<s:url value="/smsManage/newSmsTask.do?isVip=1"/>">定制VIP任务</a>
		</s:else>
		<a href="<s:url value="/smsManage/smsTaskList.do?method=query"/>" <s:if test="#request.mname=='smsTaskList'">class="current"</s:if>>定制任务管理</a> 
		<a href="<s:url value="/smsManage/smsJobList.do"/>" <s:if test="#request.mname=='smsJobList'">class="current"</s:if>>调度</a> 
		<a href="<s:url value="/smsManage/testSmsTask.do"/>" <s:if test="#request.mname=='testSmsTask'">class="current"</s:if>>测试下发</a> 
		<a href="<s:url value="/smsManage/smsHistoryList.do"/>" <s:if test="#request.mname=='smsHistoryList'">class="current"</s:if>>任务历史</a>
		<a href="<s:url value="/smsManage/responseRateList.do?method=query"/>" <s:if test="#request.mname=='responseRateList'">class="current"</s:if>>下行回复率统计</a> 
		<a href="<s:url value="/smsManage/showInterfaceData.do?method=query"/>" <s:if test="#request.mname=='showInterfaceData'">class="current"</s:if>>接口参数</a> 
		<a href="<s:url value="/optRecord/showOptRecordList.do?taskType=sms"/>" <s:if test="#request.mname=='showOptRecordList'">class="current"</s:if>>操作记录</a> 
		<a href="<s:url value="/smsManage/autoImportNumber.do?actionType=init"/>" <s:if test="#request.mname=='autoImportNumber'">class="current"</s:if>>自动导出号码</a> 
		<a href="<s:url value="/smsManage/autoImportNumberList.do"/>" <s:if test="#request.mname=='autoImportNumberList'">class="current"</s:if>>查看自动导出</a> 
		
		 <a href="<s:url value="/smsManage/inforecSmsTask.do"/>" <s:if test="isVip==0&&#request.mname=='inforecSmsTask'">class="current"</s:if>>定制类精细化任务</a> 
		 <a href="<s:url value="/smsManage/inforecSmsTask.do?isVip=1"/>" <s:if test="isVip==1&&#request.mname=='inforecSmsTask'">class="current"</s:if>>定制类精细化VIP任务</a>
		 <a href="<s:url value="/smsManage/upload.do?actionType=init"/>" <s:if test="#request.mname=='upload'">class="current"</s:if>>上传附件</a>  
		  <a href="<s:url value="/smsManage/previewTasks.do"/>" <s:if test="#request.mname=='previewTasks'">class="current"</s:if>>上传附件</a>  
	</div>