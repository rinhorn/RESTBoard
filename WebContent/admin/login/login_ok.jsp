<%@ page contentType="text/html; charset=UTF-8"%>
<%
	session.setAttribute("admin", "scott");
	response.sendRedirect("/admin/login/login.jsp");
%>