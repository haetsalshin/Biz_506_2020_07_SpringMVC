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
<% /* 
	@SessionAttributes(), @ModelAttribute(), spring form taglib를 연동한 write(입력 form)구현
	Controller class에 @SessionAttributes("bookVO")를 설정하고 
	각 method에 매개변수로 @ModelAttributes("bookVO") BookVO bookVO를 선언하고
	Controller class의 필드 멤버영역에 @ModelAttribute("bookVO") public BookVO netBookVO() {} method를 선언하고
	spring form taglib를 이용한 write form에 <form:form modelAttribute="bookVO">를 선언하여
	프로젝트를 구현하면
	
	id, seq 등 실제 사용자에게 입력받거나, 보여줄 필요가 없는 VO의 변수들을 
	<input type="hidden">으로 설정한 후 Controller로 전송하던 HTML5 표준 방식을 사용하지 않아도
	VO에 설정된  변수들을 Controller와 JSP 가 서로 공유하여 사용할 수 있다.
	
	@SessionAttrivutes()에 담긴 VO객체는 서버의 메모리에 보관되며 
	HTTP 프로토콜의 비연결지향(상태가 없는 통신) 통신상태에서도 데이터를 서로 자유롭게 
	공유하여 구현할 수 있는 장점이 있다.
	
	그럼에도 경우에 따라 입력  form을 사용자에게 보여줬을 때 최종 마지막에 입력했던 데이터들이
	form에 나타나서 불편한 경우가 있다.
	
	이러한 현상을 방지하기 위해 form에 입력되었던 데이터를 데이터 사용이 끝나면(insert, update 완료 후)
	SesseionStatus.setComplete() method를 호출하여 데이터를 clear 시켜주어야 한다.

*/ %>
<script src="${rootPath}/static/js/book-write.js?ver=2020-10-07-002"></script>
	<h3>도서 정보 입력</h3>
	<form:form id="books" modelAttribute="bookVO">
		<fieldset>
		<legend>도서 정보를 입력하세요</legend>
		
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
