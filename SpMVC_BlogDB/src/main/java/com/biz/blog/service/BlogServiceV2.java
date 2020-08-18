package com.biz.blog.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.biz.blog.dao.BlogDao;
import com.biz.blog.model.BlogVO;

// 블로그 서비스 2를 만들고 service annotaion을 붙여주는데 각각  value값에 1,2를 붙여줌
@Service(value = "bServiceV2")
public class BlogServiceV2 implements BlogService {
	/*
	 * BlogDao blogDao = sqlSession.getMapper(BlogDao.class);
	 * blogServiceImplV1을 보면 모든 class에 위와 같은 코드가 계속해서 나오는 것을 알 수 있다.
	 * 이를 간편하게 만들어 주기위하여 BlogDao를 필드변수로 만들고 이를 autowired해준다.
	 * mybatis 3.4이상에서만 가능한 기능
	 */
	@Autowired
	private BlogDao blogDao;

	@Override
	public List<BlogVO> selectAll() {
		
		return blogDao.selectAll();
	}

	@Override
	public BlogVO findBySeq(long seq) {

		return null;
	}

	@Override
	public BlogVO findByTilte(String title) {

		return null;
	}

	@Override
	public int insert(BlogVO blogVO) {
		/*
		 * LocalDate : Java 8(1.8) 이상에서 사용하는 날짜관련 클래스
		 * 기존에 있던 date관련 class가 이슈가 많아서 새로 만들어진 클래스이다.
		 * 
		 * Date, Calendar 라는 클래스를 사용하여 날짜 관련 연산을 수행했으나
		 * 많은 이슈들이 있어서 문제를 일으키는 경우가 많았다
		 * java8에서 새로운 날짜와 시간을 연산하기 위한 클래스가 등장
		 */
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		
		// 문자열형 날짜로 변환하기
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		// 만들어진 패턴에 따라 날짜 데이터를 문자열로 전환하여 VO에 세팅
		blogVO.setBl_date(localDate.format(dd).toString());
		blogVO.setBl_time(localTime.format(dt).toString());
		
		// insert()수행한 후 추가된 데이터 개수만큼을 정수로 return
		// ret값이 1이상이면 정상적으로 데이터가 추가되었다
		int ret = blogDao.insert(blogVO);
		
		return 0;
	}

	@Override
	public int update(BlogVO blogVO) {
		int ret = blogDao.update(blogVO);
		return ret;
	}

	@Override
	public int delete(long seq) {

		return blogDao.delete(seq);
	}

}
