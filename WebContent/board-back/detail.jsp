<%@page import="com.itbank.model.domain.Board"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%Board board=(Board)request.getAttribute("board"); %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(function() {
	$($("input[type=button]")[0]).click(function() {
		edit();
	});
	$($("input[type=button]")[1]).click(function() {
		del();
	});
	$($("input[type=button]")[2]).click(function() {
		location.href="/board/list";
	});
})

function edit() {
	if(!confirm("수정하시겠습니까?")){
		return;
	}	
	$("form").attr({
		"action" : "/board/update",
		"method" : "post"
	});
	$("form").submit();
}

function del() {
	if(!confirm("삭제하시겠습니까?")){
		return;
	}
	$("form").attr({
		"action" : "/board/delete",
		"method" : "post"
	});
	$("form").submit();
}
</script>
</head>
<body>

	<h3>Show Details</h3>

	<div class="container">
		<form>
			<input type="text" name="writer" value="<%=board.getWriter()%>">
			<input type="text" name="title" value="<%=board.getTitle()%>">
			<textarea name="content" style="height: 200px">
				<%=board.getContent()%>
			</textarea>
			<input type="button" value="edit">
			<input type="button" value="delete">
			<input type="button" value="list">
			<input type="hidden" name="board_id"
				value="<%=board.getBoard_id()%>">			
		</form>
	</div>

</body>
</html>
