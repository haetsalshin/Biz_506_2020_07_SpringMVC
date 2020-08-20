package com.biz.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.shop.model.ProductVO;
import com.biz.shop.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/product")
@Controller
public class ProductController {
	
	@Autowired
	@Qualifier("proServiceV1")
	private ProductService  proService;
	
	// 상품리스트보이기
	// http://localhost:8080/shop/product
	@RequestMapping(value = "/",method=RequestMethod.GET)
	public String proHome(Model model) {
		// 전체 상품 리스트를 select
		List<ProductVO> proList = proService.selectAll();
		
		// PRO_LIST에 담아서 home으로 보내기
		model.addAttribute("PRO_LIST",proList);
		model.addAttribute("BODY", "PRO_HOME");
		return "home";
	}
	
	
	// insert GET mothod : 상품정보 추가 anchor를 클릭 했을 때 
	// write 화면을 보여주는 method
	// <a href="http://localhost:8080/host/shop/product/list">상품등록</a> request 반응
	@RequestMapping(value = "/insert",method=RequestMethod.GET)
	public String insert(Model model) {
		// BODY 라는 변수를 만들고 pro-write라는 값을 만들어 둠
		model.addAttribute("BODY","PRO_WRITE");
		
		
		// 우리가 만든 jspf파일 경로		
		//return "product/product_write";
		return "home";
	}
	
	// form에서 값을 입력 한 후 저장버튼을 눌렀을 때 호출되는 method
	// <form method="POST">로 되어 있을 때 반응하는 method 
	@RequestMapping(value = "/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute ProductVO proVO) {
		
		log.debug("상품정보 입력 : {}", proVO.toString());
		int ret = proService.insert(proVO);
		
		return "redirect:/";
	}
	

}
