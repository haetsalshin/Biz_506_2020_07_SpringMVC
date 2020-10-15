# spring web tiles project

### spring에서 전통적으로 아주 많이 사용하던 layout 관리자 
### 최근 4~5년 전부터 인기가 시들해졌으나 전자정부 프레임워크 에서는 지금도 표준 레이아웃처럼 사용되고있다.

### tiles dependency 가져오기
* 2개!!!!
* tiles-core
* tiles-extras

### tiles-layout.xml 설정하기
* jsp 이용해서 화면 레이아웃을 꾸미는 방법을 정의하는 파일이다
* <tiles-definitions> 태그 사이에 레이아웃 꾸미는 방법을 기술
* xml 파일의 태그 정의할 때 태그 이름이 복수형으로 되어있다는 것은 tag 내부에 단수형으로 여러개의 
  설정내용이 정의된다. 

* 정의 방법
1. layout.jsp 파일을 default 라는 이름으로 정의
2. layout.jsp attribute로 등록한 이름을 tiles-layout.xml에 put-attribute로 등록하기
3. views 폴더에 layout 폴더를 만들고, header.jsp, menu.jsp, content.jsp, footer.jsp 를 만들기

### tiles-context.xml 파일의 urlbaseresolver bean 설정할 때 유의사항
* 일반적으로 spring에서는 bean에 bean을 주입할 때 미리 bean을 만들고 id를 통해서 주입한다
 tiles에서 TilesView 클래스를 UrlBaseResolver에 빈으로 주입할 때는 일반적인 방식으로 주입하면 안된다
 반드시 직접 TilesView 클래스이름을 url로 주입하는 방법으로 사용
 
* tilesview 클래스를 value 값에 문자열로 지정하는데 자동완성 안됨 ㅡㅡ
 임시로 bean 하나 만들고 class 앞에 TilesView 입력한 후 자동완성 하여 복붙해옴
* order property 의 value 값을 반드시 1로 설정해주어야 한다.


### tiles-context.xml 설정한 후 servlet-context.xml 변경사항 추가
* InternalResourceviewresolver의 order 속성을 반드시 2이상 값으로 지정 (우선ㅇ순위가 1이되면안됨)
* TilesView를 사용하는 urlBaseResolver를 우선순위1, internal*resolver를 우선순위2로 변경하는 것이다.












