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
    <title>��Ѷ &middot; ����</title>
    
<!--    
    <script language="javascript" type="text/javascript">
    	function testRadio(){
    		for(i=0;i<document.submitForm.optionsRadios.length;i++){
    			if(document.submitForm.optionsRadios[i].checked){
    				str=document.submitForm.optionsRadios[i].value;
    			}
    		}
    		confirm(str);
    		location.href="snatchEntry!toSearch?flag="+str;
    	}
    </script>
-->      
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="bootstrap/css/docs.css" rel="stylesheet">    
        
    <style type="text/css">
    
      body {
        padding-top: 50px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }

      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
 
    /* GLOBAL STYLES
    -------------------------------------------------- */
    /* Padding below the footer and lighter body text */

    

    /* CUSTOMIZE THE NAVBAR
    -------------------------------------------------- */

   
    .navbar-wrapper .navbar {

    }

    /* Remove border and change up box shadow for more contrast */
    .navbar .navbar-inner {
      border: 0;
      -webkit-box-shadow: 0 2px 10px rgba(0,0,0,.25);
         -moz-box-shadow: 0 2px 10px rgba(0,0,0,.25);
              box-shadow: 0 2px 10px rgba(0,0,0,.25);
    }

    /* Downsize the brand/project name a bit */
    .navbar .brand {
      padding: 14px 20px 16px; /* Increase vertical padding to match navbar links */
      font-size: 16px;
      font-weight: bold;
      text-shadow: 0 -1px 0 rgba(0,0,0,.5);
    }

    /* Navbar links: increase padding for taller navbar */
    .navbar .nav > li > a {
      padding: 15px 20px;
    }

    /* CUSTOMIZE THE CAROUSEL
    -------------------------------------------------- */

    /* Carousel base class */
    .carousel {
      margin-bottom: 3px;
    }

    .carousel .container {
      position: relative;
      z-index: 9;
    }

    .carousel-control {
      height: 80px;
      margin-top: 0;
      font-size: 120px;
      text-shadow: 0 1px 1px rgba(0,0,0,.4);
      background-color: transparent;
      border: 0;
      z-index: 10;
    }

    .carousel .item {
      height: 160px;
    }
    .carousel img {
      position: absolute;
      top: 0;
      left: 0;
      min-width: 100%;
      height: 160px;
    }

    .carousel-caption {
      background-color: transparent;
      position: static;
      max-width: 550px;
      padding: 0 20px;
      margin-top: 15px;
    }
    .carousel-caption h1,
    .carousel-caption .lead {
      margin: 0;
      line-height: 1.25;
      color: #fff;
      text-shadow: 0 1px 1px rgba(0,0,0,.4);
    }
    .carousel-caption .btn {
      margin-top: 10px;
    }

    /* RESPONSIVE CSS
    -------------------------------------------------- */

    @media (max-width: 1024px) {

      .container.navbar-wrapper {
        margin-bottom: 0;
        width: auto;
      }
      .navbar-inner {
        border-radius: 10;
        margin: -20px 0;
      }

      .carousel .item {
        height: 150px;
      }
      .carousel img {
        width: auto;
        height: 150px;
      }

    }

    @media (max-width: 767px) {

      .navbar-inner {
        margin: -20px;
      }

      .carousel {
        margin-left: -20px;
        margin-right: -20px;
      }
      .carousel .container {

      }
      .carousel .item {
        height: 100px;
      }
      .carousel img {
        height: 100px;
      }
      .carousel-caption {
        width: 65%;
        padding: 150px 70px;
        margin-top: 40px;
      }
      .carousel-caption h1 {
        font-size: 30px;
        padding: 20px
      }
      .carousel-caption .lead,
      .carousel-caption .btn {
        font-size: 18px;
      }
      
    }

		html, body {
		    height: 100%;
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
		.navbar-search{
			padding-top: 5px;		
		}
		#myCarousel{
			margin-bottom: 30px;
		}
		#myLi{
			height: 38px;
		}
		#alertError{
			weight: 300px;
		}
    </style>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->
	
 <!--	
	<script language="javascript" src="datetimepicker_css.js">
	
		function buildCal(){ //��ȡ��¼����ʱ��
			alert("here");
			parent.document.forms["0"].elements["oplogSearch.opname"].value
			=document.forms["0"].elements["oplogSearch.opname"].value;
			parent.document.forms["0"].elements["oplogSearch.dateBegin"].value
			=document.forms["0"].elements["oplogSearch.dateBegin"].value;
			parent.document.forms["0"].elements["oplogSearch.dateEnd"].value
			=document.forms["0"].elements["oplogSearch.dateEnd"].value;
			parent.document.forms["0"].submit();
			parent.document.getElementById('divWindow').style.display="none"; 
			return false;
		}

		function comparePair(){ 
			var begin,end;
			begin = document.forms["0"].elements["oplogSearch.dateBegin"].value;
			end = document.forms["0"].elements["oplogSearch.dateEnd"].value;
			if(begin > end && end != ""){
				alert("��ѡ���ʱ������!'); 
				return false; 
			}
			return true;
		}

	</script>
-->
  </head>

  <body>
	
	<div id="top"></div>
	<div id="container">
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
                      <li class="active"><a href="index.jsp">��ҳ</a></li>
                      <li class="divider-vertical"></li>
                      <li><a href="javascript:showLog()">��־</a></li>
                      <li class="divider-vertical"></li>
                      <li><a href="#">����</a></li>
                      <li class="divider-vertical"></li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Ƶ���б� <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                        
                        <s:iterator var="element" value="#session.channels">
                        	<li><a href="javascript:showSubChannels('<s:property value="#element.channelOpmlUrl"/>')" >
                        		<s:property value="#element.channelName"/></a></li>
                        </s:iterator>
            
                        </ul>
                      </li>
                    </ul>
                    
                    <form class="navbar-search pull-left" action="entryAction!toSearch" method="post">
                      <input type="text" class="search-query span2" 
                      		name="property" placeholder="����������">
                    </form>
                    <ul class="nav pull-right">
                      
                      <li class="divider-vertical"></li>
                      <a class="brand" href="#">��ӭ��&lt;&nbsp;<s:property value="#session.userName"/>&nbsp;&gt;</a>
                      <li class="divider-vertical"></li>
                    </ul>
                  </div><!-- /.nav-collapse -->
                </div>
              </div><!-- /navbar-inner -->
            </div><!-- /navbar -->
    </div>



    <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide">
      <div class="carousel-inner">
        <div class="item active">
          <img src="images2/slide-01.jpg" alt="">
          <div class="container">
            <div class="carousel-caption">
              <h1>�ƶ���������Ӫ�������</h1>
              <a class="btn btn-large btn-primary" href="#">Sign up now</a>
            </div>
          </div>
        </div>
        <div class="item">
          <img src="images2/slide-02.jpg" alt="">
          <div class="container">
            <div class="carousel-caption">
              <h1>���ߵ�������</h1>
              <a class="btn btn-large btn-primary" href="#">Learn more</a>
            </div>
          </div>
        </div>
        <div class="item">
          <img src="images2/slide-03.jpg" alt="">
          <div class="container">
            <div class="carousel-caption">
              <h1>��׼����Ӫ��</h1>
              <a class="btn btn-large btn-primary" href="#">Browse gallery</a>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div><!-- /.carousel -->

 	
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list" >
         
    		<li class="nav-header"><h3>Ƶ���б�</h3></li>
    		<s:iterator var="element" value="#session.channels" >
    			<li id="myLi"><a onclick="javascript:showSubChannels('<s:property value="#element.channelOpmlUrl"/>')"  
    			role="button" class="btn" data-toggle="modal">
    			<s:property value="#element.channelName"/>
    			</a></li>
    		</s:iterator>

            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span10">
        
          <div class="hero-unit">
            <h1>ý�廯��Ѷƽ̨</h1>
            <!--  
            <p>www.wukong.com ��Ӫ��֮�������ߺ���Դ�ϵľ�����Խ��Խ���ң�����Ѵ󲿷���Դ��Ԥ��Ͷ�������罨����ն˲����ϣ�������ɱ��Խ���Ӫ����Դ���޵������...</p>
            -->
            
       <div class="row-fluid">
       	<div class="span5">
          <div class="container" >
		      
		     <h3>��������...</h3>
		     
		     <form action="entryAction!toSearch" class="form-search" method="post" name="submitForm">
			     <span class="badge badge-success">1</span>&nbsp;&nbsp;&nbsp;<label class="radio">
	  				<input type="radio" name="optionRadio" id="optionsRadios1" checked value="entryTitle" >
	 				&nbsp;&nbsp;������&nbsp;&nbsp;
				 </label>
				<label class="radio">
	  				<input type="radio" name="optionRadio" id="optionsRadios2" value="entryDescription" >
					&nbsp;&nbsp;������
				 </label>
				
				<p>
				<span class="badge badge-warning">2</span>&nbsp;&nbsp;&nbsp;<label for="keyWords">������ؼ���&gt;&gt;</label>
				<input type="text" id="keyWords" class="input-medium search-query"  name="property" >
				<marquee  width=400 behavior=alternate direction=left align=middle scrollamount="2"><font color="#FF0099" >����ؼ������ÿո�ֿ�</font></marquee>
				</p>
<!--  				
 				<a href="#" onclick="testRadio()">����һ��</a>			
--> 			
				<p>
					<span class="badge badge-info">3</span>&nbsp;&nbsp;&nbsp;<label for="startTimePicker">��ѡ����ʼʱ���&gt;&gt;</label>	
					<input type="Text" name="timePicker1" id="startTimePicker" readonly="readonly" title="���ֻѡ��һ��ʱ��㣬��Ĭ���Ե�ǰϵͳʱ����Ϊ��һʱ���" 
						maxlength="25" size="25"/>
					<img src="images2/cal.gif" width="20" height="20"
					onClick="javascript:NewCssCal('startTimePicker', 'yyyyMMdd', 'arrow', true, '24', true)"
					style="cursor:pointer" alt="���ѡ������" />
				</p>
				<p>
					<span class="badge badge-inverse">4</span>&nbsp;&nbsp;&nbsp;<label for="endTimePicker">��ѡ����ֹʱ���&gt;&gt;</label>	
					<input type="Text" name="timePicker2" id="endTimePicker" readonly="readonly" title="�����ѡ��ʱ��㣬��Ĭ�ϲ�ѯ���з��������ļ�¼"
						maxlength="25" size="25"/>
					<img src="images2/cal.gif" width="20" height="20"
					onClick="javascript:NewCssCal('endTimePicker', 'yyyyMMdd', 'arrow', true, '24', true)"
					style="cursor:pointer" alt="���ѡ������"/>
				</p>	
				
				<p><a><button type="submit" class="btn btn-primary btn-large" >��ʼ����</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" class="btn btn-danger btn-large" value="�� ��">
				</p>
				
			 </form>
		
   		 </div> <!-- /container -->
   		 </div>
       </div>
   		             
            <div id="snatchOperation" class="row-fluid">
            	<div class="span4">
            		<h3>�Զ�ץȡʱ��������</h3>
            		<select name="snatchInterval" id="snatchIntervalId" class="input_text_1" 
            			onchange="getSnatchInterval(this.options[this.options.selectedIndex].value)">
							<option >ѡ��ץȡ���</option>
							<option value="0">�ֶ�ץȡ</option>
							<option value="1">1Сʱ</option>
							<option value="3">3Сʱ</option>
							<option value="6">6Сʱ</option>
							<option value="12">12Сʱ</option>
							<option value="24">24Сʱ</option>
					</select> 
            	
            	</div>
            	
            	<div class="span4">
            		<h3>�Զ�����ʱ��������</h3>
            		<select name="deleteInterval" id="deleteInterval" class="input_text_2" onchange="getDeleteInterval('deleteInterval')">
							<option >ѡ��������</option>
							<option value="0">�Ӳ�����</option>
							<option value="1">1��</option>
							<option value="3">3��</option>
							<option value="7">7��</option>
							<option value="30">30��</option>
							<option value="100">100��</option>
					</select> 
            	<div class="alert alert-error">
  							<button type="button" class="close" data-dismiss="alert">&times;</button>
  							<strong>��ʾ!</strong> ��ע��Ϊ0~9999��������.
						</div>
           		</div>
          		 <div class="span3">
	            	<p><a id="manualSnatch" href="entryAction!toSnatch" class="btn btn-primary btn-large" data-loading-text="����ץȡ...">�����ֶ�ץȡ&raquo;</a></p>
	        	</div>
            
            </div>
            
           
            
        </div><!--/span-->
      </div><!--/row-->
          
      	<hr>
	  </div>
    </div><!--/.fluid-container-->
	    <div id="footer-spacer"></div>   
	</div>
	
	<div id="footer">
	    <!-- �ײ���Ȩ��Ϣ��ʼ-->
		<table width="97%" border="0" cellspacing="0" cellpadding="0">
			<tr><td height="1" bgcolor="CECFCE"></td></tr>
			<tr><td align="center" class="f10"><br><img src="images2/logo.png"></img>&nbsp;&nbsp;Copyright &copy; 2008 - 2013 WUKONG.com, All Rights Reserved<br><br></td></tr>
		</table>
		<!-- �ײ���Ȩ��Ϣ����-->
	</div>
	
	<script type="text/javascript" >
		function showSubChannels(url){
	//		alert(url);
			window.location.href="channelAction!toSubChannels?channelOpmlUrl="+url;
		}
		
		function showLog(){
			window.location.href="channelAction!toLog";
		}
		
		function getSnatchInterval(val){
//			var obj=document.getElementById('snatchIntervalId');
//			var index=obj.selectedIndex;
//			var val=obj.options[index].value;
//			alert(val);
			if(val==0){
				alert("����ץȡģʽΪ�ֶ�");
			}else{
				alert("����ץȡ���Ϊ"+val+"Сʱ");
			}
			
			window.location.href="opml4channelAction!setTimeDelay?TIMER_INTERVAL="+val;
		}
		
		function getDeleteInterval(name){
			var obj=document.getElementById(name);
			alert(obj.value);
		}
	</script>   
	
	<script language="javascript" src="datetimepicker_css.js"></script>
		
	 <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/js/jquery.js"></script>
    <script src="js/js/bootstrap-transition.js"></script>
    <script src="js/js/bootstrap-alert.js"></script>
    <script src="js/js/bootstrap-modal.js"></script>
    <script src="js/js/bootstrap-dropdown.js"></script>
    <script src="js/js/bootstrap-scrollspy.js"></script>
    <script src="js/js/bootstrap-tab.js"></script>
    <script src="js/js/bootstrap-tooltip.js"></script>
    <script src="js/js/bootstrap-popover.js"></script>
    <script src="js/js/bootstrap-button.js"></script>
    <script src="js/js/bootstrap-collapse.js"></script>
    <script src="js/js/bootstrap-carousel.js"></script>
    <script src="js/js/bootstrap-typeahead.js"></script>
    <script>
      !function ($) {
        $(function(){
          // carousel demo
          $('#myCarousel').carousel()
        })
      }(window.jQuery)
    </script>
    <script src="js/js/holder/holder.js"></script>
    <script type="text/javascript">
		$(document).ready(function(){
				var snatch=$("#snatchOperation");
					snatch.hide();
		});
	</script>   
  </body>
</html>
