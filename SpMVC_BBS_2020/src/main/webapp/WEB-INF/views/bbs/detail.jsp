<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
      html,
      body {
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
      table.io-table th,
      table.io-table tr td {
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
    </style>
  </head>
  <body>
    <h3>Detail 보기📂</h3>

    <section>
    <
      <table class="io-table">
        <th>작성일자</th>
        <th>작성시각</th>
        <th>작성자</th>
        <th>제목</th>
        <th>조회수</th>
        <tr>
          <td>${BBSVO.b_date}</td>
          <td>${BBSVO.b_time}</td>
          <td>${BBSVO.b_writer}</td>
          <td>${BBSVO.b_subject}</td>
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
          <td><img src="${rootPath}/upload/${BBSVO.b_file}" width="200px"></td>
          
        </tr>
      </table>
      
    </section>
  </body>
</html>
