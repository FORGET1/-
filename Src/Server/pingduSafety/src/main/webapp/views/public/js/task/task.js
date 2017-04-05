/**
 * 
 */

var taskList = [];
 function searchTask() {
 	$("#task-form").submit();
 }
 
 function getImg(taskCode) {
	 $.ajax({
		 url:"getImage.do",
		 data:{"taskCode":taskCode},
		 dataType:"json",
		 type:"GET",
		 success:function(result) {
			 for(var i=0;i<result.length;i++){
				 var image = "<div class='col-sm-4'><img src='getRealImage.do?path="+result[i]+"' /></div>";
				 $(".photos-row").append(image);
			 }
		 }
	 });
 }
 
 function getImage(before,after){
	
	 $.ajax({
		 url:"getImage.do",
		 data:{"taskCode":before},
		 dataType:"json",
		 type:"GET",
		 success:function(result) {
			 console.log(result);
			 for(var i=0;i<result.length;i++){
				 var image = "<div class='col-sm-4'><img src='getRealImage.do?path="+result[i]+"' /></div>";
				 console.log(result[i]);
				 $(".photo-row .before-row").append(image);
			 }
		 }
	 });
	 $.ajax({
		 url:"getImage.do",
		 data:{"taskCode":after},
		 dataType:"json",
		 type:"GET",
		 success:function(result) {
			 console.log(result);
			 for(var i=0;i<result.length;i++){
				 var image = "<div class='col-sm-4'><img src='getRealImage.do?path="+result[i]+"' /></div>";
				 $(".photo-row .after-row").append(image);
			 }
		 }
	 });
 }
 
 function chooseTask(checkBox){
	 if ($(checkBox).is(':checked')) {
			
			taskList.push($(checkBox).val());
			
		} else {
			taskList.splice(jQuery.inArray($(checkBox).val(), taskList), 1);
			$('#check-all').attr("checked", false);
		}
 }
 function checkAll(checkBox){
	 	if($(checkBox).is(':checked')){
	 		
	 		for(var i=0;i<$("[name = task]:checkbox").length;i++){
	 			if($("[name = task]:checkbox").eq(i).is(":checked") == false){
	 				$("[name = task]:checkbox").eq(i).click();
	 			}	
	 		}
	 		console.log(taskList);
	 	}else{
	 		
	 		for(var i=0;i<$("[name = task]:checkbox").length;i++){
	 			$("[name = task]:checkbox").eq(i).click();
	 		}
	 		console.log(taskList);
	 	}
}
 
 function deleteTasks(){
	 if (taskList.length == 0) {
			Lobibox.confirm({
				title : "提示",
				msg : "您尚未选择任务",
				//confirmButton : "确定"
				buttons:{
			    	 ok: {
			             'class': 'btn btn-info',
			            
			             text:"确定"
			         },
			         
			    },

			});
		}
		else {
			Lobibox.confirm({
				   title:"警告",
					msg: "是否批量删除这些任务 ?",
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
				    		console.log(taskList);
				    		$("#modal").css("display","block");
		 		    		 for(var i=0;i<$(".diamond").length;i++){
		 		    			 $(".diamond").eq(i).css({"animation":"jump 2s","animation-iteration-count":"infinite","animation-delay":(i*0.5)+"s"});
		 		    		 }
				    		$.ajax({
								url:"deleteTasks.do",
								data:{"taskCode":taskList},
								
								dataType:"text",
								type:"POST",
								success:function(result){
									//console.log(result);
									location.reload(true);
								}
							});
				    	}else if(type == "cancel"){
				    		
				    	}
				    }
				});
		
	}	 
 }