<%@ page contentType="text/html; charset=UTF-8"%>
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
		regist();
	});
})

function regist() {
	$.ajax({
		url:"/rest/boards",
		type:"post",
		data:{
			writer:$("input[name='writer']").val(),
			title:$("input[name='title']").val(),
			content:$("textarea[name='content']").val()
		},
		success:function(result){
			//형식은 json이지만 서버에서 text로 전송되기 때문에 json 객체화 시켜서 사용하자
			var json=JSON.parse(result);
			if(json.resultCode==1){
				$(location).attr("href","/rest/boards");
			}else{
				alert("등록 실패");
			}
		}
		
	});
}
</script>
</head>
<body>

	<h3>Contact Form</h3>

	<div class="container">
		<form>
			<input type="text" name="writer" placeholder="Your name.."> 
			<input type="text" name="title" placeholder="Write Title...">
			<textarea name="content" placeholder="Write something.." style="height: 200px"></textarea>
			<input type="button" value="regist"> 
			<input type="button" value="list">
		</form>
	</div>

</body>
</html>
