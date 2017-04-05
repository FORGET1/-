 var pointDev = new Array();
 var pointItem = new Array();
 
function searchDev(){
	var keyWord = $("#keyWord").val();
    var searchType = $("#searchType").val();
    var pointCode=$("#point_code").val();
    //alert(pointCode);

    window.location.href = "searchPointDev.do?keyWord="+keyWord+"&searchType="+searchType+"&pointCode="+pointCode+"&page=1";
}

function searchItem(){
	var keyWord = $("#keyWord").val();
    var searchType = $("#searchType").val();
    var pointCode=$("#point_code").val();
    //alert(pointCode);

    window.location.href = "pointSearchItem.do?keyWord="+keyWord+"&searchType="+searchType+"&pointCode="+pointCode+"&page=1";
}

function checkChange(checkBox) {
 	if($(checkBox).is(':checked')){
 		console.log("checked");
 		pointDev.push($(checkBox).val());
 		console.log(pointDev);
 	}else{
 		pointDev.splice(jQuery.inArray($(checkBox).val(),pointDev),1); 
 		$('#check-all').attr("checked",false);
 		
 		console.log(pointDev);
 	}
 }
 
 function checkAll(checkBox){
 	if($(checkBox).is(':checked')){
 		console.log("all checked");
 		for(var i=0;i<$("[name = point]:checkbox").length;i++){
 			if($("[name = point]:checkbox").eq(i).is(":checked") == false){
 				$("[name = point]:checkbox").eq(i).click();
 			}	
 		}
 		console.log(pointDev);
 	}else{
 		console.log("all unchecked");
 		for(var i=0;i<$("[name = point]:checkbox").length;i++){
 			$("[name = point]:checkbox").eq(i).click();
 			}	
        //$("[name = point]:checkbox").eq(0).click();
        console.log(pointDev); 
 	}
 }
 
 function checkChanges(checkBox) {
	 	if($(checkBox).is(':checked')){
	 		console.log("checked");
	 		pointItem.push($(checkBox).val());
	 		console.log(pointItem);
	 	}else{
	 		pointItem.splice(jQuery.inArray($(checkBox).val(),pointItem),1); 
	 		$('#check-all').attr("checked",false);
	 		
	 		console.log(pointItem);
	 	}
	 }
	 
	 function checkAlls(checkBox){
	 	if($(checkBox).is(':checked')){
	 		console.log("all checked");
	 		for(var i=0;i<$("[name = point]:checkbox").length;i++){
	 			if($("[name = point]:checkbox").eq(i).is(":checked") == false){
	 				$("[name = point]:checkbox").eq(i).click();
	 			}	
	 		}
	 		console.log(pointItem);
	 	}else{
	 		console.log("all unchecked");
	 		for(var i=0;i<$("[name = point]:checkbox").length;i++){
	 			$("[name = point]:checkbox").eq(i).click();
	 			}
	        //$("[name = point]:checkbox").eq(0).click();
	        console.log(pointItem); 
	 	}
	 }
 
function addConfirms(){
	Lobibox.confirm({
		   title:"警告",
			msg: "是否添加该消息 ?",
		    buttons:{
		    	 ok: {
		             'class': 'btn btn-info',
		            
		             text:"确定"
		         },
		         cancel:{
		        	 'class':'btn btn-default',
		        	 text:"取消"
		         }
		    },
		    callback: function (lobibox, type) {
		    	if(type == "ok"){
		    			console.log("the user clicked confirm");
		    			window.close();
		    		
		    	}else if(type == "cancel"){
		    		
		    	}
		    }
		});
	
		
		
	}
 
 function addPointDev(){
	 console.log(pointDev);
	 Lobibox.confirm({
		   title:"警告",
			msg: "是否添加设备 ?",
		    buttons:{
		    	 ok: {
		             'class': 'btn btn-info',
		            
		             text:"确定"
		         },
		         cancel:{
		        	 'class':'btn btn-default',
		        	 text:"取消"
		         }
		    },
		    callback: function (lobibox, type) {
		    	if(type == "ok"){
		    			console.log("the user clicked confirm");
		    			for(var i=0;i < pointDev.length;i++){
		    				$.ajax({
		    	             url:"../point/addPointDev.do",
		    	             data:{"pointCode":$("#point_code").val(),"devCode":pointDev[i]},
		    	             dataType:"text",
		    	             async:false,
		    	             type:"POST",
		    	             success:function(result){
		    	                 //console.log(devCode);
		    	                 //alert("添加成功");
		    	                 //window.location.href="getTaskList.do?page=1&taskStatus=ALL";
		    	             }
		    	             
		    	          });
		    			}
		    			window.close();
		    		
		    	}else if(type == "cancel"){
		    		
		    	}
		    }
		});
 }
 function addPointItem(){
	 Lobibox.confirm({
		   title:"警告",
			msg: "是否添加条目 ?",
		    buttons:{
		    	 ok: {
		             'class': 'btn btn-info',
		            
		             text:"确定"
		         },
		         cancel:{
		        	 'class':'btn btn-default',
		        	 text:"取消"
		         }
		    },
		    callback: function (lobibox, type) {
		    	if(type == "ok"){
		    			console.log("the user clicked confirm");
		    			 for(var i=0;i < pointItem.length;i++){
		    					$.ajax({
		    		          url:"../point/addPointItem.do",
		    		          data:{"pointCode":$("#point_code").val(),"itemCode":pointItem[i] },
		    		          dataType:"text",
		    		          async:false,
		    		          type:"POST",
		    		          success:function(result){
		    		             
		    		              //alert("添加成功");
		    		              //window.location.href="getTaskList.do?page=1&taskStatus=ALL";
		    		          }
		    		       });
		    				}
		    			window.close();
		    		
		    	}else if(type == "cancel"){
		    		
		    	}
		    }
		});
	
	 //alert("添加成功");

}