<%@ page contentType="text/html; charset=UTF-8"%>
<%
	session.invalidate();//세션 무효화
	//이 시점부터는 기존 사용하던 세션 사용 불가
	response.sendRedirect("/admin/login/login.jsp");
%>