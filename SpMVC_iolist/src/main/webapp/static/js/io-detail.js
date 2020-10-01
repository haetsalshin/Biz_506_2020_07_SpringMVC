$(function () {
  $("td.io-title").click(function () {
    let seq = $(this).data("seq");
    
    
    // query string방식(원래 우리가 쓰던 방식)
    // document.location.href = `${rootPath}/books/detail?=${seq}`;

    // path Varriable 방식
    document.location.href = `${rootPath}/detail/${seq}`;
  });
});
