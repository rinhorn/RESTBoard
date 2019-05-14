<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//가급적 자바코드를 두면 안된다.
	//비 개발자와 jsp 코드를 공유하므로 디자인적 표현에만 집중
	//가독성이 높아야 하므로 [%%] 지양...
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TEST</title>
</head>
<body>

<c:set var="x" value="3"/>
<c:set var="y" value="2"/>
${x+y} 
<c:set var="z" value="${x+y} "/>
${z}
</body>
</html>