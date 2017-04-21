<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<s:form method="post" action="AdminUser" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0">
    <font color="#0000FF">新增一个用户</font></td>
  </tr>
  <tr>
    <td width="100%">　
    用户名：
    <s:textfield name="adminusername"/>
    密码:
    <s:password name="adminuserpassword"/><br>
    &nbsp;&nbsp;用户角色:
    <s:select name="adminuserrole" list="#{'':'====请选择====','1':'系统管理人员',
    		'2':'系部管理人员','3':'财务管理人员','4':'宿舍管理人员'}">
    </s:select>
    <s:hidden name="action" value="add"/>
    <s:submit value="提交"/>
    </td>
  </tr>
</table>
</s:form>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center" colspan="4">
    <font color="#0000FF">已有用户数据</font></td>
  </tr>
  <tr>
    <td width="20%" align="center">　
		序号
    </td>
    <td width="30%" align="center">　
		用户名
    </td> 
    <td width="30%" align="center">　
		角色
    </td> 
    <td width="20%" align="center">　
		删除？
    </td>      
  </tr>
<s:set name="userArray" value="#request.userArray" scope="action"/>
<s:if test="#userArray!=null">
	<s:iterator value="#userArray" status="status">
  <tr>
    <td width="20%" align="center">　
		<s:property value="#status.count"/>
    </td>
    <td width="30%" align="center">
    	<s:property value="adminUserName"/>s　
    </td> 
    <td width="30%" align="center">　
    	<s:if test="adminUserRole==1">
    		系统管理人员
    	</s:if>
    	<s:if test="adminUserRole==2">
    		系部管理人员
    	</s:if>
    	<s:if test="adminUserRole==3">
    		财务管理人员
    	</s:if>
    	<s:if test="adminUserRole==4">
    		宿舍管理人员
    	</s:if>
    </td> 
    <td width="20%" align="center">　
		<a href="AdminUser.action?action=del&adminusername=<s:property value="adminUserName"/>">
		删除？</a>
    </td>      
  </tr>
	</s:iterator>
</s:if>
</table>
</body>
</html>