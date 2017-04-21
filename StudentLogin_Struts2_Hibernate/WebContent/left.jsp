<%@ page contentType="text/html;charset=GBK" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head><title>登录系统</title></head>
<body>
<div align="center">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#C0C0C0" width="140">
<s:set name="adminuserrole" value="#session.adminuserrole" scope="action"/>  
<s:if test="#adminuserrole!=null&&(#adminuserrole==1||#adminuserrole==2)">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center">
    <font color="#0000FF">报到分班管理</font>
    </td>
  </tr>
  <tr>
    <td width="100%" align="center">
    <font><a href="class/classadmin.jsp" target="main">
    	报到分班</a></font>
    </td>
  </tr>
  <tr>
    <td width="100%" align="center">
    <font><a href="ClassView.action" target="main">
    	分班情况查询</a></font>
    </td>
  </tr>
</s:if>
<s:if test="#adminuserrole!=null&&(#adminuserrole==1||#adminuserrole==3)">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center">
    <font color="#0000FF">报到收费管理</font>
    </td>
  </tr>
  <tr>
    <td width="100%" align="center">
    <font><a href="money/acceptmoney.jsp" target="main">
    	收费情况登记</a></font>
    </td>
  </tr>
  <tr>
    <td width="100%" align="center">
    <font><a href="ClassView.action" target="main">
    	收费情况查询</a></font>
    </td>
  </tr>
</s:if>
<s:if test="#adminuserrole!=null&&(#adminuserrole==1||#adminuserrole==4)">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center">
    <font color="#0000FF">学生宿舍管理</font>
    </td>
  </tr>
  <tr>
    <td width="100%" align="center">
    <font><a href="bed/bedchamber.jsp" target="main">
    	宿舍分配</a></font>
    </td>
  </tr>
  <tr>
    <td width="100%" align="center">
    <font><a href="ClassView.action" target="main">
    	宿舍情况查询</a></font>
    </td>
  </tr>
</s:if>
<s:if test="#adminuserrole!=null&&#adminuserrole==1">
  <tr>
    <td width="100%" bgcolor="#C0C0C0" align="center">
    <font color="#0000FF">基础数据管理</font>
    </td>
  </tr>
  <tr>
    <td width="100%" align="center">
    <font><a href="Speciality.action" target="main">
    	录入专业</a></font>
    </td>
  </tr>
  <tr>
    <td width="100%" align="center">
    <font><a href="Matri.action" target="main">
    	录入录取学生名册</a></font>
    </td>
  </tr>
  <tr>
    <td width="100%" align="center">
    <font><a href="Bedchamber.action" target="main">
    录入宿舍</a></font>
    </td>
  </tr>
  <tr>
    <td width="100%" align="center">
    <font><a href="Class.action" target="main">录入班级</a></font>
    </td>
  </tr>
  <tr>
    <td width="100%" align="center">
    <font><a href="basicdata/regstatus.jsp" target="main">
    学生报到状况查询</a></font>
    </td>
  </tr>
  <tr>
    <td width="100%" align="center">
    <font><a href="AdminUser.action" target="main">
    用户管理</a></font>
    </td>
  </tr>
</s:if>
</table>
</div>
</body>
</html>