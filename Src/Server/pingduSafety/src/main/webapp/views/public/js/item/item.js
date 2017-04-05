/**
 * 
 */
var pointCodes = []; 
//var pointCodesModify = []; 
//获取条目列表                                                                                                           ok
function getItemList(page){
	//console.log("12343");
   $.ajax({
		url: "../../item/getItemList.do",
		type: "GET",
		data: { "page": page },
		datdType: "JSON",
		success: function (result) {
			//console.log(result);
			changeTable(page, result, getItemList);
			//window.parent.adjustHeight();
		}
	});
}
//修改条目列表表格                                                                                                        ok
function changeTable(page, result, callback) {
    //console.log("哈哈哈哈哈哈啊哈");
	//获取数据
	var items = result.dataList;
	var page = result.currentPage;
	var sumpage = result.sumPage;
//
    //console.log("22222222222222");
	// 设置页码
	$("#sumpage").text(sumpage);
	$("#page").text(page);
	if (page == 1) {
		$("#up-btn").css("display", "none");
	} else {
		$("#up-btn").css("display", "inline");
		$("#up-btn").unbind("click");
		$("#up-btn").on("click", function () { callback(page - 1) });
	}
	if (page == sumpage) {
		$("#down-btn").css("display", "none");
	} else {
		$("#down-btn").css("display", "inline");
		$("#down-btn").unbind("click");
		$("#down-btn").on("click", function () { callback(page + 1) });
	}
   // console.log("11111111111111");
	// 设置 table
	$("#list-table tbody").empty();
	for (var i = 0; i < items.length; i++) {
		$("#list-table tbody").append('<tr>' +
			'<td>' + (i+1)+ '</td>' +
			'<td>' + items[i].itemInfo + '</td>' +
			'<td>' +
			'<button onclick="turnToPage(\'modifyItem.html?itemCode=' + items[i].itemCode + '\')">修改</button>'+
			'<button class="alert-btn" onclick="deleteItem(' + items[i].itemCode +')">删除</button>' +
			'</td>' +
			'</tr>');
	}
	
}

//查询条目                                                                                                           ok
function searchItem(page) {
	var keyword = $("#keyword").val();
	//console.log(keyword);
	 $.ajax({
		url: "../../item/searchItem.do",
		type: "GET",
		data: { "page": page,"content":keyword },
		datdType: "JSON",
		success: function (result) {
			//console.log("6666");
			//console.log(result);
			changeTable(page, result, searchItem);
			//window.parent.adjustHeight();
		}
	});
}
//删除条目                                                                                                             ok
function deleteItem(itemCode) {
	//console.log(itemCode+"hhhhhhhh");
	var a = new sslAlert({
		"titleName": "确认删除？",
		"cancleBtn": "取消",
		"okBtn": "删除",
		"callback": function () {
			//console.log("This is callback");
			$.ajax({
				url: "../../item/deleteItem.do",
				type: "GET",
				data: { "itemCode": itemCode },
				dataType: "JSON",
				success: function (result) {
					// console.log(result);
					//console.log(result);
					location.reload(true);
				}
			});
		}
	});
	a.show();
}


/*
	var a = new sslAlert({
		"titleName": "确认添加吗？",
		"cancleBtn": "取消",
		"okBtn": "确认",
		"callback": function () {
			//console.log(1);
            var itemContent = $("#itemContent").val();
			//取得用户所添加的项点
			var pointCodes = {};
			$.ajax({
				url: "/emergencyRescue/addEmeRescue.do",
				type: "POST",
				data: {"newRescue":newRescue},//？？？？,newRescue错误
				dataType: 'text',//应该为JSON格式。
				success: function (result) {
					console.log("6666");
					if (result == "SUCCESS") {
						var b = new sslAlert({
							"titleName": "添加成功！",
							"cancleBtn": "取消",
							"okBtn": "确认"
						});
						b.show();
						//location.reload(true);
					} else {
						var b = new sslAlert({
							"titleName": "添加失败！",
							"cancleBtn": "取消",
							"okBtn": "确认"
						});
						b.show();
					}
				}
			});
		}
	});

	a.show();
}
*/
//获取该用户所能管辖的所有项点                                                                                         ok

 function getRelatedPoints(page){	 
    //console.log("添加条目时查询项点");
     
	 $.ajax({
			url:"../../point/getPointList.do",
			type:"GET",
			data:{"page":page},
			dataType:"JSON",
			success:function(result){
				//console.log(result);
				changePointsTable(page, result,getRelatedPoints)
				}
			  });
 }
 //填充项点列表                                                                                                      ok
 
