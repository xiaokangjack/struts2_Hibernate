<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<br><br><br><br>
<div align="center">
<s:form method="post" action="Login" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="300">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center">
    <font color="#0000FF">用户登录</font></td>
  </tr>
  <tr>
  	<td align="left">
  		<s:fielderror/><s:property value="errormsg"/>
  	</td>
  </tr>
  <tr>
  	<td>
  	请输入用户名:<s:textfield name="adminusername"/><br>
  	请输入密&nbsp;&nbsp;码:<s:password name="adminuserpassword"/><br>
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	<s:submit value="提 交"/>
  	<s:hidden name="action" value="login"/>
  	</td>
  </tr>
</table>
</s:form>
</div>
</body>
</html>