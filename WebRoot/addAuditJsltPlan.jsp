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
<title>�����̨</title>
<%@include file="/include/lib.jsp"%>
<script src="<s:url value="/js/plugins/jquery.easyInsert.js"/>" type="text/javascript"></script>
<style type="text/css">
.mytb{margin-top: 3px;z-index: 10000;}
/* �б�table */
table.listTable{width:100%;border:1px solid black;margin-top: 5px;border-top-width: 0px; }
table.listTable td{ padding:5px 10px 0 15px; text-align:left; border-top:1px solid black; background:#FFFFFF;border-left-width: 0px;border-right-width: 0px;}

</style>
<script type="text/javascript">
	
	$(function() {
		var result='${result}';
		if(result!=""&&result=="1"){
			//jAlert('success', '�����ѳɹ�!', '������ʾ');
			
			$("#smsTaskForm").attr("action", "<%=request.getContextPath()%>/auditManage/smsTaskList.do?method=query");
			$("#smsTaskForm").submit();
		}
		$.formValidator.initConfig({formid:"smsTaskForm",onerror:function(msg){jAlert('error', msg, '������ʾ��');}});
		$("#sendTitle").formValidator({empty:false,onshow:"�·��ﲻ��Ϊ��",onempty:"�·��ﲻ��Ϊ��"}).inputValidator({min:1,onerror:"�·��ﲻ��Ϊ��"}).regexValidator({regexp:"^[\\S]\.*$",onerror:"�·��ﲻ��ȷ"});;
		$("#sendOUrl").formValidator({onshow:"URL������ http:// ���� https:// ��ʼ "}).regexValidator({regexp:"^\(http|https):\/\/(\\S)+$",onerror:"url����ȷ"});
		
		$("#method1").click( function () { 
			   $("#purl").show();
			   $("#purl_bt").show();
			   $("#sendOUrl").val("");
			   $("#sendOUrl").attr("disabled",false); 
			   $("#sendOUrl").formValidator({onshow:"URL������ http:// ���� https:// ��ʼ "}).regexValidator({regexp:"^\(http|https):\/\/(\\S)+$",onerror:"url����ȷ"});
		});
		$("#method0").click( function () { 
			  // $("#purl").hide();
			   //$("#purl_bt").hide();
			 //  $("#sendOUrl").val("http://wukong.com");
			   $("#sendOUrl").formValidator({onshow:"URL������ http:// ���� https:// ��ʼ "}).regexValidator({regexp:"^$|^\(http|https):\/\/(\\S)+$",onerror:"url����ȷ"});
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
						var taskHtm="<table class='listTable' cellSpacing='0' cellPadding='0'><tr><td>ѡ��</td><td>����</td><td>�����</td><td>����</td></tr>";
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
				<td class="tdL">ѡ���ļ���</td>
				<td class="tdR">
					<select name="filePath" id="filePath">
				<!--
				 		<option></option>
					    <s:iterator value="directoryList" id="dls" >
							<option value="<s:property value="#dls"/>"><s:property value="#dls"/></option>
						</s:iterator>  
				-->       
					</select> 
					<span style="margin-left:3px;color:#FF7F00;">(ѡ���ļ���)</span>
					<div id="numDiv"></div>
					<div id="numFileTip" style="width:250px;"></div>
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdM">&nbsp;</td>
			</tr>
			<tr>
				<td class="tdL">ȥ������</td>
				<td class="tdR">
					 <input type="text" name="btDate" id="btDate" value='<s:date name="btDate" format="yyyy-MM-dd"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>&nbsp;&nbsp;
					 <input type="button" value="��ѯ����" id="taskBtn"/><a href="#" id="bnShouqi" onclick="shouqi()">����</a>&nbsp;&nbsp;&nbsp;<a href="#" id="bnShouqi" onclick="zhankai()">չ��</a><font color="red">��*ע�������ѯ��������������ȥ�أ�</font>
					 <span style="margin-left:3px;color:#FF7F00;">(ѡ�����ڲ�ѯ����)</span>
					 <div id="taskDiv"></div>
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdM">&nbsp;</td>
			</tr>
			<tr>
				<td class="tdL">�·�ʱ�䣺</td>
				<td class="tdR">
					<input type="radio" name="smsTask.sendTimeType" id="send_status0" value="0" onclick="javascript:checkSetTime('timediv',0);" checked="checked"/>�����·�&nbsp;&nbsp;
					<input type="radio" name="smsTask.sendTimeType" id="send_status1" value="1" onclick="javascript:checkSetTime('timediv',1);"/>��ʱ�·�&nbsp;&nbsp;
					<input type="radio" name="smsTask.sendTimeType" id="send_status2" value="2" onclick="javascript:checkSetTime('timediv',0);"/>�Ժ�ָ��&nbsp;&nbsp;
						        <div style="display:none;padding:6px 0 2px 20px;" id="timediv">
								        ��ʼʱ�䣺<input type="text" name="smsTask.sendTime" id="sendTime" value="<s:date name="btDate" format="yyyy-MM-dd HH:mm:00"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',minDate:'<s:date name="btDate" format="yyyy-MM-dd HH:mm:00"/>',isShowClear:false,readOnly:true})"/> 
					        	</div>
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdM"></td>
			</tr>
			<tr>
				<td class="tdL">��ʽ��</td>
				<td class="tdR">	
					<label style="width:100px;display:inline-block;"><input type="radio" name="smsTask.method" id="method0" value="0"/>����</label>
					            <input type="radio" name="smsTask.method" id="method1" value="1" checked="checked" />push
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdM">&nbsp;</td>
			</tr>
			<tr>
				<td class="tdL">�·��</td>
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
				<td class="tdL">�·�URL��</td>
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
				<td class="tdL">������</td>
				<td class="tdR">
					<input style="width: 400px" name="content" value="" type="text"/>
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdM"></td>
			</tr>
			<tr>
				<td class="tdL">���ȼ���</td>
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
				<td class="tdL">���������ˣ�</td>
				<td class="tdR">
					 <input type="checkbox" name="smsTask.blackFilter" id="blackFilter" value="1" checked="checked" />
					            <span style="margin-left:6px;color:#FF7F00;">��ͨ�·�Ĭ�Ͻ��а��������ˣ����������������˴���ҵ����գ������ʹ�ã�</span>
				</td>
			</tr>
			</s:if>		
			<tr>
				<td class="tdL"></td>
				<td class="tdR">
				</td>
			</tr>
			<tr>
				<td class="tdL">�Ƿ��������⣺</td>
				<td class="tdR">
					<input type="checkbox" name="smsTask.longTitle" id="longTitle" value="1" />
					            <span style="margin-left:6px;color:#FF7F00;">��ѡ�·�ʱ�����Ա���ضϣ������·���������һ�����ŵĳ����ʺϽض�!</span>
				</td>
			</tr>			
			<tr>
				<td class="tdL"></td>
				<td class="tdR">
				</td>
			</tr>
			<tr>
				<td class="tdL">����31597��</td>
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
				<td class="tdL">ȥ�ع���</td>
				<td class="tdR">
					<input type="radio" name="smsTask.qcType" id="qcType7" value="7" checked="checked"/>��ȫȥ��&nbsp;&nbsp;
					<input type="radio" name="smsTask.qcType" id="qcType6" value="6" />����Ԥ���б�ȥ��&nbsp;&nbsp;
					<input type="radio" name="smsTask.qcType" id="qcType5" value="5" />�����ѷ�ȥ��&nbsp;&nbsp;
					<input type="radio" name="smsTask.qcType" id="qcType0" value="0" />��ȥ��&nbsp;&nbsp;
				</td>
			</tr>			
			<tr>
				<td class="tdL"></td>
				<td class="tdR">
				</td>
			</tr>
			<tr>
				<td class="tdL">�·�ʱ��Σ�</td>
				<td class="tdR">
				 <input type="checkbox" name="itType" id="itType" value="1"/>
				 <span style="margin-left:6px;color:#FF7F00;">��ѡ�Զ����·�ʱ��Σ��������Ĭ��ʱ���!</span>
				<div id="itDiv" style="display:none; ">
					<ul id="demo2"></ul>
					<a href="#">+ ���</a>
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
					<input type="button" name="button_submit" id="button_submit" value="�ύ"/>
				</td>
			</tr>
			<tr>
				<td class="tdL">&nbsp;</td>
				<td class="tdR">&nbsp;</td>
			</tr>
		</table>
	</s:form>
	
	<script language="javascript">
		function myClose(){  //�رձ�����ʱ����
			window.dialogArguments.btnQueryHandler();  //�ر�ʱ������Ҳ���Źر�
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
					{    alert('��ѡ��������');
				//		jAlert('error', '��ѡ��������', '������ʾ��');
						return;
					}
					if(!checkIt())
					{    alert('����ȷ��д�·�ʱ��Σ�');
				//		jAlert('error', '����ȷ��д�·�ʱ��Σ�', '������ʾ��');
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
			if($("input[name=itType]:checkbox")[0].checked==true){  alert("�ѹ�ѡʱ��");
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
