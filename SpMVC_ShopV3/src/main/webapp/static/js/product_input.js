$(function () {
  $("#p_code_gen").click(function () {
    alert("key");
    $.ajax({
      type: "GET",
      URL: `${rooPath}/api/product/keyinput`,
      success: function (result) {
        alert(result);
      },
    });
  });

  //   $("#p_code_gen").on("click", function () {
  //     console.log("on 핸들러");
  //   });

  function test() {
    alert("안녕하슈");
  }
});
