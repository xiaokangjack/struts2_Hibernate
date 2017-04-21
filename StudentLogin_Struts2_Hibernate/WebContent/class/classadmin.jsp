<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<s:form method="post" action="ClassAdmin" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="700">
  <tr>
    <td width="100%" bgcolor="#C0C0C0">
    <font color="#0000FF">要查询的条件</font></td>
  </tr>
  <tr>
    <td width="100%">　
    请输入姓名：
    <s:textfield name="studentname"/>
    请输入录取通知书号：
    <s:textfield name="matrino"/>
    <s:hidden name="action" value="select"/>
    <s:submit value="提交"/>
    </td>
  </tr>
</table>
</s:form>
<s:form action="ClassAdmin" method="post" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="700">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center" colspan="8">
    <font color="#0000FF">查询到的学生数据</font></td>
  </tr>
  <tr>
    <td width="5%" align="center">序号</td>
    <td width="12%" align="center">姓名</td> 
    <td width="16%" align="center">录取通知书号</td> 
    <td width="12%" align="center">录取专业</td>  
    <td width="15%" align="center">所在班级</td> 
  </tr>
<s:iterator value="#request.stuArray" status="status">
  <tr>
    <td width="5%" align="center"><s:property value="#status.count"/></td>
    <td width="12%" align="center"><s:property value="studentName"/></td> 
    <td width="16%" align="center"><s:property value="matriNo"/></td> 
    <td width="12%" align="center">
    <s:property value="speciality.specialityName"/>
    </td>  
    <td width="15%" align="center">
    <s:hidden name="stuParamArray[%{#status.index}].studentId" value="%{StudentId}"/>
	<s:select name="stuParamArray[%{#status.index}].classTa.classId" listKey="classId"
		listValue="className" list="#request.classArray"
		headerKey="0" headerValue="==请选择==" value="classTa.classId">
    </s:select>
    </td> 
  </tr>
</s:iterator>
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center" colspan="8">
    <font color="#0000FF">
    <s:submit value="确 定"/>
    </font></td>
  </tr>	
  <s:hidden name="action" value="update"/>
</table>
</s:form>
</body>
</html>