# Spring Project 빛나리 쇼핑몰 V1
* 상품관리, 거래처관리, 회원가입, 로그인을 포함한 
* DB 오라클
* 반응형 메뉴를 사용, 반응형 메인화면 구현

# Project 시작
* java Version 1.8로 변경
* Spring Framework 5.2.8
* lombok, logback
* views/home.jsp 삭제 후 재 생성
* Run Service 수행하여 home 화면이 나타나도록

## DB 연동 설정(pom.xml)
* spring-jdbc
* mybatis
* mybatis-spring
* commons-dbcp2
* ojdbc6

* spring/appServlet/mybatis-context.xml 파일 생성, 작성

## URL(Uniform Resource Location), URI(Uniform Resource Identifier)
* URL : 파일 식별자 ( https://localhost:8080/index.html -> 이런식으로 파일 확장자가 나와있는 것 )
* URI : 통합 자원 식별자 ( https://localhost:8080/shop/insert/list )
* req해서 서버가 가장 먼저 수신하는 곳을 URL이라고 하고 나머지 부분을 qeury라고 한다.
* https://localhost:8080/shop/ : URL
* insert/list : qeury
* 우리가 만들고 있는 서비스 또한 URI라고 불러야 하지만 통상적으로 URL이라고 부른다.

### View 단에서 사용하는 URL
* <a href="http://localhost:8080/shop">서버 Home</a> 
* tomcat WAS에게 shop이라는 Context를 가진 Project가 작동되고 있으냐 ? 라고 물어보는 request
* <a href="http://localhost:8080/shop/product/list">상품리스트</a> 
* shop Context의 Dispatcher에게 product/list를 수행할 수 있는 Controller method가 있으냐? 라고 묻는 request
* 이 HTML코드를 사용해서 만나면 해당 Hyper Text(anchor 문자열 )을 클릭했을 때 서버에 Request한다. 이 떄 수행하는 Request는 method=RequestMethod.GET 설정된 함수에서 처리한다.

### Href
* href : Hyper Text Reference, URL 주소라고 생각하면 됨.

### HTML 코드에서 GET method로 Request를 요청하는 곳들
* anchor tag : <a href="주소">텍스트</a> : Controller에 의해서 우리가 요청한 것을 처리하게 되어 있다.

* script에서 : document.location.href="주소"
* script에서 : document.location.replace=("주소")

* css 가져오기 : <link rel="stylesheet" href="주소"/>
* script 가져오기 : <script src="주소'></script>
* 이미지 보이기 : <img src="주소"/>
* 배경이미지 : background-image : url("주소")

* anchor tag를 제외한 나머지는 우리의 static폴더에 넣어놨다. 그럼 나머지는 굳이 Controller가 그 값을 읽어들어 처리를 할 필요가 없다. 그냥 static폴더에 있는 것을 그대로 읽어서 보여주면 된다. 그럼 href를 이용해서 주소만 적어주면 되는 것이다. 이 내용이 servlet-context.xml에 적혀 있다. 우리가 <resources mapping="/static/**" location="/static/" /> 를 명시하는 이유가 바로 그것이다.

***
***
# Spring Project 빛나리 쇼핑몰 V3

## 상품관리 거래처 관리 이후에 회원가입, 로그인 구현

### session
* Hyper Text : Anchor Tag로 구성된 Text를 웹 브라우저에서 클릭 했을 때 해당하는 단어의 설명하는 새로운 문서가 열리는 구로조 된 그러한 문서
* HTML(Hyper Text Markup Lang.) : Hyper Text 기능을 구현하는데 사용되는 마크업 언어
* HTTP(Hyper Text Transfer Protocol) : Hyper Text로 구성된 문서를 웹 브라우저에서 보여주고 사용자가 마우스로 Anchor Tag 단어를 클릭하면 그에 따른 다른 문서를 연속해서 보여주는 용도로 최적화 된 용도로 만들어진 인터넷 프로토콜
* HTTP의 특징 중 하나는 단방향이고 비 연결지향.
	1. Web client에서 서버에 request를 보냈을 때만 서버에서 response를 할 수 있다.
	2. 한번 req-res가 이루어지면 그 연결은 끊어진다. 

* 서버의 어떤 연산을 요청할 때 요청하는 정보가 누구나 봐서는 안되는 정보라고 가정. 서버에 연산을 요청할 때 "
나는 그 정보를 봐도 되는 권한을 가지고 있다."라고 알려줘야 한다. 이러한 절차를 보통 로그인(ID, Password)라고 한다.
* 서버는 client req에서 사용자의 로그인 정보가 포함되어 있으면 그 정보를 확인하여 정상적인 로그인 정보 인지를 확인한다 = 인증
* 확인이 되면 요청한 정보를 client에게 response한다.
* HTTP는 즉시 client로부터 받은 모든 정보(ID, Password 포함)를 삭제해 버린다.
* 이후에 또다시 client가 같은 정보를 요청을 할 필요가 있을 때에는 다시 한번 Id, password를 req에 실어서 보내야 한다.
* 서버로부터 request해야 할 정보가 여러 page에 있을 경우 매 Page를 요청할 때마다 ID, Password를 보내고, 인증 후 response하는 절차를 반복해야 한다.
* 이러한 불편을 해결하기 위해서 HTTPSession이라고 하는 보조 프로토콜을 만들어 두었다.

## HTTP Session(연결 통로가 만들어 졌다)
1. client에서 로그인을 시도, ID와 Password를 먼저 request한다.
2. 인증절차: Server에서 사용자 보낸 ID, password를 확인하여 정상적인 정보인지 검사
3. 서버는 정상적인 사용자 임이 확인되면 서버 메모리 어딘가에 HTTPSession정보를 저장하기 위해 공간을 마련한다.
4. 이 공간에는 HTTPSession규격에 따라 서버가 데이터를 보관한다.
5. 이 공간에는 Session ID라고 하는 식별자(PK)를 만들어둔다. 
6. Server가 client Response를 할 때 Response 정보 생성된 SessionID를 같이 부착하여 보낸다.
7. Web Browser는 서버로부터 전달된 Response에 HttpSession규격에 해당하는 Session ID가 있으면 Browser의 임시 저장소에 보관한다.
8. 이후에 client가 Server에 Request하면, Browser는 이 SessionID를 request에 같이 부착하여 Server에 보낸다.
9. Server에서 Request를 수신했을 때 HttpSession 규격에 맞는 Session ID가 있으면 서버가 정상적으로 발행(Response에 부착하여 보낸)한 SessionID이고, 유효기간이 정상적 이라면, 이 요청은 "인가된" client로부터 전달된 것임을 확인하고 이후 response를 수행해 준다.



