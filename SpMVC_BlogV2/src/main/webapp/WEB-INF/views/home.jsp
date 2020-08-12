<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<c:set var="rootPath" value="${pageContext.request.contextPath}" />
 
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
    <section id="main">
        <article id="button">
        	<!-- <a href="input"> 이걸 작성하면 내가 블로그작성 버튼을 누르면 웹사이트 주소가
        	 input으로 되어 있는 곳으로 이동을 한다 -->
            <button><a href="${rootPath}/blog/input">블로그작성</a></button>
        </article>
   
        <!-- 이름명 명명할 때 가급적 _(언더바)로 해주기
        나중에 자바 스크립으로 넘어가면 -(일반)로 명명했을 때 문제 생길 수도 있다. -->
        <article id="blog_body">
            <section class="blog_title">
                <h3>너는 물처럼 내게 밀려오라 - 이정하</h4>
               
            </section>
            	
            
            <section class="blog_text">
              	<h5>낮은 곳에 있고 싶었다.</h5>
            	<h5>낮은 곳이라면 지상의 그 어디라도 좋다.</h5>
            	<h5>찰랑찰랑 고여들 네 사랑을</h5>
            	<h5>온몸으로 받아들일 수만 있다면</h5>
            	<h5>한 방울도 헛되이 새어 나가지 않게 할 수 있다면</h5>
            	<h5>그래, 내가 낮은 곳에 있다는 것은</h5>
            	<h5>너를 위해 나를 온전히 비우겠다는 뜻이다.</h5>
            	<h5>잠겨 죽어도 좋으니</h5>
            	<h5>너는</h5>
            	<h5>물처럼 내게 밀려오라.</h5>
            </section>
       
	<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
</body>
</html>