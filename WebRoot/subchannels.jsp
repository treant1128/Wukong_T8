<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
  <head>
    <meta charset="UTF-8">
    <title>频道 &middot; 细分</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="wukong">
    <meta name="keywords" content="">
    <meta name="robots" content="index,follow">
    <meta name="application-name" content="bootcss.com">

    <!-- Le styles -->
    <link href="bootstrap/css/bootstrap.min.css?v2.3.1" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-responsive.min.css?v2.3.1" rel="stylesheet">
    <link href="bootstrap/css/docs.css?v2.3.1" rel="stylesheet">
  
   

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="assets/js/html5shiv.js"></script>
    <![endif]-->
	<style type="text/css">
	
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
	
	</style>

  </head>

  <body data-spy="scroll" data-target=".bs-docs-sidebar">
  
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
                        	<li><a href="javascript:skipToSubChannelEntry('<s:property value="@com.wukong.t8.utils.Utils@encodeURL(#element.opmlOutlineXmlUrl)"/>')" >
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
							<li><a href="javascript:showMainChannels('<s:property value="#element.channelOpmlUrl"/>')" >
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


   <hr class="soften">
	<div class="container">

  		<div class="marketing">
  
		  <s:iterator begin="0" end="#session.o4cListRows" status="status">
		  	
		  		<div class="row-fluid">
		  		
					<div class="span4">
						<h2> 
						<!-- 	<a href="javascript:skipToSubChannelEntry('<s:property value="@com.wukong.t8.utils.Utils@encodeByUTF8(#session.o4cList[#status.index*3+0].opmlOutlineXmlUrl)"/>')" >  -->
								<a href="javascript:skipToSubChannelEntry('<s:property value="#session.o4cList[#status.index*3+0].opmlOutlineXmlUrl"/>')" >
								<s:property value="#session.o4cList[#status.index*3+0].opmlOutlineTitle"/></a><br>
						<!--  <a href=<s:property value="#session.feedList[#status.index*3+0].feedImageLink"/> target="_blank">
								<small><s:property value="#session.feedList[#status.index*3+0].feedImageLink"/></small></a>  -->
						</h2>
						<p><br/>
						<!--  	<s:property value="#session.feedList[#status.index*3+0].feedImageTitle"/>  -->
						</p>
					</div>
		
					<div class="span4">
						<h2> 
							<a href="javascript:skipToSubChannelEntry('<s:property value="#session.o4cList[#status.index*3+1].opmlOutlineXmlUrl"/>')" >
								<s:property value="#session.o4cList[#status.index*3+1].opmlOutlineTitle"/></a><br>
						<!--    <a href=<s:property value="#session.feedList[#status.index*3+1].feedImageLink"/> target="_blank">
								<small><s:property value="#session.feedList[#status.index*3+1].feedImageLink"/></small></a>  -->
						</h2>
						<p><br/>
						<!--   <s:property value="#session.feedList[#status.index*3+1].feedImageTitle"/>  -->
						</p>
					</div>
		
					<div class="span4">
						<h2> 
							<a href="javascript:skipToSubChannelEntry('<s:property value="#session.o4cList[#status.index*3+2].opmlOutlineXmlUrl"/>')" >
								<s:property value="#session.o4cList[#status.index*3+2].opmlOutlineTitle"/></a><br>
						<!--  	<a href=<s:property value="#session.feedList[#status.index*3+2].feedImageLink"/> target="_blank">
								<small><s:property value="#session.feedList[#status.index*3+2].feedImageLink"/></small></a>  -->
						</h2>
						<p><br/>
						<!--  <s:property value="#session.feedList[#status.index*3+2].feedImageTitle"/>  -->
						</p>
					</div>
					
				</div>
		  
		  </s:iterator>

  		</div>

	</div>
	<hr class="soften">
	<div id="footer-spacer"></div>   
	</div>
	<!-- 
	<div id="footer">
    <footer class="footer">
      <div class="container">
        <p>Designed and built with all the love in the world by <a href="http://twitter.com/mdo" target="_blank">@mdo</a> and <a href="http://twitter.com/fat" target="_blank">@fat</a>.</p>
        <p>Code licensed under <a href="http://www.apache.org/licenses/LICENSE-2.0" target="_blank">Apache License v2.0</a>, documentation under <a href="http://creativecommons.org/licenses/by/3.0/" target="_blank">CC BY 3.0</a>.</p>
        <p><a href="http://glyphicons.com" target="_blank">Glyphicons Free</a> licensed under <a href="http://creativecommons.org/licenses/by/3.0/" target="_blank">CC BY 3.0</a>.</p>
        <ul class="footer-links">
          <li><a href="#" target="_blank">关于</a></li>
          <li class="muted">&middot;</li>
          <li><a href="#" target="_blank">问题反馈</a></li>
          <li class="muted">&middot;</li>
          <li><a href="#">版本变更记录</a></li>
          <li><a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备11008151号</a></li>
        </ul>
      </div>
    </footer>
	</div>
  -->
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
//			alert(decodeURIComponent(url));
//			alert(url);
//			alert(encodeURIComponent(url));
//			alert(encodeURIComponent(encodeURIComponent(url)));
			window.location.href="opml4channelAction!toSubChannelEntry?opmlOutlineXmlUrl="+encodeURIComponent(encodeURIComponent(url))+"&pageKey=1st";
		}
		function showMainChannels(url){
	//		alert(url);
			window.location.href="channelAction!toSubChannels?channelOpmlUrl="+encodeURIComponent(url);
		}
	</script>  

    <script type="text/javascript">
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F3d8e7fc0de8a2a75f2ca3bfe128e6391' type='text/javascript'%3E%3C/script%3E"));
    </script>
	
  </body>
</html>
