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
    <link type="text/css" href="css/custom-theme/jquery-ui-1.10.0.custom.css" rel="stylesheet" />
 	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="bootstrap/css/docs.css" rel="stylesheet">   

	<base target="_seft">
	 <style type="text/css">
	     #input22 {
	      width: 655px;
	     }

    </style>
</head>

<body onunload="myClose()">
   <%!java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("MM-dd-yyyy"); %>
<!--Controller-->
 
	<div class="container-fluid">
	    
	<form name="submitForm" action="submitAction!toSubmit" method="post" onsubmit="return checkForm()">
			<!-- Main content Stared-->
				<div class="span7 offset2">
				
					<div class="row-fluid" >
								
						<div class="control-group">
						    <h4>����&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						    
						    <textarea name="addSendPlan.sendTitle" id="sendTitle" cols='80' rows='3'
						     onkeyup="value=value.replace(/[\r\n]/g,'')" placeholder="���������...."></textarea>
						    
						    </div>
						</div>
						 
						<div class="control-group">
						    <h4>URL&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						      <input name="addSendPlan.sendUrl" id="sendUrl" type="text" id="input22" placeholder="������URL...." readonly="readonly">
						    </div>
						</div>
						  
						<div class="control-group">
						    <h4>��ͼURL&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						      <input name="gUrl" id="modifyTask.imgUrl" type="text"  onChange="loadImage()" placeholder="������URL....">
						    </div>
						</div>
						
						<div class="alert alert-error">
  							<button type="button" class="close" data-dismiss="alert">&times;</button>
  							<strong>��ʾ!</strong> ��ע��Ϊ0~9999��������.
						</div><br/>
						<div class="control-group">
						    <h4>��ע��&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						      <input name="priority" id="priority" type="text" id="input" placeholder="�������ע��....">
						    </div>
						</div>
						  
						  <div class="control-group">
						    <div class="controls">
						      <input type="submit" class="btn btn-success btn-large" value="��&nbsp;��"></input>
						    </div>
						  </div>
						        	
					</div><!-- row-fluid END-->
				
				 </div><!--container-fluid END -->
	</form>
					<div class="span4">
						<img id="taskImage" alt="��������ȷ��ͼ·��..." width="450" height="450" style="visibility:hidden;" >						
					</div>
			  <hr>

	    	</div><!--/.fluid-container-->
	
 	<script src="js/jquery.min.js"></script>
 	<script src="js/jquery.js"></script>
 	<script src="js/js/bootstrap-alert.js"></script>
    <script language="javascript" src="datetimepicker_css.js"></script>

	<script type="text/javascript">
			$(document).ready(function(){
				window.onmessage = function(event) {
    				console.log(event.data);
    				var message=event.data;
    				var title=$("#sendTitle");
    				var url=$("#sendUrl");
    					title.val(message.title);
    					url.val(message.url);
				};
			});
	</script>

	<script language="javascript">
		function myClose(){  //�رձ�����ʱ����
			window.dialogArguments.btnQueryHandler();  //�ر�ʱ������Ҳ���Źر�
		}
		function loadImage(){
			var imgUrl=document.getElementById("modifyTask.imgUrl").value;
			var img=document.getElementById("taskImage");
//			alert(imgUrl);
			img.style.visibility="visible";
			img.src=imgUrl;
		}
		
		function checkForm(){
			var priority = document.getElementById("priority").value.trim();
			alert("abc345");
			alert(Integer.parseInt(priority));
			if(
	//		!priority.match("^[1-9]\\d*|0$")JSP��������ʽ��ƥ������//////////////////
			||Integer.parseInt('priority')<0
			||Integer.parseInt('priority')>9999
			){
				alert("���ȼ�ֻ����Ϊ0~9999������");
				submitForm.priority.focus();
				return false;
			}
			return true;
		}
	</script>
  	
   
    
   
  	
</body>
</html>
