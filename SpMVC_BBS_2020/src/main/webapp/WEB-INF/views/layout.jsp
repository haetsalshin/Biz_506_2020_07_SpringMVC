<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" /> 
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>BBS 2020</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${rootPath}/resources/summernote/summernote-bs4.min.css">
<script src="${rootPath}/static/summernote/summernote-bs4.min.js"></script>
<script src="${rootPath}/static/summernote/lang/summernote-ko-KR.min.js"></script>
<style>
	*{
	font-family: "Jua", sans-serif;
		box-sizing: border-box;
		margin: 0;
		padding: 0; 
		
		
	}
	html, body{
		width: 100%;
		height: 100%;
	}
	
	body{
		display: flex;
		flex-direction: column;
	}


	header{
		background: linear-gradient(to right, gold, orange);
		color: white;
		padding: 3rem;
		text-align: center;
	}
	nav ul {
		list-style: none;
		display: flex;
		background: linear-gradient(to left, pink, #FA8258);
		color: white;
	}
	
	nav ul li{
		display: inline-block;
		margin: 3px 10px; /* top-bottom, left-right*/
		padding: 6px 12px;
		cursor: pointer;
		
	}
	nav ul li:hover{
		background-color: #ddd;
		color: black;
	}
	/*
		body를 flex로 선언하고
		content가 위치하는 box에 flex값을 1로 선언하면
		화면에 가득차는 layout이 만들어진다.
		html, body의 height를 100%로 선언해줘야한다.
		overflow: auto 로 설정을 하면 내용이 가득 찼을 때 자동으로 
		스크롤바가 생성
	*/
	section#content{
		flex:1;
		overflow: auto;
	
	}
	
	
	footer{
		background-color: black;
		color: white;
		text-align: center;
		padding: 0.3rem;
	}

</style>
</head>
<body>
<%
/*
	tiles:insertAttribute
	다른 jsp파일을 부착하는 용도의 설정값
	여기에 name으로 설정된 부분에 tile.xml파일에서 지정한 jsp 파일이 부착된다

*/%>
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="nav"/>
	<section id="content">
		<tiles:insertAttribute name="content"/>
	</section>
	
	<tiles:insertAttribute name="footer"/>
</body>
</html>