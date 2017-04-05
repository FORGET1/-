function search(url){
    var keyWord = $("#keyWord").val();
    var searchType = $("#searchType").val();
    window.location.href = url+".do?keyWord="+keyWord+"&searchType="+searchType+"&page=1";
 }
 
 function getCookie(c_name){
    if (document.cookie.length>0){ 
        c_start=document.cookie.indexOf(c_name + "=")
        if (c_start!=-1)
        { 
             c_start=c_start + c_name.length+1 
            c_end=document.cookie.indexOf(";",c_start)
            if (c_end==-1) c_end=document.cookie.length
                return unescape(document.cookie.substring(c_start,c_end))
        } 
    }   
    return ""
}

function setCookie(c_name,value,expiredays)
{
    var exdate=new Date()
    exdate.setDate(exdate.getDate()+expiredays)
    document.cookie=c_name+ "=" +escape(value)+
    ((expiredays==null) ? "" : "; expires="+exdate.toGMTString())
}

$(document).ready(function(){
	 $(document.body).css({
		   "overflow-x":"hidden",
		   "overflow-y":"auto"
		 });
	$(".left-menu-ul-child").slideUp();
	
	$(".left-menu-ul-child").prev().on("click",function(param){
		console.log(param.toElement);
		$(param.toElement).parent().next().slideToggle();
	});
	if(typeof(Storage)!=="undefined")
	{
		console.log(sessionStorage.adminCode)
	   if(sessionStorage.adminCode != '' && sessionStorage.adminCode != null){
		   $("#current-admin").text("当前用户:   "+sessionStorage.adminCode);
	   }else{
		   window.location.href = "./";
	   }
	} else {
	    // 抱歉! 不支持 web 存储。
		console.log("抱歉! 不支持 web 存储。");
		if(getCookie("adminCode") == '' || getCookie("adminCode") == null){
			window.location.href = "./";
		}
	    $("#current-admin").text("当前用户:   "+getCookie("adminCode"));
	}
//	$.ajax({
// 		url:"../warning/appGetWarningTitle.do",
// 		data:{},
// 		dataType:"json",
// 		type:"GET",
// 		success:function(result){
// 			console.log(result)
// 			$("marquee").text("报警信息："+result['warningTitle']);
// 			}
// 		});
});

function deleteConfirm(url) {
	Lobibox.confirm({
		   title:"  警 告",
			msg: "确定删除此条记录吗 ?",
		    buttons:{
		    	 ok: {
		             'class': 'btn btn-info',
		             text:"确 定"
		         },
		         cancel:{
		        	 'class':'btn btn-default',
		        	 text:"取 消"
		         }
		    },
		    callback: function (lobibox, type) {
		    	if(type == "ok"){
		    		console.log("ok");
//		    		 window.location.href = url;
		    		$.ajax({
		    			url:url,
		    			type:"GET",
		    			success:function(result){
		    				if(result == "SUCCESS"){
		    					Lobibox.confirm({
		    						   title:" 提示",
		    							msg: " 删除成功",
		    						    buttons:{
		    						    	 ok: {
		    						             'class': 'btn btn-info',
		    						             text:"确 定"
		    						         },
		    						       
		    						    },
		    					});
		    					setTimeout(function(){
		    						console.log("shanchuchenggong");
		    						
		    						location.reload(true);
		    						},500);
		    				}else{
		    					Lobibox.confirm({
		    						   title:" 提示",
		    							msg: " 删除失败",
		    						    buttons:{
		    						    	 ok: {
		    						             'class': 'btn btn-info',
		    						             text:"确 定"
		    						         },
		    						       
		    						    },
		    					});
		    				}
		    			}
		    		});
		    		
		    	}else if(type == "cancel"){}
		    }
		});
}

function deleteConfirmTry(deleteUrl,pageUrl) {
	Lobibox.confirm({
		   title:"  警 告",
			msg: "确定删除此条记录吗 ?",
		    buttons:{
		    	 ok: {
		             'class': 'btn btn-info',
		            
		             text:"确 定"
		         },
		         cancel:{
		        	 'class':'btn btn-default',
		        	 text:"取 消"
		         }
		    },
		    callback: function (lobibox, type) {
		    	if(type == "ok"){
		    		console.log("ok");
		    		$.ajax({
			     		url:deleteUrl,
			     		data:{},
			     		dataType:"text",
			     		type:"GET",
			     		success:function(result){
			     			console.log(result)
			     			if(result=="FAILED"){
			     				Lobibox.alert('error', {
			     					title:"  警告",
			                        msg: "该记录存在下级信息，不能删除",
			                        buttons:{
			                        	ok:{
			                        		text:"确定"
			                        	}
			                        }
			                    });
			     				
			     			}else{
			     				Lobibox.confirm({
		    						   title:" 提示",
		    							msg: " 删除成功",
		    						    buttons:{
		    						    	 ok: {
		    						             'class': 'btn btn-info',
		    						             text:"确 定"
		    						         },
		    						       
		    						    },
		    					});
		    					setTimeout(function(){
		    						console.log("shanchuchenggong");
		    						
		    						location.reload(true);
		    						},500);
			     			}
			     			}
			     		});
		    	}else if(type == "cancel"){
		    		
		    	}
		    }
		});
	
}
function safeQuit() {
	Lobibox.confirm({
		   title:"  警 告",
			msg: "确定退出 ?",
		    buttons:{
		    	 ok: {
		             'class': 'btn btn-info',
		             text:"确 定"
		         },
		         cancel:{
		        	 'class':'btn btn-default',
		        	 text:"取 消"
		         }
		    },
		    callback: function (lobibox, type) {
		    	if(type == "ok"){
		    		console.log("ok");
		    		if(typeof(Storage)!=="undefined")
			    	{
			    		sessionStorage.adminCode = '';
			    		window.location.href = "./";
			    	   
			    	} else {
			    	    // 抱歉! 不支持 web 存储。
			    		console.log("抱歉! 不支持 web 存储。");
			    		setCookie("adminCode",'',365);
			    		window.location.href = "./";
			    	}
		    	}else if(type == "cancel"){}
		    }
		});
	
}