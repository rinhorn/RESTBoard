<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.wrapper{
	width:100%;
	height:500px;
}
.regist-area{
	width:20%;
	height:500px;
	background:yellow;
}
.list-area{
	width:60%;
	height:500px;
	background:pink;
	overflow:scroll;
}
.detail-area{
	width:20%;
	height:500px;
	background:blue;
}
.regist-area, .list-area, .detail-area{
	float:left;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(function(){
	getList();
	
	$("form[name='regist-form']").find("button").click(function(){
		regist();
	});

	$($("button")[1]).click(function(){
		edit();
	});
	
	$($("button")[2]).click(function(){
		del();
	});
});

//비동기 요청
function regist(){
	$.ajax({
		url:"/rest/boards",
		type:"post",
		data:{
			writer:$($("form[name='regist-form']").find("input[name='writer']")).val(),
			title:$($("form[name='regist-form']").find("input[name='title']")).val(),
			content:$($("form[name='regist-form']").find("input[name='content']")).val()
		},
		success:function(result){
			//alert("파싱 전의 값: "+result);
			var json=JSON.parse(result);
			//목록 갱신
			if(json.resultCode==1){
				getList();
			}else{
				alert("등록되지 않았습니다");
			}
		},
		error:function(result){
			alert(result);
		}
	});
}

//비동기 목록 요청
function getList(){
	$.ajax({
		url:"/rest/boards",
		type:"get",
		success:function(result){
			renderList(JSON.parse(result));
		},
		error:function(result){
		}
	});
}

//리스트 화면 처리
function renderList(json){
	$(".list-area").html("");  //기존 데이터 지우기

	$(".list-area").append("<table width='100%' border='1'>");
	$(".list-area").append("<tr>");
	$(".list-area").append("<td width='10%'>Seq</td>");
	$(".list-area").append("<td width='30%'>작성자</td>");
	$(".list-area").append("<td width='30%'>제목</td>");
	$(".list-area").append("<td width='30%'>내용</td>");
	$(".list-area").append("</tr>");

	for(var i=0;i<json.length;i++){
		var obj=json[i];
		$(".list-area").append("<tr>");
		$(".list-area").append("<td>"+obj.board_id+"</td>");
		$(".list-area").append("<td>"+obj.writer+"</td>");
		$(".list-area").append("<td>"+obj.title+"</td>");
		$(".list-area").append("<td><a href='javascript:getDetail("+JSON.stringify(obj)+");'>"+obj.content+"</a></td>");
		$(".list-area").append("</tr>");
	}
	
	$(".list-area").append("</table>");
}

function getDetail(board){
	$.ajax({
		url:"/rest/boards/"+board.board_id,
		type:"get",
		
		success:function(result){
			var json=JSON.parse(result);
			$($("form[name='detail-form']").find("input[name='board_id']")).val(json.board_id);
			$($("form[name='detail-form']").find("input[name='writer']")).val(json.writer);
			$($("form[name='detail-form']").find("input[name='title']")).val(json.title);
			$($("form[name='detail-form']").find("input[name='content']")).val(json.content);
		}
	});
}

function del(){
	if(!confirm("삭제하실래요?")){
		return;
	}
	$.ajax({
		url:"/rest/boards",
		type:"get",
		data:{
			member_id:$($("form[name='detail-form']").find("input[name='board_id']")).val()
		},
		success:function(result){
			var json=JSON.parse(result);
			if(json.result==1){
				//alert("삭제완료");
				getList();
				//상세보기 초기화 (채워져 있지 않게)
				$("form[name='detail-form']").trigger("reset");
			}else{
				alert("삭제실패");
			}
		}
	});
}

function edit(){
	$.ajax({
		url:"/rest/boards",
		type:"post",
		data:{
			member_id:$($("form[name='detail-form']").find("input[name='board_id']")).val(),
			id:$($("form[name='detail-form']").find("input[name='writer']")).val(),
			pass:$($("form[name='detail-form']").find("input[name='title']")).val(),
			name:$($("form[name='detail-form']").find("input[name='content']")).val()
		},
		success:function(result){
			var json=JSON.parse(result);
			if(json.result==1){
				getList();
			}else{
				alert("수정실패")
			}
		}
	});
}
</script>
</head>
<body>
	<div class="wrapper">
		<div class="regist-area">
			<form name="regist-form">
				<input type="text" name="writer" placeholder="작성자입력"/>
				<input type="text" name="title" placeholder="제목입력"/>
				<input type="text" name="content" placeholder="내용입력"/>
				</p>
				<button type="button">등록</button>
			</form>
		</div>
		<div class="list-area"></div>
		<div class="detail-area">
			<form name="detail-form">
				<input type="hidden" name="board_id"/>
				<input type="text" name="writer" placeholder="작성자입력"/>
				<input type="text" name="title" placeholder="제목입력"/>
				<input type="text" name="content" placeholder="내용입력"/>
				</p>
				<button type="button">수정</button>
				<button type="button">삭제</button>
			</form>
		</div>
	</div>
</body>
</html>