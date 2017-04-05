//var pointCode = GetQueryString("pointCode");
//var str =sessionStorage.user;
//var currentUser = JSON.parse(str);
var userType = "3";//var userType = currentUser.userType;
//var manageCode = currentUser.manageCode;
//"userType" 1:超级  2：安监局  3：安监办  4企业
//"manageCode" "super":超级  "":安监局   "deptCode":安监办/企业编号
//ainjdebx安监局  ainjdejx安监办  qinydex企业  suindeperx超级管理员 


function turnToadd()
{
	turnToPage('addPointItems.html?pointCode='+pointCode+'\'');
}
//获取项点列表                                                                                                          ok
function getPointList(page){
	  
	   $.ajax({
			     url:"../../point/getPointList.do",
				 type:"GET",
				 data:{"page":page},
				 dataType:"JSON",
				 success:function(result){
					  //console.log("111");
					    //console.log(result);
						//console.log("1111");
					    changeTable(page,result,getPointList);
					 }
			  });
	}

//填充项点表格                                                                                                        ok

function changeTable(page,result,callback){
	   //获取数据
	var points = result.dataList;
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
    for(var i=0;i<points.length;i++){
        $("#list-table tbody").append('<tr>'+
        '<td>'+(i+1)+'</td>'+//显示在页面的序号
        //'<td>'+points[i].pointCode+'</td>'+//项点编号
		'<td><a href="pointInfo.html?pointCode='+points[i].pointCode+'">'+points[i].pointName+'</a></td>'+
		 '<td>'+points[i].entName+'</td>'+
        '<td>'+points[i].pointPerson+'</td>'+
        '<td>'+points[i].pointPersonTel+'</td>'+
        '<td>'+
            '<button onclick="turnToPage(\'modifyPoint.html?pointCode='+points[i].pointCode+'\')">修改</button>'+
            '<button class="alert-btn" onclick="deletePoint(\''+points[i].pointCode+'\')">删除</button>'+
        '</td>'+
    	'</tr>');
		//console.log(points[i].pointCode);
    }  
	}
	
//删除项点                                                                                                           ok

function deletePoint(pointCode){
       var a = new sslAlert({
		   "titleName":"确认删除？",
            "cancleBtn":"取消",
            "okBtn":"删除",
            "callback":function(){
                //console.log("This is callback");
                $.ajax({
			        url:"../../point/deletePoint.do",
			        type:"GET",
			        data:{"pointCode":pointCode},
			        dataType:"JSON",
			        success:function(result){
			            //console.log(result);
			           // console.log(result);
			            location.reload(true);   
			       }
			    });
                }
			});
	
	
	   a.show();
	
   }

//查找用户所能管辖的企业，并获取用户所属于的部门名称 安检办获取自己所能管辖的企业名字，安监局获取所有的企业名称   企业？？？
function getEntsByUserType(){
	//判断用户 安监局查看所有任务  安检办查看自己所管辖企业的任务
    switch(userType){
    	case "2":
    		getAllEnts(1);
    		break;
        case "3":
           getEntsByDept(1);
            break;
		default:     //企业和超级管理员不能查看？？？？？
		    break;
    }
}

//添加项点
function addPoint(){
    var mySelected = document.getElementById("company-belong");
	var index= mySelected.selectedIndex;
	var entCode = mySelected.options[index].val();
    /*
   	var inputs = $(".information input");
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
	*/
	var pointName = $("#point-name").val();
	//var entName = mySelected.options[index].text;//下拉框
	var deptCode = "1";
	var entCode = "1";
	//var manDepartment = $("#management-department").val();
	var pointAddr = $("#point-address").val();
	var entPrincipal = $("#point-entPrincipal").val();
	var prinPhone = $("#prinPhone").val();
	var lantitude = $("#GPS-X").val();
	var longtitude = $("#GPS-Y").val();
	var note = $("secyrity-tips").val(); 
	var point = {"pointName":pointName,"deptCode":deptCode,
				"pointAddr":pointAddr,"entPrincipal":entPrincipal,"prinPhone":prinPhone,
				"lantitude":lantitude,
				"longtitude":longtitude,
				"note":note
			};
			console.log("添加");
	$.ajax({
		
			url:"../../point/addPoint.do",
			type:"POST",
			data:{"point":point,"entCode":entCode},
			dataType:'JSON',
			success:function(result){
				console.log("添加项点");
				console.log(result);
				if(result == "SUCCESS"){
				var b = new sslAlert({
				"titleName":"添加成功！",
				"cancleBtn":"取消",
				"okBtn":"确认"});
				b.show();
				//location.reload(true);
			}else{
				var b = new sslAlert({
				"titleName":"添加失败！",
				"cancleBtn":"取消",
				"okBtn":"确认"});
				b.show();
				}}
		});
	}
				
	
        

//添加项点是查询用户所属的部门名称
//添加项点时查找用户所能管辖的企业
function findEnts(deptCode){
    
	$.ajax({
			url:"../../enterprise/getEnterpriseByDepNoPage.do",
			type:"GET",
			data:{"deptCode":deptCode},
			dataType:"JSON",
			success:function(result){
				console.log(result);
				// console.log(result);
				//location.reload(true); 
				//填充在下拉框中  
				//$("#company-belong").append('<option value='+Code+'>'+name+'</option>');
			}
			    });
}

//查看项点详细信息                                                                                                      ok

