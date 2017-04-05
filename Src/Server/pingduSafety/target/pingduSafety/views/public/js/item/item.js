/**
 * 
 */
//获取条目列表
function getItemList(page){
   $.ajax({
		url: "/item/getItemList.do",
		type: "GET",
		data: { "page": page },
		datdType: "JSON",
		success: function (result) {
			changeTable(page, result, getItemList);
			//window.parent.adjustHeight();
		}
	});
}
//修改条目列表表格
function changeTable(page, result, callback) {

	//获取数据
	var items = result.items;
	var page = result.page;
	var sumpage = result.sumpage;


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

	// 设置 table
	$("#list-table tbody").empty();
	for (var i = 0; i < items.length; i++) {
		$("#list-table tbody").append('<tr>' +
			'<td>' + i + '</td>' +
			'<td>' + items[i].itemInfo + '</td>' +
			'<td>' +
			'<button onclick="turnToPage(\'modify.html?itemCode=' + items[i].itemCode + '\')">修改</button>'+
			'<button class="alert-btn" onclick="deleteItem(\'' + items[i].itemCode + '\')">删除</button>' +
			'</td>' +
			'</tr>');
	}
	
}

//查询条目
function searchItem(page) {
	 $.ajax({
		url: "/item/getItemList.do",
		type: "GET",
		data: { "page": page },
		datdType: "JSON",
		success: function (result) {
			//console.log("6666");
			changeTable(page, result, searchItem);
			//window.parent.adjustHeight();
		}
	});
	/*
	$.ajax({
		url: "/item/searchItem.do",
		type: "GET",
		data: { "content": $("#keyword").val(), "page": page },
		dataType: "text",//
		success: function (result) {
			
			changeTable(page, result, searchItem);
		}
	});
	*/
}
//删除条目
function deleteItem(itemCode) {
	var a = new sslAlert({
		"titleName": "确认删除？",
		"cancleBtn": "取消",
		"okBtn": "删除",
		"callback": function () {
			console.log("This is callback");
			$.ajax({
				url: "/item/deleteItem.do",
				type: "GET",
				data: { "itemCode": itemCode },
				dataType: "text",
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

//添加条目
function addItem(){
	var inputs = $(".information input");
	for (var i = 0; i < inputs.length; i++) {
		if (inputs.eq(i).val() == "" || inputs.eq(i).val() == null) {
			inputs.eq(i).css({
				"border-color": "red",
				"border-style": "dashed",
				"border-width": "2px"
			});
			return;
		}
	}
	var a = new sslAlert({
		"titleName": "确认添加吗？",
		"cancleBtn": "取消",
		"okBtn": "确认",
		"callback": function () {
			//console.log(1);
            var itemContent = $("#itemContent").val();
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
//获取所有项点
 function getAllPoints(){
     $.ajax({
		url:"/point/getAllPoints.do",
		dataType:"json",
		type:"GET",
		success:function(result){
			console.log(result);
			for(var i=0;i<result.length;i++){
				$("#pointsDiv").append("<div class='col-sm-2 point'><input type='checkbox' name='point' value='"+result[i].pointCode+"' onchange='checkChange(this)'/>&nbsp;&nbsp;"+result[i].pointCode+"."+result[i].pointName+"</div>");
			}
		}
	});
 }


//修改条目
