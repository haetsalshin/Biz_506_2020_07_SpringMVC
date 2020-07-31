<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>

</head>
<body>
	
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <style>
        table{
            /* collapse: table을 그릴 때 테두리 선을 간격 없이 단선으로]
            기본은 separate (간격이 있게 된다) */
            border-collapse: collapse;
            border-spacing: 1;
            /* box model tag 테두리 선의 두께, 모양, 색깔을 지정하는 것*/
            border: 1px solid rgb(253, 130, 130);
            width: 95%;
        }
        /*다중 태그에 똑같은 style을 적용하고 싶을 때*/
        /* 쌤) 여러개의 selector에 공통의 style을 지정할 때는
            selector를 컴마(,)로 나열하면 된다.
        */
        th, td{
            border: 1px solid #ccc;
            /* padding: 8px 8px : 박스를 그려서 박스 내부에 content가 들어감
                이 안에 빈공간을 줄텐데 어떻게 줄꺼냐면 8px한개는 네 방향 모두에게 8px로 주는것

                근데 8px  3px    4px    5px(값을 최대 4개까지 줄 수 있음)
                     Top  Right  Botton left 순으로 시계방향으로 돌아가면서
                     2개면(top-botton)
                     paddinig을 주는 것              
             */
            padding: 8px 8px;

            padding-top: 10px;
            padding-bottom: 10px;
            padding-left: 5px;
            padding-right: 5px;
            text-align: left;
        }
        tr{
            border-bottom: 1px solid #ddd;
        }

    </style>

</head>
<body>
    <!-- 지금 border 태그가 빨간색으로 표시되어 있는 것을 알 수 있는데 지금은 쓸 수 있지만 
        나중에 문제를 일으킬 수 있기 때문에 웬만하면 사용하지 않는 것을 권장
    -->
    <table >
        <thead>
            <!-- thead 부분에 표의 제목이 들어갈 것임-->
            <th>NO</th>
            <th>주문번호</th>
            
            <th>고객번호</th>
            <th>주문일자</th>
            <th>상품번호</th>
            <th>상품이름</th>
            <th>가격</th>
            <th>수량</th>
            <th>합계</th>
        </thead>
        <tbody>
            <td>1</td>
            <td>20001</td>
            <td>C001</td>
            <td>2020-07-31</td>
            <td>P0001</td>
            <td>새우깡번들</td>
            <td>1200</td>
            <td>10</td>
            <td>12000</td>

        </tbody>

    </table>
</body>
</body>
</html>