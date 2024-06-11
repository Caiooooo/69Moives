$(function(){
	$("input[name='email']").blur(function(){
		var email = $(this).val();
		
		if(formObj.checkNull("email","Email不能为空！") && formObj.checkEmail()){
			$.post("/index/checkemail",{"email":email},function(result){
				$("#email_msg").text(result);
				$("#email_msg").css("color","red");
			});
		}
	});
	
	$("input[name='passw1']").blur(function(){
		formObj.checkNull("passw1","密码不能为空！");
		formObj.checkPassword();
		
	});
	
	$("input[name='passw2']").blur(function(){
		formObj.checkNull("passw2","确认密码不能为空！");
		formObj.checkPassword();
	});
	
	$("form").submit(function(){
		var flag=true;
		flag=formObj.checkNull("email","Email不能为空！") && flag;
		flag=formObj.checkNull("passw1","密码不能为空！") && flag;
		flag=formObj.checkNull("passw2","确认密码不能为空！") && flag;
		flag=formObj.checkEmail() && flag;
		flag=formObj.checkPassword() && flag;
		
		var email = $("input[name='email']").val();
		var passw1 = $("input[name='passw1']").val();		
		if(flag){
			$.post("/index/doRegister",{"email":email,"passw1":passw1},function(result){
				if(result=="注册成功"){
					alert(result);
					window.location.href("/index");
				}else{}
					$("#form_msg").html(result);
					$("#form_msg").css("color","red");
			});		
		}
		return false;		
	});	
});

var formObj={
		"checkPassword":function(){
			var passw1=$("input[name='passw1']").val();
			var passw2=$("input[name='passw2']").val();
			if(passw1.length>0 &&passw2.length>0){
				if(passw1!=passw2){
					this.setMsg("passw1","两次密码不一致！");
					this.setMsg("passw2","两次密码不一致！");
					return false;
				}else{
					this.setMsg("passw1","");
					this.setMsg("passw2","");
				}
			}
			return true;
		},
		"checkEmail":function(){
			var email=$("input[name='email']").val();
			var regExp=/^\w+@\w+(\.\w+)+$/;
			if(email.length>0){
				if(!regExp.test(email)){
					this.setMsg("email","email格式不正确！");
					return false;
				}
			}
			return true;
		},
		"checkNull":function(name,msg){
			var value =$("input[name='"+ name +"']").val();
			if(value.length ==0){
				this.setMsg(name,msg);
				return false;
			}else{
				this.setMsg(name,"");
			}
			return true;
		},
		"setMsg":function(name,msg){
			$("#"+name+"_msg").text(msg);
			$("#"+name+"_msg").css("color","red");
	//		$("#passw2_msg").css("color","red");
		}
		
}

