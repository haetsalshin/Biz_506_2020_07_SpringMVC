<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>

<title>나의 홈페이지</title>
<script src="${rootPath}/static/js/io-detail.js?ver=2020-10-01-001"></script>
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

table#io-table {
	align-items: center;
	padding: 10px;
	margin: 20px auto;
	width: 80%;
	border-top: 1px solid black;
	border-collapse: collapse;
}

table#io-table th, table#io-table tr td {
	border-bottom: 1px solid black;
	text-align: center;
}

table#io-table th {
	padding: 14px;
	background-color: palegoldenrod;
}

table#io-table tr td {
	padding: 7px;
	cursor: pointer;
}

table#io-table tr td:nth-child(1) {
	width: 8px;
}

table#io-table tr:nth-child(even) {
	background-color: #f1f1f1;
}

table#io-table tr:nth-child(odd) {
	background-color: #fff;
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
}
</style>
</head>
<body>
	<section>
		<h3>💥 매입 매출 리스트 💥</h3>
		<table id="io-table">
			<th>SEQ</th>
			<th>일자</th>
			<th>시각</th>
			<th>상품명</th>
			<th>매입단가</th>
			<th>판매단가</th>
			<th>수량</th>
			<th>매입합계</th>
			<th>판매합계</th>


			<c:forEach items="${LISTS}" var="lists" varStatus="i">

				<c:choose>
					<c:when test="${lists.io_input =='1'}">
						<tr>
							<td>${i.count}</td>
							<td>${lists.io_date}</td>
							<td>${lists.io_time}</td>
							<td class="io-title" data-seq="${lists.seq}">${lists.io_pname}</td>
							<td>${lists.io_price}</td>
							<td></td>
							<td>${lists.io_quan}</td>
							<td>${lists.io_total}</td>
							<td></td>
						</tr>

					</c:when>
					<c:otherwise>
						<tr>
							<td>${i.count}</td>
							<td>${lists.io_date}</td>
							<td>${lists.io_time}</td>
							<td class="io-title" data-seq="${lists.seq}">${lists.io_pname}</td>
							<td></td>
							<td>${lists.io_price}</td>
							<td>${lists.io_quan}</td>
							<td></td>
							<td>${lists.io_total}</td>

						</tr>
					</c:otherwise>

				</c:choose>
				<c:if test="${i.last}">
					<tr>
						<td colspan="7">
						<td>${lists.input_total}</td>
						<td>${lists.out_total}</td>
					</tr>

				</c:if>


			</c:forEach>
		</table>
		<div>
			<button id="save-btn">
				<a href="${rootPath}/input">새로작성</a>
			</button>
		</div>

	</section>
</body>
</html>