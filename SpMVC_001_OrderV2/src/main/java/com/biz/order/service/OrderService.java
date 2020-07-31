package com.biz.order.service;

import java.util.List;

import com.biz.order.model.OrderVO;

public interface OrderService {
	// 기본적으로 OrderDao랑 비슷하다.
	// 일단 OrderDao 복사 붙여넣기
	public List<OrderVO> selectAll();
	

	public OrderVO findBySeq(long seq);
	public int insert(OrderVO orderVO);
	public int update(OrderVO orderVO);
	public int delete(long seq);

	

}
