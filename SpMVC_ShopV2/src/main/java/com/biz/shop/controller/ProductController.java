package com.biz.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/product")
@Controller
public class ProductController {
	
	@RequestMapping(value = "/insert",method=RequestMethod.GET)
	public String insert(Model model) {
		// BODY 라는 변수를 만들고 pro-write라는 값을 만들어 둠
		model.addAttribute("BODY","PRO_WRITE");
		
		
		
		
		// 우리가 만든 jspf파일 경로		
		//return "product/product_write";
		return "home";
	}
	

}
