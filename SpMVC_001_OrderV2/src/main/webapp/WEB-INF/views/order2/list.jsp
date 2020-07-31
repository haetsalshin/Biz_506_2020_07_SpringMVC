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
	<%@ include file = "/WEB-INF/views/include/include-header.jspf" %>
	<%@ include file= "/WEB-INF/views/include/include-nav.jspf" %>
	<section>
	
	 <table >
        <thead>
        	<tr>
            <!-- thead 부분에 표의 제목이 들어갈 것임-->
	            <th>NO</th>
	            <th>주문번호</th>
	            <th>주문일자</th>
	            <th>고객번호</th>
	            <th>상품번호</th>
	            <th>상품이름</th>
	            <th>가격</th>
	            <th>수량</th>
	            <th>합계</th>
        	</tr>
        </thead>
        
      	 <%
        		for(int i = 0; i<10; i ++){
        			
        	%>
        	
        <!--<h6>배고프다</h6>  -->
        <%			
        		}
         %>
        
        
        
        
        <tbody>
        <!--  for(OrderVO vo:ORDERS) -->
        	<c:forEach items="${ORDERS}" var="vo" varStatus="index">
        	<tr>
        	<!-- 확장된 for 문
        		tr row을 10줄을 만들어라
        		varStatus="index" 일종의 변수. 
        		변수를 만들고 count라는 명령어를 쓰면 순번이 몇번째인지 보여준다.(1~10까지)
        		index.index 는 0~9까지
        	 -->
	            <td>${index.count}</td>
	            <td>
	            <!-- 주문번호 클릭하면 상세 주문 내역 정보 확인 할 수 있도록 -->
	            <a href = "order?seq=${vo.o_seq}">
	            ${vo.o_num}
	            </a>
	            
	            </td>
	            <td>${vo.o_date}</td>
	            <td>${vo.o_cnum}</td>
	            <td>${vo.o_pcode}</td>
	            <td>${vo.o_pname}</td>
	            <td>${vo.o_price}</td>
	            <td>${vo.o_qty}</td>
	            <td>${vo.o_price * vo.o_qty }</td>
			</tr>
			<!-- orderVO에 getter method가 없다 그래서 오류가 발생하는 것. 
				 원래 vo.getPrice();로 값을 불러들이는 것인데
				 vo라는 객체는 있지만 객체에 포함된 필드변수(price)가 없기 떄문에 발생하는 오류
				 웹에서 : javax.el.PropertyNotFoundException: 타입 [com.biz.order.model.OrderVO]에서
				 		 프로퍼티 [price]을(를) 찾을 수 없습니다.
			-->
			
			</c:forEach>
        </tbody>

    </table>
	
	</section>
	<hr/>
	<section id="buttons">
		<!-- 주문서작성 buttons을 누르면 해당 페이지로 가도록 먼저 a태그를 만들어준다 -->
		<!--  그러면 버튼 누르면 페이지가 http://localhost:8080/order/input  -->
		<!--  input.jsp를 만들어준다. -->
		<button id="order_input"><a href="input">주문서작성</a></button>
	
	
	</section>
	<hr/>
	<section>
		<p>EL 단일변수 : ${price}, 변수가 선언되지 않더라도 오류가 없다</p>
		<p>EL 객체변수 : ${vo.price}, vo 객체는 있는데 vo에 price필드변수의 getter, getPrice()가 없을 경우 exception이 발생
	</section>
	<%@ include file ="/WEB-INF/views/include/include-footer.jspf" %>
</body>

</html>
	