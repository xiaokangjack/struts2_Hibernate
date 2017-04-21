<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#session.adminusername==null">
	<%
		response.sendRedirect("login.jsp");
	%>
</s:if>
<html>
<head>
<title>报到管理系统</title>
</head>
<frameset framespacing="0" border="0" frameborder="0" rows="64,*"
	marginwidth="0" marginheight="0">
	<frame name="banner" scrolling="no" noresize target="contents"
		src="banner.html">
	<frameset cols="160,*">
		<frame name="left" target="main" scrolling="auto" src="left.jsp"
			marginwidth="10" marginheight="0">
		<frame name="main" scrolling="auto" marginwidth="0" marginheight="0"
			src="RegStatus.action">
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</frameset>
</html>