package com.biz.iolist.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.biz.iolist.mapper.IoListDao;
import com.biz.iolist.model.IoListVO;
import com.biz.iolist.service.IoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("service")
public class IoListImplV1 implements IoService {
	
	@Autowired
	private IoListDao ioListDao;
	
	@Override
	public List<IoListVO> selectAll() {
		
		
		List<IoListVO> ioList = ioListDao.selectAll();
		log.debug("SELECT 성공");
		
		ioList = calcSum(ioList);
		
		return ioList;
		
	}

	@Override
	public IoListVO findById(Long id) {
		
		
		return ioListDao.findById(id);
	}

	@Override
	public int insert(IoListVO vo) {
		
		int ret = ioListDao.insert(vo);
		
		if(ret>0) {
			log.debug("INSERT 성공 {}개의 데이터 추가", ret);
		}else {
			log.debug("INSERT 실패 : {} ",ret);
		}
		
		return ret;
	}

	@Override
	public int update(IoListVO vo) {

		int ret = ioListDao.update(vo);
		
		if(ret>0) {
			log.debug("UPDATE 성공 {}개의 데이터 추가", ret);
		}else {
			log.debug("UPDATE 실패 : {} ",ret);
		}
		return ret;
	}

	
	@Override
	public int delete(Long seq) {
		
		int ret = ioListDao.delete(seq);
		
		
		if(ret>0) {
			log.debug("DELETE 성공 {}개의 데이터 삭제", ret);
		}else {
			log.debug("DELETE 실패 : {} ",ret);
		}
		
		return ret;
	}
	
	private List<IoListVO> calcSum(List<IoListVO> ioList){
		
		
		long iTotal = 0;
		long oTotal = 0;
		
		for(IoListVO ioListVO : ioList) {
			
			if(ioListVO.getIo_input().equals("1")) {
				iTotal += ioListVO.getIo_total();
				
				
			}else {
				oTotal += ioListVO.getIo_total();
				
			}
			
			ioListVO.setOut_total(oTotal);
			ioListVO.setInput_total(iTotal);
			
		}
		
		return ioList;
	}
	
	
}
