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
			&nbsp;&nbsp;<s:property value="serviceName"/><font style="COLOR: #000000;">【${serverName}】</font>
			</td>
			<td align="right" valign="bottom">你好：${usersession.adminname} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br></td>
			<td align="right" valign="bottom"><a href="<s:url value="/logout.do"/>">退出</a></td>
		</TR>
	</table>
	<div class="logobottom"></div>
	<div class="blank12"></div>
	</td>
</tr>
</table>

