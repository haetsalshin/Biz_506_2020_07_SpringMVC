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
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@Service("naverServiceV1")
public class NaverServiceImplV1 implements NaverService<BookVO> {
	
	// 도서명을 매개변수로 받아서 queryURL을 생성
	public String queryURL (String category, String bookName) {
		
		String queryURL = NaverSecret.NAVER_BOOK_XML;
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
	private String getNaverBook(String queryURL) {
		
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
			
			
			
			/*
			 * HTTP 프로토콜에서 response Code
			 * 200 : Server가 요청(Request)를 정상적으로 수신하고 response 할 데이터를 준비중이다
			 * 
			 * 3xx : 요청은 정상적으로 수신했으나 보낼 데이터가 없거나 이미 보냈기 때문에 다시 보내지 않겠다.
			 * 
			 * 302 : redirect 했다
			 * 304 : 직전에 보낸 데이터가 변화가 없으니 그대로 사용하라
			 * 
			 * 4xx : 요청정보가 문제가 있다
			 * 
			 * 400 : form에  데이터를 입력하고 서버로 전송했을 때
			 * 		 Controller의 매개변수 차원에서 문제가 발생했을 때
			 * 		 <input name="age"> 라는 input box에 값을 입력하지 않고 submit을 했는데
			 * 		 public String input(int age)라고 Controller에 method의 매개변수로 설정해 두었을 때
			 * 		 spring의 Dispatcher는 age변수로 담긴 값을 int형으로 형변환을 시도한다.
			 * 		 이때, input box에 아무런 값이 없으면 결국 age = Integer.valueOf("")의 코드가 실행되는 것과 같다
			 * 		 이럴때 내부에서 exception이 발생하고 res code로 400을 보낸다.
			 *  
			 *  
			 * 403 : 요청권한이 없다. 인증이 잘못되었거나 ROLL(역할)이 잘못되었다.
			 * 404 : 요청주소(URL, URI)가 서버에서 처리할 end point가 없다.
			 * 405 : 요청주소는 있으나 method(GET, POST)가 지정되지 않았다
			 * 		http://localhost:8080/book/input라고 request를 했는데
			 * 		서버에 @requestMapping(value ="/input", method=POST)만 있을 때
			 * 
			 * 		form의 action="/input" method=POST로 지정된 데이터를 submit했는데
			 * 		@requestMapping(value ="/input", method=GET)만 있을 때
			 * 
			 * 
			 * 500 : Server Internal Error
			 * 		사용자의 요청을 처리하는 과정에서 Controller, Service, Dao 등등 코드를 실행하는 도중
			 * 		어떠한 원인으로 exception이 발생했을 때 보내는 코드
			 * 
			 * 		오류 메시지를 제일 하단부터 거꾸러 검토해 나가자
			 * 		Error Stack 메세지는 발생된 순서가 역순으로 나타나기 때문
			 * 
			 * 
			 * 
			 */
			
			
			// bufferreader를 통해 데이터를 받는다
			BufferedReader buffer = null;

			
			InputStreamReader is = null;
			
			if(resCode == 200) {
				// naver가 정상적으로 응답을 할 것이다.
				
				is = new InputStreamReader(httpConn.getInputStream());
				
			}else {
				
				is = new InputStreamReader(httpConn.getErrorStream());
				
			}
				// InputStreamReader와 BufferedReader를 파이프로 연결
				buffer = new BufferedReader(is);		
				StringBuffer sBuffer = new StringBuffer();
				
				// 네이버가 보낸 payload(response data)를 한개의 문자열로 
				// 통합하여 수신한다.
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
				// sBuffer에 append한 문자열을 한개의 문자열로 변환하여 return
				
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
	private List<BookVO> getJsonObject(String jsonString) {
		
		List<BookVO> bookList = new ArrayList<BookVO>();
		
		/*
		 * JSON* 클래스를 사용해서 JSON 문자열을 객체로 변환하기
		 * 1. JSONParser를 사용하여 JSONObject로 변환
		 * 2. JSONObject에서 "items"를 기준으로 잘라서 JSONArray 로 변환
		 * 3. JSONArray를 for문으로 반복하면서 각 요소를 다시 JSONObject로 변환
		 * 4. 요소로 변환된 JSONObject에서 각각의 항목을 추출하여 VO에 담기
		 * 5. VO를 List에 add()하기
		 */
		
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
				
				/*
				 *  빌드 패턴을 사용하여 bookVO객체 생성
				 *  일반적인 VO 객체를 생성하고 데이터를 Setting하는 방법
				 *  1. 비어있는 VO객체를 생성 : vo = new VO();
				 *  	setter method를 사용하여 데이터를 입력하는 방법
				 *  2. 생성자에 값을 설정하고 VO객체를 생성 
				 *  	: vo = newVO(값1, 값2, 값3 ...)
				 *  	생성자에 값을 설정(주입)하고 VO객체를 생성하는 방법은
				 *  	
				 *  	가.데이터의 순서가 완전히 개발자 책임이다
				 *  		혹여 순서가 바뀐채로 VO객체가 생성되면
				 *  		이후에 발생하는 모든 문제를 개발자가 책임져야 한다.
				 *  	나. 일부데이터만 사용하여 객체를 생성하려면
				 *  		생성자를 필요한 매개변수가 있는 상태로 또 다시 만들어야 한다
				 *  		vo = new VO(값1, 값2) : VO(String 값1, String 값2)
				 *  		vo = new VO(값1, 값2, 값3) : VO(String 값1, String 값2, String 값3)
				 *  		많은 생성자의 중복 선언이 발생할 수 있다
				 *  3. Builder(Build) 패턴을 사용한 객체 생성
				 *  	VO = VO.builder()
				 *  		.변수1(값1)
				 *  		.변수2(값2)
				 *  		.변수3(값3)
				 *  		.build();
				 *  
				 *  	가. 생성자를 통해서 객체를 필요할 때 즉시 생성한다.
				 *  	나. 생성자에 매개변수를 필요한 값을 주입하는 방식인데
				 *  		여기서는 필요한 데이터만 매개변수로 주입할 수 있다.
				 *  		모든 변수를 생성자에 주입할 필요가 없다.
				 *  	다. 매개변수를 주입할 때 set*()와 같은 method를 사용하지 않고
				 *  		매개변수의 이름을 통해 직접 설정할 수 있다. 
				 *  	라. 객체를 생성할 때 객체 chaining 코딩을 사용할 수 있다.
				 *  		객체.변수1().변수2().변수3().변수4().변수5()
				 *  
				 *  
				 */
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
	
	// 이런식으로 하면 기존의 코드를 수정하지 않고 사용할 수 있다.

	@Override
	public String getNaverSearch(String queryURL) {

		return this.getNaverBook(queryURL);
	}

	@Override
	public List<BookVO> getNaverList(String jsonString) {

		return this.getJsonObject(jsonString);
	}
	
	

}

