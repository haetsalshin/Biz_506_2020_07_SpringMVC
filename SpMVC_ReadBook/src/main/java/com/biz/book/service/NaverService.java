package com.biz.book.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.biz.book.config.NaverSecret;
import com.biz.book.model.BookVO;

import lombok.Builder;

/*
 * naver API를 통하여 도서명을 보내고
 * 그 결과를 JSON 형태로 수신하여 parsing 처리를 수행하는 서비스 클래스
 * naver가 server가 되고 ReadBook 프로젝트가 client가 된다.
 * 1. naver API에 보낼 쿼리 문자열이 포함된 URL을 생성
 * 2. naver API에서 보내온 문자열을 JSON객체로 변환
 * 3. Parsing을 수행하고 
 * 4. BookVO에 담고
 * 5. List<BookVO>에 담기
 */

@Service
public class NaverService {
	
	// 도서명을 매개변수로 받아서 queryURL을 생성
	public String queryURL (String category, String bookName) {
		
		String queryURL = NaverSecret.NAVER_BOOK_JSON;
		if(category.equalsIgnoreCase("NEWS")) {
			queryURL = NaverSecret.NAVER_NEWS_JSON;
		}else if(category.equalsIgnoreCase("MOVIE")){
			queryURL = NaverSecret.NAVER_MOVIE_JSON;
			
		}
		
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
	
	// queryURL을 naver API에게 보내고 데이터를 수신하는 method
	public String getNaverBook(String queryURL) {
		
		// java.net.URL import
		// ㅆ) queryURL 문자열을 http 프로토콜을 통해서 전송하기 위한
		// 형식으로 만드는 클래스
		URL url = null;
		
		// ㅆ) http 프로토콜을 사용하여 데이터를 주고받는 Helper Class
		HttpURLConnection httpConn = null;
		
		try {
			// 서버에 접속하는 것이기 때문에 항상 오류가 날 수 있기 때문에 
			// try-catch로 묶어준다
			url = new URL(queryURL);
			
			// 기본값이 url이기 때문에 형변환을 시켜줘야 한다.
			httpConn = (HttpURLConnection) url.openConnection();
			
			// api를 요청할 때 GET방식이다.
			httpConn.setRequestMethod("GET");
			// http 프로토콜에 X-Naver-Client-Id 라는 변수로 
			// client id값 저장(심기)
			
			// http 헤더 부분에 들어가기 때문에 url에 노출되지 않는다.
			httpConn.setRequestProperty("X-Naver-Client-Id", NaverSecret.NAVER_CLIENT_ID);
			httpConn.setRequestProperty("X-Naver-Client-Secret", NaverSecret.NAVER_CLIENT_SECRET);
			
			// 서버는 데이터를 보내기 전에 현재 상태가 어떤지 상태 코드를 보낸다
			// ㅆ) 내가 URL을 만들고, GET로 요청을 하면서 Property에 Client Id와
			// Client Secret를 저장하여 보냈는데
			// 나에게 응답을 해줄래?
			// 네이버는 우리의 요청이 모두 정상적이라면 
			int resCode = httpConn.getResponseCode();
			
			// bufferreader를 통해 데이터를 받는다
			BufferedReader buffer = null;

			
			InputStreamReader is = null;
			
			if(resCode == 200) {
				// naver가 정상적으로 응답을 할 것이다.
				
				is = new InputStreamReader(httpConn.getInputStream());
				
			}else {
				is = new InputStreamReader(httpConn.getInputStream());
			}
				// InputStreamReader와 BufferedReader를 파이프로 연결
				buffer = new BufferedReader(is);		
				StringBuffer sBuffer = new StringBuffer();
				//String sBuffer = "";
				
				String reader = new String();
				while((reader = buffer.readLine()) != null) {
					sBuffer.append(reader);
					//sBuffer += reader;
				}
				
				
//				while(true) {
//					reader = buffer.readLine();
//					if(reader == null) break;
//					sBuffer.append(reader);
//				}
				
				buffer.close();
				return sBuffer.toString();
				
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return null;
	}
	
	// jsonString을 parsing하여 Object(VO등등)으로 바꾸는 기능 
	public List<BookVO> getJsonObject(String jsonString) {
		
		List<BookVO> bookList = new ArrayList<BookVO>();
		JSONParser jParser = new JSONParser();
		try {
			// JSONParser 도구를 사용하여 Json형태의 문자열을
			// JSONObject(객체)로 변환하기
			JSONObject jObject = (JSONObject) jParser.parse(jsonString);
			
			// items의 항목만 뽑아서 jArray에 보내라
			JSONArray jArray = (JSONArray) jObject.get("items");
			
			int size = jArray.size();
			for(int i = 0;  i < size ; i++) {
			
				JSONObject jo = (JSONObject) jArray.get(i);
				//BookVO bookVO = new BookVO();
				
				// 클래스 객체를 만들때 매개변수를 각각 만들어서 주입해주는 방법이 있는데(또다른 방법은 set방식을 통해서)
				
				// bookVO = new BookVO(title, link, image, author, price ..)
				/*
				bookVO.setTitle(jo.get("title").toString());
				bookVO.setImage(jo.get("image").toString());
				bookVO.setLink(jo.get("link").toString());
				
				이 패턴보다는 하단의 builder pattern을 이용해서 만드는 추세이다.
				생성자를 이용해서 만들 때는 순서를 잘못해서 엉뚱한 데이터가 잘못들어갈 수도 있고
				이를 방지하기 위해 getTitle 이런식으로 만들어주지만 이것 또한 복잡해서 쓰기가 불편하다
				따라서 lombok을 이용해서 쉽게 만들기 위해서 하단과 같은 패턴을 통해 만들어준다.
				*/
				
				/*
				 * ㅆ) VO에 @Builder를 설정함으로서
				 * VO 객체를 생설할 때 Builder패턴을 사용할 수 있다.
				 * GoF 패턴 중 생성자 패턴 중 1가지
				 */
				
				// builder pattern : 하나의 객체를 만들고 각각 ,을 찍어서 chaining으로... (lombok을 통해서 가능)
				
				BookVO bookVO = BookVO.builder()
						.title(jo.get("title").toString())
						.image(jo.get("image") == null ? "noImage" : jo.get("image").toString())
						.link(jo.get("link").toString())
						.description(jo.get("description") == null ? "" : jo.get("description").toString())
						.build();
				
				bookList.add(bookVO);
				
			}
			
			
			return bookList;
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
		
	}
	
	

}

