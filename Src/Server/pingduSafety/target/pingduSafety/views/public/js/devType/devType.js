/**
 * 
 */

$(document).ready(function(){
	$("#dev").parent().next().slideDown();
	 inputList = ["#devTypeCode","#devTypeName","#devTypeInfo"];
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
		 $("#info_form").submit(); 
	 }	 
}

function addDevType() {
	if(validate()){
 		$.ajax({
        url:"checkDevTypeCode.do",
        data:{"devTypeCode":$("#devTypeCode").val()},
        dataType:"text",
        type:"GET",
        success:function(result){
        	if(result == "exist"){
        		Lobibox.alert('warning', {
	   				 title:"提示",
	                   msg: "设备类型编号已经存在，请重新输入！！",
	                   buttons:{
	                  	 ok:{
	                  		 text:"确定"
	                  	 }
	                   }
	               });
        		
        		$("#devTypeCode").css({
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