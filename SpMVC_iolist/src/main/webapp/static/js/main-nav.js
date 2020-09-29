$("ul#main-nav li").click(function () {
  let menu_text = $(this).text();
  let menu_id = $(this).attr("id");

  if (menu_text === "io-write") {
    document.location.href = `${rootPath}/io/input`;
  }
});
