<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${rootPath}/static/css/member-write.css?ver=2020-09-28-001" />

<script>
	// 함수를 변수처럼 선언하는 ES6 코드
	// 함수를 변수처럼 선언하면 = JS 객체화된다.
	// 함수 = 객체 = 변수
	// 1급 함수 : 함수자체를 다른 함수의 매개변수로 전달 할 수 있다.
	const id_check = function(username){
		
		if(username === ""){
			$("div#msg_username").css("display","block")
			$("div#msg_username span").css("color","red")
			$("div#msg_username span").text("USER NAME을 입력해 주세요")
			$("#username").focus()
			return false;
		}
		
		
		
		$.ajax({
            url : "${rootPath}/member/id_check",
            method : "POST",
            data : {"username" : username },
            beforeSend : function(ax){
               ax.setRequestHeader ("${_csrf.headerName}", "${_csrf.token}")
            }, 
            success : function(result){
            	$("div#msg_username").css("display","block")
               if(result === "OK"){
                  //alert("사용가능한 username입니다")
                  $("div#msg_username span").text("🆗사용 가능한 회원 ID 입니다🆗")
                  $("div#msg_username span").css("color","blue")
                  $("#password").focus()
               } else {
                  //alert("이미 등록된 username입니다")
                  $("div#msg_username span").text("❗사용 불가능한 회원 ID 입니다❗")
                  $("div#msg_username span").css("color","red")
                  $("#username").focus()
               }
               
            },
            error : function(){
               alert("서버 통신오류 입니다!")
            }
		 })
	}




   $(function(){
   
	   
	   
	   
	   $("#btn_save").click(function(){
		 let username = $("#username").val()
		 let password = $("#password").val()
		 let re_password = $("#re_password").val()
		 
		 
		 if(username === ""){
			 alert("사용자 이름을 입력한 후 ID 중복검사를 수행하세요")
			 $("#username").focus()
			 return false
		 }
		 if(password === ""){
			 alert("비밀번호를 입력해주세요")
			 $("#password").focus()
			 return false
		 }
		 if(re_password === ""){
			 alert("비밀번호 확인을 입력해 주세요")
			 $("#re_password").focus()
			 return false
		 }
		 if(password !== re_password){
			 alert("비밀번호와 비밀번호 확인란이 일치하지 않습니다")
			 $("#password").val("")
			 $("#re_password").val("")
			 $("#password").focus()
			 return false
			
		 }
		 $("form").submit()
		 
		 
	   })
	   /*
	   	input box에 focus()가 있다가 다른 곳으로 focus()가 이동할 때
	   	발생되는 event
	   	ID 중복 버튼을 클릭하지 않아도 ID 중복검사를 할 수 있도록
	   	username input box에 blur 이벤트를 설정
	   
	   */
	   $("#username").blur(function(){
		 let username = $("#username").val()
		 id_check(username)
	   })
	   
	   
      $("#m_username").click(function(){
         let username = $("#username").val()
         id_check(username)
         
      })
        
     
      
   })
   
   
</script>
<style>
<%/* Controller에서 @SessionAttrivutes()와 @ModelAttributes() 설정이 있고
	jsp에서 spring form taglib를 사용하면서
	form:form 에 있는 modelAttribute를 설정해 두면
	현재 이 JSP 는 username, password, re_password만 있지만
	memberVO만들어진 UserDetailsVO에 설정된 모든 변수가
	마치 input hidden으로 설정된 것과 같이 포함되어 있다.
*/%>


	div#msg_username {
		display: none;
	}
</style>
<form:form modelAttribute="memberVO" id="member-write">
	<fieldset>
		<legend>회원가입</legend>
		<div>
			<label>회원 ID</label>
			<form:input path="username" class="username" />
		</div>
		<div id="msg_username">
			<label></label>
			<span></span>
		</div>
		<div>
			<label>비밀번호</label>
			<form:input path="password" type="password" />
		</div>
		<div id="msg_password">
			<label></label>
			<span></span>
		</div>
		<div>
			<!-- 굳이 데이터베이스에 전송할 필요가 없기 때문에 이렇게 form tag로 감싸주지 않는다  -->
			<label>비밀번호 확인</label> <input name="re_password" id="re_password" type="password" />
		</div>

		<div id="btn_box">
			<button type="button" id="btn_home">홈으로</button>
			<button type="button" id="btn_save">다음으로</button>
		</div>
	</fieldset>
</form:form>