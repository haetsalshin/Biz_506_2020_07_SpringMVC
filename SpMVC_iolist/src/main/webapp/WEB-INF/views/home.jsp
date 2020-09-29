<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${rootPath}/static/css/homepage-main.css?ver=2020-09-29-004" rel="stylesheet">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    /*  js파일에서 el tag의 ${rootPath}값을 참조하기 위해서
    	rootPath 변수를 전역으로 선언해둔다.
    	*/
    	var rootPath = "${rootPath}"
    </script>
<script src="${rootPath}/static/js/main-nav.js?ver=2020-09-29-003"></script>
<title>매출관리 홈페이지</title>
<style>
</style>
</head>
<body>
	<header id="main-header">
		<h1>📕 Loo9 샵 매입매출 관리 간편장부 📕</h1>
		<p>서비스에 오신 것을 환영합니다</p>
	</header>
	<nav>
		<ul id="main-nav">
			<li>Home💵</li>
			<li id="io-list">장부보기💵</li>
			<li>회원가입💵</li>
			<li>로그인💵</li>
			<li>로그아웃💵</li>
		</ul>
	</nav>
	
	<section id="main-section">
	<c:choose>
		<c:when test="${BODY == 'IO-LIST'} }">
			<%@ include file = "/WEB-INF/views/io/iolist.jsp" %>
		
		</c:when>
		<c:when test="${BODY == 'IO-WRITE'} }">
			<%@ include file = "/WEB-INF/views/io/io-write.jsp" %>
		</c:when>
		
	<c:otherwise>
		<%@ include file ="/WEB-INF/views/main-body.jsp" %>
	
	</c:otherwise>
	</c:choose>
	
	</section>
	<footer>
		<address>CopyRight &copy; sinsin09022@gmail.com</address>
	</footer>
</body>