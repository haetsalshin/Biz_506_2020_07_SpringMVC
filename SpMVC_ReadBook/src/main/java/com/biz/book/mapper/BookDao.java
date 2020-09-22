package com.biz.book.mapper;

import com.biz.book.model.BookVO;

/*
 * genericDao를 extends 함으로써 기본 CRUD method를
 * 별도로 정의하지 않아도 된다
 */
public interface BookDao extends GenericDao<BookVO, Integer>{
	
	
}
