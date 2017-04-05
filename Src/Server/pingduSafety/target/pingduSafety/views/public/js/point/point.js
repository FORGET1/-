//获取项点列表

function getPointList(page){
	   $.ajax({
			     url:"/point/getPointList.do",
				 type:"GET",
				 data:{"page":page},
				 dataType:"JSON",
				 success:function(result){
					    changeTable(page,result,getPointList);
					 }
			  });
	}

//填充项点表格

function changeTable(page,result,callback){
	   //获取数据
	var points = result.points;
	var page = result.page;
	var lastpage = result.lastpage;
	
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
        '<td>'+i+'</td>'+//显示在页面的序号
        //'<td>'+points[i].pointCode+'</td>'+//项点编号
		'<td><a href="pointInfo.html?"pointCode='+points[i].pointCode+'>'+points[i].pointName+'</a></td>'+
		 '<td>'+points[i].entName+'</td>'+
        '<td>'+points[i].entPrincipal+'</td>'+
        '<td>'+points[i].prinPhone+'</td>'+
        '<td>'+
            '<button onclick="turnToPage(\'modifyPoint.html?pointCode='+points[i].pointCode+'\')">修改</button>'+
            '<button class="alert-btn" onclick="deletePoint(\''+points[i].pointCode+'\')">删除</button>'+
        '</td>'+
    	'</tr>');
    }  
	}
	
//删除项点

function deletePoint(pointCode){
       var a = new sslAlert({
		   "titleName":"确认删除？",
            "cancleBtn":"取消",
            "okBtn":"删除",
            "callback":function(){
                //console.log("This is callback");
                $.ajax({
			        url:"/point/deletePoint.do",
			        type:"GET",
			        data:{"pointCode":pointCode},
			        dataType:"text",
			        success:function(result){
			            console.log(result);
			           // console.log(result);
			            location.reload(true);   
			       }
			    });
                }
			});
	
	
	   a.show();
	
   }

//添加项点
function addPoint(){
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
	var a = new sslAlert({
						 "titleName":"确认添加吗？",
						 "cancleBtn":"取消",
						 "okBtn":"确认",
						 "callback":function(){
							// var options = $("#company-belong option:selected");
							 var mySelected = document.getElementById("company-belong");
							 var index= mySelected.selectedIndex;

							 var pointName = $("#point-name").val();
							 var entName = mySelected.options[index].text;//下拉框
							 var manDepartment = $("#management-department").val();
							 var pointAddr = $("#point-address").val();
							 var entPrincipal = $("#point-entPrincipal").val();
                             var prinPhone = $("#prinPhone").val();
                             var GPX_X = $("#GPX-X").val();
                             var GPX_Y = $("#GPX-Y").val();
                             var note = $("secyrity-tips").val();
                            
							 var newPoint = {"pointName":pointName,"entName":entName,"manDepartment":manDepartment,
                                            "pointAddr":pointAddr,"entPrincipal":entPrincipal,"prinPhone":prinPhone,
                                            "GPX_X":GPX_X,
                                            "GPX_Y":GPX_Y,
                                            "note":note
                                        };
							//console.log(entName);
							 $.ajax({
									
									 url:"/point/addPoint.do",
									 type:"POST",
									 data:{"newPoint":newPoint},
									 dataType:'text',//应该是Json格式
									 success:function(result){
										 //console.log("添加AJAX");
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
						 
						 });
	
        a.show();
}

//查看项点详细信息

function getPointInfo(pointCode){
	//获取项点详细信息
   $.ajax({
	   url:"/point/pointInfo.do",
	   type:"GET",
	   data:{"pointCode":pointCode},
	   dataType:"JSON",
	   success:function(result){
           console.log(pointCode)
		   //填充项点详细信息表格
		   var point = result.point,
		   /*
		   $("#name").
		   $("#company")
		   $("#department")
		   $("#address")
		   $("#entPrincipal")
		   $("#prinPhone")
		   $("#GPS-X")
		   $("#GPS-Y")
		  */
	   }
	   
   });
   getPointItems(1);
}
 //获取项点相关条目
 function getPointItems(page){

		$.ajax({
					url:"/point/getPointItems.do",
					type:"GET",
					data:{"pointCode":pointCode,"page":page},
					dataType:"JSON",
					success:function(result){
							changePointItemTable(page,result,pointCode,getPointItems);
						}
					});
 }
//填充项点条目表格
function changePointItemTable(page,result,pointCode,callback){
    var items = result.items;
	var page = result.page;
	var lastpage = result.lastpage;
	
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
     $("#pointItem-table tbody").empty();
    for(var i=0;i<items.length;i++){
        $("#pointItem-table tbody").append('<tr>'+
        '<td>'+i+'</td>'+//显示在页面的序号
        //'<td>'+items[i].itemCode+'</td>'+//项点编号
        '<td>'+items[i].itemInfo+'</td>'+
        '<td>'+
            '<button class="alert-btn" onclick="deletePointItem(\''+items[i].itemCode,+pointCode+'\')">删除</button>'+
        '</td>'+
    	'</tr>');
    }  
}
//删除项点关联的条目
function deletePointItem(pointCode,itemCode){
	$.ajax({
		url:"/point/deletePointItem.do",
		type:"GET",
		data:{"pointCode":pointCode,"itemCode":itemCode},
		dataType:"JSON",
        success:function(result){
			location.reload(true); 
		}
	});

}
//查找项点

function searchPoint(page){
	var mySelected = document.getElementById("search-type");
	var index= mySelected.selectedIndex;
	var type = mySelected.options[index].text;
	$.ajax({
		url:"/point/searchPoint.do",
		type:"GET",
		data:{"key":$("#keyword").val(), "page": page,"type":type},
		dataType:"JSON",
        success:function(result){
			changeTable(page,result,searchPoint);
         //
		}
	});
}
//修改项点
function modifyPoint(pointCode){
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