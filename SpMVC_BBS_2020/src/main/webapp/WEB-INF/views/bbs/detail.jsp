<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}

h3 {
	margin: 20px auto;
	text-align: center;
}

table.io-table {
	align-items: center;
	padding: 10px;
	margin: 20px auto;
	width: 70%;
	border-top: 1px solid black;
	border-collapse: collapse;
}

table.io-table th, table.io-table tr td {
	border-bottom: 1px solid black;
	text-align: center;
}

table.io-table th {
	padding: 10px;
	background-color: orange;
}

table.io-table tr td {
	padding: 7px;
}

section#bbs-button-box {
	width: 50%;
	margin: 10px auto;
	padding: 6px 12px;
	text-align: right;
}

section#bbs-button-box button {
	margin: 5px;
	padding: 8px 12px;
	border: 0;
	outline: 0;
	border-radius: 5px;
	font-weight: bold;
}

section#bbs-button-box button:hover {
	box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.6);
}

section#bbs-button-box button:nth-child(1) {
	background-color: white;
	border: 1px solid black;
}

section#bbs-button-box button:nth-child(2) {
	background-color: white;
	border: 1px solid black;
}

section#bbs-button-box button:nth-child(3) {
	background-color: red;
	color: white;
}
</style>
<script>
   document.addEventListener("DOMContentLoaded", function() {
      document.querySelector("section#bbs-button-box").addEventListener("click",function(e){
         
         let url = "${rootPath}/bbs/${BBSVO.b_seq}/"
         
         if(e.target.tagName ===('BUTTON')){
            
            // 게시글 삭제를 요청하면(삭제버튼 클릭)
            // ajax를 통해 서버에 delete method타입으로 삭제를 요청한다. 
            if(e.target.className == "delete"){
               if(confirm("정말 삭제할까요?")){
                  
                  let data = {seq:"${BBSVO.b_seq}",
                		  subject : "${BBSVO.b_subject}"
                		
                  }
                  // fetch를 통해서 데이터를 순수하게 못보내기 때문에 
                  // JSON형태로 만들어서 데이터를 문자열화 만들어서 
                  // controller로 보내면
                  // controller가 requestBody가 이를 받아서 다시 원래데이터로 바꾼다
                  fetch("${rootPath}/api/bbs",
                		  { method : "PUT",
                	  		headers : {
                	  			"Content-Type" : "application/json"
                	  		},
                	  		 //JSON 객체 데이터를 문자열화 하여 HTTP BODY 담기
                	  		body : JSON.stringify(data)
                		  }
                  )
                  .then(function(result){
                	  alert("성공")
                  })
                  .catch(function(error){
                	  alert("실패")
                  })
                  return false;
               }
            }
            document.location.href=url + e.target.className
         }
      })
   })
</script>
</head>
<body>
	<h3>${BBSVO.b_subject}📂</h3>

	<section>

		<table class="io-table">
			<th>작성일자</th>
			<th>작성시각</th>
			<th>작성자</th>
			<th>조회수</th>
			<tr>
				<td>${BBSVO.b_date}</td>
				<td>${BBSVO.b_time}</td>
				<td>${BBSVO.b_writer}</td>
				<td>${BBSVO.b_count}</td>
			</tr>
		</table>
		<table class="io-table">
			<th colspan="3">내용</th>
			<th></th>
			<th></th>
			<th>사진</th>

			<tr>
				<td colspan="3">${BBSVO.b_content}</td>
				<td></td>
				<td></td>
				<td><c:if test="${empty BBSVO.b_file}">
						<img src="${rootPath}/static/files/noimage.png" width="200px">
					</c:if> <c:if test="${not empty BBSVO.b_file}">
						<img src="${rootPath}/upload/${BBSVO.b_file}" width="200px">
					</c:if></td>

			</tr>
		</table>

	</section>
	<section id="bbs-button-box">
		<button class="list">리스트</button>
		<button class="update">수정</button>
		<button class="delete">삭제</button>

	</section>
	<style>
section#images-box {
	height: 50%;
	margin: 10px auto;
	padding: 5px;
}

section#images-box img {
	margin: 3px;
	border-radius: 20px;
}
</style>
	<section id="images-box">
		<c:if test="${not empty BBSVO.images}">
			<c:forEach items="${BBSVO.images}" var="image">
				<img src="${rootPath}/upload/${image.i_file_name}" width="100px">
			</c:forEach>
		</c:if>
	</section>
</body>
</html>
