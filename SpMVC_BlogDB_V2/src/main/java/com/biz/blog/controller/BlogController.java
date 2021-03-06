package com.biz.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.blog.model.BlogVO;
import com.biz.blog.service.BlogService;

import lombok.extern.slf4j.Slf4j;

// lombok을 사용하여 slf4j와 logback을 연동하고 log를 사용할 수 있도록 설정하라
@Slf4j
@RequestMapping(value = "blog")
@Controller
public class BlogController {

	// @Service Annotation이 부착된 클래스를 주입해 달라
	@Autowired
	@Qualifier("bServiceV2")
	private BlogService bService;

	// http://localhost:8080/blog/blog/list 주소로 req했을 때 응답할 함수
	// method=RequestMethod.GET : req 했을 때 <a href=주소>를 클릭 했을 때
	// 응답하는 요청 method
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<BlogVO> blogList = bService.selectAll();
		model.addAttribute("BLOGS", blogList);

		return "list";
	}

	// 매개변수로 Model 을 받는다
	// 한개의 데이터만 받는 method
	@RequestMapping(value = "/getblog", method = RequestMethod.GET)
	public String getBlog(Model model) {

		System.out.println("여기는 블로그 보기!!!");

		List<BlogVO> blogList = bService.selectAll();
		if (blogList != null) {
			int size = blogList.size();

			// size - 1 값이 0부터 시작하므로 전체 리스트에서 -1 을 해주어야
			// 가장 최근에 저장한 마지막 값이 저장되기 때문이다.
			model.addAttribute("TITLE", blogList.get(size - 1).getBl_title());
			model.addAttribute("CONTENT", blogList.get(size - 1).getBl_contents());
			model.addAttribute("USER", blogList.get(size - 1).getBl_user());
		} else {
			model.addAttribute("TITLE", "데이터가 없음");
		}
		return "view";
	}
	
	// 작성하기를 누르면 호출되는 곳
	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(Model model) {
		// write.jsp에서 input Post로 데이터를 전달할 때
		// 비어있는 ("")데이터 때문에 발생하는 400 오류를 방지하기 위해
		// 공백의 새로운 BlogVO만들어서 write.jsp로 보내준다.
		model.addAttribute("BLOG", new BlogVO());
		return "write";
	}

	/*
	 * @ModelAttribute form에서 input 에 입력한 문자열이 전송되어 오면 input tag의 변수(name)을 분석하여 VO
	 * class의 필드변수와 일치하면 전달된 데이터(값)을 VO객체에 담아달라
	 * 
	 * 저장하기를 누르면 호출되는 곳
	 * 
	 */
	@RequestMapping(value = "/input", method = RequestMethod.POST)
	// @ModelAttribute 에 의하여 우리가 입력하지 않아도 모든 값이 자동으로 입력된다.
	public String write(@ModelAttribute BlogVO blogVO, Model model) {
		/*
		 * Debuging Code : 어떤 값을 확인하는 용도 form에서 건너온 데이터가 정확히 VO에 담겼는가를 확인하기 위해 사용한 코드 이
		 * 코드는 프로젝트 수행과는 아무런 관련이 없는 코드
		 * 
		 * 이코드는 개발을 할 때는 필요하지만 만약에 사용자 비밀번호가 콘솔에 노출이 된다면 보안상 상당한 위험을 가질 것이다.
		 * 
		 * 우리가 프로젝트를 완성해서 서버에 보낸다(deploy 배포) 우리는 이 코드를 다 찾아서 제거하거나 주석처리 해야한다. 프로젝트 업데이트를
		 * 하면 또 작성을 하고 배포시 또 지우고... 해야한다... 이 디버깅 코드때문에 스트레스를 받음 ㅠ 다행히 spring에는 이것을 해결해줄
		 * 도구를 만들어뒀는데 이것이 바로 "log"
		 * 
		 * 
		 */

		log.debug("USER :" + blogVO.getBl_user());
		log.debug("TITLE :" + blogVO.getBl_title());
		log.debug("CONTENT :" + blogVO.getBl_contents());
		log.debug("로그인한 사용자는? " + "홍길동");
		log.debug("로그인한 비밀번호는? " + "1234");

		bService.insert(blogVO);

//		model.addAttribute("TITLE", blogVO.getBl_title());
//		model.addAttribute("USER", blogVO.getBl_user());
//		model.addAttribute("CONTENT", blogVO.getBl_contents());

		// 우리가 데이터를 입력한 후에 다시 리스트로 돌아가는 return문을 만들고 싶다.
		return "redirect:/blog/list";

	}

	/*
	 * @RequestMapping(value = "/writer1", method=RequestMethod.GET) public String
	 * write1() { // return이 null 값으면 writer1을 찾는다 return "null"; }
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(String seq, Model model) {

		// 목록에서 클릭한 게시글의 seq값을 console에 보여준다.
		log.debug("SEQ {}", seq);

		/*
		 * SQL Injectionr 공격을 사전에 차단하기 위해 Controller에서 SEQ값을 문자열형에서 Long형으로 전환하는 코드를 추가
		 */
		long long_seq = 0;
		try {
			long_seq = Long.valueOf(seq);
		} catch (Exception e) {
			model.addAttribute("ERROR", seq + " 형식의 QUERY금지!!!!");
			return "view_error";
		}
		BlogVO blogVO = bService.findBySeq(long_seq);
		model.addAttribute("BLOG", blogVO);
		return "view";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(String seq, Model model) {

		long long_seq = 0;
		try {
			long_seq = Long.valueOf(seq);
		} catch (Exception e) {
			return "view_error";
		}
		// update할 데이터를 SELECT 해 오기
		BlogVO blogVO = bService.findBySeq(long_seq);

		// updatge 할 데이터를 model에 싣기
		model.addAttribute("BLOG", blogVO);

		// 입력폼 화면 열기
		return "write";
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	public String update(BlogVO blogVO, Model model) {
		
		// log.debug할 때는 반드시 toString을 붙여줘야한다.
		log.debug("UPDATE POST Method");
		log.debug(blogVO.toString());
		
		bService.update(blogVO);
		model.addAttribute("seq", blogVO.getBl_seq());
		
		// 수정이 완료 되면 다시 detail_view로 화면을 전환하기
		
		// return " redirect:/blog/view?seq=" + blogVO.getBl_seq();
		// 이렇게 긴 코드를 아래와 같이만 적어도 똑같이 된다. 
		// 굳이 위처럼 길게 적을 필요는 없다.
		return "redirect:/blog/view";
	}
	
	
	

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(String seq) {
		long long_seq = 0;
		try {
			long_seq = Long.valueOf(seq);
		} catch (Exception e) {
			return "view_error";
		}
		bService.delete(long_seq);
		return "redirect:/blog/list";
	}

}
