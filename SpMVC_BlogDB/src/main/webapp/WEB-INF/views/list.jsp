<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<c:set var="rootPath" value="${pageContext.request.contextPath}" />
 
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<style>
	#main{
		background-image: url("${rootPath}/static/images/협재해수욕장.jpg");
	}


</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
    <section id="main">
        <article id="button">
        	<!-- <a href="input"> 이걸 작성하면 내가 블로그작성 버튼을 누르면 웹사이트 주소가
        	 input으로 되어 있는 곳으로 이동을 한다 -->
            <button><a href="${rootPath}/blog/input">블로그 작성</a></button>
        </article>

        <article id="blog_body">
        <% /* 향상된 for문 이용해서 전체 리스트를 다 보여주기
        	for(BlogVO : blogList)
        	== <c:forEach>형식으로 만들어준다.
        
       items=${BLOGS}: 컨트롤러로부터 받은 값 
       var="BLOG" : 변수
        
   		${BLOG.title}에서 title은 VO에 있는 변수 명과 같아야 한다.
        */ %>
        	<c:forEach items="${BLOGS}" var="BLOG">
            <section class="blog_title" onclick="goView(${BLOG.bl_seq})">
                <h3>${BLOG.bl_title} - <span>${BLOG.bl_user}</span></h4>
               
            </section>    
            <section class="blog_text">
              	<h5>${BLOG.bl_contents}</h5>
            	
            </section>
            </c:forEach>
	<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
	<script>
		function goView(seq){
			/*  현재 보고 있는 화면에서 href="주소"로 화면을 전환하라
 */			
			document.location.href
			="${rootPath}/blog/view?seq="+seq
		}
	</script>
</body>
</html>