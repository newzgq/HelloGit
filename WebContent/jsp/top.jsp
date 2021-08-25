<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>顶部</title>
<STYLE type=text/css>
BODY {
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-TOP: 0px;
	BACKGROUND-COLOR: #2a8dc8
}

BODY {
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}

TD {
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}

DIV {
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}

P {
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}
</STYLE>
</head>
<body>
	<FORM id=form1 name=form1 action="" method=post>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
			<TBODY>
				<TR>
					<TD width=10><IMG src="${pageContext.request.contextPath }/images/new_001.jpg" border=0></TD>
					<TD background="${pageContext.request.contextPath }/images/new_002.jpg"><FONT size=5><B>客户关系管理系统v1.0</B></FONT></TD>
					<TD background="${pageContext.request.contextPath }/images/new_002.jpg">
						<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
							<TBODY>
								<TR>
									<TD align=right height=35></TD>
								</TR>
								<TR>
								
									<%-- <TD height=35 align="right">
										&nbsp;&nbsp;&nbsp;&nbsp;
										<A href="#" target=_top><FONT color=red>
										<a href="${pageContext.request.contextPath }/jsp/login.jsp" target="_top">请登录</a>
										</FONT></A>
									</TD> --%>
								
									<TD height=35 align="right">
										当前用户：<s:property value="#session.existUser.user_name"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<A href="#" target=_top><FONT color=red>
										<a href="${pageContext.request.contextPath }/user_toEdit.action" target="main">修改密码</a>
										</FONT></A>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<A href="#" target=_top><FONT color=red>
										<a href="${pageContext.request.contextPath }/user_exit.action" target="_top">安全退出</a>
										</FONT></A>
									</TD>
									
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=10><IMG src="${pageContext.request.contextPath }/images/new_003.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</body>
</html>