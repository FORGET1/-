/**
 * 
 */
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
function getUserCode(){
	if(typeof(Storage)!=="undefined")
	{
		return sessionStorage.adminCode;
	   
	} else {
	    // 抱歉! 不支持 web 存储。
		
		
	    return getCookie("adminCode");
	}
}
function backupDB(){
	
	Lobibox.confirm({
		   title:"  提示",
			msg: "确定备份数据库数据吗 ?",
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
		    			url:"backUpDataBase.do",
		    			data:{"userCode":getUserCode()},
		    			type:"GET",
		    			success:function(result){
		    				if(result=="SUCCESS"){
		    					Lobibox.confirm({
		    						   title:" 提示",
		    							msg: "备份成功！",
		    						    buttons:{
		    						    	 ok: {
		    						             'class': 'btn btn-info',
		    						             text:"确 定"
		    						         },
		    						        
		    						    },
		    						    
		    						});
		    					setTimeout(function() {
		    						location.reload(true);
								}, 500); 

		    				}else{
		    					Lobibox.confirm({
		    						   title:" 提示",
		    							msg: "备份失败！",
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
/**
 * 
 */
function deleteDB(){
	Lobibox.confirm({
		   title:"  警 告",
			msg: "确定删除数据库数据吗 ?",
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
		    			url:"deleteDataBase.do",
		    			data:{"userCode":getUserCode()},
		    			type:"GET",
		    			success:function(result){
		    				if(result == "SUCCESS"){
		    					Lobibox.confirm({
		    						   title:"  提示",
		    							msg: "删除成功 ！",
		    						    buttons:{
		    						    	 ok: {
		    						             'class': 'btn btn-info',
		    						             text:"确 定"
		    						         },
		    						         
		    						    },
		    						   
		    						});
		    					setTimeout(function() {
		    						location.reload(true);
								}, 500); 
		    				}else{
		    					Lobibox.confirm({
		    						   title:"  提示",
		    							msg: "删除失败 ！",
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
function deleteBackDB(code){
	Lobibox.confirm({
		   title:"  警 告",
			msg: "确定删除该条数据库数据吗 ?",
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
		    			url:"deleteBackupRecord.do",
		    			data:{"code":code},
		    			type:"GET",
		    			success:function(result){
		    				if(result == "SUCCESS"){
		    					Lobibox.confirm({
		    						   title:"  提示",
		    							msg: "删除成功 ！",
		    						    buttons:{
		    						    	 ok: {
		    						             'class': 'btn btn-info',
		    						             text:"确 定"
		    						         },
		    						         
		    						    },
		    						   
		    						});
		    					setTimeout(function() {
		    						location.reload(true);
								}, 500); 
		    					 location.reload(true);
		    				}else{
		    					Lobibox.confirm({
		    						   title:"  提示",
		    							msg: "删除失败 ！",
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