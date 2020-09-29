<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
      <legend>📄 매입매출 입력 📄</legend>
      <div>
        <label><input type="date" /></label>
      </div>
      <div>
        <label><input type="time" placeholder="시각" /></label>
      </div>
      <div>
        <label><input placeholder="상품명" /></label>
      </div>
      <secton id="section-inout">
        <div>
          <label class="io"
            ><input type="radio" name="inout" value="1" />매입</label
          >
        </div>
        <div>
          <label class="io"
            ><input type="radio" name="inout" value="2" />매출</label
          >
        </div>
      </secton>
      <div>
        <label><input placeholder="단가" /></label>
      </div>
      <div>
        <label><input type="number" placeholder="수량" /></label>
      </div>
    </section>
    <section id="section-btn">
      <div>
        <button>처음으로</button>
        <button>입력완료</button>
      </div>
    </section>
