/**
 * 
 */
$(document).ready(function(){
	$(".time2").hide();
	$.ajax({
		url:"../task/getAutoTaskTime.do",
		success:function(result){
			console.log(result);
			$("#autoTimeSpan").text(result);
		}
	});
});
function changeTime(){
	$("#btnChangeTime").hide();
	$("#btnConfirmTime").show();
	$(".time1").hide();
	$(".time2").show();
	$("#autoTime").val($("#autoTimeSpan").text());
}
function confirmTime(){
	
	
	Lobibox.confirm({
		   title:"  警 告",
			msg: "确定修改吗 ?",
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
		    		var time  = $("#autoTime").val()+":00";
		    		$.ajax({
		    			url:"../task/setAutoTask.do",
		    			data:{"time":time},
		    			success:function(result){
		    				if(result == "success"){
		    					$("#btnChangeTime").show();
		    					$("#btnConfirmTime").hide();
		    					$(".time1").show();
		    					$(".time2").hide();
		    					$("#autoTimeSpan").text(time);
		    					Lobibox.confirm({
		    						   title:"提 示",
		    							msg: "修改成功 ！",
		    						    buttons:{
		    						    	 ok: {
		    						             'class': 'btn btn-info',
		    						             text:"确 定"
		    						         },
		    						         
		    						    },
		    						    callback: function (lobibox, type) {
		    						    	if(type == "ok"){
		    						    				    						    	}
		    						    }
		    					});
		    				}else{
		    					Lobibox.confirm({
		    						   title:"提 示",
		    							msg: "修改失败 ！",
		    						    buttons:{
		    						    	 ok: {
		    						             'class': 'btn btn-info',
		    						             text:"确 定"
		    						         },
		    						         
		    						    },
		    						    callback: function (lobibox, type) {
		    						    	if(type == "ok"){
		    						    				    						    	}
		    						    }
		    					});
		    				}
		    			}
		    		});
		    	}
		    }
	});
	
}
function change(){
	$("#btnChange").hide();
	$("#btnConfirm").show();
	$("select").attr("disabled",false);
	$.ajax({
		url:"../user/getPatrolPersons.do",
		type:"GET",
		dataType:"json",
		success:function(result){
			console.log(result);
			for(var i=0;i<$("select").length;i++){
				
//				console.log(value);
				var select = $("select").eq(i);
				var value = select.val();
				select.children().remove();
				for(var j=0;j<result.length;j++){
					select.append("<option value='"+result[j].userCode+"'>"+result[j].userName+"</option>");
				}
				select.val(value);
			}
		}
	});
	
}

function confirm(){
	var isOK = true;
	Lobibox.confirm({
		   title:"  警 告",
			msg: "确定修改吗 ?",
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
		    		var date = ["周日","周一","周二","周三","周四","周五","周六"];
		    		 for(var i=1;i<8;i++){
		    			 var options = $(".day"+i);
		    			 for(var j=0;j<options.length-1;j++){
		    				 for(var k=j+1;k<options.length;k++){
		    					 if(options.eq(j).val() == options.eq(k).val()){
		    						 isOK = false;
		    						 Lobibox.confirm({
		    							   title:"提 示",
		    								msg: date[i-1]+" 人员重复，请修改！",
		    							    buttons:{
		    							    	 ok: {
		    							             'class': 'btn btn-info',
		    							             text:"确 定"
		    							         },
		    							         
		    							    },
		    							    callback: function (lobibox, type) {
		    							    	if(type == "ok"){
		    							    		
		    							    		return;
		    							    	}else if(type == "cancel"){}
		    							    }
		    							});
		    					 }
		    				 }
		    			 }
		    		 }
		    		 if(isOK == false){
		    			 return;
		    		 }
		    		 var personss = [];
		    		 for(var i=1;i<8;i++){
		    			 var options = $(".day"+i);
		    			 var persons = [];
		    			 for(var j=0;j<options.length;j++){
		    				 persons.push(options.eq(j).val());
		    				 
		    			 }
		    			 personss.push(persons);
		    		 }
//		    		 console.log(personss);
		    		 var data = {};
		    		 for(var i=1;i<8;i++){
		    			 data["list"+i] = personss[i-1];
		    		 }
		    		 console.log(data)
		    		 $.ajax({
		    			url:"modifyPersonArrangeList.do",
		    			data:data,
		    			type:"POST",
		    			success:function(result){
		    				console.log(result)
		    			}
		    		 });
		    		 $("#btnChange").show();
		    			$("#btnConfirm").hide();
		    			$("select").attr("disabled",true);
		    	}else if(type == "cancel"){}
		    }
		});
	
}