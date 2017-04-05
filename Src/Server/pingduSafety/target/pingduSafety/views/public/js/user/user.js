var str = sessionStorage.user;
var currentUser = JSON.parse(str);
var roleCode;
// ***********************************添加***************************************
// 显示添加用户的内容
function showAdd(userType) {
    $("#list-table tbody tr").eq(0).before('<tr><form>'+   									
    									'<td><input id="user-code" type="text" placeholder="用户编号"/></td>'+
    									'<td><input id="user-name" type="text" placeholder="用户姓名"/></td>'+
    									'<td><select id="user-manage"></select></td>'+
    									'<td><input id="user-password" type="password" placeholder="密码"/></td>'+
    									'<td><button type="button" onclick="addUser('+userType+')">添加</button></td>'+
    									'</form></tr>');
    var options = [];
    switch(userType){
    	case "0":
    		options.push({"value":4,"text":"安监局"});
    		break;
        case "1":
            options = getAllDepartment();
            break;
        case "2":
            options = getAllCompany(currentUser.manageCode);
            break;

    }
    for(var i=0;i<options.length;i++){
    	$("#user-manage").append('<option value="'+options[i]["value"]+'" >'+options[i]["text"]+'</option>');
    }
    
}

function getAllDepartment(){
     $.ajax({
        url:"../../department/getAllDepartment.do",
        type:"GET",
        
        dataType:"Json",
        success:function(result){
            // console.log(result);
            var options = [];
            for(r in result){
                options.push({"value":result[r].departmentCode,"text":result[r].departmentName});
            }
            return options;
       }
    });
}

function getAllCompany(code) {
    $.ajax({
        url:"../../company/getAllCompany.do",
        type:"GET",
        data:{"departmentCode":code},
        dataType:"Json",
        success:function(result){
            // console.log(result);
            var options = [];
            for(r in result){
                options.push({"value":result[r].companyCode,"text":result[r].companyName});
            }
            return options;
       }
    });
}

