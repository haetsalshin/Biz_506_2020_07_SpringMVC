package com.biz.grade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.grade.model.GradeVO;


public interface GradeDao {

	@Select("SELECT * FROM tbl_grade")
	public List<GradeVO> selectAll();
	
	@Select("SELECT * FROM tbl_grade WHERE seq =#{seq}")
	public GradeVO findById(long seq);
	
	public int insert(GradeVO gradeVO);
	public int update(GradeVO gradeVO);
	
	@Delete("DELETE FROM tbl_grade WHERE seq =#{seq}")
	public int delete(long seq);
	
}
