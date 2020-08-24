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





