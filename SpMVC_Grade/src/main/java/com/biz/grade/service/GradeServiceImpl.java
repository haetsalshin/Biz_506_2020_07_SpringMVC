package com.biz.grade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.biz.grade.mapper.GradeDao;
import com.biz.grade.model.GradeVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Qualifier("gradeServiceV1")
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeDao gradeDao;
	
	@Override
	public List<GradeVO> selectAll() {

		List<GradeVO> gradeList = gradeDao.selectAll();
		return gradeList;
	}

	@Override
	public GradeVO findById(long seq) {

		GradeVO gradeVO = gradeDao.findById(seq);
		return gradeVO;
	}

	@Override
	public int insert(GradeVO gradeVO) {

		int ret = gradeDao.insert(gradeVO);
		log.debug("INSERT 성공! :", gradeDao.toString() );
		
		return ret;
	}

	@Override
	public int update(GradeVO gradeVO) {

		return gradeDao.update(gradeVO);

	}

	@Override
	public int delete(long seq) {

		return gradeDao.delete(seq);
		
	}

}
