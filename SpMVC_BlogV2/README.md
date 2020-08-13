# WAS: Web Application Service(Server)
* web Browser를 사용하여 server와 통신을 하면서 어떤 일을 수행하는 어플리케이션
* 클래스에 포함 된 method는 함수라고 표현하겠다.

## WEB Browser에서 Server에 보내는 Request
* a href="주소" : Hyper Text를 보여주고, Hyper Text를 클릭했을 떄 Server로 Request를 보내는 가장 기본적인 형태. 이 request를 Controller에서 받아서 처리 할 때는 method=request.GET으로 지정된 함수가 연산을 수행한다.

* from tag의 button을 클릭 했을 때 Server로 request를 보내는 형태. input box에 문자열을 입력한 후 button을 클릭하면 그 문자열들을 Query String 이라는 형식으로 바꾸어서 Server에 한꺼번에 request한다.

* form tag에 특별히 method라는 것을 지정하지 않으면 Controller에서 처리할 때는 method=Request.GET으로 지정된 함수가 처리한다.
* form tag에 method를 지정(통상 POST)하면 Controller에서 처리 할 때는 지정된 함수의 처리가 표현된 함수가 처리한다.

## Request, Response
* Request_1 ( a href="input" ) == Response_1 (write.jsp)
* Request_2 ( write.jsp, form, input, button click) 
==> Controller 
==> write(String title, String content)
* Response_2 (write함수에서 model에 TITLE, CONTENT attribute를 설정하고, view.jsp와 Rendering)
==> http 코드를 보내주기

* server는 web이 요청을 했을 때만 응답할 수 있다. 먼저 server가 web에 요청을 할 수 없다.
