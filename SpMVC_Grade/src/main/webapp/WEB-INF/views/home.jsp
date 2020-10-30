<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" /> 
<link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&display=swap" rel="stylesheet">   
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
		display: flex;
		margin: 30px 0;
		padding: 20px;
	}
</style>
</head>
<body>
	<h3>성적 처리 프로그램 V1</h3>
	<table>
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>국어</th>
			<th>영어</th>
			<th>수학</th>
			<th>총점</th>
			<th>평균</th>
			<th>기타</th>
			
		</tr>
		<c:if test="${empty GRADE_LIST }">
		<tr>
			<td colspan="8">데이터가 없습니다</td>
		</tr>
		</c:if> 
		<c:forEach items="${GRADE_LIST}" var="grade">
		<tr>
			<td>${grade.g_id}</td>
			<td>
			<a href="${rootPath}/update?seq=${grade.seq}">${grade.g_name}</a>
			</td>
			<td>${grade.g_kor}</td>
			<td>${grade.g_eng}</td>
			<td>${grade.g_math}</td>
			<td>${grade.g_sum}</td>
			<td>${grade.g_avg}</td>
			<td>
			<a href="${rootPath}/delete?seq=${grade.seq}">삭제</a>
			</td>
		</tr>
	</c:forEach>
	</table>
	<div>
		<a href="${rootPath}/input">성적입력하기</a>
	</div>
</body>
</html>