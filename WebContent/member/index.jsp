<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
*{margin:0px;padding:0px}
.wrapper{
	width:100%;
	height:600px;
	overflow:hidden;
}
.regist-area{
	width:15%;
	height:100%;
	background:yellow;
	float:left;
}
.list-area{
	width:85%;
	height:100%;
	background:dodgerblue;
	float:left;
}
</style>
<script src="/static/js/lib.js"></script>
<script>
var id,name,gender;
var jsonArray=new Array();

addEventListener("load",function(){
	id=document.getElementById("id");
	pass=document.getElementById("pass");
	gender=document.getElementById("gender");
	
	document.querySelector("button").addEventListener("click",function(){
		regist();
	});
	getList();
});

//쿠키에 데이터를 담자
//쿠키에 들어가는 데이터는 모두 스트링
function regist(){
	//객체 취급해야 프로그래밍시 편하므로 그냥 객체로 일단 코드를 작성하고 있음
	//등록 버튼 누를때마다 한사람에 대한 정보 생성, 즉 객체지향적으로 처리한다면 json이 적당함. 
	//물론 배열도 가능하지만 객체지향적은 아니다.
	var json={
		id:id.value,
		pass:pass.value,
		gender:gender.value
	};
	//만일 쿠키에 배열이 있다면 그 배열을 이용한다
	if(getCookie("memberList").length>0){
		jsonArray=JSON.parse(getCookie("memberList"));
	}
	
	jsonArray.push(json);
	console.log("누적된 회원 수 : ",jsonArray.length);
	
	//만들어진 배열을 쿠키에 넣기
	setCookie("memberList",JSON.stringify(jsonArray),365);
	
	getList();
	
	/*
	//alert(json.id+", "+json.pass+", "+json.gender);
	//쿠키에 넣기
	//alert(JSON.stringify(json)); //JSON.stringify() 객체를 스트링화 시킴
	//setCookie("member",JSON.stringify(json),365);
	
	//복수의 데이터 넣기
	setCookie("memberList",JSON.stringify(json),365);
	
	//한사람 정보를 꺼내어 성별 출력해보자
	var obj=getCookie("member");
	//쿠키에 들어간 데이터는 스트링이기 때문에 객체취급하려면 다시 JSON객체로 복원해야 한다.
	var member=JSON.parse(obj);// 반대는 JSON.stringify()
	//alert(member.gender);
	*/
}
//div에 테이블 동적 출력
function getList(){
	var list_area=document.getElementById("list-area");
	var str="";
	str+="<table width='100%' border='1px'>";
	str+="<tr>";
	str+="<td>ID</td>";
	str+="<td>PASSWORD</td>";
	str+="<td>GENDER</td>";
	str+="</tr>";
	
	//쿠키에 들어있는 배열(현재는 String임) 가져오기
	var memberList=getCookie("memberList");
	console.log("가져온 쿠키 길이는 ", memberList.length);
	if(memberList.length==0){
		str+="<tr>";
		str+="<td colspan='3'>데이터가 없습니다.</td>";
		str+="</tr>";
	}else{
		var jsonArray=JSON.parse(getCookie("memberList"));
		for(var i=0;i<jsonArray.length;i++){
			var json=jsonArray[i];//DTO 꺼내기
			str+="<tr>";
			str+="<td>"+json.id+"</td>";
			str+="<td>"+json.pass+"</td>";
			str+="<td>"+json.gender+"</td>";
			str+="</tr>";
		}
	}
	str+="</table>";
	
	list_area.innerHTML=str;
}
</script>
</head>
<body>
<div class="wrapper">
	<div class="regist-area">
		<input type="text" id="id" placeholder="아이디 입력"/>
		<input type="text" id="pass" placeholder="비밀번호 입력"/>
		<input type="text" id="gender" placeholder="성별 입력"/>
		<button onClick="">등록</button>
	</div>
	<div class="list-area" id="list-area"></div>
</div>
</body>
</html>