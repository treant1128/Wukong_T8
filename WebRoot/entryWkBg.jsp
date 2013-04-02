<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="GBK">
    <title>资讯 &middot; 内容</title>
   
    <meta name="description" content="">
    <meta name="author" content="">
	<!-- Le styles -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="bootstrap/css/docs.css" rel="stylesheet">

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

    <style type="text/css">
	     body {
	       padding-top: 40px;
	       padding-bottom: 40px;
	       background-color: #f5f5f5;
	     }

		html, body {
   		 	height: 100%;
		}
		body {
		    margin: 0;
		    padding: 0;
		    background-color: #fff;
		}
		#top {
		    position: absolute;
		}
		h1, p {
		    margin: 0;
		    padding: 0.3em 0;
		}
		#container {
		    min-height: 100%;
		    margin-bottom: -36px;
		}
		* html #container {
		    height: 100%;
		}
		#footer-spacer {
		    height: 36px;
		}
		#footer {
		    border-top: 1px solid #000;
		    height: 35px;
		}
		#myModal{
			width:1000px;
			height: 800px;
			margin:0px 0px 0px -450px;
		}
		#myModal .modal-body{
			max-height: 980px;
		}
		#addTaskFrame{
			margin-left: 10px;
			margin-right: 10px;
			width: 900px;
			height: 700px;
		}
    </style>

  </head>

  <body>
	<div id="top"></div>
	<div id="container">
	        <!-- Navbar ================================================== -->
    <div class="navbar navbar-inverse navbar-fixed-top">
	  <div class="navbar navbar-inverse" style="position: static;">
              <div class="navbar-inner">
                <div class="container">
                  <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-inverse-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </a>
                  
                  <div class="nav-collapse collapse navbar-inverse-collapse">
                    <ul class="nav">
                      <li class="divider-vertical"></li>
                      <li class="active"><a href="index.jsp">首页</a></li>
                      <li class="divider-vertical"></li>
                      <li><a href="javascript:showLog()">日志</a></li>
                      <li class="divider-vertical"></li>
                      <li><a href="#">关于</a></li>
                      <li class="divider-vertical"></li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">子频道列表 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                        
                        <s:iterator var="element" value="#session.o4cList">
                        	
                        	<li><a href="javascript:skipToSubChannelEntry('<s:property value="#element.opmlOutlineXmlUrl"/>')" >
							<s:property value="#element.opmlOutlineTitle"/></a></li>
                        </s:iterator>
            
                        </ul>
                      </li>
                      <li class="divider-vertical"></li>
                    </ul>
                    
                    <form class="navbar-search pull-left" action="entryAction!toSearch" method="post">
                      <input type="text" class="search-query span2" 
                      		name="property" placeholder="按标题搜索">
                    </form>
                    
                    <ul class="nav pull-right">
                     
                      <li class="divider-vertical"></li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">主频道列表 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                        
                        <s:iterator var="element" value="#session.channels">
							<li><a href="javascript:showSubChannels('<s:property value="#element.channelOpmlUrl"/>')" >
                        		<s:property value="#element.channelName"/></a></li>                        
                        </s:iterator>
                        </ul>
                      </li>
                      <li class="divider-vertical"></li>
                      <a class="brand" href="#"><font style="bold">悟空资讯</font></a>
                      <li class="divider-vertical"></li>
                    </ul>
                  </div><!-- /.nav-collapse -->
                </div>
              </div><!-- /navbar-inner -->
            </div><!-- /navbar -->
    </div>
	<div class="jumbotron masthead">
	  <div class="container">
	    <marquee width="%100" scrollamount=2>
	    	<h1 ><a href="http://www.wukong.com" target="_blank">www.wukong.com</a></h1></marquee>
	  </div>
	</div>

	<ul class="breadcrumb">
	  <li><a href="index.jsp">
	  	<FONT face=楷体_GB2312 color="#9932CC" size=5><STRONG>主频道</STRONG></FONT></a><span class="divider">&gt;&gt;</span></li>
	  <li><a href="subchannels.jsp">
	  	<FONT face=楷体_GB2312 color="#4169E5" size=5><STRONG>子频道</STRONG></FONT></a><span class="divider">&gt;&gt;</span></li>
	  <li class="active">
	  	<FONT face=楷体_GB2312 color="#ff6600" size=5><STRONG>当前页面记录数目&lt;</STRONG></FONT>
	  	<FONT face=楷体_GB2312 color="#ff6600" size=5><STRONG><s:property value="#session.entrySize"/></STRONG></FONT>
	  	<FONT face=楷体_GB2312 color="#ff6600" size=5><STRONG>&gt;条</STRONG></FONT></li>
	</ul>


	  <div class="pagination pagination-large">
		  <ul>
		    <li><a href="#">上一页</a></li>
		    <li class="active"><a href="#">1</a></li>
		    <li><a href="#">2</a></li>
		    <li><a href="#">3</a></li>
		    <li><a href="#">4</a></li>
		    <li><a href="#">5</a></li>
		    <li><a href="#">下一页</a></li>
		  </ul>
	</div>
	 	
   	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<table class="table table-hover">
						<thead>
							<tr>
					  			<td><h3>编 号</h3></td>
					  			<td><h3>标 题</h3></td>
					  			<td><h3>链 接</h3></td>
					  	<!-- 		<td><h3>分 类</h3></td>   -->
					  	  		<td><h3>来 自</h3></td>  
					  			<td><h3>时 间</h3></td>
					  			<td><h3>操 作</h3></td>
							</tr>
						</thead>
						<tbody>
						<tr>

  						</tr>  
  						 	<s:iterator var="element" value="#session.entries">
						   		<tr title='<s:property value="#element.entryDescription"/>'>
						  			<td><s:property value="#element.entryId"/></td>
						  		
						  			<td class="newsTitle"><s:property value="#element.entryTitle" /></td>
						  			<td>
						  				<a class="newsUrl" target="_blank" href=<s:property value="#element.entryGuid"/>>
						  					<s:property value="#element.entryGuid"/>
						  				</a>
						  			</td>
						  	<!-- 		<td><s:property value="#element.entryCategory"/></td>   -->
						 			<td><s:property value="#element.entryAuthor"/></td>  
						  		
						  			<td><s:property value="@com.wukong.t8.utils.Utils@cutOffMillis(#element.entryPubDate)" /></td>
						  			<td>
						  				<a href="#myModal" role="button" class="btn btn-primary btn-small" data-toggle="modal">提&nbsp;交</a>
						  				<a href="#myModal" role="button" class="btn btn-danger btn-small" data-toggle="modal">更&nbsp;新</a>
						  			</td>
						  		</tr>
   							</s:iterator>
   						</tbody>
					</table>				
				</div>
			</div>
		</div>   	
   	</div>
   	
   	<div class="pagination pagination-large">
		  <ul>
		    <li><a href="#">上一页</a></li>
		    <li class="active"><a href="#">1</a></li>
		    <li><a href="#">2</a></li>
		    <li><a href="#">3</a></li>
		    <li><a href="#">4</a></li>
		    <li><a href="#">5</a></li>
		    <li><a href="#">下一页</a></li>
		  </ul>
	</div>
	
   	 
   	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   		<div class="modal-header">
   			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
   			<h3 id="myModalLabel">信息下发</h3>
   		</div>
   		<div class="modal-body">
   			<iframe id="addTaskFrame" frameBorder="0" width="100%" height="100%" src="modifyParams.jsp"></iframe>
   		</div>
   		
   	</div>  
   
   	
  
   	
   	
	    <div id="footer-spacer"></div>   
	</div>
	
	<div id="footer">
	    <!-- 底部版权信息开始-->
		<table width="97%" border="0" cellspacing="0" cellpadding="0">
			<tr><td height="1" bgcolor="CECFCE"></td></tr>
			<tr><td align="center" class="f10"><br><img src="images2/logo.png"></img>&nbsp;&nbsp;Copyright &copy; 2008 - 2013 WUKONG.com, All Rights Reserved<br><br></td></tr>
		</table>
		<!-- 底部版权信息结束-->
	</div>
	
   	<script src="js/jquery.js"></script>
   	<script src="bootstrap/js/bootstrap.min.js"></script>
   	<script type="text/javascript" >
   		function skipToSubChannelEntry(url){
			window.location.href="opml4channelAction!toSubChannelEntry?opmlOutlineXmlUrl="+encodeURIComponent(encodeURIComponent(url));
		}
		
		function showSubChannels(url){
	//		alert(url);
			window.location.href="channelAction!toSubChannels?channelOpmlUrl="+encodeURIComponent(encodeURIComponent(url));
		}
		
		function showLog(){
			window.location.href="channelAction!toLog";
		}
	</script>   
	
	<script type="text/javascript">
			$(document).ready(function(){
				var sendBtn=$(".btn");

				sendBtn.click(function(){
						var myself=$(this);
						var tr=myself.parent().parent();
						var title=tr.find(".newsTitle");
						var title_content="";
						var url=tr.find(".newsUrl");
						var url_href="";

						var message={};

						if(tr[0].nodeName==="TR"){
							if (title.length>0) {
								title_content=title[0].innerText;
							}else{
								title_content="错误，未找到合适的新闻标题"; 
							}
							//===============================================

							if (url.length>0) {
									url_href=url.attr("href");
							}else{
									url_href="地址错误";
							}
							
						}else{
							title_content="未知错误";
							url_href="未知错误";
						}
						
						message.title=title_content;
						message.url=url_href;
						window.frames[0].postMessage(message, '*');
						//console.log(message);
				});
			});

	</script>
	
  </body>
</html>
