# Read Book, 나의 독서록
* 내가 보유한 도서 등록
* 보유한 도서 읽기 일지 정리 : 독서록 정리

## 도서 등록
* 도서명을 입력하여 네이버 도서 API로부터 도서정보 가져오기
* https://openapi.naver.com/v1/search/book.json?query=자바
* 가져온 정보를 나의 DB에 저장

## 독서록 정리
* 내가 보유한 도서정보 검색하여 해당 도서 읽기 일지를 등록
* 읽기 시작한 날, 읽기 종료한 날, 중간중간 진행 상황

## 로그인 기능
* Spring Security를 사용하여 사용자 정보 암호화 등을 수행

## 도서 입력에서 네이버 검색 결과 가져오기
* 도서명을 입력하고 네이버 검색 버튼을 클릭하면 검색정보를 새로운 POP UP창에 보여주고, 도서를 선택하면 자동으로 입력박스에 도서 정보가 채워지도록 구현
* 최근의 거의 대부분의 Browser들이 POP UP 차단기능을 구현해 놓고 사용자는 POP UP 기능을 활성화 하여 POP UP으로 검색 결과를 보여주는데 문제가 발생을 한다.
* 요즘 UI는 POP UP 대한 MODAL 창 기법을 사용을 한다.

* 도서명을 입력하고 네이버 검색 버튼을 클릭하면 도서명을 ajax를 사용하여 검색하고 결과를 Modal 창에 띄우기


## 수정해야 할 부분
* 만약 내가 도서 리스트에서 어떤 도서를 클릭하고 수정버튼만 누르고 수정을 진행하지 않고 새로운 도서리스트를 작성하기 누르면 그 전에 수정하려고 보았던 도서 정보가 자동으로 나오는것을 확인 할 수 있다. 이를 방지하기 위하여 BooksController에서 input method=GET 부분에 BookVO bookVO = new BookVO()를 다시 새롭게 선언해 주는 방법이 있지만 썩 좋은 방법은 아니다. 따라서 본인이 생각해서 이러한 코드부분을 구현하는 방법을 생각해보자.