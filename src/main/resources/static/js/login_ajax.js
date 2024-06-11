$(function() {

	$("form").submit(function() {
		var email = $("input[name='email']").val();
		var password = $("input[name='password']").val();

		$.post("/index/doLogin", {
			"email" : email,
			"password" : password
		}, function(result) {
				$("#form_msg").html(result);
				$("#form_msg").css("color", "red");
				window.location.href="/index";
		});

		return false;
	})
})
