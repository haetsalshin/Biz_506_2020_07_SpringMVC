<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>

<c:set var="korea" value="대한민국" />
<c:set var="num1" value="100" />
<c:set var="num2" value="200" />

<%@ include file="/WEB-INF/views/include/include-head.jspf"%>

</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf"%>

	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>

	<section>


		<h3>주문내역 상세보기</h3>
		<p>SEQ : ${ORDER.o_seq}</p>
		
		<p>주문번호 : ${ORDER.o_num}</p>
		<p>고객번호: ${ORDER.o_cnum}</p>
		<p>주문일자: ${ORDER.o_date}</p>
		<p>상품코드: ${ORDER.o_pcode}</p>
		<p>가격: ${ORDER.o_price}</p>
		<p>수량: ${ORDER.o_qty}</p>


	</section>

	<hr />

	<section id="buttons">

		<button id="order_input">
			<a href="delete?seq=${ORDER.o_seq}">주문서삭제</a>

		</button>

	</section>




	<%@ include file="/WEB-INF/views/include/include-footer.jspf"%>



</body>

</html>