<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<!-- home.jsp에 있는 코드 붙여 넣음. -->
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
</head>
<body>
	    <%@ include file="/WEB-INF/views/include/include-header.jspf" %>
    <section id="main">
        <article id="button">
        	<!-- <a href="input"> 이걸 작성하면 내가 블로그작성 버튼을 누르면 웹사이트 주소가
        	 input으로 되어 있는 곳으로 이동을 한다 -->
            <button><a href="input">블로그작성</a></button>
        </article>
   
        <!-- 이름명 명명할 때 가급적 _(언더바)로 해주기
        나중에 자바 스크립으로 넘어가면 -(일반)로 명명했을 때 문제 생길 수도 있다. -->
        <article id="blog_body">
            <section class="blog_title">
                <h4 >${TITLE}</h4>
               
            </section>
            
            
            <section class="blog_text">
                <p>${CONTENT}</p>
            </section>
            
       
<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>

</body>
</html>