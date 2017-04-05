/*
var str =sessionStorage.user;
var currentUser = JSON.parse(str);
var userType = currentUser.userType;
var manageCode = currentUser.manageCode;
*/
/*
"userType" 1:超级  2：安监局  3：安监办  4:企业
"manageCode" "super":超级  "":安监局   "deptCode":安监办/企业编号
ainjdebx安监局  ainjdejx安监办  qinydex企业  suindeperx超级管理员  
*/
var userType = "3";
var manageCode = "3";
//根据用户角色获取任务列表
/*
function getTaskList(page){
	
	//判断用户 安监局查看所有任务  安检办查看自己所管辖企业的任务
    switch(userType){
    	case "2":
    		getAllTasks(1);
    		break;
        case "3":
           getTasksByDept(1);
            break;
		default:     //企业和超级管理员不能查看？？？？？
		    break;
    }
	
}
*/
//安监办查看自己管辖企业的所有任务                                                                                ok

function getTasksByDept(page){
	//console.log("获取任务列表");
      $.ajax({
		url: "../../task/getTaskListByDeptAndStatus.do",
		type: "GET",
		data: { "manageCode":"1","page": page ,"status":"ALL"},
		datdType: "JSON",
		success: function (result) {
			//console.log(result);
			changeTable(page, result, getTasksByDept);
			//window.parent.adjustHeight();
		}
	});
}



//填充任务列表表格                                                                                              ok
function changeTable(page,result,callback){
	   //获取数据
	//console.log("填充任务列表");
	var tasks = result.dataList;
	var page = result.currentPage;
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
    for(var i=0;i<tasks.length;i++){
        $("#list-table tbody").append('<tr>'+
        '<td>'+(i+1)+'</td>'+//显示在页面的序号
        //'<td>'+tasks[i].taskCode+'</td>'+//项点编号
		 '<td>'+tasks[i].entName+'</td>'+
        '<td>'+tasks[i].deptName+'</td>'+
        '<td>'+tasks[i].completeTime+'</td>'+
		'<td>'+tasks[i].exception+'</td>'+
        '<td>'+
            '<button onclick="turnToPage(\'taskInfo.html?taskCode='+tasks[i].taskCode+'\')">审阅</button>'+
        '</td>'+
    	'</tr>');
		//console.log(points[i].pointCode);
    }  
	}

//获取任务详细信息                                                                                               ok
function getTaskInfo(taskCode){
	//console.log("获取任务的详细信息");
	//console.log("任务编号："+taskCode);
    $.ajax({
		url: "../../task/getTaskInfo.do",
		type: "GET",
		data: {"taskCode":taskCode},
		datdType: "JSON",
		success: function (result) {
			console.log("详细信息："); 
			console.log(result);
			changeTaskInfo(result);
			//window.parent.adjustHeight();
		}
	});
}
//填充详细信息                                                                                                    ok
function changeTaskInfo(result){
	var task = result.data;

	 $("#entName").text(task.entName);
	 $("#pointName").text(task.pointName);
	 $("#deptNam").text(task.deptName);
	 $("#NFC").text(task.pointCode);            
	 $("#userName").text(task.name);      
	 $("#completeTime").text(task.completeTime);
	 $("#probDesc").text(task.probDesc);       
	 //console.log(task.taskCode);
	 $("#taskCode").val(task.taskCode);  
	 $("#advDesc").val(task.advDesc); 
	 //如果返回值中有items taskview 那上面的都不行
	 var items = result.data.items;
	 console.log(items);
     for(var i=0;i<items.length;i++){
	    $("#taskItems tbody").append('<tr>'+
		 '<td>'+(i+1)+'</td>'+//显示在页面的序号
        '<td>'+items[i].itemInfo+'</td>'+
		'<td>'+items[i].isException+'</td>'+
    	'</tr>');
		//console.log(points[i].pointCode);
    }  
}

//获取图片并显示图片                                                                                              ok

function getImage(taskCode){
	//console.log("获取图片路径");
	    $.ajax({
		url: "../../task/getImage.do",
		type: "GET",
		data: {"taskCode":taskCode},
		datdType: "JSON",
		success: function (result) {
			//console.log("图片路径：");                        
			//console.log(result);
			/*  
			var imagePaths = result.dataList;
           for(var i=0;i<imagePaths.length;i++){
			loadImage(imagePaths[i]);
			}
			*/
		}
	});
}
//                                                                                                                 notok                                        
function loadImage(imagePaths){
        $.ajax({
		url: "../../task/getRealImage.do",
		type: "GET",
		data: {"path":imagePaths},
		datdType: "JSON",
		success: function (result) {
			 //var image = result;
            //console.log("将图片显示在网页上");
			//console.log(result);
			 //将图片显示在网页上
			 
			 $("#Images tbody").append('<tr>'+
		 '<td><img src="'+result+'"/></td>'+
    	'</tr>');
		
		}
	});
}

//获取任务的条目信息   
/*                                                                               
function getTaskItems(taskCode){
     $.ajax({
		url: "../../task/getTaskItems.do",
		type: "GET",
		data: {"taskCode":taskCode},
		datdType: "JSON",
		success: function (result) {
		console.log
		var items = result.data;
		for(var i=0;i<items.length;i++){
	    $("#items tbody").append('<tr>'+
		 '<td>'+(i+1)+'</td>'+//显示在页面的序号
        '<td>'+items[i].itemName+'</td>'+
		'<td>'+items[i].isException+'</td>'+
    	'</tr>');
		//console.log(points[i].pointCode);
    }  
		}
	 });
}
*/
//保存审阅信息                                                                                                        ok

function savaverify(){
	console.log("保存审阅信息");
	//console.log();
	 $.ajax({
		url: "../../task/checkPassTask.do",
		type: "GET",
		data: {"taskCode":$("#taskCode").val(),"status":1,"advDesc":$("#advDesc").val()},
		datdType: "JSON",
		success: function (result) {
			console.log("审核意见");
			console.log($("#advDesc").val());
			console.log(result);
        }  
		}
	 );
	
}

//查询任务
function searchTask(page){
	var mySelected = document.getElementById("search-type");
	var index= mySelected.selectedIndex;
	var type = mySelected.options[index].text;
	//$("#keyword").val()
	$.ajax({
		url: "../../task/searchTask.do",
		type: "GET",
		data: { "manageCode":"1","page": page ,"searchType":type,"keyword":$("#keyword").val(),"status":"ALL"},
		datdType: "JSON",
		success: function (result) {
			console.log(result);
			changeTable(page, result,searchTask);
			//window.parent.adjustHeight();
		}
	});
    
}