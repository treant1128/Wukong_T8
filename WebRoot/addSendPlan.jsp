<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>�·� &middot; �ύ</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<base target="_seft">
	<style type="text/css">
		#sendUrl, textarea{
			margin-left:50px;
			width: 550px;
		}
		
		#inputDate, select{
			margin-left:50px;
			width:40%;
		}
	
		.btn{
			margin-left:300px;
		}
	</style>
</head>

<body onunload="myClose()">
   <%!java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("MM-dd-yyyy"); %>
<!--Controller-->
 
	<div class="container-fluid">
	    
	<form id="submitForm" action="submitAction!toSubmit" method="post" style="width: 100%;height: 100%">
			<!-- Main content Stared-->
						<div class="control-group">
							<h4>�û�����&nbsp;&gt;&gt;</h4>
						
								<div class="row-fluid">
									<div class="span2 offset1">
									        <label class="checkbox" >
									            <input type="checkbox" name="iPhone" value="3G">3G-iPhone
									        </label>
									        <label class="checkbox" >
									            <input type="checkbox" name="iPhone" value="2G">2G-iPhone
									        </label>
									</div>
									
									<div class="span2">   
									        <label class="checkbox">
									            <input type="checkbox" name="android" value="3G">3G-Android
									        </label>
									        <label class="checkbox">
									            <input type="checkbox" name="android" value="2G">2G-Android
									        </label>
									</div>
									
									<div class="span2">   
									        <label class="checkbox">
									            <input type="checkbox" name="active" value="3G">3G-��Ծ
									        </label>
									        <label class="checkbox">
									            <input type="checkbox" name="active" value="2G">2G-��Ծ
									        </label>
									</div>
									<div class="span2">   
									        <label class="checkbox">
									            <input type="checkbox" name="inactive" value="3G">3G-�ǻ�Ծ
									        </label>
									        <label class="checkbox">
									            <input type="checkbox" name="inactive" value="2G">2G-�ǻ�Ծ
									        </label>
									</div>
									<div class="span2">   
									        <label class="checkbox">
									            <input type="checkbox" name="silent" value="3G">3G-��Ĭ

									        </label>
									        <label class="checkbox">
									            <input type="checkbox" name="silent" value="2G">2G-��Ĭ
									        </label>
									</div>
									
								</div>
							</div>
								
						<div class="control-group">
						    <h4>����&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						    
						    <textarea name="addSendPlan.sendTitle" id="sendTitle" cols='80' rows='3' style=""
						     onkeyup="value=value.replace(/[\r\n]/g,'')" placeholder="���������...."></textarea>
						    
						    </div>
						</div>
						 
						<div class="control-group">
						    <h4>URL&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						      <input name="addSendPlan.sendUrl" id="sendUrl" type="text"
						      id="inputPassword" placeholder="������URL....">
						    </div>
						</div>
						  
						<div class="control-group">
						    <h4>�·�����&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						      <input name="addSendPlan.date" type="text" id="inputDate" 
						      readonly="readonly" value=<%=formatter.format(new Date()) %>>
						    &nbsp;<<<&nbsp;
						    <img src="images2/cal.gif" width="35" height="35"
								onClick="javascript:NewCssCal('inputDate', 'mmddyyyy', 'arrow', '', '', '', 'future')"
								style="cursor:pointer" title="���ѡ������"/>	
						    </div>
						  </div>
						  
						  <div class="control-group">
						    <h4>���ȼ�&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						      <select name="addSendPlan.prio">
						      	<option value="? undefined:undefined ?"></option>
								  <option>�з�����</option>
						  		  <option>ʡ������</option>
						  		  <option>ͬ������</option>		  		  		  		  
								</select>
						    </div>
						  </div>	
						  						  							  							  
						  <div class="control-group">
						    <div class="controls">
								<input type="button" name="button_submit" id="button_submit" class="btn btn-danger btn-large" value="ȷ��"></input>
						    </div>
						  </div>

	</form>
			  <hr>

	    	</div><!--/.fluid-container-->

 	<script src="js/jquery.js"></script>
    <script language="javascript" src="datetimepicker_css.js"></script>

	<script type="text/javascript">
			$(document).ready(function(){
				window.onmessage = function(event) {
//    				console.log(event.data);
    				var message=event.data;
    				var title=$("#sendTitle");
    				var url=$("#sendUrl");
    					title.val(message.title);
    					url.val(message.url);
				};
				
				
				$("#button_submit").click(function(){
//					window.parent.postMessage("Treant", '*');
					$("#submitForm").submit();
				});
			});
	</script>

	<script language="javascript">
		function myClose(){  //�رձ�����ʱ����
			window.dialogArguments.btnQueryHandler();  //�ر�ʱ������Ҳ���Źر�
		}
	
	</script>
  	
</body>
</html>
