<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	/*
spring security에서 role 정보를 확인하여 권한에 따라서 메뉴를 보이고 보이지 않도록 하는 용도로 사용하는 taglib

*/
%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<script>
	//현재 화면의 DOM이 load된 후 
	// DOMContentLoaded 와 비슷한 이벤트로 load 이벤트 가 있는데 
	// load event 는 좀 오래전에 지정된 event이다. 
	// 원래 이 이벤트는 window.onLoad() event를 감싸는 핸들링이다. 

	// 최근의 JS에서는 별로 사용을 권하지 않는 이벤트이다. 
	window.addEventListener("load", function() {
		
		document.querySelector("#menu-home").addEventListener("click",function() {
			
					document.location.href = "${rootPath}/"

				})
				
		document.querySelector("#menu-center").addEventListener("click",function() {
			
					document.location.href = "${rootPath}/admin"

				})

		document.querySelector("#menu-join").addEventListener("click",
				function() {
					//alert("회원가입")
					document.location.href = "${rootPath}/user/join"

				})

		document.querySelector("#menu-login").addEventListener("click",
				function() {
					document.location.href = "${rootPath}/user/login"

				})
		document.querySelector("#menu-mypage").addEventListener("click",
				function() {
					document.location.href = "${rootPath}/user/mypage"

				})
	})
</script>
<nav id="main-nav">
	<ul>
		<li id="menu-home">Home</li>
		<li id="menu-center">Center</li>
		
		<sec:authorize access="isAnonymous()">

			<li id="menu-join">회원가입</li>
			<li id="menu-login">로그인</li>
		</sec:authorize>
		
		<%
			/*
			isAuthenticated()
				   현재 화면에서 권한에 관계없이 로그인한 세션정보가 있으면 mypage, 로그아웃 메뉴를 보여라 
				*/
		%>
		<sec:authorize access="isAuthenticated()">
			<li id="menu-mypage">MyPage</li>
			<li>로그아웃</li>
		</sec:authorize>

		<%
			/*
			hasrole 권한정보가 admin이면
				*/
		%>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li>관리자</li>
		</sec:authorize>
		
		<%
			/*
			hasrole
				*/
		%>
		<sec:authorize access="hasRole('USER')">
			<li>일반사용자</li>
		</sec:authorize>
		
		
			<%
			/*
			hasrole
				*/
		%>
		<sec:authorize access="hasRole('GUEST')">
			<li>손님</li>
		</sec:authorize>
	</ul>
</nav>








