<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri ="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    

<style>
      * {
        box-sizing: border-box;
        padding: 0;
        margin: 0;
      }
      body,
      html { 
        padding: 20px;
        margin: 0 auto;
        width: 60%;
      }
      section#section-write {
        background-color: rgb(245, 219, 227);
        padding: 20px;
      }
      legend {
        background-color: indianred;
        color: white;
        padding: 8px;
        margin: 8px auto;
        text-align: center;
      }
      label {
      }
      .io {
        display: inline-block;
        margin: 0;
      }
      input {
        width: 90%;
        padding: 10px;
        margin: 2px auto;
      }
      section#section-btn {
        display: inline-block;
      }
      section#section-btn button {
        background-color: indianred;
        border: none;
        color: white;
        border-radius: 5px;
        align-items: right;
        padding: 5px 8px;
        margin: 8px auto;
      }
    </style>
    
<!-- </head>
<body> -->

	 <section id="section-write">
	 <form:form modelAttribute="IoListVO">
      <legend>📄 매입매출 입력 📄</legend>
      <div>
      <p>일자
        <label><form:input path="io_date" placeholder="날짜"/></label>
      </div>
      <div>
      <p>시각
        <label><form:input path="io_time" placeholder="시각" /></label>
      </div>
      <div>
      <p>상품명
        <label><form:input path="io_pname" placeholder="상품명" /></label>
      </div>

      <div>
      <p>매입/매출 구분
      	<lable><form:input path="io_input" placeholder="매입:1,매출:2"/></lable>
      </div>
      <div>
      <p>단가
        <label><form:input path="io_price" placeholder="단가"/></label>
      </div>
      <div>
      <p>수량
        <label><form:input path="io_quan" placeholder="수량" /></label>
      </div>
      <p>합계
        <label><form:input path="io_total" placeholder="합계"/></label>
      </div>
    </section>
    
    <section id="section-btn">
      <div>
        <button>처음으로</button>
        <button type="submit">입력완료</button>
      </div>
    </section>
</form:form>