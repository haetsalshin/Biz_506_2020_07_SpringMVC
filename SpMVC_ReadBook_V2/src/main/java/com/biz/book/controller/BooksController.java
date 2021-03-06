package com.biz.book.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.book.mapper.BookDao;
import com.biz.book.mapper.ReadBookDao;
import com.biz.book.model.BookVO;
import com.biz.book.model.ReadBookVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * @Transactional Annotaion을 클래스 차원에서 설정하면
 * 현재 클래스의 모든 method에서 DB와 연동되는 부분이
 * 자동으로 Transactional이 적용된다.
 */
@RequiredArgsConstructor
@SessionAttributes("bookVO")
@Transactional
@Slf4j
@Controller
@RequestMapping(value = "/books")
public class BooksController {
	
	
	private final BookDao bookDao;
	private final ReadBookDao rbookDao;
	
	@ModelAttribute("bookVO")
	public BookVO newBookVO() {
		LocalDate localDate = LocalDate.now();
		String todayString = DateTimeFormatter
				.ofPattern("yyyy-MM-dd")
				.format(localDate);
		
		BookVO bookVO = BookVO.builder().buydate(todayString)
				.buyprice(10000)
				.build();
		
		return bookVO;
	}
	
	/*
	 * SqlSessionTemplate 대신
	 * DataSourceTransactionManager 이것을 mybatis-context.xml에 만들어주고
	 * appServlet/txt-context.xml을 만들고
	 * <tx:annotaion-driven/>을 설정하고
	 * transaction이 필요한 method에 @transactional Annotaion을 설정해주면
	 * 자동으로 기본 정책으로 Transaction이 수행된다.
	 */
	
	//ㅆ) localhost:8080/book/books
	// localhost:8080/book/books 슬래시를 넣었을때나 안넣었을 때 모두 화면을 출력하도록
	// {"/",""} 배열로 만들어준다
	//@ResponseBody : 객체를 그대로 json형태로 보내라
	//@ResponseBody	
	@RequestMapping(value = {"/",""}, method=RequestMethod.GET)
	public String list(Model model){
		
		List<BookVO> bookList = bookDao.selectAll();
		model.addAttribute("BOOKS",bookList);
		model.addAttribute("BODY", "BOOK-LIST");
		 return "home";
		
	}
	
	@RequestMapping(value="/input", method=RequestMethod.GET)
	public String input(@ModelAttribute("bookVO") BookVO bookVO, Model model) {


		
		model.addAttribute("BODY", "BOOK-WRITE");
		model.addAttribute("bookVO",bookVO);
		 return "home";
		
		// return null로 해두면 우리가 굳이 list.jsp파일을 따로 만들지 않아도
		// 자동으로 생성이 된다.
		//ㅆ) Controller의 Mapping method의 return type이 String일 때
		// null 값을 return하면 mapping method를 호출 할 떄
		// 사용했던 mapping URL.jsp 형식의 return문이 자동으로 생성된다.
		
	}
	/*
	 * spring form tag lib를 사용하여 write form을 만들었을 경우에는
	 * VO클래스, 객체를 매개변수로 사용할 때
	 * @ModelAttribute("VO")를 필수로 사용하자
	 */
	
	@RequestMapping(value = "/input",method=RequestMethod.POST)
	public String input(@ModelAttribute("bookVO") BookVO bookVO, SessionStatus status) {
		
		log.debug(bookVO.toString());
		
		int ret = bookDao.insert(bookVO);
		if(ret < 1) {
			// insert가 실패했으므로 그에 대한 메시지를 보여주는 페이지로 Jump
		}
		/*
		 * sessionAttributes를 Controller에 설정했을 경우
		 * 입력박스에 담긴 값을 POST받아 DB에 반영한 후에는
		 * 반드시 SessionStatus.setComplete() method를 호출해서
		 * session을 clear 해 주어야 한다.
		 * 그렇지 않으면 한번 입력한 내용이 계속해서 입력 창에 나타난다.
		 */
		status.setComplete();
		return "redirect:/books";
	}
	
	// localhost:8080/book/books/detail/3 이라고 request가 오면
	// 맨 끝에 숫자 3을 Mapping 주소의 {book_seq}위치에 Mapping 한다
	// 매개변수에 설정된 PathVariable에 따라 String id 변수에
	// 3의 값이 할당되어 method에 전달된다.
	
	@RequestMapping(value = "/detail/{book_seq}",method=RequestMethod.GET,
			produces = "application/json;charset=utf8")
	public String detail(@PathVariable("book_seq")
	String id, Model model) {
		
		log.debug("PATH : {}",id);
		long seq = Long.valueOf(id);
		BookVO bookVO = bookDao.findById(seq);
//		log.debug(bookVO.toString());
		
		model.addAttribute("BOOKVO", bookVO);
		
		
		// 09-28 추가
		LocalDateTime lDateTime = LocalDateTime.now();
		String lDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(lDateTime);
		String lTime = DateTimeFormatter.ofPattern("HH:MM:SS").format(lDateTime);
		
		// 09-28 추가
		ReadBookVO readBookVO = ReadBookVO.builder()
				.r_date(lDate)
				.r_stime(lTime)
				.build();
		
		List<ReadBookVO> readList = rbookDao.findByBSeq(seq);
		model.addAttribute("READ_BOOK", readList);
		
		
		// 09-28 추가
		model.addAttribute("readBookVO",readBookVO);
		
		
		model.addAttribute("BODY", "BOOK-DETAIL");
		return "home";
	}
	
	@RequestMapping(value = "/delete/{seq}",method=RequestMethod.GET)
	public String delete(@PathVariable("seq") String seq,
			@ModelAttribute("bookVO") BookVO bookVO) {
		
		long id = Long.valueOf(seq);
		
		bookDao.delete(id);
		
		return "redirect:/books";
	}
	
	@RequestMapping(value = "/update/{seq}",method=RequestMethod.GET)
	public String update(@PathVariable("seq") String seq,
			@ModelAttribute("bookVO") BookVO bookVO, Model model) {
		
		long id = Long.valueOf(seq);

		bookVO = bookDao.findById(id);
		model.addAttribute("bookVO", bookVO);
		model.addAttribute("BODY", "BOOK-WRITE");
		
		return "home";
	}
	
	@RequestMapping(value = "/update/{seq}",method=RequestMethod.POST)
	public String update(@PathVariable("seq") String seq,
			@ModelAttribute("bookVO") BookVO bookVO, Model model,SessionStatus status) {
		
		bookDao.update(bookVO);
		status.setComplete();
		return "redirect:/books/detail/" + seq;
	}
	
	
}
