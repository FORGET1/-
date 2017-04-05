// ***********************************添加***************************************
function showAddDept() {
    $("#list-table tbody tr").eq(0).before('<tr><form>'+
                                        
                                        '<td><input id="department-code" type="text" placeholder="部门编号"/></td>'+
                                        '<td><input id="department-name" type="text" placeholder="部门名称"/></td>'+
                                        
                                        '<td><input id="department-tel" type="text" placeholder="部门电话"/></td>'+
                                        '<td><button type="button" onclick="addDept()">添加</button></td>'+
                                        '</form></tr>');
    
}

function addDept(argument) {
     window.parent.showDialog('添加',function(){
       
                    var departmentCode = $("#department-code").val();
                    var departmentName = $("#department-name").val();
                    var departmentTel = $("#department-tel").val();
                    var department = {"departmentCode":departmentCode,"departmentName":departmentName,"departmentTel":departmentTel};
                    $.ajax({                                    
                        type : "POST",
                        data : department,
                        url : '../../department/addDepartment.do',
                        dataType : 'text',
                        success : function(result) {
                        	if(result=="exist"){
                        		window.parent.showDialog('编号重复');
                        	}else if(result == "SUCCESS"){
                                window.parent.showDialog('添加成功');
                                location.reload(true);
                            }else{
                                window.parent.showDialog('添加失败');
                            }                                       
                        },
                    });
           
     });
}
// ***********************************添加 end***************************************
// ***********************************修改***************************************
function showModify(code) {
    getDeptInfo(code);
}
function modify(){
	 window.parent.showDialog('修改',function(){
        var departmentCode = $("#department-code").val();
        var departmentName = $("#department-name").val();
        var departmentPerson = $("#department-person").val();
        var departmentPersonTel = $("#department-person-tel").val();
        var departmentTel = $("#department-tel").val();
        var departmentAddr = $("#department-addr").val();
        var department = {"departmentCode":departmentCode,"departmentName":departmentName,
                    "departmentPerson":departmentPerson,"departmentPersonTel":departmentPersonTel,
                    "departmentTel":departmentTel,"departmentAddr":departmentAddr};
        $.ajax({                                    
            type : "POST",
            data : department,
            url : '../../department/modifyDepartment.do',
            dataType : 'text',
            success : function(result) {
                if(result == "SUCCESS"){
                    window.parent.showDialog('修改成功');
                    location.reload(true);
                }else{
                    window.parent.showDialog('修改失败');
                }                                       
            },
        });
     });
}
// ***********************************修改end***************************************
// ***********************************查询***************************************
function getDeptList(page){
     $.ajax({
        url:"../../department/getDepartmentList.do",
        type:"GET",
        data:{"page":page},
        dataType:"Json",
        success:function(result){
            // console.log(result);
            changeTable(page,result,getDeptList);
            window.parent.adjustHeight();
       }
    });
}
function search(page){
    
    $.ajax({
        url:"../../department/searchDepartmentList.do",
        type:"GET",
        data:{"searchType":$("#search-type").val(),"keyWord":$("#keyword").val(),"page":page},
        
        dataType:"Json",
        success:function(result){
            // console.log(result);
            changeTable(page,result,search);
            window.parent.adjustHeight();
       }
    });
}

function changeTable(page,result,callback){
    // 获取数据
    var data = result.dataList;
    var lastpage = result.sumPage;
    
    // 设置页码
    $("#lastpage").text(lastpage);
    $("#page").text(page);
    if(page == 1){
        $("#up-btn").css("display","none");
    }else{
         $("#up-btn").css("display","inline");
         $("#up-btn").unbind("click");
        $("#up-btn").on("click",function(){callback(page-1)});
    }
    if(page == lastpage){
        $("#down-btn").css("display","none");
    }else{
         $("#down-btn").css("display","inline");
         $("#down-btn").unbind("click");
        $("#down-btn").on("click",function(){callback(page+1)});
    }
    
    // 设置 table
     $("#list-table tbody").empty();
    for(var i=0;i<data.length;i++){
        $("#list-table tbody").append('<tr>'+
        '<td>'+i+'</td>'+
        '<td>'+data[i].departmentName+'</td>'+
        '<td>'+data[i].departmentTel+'</td>'+
        
        '<td>'+
            '<button onclick="turnToPage(\'departmentInfo.html?departmentCode='+data[i].departmentCode+'\')">查看</button>'+  
            '<button onclick="turnToPage(\'modifyDepartment.html?departmentCode='+data[i].departmentCode+'\')">修改</button>'+
            '<button class="alert-btn" onclick="deleteDepartment(\''+data[i].departmentCode+'\')">删除</button>'+
        '</td>'+
        '</tr>');
    };    
}
// ***********************************查询 end***************************************
// ***********************************查看***************************************
function getDeptInfo(code) {
    $.ajax({
        url:"../../department/departmentInfo.do",
        type:"GET",
        data:{"departmentCode":code},
        
        dataType:"Json",
        success:function(result){
            // console.log(result);
        	result = result.data;
            $("#department-code").val(result.departmentCode);
            $("#department-parent").val(result.departmentParent);
            $("#department-name").val(result.departmentName);
            $("#department-person").val(result.departmentPerson);
            $("#department-person-tel").val(result.departmentPersonTel);
            $("#department-tel").val(result.departmentTel);
            $("#department-addr").val(result.departmentAddr);
       }
    });
    
}
// ***********************************查看 end***************************************
// ***********************************删除***************************************
function deleteDepartment(code) {
    // body...
     window.parent.showDialog('删除',function(){
                
                $.ajax({
                    url:"../../department/deleteDepartment.do",
                    type:"GET",
                    data:{"departmentCode":code},
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
// ***********************************删除 end**************************************