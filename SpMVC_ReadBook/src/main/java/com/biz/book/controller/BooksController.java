package com.biz.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.book.mapper.BookDao;
import com.biz.book.model.BookVO;

@Controller
@RequestMapping(value = "/books")
public class BooksController {
	
	// service를 거치지 않고 바로 handling하도록
	@Autowired
	private BookDao bookDao;
	
	//ㅆ) localhost:8080/book/books
	// localhost:8080/book/books 슬래시를 넣었을때나 안넣었을 때 모두 화면을 출력하도록
	// {"/",""} 배열로 만들어준다
	//@ResponseBody : 객체를 그대로 json형태로 보내라
	//@ResponseBody
	@RequestMapping(value = {"/",""}, method=RequestMethod.GET)
	public String list(Model model){
		
		List<BookVO> bookList = bookDao.selectAll();
		model.addAttribute("BOOKS",bookList);
		 return "books/list";
		
	}
	
	@RequestMapping(value="/input", method=RequestMethod.GET)
	public String input() {
		
		return "books/write"
		
		// return null로 해두면 우리가 굳이 list.jsp파일을 따로 만들지 않아도
		// 자동으로 생성이 된다.
		//ㅆ) Controller의 Mapping method의 return type이 String일 때
		// null 값을 return하면 mapping method를 호출 할 떄
		// 사용했던 mapping URL.jsp 형식의 return문이 자동으로 생성된다.
		
		;
	}
	
	
}
