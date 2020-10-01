<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
	<style>
	table#io-detail{
		width: 30%;
		margin: 20px auto;
	}
	
	table#io-detail .title td{
		padding: 0.5rem 1rem;
		background-color: #ECF6CE;
		border-bottom: 1px solid #ddd;
		text-align: center;
	}
	
	table#io-detail .info td:first-child{
		display: flex;
		justify-content: center;
		align-items: center;
		padding: 10px;
		
	}
	
	table#io-detail table th{
		color: #FF4000;
		padding: 8px 12px;
		text-align: right;
		border-bottom: 1px solid #ddd;
	}
	table#io-detail table td{
		border-bottom: 1px solid #ddd;
	}
	
	table#io-detail .dummy{
		padding: 30px;
	}
	
	table#io-detail .desc-title th{
		text-align: left;
		padding-left: 10px 30px;
		color: black;
		background-color: #ECF6CE;
		text-align: center;
	
	}
	
	table#io-detail .desc td{
		padding: 15px;
	}
	
	
	table#io-detail .link th,
	table#io-detail .desc-title td{
		background-color: #ECF6CE;
		padding: 10px;
		
	}
	
	section#read-book-body{
		width: 60%;
		border: 1px solid #green;
		margin: 5px auto;
			
	}
	#view_menu{
		width: 90%;
		margin: 10px;
		text-align: center;
	
	
	}
	
	#view_menu a {
		display: inline-block;
		padding: 8px 16px;
		margin: 8px 4px;
		text-decoration: none;
		background-color: #003399;
		color: white;
		border-radius: 5px;
		
	}
	#view_menu a:nth-child(1){
		background-color: #F7D358;
	}
	
	#view_menu a:nth-child(2){
		background-color: #FA58AC;
	}
	
	#view_menu a:hover{
		background-color: #ddd;
		color: black;
	}
	
	
	</style>
	<script>
	$(function(){
		$("#pro_delete").click(function(){
			if(confirm("${IoListVO.io_pname} \n상품데이터를 삭제합니다.")){
	
				document.location.replace("${rootPath}/delete?seq=${IoListVO.seq}");
			}
			
		})
		

	})
	</script>
	
	
	<table id="io-detail">
	<tr class="title"><td colspan="2"><h3>${IoListVO.io_pname}</h3> </td></tr>
	<tr class="info">
		
		<td>
			<table >
				<tr class="author"><th>SEQ</th><td>${IoListVO.seq }</td></tr>
				<tr class="author"><th>일자</th><td>${IoListVO.io_date }</td></tr>	
				<tr class="price"><th>시각</th><td>${IoListVO.io_time}</td></tr>
				<tr class="pub"><th>단가</th><td>${IoListVO.io_price}</td></tr>
				<tr class="pub"><th>수량</th><td>${IoListVO.io_quan}</td></tr>
				<tr class="pub"><th>합계</th><td>${IoListVO.io_total}</td></tr>		
			
			</table>
		</td>
	</tr>
	
	</table>
	<section id="view_menu">
	<a href="${rootPath}/update?id=${IoListVO.seq}">수정</a>
	<a href="${rootPath}/delete?seq=${IoListVO.seq}">삭제</a>
	</section>
	
	
	