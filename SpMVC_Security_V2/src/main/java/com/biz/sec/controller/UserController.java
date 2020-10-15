package com.biz.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping(value="/user")
@Controller
public class UserController {

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		
		return "user/join";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		
		return "user/login";
	}
	
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String mypage() {
		
		// internalview를 사용한 렌더링
		// return "users/mypage" >> internalView를 사용한 렌더링
		// return "mypage" >> tiles의 마이페이지 definition 찾아 렌더링
		return "user/mypage"; // >> tiles의 user/* 찾고, *대신 mypage 문자열 전달하여 렌더링
		
		// 1. tiles-layout.xml 에서 user/mypage 설정값 찾아보기
		// 2. tiles-layout.xml에서 user/* 설정값 찾아보기
		// 1번 또는 2번에서 해당 설정을 찾게 되면 template 로 설정된 layout.jsp를 열고 attribute로 설정된 jsp 파일들을 로딩하여
		// layout.jsp 에 설정된 대로 레이아웃을 만들고 HTML로 렌더링한 후 response
		
		// 3. /WEB-INF/views/user/mypage.jsp 파일 찾는다
		// 		파일 찾으면 internalview에게 보내어 렌더링 수행한 후 response
		
		// 1,2,3 모든 경우에 해당하지 않는경우 404 오류를 response 한다
		
		
	}
	
}
