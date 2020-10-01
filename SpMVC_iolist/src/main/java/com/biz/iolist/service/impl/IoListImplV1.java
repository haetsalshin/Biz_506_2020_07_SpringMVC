package com.biz.iolist.service.impl;

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
	
	public List<IoListVO> selectAll() {
		

		log.debug("SELECT 성공");
		
		return ioListDao.selectAll();
		
	}

	@Override
	public IoListVO findById(Long id) {
		return null;
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
		return 0;
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

}
