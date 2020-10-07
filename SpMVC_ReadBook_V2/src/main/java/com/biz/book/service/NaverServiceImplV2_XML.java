package com.biz.book.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biz.book.config.NaverSecret;
import com.biz.book.model.BookVO;
import com.biz.book.model.Book_JSON_Parent;
import com.biz.book.model.Book_XML_Parent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("naverServiceV2_XML")
public class NaverServiceImplV2_XML extends NaverServiceImplV1{

	// NaverServiceV1의 3개의 method를 모두 상속받아서
	// 여기에 아무런 코드가 없어도 프로젝트가 정상 수행이 되고 있다.
	
	// 3개의 method 중에서 getNaverList(String jsonString) method만 upgrade하겠다
	
	@Override
	public List<BookVO> getNaverList(String queryURL) {
		
		// URL이 오랫동안 사용하다 보니 버그가 많아서 URI라는 것을 다시 만든 것
		// 하는 일은 똑같으나 restTemplate를 사용할 때는 URL가 아닌 URI사용한다는 것만 알고 있으면 되겠다
		
		// 쌤) queryURL(요청주소 포함된) 문자열을 URI 객체로 만들어
		// http 프로토콜에서 사용할 수 있도록 만드는 클래스
		// 기존에 URL 클래스가 있으나, 새로운 기능을 수행하기 위해서
		// 별도로 URI라는 클래스를 만들어놨으며
		// 많은 기능들이 추가되어 있다.
		// RestTemplate를 사용하기 위해서는 queryURL 문자열을 URL 객체가 아닌
		// URI 객체로 만들어야 한다
		URI restURI = null;
		/*
		 * ㅆ) springframework에서 외부 API를 사용하여 데이터를 가져올때
		 * 기존에는 JSON(XML) 형식으로 가져오고,
		 * 여러가지 외부 라이브러리를 사용하여 객체로 parsing하는 과정을
		 * 복잡하게 만들어야 했다.
		 * 
		 * spring에서 외부 API를 사용하여 데이터를 가져올 일이 점점 많아지면서
		 * RestTemplate라는 클래스를 새로 만들어
		 * framework 프로젝트에서 사용할 수 있도록 만들어 두었다.
		 * 
		 * HttpHeader, HttpEntity, ResponseEntity 객체만 잘 작성하면
		 * 외부 API에 요청하고, 응답받은 데이터를 parsing하는 절차를
		 * 대신 수행 해준다.
		 * 
		 */
		// 외부에 있는 api를 조금더 쉽게 가져올 수 있는 방법을 고안하다가 만들어진 코드
		RestTemplate restTemp = new RestTemplate();
		
		try {
			restURI = new URI(queryURL);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 서버로 보내기 위한 http Entity를 만드는 것
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("X-Naver-Client-Id",NaverSecret.NAVER_CLIENT_ID);
		headers.set("X-Naver-Client-Secret",NaverSecret.NAVER_CLIENT_SECRET);
		
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		
		
		HttpEntity<String> entity = new HttpEntity<String>("Parameter",headers);
		
		// 수신하기 위한 http몸체를 만드는 것 . 나는 네이버가 보낸 데이터를 bookList클래스의 구조로 받겠다
		
		ResponseEntity<Book_XML_Parent> bookList = null;
		
		// 지금부터 데이터를 가져와라. 위에서 내가 만든restURI(요청주소)에 GET방식으로 요청을 했다
		// 위에서 만든 헤더가 필요한 entitiy를 주입
		//  restTemp.exchange(restURI, -> 서버에게 요청하는 값
		//	HttpMethod.GET, -> 서버에게 요청하는 값
		//	entity, -> 서버에게 요청하는 값
		// 데이터를 수신해서 BookList.class구조로 잘게 쪼개라(builder구조로)
		// 그리고 VO에 하나하나 세팅하고 그 값을 List에 담아라
		bookList = restTemp.exchange(restURI,
				HttpMethod.GET,
				entity,
				Book_XML_Parent.class);
		
		log.debug(bookList.toString());
		// 리스트에 담겨줌. 헤더는 필요없고 우리는 바디 부분만 추출하고 그 바디에서  items만 추출한다
		// bookList를 보면 itmes라는 이름이 있는데 그건 네이버가 보내준 데이터(출력결과에 있는 items 를 기준으로 )
		// bookVO에 나머지 데이터가 들어가 있는데 그 값을 list에 담아서 나에게 달라
		// JSON을 통해서 우리가 하나하나 일일이 다 쪼갤 필요가 없다.
		// NaverServiceImplV1보다 훨씬 더 편한 방법이다. (jsonObejct보다 restObject가 훨씬 더 편하다)
		return bookList.getBody().channel.item;
	}
	
public String queryURL (String category, String bookName) {
		
		String queryURL = NaverSecret.NAVER_BOOK_XML;
	
		
		String encodeText = null;
		
		try {
			// 한글 검색을 위해서 검색어를 UTF 로 encoding 처리해주어야 한다
			encodeText = URLEncoder.encode(bookName.trim(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// https://openapi.naver.com/v1/search/book.json?query=자바 문자열로 만들어줘야 하기 때문에
		// ㅆ) url?qeury=자바
		queryURL += String.format("?query=%s",encodeText);
		
		// 한번에 조회할 데이터 개수를 지정할 수 있다.
		queryURL += "&display=10";
		
		return queryURL;
	}
	
	
}
