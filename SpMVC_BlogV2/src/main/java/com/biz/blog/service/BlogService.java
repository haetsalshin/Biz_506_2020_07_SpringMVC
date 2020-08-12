package com.biz.blog.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
// 1.8d에서 새로 만들어진 newio.file.path
// spring에서는 이 코드를 사용할 수 밖에 없다. 
// 근데 스프링 다른버전에서는 사용안될수도 ㅠ
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.biz.blog.model.BlogVO;

// controller와 연결하기 위하여 @service 어노테이션 붙인다.
@Service
public class BlogService {
	
	private String blogFile ="blog.txt";
	
	// 우리가 작성해서 저장한 블로그 게시글이 어디 저장됐는지 확인해보자
	public void selectAll() {
		ClassPathResource rs = new ClassPathResource(blogFile);
		Path path = null;
		
		try {
			
			path = Paths.get(rs.getURI());
		
			List<String> blogList = Files.readAllLines(path);
		
			for(String str : blogList) {
				System.out.println(str);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	// java 1.8 이상에서만 정상작동 되는 코드
	// blogVO 데이터를 읽어서 blog.txt에 그 데이터를 기록하는 코드
	// 스프링에서는 서버에 있는 어떤 데이터를 쓰기 위하여 
	// classPathResource를 이용하여 서버의 어딘가에 기록하고 이를 blog.txt를 통해...
	public void insert(BlogVO blogVO) {
		
		// Server의 class 들이 저장되는 폴더 정보 가져오기
		ClassPathResource rs = new ClassPathResource(blogFile);
		
		Path path = null;
		FileWriter filewriter = null;
		PrintWriter print = null;
		try {
			path = Paths.get(rs.getURI());
			filewriter = new FileWriter(path.toString(),true);
			print = new PrintWriter(filewriter);
			
			String strBlog = String.format("%s:%s:%s", 
					blogVO.getTitle(), 
					blogVO.getContent(), 
					blogVO.getUser());
			
			print.println(strBlog);
			print.flush();
			print.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
