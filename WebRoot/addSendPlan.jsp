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
							<h4>�û�����&nbsp;&gt;&gt;</h4>
						
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
									            <input type="checkbox" name="userStatus" value="status1">��Ծ
									        </label>
									        <label class="checkbox">
									            <input type="checkbox" name="userStatus" value="status2">��Ĭ
									        </label>
									        <label class="checkbox">
									            <input type="checkbox" name="userStatus" value="status3">�ǻ�Ծ
									        </label>
									</div>
								</div>
							</div>
								
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
						      <input name="addSendPlan.sendUrl" id="sendUrl" type="text" id="inputPassword" placeholder="������URL....">
						    </div>
						</div>
						  
						<div class="control-group">
						    <h4>�·�����&nbsp;&gt;&gt;</h4>
						    <div class="controls">
						      <input name="addSendPlan.date" type="text" id="inputDate" readonly="readonly" placeholder=<%=formatter.format(new Date()) %>>
						    &nbsp;&nbsp;&nbsp;&nbsp;
						    <img src="images2/cal.gif" width="30" height="30"
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
		function myClose(){  //�رձ�����ʱ����
			window.dialogArguments.btnQueryHandler();  //�ر�ʱ������Ҳ���Źر�
		}
	
	</script>
  	
</body>
</html>
