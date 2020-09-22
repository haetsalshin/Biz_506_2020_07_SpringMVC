<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
</head>
<body>
	<h3>Herzlich Willkommen zu meiner HomePage!</h3>
	<p> Ich bin haetsalshin
	<p> Suchen Sie alles hier
	<form method="POST" action="${rootPath}/naver/api">
		<input name="book_name">
		<button>검색</button>
	</form>
</body>
</html>