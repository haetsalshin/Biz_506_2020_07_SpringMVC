<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&display=swap" rel="stylesheet">
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
<style>
	*{
	font-family: 'East Sea Dokdo', cursive;
	font-size: 30px;
	
	}
	#button{
		padding: 12px 36px;
		margin: 10px 0;
		text-decoration: none;
		text-transform: uppercase;
		border-radius: 40px;
		background: linear-gradient(90deg,#0162c8,#55e7fc);
		border: none;
		overflow: hidden;
	}
	span{
		position: absolute;
		background: #fff;
		transform: translate(-50%, -50%);
		pointer-events: none;
		border-radius: 50%;
		animation: animate 1s linear infinite;
	}
	@keyframes animate
	{
		0%{
			width: 0px;
			height: 0px;
			opacity: 0.5;
		}
		100%{
			width: 500px;
			height: 500px;
			opacity:0;
		}
	}
</style>
<script type="text/javascript">
const buttons = document.querySelectorAll('button');
buttons.forEach(btn =>{
	btn.addEventListener('click',function(e){
		let x = e.clientX - e.target.offsetLeft;
		let y = e.clientY - e.target.offsetTop;
		
		let ripples = document.createElement('span');
		ribbles.style.left = x + 'px';
		ribbles.style.left = x + 'px';
		this.appendChild(ripples);
		
		setTimeOut(()=>{
			ripples.remove()
		},1000);
	})
})
</script>
</head>
<body>
	<h3>나의 홈페이지 방문을 환영합니다</h3>
<form method="POST" enctype="multipart/form-data">
	<div>
		<label>보내는 Email</label>
		<input name="from_email" value="${EMS.from_email}"/>
	</div>
	<div>
		<label>받는 Email</label>
		<input name="to_email" value="${EMS.to_email}"/>
	</div>
	<div>
		<label>보내는 날짜</label>
		<input type="date" name="s_date" value="${EMS.s_date}"/>
	</div>
	<div>
		<label>보내는 시각</label>
		<input type="time" name="s_time" value="${EMS.s_time}"/>
	
	</div>
	<div>
		<label>제목</label>
		<input name="s_subject" value="${EMS.s_subject}"/>
	</div>
	<div>
		<label>내용</label>
		<textarea name="s_content">${EMS.s_content}</textarea>
	</div>
	<div>
		<label>첨부파일1</label>
		<input type="file" name="file1"/>
	</div>
	<div>
		<img src="${rootPath}/files/${EMS.s_file1}" width="100px">
	</div>
		<div>
		<label>첨부파일2</label>
		<input type="file" name="file2"/>
	</div>
	<div>
		<img src="${rootPath}/files/${EMS.s_file2}" width="100px">
	</div>
	<div >
		<button id="button">저장</button>
	</div>
</form>
</body>
</html>