<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
       
    <title>审核提交成功*_*</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body onunload="myClose()">

  <script type="text/javascript">
  	function isIFrameSelf(){
  		try{
  			if(window.top==window){
  				return false;
  			}else{
  				return true;
  			}
  		}catch(e){
  			return true;
  		}
  	}
  	
  	function toHome(){
  		
  		if(isIFrameSelf()){
 // 		self.opener.location.reload(); 
  		parent.location.reload(); 
 // 		window.location.href="entryAuditJslt.jsp";
  		
  		}
  	}
  	window.setTimeout("toHome()", 1500);
  </script>
  
  	
	<table width=544 height=157 border=0 cellpadding=0 cellspacing=0 align=center>
		<tr valign=middle align=middle>
			<td background="images2/tz-002.gif">
				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td style=padding-left:80px; padding-top:10px>
							<strong>审核任务提交成功**_**!!</strong>
						</td>
					</tr>
				</table>
			</td>	
		</tr>
	</table>  
	
	  
  <script language="javascript">
  	function myClose(){   //关闭本窗口时调用此方法
		window.opener.close();  //关闭时父窗口跟着关闭
  	}
  </script>
  
	
  </body>
</html>
