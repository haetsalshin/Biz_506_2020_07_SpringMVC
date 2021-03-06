<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&display=swap" rel="stylesheet">
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
<style>
*{
		font-family: 'Nanum Myeongjo', serif;
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	h3{
		margin: 30px 0;
		padding: 20px;
		text-align: center;
	}
	div{
		width: 90%;
	
	}
</style>
</head>
<body>

	<h3>학생의 성적을 입력해주세요</h3>
	<form method="POST" id="form">
		<div>
			<label>학번</label> <input name="g_id" placeholder="S001형식으로 입력해주세요" value="${GRADE.g_id}">
		</div>
		<div>
			<label>이름</label> <input name="g_name" value="${GRADE.g_name}">
		</div>
		<div>
			<label>국어</label> <input name="g_kor" type="number" value="${GRADE.g_kor}">
		</div>
		<div>
			<label>영어</label> <input name="g_eng" type="number"  value="${GRADE.g_eng}">
		</div>
		<div>
			<label>수학</label> <input name="g_math" type="number"  value="${GRADE.g_math}">
		</div>

		<div>
			<button>저장</button>
		</div>
	</form>
</body>
</html>