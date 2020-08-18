package com.biz.blog.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
// 1.8d에서 새로 만들어진 newio.file.path
// spring에서는 이 코드를 사용할 수 밖에 없다. 
// 근데 스프링 다른버전에서는 사용안될수도 ㅠ
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.blog.model.BlogVO;

// controller와 연결하기 위하여 @service 어노테이션 붙인다.
@Service
public class BlogFileService {
	
	
	/*
	 * servletContext
	 * 		tomcat위에서 실행되는 WA(Web App)의 모든 정보를 담고 있는 객체
	 * 
	 * 이미 Sprint Project에서는 ServletContext의 클래스의
	 * 		객체 인스턴스가 이미 만들어 있기 때문에 가져다 사용하기 위해서
	 * 		Autowired로 묶어주기
	 */	
	
	// @Autowired : 스프링이 객체를 만들어 주입해주는 것. 
	// 			그리고 이를 초기화 시켜 주는 것
	@Autowired
	private  ServletContext context;
	
	private String serverRootPath;
	private String blogFile;
	
	
	public BlogFileService() {
		this.blogFile = "blog.txt";
		// realpath :
		//this.serverRootPath = context.getRealPath("/");

	}
	
	// 우리가 작성해서 저장한 블로그 게시글이 어디 저장됐는지 확인해보자
	public List<BlogVO> selectAll() {
		this.serverRootPath = context.getRealPath("/");
		Path path = null;
		
		try {
			
			System.out.println("서버 rootPath :" + this.serverRootPath);
			
			// 서버의 root path와 blog파일 이름을 묶어서
			// 파일 관련 연산을수행할 떄 사용할 file 객체 생성
			File file = new File(this.serverRootPath,blogFile);
			
			path = Paths.get(file.toString());
		
			List<String> strList = Files.readAllLines(path);
		
			// 문자열 읽어들인 것을 vo에 담기
			List<BlogVO> blogList = new ArrayList<BlogVO>();
			
			// title, content, user
			for(String str : strList) {
				String[] sSplit = str.split(":");
				BlogVO blogVO = new BlogVO();
				blogVO.setBl_title(sSplit[0]);
				blogVO.setBl_contents(sSplit[1]);
				blogVO.setBl_user(sSplit[2]);
				blogList.add(blogVO);
			}
			// 다 저장했으면 그 리스트를 리턴해주어야 한다.
			return blogList;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try-catch문으로 묶었기 때문에 리턴을 null값으로 해주어야 오류가 발생하지 않는다
		// 리턴 null 안해주면 selectAll() 부분에 빨간색 밑줄이 쳐진다.
		return null;
		
	}
	
	
	
	/*
	 * blog글쓰기를 수행하고 저장을 하면
	 * server root path에 blog.txt라는 파일로 저장하겠다.
	 */
	public void insert(BlogVO blogVO) {
		
		// Server의 class 들이 저장되는 폴더 정보 가져오기
		this.serverRootPath = context.getRealPath("/");
		
		File file = new File(this.serverRootPath, blogFile);
		
		
		Path path = null;
		
		FileWriter filewriter = null;
		PrintWriter print = null;
	
		try {
			
			path = Paths.get(file.toString());
			filewriter = new FileWriter(path.toString(),true);
			print = new PrintWriter(filewriter);
			
			String strBlog = String.format("%s:%s:%s", 
					blogVO.getBl_title(), 
					blogVO.getBl_contents(), 
					blogVO.getBl_user());
			
			print.println(strBlog);
			print.flush();
			print.close();
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
