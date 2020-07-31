<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <!--  지금부터 core 라이브러리를 가자와서 사용하겠다
  근데 어떻게 적용? prefix="c"  이렇게 -->
  
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
    
    
    
<!DOCTYPE html>
<html>
<head>
	<!-- core(핵심) 라이브러리를 태그모양으로 쓰는 것
		set이라는 태그를 내가 하나 만들겠다. 근데 그 태그는 기본적로 html에 없는 태그
		그래서 core lib를 가져와서 이 set태그를 쓰겠다
		태그가 하는일 : korea라는 변수를 만들고 대한민국이라는 문자열을 담아서 초기화
		이 페이지 내에서 그럼  korea라는 변수를 사용해서 다양한 일을 할 수있다.
	 -->
	<c:set var="korea" value="대한민국" />
	<c:set var="num1" value="100"/>
	<c:set var="num2" value="200"/>
	<%@ include file = "/WEB-INF/views/include/include-head.jspf" %>
	
	<style>
		section{
		
			margin: 10px;
		
		}
		#buttons {
			padding: 10px;
			text-align: right;
		}
		
		#order_input{
			background-color: blue;
			color: white;
			padding: 5px;
			border: 0;
			border-bottom: 2px solid blue;
		}
		/* 어떤 tag에 마우스를 올렸을 때의 효과를 지정 */
		#order_input:hover{
			background-color: gray;
			color: black;
			border-bottom: 2px solid red;	
			
			
		
		}
		td a {
			color: black;
			cursor: pointer;
		}
	
	</style>
	</head>
<body>
	
	<h3>주문내역 상세보기</h3>
	<p>주문번호 : ${ORDER.o_num}</p>
	<p>고객번호 : ${ORDER.o_cnum}</p>
	<p>주문일자 : ${ORDER.o_date}</p>
	<p>상품코드 : ${ORDER.o_pcode}</p>
	<p>가격 : ${ORDER.o_price}</p>
	<p>수량 : ${ORDER.o_qty}</p>

</body>
</html>