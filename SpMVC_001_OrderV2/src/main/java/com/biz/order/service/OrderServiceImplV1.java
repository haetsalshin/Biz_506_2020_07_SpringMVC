package com.biz.order.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.order.dao.OrderDao;
import com.biz.order.model.OrderVO;

//  No qualifying bean of type : 'com.biz.order.service.OrderService' 를 이용해서 bean으로 만들지못하겠다
// 		객체로 선언을 하지 못하겠다. 객체를 만들지도 않았는데 controller에서 호출을 하니 오류가 뜨는 것
//		그래서 controller또한 에러가 연달아 일어나는 것
@Service
public class OrderServiceImplV1 implements OrderService {
	
	// autowired 를 빼먹으면 getMapper에서 sqlsession이 값을 얻어들일 수 없으므로
	//null pointerException이 발생한다... 빼먹지 말 것
	@Autowired
	private SqlSession sqlSession;
	private OrderDao orderDao;
	/*
	 * 
	 * OrderServiceImpl 클래스를 객체로 생성하는 과정에서
	 * getMapper() method를 자동으로 호출하고
	 * orderDao를 초기화 하라
	 * 
	 * ServiceImpl 클래스를 객체로 생성할 때 호출되는 생성자는 
	 * 최초에 한번 Container에 등록 될 때 호출된다.
	 * 
	 * 이후에 다른 객체, 변수등을 초기화 하려면
	 * 별도의 method를 만든 후 @Autowired를 설정해주어야 한다.
	 * 
	 */
	
	@Autowired
	public void getMapper() {
		this.orderDao = sqlSession.getMapper(OrderDao.class);	
	}
	
	/*
	 * 현재는 단순하기 때문에 서비스가 하는 일이 없지만
	 * controller에서 온 데이터를 가공하여 dao로 보내고
	 * dao로 보내기 전에 또다시 
	 * */
	@Override
	public List<OrderVO> selectAll() {
		List<OrderVO> orderList = orderDao.selectAll();
		
		// Dao에서 받은 데이터가 있는지(정확)한지 확인하는 코드(Debugging)
		// 콘솔에 [OrderVO(o_seq=4, o_num=O00002, o_date=2020-07-22, o_cnum=C0033, o_pcode=P00001, o_pname=null, o_pric
		// 가 출력되는데
		// 우리가 @TOString을 만들었기 때문에
		// 이게 나온다.
		System.out.println("Controller에서 받은 데이터");
		System.out.println(orderList);
		return orderList;
	}
	/*
	 * seq 값을 파라메터로 받아서 OrderDao.findBySeq(seq)를 호출하고
	 * DB로부터 전달되어 온 Order 주문서 1개 레코드를 
	 * orderVO에 담고 호출한 곳으로 그대로 return하는 구조
	 */
	@Override
	public OrderVO findBySeq(long seq) {
		// DB에 저장된 seq중 값이 많은 seq = 21
		OrderVO orderVO = orderDao.findBySeq(seq);
		return orderVO;
	}

	@Override
	public int insert(OrderVO orderVO) {
		int ret = orderDao.insert(orderVO);
		return ret;
	}

	@Override
	public int update(OrderVO orderVO) {

		return 0;
	}

	@Override
	public int delete(long seq) {
		int ret = orderDao.delete(seq);
		
		return ret;
	}

}
