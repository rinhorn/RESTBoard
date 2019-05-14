<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//컨트롤러에서 과이렴ㅇ이 넘어왔다 간주한다.
	List list=new ArrayList();
	String[] fruits={"배","딸기","사과","오렌지","바나나"};
	for(String fruit:fruits){
		list.add(fruit);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test2</title>
</head>
<body>
<c:set var="x" value="8"/>
<c:if test="${x%2==0}">
	${"짝수입니다"}<br>
</c:if>
<c:if test="${x%2!=0}">
	${"홀수입니다"}<br>
</c:if>

<c:forEach var="i" begin="1" end="10">
${"줄넘기"} ${i}번 <br>
</c:forEach>

<c:set var="num" value="10"/>
<c:forEach var="i" begin="1" end="10">
${"num"}의 값:  ${num}번 
<c:set var="num" value="${num-1}"/>
</c:forEach>

과일명 출력하기 <br>
<c:forEach var="item" items="<%=list %>">
${"과일명은"} : ${item}<br>
</c:forEach>

</body>
</html>