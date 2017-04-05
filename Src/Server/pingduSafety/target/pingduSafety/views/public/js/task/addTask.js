/**
 * 
 */
 
 var deptCode = '';
 var deptName = '';
 var pointList = new Array();
 var currentPage = 1;
 var allPage = 0;
 
 $(document).ready(function(){
//	 var testList = ["123","234"];
//	 console.log(testList.indexOf("123"))

 	$(".point-row").hide();
 	$("#show-point").click(function(){
         $(".point-row").slideToggle();
    });
 	$.ajax({
        url:"../department/getDeptByParentAndLevel.do",
        data:{"parentCode":"","deptLevel":1},
        dataType:"json",
        type:"GET",
        success:function(result){
            console.log(result);
           for(var i = 0;i < result.length;i ++){
           	$("#firstDept").append("<option value='"+result[i].deptCode+"'>"+result[i].deptName+"</option>");
           }
        }
     });
    
    $("#task").parent().next().slideDown();
 });
 
 function showSecondDept(){
 	if($("#firstDept").val() != "first"){
 		deptCode = $("#firstDept").val();
 		deptName = $("#firstDept").find("option:selected").text(); 
 		$.ajax({
        url:"../department/getDeptByParentAndLevel.do",
        data:{"parentCode":$("#firstDept").val(),"deptLevel":2},
        dataType:"json",
        type:"GET",
        success:function(result){
            console.log(result);
            $("#secondDept").children().remove();
            $("#secondDept").append("<option value='second'>选择二级部门</option>");
           for(var i = 0;i < result.length;i ++){
            $("#secondDept").append("<option value='"+result[i].deptCode+"'>"+result[i].deptName+"</option>");
           }
        }
     });
     $.ajax({
        url:"../user/getContacts.do",
        data:{"deptCode":$("#firstDept").val()},
        dataType:"json",
        type:"GET",
        success:function(result){
            console.log(result);
            $("#taskPerson").children().remove();
           
           for(var i = 0;i < result.length;i ++){
            $("#taskPerson").append("<option value='"+result[i].userCode+"'>"+result[i].userName+"</option>");
           }
        }
     });
 	}else{
 		deptCode = '';
 		deptName = '';
 		$("#secondDept").children().remove();
        $("#secondDept").append("<option value='second'>选择二级部门</option>");
        $("#taskPerson").children().remove();
 	}
 }
 
 function showThirdDept(){
 	if($("#secondDept").val() != "second"){
 		deptCode = $("#secondDept").val();
 		deptName = $("#secondDept").find("option:selected").text(); 
        $.ajax({
        url:"../department/getDeptByParentAndLevel.do",
        data:{"parentCode":$("#secondDept").val(),"deptLevel":3},
        dataType:"json",
        type:"GET",
        success:function(result){
            console.log(result);
            $("#thirdDept").children().remove();
            $("#thirdDept").append("<option value='third'>选择三级部门</option>");
           for(var i = 0;i < result.length;i ++){
            $("#thirdDept").append("<option value='"+result[i].deptCode+"'>"+result[i].deptName+"</option>");
           }
        }
     });
      $.ajax({
        url:"../user/getContacts.do",
        data:{"deptCode":$("#secondDept").val()},
        dataType:"json",
        type:"GET",
        success:function(result){
            console.log(result);
            $("#taskPerson").children().remove();
           
           for(var i = 0;i < result.length;i ++){
            $("#taskPerson").append("<option value='"+result[i].userCode+"'>"+result[i].userName+"</option>");
           }
        }
     });
    }else{
    	deptCode = '';
    	deptName = '';
    	$("#thirdDept").children().remove();
        $("#thirdDept").append("<option value='third'>选择三级部门</option>");
        $("#taskPerson").children().remove();
    }
 }
 
 function thirdDeptChange() {
 	if($("#thirdDept").val() != "third"){
 		deptCode = $("#thirdDept").val();
 		deptName = $("#thirdDept").find("option:selected").text(); 
 		$.ajax({
        url:"../user/getContacts.do",
        data:{"deptCode":$("#thirdDept").val()},
        dataType:"json",
        type:"GET",
        success:function(result){
            console.log(result);
            $("#taskPerson").children().remove();
           
           for(var i = 0;i < result.length;i ++){
            $("#taskPerson").append("<option value='"+result[i].userCode+"'>"+result[i].userName+"</option>");
           }
        }
     });
 	}else{
 		deptCode = '';
 		deptName = '';
 		$("#taskPerson").children().remove();
 	}
 }
 
 function showPoint() {
	
 	if($(".point-row").is(":hidden")){
     //  ajaxPointList();
 		getAllPoint();
     }
 }
 
 function refreshPoint(){
 	// ajaxPointList();
	 getAllPoint();
 }
 function ajaxPointList() {
	if(deptCode!=''){
     $.ajax({
        url:"../point/getDeptPoint.do",
        data:{"deptCode":deptCode,"page":currentPage},
        dataType:"json",
        type:"GET",
        success:function(result){
            console.log(result);
             var data = result["deptpoints"];
             $("#point-table tbody").children().remove();
             for(var i = 0;i < data.length;i ++){
                
                $("#point-table tbody").append("<tr>" +
                                "<td>"+(i+1)+"</td>" +
                                "<td>"+data[i].pointName+"</td>"+
                                "<td>"+data[i].pointInfo+"</td>"+
                                "<td><input name='point' class='item-check' type='checkbox' value='"+data[i].pointCode+"' onchange='checkChange(this)'>选择</td>" +
                                
                                        "</tr>");
             }
             for(var i = 0;i < data.length;i ++){
            	 if(pointList.indexOf(data[i].pointCode) >= 0){
            		 $(".item-check").eq(i).attr("checked",true);
            	 }
             }
            currentPage = result["page"];
            allPage = result['lastpage'];
            $("#pre_page").remove();
            $("#next_page").remove();
            $("#last_page").remove();
            
            if (currentPage > 1) {
                $("#first_page").after("<span id='pre_page' onclick='pointPage("+(currentPage-1)+")'>上一页</span> ");
            }
            if (currentPage < allPage) {
                $("#all_page").before("<span id='next_page' onclick='pointPage("+(currentPage+1)+")'>下一页</span> ");
            }
            $("#all_page").before("<span id='last_page' onclick='pointPage("+allPage+")'>尾页</span> ");
            $("#current_page").text("第"+currentPage+"页");
            $("#all_page").text("共"+allPage+"页");
            
      }});
	}
 }
 function getAllPoint() {
	 if(deptCode!=''){
		$.ajax({
			url:"../point/getAllDeptPoint.do?deptCode="+deptCode,
		    
		    dataType:"json",
			type:"GET",
			success:function(result){
				console.log(result);
				var data = result;
				 $("#pointsDiv").children().remove();
				 $("#pointsDiv").append("<div class='col-sm-2 point'><input type='checkbox'  onchange='checkAll(this)' id='check-all'/>&nbsp;&nbsp;全选</div>");
				for(var i=0;i<result.length;i++){
					$("#pointsDiv").append("<div class='col-sm-2 point'><input type='checkbox' name='point' value='"+data[i].pointCode+"' onchange='checkChange(this)'/>&nbsp;&nbsp;"+data[i].pointCode+"."+data[i].pointName+"</div>");
				}
			}
		});
	 }
	}
  function pointPage(page) {
    currentPage = page;
    ajaxPointList();
 }
 
 function checkChange(checkBox) {
 	if($(checkBox).is(':checked')){
 		console.log("checked");
 		pointList.push($(checkBox).val());
 		console.log(pointList);
 	}else{
 		pointList.splice(jQuery.inArray($(checkBox).val(),pointList),1); 
 		$('#check-all').attr("checked",false);
 		
 		console.log(pointList);
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
 		console.log(pointList);
 	}else{
 		console.log("all unchecked");
 		for(var i=0;i<$("[name = point]:checkbox").length;i++){
 			$("[name = point]:checkbox").eq(i).click();
 		}
        console.log(pointList); 
 	}
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
	
function getAdminCode(){
	if(typeof(Storage)!=="undefined")
	{
		return sessionStorage.adminCode;
	   
	} else {
	    // 抱歉! 不支持 web 存储。
		return getCookie("adminCode");
	}
}
 function addTask(){
	 
 	if(pointList.length == 0){
 		Lobibox.alert("warning",{
 			title:"警告",
 			msg:"请选择至少一个项点！！",
 			buttons:{
 				ok:{
 					text:"确定"
 				}
 			}
 		});
 		
 		return;
 	}else{
 		Lobibox.confirm({
 		   title:"提示",
 			msg: "是否添加新任务 ?",
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
 		    		console.log("ok");
// 		    		console.log($("input[name='taskType']:checked").val());
// 		    		console.log($("input[name='taskType']:checked").next("span").text());
 		    		$("#modal").css("display","block");
 		    		 for(var i=0;i<$(".diamond").length;i++){
 		    			 $(".diamond").eq(i).css({"animation":"jump 2s","animation-iteration-count":"infinite","animation-delay":(i*0.5)+"s"});
 		    		 }
 		    			$.ajax({
 		                   url:"addTask.do",
 		                   data:{"deptCode":deptCode,"userCode":$("#taskPerson").val(),
 		                       "patrolTypeCode":$("input[name='taskType']:checked").val(),"pointList":pointList,
 		                       "userName":$("#taskPerson").find("option:selected").text(),
 		                       "deptName":deptName,"patrolTypeName":$("input[name='taskType']:checked").next("span").text(),
 		                       "taskOrderArtificialCode":getAdminCode()},
 		                   dataType:"text",
 		                   type:"POST",
 		                   success:function(result){
 		                	  $("#modal").css("display","none");
 		                	   if(result == "SUCCESS"){
 		                		  Lobibox.confirm({
 		 		               		   title:"提示",
 		 		               			msg: "添加成功 ！",
 		 		               		    buttons:{
 		 		               		    	 ok: {
 		 		               		             'class': 'btn btn-info',
 		 		               		             text:"确定"
 		 		               		         },
 		 		               		        
 		 		               		    },
 		 		               		    callback: function (lobibox, type) {
 		 		               		    }
 		 		                	  });
 		                		  setTimeout(' window.location.href="getTaskList.do?page=1&taskStatus=ALL"',2000);
 		                	   }else{
 		                		  Lobibox.confirm({
		 		               		   title:"提示",
		 		               			msg: "添加失败 ！",
		 		               		    buttons:{
		 		               		    	 ok: {
		 		               		             'class': 'btn btn-info',
		 		               		             text:"确定"
		 		               		         },
		 		               		        
		 		               		    },
		 		               		    callback: function (lobibox, type) {
		 		               		    }
		 		                	  });
 		                	   }   
 		                   }
 		                });
 		    		
 		    	}else if(type == "cancel"){}
 		    }
 		});
 	}
 }
 
 function reformTask() {
	 if(deptCode==''){
		 Lobibox.alert("warning",{
	 			title:"警告",
	 			msg:"请选择部门！！",
	 			buttons:{
	 				ok:{
	 					text:"确定"
	 				}
	 			}
	 		});
		
	 }else if($("#taskPerson").val()==null) {
		 Lobibox.alert("warning",{
	 			title:"警告",
	 			msg:"请选择巡检人员",
	 			buttons:{
	 				ok:{
	 					text:"确定"
	 				}
	 			}
	 		});
		 
	 }else{
		 Lobibox.confirm({
	 		   title:"提示",
	 			msg: "是否整改任务 ?",
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
	 		    		console.log("ok");
//	 		    		console.log($("input[name='taskType']:checked").val());
//	 		    		console.log($("input[name='taskType']:checked").next("span").text());
//	 		    		$("#modal").css("display","block");
//	 		    		 for(var i=0;i<$(".diamond").length;i++){
//	 		    			 $(".diamond").eq(i).css({"animation":"jump 2s","animation-iteration-count":"infinite","animation-delay":(i*0.5)+"s"});
//	 		    		 }
	 		    			$.ajax({
	 		                   url:"rectificationTask.do",
	 		                   data:{"deptCode":deptCode,"userCode":$("#taskPerson").val(),
	 		                       "taskCode":$("#oldTaskCode").val(),
	 		                       "userName":$("#taskPerson").find("option:selected").text(),
	 		                       "deptName":deptName,"handleSuggestion":$("#handleSuggestion").val(),
	 		                       "taskOrderArtificialCode":getAdminCode()},
	 		                   dataType:"text",
	 		                   type:"POST",
	 		                   success:function(result){
	 		                       console.log(result);
	 		                       window.location.href="getTaskList.do?page=1&taskStatus=ALL";
	 		                   }
	 		                });
	 		    		
	 		    	}else if(type == "cancel"){}
	 		    }
	 		});
	 }
 }