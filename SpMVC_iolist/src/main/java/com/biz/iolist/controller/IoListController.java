package com.biz.iolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.iolist.model.IoListVO;
import com.biz.iolist.service.IoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/")
public class IoListController {

	@Autowired
	@Qualifier("service")
	private IoService ioService;
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String list(Model model) {
		
		
		
		List<IoListVO> ioList = ioService.selectAll();
		
		
		model.addAttribute("LISTS", ioList);
		model.addAttribute("BODY", "IO-LIST");
		
		return "/home";
	}
	
	@RequestMapping(value="/input", method=RequestMethod.GET)
	public String input(Model model) {
		
		model.addAttribute("BODY","IO-WRITE");
		model.addAttribute("IoListVO",new IoListVO());

		
		return "/io/io-write";
		
	}
	
	@RequestMapping(value = "/input", method=RequestMethod.POST)
	public String input(@ModelAttribute("IoListVO") IoListVO ioListVO) {
		
		log.debug(ioListVO.toString());
		
		IoListVO ioListVO1 = new IoListVO();
		
		/*
		 * //BookVO bookVO = BookVO.builder().buydate(todayString).build(); int ioTotal
		 * = ioListVO1.getIo_price() * ioListVO1.getIo_quan();
		 * 
		 * ioListVO1 = IoListVO.builder().io_total(ioTotal).build();
		 */
		
		int ret = ioService.insert(ioListVO);
		
		if(ret < 1) {
			log.debug("insert되지 않았습니다");
		}
		return "redirect:/";
	}
	
	
	
}
