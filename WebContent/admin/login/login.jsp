<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  background-color: black;
}

* {
  box-sizing: border-box;
}

/* Add padding to containers */
.container {
  padding: 16px;
  background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.registerbtn:hover {
  opacity: 1;
}

/* Add a blue text color to links */
a {
  color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
  background-color: #f1f1f1;
  text-align: center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

function login(){
	//location.href="login_ok.jsp";//세션 부여 jsp
	form1.action="/admin/login";
	form1.method="post";
	form1.submit();
}
function logout(){
	location.href="login_out.jsp";//세션 소멸 jsp
}
</script>
</head>
<body>

<form name="form1">
  <div class="container">
    <h1>관리자 로그인</h1>
    <hr>
    
    <input type="text" placeholder="ID" name="id" required>

    <input type="password" placeholder="PASSWORD" name="pass" required>
    
    <%if(session.getAttribute("admin")==null){ %>
 	   <button type="button" class="registerbtn" onClick="login()">Login</button>
    <%}else{ %>
  	  <button type="button" class="registerbtn" onClick="logout()">Logout</button>
    <%} %>
  </div>
  
<!-- 
  <div class="container signin">
    <p>Already have an account? <a href="#">Sign in</a>.</p>
  </div>
-->
</form>

</body>
</html>
