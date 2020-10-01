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
    <script src="${rootPath}/static/js/io-detail.js?ver=2020-10-01-001"> </script>
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
      table#io-table {
        align-items: center;
        padding: 10px;
        margin: 20px auto;
        width: 80%;
        border-top: 1px solid black;
        border-collapse: collapse;
      }
      table#io-table th,
      table#io-table tr td {
        border-bottom: 1px solid black;
        text-align: center;
      }
      table#io-table th {
        padding: 14px;
        background-color: palegoldenrod;
      }
      table#io-table tr td {
      cursor: pointer;
        padding: 7px;
      }
      table#io-table tr td:nth-child(1) {
        width: 8px;
      }
    
      h3 {
        text-align: center;
        margin: 40px auto;
        font-style: italic;
      }

      #save-btn {
        display: flex;
        margin: 10px auto;
        border: none;
      }

      #save-btn a {
        display: inline-block;

        text-decoration: none;
        padding: 10px 15px;
        background-color: orange;
        color: white;

        border-radius: 5px;
      }
      #save-btn:hover a {
        background-color: red;
        text-decoration: inherit;
        cursor: pointer;
      }
    </style>
    <script>
    	$(function(){
    		$(".io_item").clcik(function(){
    			
    			const seq = $(this).children().eq(0).text()
    			
    			const seq1 = $(this).data("seq")
    			
    			document.location.href="${rootPath}/detail?id=" +seq
    		})
    	})
    
    </script>
  </head>
  <body>
    <section>
		<h3>💥 매입 매출 리스트 💥</h3>
		<table id="io-table">
			<th>SEQ</th>
			<th>일자</th>
			<th>시각</th>
			<th>상품명</th>
			<th>구분</th>
			<th>단가</th>
			<th>수량</th>
			<th>합계</th>

			<c:choose>
				<c:when test="${empty LISTS}">
					<tr>
						<td colspan="8">데이터가 없음</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${LISTS}" var="lists" varStatus="i">
						<tr class="io_item">
							<td>${i.count}</td>
							<td>${lists.io_date}</td>
							<td>${lists.io_time}</td>
							<td>${lists.io_pname}</td>
							<td>${lists.io_input}</td>
							<td>${lists.io_price}</td>
							<td>${lists.io_quan}</td>
							<td>${lists.io_total}</td>
						</tr>
	
					</c:forEach>
				

				</c:otherwise>

			</c:choose>

		</table>
 	<div>
      <button id="save-btn"><a>새로작성</a></button>
    </div>

	</section>
  </body>
</html>
