<%@page import="com.itbank.common.board.Pager"%>
<%@page import="com.itbank.model.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!Pager pager = new Pager();%>
<%
	List<Board> boardList = (List) request.getAttribute("boardList");
	pager.init(request, boardList.size());
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}
</style>
</head>
<body>
	<h2>Itbank Board</h2>
	<table>
		<tr>
			<th>No</th>
			<th>title</th>
			<th>writer</th>
			<th>content</th>
			<th>regdate</th>
			<th>hit</th>
		</tr>
		<%
			int num = pager.getNum();
			int curPos = pager.getCurPos();
			for (int i = 0; i < boardList.size(); i++) {
				Board board = boardList.get(curPos++);
				if (num < 1)
					break;
		%>
		<tr>
			<td><%=num--%></td>
			<td><a href="/board/detail?board_id=<%=board.getBoard_id()%>">
					<%=board.getTitle()%>
			</a></td>
			<td><%=board.getWriter()%></td>
			<td><%=board.getContent()%></td>
			<td><%=board.getRegdate()%></td>
			<td><%=board.getHit()%></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td colspan="6">
				<%
					for (int i = pager.getFirstPage(); i <= pager.getLastPage(); i++) {
						if (i > pager.getTotalPage())
							break;
				%> [<%=i%>] <%
					}
				%>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<button onClick="location.href='/board/write.jsp';">regist</button>
			</td>
		</tr>
	</table>
</body>
</html>