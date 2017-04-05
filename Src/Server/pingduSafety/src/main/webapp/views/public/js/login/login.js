/**
 * 
 */
 
 function login() {

 	var userCode = $("#userCode").val();
 	var userPwd = $("#password").val();
 	var captcha = $("#captcha").val();
 	var validate = true;
    
 	if(userCode == null || userCode ==''){
 		$("#userCode").css({
 			"border-color":"red",
 			"border-style":"dashed",
 			"border-width":"2px"
 		});
 		validate = false;
 	}
 	if(userPwd == null || userPwd ==''){
 		$("#password").css({
 			"border-color":"red",
 			"border-style":"dashed",
 			"border-width":"2px"
 		});
 		validate = false;
 	}
 	if(captcha == null || captcha ==''){
 		$("#captcha").css({
 			"border-color":"red",
 			"border-style":"dashed",
 			"border-width":"2px"
 		});
 		validate = false;
 	}
 	if (validate == false) {
 		return;
 	}
 	
   
 	$.ajax({
 		url:"login.do",
 		data:{"userCode":userCode,"captcha":captcha},
 		dataType:"json",
 		type:"POST",
 		success:function(result){
 			
 			var data = result["status"];
 			
 			if(data == "4000"){
 				var pw = result["userPwd"];
 				var salt = result["userSalt"];
 				userPwd = salt+userPwd;
 				
 				userPwd = hex_md5(userPwd);
 				
 				if(pw == userPwd){
 					if(typeof(Storage)!=="undefined")
 					{
 						var str = JSON.stringify(result); 
 	 				    //存入 
 	 				    sessionStorage.user = str; 
 	 				    switch(result["userType"]){
 	 				    case 1:
 	 				    	window.location.href = "suindeperx.html";
 	 				    	break;
 	 				    case 2:
 	 				    	window.location.href = "ainjdejx.html";
 	 				    	break;
 	 				    case 3:
 	 				    	window.location.href = "ainjdebx.html";
 	 				    	break;
 	 				    case 4:
 	 				    	window.location.href = "qinydex.html";
 	 				    	break;
 	 				    }
 	 				   
 					} else {
 					    // 抱歉! 不支持 web 存储。
// 						var a = new sslAlert({
// 	 			            "titleName":"请使用最新版浏览器",
// 	 			            "cancleBtn":"取消",
// 	 			            "okBtn":"确认",
// 	 			           
// 	 			        });
// 	 			        a.show();
 						alert("请使用最新浏览器");
 					}		
 					
 				}else{
// 					var a = new sslAlert({
// 			            "titleName":"密码错误",
// 			            "cancleBtn":"取消",
// 			            "okBtn":"确认",
// 			        });
// 			        a.show();
 					alert("密码错误");
 					
	 				$("#password").css({
		 			"border-color":"red",
		 			"border-style":"dashed",
		 			"border-width":"2px"
		 			});
	 				refreshCaptcha();
	 				$("#password").val("");
	 				$("#captcha").val("");
 				}
 				
 			}
 			else if(data == "4001"){
// 				var a = new sslAlert({
// 		            "titleName":"工号不存在",
// 		            "cancleBtn":"取消",
// 		            "okBtn":"确认"
// 		        });
// 		        a.show();
 				alert("工号不存在");
 				$("#userCode").css({
	 			"border-color":"red",
	 			"border-style":"dashed",
	 			"border-width":"2px"
	 			});
 				refreshCaptcha();
 				$("#captcha").val("");
 			}
 			else if(data == "4003"){
 				console.log("4003");
// 				var a = new sslAlert({
// 		            "titleName":"验证码错误",
// 		            "cancleBtn":"取消",
// 		            "okBtn":"确认",
// 		        });
// 		        a.show();
 				alert("验证码错误");
 				
 				$("#captcha").css({
	 			"border-color":"red",
	 			"border-style":"dashed",
	 			"border-width":"2px"
	 			});
	 			refreshCaptcha();
	 			$("#captcha").val("");
 			}else if(data == "4002"){
// 				var a = new sslAlert({
// 		            "titleName":"您没有权限登录",
// 		            "cancleBtn":"取消",
// 		            "okBtn":"确认",
// 		            
//
// 		        });
// 		        a.show();
 				alert("您没有权限登录");

 			}
 		}

 	});
 }

 function inContent(arg) {
 	// body...
 	$(arg).css({
 		"border": "none",
 		"border-bottom": "1px solid #4d4d4d"
 	})
 }
 function refreshCaptcha() {
 	$.ajax({
 	  url:"captcha.do",
 	  success:function(result){
 	    $("#captcha-img").remove();
 	  	$("#captcha_a").append("<img alt='验证码' src='captcha.do' id='captcha-img' />");
 	  }
 	});
 }
 $(document).ready(function(){
	 document.onkeydown=function(event){
         var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==13){ // 按 enter 
           login();
          }
         
    }; 
 });
 function log(){
	 login();
 }
 