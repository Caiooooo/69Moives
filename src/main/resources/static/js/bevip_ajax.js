$(function() {

    $("form").submit(function() {
        var email = $("input[name='email']").val();

        $.post("/buyVip", {
            "email" : email,
        }, function(result) {
            $("#form_msg").html(result);
            $("#form_msg").css("color", "red");
            window.location.href="/index/logOut";
        });

        return false;
    })
})
