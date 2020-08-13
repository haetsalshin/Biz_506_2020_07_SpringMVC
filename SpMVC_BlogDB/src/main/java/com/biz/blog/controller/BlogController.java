package com.biz.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.blog.model.BlogVO;
import com.biz.blog.service.BlogService;

@RequestMapping(value = "blog")
@Controller
public class BlogController {
	
	// @Service Annotation이 부착된 클래스를 주입해 달라
	@Autowired
	private BlogService bService;
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<BlogVO> blogList = bService.selectAll();
		model.addAttribute("BLOGS", blogList);
		
		
		
		return "list";
	}
	
	
	// 매개변수로 Model 을 받는다
	// 한개의 데이터만 받는 method
	@RequestMapping(value = "/getblog", method=RequestMethod.GET)
	public String getBlog(Model model) {
		
		System.out.println("여기는 블로그 보기!!!");
		
		List<BlogVO> blogList = bService.selectAll();
		if(blogList !=null) {
		int size =blogList.size();
		
		// size - 1 값이 0부터 시작하므로 전체 리스트에서 -1 을 해주어야
		// 가장 최근에 저장한 마지막 값이 저장되기 때문이다.
		model.addAttribute("TITLE", blogList.get(size - 1).getTitle());
		model.addAttribute("CONTENT", blogList.get(size - 1).getContent());
		model.addAttribute("USER", blogList.get(size - 1).getUser());
		} else {
			model.addAttribute("TITLE", "데이터가 없음");
		}
		return "view";
	}
	
	@RequestMapping(value = "/input", method=RequestMethod.GET)
	public String input() {
		
		
		return "write";
	}
	/*
	 *  @ModelAttribute
	 *  	form에서 input 에 입력한 문자열이
	 *  	전송되어 오면 input tag의 변수(name)을 분석하여
	 *  	VO class의 필드변수와 일치하면 전달된 
	 * 		데이터(값)을 VO객체에 담아달라
	 *  
	 */
	@RequestMapping(value = "/writer", method=RequestMethod.POST)
	public String write(@ModelAttribute BlogVO blogVO, Model model) {
		
		System.out.println("USER :" + blogVO.getUser());
		System.out.println("TITLE :" + blogVO.getTitle());
		System.out.println("CONTENT :" + blogVO.getContent());
		
		bService.insert(blogVO);
		
		
		model.addAttribute("TITLE",blogVO.getTitle());
		model.addAttribute("USER",blogVO.getUser());
		model.addAttribute("CONTENT",blogVO.getContent());
		
		// 우리가 데이터를 입력한 후에 다시 리스트로 돌아가는 return문을 만들고 싶다.
		return "redirect:/blog/list";
		
	}
	/*
	@RequestMapping(value = "/writer1", method=RequestMethod.GET)
	public String write1() {
		// return이 null 값으면 writer1을 찾는다
		return "null";
	}
	*/
	
	
}
