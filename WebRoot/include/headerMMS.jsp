<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<table style="width:100%;">
<tr>
	<td align="center">
	<div class="blank12"></div>
	<table cellSpacing=0 cellPadding=0 width="100%" align="center"
		style="margin: 0px auto;">
		<TR>
			<td valign="middle" class="logotext">
			&nbsp;&nbsp;<s:property value="serviceName"/><font style="COLOR: #000000;">【${serverName}】彩信</font>
			</td>
			<td align="right" valign="bottom">你好：${usersession.adminname} &nbsp;&nbsp;&nbsp;&nbsp;<a href="http://t4.wukong.com/mt_home.html">选择服务器</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</TR>
	</table>
	<div class="logobottom"></div>
	<div class="blank12"></div>
	</td>
</tr>
</table>

