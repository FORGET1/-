/**
 * 
 */

var deptName = '12321';
var deptCode = '';

$(document).ready(function(){
	 $("#dev").parent().next().slideDown();
	 inputList = ["#devName","#devProperty","#devCode"];
	 deptName = $("#dept-name").val();
	 console.log(deptName)
	 deptCode = $("#dept-code").val();
});

function searchDev(code,name,level,page) {
	var keyWord = $("#keyWord").val();
	var type=$("#searchType").val();
	window.location.href = "searchDev.do?keyWord="+keyWord+"&searchType="+type+"&parentCode="+code+"&devLevel="+level+"&page="+page+"&parentName="+name;
}


function setDevType(){
	var devTypeCode = $("#devTypeCode").val();
	var devTypeName = $("#devTypeName").val();
	$.ajax({
		url:"../devType/getAllDevTypeList.do",
		
		dataType:"json",
		success:function(result){
			for(var i = 0;i < result.length;i++){
			
				$("#devType").append("<option value='"+result[i].devTypeCode+"'>"+result[i].devTypeName+"</option>");
			}
			if(devTypeCode != ''){
				$("#devType").val(devTypeCode);
			}
			
		}
	})
}
function setDept() {
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
}
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
    
 	}else{
 		deptName = '';
 		deptCode = '';
 		$("#secondDept").children().remove();
        $("#secondDept").append("<option value='second'>选择二级部门</option>");
       
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
      
    }else{
    	deptName = '';
    	deptCode = '';
    	$("#thirdDept").children().remove();
        $("#thirdDept").append("<option value='third'>选择三级部门</option>");
       
    }
 }
 
 function thirdDeptChange() {
 	if($("#thirdDept").val() != "third"){
 		deptCode = $("#thirdDept").val();
 		deptName = $("#thirdDept").find("option:selected").text(); 
 		
 	}else{
 		deptName = '';
 		deptCode = '';
 	}
 }
 
 function chooseDept(){
	 $(".old-dept").remove();
	 $(".new-dept").show();
	 setDept();
	 deptName='';
 }
 function modify() {
	 if(validate() == true){
		 console.log(deptName);
		 if(deptName == ''){
			 Lobibox.alert('warning', {
	   				 title:"提示",
	                   msg: "请选择管辖部门！！",
	                   buttons:{
	                  	 ok:{
	                  		 text:"确定"
	                  	 }
	                   }
	               });
			 console.log(1)
			 
		 }else {
			 console.log(deptName);
			 console.log(deptCode);
			 if(deptCode=='0'){
				 Lobibox.alert('warning', {
	   				 title:"提示",
	                   msg: "请选择管辖部门！！",
	                   buttons:{
	                  	 ok:{
	                  		 text:"确定"
	                  	 }
	                   }
	               });
				 
				 return;
			 }
			 $("#deptName").val(deptName);
			 $("#deptCode").val(deptCode);
			 $("#info_form").submit();
		 }
	 }
	 
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

 function incontent(param) {
	 $(param).css({
         "border-color":"black",
        "border-style":"solid",
        "border-width":"1px"
	 });
 }
 
 function addDev() {
	 if(validate() == true){
		 console.log(deptName);
		 if(deptName == ''){
			 Lobibox.alert('warning', {
   				 title:"提示",
                   msg: "请选择管辖部门！！",
                   buttons:{
                  	 ok:{
                  		 text:"确定"
                  	 }
                   }
               });
			 console.log(1)
			 
		 }else {
			 $.ajax({
			        url:"checkDevCode.do",
			        data:{"devCode":$("#devCode").val()},
			        dataType:"text",
			        type:"GET",
			        success:function(result){
			        	if(result == "exist"){
			        		 Lobibox.alert('warning', {
				   				 title:"提示",
				                   msg: "设备编号已经存在，请重新输入！！",
				                   buttons:{
				                  	 ok:{
				                  		 text:"确定"
				                  	 }
				                   }
				               });
			        		
			        		$("#devCode").css({
			                "border-color":"red",
			                "border-style":"dashed",
			                "border-width":"2px"
			            });
			        	}else{
			        		$("#deptName").val(deptName);
			   			 	$("#deptCode").val(deptCode);
			        		$("#info_form").submit();
			        	}
			        }
			 	});
			
		 }
	 }
}