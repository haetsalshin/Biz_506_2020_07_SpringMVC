package com.biz.shop.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.shop.model.ProductVO;

public interface ProductDao extends GenericDao<ProductVO, String> {

	
	@Select(" SELECT * FROM tbl_product")
	@Override
	public List<ProductVO> selectAll();

	public List<ProductVO> FindByTitle(String title);

	/*
	 * myBatis 3.4.x 이상에서 사용하는 새로운 SQL Mapper방식
	 * mapper.xml 파일을 작성하지 않고, 직접 Dao 인터페이스에 SQL을
	 * 구현하는 방법
	 */
	@Select("SELECT MAX(p_code) FROM tbl_product")
	public String maxPCode();

	//RPAD(#{id},6,' ') : id를 쓰고 총 6자리로 만들어주고 나머지 공백은 빈칸으로 넣어라
	@Select(" SELECT * FROM tbl_product WHERE p_code= RPAD(#{id},6,' ')")
	@Override
	public ProductVO findById(String id);

}
