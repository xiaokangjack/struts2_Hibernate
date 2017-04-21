<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
<s:form method="post" action="ClassView" theme="simple">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="700">
  <tr>
    <td width="100%" bgcolor="#C0C0C0">
    <font color="#0000FF">要查询的条件</font></td>
  </tr>
  <tr>
    <td width="100%">　
    请选择要查询的班级:
	<s:select name="classid" listKey="classId"
		listValue="className" list="#request.classArray"
		headerKey="0" headerValue="==请选择==" value="classId">
    </s:select>
    <s:hidden name="action" value="select"/>
    <s:submit value="提交"/>
    </td>
  </tr>
</table>
</s:form>

<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="700">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center" colspan="8">
    <font color="#0000FF">已有的学生报到数据</font></td>
  </tr>
  <tr>
    <td width="5%" align="center">序号</td>
    <td width="12%" align="center">姓名</td> 
    <td width="16%" align="center">录取通知书号</td> 
    <td width="12%" align="center">录取专业</td>  
    <td width="15%" align="center">所在班级</td>
    <td width="10%" align="center">是否交费</td>   
    <td width="15%" align="center">已交学费</td>
    <td width="15%" align="center">所在宿舍</td>    
  </tr>
<s:set name="stuArray" value="#request.stuArray" scope="action"/>
<s:if test="#stuArray!=null">
	<s:set name="counts" value="%{0}"/>
	<s:iterator value="#stuArray" status="status">
	<s:if test="StudentId!=null">
	<s:set name="counts" value="#counts+1"/>
  <tr>
    <td align="center"><s:property value="#counts"/></td>
    <td align="center"><s:property value="studentName"/></td> 
    <td align="center"><s:property value="matriNo"/></td> 
    <td align="center">
    <s:property value="speciality.specialityName"/>
    </td>  
    <td align="center">
    <s:if test="classTa.className!=null">
    	<s:property value="classTa.className"/> 
    </s:if>
    <s:else>
    	尚未分班
    </s:else>
    </td>
    <td align="center">
    <s:if test="payOK!=1">
    	未交清
    </s:if>
    <s:else>
    	已交清
    </s:else>
    </td>   
    <td align="center">
    <s:if test="payAmount!=null">
    	<s:property value="payAmount"/>
    </s:if>
    <s:else>
    	0.0
    </s:else>
    </td>
    <td align="center">
    <s:if test="bedchamber!=null">
    	<s:property value="bedchamber.bedchamberName"/>
    </s:if>
    <s:else>
    	尚未分配
    </s:else>
    </td>    
  </tr>
    </s:if>
	</s:iterator>
</s:if>
</table>
</body>
</html>
