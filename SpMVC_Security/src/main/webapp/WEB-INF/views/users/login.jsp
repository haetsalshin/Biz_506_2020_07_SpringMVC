<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
</head>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	
}

html, body {
	height: 100%;
	width: 100%;
}

input {
	display: inline-block;
	margin: 8px 20px;
	padding: 5px;
	flex: 1;
	text-align: center;
	font-weight: bold;
	width: 20%;
	background-color:lightyellow;
}

button {
	background-color: orange;
	padding: 5px 40px;
	text-align: center;
	margin: 2%;
	display: block;
	border: none;
}

button:hover {
	cursor: pointer;
}

label {
	display: inline-block;
}

h4#login-fail {
	margin:5px auto;
	background: linear-gradient(to right, black, blue);
	color:white;
	border-radius: 15px;
	padding:8px;
	width:25%;
	text-align:center;
}
</style>
<body>
<section id="">
	
		<form method="POST" action="${rootPath }/login">
		<h2>로그인</h2>
		
		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
		<h4 id="login-fail">${SPRING_SECURITY_LAST_EXCEPTION.message }</h4>
			<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
		</c:if>
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<label>아이디</label><input name="username" placeholder="USER ID">
			 <label>비밀번호</label><input name="password" type="password" placeholder="PASSWORD">
			<button>로그인</button>
			<button type="button">회원가입</button>
		</form>
	
	</section>
</body>
</html>