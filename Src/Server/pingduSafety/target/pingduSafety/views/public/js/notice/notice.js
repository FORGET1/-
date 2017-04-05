/**
 * 
 */

$(document).ready(function(){
	 $("#notice").parent().parent().find("ul").show();
	 inputList = ["#noticeTitle","#noticePerson"];
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

function modifyNotice() {
	
	if(validate()){
	Lobibox.confirm({
		   title:"警告",
			msg: "是否修改该通知 ?",
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
		    		var formData = new FormData(document.getElementById("notice_form"));
	 	 		      console.log(formData)
	 	 		    $.ajax({
	 	 		        
	 	 		        type : "POST",
	 	 		        data : formData,
	 	 		        url : 'modifyNotice.do',
	 	 		        dataType : 'text',
	 	 		        contentType: false, //必须
	 	 		        processData: false, //必须
	 	 		      	enctype:"multipart/form-data",
	 	 		        success : function(result) {
	 	 		            console.log('success');
	 	 		          Lobibox.confirm({
							   title:"提示",
								msg: "修改成功 ！",
							    buttons:{
							    	 ok: {
							             'class': 'btn btn-info',
							            
							             text:"确定"
							         },
							    }
						});
						setTimeout("window.location.href='./getNoticeList.do?page=1'",1000);
	 	 		        },
	 	 		        error : function(arg1, arg2, arg3) {
	 	 		            console.log(arg1 + "--" + arg2 + "--" + arg3);
	 	 		        }
	 	 		    });
//	 		 		$("#notice_form").submit();
		    	}else if(type == "cancel"){
		    		
		    	}
		    }
		});
}
}

function addNotice() {
	if(validate()){
		Lobibox.confirm({
			   title:"警告",
				msg: "是否添加该通知 ?",
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
			    		var formData = new FormData(document.getElementById("notice_form"));
		 	 		      console.log(formData)
		 	 		    $.ajax({
		 	 		        
		 	 		        type : "POST",
		 	 		        data : formData,
		 	 		        url : 'addNotice.do',
		 	 		        dataType : 'text',
		 	 		        contentType: false, //必须
		 	 		        processData: false, //必须
		 	 		      	enctype:"multipart/form-data",
		 	 		        success : function(result) {
		 	 		            console.log('success');
		 	 		          Lobibox.confirm({
								   title:"提示",
									msg: "添加成功 ！",
								    buttons:{
								    	 ok: {
								             'class': 'btn btn-info',
								            
								             text:"确定"
								         },
								    }
							});
							setTimeout("window.location.href='./getNoticeList.do?page=1'",1000);
		 	 		        },
		 	 		        error : function(arg1, arg2, arg3) {
		 	 		            console.log(arg1 + "--" + arg2 + "--" + arg3);
		 	 		        }
		 	 		    });
//		 		 		$("#notice_form").submit();
			    	}else if(type == "cancel"){
			    		
			    	}
			    }
			});
	}
}