function getPointInfo(pointCode){
	//获取项点详细信息
	//console.log(pointCode);
   $.ajax({
	   url:"../../point/pointInfo.do",
	   type:"GET",
	   data:{"pointCode":pointCode},
	   dataType:"JSON",
	   success:function(result){
           //console.log(result);
		   //填充项点详细信息表格
		   var point = result.data;
		   
		   $("#name").text(point.pointName);
		   $("#company").text(point.entName);
		   $("#department").text(point.deptName);
		   $("#address").text(point.pointAddr);
		   $("#entPrincipal").text(point.pointPerson);
		   $("#prinPhone").text(point.pointPersonTel);
		   $("#GPS-X").text(point.longtitude);
		   $("#GPS-Y").text(point.lantitude);
		   
	   }
	   
   });
   getPointItems(pointCode);
   
}
//安检办获取自己管辖的企业名称
//安监局获取所有企业
//通过用户编号获取用户的部门（用作管辖部门）

 //获取项点相关条目                                                                                              ok
 function getPointItems(pointCode){
        //console.log("55555");
		//console.log(pointCode);
		$.ajax({
					url:"../../point/getItemByPointCode.do",
					type:"GET",
					data:{"pointCode":pointCode},
					dataType:"JSON",
					success:function(result){
						//console.log(pointCode);
						//console.log(result);
						changePointItemTable(result,pointCode);
						}
					});
 }
 
//填充项点条目表格                                                                                               ok

function changePointItemTable(result,pointCode){
    var items = result.dataList;
    // 设置页码
    
    // 设置 table
     $("#pointItem-table tbody").empty();
    for(var i=0;i<items.length;i++){
        $("#pointItem-table tbody").append('<tr>'+
        '<td>'+(i+1)+'</td>'+//显示在页面的序号
        //'<td>'+items[i].itemCode+'</td>'+//项点编号
        '<td>'+items[i].itemInfo+'</td>'+
        '<td>'+
            '<button class="alert-btn" onclick="deletePointItem('+items[i].itemCode+','+pointCode+')">删除</button>'+
        '</td>'+
    	'</tr>');
    }  
}


//删除项点关联的条目                                                                                             ok
function deletePointItem(itemCode,pointCode){
	$.ajax({
		url:"../../point/deletePointItem.do",
		type:"GET",
		data:{"pointCode":pointCode,"itemCode":itemCode},
		dataType:"JSON",
        success:function(result){
			//console.log(result);
			location.reload(true); 
		}
	});

}

//查找项点                                                                                                                  ok

function searchPoint(page){
	var mySelected = document.getElementById("search-type");
	var index= mySelected.selectedIndex;
	var type = mySelected.options[index].text;
	
	//console.log(type);
	//console.log($("#keyword").val());
	$.ajax({
		url:"../../point/searchPoint.do",
		type:"GET",
		data:{"key":$("#keyword").val(), "page": page,"type":type},
		dataType:"JSON",
        success:function(result){
			console.log(result);
			changeTable(page,result,searchPoint);
         //
		}
	});
}

//修改项点详细信息
function modifyPoint(){
	       
	      var pointCode= $("#pointCode").val();// pointCode  
		  var pointName = $("#point-name").val();
		  //var entName = $("#company-belong").val();
		  //var deptName =$("#management-department").val();
		  var pointAddr = $("#point-address").val();
		  var pointPerson = $("#leading-person").val();
		  var pointPersonTel = $("#phone").val();
		  var longtitude = $("#GPS-X").val();
		  var lantitude = $("#GPS-Y").val();
		  var safetyTips = $("#secyrity-tips").val();
          var point = {"pointCode":pointCode,"pointName":pointName,"pointAddr":pointAddr,
				       "pointPerson":pointPerson,"pointPersonTel":pointPersonTel,
				       "longtitude":longtitude,"lantitude":lantitude,"safetyTips":safetyTips};
	$.ajax({
		url:"../../point/modifyPoint.do",
		type:"GET",
		data:{"point":point,"entCode":1},
		dataType:"JSON",
        success:function(result){
			console.log(111111+"aaaaaaaaa");																																																																																																																													
			console.log(result);
		}
	});
}

//修改项点时显示项点信息                                                                                             ok

function pointInfoWhenModify(pointCode){
	//console.log(pointCode);
	
       $.ajax({
	   url:"../../point/pointInfo.do",
	   type:"GET",
	   data:{"pointCode":pointCode},
	   dataType:"JSON",
	   success:function(result){
           //console.log(result);
		   //填充项点详细信息表格
		   
		   var point = result.data;
		   $("#point-name").val(point.pointName);
		   $("#company-belong").val(point.entName);
		   $("#management-department").val(point.deptName);
		   $("#point-address").val(point.pointAddr);
		   $("#leading-person").val(point.pointPerson);
		   $("#phone").val(point.pointPersonTel);
		   $("#GPS-X").val(point.longtitude);
		   $("#GPX-Y").val(point.lantitude);
		   $("#secyrity-tips").val(point.safetyTips);
		   $("#pointCode").val(point.pointCode);
	   }
	   //显示项点的条目
	  
   });
    getPointItems(pointCode);
}

//为项点添加条目
/*
function addItemsForPoint(){
		$.ajax({
		url:"/point/pointInfo.do",
		type:"GET",
		data:{"pointCode":pointCode},
		dataType:"JSON",
        success:function(result){
			changeTable(page,result,searchPoint);
		}
	});

}
*/