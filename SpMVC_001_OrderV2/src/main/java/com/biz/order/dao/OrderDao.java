package com.biz.order.dao;
// package이름만 복사한 다음에 order-mapper.xml에 붙여넣기 해준다.
import java.util.List;

import com.biz.order.model.OrderVO;

public interface OrderDao {
	
	// 기본적으로 우리가 구현할 CRUD 5가지 method
	// Dao를 호출하는 것은 CRUD SQL을 호출하는 것
	public List<OrderVO> selectAll();
	
	public OrderVO findBySeq(long seq);
	public int insert(OrderVO orderVO);
	public int update(OrderVO orderVO);
	public int delete(long seq);

}
