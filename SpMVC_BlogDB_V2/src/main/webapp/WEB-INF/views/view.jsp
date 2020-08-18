<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
<c:set var="rootPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<!-- home.jsp에 있는 코드 붙여 넣음. -->
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<style>
	menu{
	text-align: right;
	width: 90%;
	}

	menu a {
	display: inline-block;
	padding: 8px 16px;
	margin: 5px;
	border-radius: 5px
	}
	
	
	
	menu a:nth-child(1) {
	background-color: blue;
	color: white;
	}
	menu a:nth-child(2) {
	background-color: green;
	}
	menu a:nth-child(3) {
	background-color: aqua;
	}
	
	menu a:nth-child(4) {
	background-color: orange;
	color: black;
	}
	
	menu a:hover {
	background-color: gray;
	color: blue;
	}
</style>

</head>
<script type="text/javascript">

	$(function(){
	
	
	/*
	// 선택자
		$(this) : 이벤트 핸들러에서 사용하는 시스템객체
		$("tag") : tag 선택자
		$("#id") : id 선택자
		$(".class") : class 선택자, 같은 class가 지정된 모든 tag
	  */
	$("#delete").click(function(){
			if(confirm("정말 삭제할까요?")){
			document.location.href = "${rootPath}/blog/delete?seq=${BLOG.bl_seq}"
		}
	})
		
		
	$("#update").click(function(){
		document.location.href = "${rootPath}/blog/update?seq=${BLOG.bl_seq}"
	})
	
	$("#dumy").click(function(){
		alert("실제 존재하지 않는 id에 event를  설정하여도 코드는 오류가 나지 않는다.")
	})
	
	})

</script>
<body>
	    <%@ include file="/WEB-INF/views/include/include-header.jspf" %>
    <section id="main">
        
        <!-- 이름명 명명할 때 가급적 _(언더바)로 해주기
        나중에 자바 스크립으로 넘어가면 -(일반)로 명명했을 때 문제 생길 수도 있다. -->
        <article id="blog_body">
            <section class="blog_title">
                <h3>${BLOG.bl_title}</h3>
                <h3>작성자 : ${BLOG.bl_user}</h3>
                <h3>작성일자 : ${BLOG.bl_date}</h3>
                <h3>작성시간 : ${BLOG.bl_time}</h3>
               
            </section>
            
            
            <section class="blog_text">
                <p>${BLOG.bl_contents}</p>
            </section>
            
            </article>
            
            <menu>
            	<a href="${rootPath}/">처음으로</a>
            	<a href="${rootPath}/blog/list">리스트</a>
            	<a href="javascript:void(0)" id="update">수정</a>
            	<!--"javascript:void(0) : 
            		a 태그의 역할을 하지 말라
            		클릭하면 반응하지 말아라! -->
            	<a href="javascript:void(0)" id="delete">삭제</a>
            	
            </menu>
       
<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>

</body>
</html>