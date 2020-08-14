package com.biz.blog.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.blog.dao.BlogDao;
import com.biz.blog.model.BlogVO;

/*
 *  No qualifying bean of type 'com.biz.blog.service.BlogService'
 *  spring 프로젝트에서 서버를 시작햇을 때 매우 자주 만나는 exception
 *  Controller, Service 등의 클래스에 Anotaion을 부착하지 않았을 때  발생
 */

// BlogDao가 아닌 BlogService를 implements하는 것이 원칙!
@Service
public class BlogServiceImplV1 implements BlogService {
	
	// 객체 주입
	// @Autowired를 빼먹으면 ? NullPointgerException을 만나게 된다.
	// sprinig에서 nullpointer를 만난다? 10에 6은 @Autowired를 안해줘서이다.
	// 잊지 말고 해주자!
	@Autowired
	// myBatis-context에서 설정한 SqlSessionTemplate를 가져와서 사용할 수 있도록 선언
	// splsession은 interface이기 떄문에 autowired해준다
	private SqlSession sqlSession;
	
	@Override
	public List<BlogVO> selectAll() {
		// sqlsession을 통해 blog와 연결하는 작업을 해주자
		// 쌤) BlogDao와 SqlSession을 연동하여 MyBatis 연결 구성
		
		/*
		 * sqlSession에게 BlogDao 인터페이스와 blog-mapper.xml 파일을 참조하여
		 * BlogDao 인터페이스를 구현한 클래스를 만들고, 객체로 생성하여
		 * 사용할 수 있도록 해달라
		 */
		BlogDao blogDao = sqlSession.getMapper(BlogDao.class);
		
		List<BlogVO> blogList = blogDao.selectAll();
		
		return blogList;
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

		return 0;
	}

	@Override
	public int update(BlogVO blogVO) {

		return 0;
	}

	@Override
	public int delete(long seq) {

		return 0;
	}

}