// 添加用户
function addUser(userType){

	var inputs = $("#list-table input");
	for(var i =0;i<inputs.length;i++){
		if(inputs.eq(i).val() == "" || inputs.eq(i).val() == null){
			inputs.eq(i).css({
	                "border-color":"red",
	                "border-style":"dashed",
	                "border-width":"2px"
	           		 });
			return;
		}
	}
	window.parent.showDialog('添加',function(){
                $.ajax({
 				        url:"../../user/checkUserCode.do",
 				        data:{"userCode":$("#user-code").val()},
 				        dataType:"text",
 				        type:"GET",
 				        success:function(result){
 				        	console.log(result);
 				        	if(result.status.msg == "exist"){
 				        		window.parent.showDialog('用户编号重复');
 				        		$("#user-code").css({
 				                "border-color":"red",
 				                "border-style":"dashed",
 				                "border-width":"2px"
 				           		 });
 				        	}else{
 				        		var userSalt = randomString(Math.random() * 9);
 				        		var userCode = $("#user-code").val();
 				        		var userName = $("#user-name").val();
 				        		var userPwd = $("#userPwd").val();
 				        		var userManage = $("#user-manage").val();
 				        		var manageCode = currentUser.manageCode;
 				        		userPwd = hex_md5(userSalt+userPwd);
 				        		
 				        		var newUser= {"salt":userSalt,"userCode":userCode,"name":userName,"password":userPwd,"roleCode":userType
 				        						,"deptCode":userManage};			        		
 				 	 		    $.ajax({				 	 		        
 				 	 		        type : "POST",
 				 	 		        data : newUser,
 				 	 		        url : '../../user/addUser.do',
 				 	 		        dataType : 'text',
 				 	 		        success : function(result) {
 				 	 		            if(result == "SUCCESS"){
 				 	 		            	window.parent.showDialog('添加成功');
		 				        			location.reload(true);
 				 	 		            }else{
 				 	 		            	window.parent.showDialog('添加失败');
 				 	 		            }										
 				 	 		        },
 				 	 		    });
 				        	}
 				        }
 				 	});
            }
        );
        
}
// ***********************************添加 end***************************************
// ***********************************删除***************************************
function deleteUser(userCode){
    window.parent.showDialog('删除',function(){
                
                $.ajax({
			        url:"../../user/deleteUser.do",
			        type:"GET",
			        data:{"userCode":userCode},
			        dataType:"text",
			        success:function(result){
			            // console.log(result);
			            if(result == "SUCCESS"){
                             window.parent.showDialog('删除成功');
                             location.reload(true); 
                        }else{
                             window.parent.showDialog('删除失败');
                        }
			              
			       }
			    });
            }

        );
}
// ***********************************删除 end***************************************
// ***********************************查询列表***************************************
// 用户列表
function getUserList(page,roleCode){
	roleCode = roleCode;
     $.ajax({
        url:"../../user/getUsersByRole.do",
        type:"GET",
        data:{"page":page,"roleCode":roleCode,"manageCode":currentUser.manageCode},
        dataType:"Json",
        success:function(result){
            //console.log(result);
            changeTable(page,result,getUserList);
            window.parent.adjustHeight();
       }
    });
}
//搜索用户
function search(page){
	
	$.ajax({
        url:"../../user/searchUser.do",
        type:"POST",
        data:{"searchType":$("#search-type").val(),"keyword":$("#keyword").val(),"page":page,"manageCode":currentUser.manageCode,},
 		
        dataType:"Json",
        success:function(result){
            // console.log(result);
            changeTable(page,result,search);
            window.parent.adjustHeight();
       }
    });
}
// 修格内容
function changeTable(page,result,callback){
	// 获取数据
    var users = result.dataList;
    var lastpage = result.sumPage;
    
    // 设置页码
    $("#lastpage").text(lastpage);
    $("#page").text(page);
    if(page == 1){
        $("#up-btn").css("display","none");
    }else{
         $("#up-btn").css("display","inline");
         $("#up-btn").unbind("click");
        $("#up-btn").on("click",function(){callback(page-1,roleCode)});
    }
    if(page == lastpage){
        $("#down-btn").css("display","none");
    }else{
         $("#down-btn").css("display","inline");
         $("#down-btn").unbind("click");
        $("#down-btn").on("click",function(){callback(page+1,roleCode)});
    }
    
    // 设置 table
     $("#list-table tbody").empty();
    for(var i=0;i<users.length;i++){
        $("#list-table tbody").append('<tr>'+
        '<td>'+i+'</td>'+
        '<td>'+users[i].userCode+'</td>'+
        '<td>'+users[i].userName+'</td>'+
        '<td>'+users[i].deptName+'</td>'+
        '<td>'+
            '<button onclick="turnToPage(\'userInfo.html?userCode='+users[i].userCode+'\')">查看</button>'+  
            '<button onclick="turnToPage(\'modifyUser.html?userCode='+users[i].userCode+'&userType='+currentUser.userType+'\')">修改</button>'+
            '<button class="alert-btn" onclick="deleteUser(\''+users[i].userCode+'\')">删除</button>'+
        '</td>'+
    	'</tr>');
    };    
}
// ***********************************查询列表 end***************************************
// ***********************************查看信息***************************************
// 获取用户信息
function getUserInfo(userCode){
	$.ajax({
		url:"../../user/getUserByCode.do",
        type:"GET",
        data:{"userCode":userCode},
 		
        dataType:"Json",
        success:function(result){
             console.log(result);
             result = result.data;
            $("#user-code").val(result.userCode);
            $("#user-name").val(result.userName);
            $("#user-manage").val(result.deptName);
            $("#user-id").val(result.id);
            $("#user-birth").val(result.birthDate);
            $("#user-sex").val(result.gender);
            $("#user-pwd").val(result.password);
            $("#user-phone").val(result.phone);
       }
	});
}
// ***********************************查看信息 end***************************************
// ***********************************修改***************************************
function showModify(userCode,userType){
    var options = [];
    console.log(userType);
    switch(userType){
        case "1":
            options.push({"value":"","text":"安监局"});
            break;
        case "2":
            options = getAllDepartment();
            break;
        case "3":
            options = getAllCompany(currentUser.manageCode);
            break;

    }
    for(var i=0;i<options.length;i++){
        $("#user-manage").append('<option value="'+options[i]["value"]+'" >'+options[i]["text"]+'</option>');
    }
	//getUserInfo(userCode);
}

function modify(){
	var inputs = $("input");
	for(var i =0;i<inputs.length;i++){
		if(inputs.eq(i).val() == "" || inputs.eq(i).val() == null){
			inputs.eq(i).css({
	                "border-color":"red",
	                "border-style":"dashed",
	                "border-width":"2px"
	           		 });
			return;
		}
	}
    window.parent.showDialog('修改',function(){
        var userSalt = randomString(Math.random() * 9);
        var userCode = $("#user-code").val();
        var userName = $("#user-name").val();
        var userPwd = $("#userPwd").val();
        var userManage = $("#user-manage").val();
        var manageCode = currentUser.manageCode;
        userPwd = hex_md5(userSalt+userPwd);
        var newUser= {"userSalt":userSalt,"userCode":userCode,"userName":userName,"userPwd":userPwd,"userManage":userManage
                                                ,"manageCode":manageCode};                          
        $.ajax({                                    
            type : "POST",
            data : newUser,
            url : '../../user/addUser.do',
            dataType : 'text',
            success : function(result) {
                if(result == "SUCCESS"){
                    window.parent.showDialog('添加成功');
                    location.reload(true);
                }else{
                    window.parent.showDialog('添加失败');
                }                                       
            },
        });
    });

}
// ***********************************修改end***************************************