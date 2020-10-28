<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link href="https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&display=swap" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
<style>
	*{
	font-family: 'East Sea Dokdo', cursive;
	box-sizing: border-box;
	
	}
	h3{
	font-size: 40px;
	text-align: center;
	}
	#h4{
		width: 20%;
		border-bottom: 2px dotted red;
		align-items: center;
		
	}
	h4{
	font-size: 25px;
	text-align: center;
	
	}
	table#ems-list{
		width: 80%;
		font-size: 30px;
		margin: 10px auto;
		border-collapse: collapse;
		border: 1px solid #ccc;
	
	}
	
	td, th{
	font-size: 25px;
		border:  1px solid #ccc;
	}
	
	div{
	font-size: 30px;
	}
	#write{
		text-align: center;
	}
</style>

<body>
	<h3>📁나의 EMS SERVICE📁</h3>
	
	<div id="h4">
	<h4>❗ 수정을 원하면 제목을 클릭해주세요 ❗</h4>
	</div>
	
	<table id="ems-list">
		<tr>
			<th>발송 email</th>
			<th>수신 email</th>
			<th>발송 일자</th>
			<th>발송 시각</th>
			<th>제목</th>
			<th>삭제</th>
		</tr>
		<c:if test="${empty EMS_LIST}">
			<tr>
				<td colspan="5">데이터가 없슈</td>
			</tr>
		</c:if>
		<c:forEach items="${EMS_LIST }" var="ems">
			<tr>
				<td>${ems.from_email}</td>
				<td>${ems.to_email}</td>
				<td>${ems.s_date}</td>
				<td>${ems.s_time}</td>
				<td>
				<a href="${rootPath}/update?id=${ems.id}">
				${ems.s_subject}
				</a>
				
				</td>
				<td>
					<a href="${rootPath}/delete?id=${ems.id}">삭제</a>
				</td>
			</tr>
			
			
		
		</c:forEach>
	
	</table>
	
	<div id="write">
		<a href="${rootPath}/write">메일작성</a>
	</div>
</body>
</html>