function changePointsTable(page, result, callback){
	var points = result.dataList;
	var page = result.currentPage;
	var sumpage = result.sumPage;
	$("#sumpage").text(sumpage);
	$("#page").text(page);
	if (page == 1) {
		$("#up-btn").css("display", "none");
	} else {
		$("#up-btn").css("display", "inline");
		$("#up-btn").unbind("click");
		$("#up-btn").on("click", function () { callback(page - 1) });
	}
	if (page == sumpage) {
		$("#down-btn").css("display", "none");
	} else {
		$("#down-btn").css("display", "inline");
		$("#down-btn").unbind("click");
		$("#down-btn").on("click", function () { callback(page + 1) });
	}
    //<input type='checkbox' name="point" style="margin-left:30px; " onchange='checkChange(this)' id="check" value=""/>&nbsp项点1
			//for(var i=0;i<result.length;i++){
			//	$("#pointsDiv").append("<div class='col-sm-2 point'><input type='checkbox' name='point' value='"+result[i].pointCode+"' onchange='checkChange(this)'/>&nbsp;&nbsp;"+result[i].pointCode+"."+result[i].pointName+"</div>");
			//}
	//$("#list-table tbody").empty();
	for (var i = 0; i < points.length; i++) {
	$("#pointsDiv").append("<div><input type='checkbox' name='point' value='"+points[i].pointCode+"' onchange='checkChanges(this)'/>&nbsp;&nbsp;"+points[i].pointCode+"."+points[i].pointName+"</div>");
	}
	
}

//安监局获取所有项点
//function getAllPoints() 
//安监办用部门编号获取获取自己所能管辖的项点    //用户判断学弟写在getPointList里了

//将项点填充在表格里供用户选择




//修改条目                                                                                                               notok

function modifyItem(){
	
	console.log("点击保存修改条目");
    var itemCode = $("#itemCode").val(); 
	console.log("1");
	var itemContent = $("#item-info").val();
	console.log("2");
	console.log(pointCodes);
	console.log("3");
	console.log(itemCode);
	console.log("4");
	console.log(itemContent);
	console.log("开始调用后台函数");
	$.ajax({
			url:"../../item/modifyItem.do",
			type:"POST",
			data:{"itemCode":itemCode,"itemContent":itemContent,"pointCodes":pointCodes},
			dataType:"JSON",
			success:function(result){
				console.log("修改条目成功");
				console.log(result);
	        } }
			  );
}

//获取条目详细信息                                                                                                      ok
function getItemInfo(itemCode){                                                                                        
	//console.log("获取条目详细信息");
	$.ajax({
			url:"../../item/itemInfo.do",
			type:"GET",
			data:{"itemCode":itemCode},
			dataType:"JSON",
			success:function(result){
				//console.log("获取条目详细信息成功");
				//console.log(result);
				//填充item
				var item = result.data;
				$("#itemCode").val(item.itemCode);
				$("#item-info").val(item.itemInfo);
				//changePointsTable(page, result,getRelatedPoints)
				}
			  });
}
//添加条目                                                                                                               notok
function addItem(){
	console.log("添加条目");
	var itemContent=$("#itemContent").val();
	console.log(itemContent);
	console.log(pointCodes);
	$.ajax({
			url:"../../item/addItem.do",
			type:"POST",
			data:{"itemContent":itemContent,"pointCodes":pointCodes},
			dataType:"JSON",
			success:function(result){
				console.log(result);
				//changePointsTable(page, result,getRelatedPoints)
				}
			  });

}
//全选被点击时                                                                                                       notok
function checkAlls(checkBox){
	console.log("当全选被点击");
if($(checkBox).is(':checked')){
	//console.log("all checked");
	for(var i=0;i<$("[name = point]:checkbox").length;i++){
		if($("[name = point]:checkbox").eq(i).is(":checked") == false){
			$("[name = point]:checkbox").eq(i).click();
		}	
	}
}else{
	for(var i=0;i<$("[name = point]:checkbox").length;i++){
		$("[name = point]:checkbox").eq(i).click();
		}
}
}

 //条目的checkBox被点击时                                                                                          ok         
 function checkChanges(checkBox) {
	 	if($(checkBox).is(':checked')){
	 		//console.log("checked");
	 		pointCodes.push($(checkBox).val());
	 		//console.log(pointItem);
	 	}else{
	 		pointCodes.splice(jQuery.inArray($(checkBox).val(),pointCodes),1); 
	 		$('#check-all').attr("checked",false);
	 		//console.log(pointItem);
	 	}
	 }
