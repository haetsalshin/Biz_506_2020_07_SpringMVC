package com.biz.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.order.model.OrderVO;
import com.biz.order.service.OrderService;

@Controller
public class OrderController {
	/*
	 * OrderService interface를 이용하여 oService를 선언하고
	 * 이미 생성되어  Container에 보관 중인 OrderServiceImplV1 객체를
	 * oService에 주입하라(Dependency Inject)
	 * 
	 * 결국 oService 객체를 통하여 method를 호출할 준비가 된다.
	 */
	
	@Autowired
	private OrderService oService;
	/*
	 * 주문서 전체리스트 DB로부터 가져와서 보여주는 Controller
	 * 1. Service에서 전체리스트를 요청
	 * 2. Service는 Dao에게 전체리스트를 요청
	 */
	@RequestMapping(value="/list")
	public String orderList(Model model) {
		List<OrderVO> orderList = oService.selectAll();
		model.addAttribute("ORDERS", orderList);
		return "order/list";
		
	}
	// a href ="input"로 설정된 링크를 클릭 했을 때 응답할 method
	@RequestMapping(value="/input", method=RequestMethod.GET)
	public String input() {
		
		return "order/input";
	}
	
	
	// input form에서 action으로 지정하여 호출할 method
	@RequestMapping(value="/input", method=RequestMethod.POST)
	public String input(@ModelAttribute OrderVO orderVO, Model model) {

		
		System.out.println(orderVO);
		int ret = oService.insert(orderVO);
		return "redirect:/list";
	}
	
	
	
	/*
	 * browser에서 order requset를 실행 할 때 (요청을 할 떄 )
	 * ?변수1= 값&변수2=값&변수3= 값 형식으로 query를 나열하여 요청을 할 때
	 * Controller의 method에 변수1, 변수2, 변수3의 매개변수를 마련해 두면
	 * query에 담겨서 전달 된 값을 method에서 수신하여 연산에 사용할 수 있다.
	 */
	@RequestMapping(value="/order")
	public String getOrder(String seq, String name, Model model) {
		// 변수만 설정해두면 http://localhost:8080/order/order?seq=1&name=홍길동
		// 치면 이렇게 할 수있다.
		System.out.println("SEQ 값 :"+ seq);
		System.out.println("NAME 값 :"  + name);

		long longSeq = 0;
		try {
			longSeq = Long.valueOf(seq);
		} catch (Exception e) {
			System.out.println("SEQ값을 받지 못함. 잘못된 SEQ를 받음");
			// TODO: handle exception
		}
		
		
		// service에 seq(21)을 전달하고 
		// 데이터 레코드를 SELECT 한 결과를 받아서 
		// orderVO에 담는다
		OrderVO orderVO = oService.findBySeq(longSeq);
		/*
		 * view(*.jsp)파일에 전달하여 Rendering을 수행할 수 있도록
		 * model 객체에 orderVO 데이터를 추가해 놓는다.
		 */
		model.addAttribute("ORDER", orderVO);
		
		/*
		 * Dispatcher야 /WEB-INF/views/order.view.jsp 파일을 읽어서
		 * 클라이언트(요청한 곳)으로 응답을 하여라
		 * 이 때 model에 담겨있는 데이터를 Rendering 할 때 사용하라 
		 */
		return "order/detail_view";
		
		
	}
	/*
	 * 매개변수는 달라도 method가 같은게 두개 있으면 이런 에러가 뜬다
	 *  
	 * java.lang.IllegalStateException: Ambiguous mapping. Cannot map 'orderController' method 
com.biz.order.controller.OrderController#insert(String, String, String, String, String, String, Model)
to { /insert}: There is already 'orderController' bean method
com.biz.order.controller.OrderController#insert(String, Model) mapped.

	
method=RequestMethod.POST (주소값을 POST)로 한 건 이 method를 사용하게 되고
method=RequestMethod.GET ( get 주소값을 입력하면 ) 이 method를 사용하게 된다.

	 * */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute OrderVO orderVO, Model model) {
		//@ModelAttribute OrderVO orderVO : 이렇게만 써주면 하단의 String o_num, String o_date...
		//이런식으로 모두 적어줘야 하는데 modelAttribute 어노테이션이 이러한 역할을 해준다.
		// 그래서 코드가 매우 단순해진다.
		// 그리고 setting까지 다 해준다.
		// 코드가 매우 짧아짐. 굿
		
		// 칼럼 , vo, 이름 같게 해주기
		
		System.out.println(orderVO);
		int ret = oService.insert(orderVO);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(String o_num,String o_date,String o_cnum,String o_pcode,
			String o_price,String o_qty, Model model) {
		
		System.out.println("주문번호 :" + o_num);
		System.out.println("날짜 :" + o_date);
		System.out.println("고객번호 :" + o_cnum);
		
		OrderVO orderVO = new OrderVO();
		
		orderVO.setO_num(o_num);
		orderVO.setO_date(o_date);
		orderVO.setO_cnum(o_cnum);
		orderVO.setO_pcode(o_pcode);
		
		try {
			orderVO.setO_price(Integer.valueOf(o_price));
			orderVO.setO_qty(Integer.valueOf(o_qty));
			orderVO.setO_total(orderVO.getO_price() * orderVO.getO_qty());
		} catch (Exception e) {
			
			System.out.println("숫자변환 오류");
		}
		int ret = oService.insert(orderVO);
		
		// redirect :  우리가 주문서 작성까지 완료 되면 다시 list로 돌아가게 하는 것
		/*
		 * web Browser 의 주소창에 .../order/list라고 입력한 후 Enter를 누른 것과 같이
		 * 명령을 수행하라고 web browser에게 알려주기
		 */
		return "redirect:/list";
		
		
	}
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(String seq) {
		long longSeq = 0;
		try {
			longSeq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
		}
		int ret = oService.delete(longSeq);
		
		return "redirect:/list";
	}
	
	
	
	
}
