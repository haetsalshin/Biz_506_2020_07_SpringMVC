package com.biz.shop.persistence;

import java.util.List;

public interface GenericDao<VO, PK> {
	
	public List<VO> selectAll();
	public VO findById(PK id); // ID = PK 라는 개념으로 생성하는 method
	
	public int insert(VO vo);
	public int update(VO vo);
	public int delete(PK id);

}
