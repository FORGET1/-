/**
 * 
 */

$(document).ready(function(){
	 $("#patrolType").parent().parent().find("ul").show();
	 inputList = ["#patrolTypeCode","#patrolTypeName","#patrolTypeInfo"];
});

function incontent(param) {
	 $(param).css({
        "border-color":"black",
       "border-style":"solid",
       "border-width":"1px"
	 });
}

function validate(){
	 
 	var validate = true;
 	for(var i=0;i < inputList.length;i ++){
 		if($(inputList[i]).val() == '' || $(inputList[i]).val()== null)
 		{
 			console.log(i)
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

function modify() {
	
	 if(validate() == true){
		 $("#patrolType_form").submit(); 
	 }	 
}

function addPatrolType() {
     console.log(0)
	if(validate()){
 		$.ajax({
        url:"checkPatrolTypeCode.do",
        data:{"patrolTypeCode":$("#patrolTypeCode").val()},
        dataType:"text",
        type:"GET",
        success:function(result){
        	if(result == "exist"){
        		Lobibox.alert('warning', {
	   				 title:"提示",
	                   msg: "巡检类型编号已经存在，请重新输入！！",
	                   buttons:{
	                  	 ok:{
	                  		 text:"确定"
	                  	 }
	                   }
	               });
        		
        		$("#patrolTypeCode").css({
                "border-color":"red",
                "border-style":"dashed",
                "border-width":"2px"
            });
        	}else{
        		$("#patrolType_form").submit();
        	}
        }
 	});
 }
}