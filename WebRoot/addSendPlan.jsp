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
	<base target="_seft">
</head>

<body onunload="myClose()">
   <%!java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("MM-dd-yyyy"); %>
<!--Controller-->
 
	<div class="container-fluid">
	    
	<form name="submitForm" action="submitAction!toSubmit" method="post">
			<!-- Main content Stared-->
				<div class="span8 offset3">
				
					<div class="row-fluid">
						<div class="control-group">
							<h4>用户属性&nbsp;&gt;&gt;</h4>
						
								<div class="row-fluid">
									<div class="span3 offset1">
									        <label class="checkbox" >
									            <input type="checkbox" name="osCheckbox" value="os1">Android
									        </label>
									        <label class="checkbox" >
									            <input type="checkbox" name="osCheckbox" value="os2">iPhone
									        </label>
									        <label class="checkbox">
									            <input type="checkbox" name="osCheckbox" value="os3">WP8
									        </label>
									</div>
									
									<div class="span3">   
									        <label class="checkbox">
									            <input type="checkbox" name="userStatus" value="status1">活跃
									        </label>
									        <label class="checkbox">
									            <input type="checkbox" name="userStatus" value="status2">沉默
									        </label>
									        <label class="checkbox">
									            <input type="checkbox" name="userStatus" value="status3">非活跃
									        </label>
									</div>
								</div>
							</div>
								
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
						      <input name="addSendPlan.sendUrl" id="sendUrl" type="text" id="inputPassword" placeholder="请输入URL....">
						    </div>
						</div>
						  
						<div class="control-group">
						    <h4>下发日期&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						      <input name="addSendPlan.date" type="text" id="inputDate" readonly="readonly" placeholder=<%=formatter.format(new Date()) %>>
						    &nbsp;&nbsp;&nbsp;&nbsp;
						    <img src="images2/cal.gif" width="30" height="30"
								onClick="javascript:NewCssCal('inputDate', 'mmddyyyy', 'arrow', '', '', '', 'future')"
								style="cursor:pointer" title="点击选择日期"/>	
						    </div>
						  </div>
						  
						  <div class="control-group">
						    <h4>优先级&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						      <select name="addSendPlan.prio">
						      	<option value="? undefined:undefined ?"></option>
								  <option>市分优先</option>
						  		  <option>省分优先</option>
						  		  <option>同步排重</option>		  		  		  		  
								</select>
						    </div>
						  </div>	
						  						  							  							  
						  <div class="control-group">
						    <div class="controls">
						      <input type="submit" class="btn btn-primary btn-large" ></input>
						    </div>
						  </div>
						        	
					</div><!-- row-fluid END-->
				
				 </div><!--container-fluid END -->
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
			});
	</script>

	<script language="javascript">
		function myClose(){  //关闭本窗口时调用
			window.dialogArguments.btnQueryHandler();  //关闭时父窗口也跟着关闭
		}
	
	</script>
  	
</body>
</html>
