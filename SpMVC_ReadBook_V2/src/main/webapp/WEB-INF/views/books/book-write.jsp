<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link href ="${rootPath}/static/css/book-write.css?ver=2020-09-24-003" rel="stylesheet"> 
<script>


/*  컨트롤러에서 보내준 _csrf.headerName과 _csrf.token값을 JS 파일로 전달하기 위해서
	스크립트 변수를 선언하고 book-write.js에서 ajax POST 전송전에 값을 header에 실어서 보낸다.
	js 파일에서는 csrf_header 변수와 csrf_token 변수를 백틱으로 묶어서 사용한다	
*/
	var csrf_header = '${_csrf.headerName}'
	var csrf_token = '${_csrf.token}'


</script>   
<script src="${rootPath}/static/js/book-write.js?ver=2020-10-07-002"></script>
	<h3>die Anmeldung des Buch Information</h3>
	<form:form id="books" modelAttribute="bookVO">
		<fieldset>
		<legend>Schreib das Buch Information</legend>
		
			<div><lable for="title">도서명</lable>
			<form:input path="title" placeholder="도서명"/>
			
			<button id="naver_search" type="button">네이버검색</button>
			</div>
			<div><lable for="link">상세링크</lable>
			<form:input path="link" placeholder="상세링크"/>
			</div>
			<div><lable for="image">이미지</lable>
			<form:input path="image" placeholder="이미지"/>
			</div>
			<div><lable for="author">저자</lable>
			<form:input path="author" placeholder="저자"/>
			</div>
			<div><lable for="price">가격</lable>
			<form:input path="price" placeholder="가격"/>
			</div>
			<div><lable for="discount">할인</lable>
			<form:input path="discount" placeholder="할인"/>
			</div>
			<div><lable for="publisher">출판사</lable>
			<form:input path="publisher" placeholder="출판사"/>
			</div>
			<div><lable for="isbn">ISBN</lable>
			<form:input path="isbn" placeholder="ISBN"/>
			</div>
			<div><lable for="description">세부설명</lable>
			<form:input path="description" placeholder="세부설명"/>
			</div>
			<div><lable for="pubdate">출판일자</lable>
			<form:input path="pubdate" placeholder="출판일자"/>
			</div>
			<div><lable for="buydate">구입일자</lable>
			<form:input path="buydate" placeholder="구입일자"/>
			</div>
			<div><lable for="buyprice">구입가격</lable>
			<form:input path="buyprice" placeholder="구입가격"/>
			</div>
			<div><lable for="buystore">구입처</lable>
			<form:input path="buystore" placeholder="구입처"/>
			</div>
			
		<div class="button-box">
		<button id="btn-save" type="button">저장</button>
			</div>	
		</fieldset>
</form:form>

<section id="book-modal">
	<article id="modal-body">
	<div id="modal-header">
		<span>&times;</span>
		</div>
		<div id="search-result"></div>
	</article>
</section>
