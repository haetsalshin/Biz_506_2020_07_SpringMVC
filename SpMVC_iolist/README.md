#💻💻 💻IoList Project💻 

##  DB Table을 사용하여 CRUD 구현하기

## java dependency 설정🔋
* java-version 1.8
* springframework 5.2.8

## Oracle, Mybatis 연동을 위한 Dependency 설정💿
* spring-jdbc
* mybatis
* mybatis-spring
* ojdbc
* commons-dbcp2
* lombok


## Logging🖱
* logback 1.2.3

### 상제 설명💽
* 첫화면에 매입 매출 리스트 전부 확인 가능
* 새로 작성을 누르면 새로운 리스트 입력 가능 ( http://localhost:8080/iolist/input)
* 입력완료 누르면 처음 화면에 해당 data 입력 됨
* 첫 화면에서 삭제하고 싶거나 수정하고 싶은 데이터 상품명 클릭하면 detail page로 이동


## 수정해야 할 부분🖨
* 회원가입/ 로그인 구현
* 확인하고 싶은 날짜 선택하면 해당 날짜 데이터만 확인 할 수 있도록
* 처음에 데이터 입력할 때 아무런 값을 넣지 않았을 때 값을 넣지 않으면 반드시 데이터를 입력 해야 한다는 알람