$(function() {

	$("form").submit(function() {
		var username = $("input[name='username']").val();
		var password = $("input[name='password']").val();

		$.post("/admin/doAdminLogin", {
			"username" : username,
			"password" : password
		}, function(result) {
				$("#form_msg").html(result);
				$("#form_msg").css("color", "red");
				window.location.href="/admin/adminindex";
		});

		return false;
	})
})
