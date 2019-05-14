/*------------------------------------------------------
쿠키에 값 저장하기
cname : 저장할 쿠키 이름(즉 key값)
cvalue : 저장할 데이터(즉 value 값)
exdays : 유효기간(쿠키 사용가능 기간)
------------------------------------------------------------*/
function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays*24*60*60*1000));
	var expires = "expires="+ d.toUTCString();
	document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

/*------------------------------------------------------
쿠키값 읽기
cname값 즉 키값을 넘겨주면 데이터를 반환
------------------------------------------------------------*/
function getCookie(cname) {
	var name = cname + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for(var i = 0; i <ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);//반환데이터
		}
	}
	return "";
}

/*------------------------------------------------------
쿠키 삭제
cname : 지우고 싶은 쿠키 이름(즉 key 값)
              과거날짜를 명시하면 살제가 된다.
------------------------------------------------------------*/
function deleteCookie(cname){
	document.cookie = cname+"=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}