package com.biz.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.shop.model.ProductVO;
import com.biz.shop.persistence.ProductDao;
import com.biz.shop.service.ProductService;

import lombok.extern.slf4j.Slf4j;

// debug사용하기 위하여 annotation 
//generic을 이용한 공통된 interface만들기
@Slf4j
@Service(value = "proServiceV1")
public class ProductServiceImplV1 implements ProductService {

	@Autowired
	private ProductDao proDao;
	
	@Override
	public List<ProductVO> selectAll() {
		return proDao.selectAll();
		
	}

	@Override
	public ProductVO findById(String id) {
		
		return proDao.findById(id);
	}

	@Override
	public int insert(ProductVO vo) {
		vo.setP_image("이미지없음");
		int ret = proDao.insert(vo);
		
		if(ret > 0) {
			log.debug("INSERT 성공 {}개 데이터 추가", ret);
		}else {
			log.debug("INSERT 실패 {} :/", ret);
		}
		
		return ret;
	}

	@Override
	public int update(ProductVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductVO> findByTitle(String title) {
		return null;
	}

	// 상품코드를 생성하는 코드 만들기
	@Override
	public String getPCode() {
		
		/*
		 * tbl_product table에서 상품코드를 조회하는데
		 * 가장 큰 값의 코드를 가져와라
		 * P0001, p0002, P0010 이런 코드가 있다고 가정하면
		 * 그중에 P0010코드를 가져오는 SQL을 만들겠다.
		 */
		String strMaxPCode = proDao.maxPCode();
		log.debug("조회한 상품코드 : {}", strMaxPCode );
		

		
		/*
		 * table에 상품정보가 하나도 없을 경우
		 * 미리 최로의 상품 코드를 변수에 담아놓고
		 * retPCode를 생성하는 코드를 실행하여 
		 * try에서 exception이 발생하여 건더뛰도록 한다.
		 * 이렇게 하면 상품코드는 P00001을 자동으로 return할 것이다,.
		 */
		String retPCode = "P00001";
		try {
			// 맨 앞에 있는 한글자만 뽑아내기
			String preCode = strMaxPCode.substring(0,1);
			String pCode = strMaxPCode.substring(1);
			
			log.debug("분리된 상품코드 {}, {}", preCode, pCode);
			
			retPCode = String.format("%s%05d", preCode, Integer.valueOf(pCode)+1);
		} catch (Exception e) {
			// TODO: handle exception
		}
				
		
		log.debug("새로 생성된 상품코드 {}", retPCode);
		 
		return retPCode;
	}

	

	
}
