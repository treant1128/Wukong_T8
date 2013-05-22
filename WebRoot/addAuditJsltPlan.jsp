<%@page language="java" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	request.setCharacterEncoding("GBK"); 
    response.setCharacterEncoding("GBK");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=GBK">
<title>唔箜后台</title>
<%@include file="/include/lib.jsp"%>
<script src="<s:url value="/js/plugins/jquery.easyInsert.js"/>" type="text/javascript"></script>
<style type="text/css">
.mytb{margin-top: 3px;z-index: 10000;}
/* 列表table */
table.listTable{width:100%;border:1px solid black;margin-top: 5px;border-top-width: 0px; }
table.listTable td{ padding:5px 10px 0 15px; text-align:left; border-top:1px solid black; background:#FFFFFF;border-left-width: 0px;border-right-width: 0px;}

</style>
<script type="text/javascript">
	
	$(function() {
		var result='${result}';
		if(result!=""&&result=="1"){
			//jAlert('success', '操作已成功!', '操作提示');
			
			$("#smsTaskForm").attr("action", "<%=request.getContextPath()%>/auditManage/smsTaskList.do?method=query");
			$("#smsTaskForm").submit();
		}
		$.formValidator.initConfig({formid:"smsTaskForm",onerror:function(msg){jAlert('error', msg, '错误提示！');}});
		$("#sendTitle").formValidator({empty:false,onshow:"下发语不能为空",onempty:"下发语不能为空"}).inputValidator({min:1,onerror:"下发语不能为空"}).regexValidator({regexp:"^[\\S]\.*$",onerror:"下发语不正确"});;
		$("#sendOUrl").formValidator({onshow:"URL必须以 http:// 或者 https:// 开始 "}).regexValidator({regexp:"^\(http|https):\/\/(\\S)+$",onerror:"url不正确"});
		
		$("#method1").click( function () { 
			   $("#purl").show();
			   $("#purl_bt").show();
			   $("#sendOUrl").val("");
			   $("#sendOUrl").attr("disabled",false); 
			   $("#sendOUrl").formValidator({onshow:"URL必须以 http:// 或者 https:// 开始 "}).regexValidator({regexp:"^\(http|https):\/\/(\\S)+$",onerror:"url不正确"});
		});
		$("#method0").click( function () { 
			  // $("#purl").hide();
			   //$("#purl_bt").hide();
			 //  $("#sendOUrl").val("http://wukong.com");
			   $("#sendOUrl").formValidator({onshow:"URL必须以 http:// 或者 https:// 开始 "}).regexValidator({regexp:"^$|^\(http|https):\/\/(\\S)+$",onerror:"url不正确"});
			   //$("#sendOUrl").attr("disabled",true); 
		});
		
		$("#itType").click( function () { 
			   $("#itDiv").toggle(1000);
		});
		
//		$("#filePath").change(function(){
//			$("#numDiv").html("");
//			var fp=$("#filePath").val();
//			if(typeof(fp) == "undefined" || $.trim(fp)=="")
//				return;
//			$.getJSON(
//					'<s:url value="/auditManage/getFileList.do"/>', 
//					{ "filePath":fp},
//					function(data){
//						if(data==null)
//							return;
//						var numHtm="";
//						$.each(data,function(idx,item){ 
//							numHtm = numHtm + "<span style='width:250px;display:inline-block;'><input name='numFiles' value='"+item.name+","+item.linenum+"' type='checkbox'>"+item.name+"("+item.linenum+")</span>";
//						});
//						$("#numDiv").html(numHtm);			
//					}
//				);
//		});
		
		$("#taskBtn").click(function(){
			$("#taskDiv").html("");
			var btd = $("#btDate").val();
			if(typeof(btd) == "undefined" || $.trim(btd)=="")
				return;
			$.getJSON(
					'<s:url value="/auditManage/getDRTaskList.do"/>', 
					{ "btDate":btd },
					function(data){
						if(data==null)
							return;
						zhankai();
						var taskHtm="<table class='listTable' cellSpacing='0' cellPadding='0'><tr><td>选择</td><td>标题</td><td>号码包</td><td>描述</td></tr>";
						$.each(data,function(idx,item){ 
							taskHtm = taskHtm + "<tr><td><input type='checkbox' value='"+item.excfile+"' name='qcFiles' checked='checked'/></td><td>"+item.sendTitle+"</td><td>"+item.srcfile+"</td><td>"+item.content+"</td></tr>";
						});
						taskHtm=taskHtm+"</table>";
						$("#taskDiv").html(taskHtm);			
					}
				);
		});
		
		
		//$("#bnShouqi").click(function(){
		//	var bnShouqi = document.getElementById("#div_shouqi");
		//	alert(bnShouqi.innerHTML);
		//	bnShowqi.disabled="true";
		//	alert("11");
		//}
		//);
 		
 		$("#demo2").easyinsert({
 			name: ["intervlTimes"],
 			type: ["wk"]
 		});
		
	});
	
	
	function checkSetTime(divId,flag){
	    if(1 == flag){
	        document.getElementById(divId).style.display='block';
	        $("#sendTime").attr("disabled",false); 
	    }else{
	        document.getElementById(divId).style.display='none';
	        $("#sendTime").attr("disabled",true); 
	    }

	}
	
	function shouqi(){
		//$("#taskDiv").hide();
		document.getElementById("taskDiv").style.display="none";
	}
	function zhankai(){
		document.getElementById("taskDiv").style.display="block";
	}
</script>
</head>

<body onunload="myClose()">
	
	<br>
	<s:form action="auditAction" id="smsTaskForm" name="smsTaskForm" method="post">
		<span id="infols"></span>
		<table width="100%" class="infoForm">
			<tr>
				<td class="tdL">选择文件：</td>
				<td class="tdR">
					<select name="filePath" id="filePath">
				<!--
				 		<option></option>
					    <s:iterator value="directoryList" id="dls" >
							<option value="<s:property value="#dls"/>"><s:property value="#dls"/></option>
						</s:iterator>  
				-->       
					</select> 
					<span style="margin-left:3px;color:#FF7F00;">(选择文件夹)</span>
					<div id="numDiv"></div>
					<div id="numFileTip" style="width:250px;"></div>
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdM">&nbsp;</td>
			</tr>
			<tr>
				<td class="tdL">去重任务：</td>
				<td class="tdR">
					 <input type="text" name="btDate" id="btDate" value='<s:date name="btDate" format="yyyy-MM-dd"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>&nbsp;&nbsp;
					 <input type="button" value="查询任务" id="taskBtn"/><a href="#" id="bnShouqi" onclick="shouqi()">收起</a>&nbsp;&nbsp;&nbsp;<a href="#" id="bnShouqi" onclick="zhankai()">展开</a><font color="red">（*注：不点查询任务不与其他任务去重）</font>
					 <span style="margin-left:3px;color:#FF7F00;">(选择日期查询任务)</span>
					 <div id="taskDiv"></div>
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdM">&nbsp;</td>
			</tr>
			<tr>
				<td class="tdL">下发时间：</td>
				<td class="tdR">
					<input type="radio" name="smsTask.sendTimeType" id="send_status0" value="0" onclick="javascript:checkSetTime('timediv',0);" checked="checked"/>立即下发&nbsp;&nbsp;
					<input type="radio" name="smsTask.sendTimeType" id="send_status1" value="1" onclick="javascript:checkSetTime('timediv',1);"/>定时下发&nbsp;&nbsp;
					<input type="radio" name="smsTask.sendTimeType" id="send_status2" value="2" onclick="javascript:checkSetTime('timediv',0);"/>稍后指定&nbsp;&nbsp;
						        <div style="display:none;padding:6px 0 2px 20px;" id="timediv">
								        开始时间：<input type="text" name="smsTask.sendTime" id="sendTime" value="<s:date name="btDate" format="yyyy-MM-dd HH:mm:00"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',minDate:'<s:date name="btDate" format="yyyy-MM-dd HH:mm:00"/>',isShowClear:false,readOnly:true})"/> 
					        	</div>
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdM"></td>
			</tr>
			<tr>
				<td class="tdL">方式：</td>
				<td class="tdR">	
					<label style="width:100px;display:inline-block;"><input type="radio" name="smsTask.method" id="method0" value="0"/>短信</label>
					            <input type="radio" name="smsTask.method" id="method1" value="1" checked="checked" />push
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdM">&nbsp;</td>
			</tr>
			<tr>
				<td class="tdL">下发语：</td>
				<td class="tdR">
					<input style="width: 400px" name="send_title" id="sendTitle" maxlength="100" type="text"/>
					        	<div id="sendTitleTip"></div>
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdM">&nbsp;</td>
			</tr>
			<tr id="purl">
				<td class="tdL">下发URL：</td>
				<td class="tdR">
 					<input style="width: 400px" name="send_o_url" id="sendOUrl" value="" type="text"/>
					<div id="sendOUrlTip"></div>
				</td>
			</tr>
			<tr id="purl_bt">
				<td class="tdL">&nbsp;</td>
				<td class="tdM"></td>
			</tr>
			
			<tr>
				<td class="tdL">描述：</td>
				<td class="tdR">
					<input style="width: 400px" name="content" value="" type="text"/>
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdM"></td>
			</tr>
			<tr>
				<td class="tdL">优先级：</td>
				<td class="tdR">
					<select name="smsTask.priority">
					                <option value="0">0</option>
					                <option value="1">1</option>
					                <option value="2">2</option>
					                <option value="3">3</option>
					                <option value="4">4</option>
					                <option value="5">5</option>
					                <option value="6">6</option>
					                <option value="7">7</option>
					                <option value="8">8</option>
					                <option value="9">9</option>
					            </select>
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdM">&nbsp;</td>
			</tr>
			<s:if test="isVip==1">
			<s:hidden name="smsTask.vip" value="1"></s:hidden>
			<tr>
				<td class="tdL">白名单过滤：</td>
				<td class="tdR">
					 <input type="checkbox" name="smsTask.blackFilter" id="blackFilter" value="1" checked="checked" />
					            <span style="margin-left:6px;color:#FF7F00;">普通下发默认进行白名单过滤，不经过白名单过滤存在业务风险，请谨慎使用！</span>
				</td>
			</tr>
			</s:if>		
			<tr>
				<td class="tdL"></td>
				<td class="tdR">
				</td>
			</tr>
			<tr>
				<td class="tdL">是否保留长标题：</td>
				<td class="tdR">
					<input type="checkbox" name="smsTask.longTitle" id="longTitle" value="1" />
					            <span style="margin-left:6px;color:#FF7F00;">勾选下发时将不对标题截断，否则下发标题会根据一条短信的长度适合截断!</span>
				</td>
			</tr>			
			<tr>
				<td class="tdL"></td>
				<td class="tdR">
				</td>
			</tr>
			<tr>
				<td class="tdL">跳出31597：</td>
				<td class="tdR">
					 <input type="checkbox" name="smsTask.jump" id="jump" value="1"/>
				</td>
			</tr>			
			<tr>
				<td class="tdL"></td>
				<td class="tdR">
				</td>
			</tr>
			<tr>
				<td class="tdL">去重规则：</td>
				<td class="tdR">
					<input type="radio" name="smsTask.qcType" id="qcType7" value="7" checked="checked"/>完全去重&nbsp;&nbsp;
					<input type="radio" name="smsTask.qcType" id="qcType6" value="6" />仅和预置列表去重&nbsp;&nbsp;
					<input type="radio" name="smsTask.qcType" id="qcType5" value="5" />仅和已发去重&nbsp;&nbsp;
					<input type="radio" name="smsTask.qcType" id="qcType0" value="0" />不去重&nbsp;&nbsp;
				</td>
			</tr>			
			<tr>
				<td class="tdL"></td>
				<td class="tdR">
				</td>
			</tr>
			<tr>
				<td class="tdL">下发时间段：</td>
				<td class="tdR">
				 <input type="checkbox" name="itType" id="itType" value="1"/>
				 <span style="margin-left:6px;color:#FF7F00;">勾选自定义下发时间段，否则采用默认时间段!</span>
				<div id="itDiv" style="display:none; ">
					<ul id="demo2"></ul>
					<a href="#">+ 添加</a>
				</div>
				</td>
			</tr>			
			<tr>
				<td class="tdL"></td>
				<td class="tdR">
				</td>
			</tr>
			<tr>
				<td class="tdL"></td>
				<td class="tdR">
					<input type="hidden" value="<s:property value="isVip"/>" name="isVip"/>
					<input type="button" name="button_submit" id="button_submit" value="提交"/>
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdR">&nbsp;</td>
			</tr>
		</table>
	</s:form>
	
	<script language="javascript">
		function myClose(){  //关闭本窗口时调用
			window.dialogArguments.btnQueryHandler();  //关闭时父窗口也跟着关闭
		}
	
	</script>
	
	</body>
	
	<script type="text/javascript">
		$(document).ready(function(){
			window.onmessage=function(event){
				var message=event.data;
				var title=$("#sendTitle");
				var url=$("#sendOUrl");
					title.val(message.title);
					url.val(message.url);
			}
			
			var g_dirs=[];
			var templete="<option></option>";
			$.get('http://122.192.35.95:8000/getDir', function(data){
				g_dirs=eval(data);
				for(var i=1;i<g_dirs.length;i++){
					templete+="<option value="+g_dirs[i]+">"+g_dirs[i]+"</option>";
				}
				jQuery("#filePath").empty();
				jQuery("#filePath").append(templete);
			});  //End of Get

			$("#filePath").change(function(){
				$("#numDiv").html("");
				var fp=$("#filePath").val();
				
				$.post("http://122.192.35.95:8000/getFiles", 
						{"dirName":fp},
						function(data){

							if(data==null||fp.length==0){
								return;
							}else{
								var numHtml="";
								$.each(data, function(idx, item){
									numHtml+="<span style='width:150px;display:inline-block;'><input name='numFiles' value='"
									+item.fileName
									+"' type='checkbox'>"
									+item.fileName
									+"("
									+"*"
									+")</span>";
								});
								$("#numDiv").html(numHtml);
							}  //End of If
						});  //End of Post
			});  //End of Change


		$("#button_submit").click(function(){
					if(!checkNumF())
					{    alert('请选择号码包！');
				//		jAlert('error', '请选择号码包！', '错误提示！');
						return;
					}
					if(!checkIt())
					{    alert('请正确填写下发时间段！');
				//		jAlert('error', '请正确填写下发时间段！', '错误提示！');
						return;
					}
					$("#smsTaskForm").submit();
				});


		function checkNumF(){
			var flag = false;
			var nf = $("input[name=numFiles]:checkbox");
			if(typeof(nf) == "undefined"||nf.length==0){
				return flag;
			}
			
			$("input[name=numFiles]:checkbox").each(function () {
				if ($(this)[0].checked==true) { 
					flag=true;				
				} 
			});
			return flag;
		}
	
		function checkIt(){
			var flag = true;
			if($("input[name=itType]:checkbox")[0].checked==true){  alert("已勾选时间");
				$("input[name=intervlTimes]:text").each(function () { 
					if ($(this)[0].value=="") { 
						flag=false;				
					} 
				});
			}
			return flag;
		}


		});  //End of Ready
			
	</script>
	
</html>
