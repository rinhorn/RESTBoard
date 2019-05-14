<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
현재 페이지는 : ${pager.currentPage}<br>
총 레코드 수는 : ${pager.totalRecord}<br><!--  getter는 ()표시하지 않음 -->
firstPage:${pager.firstPage}<br>
lastPage:${pager.lastPage}<br>
<%-- 게시물 객체는 : ${boardList} --%> <!-- Scope은 생략가능 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page list</title>
</head>
<body>

<table width="100%" border="1px">
	<tr>
		<td>No</td>
		<td>작성자</td>
		<td>제목</td>
	</tr>
	<c:set var="curPos" value="${pager.curPos}"/>
	<c:set var="num" value="${pager.num}"/>
	<c:forEach var="board" items="${boardList}" begin="${pager.curPos}" end="${pager.curPos+pager.pageSize}">
		<tr>
			<td>${num}</td>
			<td>${board.writer}</td>
			<td>${board.title}</td>
		</tr>
		<c:set var="num" value="${num-1}"/>
	</c:forEach>
	<tr>
		<td colspan="3" align="center">
			<c:forEach var="i" begin="${pager.firstPage}" end="${pager.lastPage}">
			<c:if test="${i<=pager.totalPage}">
				<a href="/jstl/boardcontroller.jsp?currentPage=${i}">[${i}]</a>
			</c:if>
			</c:forEach>
		</td>
	</tr>
</table>
</body>
</html>