<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<s:form method="post" action="Class" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0">
    <font color="#0000FF">录入班级数据</font></td>
  </tr>
  <tr>
    <td width="100%">　
    请输入班级名称：
    <s:textfield name="classname"/>
    <s:hidden name="action" value="add"/>
    <s:submit value="提交"/>
    </td>
  </tr>
</table>
</s:form>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="600">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center" colspan="3">
    <font color="#0000FF">已有班级数据</font></td>
  </tr>
  <tr>
    <td width="20%" align="center">　
		序号
    </td>
    <td width="60%" align="center">　
		班级名称
    </td> 
    <td width="20%" align="center">　
		删除？
    </td>      
  </tr>
<s:iterator value="#request.classArray" status="status">
  <tr>
    <td width="20%" align="center">　
		<s:property value="#status.count"/>
    </td>
    <td width="60%" align="center">　
    	<s:property value="className"/>
    </td> 
    <td width="20%" align="center">　
		<a href="Class.action?action=del&classid=<s:property value="classId"/>">
		删除？</a>
    </td>      
  </tr>
</s:iterator>
</table>
</body>
</html>