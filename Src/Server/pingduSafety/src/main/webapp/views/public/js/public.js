
function turnToPage(url){
	window.location.href=url;
}
function judgeUser() {
	
	if(sessionStorage.user == "null" || sessionStorage.user == "" || sessionStorage.user == "undefined"){
		
		window.location.href = "./login.html"
	}
}

function safeQuit(){
	showDialog("退出",function(){
		sessionStorage.user = null;
		window.location.href = "./login.html"
	})
	
}