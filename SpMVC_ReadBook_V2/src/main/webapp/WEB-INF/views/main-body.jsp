<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<style>
	*{
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	#div-main{
		margin: 40px auto;
		text-align: center;
	}
	a{
		background-image: url("../images/bookboy.png");
	}

</style>

<section id="main-body">
		
	<div id="div-main">
		<h3>가장 훌륭한 벗은 가장 좋은 책이다</h3>
		<h5>-체스터필드-</h5>
	</div>
	<div id="div-pic"><a></a></div>

</section>