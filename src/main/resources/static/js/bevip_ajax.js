$(function() {
    $("form").submit(function() {
        var email = $("input[name='email']").val();

        $.post("/buyVip", {
            "email" : email,
        }, function(result) {
            var traceNo = Math.floor(Math.random()*100000000);
            // 每次启动内网穿透更改此URL
            window.open(`http://er747e.natappfree.cc/alipay/pay?subject=购买VIP&traceNo=${traceNo}&totalAmount=10`)
            // $("#form_msg").html(result);
            // $("#form_msg").css("color", "red");
            window.location.href="/index/logOut";
        });
        return false;
    })
})