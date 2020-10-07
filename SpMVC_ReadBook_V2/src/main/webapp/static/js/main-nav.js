$(function () {
  // 화면에 scroll이 이동하면
  $(window).scroll(function () {
    let headerHeight = $("header").height(); // header tag의 높이값
    // 화면을 세로 방향으로 스크롤 할 떄 윈도우 화면의 최 상단 좌표 가져오기
    let windowTop = $(window).scrollTop();
    //  nav가 header부분을 넘겨버리면(화면 아래로 넘어가면)
    if (windowTop > headerHeight) {
      //  네비 부분의 포지션을 고정시키고 가장 위쪽으로 달라붙에 만든다
      $("#main-nav").css("position", "fixed");
      $("#main-nav").css("top", "0");
    } else {
      $("#main-nav").css("position", "relative");
    }
    console.log(headerHeight, windowTop);
  });

  $("li#menu-home").click(function () {});
  $("li#menu-books").click(function () {});
  $("li#menu-read-book").click(function () {});
  $("li#menu-login").click(function () {});

  // nav의 li tag를 눌렀을 때
  $("#main-nav li").click(function () {
    let menu_text = $(this).text();
    let menu_id = $(this).attr("id");
    // 하나의 method에 모아서 통합해서 관리하는 것
    if (menu_text === "도서정보") {
      document.location.href = `${rootPath}/books`;
    } else if (menu_id === "menu-home") {
      document.location.href = `${rootPath}/`;
    } else if (menu_id === "menu-read-book") {
      document.location.href = `${rootPath}/read/`;
    } else if (menu_id === "menu-join") {
      document.location.href = `${rootPath}/member/join`;
      // Localhost:8080/book/login
      // spring security에서 자체 지원되는 login form을 사용하기 위하여
    } else if (menu_id === "menu-login") {
      document.location.href = `${rootPath}/login`;
    } else if (menu_id === "menu-mypage") {
      document.location.href = `${rootPath}/member/mypage`;
    } else if (menu_id === "menu-logout") {
      $.ajax({
        url: `${rootPath}/logout`,
        method: "POST",
        // spring security 에서 로그아웃 수행할 때
        // post 방식으로 요청 해야한다.
        // post 방식으로 ajax요청할 때 보안경고가 뜨고 서버가 거부하는 현상이 발생
        // spring security 서버는 POST방식으로 요청을 할 때 csrf_token 값을 건네주어야 한다.
        // spring form tag 사용할 때는 문제가 없는데 ajax방식으로 요청할 때는 수동으로 설정한다
        // 이때 사용하는 속성이 beforeSend이다.
        // beforeSend Call back 함수를 붙여서 setRequestHeader에 csrf 속성 값들을 설정하여 전달해 주어야 한ㄷㅏ.

        beforeSend: function (ax) {
          ax.setRequestHeader(`${csrf_header}`, `${csrf_token}`);
        },
        success: function (result) {
          document.location.replace(`${rootPath}/`);
        },
        error: function () {
          alert("서버와 통신오류 발생!");
        },
      });
    }
  });
});
