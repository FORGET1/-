var str = sessionStorage.user;
var currentUser = JSON.parse(str);
var roleCode;
// ***********************************添加***************************************
// 显示添加用户的内容
function showAdd(userType) {
	$(".create-message").hide();
    if($("#list-table tbody tr").length ==0){
         $("#list-table tbody").append('<tr><form>'+                                    
                    '<td><input id="user-code" type="text" placeholder="用户编号"/></td>'+
                    '<td><input id="user-name" type="text" placeholder="用户姓名"/></td>'+
                    '<td><select id="user-manage"></select></td>'+
                    '<td><input id="user-password" type="password" placeholder="密码"/></td>'+
                    '<td><button type="button" onclick="addUser('+userType+')">添加</button>\
                    <button type="button" onclick="cancelAdd()">取消</button></td>'+
                    '</form></tr>');
    }else{
         $("#list-table tbody tr").eq(0).before('<tr><form>'+                                       
                    '<td><input id="user-code" type="text" placeholder="用户编号"/></td>'+
                    '<td><input id="user-name" type="text" placeholder="用户姓名"/></td>'+
                    '<td><select id="user-manage"></select></td>'+
                    '<td><input id="user-password" type="password" placeholder="密码"/></td>'+
                    '<td><button type="button" onclick="addUser('+userType+')">添加</button>\
                    <button type="button" onclick="cancelAdd()">取消</button></td>'+
                    '</form></tr>');
    }
   
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
    console.log(options);
    for(var i=0;i<options.length;i++){
        $("#user-manage").append('<option value="'+options[i]["value"]+'" >'+options[i]["text"]+'</option>');
    }
    
}

function cancelAdd(){
	$(".create-message").show();
    if($("#list-table tbody tr").length == 1){
        $("#list-table tbody").empty();
    }else{
        $("#list-table tbody tr").eq(0).remove();
    }
}

function getAllDepartment(){
     var options = [];
     $.ajax({
        url:"../../department/getAllDepartmentList.do",
        type:"GET",
        async:false,
        dataType:"Json",
        success:function(result){
            // console.log(result);
            result = result.dataList;
            
            for(var r=0;r<result.length;r++){
                options.push({"value":result[r].departmentCode,"text":result[r].departmentName});
            }
           
       }
    }); 
    return options;
}

function getAllCompany(code) {
    var options = [];
    $.ajax({
        url:"../../company/getEnterpriseByDepNoPage.do",
        type:"GET",
        data:{"deptCode":code},
        async:false,
        dataType:"Json",
        success:function(result){
            // console.log(result);
           
            result = result.dataList;
            for(var r=0;r<result.length;r++){
                options.push({"value":result[r].companyCode,"text":result[r].companyName});
            }
            
       }
    });
    return options;
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
 				 	 		        dataType : 'Json',
 				 	 		        success : function(result) {
// 				 	 		        	console.log(result.status);
 				 	 		            if(result.status.msg == "SUCCESS"){
 				 	 		            	window.parent.showDialog('添加成功');
		 				        			location.reload(true);
 				 	 		            }else{
 				 	 		            	window.parent.showDialog('添加失败');
 				 	 		            }										
 				 	 		        },
 				 	 		    });
            }
        );
        
}
function randomString(len) {
	len = len || 32;
	var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
	var maxPos = $chars.length;
	var pwd = '';
	for (i = 0; i < len; i++) {
		pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}
// ***********************************添加 end***************************************
// ***********************************删除***************************************
function deleteUser(userCode){
    window.parent.showDialog('删除',function(){
                
                $.ajax({
			        url:"../../user/deleteUserByCode.do",
			        type:"GET",
			        data:{"userCode":userCode},
			        dataType:"json",
			        success:function(result){
			            console.log(result);
			            if(result.status.msg == "SUCCESS"){
                             window.parent.showDialog('删除成功');
                             
                        }else{
                             window.parent.showDialog('删除失败');
                        }
			              
			       }
			    });
                location.reload(true); 
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
            changeTable(page,result,getUserList,roleCode);
            console.log(result);
            window.parent.adjustHeight();
       }
    });
}
//搜索用户
function search(page,roleCode){
	roleCode = roleCode;
	$.ajax({
        url:"../../user/searchUserByKeyAndRole.do",
        type:"GET",
        data:{"searchType":$("#search-type").val(),"keyWord":$("#keyword").val(),"page":page,"manageCode":currentUser.manageCode,"roleCode":roleCode},
 		
        dataType:"Json",
        success:function(result){
            // console.log(result);
            changeTable(page,result,search,roleCode);
            window.parent.adjustHeight();
       }
    });
}
// 修格内容
function changeTable(page,result,callback,roleCode){
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
            '<button class="change-btn" onclick="turnToPage(\'modifyUser.html?userCode='+users[i].userCode+'&userType='+roleCode+'\')">修改</button>'+
            '<button class="delete-btn" onclick="deleteUser(\''+users[i].userCode+'\')">删除</button>'+
        '</td>'+
    	'</tr>');
    };
    if(roleCode == 2){
    	$(".change-btn").remove();
    	$(".delete-btn").remove();
    }
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
    $.ajax({
		url:"../../user/getUserByCode.do",
        type:"GET",
        data:{"userCode":userCode},
 		
        dataType:"Json",
        success:function(result){
            // console.log(result);
             result = result.data;
            $("#user-code").val(result.userCode);
            $("#user-name").val(result.userName);
            $("#user-manage").val(result.deptCode);
            $("#user-id").val(result.id);
            $("#user-birth").val(result.birthDate);
            $("#user-sex").val(result.gender);
            
            $("#user-phone").val(result.phone);
       }
	});
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
	var p =  /^(\d{4})(-)(\d{2})(-)(\d{2})$/;
	if(p.test($("#user-birth").val())==false){
		$("#user-birth").css({
            "border-color":"red",
            "border-style":"dashed",
            "border-width":"2px"
       		 });
		$("#user-birth").after('<span style="color:red">格式:2000-01-01</span>');
		return;
	}
    window.parent.showDialog('修改',function(){
        var userSalt = randomString(Math.random() * 9);
        var userCode = $("#user-code").val();
        var userName = $("#user-name").val();
        var userPwd = $("#userPwd").val();
        var userManage = $("#user-manage").val();
        var userId = $("#user-id").val();
        var userSex = $("#user-sex").val();
        var userBirth = $("#user-birth").val();
        var userTel = $("#user-phone").val();
        var manageCode = currentUser.manageCode;
        userPwd = hex_md5(userSalt+userPwd);
        var newUser= {"salt":userSalt,"userCode":userCode,"name":userName,"password":userPwd,"id":userId,"gender":userSex,
        			"birthDate":userBirth,"phone":userTel,"deptCode":userManage};
                                                                         
        $.ajax({                                    
            type : "POST",
            data : newUser,
            url : '../../user/modifyUser.do',
            dataType : 'Json',
            success : function(result) {
                if(result.status.msg == "SUCCESS"){
                    window.parent.showDialog("修改成功");
                    location.reload(true);
                }else{
                    window.parent.showDialog('修改失败');
                }                                       
            },
        });
    });

}
// ***********************************修改end***************************************