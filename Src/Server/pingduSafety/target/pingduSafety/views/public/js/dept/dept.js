/**
 * 
 */

var currentPage = 1;
var allPage = 0;
 
 $(document).ready(function(){
    $("#point_table").hide();
    $("#more_point").click(function(){
    	 $("#point_table").slideToggle();
    });
    inputList = ["#deptCode","#deptName","#contactName","#deptTel","#contactTel","#deptAddr"];
 });
 
 function searchDept(parentCode,parentName,deptLevel,page){
	 var keyWord = $("#keyWord").val();
	 var searchType = $("#searchType").val();
	 window.location.href = "searchDept.do?keyWord="+keyWord+"&searchType="+searchType+"&parentCode="+parentCode+"&deptLevel="+deptLevel+"&page="+page+"&parentName="+parentName;
 }
 
 function getPointList(){
 	//console.log($("#deptCode").val());
 	ajaxPointList();
 }
 
 function pointPage(page) {
 	currentPage = page;
 	ajaxPointList();
 }
 function ajaxPointList() {
 	 $.ajax({
        url:"../point/getDeptPoint.do",
        data:{"deptCode":$("#dept_code").val(),"page":currentPage},
        dataType:"json",
        type:"GET",
        success:function(result){
            console.log(result);
             var data = result["deptpoints"];
             $("#point_table tbody").children().remove();
             for(var i = 0;i < data.length;i ++){
                
                $("#point_table tbody").append("<tr>" +
                                "<td>"+(i+1)+"</td>" +
                                "<td>"+data[i].pointCode+"</td>"+
                                "<td><a target='_blank' href='../point/getPointInfo.do?pointCode="+data[i].pointCode+"'>"+data[i].pointName+"</a></td>"+
                                "<td>"+data[i].pointAddr+"</td>" +
                                
                                        "</tr>");
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
 
 function incontent(param){
    
    $(param).css({
                 "border-color":"black",
                "border-style":"solid",
                "border-width":"1px"
            });
 }
 //modifyDept
 function getPoints(){
 	if($("#point_table").is(":hidden")){
 		console.log(1);
        ajaxPoint();
     }
 }

 function getPage(page){
    currentPage = page;
    ajaxPoint();
  
}
function refreshPointList(){
	ajaxPoint();
}

function ajaxPoint() {
     $.ajax({
        url:"../point/getDeptPoint.do",
        data:{"deptCode":$("#deptCode").val(),"page":currentPage},
        dataType:"json",
        type:"GET",
        success:function(result){
            console.log(result);
             var data = result["deptpoints"];
             $("#point_table tbody").children().remove();
             for(var i = 0;i < data.length;i ++){
                
                $("#point_table tbody").append("<tr>" +
                                "<td>"+(i+1)+"</td>" +
                                "<td><a target='_blank' href='../point/getPointInfo.do?pointCode="+data[i].pointCode+"'>"+data[i].pointName+"</a></td>"+
                                "<td>"+data[i].pointAddr+"</td>" +
                                "<td><a id='del_point' onclick=delDeptPoint("+$("#deptCode").val()+",'"+(data[i].pointCode)+"')>删除</a></td>" +
                                        "</tr>");
                console.log(data[i].pointCode)
             }
            currentPage = result["page"];
            allPage = result['lastpage'];
            $("#pre_page").remove();
            $("#next_page").remove();
            $("#last_page").remove();
            
            if (currentPage > 1) {
                $("#first_page").after("<span id='pre_page' onclick='getPage("+(currentPage-1)+")'>上一页</span> ");
            }
            if (currentPage < allPage) {
                $("#all_page").before("<span id='next_page' onclick='getPage("+(currentPage+1)+")'>下一页</span> ");
            }
            $("#all_page").before("<span id='last_page' onclick='getPage("+allPage+")'>尾页</span> ");
            $("#current_page").text("第"+currentPage+"页");
            $("#all_page").text("共"+allPage+"页");
            
      }});
}
 
function delDeptPoint(deptCode,pointCode){
	console.log(pointCode)
	Lobibox.confirm({
		   title:"警告",
			msg: "是否删除该部门下项点？",
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
		    		$.ajax({
		 		           url:"delDeptPoint.do",
		 		           data:{"deptCode":$("#deptCode").val(),"pointCode":pointCode},
		 		           dataType:"text",
		 		           type:"GET",
		 		           success:function(result){
		 		                if(result == allPage){
		 		                   ajaxPoint();
		 		                }else{
		 		                   currentPage=1;
		 		                   allPage=result;
		 		                   ajaxPoint();
		 		                }
		 		         }});
		    	}else if(type == "cancel"){
		    		
		    	}
		    }
		});
}
 function validate(){
    var validate = true;
    for(var i=0;i < inputList.length;i ++){
        if($(inputList[i]).val() == '' || $(inputList[i]).val()== null)
        {
            $(inputList[i]).css({
                "border-color":"red",
                "border-style":"dashed",
                "border-width":"2px"
            });
            validate = false;
        }
    }
    return validate;
    
 }
 function modify(){
 	if(validate()){
 		Lobibox.confirm({
 		   title:"警告",
 			msg: "是否修改该部门 ?",
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
 		    		$("#info_form").submit();
 		    	}else if(type == "cancel"){}
 		    }
 		});
 		
 		
 	}
 }
 function add(){
 	if(validate()){
         $.ajax({
        url:"checkDeptCode.do",
        data:{"deptCode":$("#deptCode").val()},
        dataType:"text",
        type:"GET",
        success:function(result){
            if(result == "exist"){
            	Lobibox.alert('warning', {
	   				 title:"提示",
	                   msg: "编号已经存在，请重新输入！！",
	                   buttons:{
	                  	 ok:{
	                  		 text:"确定"
	                  	 }
	                   }
	               });
                $("#deptCode").css({
                "border-color":"red",
                "border-style":"dashed",
                "border-width":"2px"
            });
            }else{
                $("#info_form").submit();
            }
        }
    });
    
 }
 }
// 给部门添加项点
 var pointList = [];
 function getFirstPoint() {
	 pointList = [];
	 $("#first_point").children().remove();
//	 $("#second_point").children().remove();
//	 $("#third_point").children().remove();
	 $("#first_point").append("<div class='col-sm-4'>" +
				"<input type='checkbox' onchange='checkAll(this)' >" +
				"&nbsp;<label for='check'>全选</label></div>");
	 $.ajax({
	        url:"../point/getPointByParentAndLevel.do",
	        data:{"pointLevel":1,"parentCode":''},
	        dataType:"json",
	        type:"GET",
	        success:function(result){
	            for(var i=0;i<result.length;i++){
	            	$("#first_point").append("<div class='col-sm-4'>" +
	            					"<input type='checkbox' onchange='choosePoint(this)' value='"+result[i].pointCode+"' name='point'>" +
	            					"&nbsp;"+result[i].pointCode+".<label for='check'>"+result[i].pointName+"</label></div>");
	            }
	        }
	    });
 }
 
 function choosePoint(checkBox){
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
	 		
	 		for(var i=0;i<$("[name = point]:checkbox").length;i++){
	 			if($("[name = point]:checkbox").eq(i).is(":checked") == false){
	 				$("[name = point]:checkbox").eq(i).click();
	 			}	
	 		}
	 		console.log(pointList);
	 	}else{
	 		
	 		for(var i=0;i<$("[name = point]:checkbox").length;i++){
	 			$("[name = point]:checkbox").eq(i).click();
	 		}
	 		console.log(pointList);
	 	}
	 }
 
 function showSecondPoint(ele,code){
	 if($(ele).hasClass("glyphicon glyphicon-plus")){
		 $(ele).removeClass("glyphicon glyphicon-plus");
		 $(ele).addClass("glyphicon glyphicon-minus");
		 $.ajax({
		        url:"../point/getPointByParentAndLevel.do",
		        data:{"pointLevel":2,"parentCode":code},
		        dataType:"json",
		        type:"GET",
		        success:function(result){
		            for(var i=0;i<result.length;i++){
		            	$("#second_point").append("<div class='col-sm-3 point-"+code+"'>" +
		            					"<label for='check'>"+result[i].pointName+"</label>" +
		            					"<input type='checkbox' onchange='choosePoint(this)' value='"+result[i].pointCode+"'>" +
		            					"&nbsp;<i class='glyphicon glyphicon-plus' onclick=showThirdPoint(this,'"+result[i].pointCode+"')></i></div>");
		            }
		        }
		    });
	 }else{
		 $(ele).removeClass("glyphicon glyphicon-minus");
		 $(ele).addClass("glyphicon glyphicon-plus");
		 $(".point-"+code).remove();
	 }
 }
 
 function showThirdPoint(ele,code){
	 if($(ele).hasClass("glyphicon glyphicon-plus")){
		 $(ele).removeClass("glyphicon glyphicon-plus");
		 $(ele).addClass("glyphicon glyphicon-minus");
		 $.ajax({
		        url:"../point/getPointByParentAndLevel.do",
		        data:{"pointLevel":3,"parentCode":code},
		        dataType:"json",
		        type:"GET",
		        success:function(result){
		            for(var i=0;i<result.length;i++){
		            	$("#third_point").append("<div class='col-sm-3 point-"+code+"'>" +
		            					"<label for='check'>"+result[i].pointName+"</label>" +
		            					"<input type='checkbox' onchange='choosePoint(this)' value='"+result[i].pointCode+"'></div>");
		            }
		        }
		    });
	 }else{
		 $(ele).removeClass("glyphicon glyphicon-minus");
		 $(ele).addClass("glyphicon glyphicon-plus");
		 $(".point-"+code).remove();
	 }
 }
 function addDeptPoints(){
 	if(pointList.length==0){
 		Lobibox.alert('warning', {
				 title:"提示",
               msg: "您尚未选择项点！！",
               buttons:{
              	 ok:{
              		 text:"确定"
              	 }
               }
           });
 		
 	}else{
 		for(var i=0;i<pointList.length;i++){
 			$.ajax({
 		        url:"modDeptPoint.do",
 		        data:{"deptCode":$("#deptCode").val(),"deptName":$("#deptName").val(),"pointCode":pointList[i]},
 		        dataType:"text",
 		        type:"GET",
 		        success:function(result){
 		            console.log(result);
 		           
 		        }
 		     });
 		}
 		$("#pointModal").hide();
 	}
 }
