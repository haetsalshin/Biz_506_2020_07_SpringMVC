<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
	font-family: 'East Sea Dokdo', cursive;
	font-size: 30px;
	}
</style>
</head>
<body>
	<h3>📫 상세 Email 내역 📫</h3>
	<p>보내는 Email : ${EMS.from_email}</p>
	<p>받는 Email : ${EMS.to_email}</p>
	<p>보내는 날짜 : ${EMS.s_date}</p>
	<p>보내는 시각 : ${EMS.s_time}</p>
	<p>제목  : ${EMS.s_subject}</p>
	<p>내용 : ${EMS.s_content}</p>
</body>
<a href="${rootPath}/">처음으로</a>
</html>