<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<s:form method="post" action="Speciality" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0">
    <font color="#0000FF">录入专业数据</font></td>
  </tr>
  <tr>
    <td width="100%">　
    请输入专业名称：
    <s:textfield name="specialityname"/>
    <s:hidden name="action" value="add"/>
    <s:submit value="提交"/>
    </td>
  </tr>
</table>
</s:form>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center" colspan="3">
    <font color="#0000FF">已有专业数据</font></td>
  </tr>
  <tr>
    <td width="20%" align="center">　
		序号
    </td>
    <td width="60%" align="center">　
		专业名称
    </td> 
    <td width="20%" align="center">　
		删除？
    </td>      
  </tr>
<s:iterator value="#request.specialityArray" status="status">
  <tr>
    <td width="20%" align="center">　
		<s:property value="#status.count"/>
    </td>
    <td width="60%" align="center">　
    	<s:property value="specialityName"/>
    </td> 
    <td width="20%" align="center">　
		<a href="Speciality.action?action=del&specialityid=<s:property value="specialityId"/>">
		删除？</a>
    </td>      
  </tr>
</s:iterator>
</table>
</body>
</html>