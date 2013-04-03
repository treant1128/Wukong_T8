<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>下发 &middot; 提交</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
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
						    <h4>标题&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						    
						    <textarea name="addSendPlan.sendTitle" id="sendTitle" cols='80' rows='3'
						     onkeyup="value=value.replace(/[\r\n]/g,'')" placeholder="请输入标题...."></textarea>
						    
						    </div>
						</div>
						 
						<div class="control-group">
						    <h4>URL&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						      <input name="addSendPlan.sendUrl" id="sendUrl" type="text" id="input22" placeholder="请输入URL...." readonly="readonly">
						    </div>
						</div>
						  
						<div class="control-group">
						    <h4>题图URL&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						      <input name="gUrl" id="modifyTask_imgUrl" type="text"  placeholder="请输入URL....">
						    </div>
						</div>
						
						<div class="alert alert-error">
  							<button type="button" class="close" data-dismiss="alert">&times;</button>
  							<strong>提示!</strong> 关注度为0~9999的正整数.
						</div><br/>
						<div class="control-group">
						    <h4>关注度&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						      <input name="priority" id="priority" type="text" class="fv_input_text_default" placeholder="请输入关注度....">
						    </div>
						</div>
						  
						  <div class="control-group">
						    <div class="controls">
						      <input type="submit" class="btn btn-success btn-large" value="更&nbsp;新"></input>
						    </div>
						  </div>
						        	
					</div><!-- row-fluid END-->
				
				 </div><!--container-fluid END -->
	</form>
					<div class="span4">
						<img id="taskImage" alt="请输入正确题图路径..." width="450" height="450">						
					</div>
			  <hr>

	    	</div><!--/.fluid-container-->
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

				
				var imgTextBox=$("#modifyTask_imgUrl");
				var taskImage=$("#taskImage");
					taskImage.hide();
				
				//console.log(imgTextBox);
				
				imgTextBox.blur(function(){
					//console.log("hi i am blur");
					var imgUrl=$(this).val();
					console.log(imgUrl);
					taskImage.attr("src",imgUrl);
					taskImage.show();
				});		
				
				$("#priority").formValidator({
				automodify:true,onshow:"请输入的优先级（1-999岁之间）",onfocus:"只能输入1-999之间的数字哦",oncorrect:"恭喜你,你输对了"}).inputValidator({min:1,max:99,type:"value",onerrormin:"你输入的值必须大于等于1",onerror:"年龄必须在1-990之间，请确认"}).defaultPassed();
				

			});
	</script> 	
</body>
</html>
