

//获取应急救援库信息列表getemergencyRescueList                                                                              ok
window.parent.adjustHeight();
function getemergencyRescueList(page) {
	$.ajax({
		url: "../../emergencyRescue/getEmeRescueList.do",
		type: "GET",
		data: { "page": page },
		datdType: "JSON",
		success: function (result) {
			//console.log(result);
			//console.log(result.status);
			//console.log(result.dataList);
			changeTable(page, result, getemergencyRescueList);
			window.parent.adjustHeight();
		}
	});
	
	
}

//修改表格内容 显示应急救援库信息列表                                                                                        ok

function changeTable(page, result, callback) {

	//获取数据
	var emeRescues = result.dataList;
	var page = emeRescues[emeRescues.length-1];
	var lastpage = emeRescues[emeRescues.length-2];


	// 设置页码
	$("#lastpage").text(lastpage);
	$("#page").text(page);
	if (page == 1) {
		$("#up-btn").css("display", "none");
	} else {
		$("#up-btn").css("display", "inline");
		$("#up-btn").unbind("click");
		$("#up-btn").on("click", function () { callback(page - 1) });
	}
	if (page == lastpage) {
		$("#down-btn").css("display", "none");
	} else {
		$("#down-btn").css("display", "inline");
		$("#down-btn").unbind("click");
		$("#down-btn").on("click", function () { callback(page + 1) });
	}

	// 设置 table
	$("#list-table tbody").empty();
	for (var i = 0; i < emeRescues.length-2; i++) {
		$("#list-table tbody").append('<tr>' +
			'<td>' + (i+1) + '</td>' +
			//'<td>' + emeRescues[i].rescueCode + '</td>' +
			'<td>' + emeRescues[i].rescueName + '</td>' +
			'<td>' + emeRescues[i].phone + '</td>' +
			'<td>' + emeRescues[i].email + '</td>' +
			'<td>' + emeRescues[i].address + '</td>' +
			'<td>' +
			'<button onclick="turnToPage(\'modifytEmeRescue.html?rescueCode=' + emeRescues[i].rescueCode + '\')">修改</button>'+
			'<button class="alert-btn" onclick="deleteRescue(\'' + emeRescues[i].rescueCode + '\')">删除</button>' +
			'</td>' +
			'</tr>');
			//console.log(emeRescues[i].rescueCode);
	}
	
}

//查找应急救援库信息                                                                                                 ajax调用不了 notOK

function searchRescue(page) {
	//获取查找类型
	var mySelected = document.getElementById("search-type");
	var index= mySelected.selectedIndex;
	var type = mySelected.options[index].text;
	var keyword = $("#keyword").val();
	console.log({ "searchType":type,"keyword": keyword , "page": page });
	$("#list-table tbody").empty();
	$.ajax({
		url: "../../emergencyRescue/searchEmeRescue.do",
		type: "GET",
		data: { "searchType":type,"keyword": keyword , "page": page },
		dataType: "JSON",
		success: function (result) {
			console.log("ajax");
			if(result.dataList.length != 0)
			changeTable(page, result, searchRescue);
		}
	});
}

//按编号查找应急救援库信息,并填充在input中                                                                          需新增 notOK

function searchRescueByCode(rescueCode) {
	$.ajax({
		url: "../../emergencyRescue/searchEmeRescue.do",
		type: "GET",
		data: { "searchType":"编号","keyword":rescueCode, "page": 1 },
		dataType: "JSON",
		success: function (result) {
			//$("#resecue-name").val("111");
			$("#resecue-name").val(result.emeRescue.rescueName);
			$("#resecue-phone").val(result.emeRescue.phone);
			$("#resecue-email").val(result.emeRescue.email);
			$("#resecue-address").val(result.emeRescue.address);
		}
	});
}

//修改应急救援库信息                                                                                              ajax调用不了
function modifyRescue() {
	//console.log("rescueCode:");
	//console.log(GetQueryString("rescueCode"));
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
		"titleName": "确认修改吗？",
		"cancleBtn": "取消",
		"okBtn": "确认",
		"callback": function () {
			var rescueName = $("#rescue-name").val();
			var phone = $("#rescue-phone").val();
			var email = $("#rescue-email").val();
			var address = $("#rescue-address").val();
		    var emeRescue = { "rescueCode":"7","rescueName": rescueName, "phone": phone, "email": email, "address": address };
		    //console.log(1);
			
			$.ajax({
				url: "../../emergencyRescue/modifyEmeRescue.do",
				type: "POST",
				data: {"emeRescue":emeRescue,"page":1,"lastpage":1},
				dataType: 'JSON',
				success: function (result) {
					console.log("ajax");
					console.log(result);
					if (result == "SUCCESS") {
						var b = new sslAlert({
							"titleName": "修改成功！",
							"cancleBtn": "取消",
							"okBtn": "确认"
						});
						b.show();
						//location.reload(true);//否则不会出提示框
					} else {
						var b = new sslAlert({
							"titleName": "修改失败！",
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

//删除应急救援库信息                                                                                                      ok
function deleteRescue(rescueCode) {
	var a = new sslAlert({
		"titleName": "确认删除？",
		"cancleBtn": "取消",
		"okBtn": "删除",
		"callback": function () {
			console.log("This is callback");
			$.ajax({
				url: "../../emergencyRescue/deleteEmeRescue.do",
				type: "POST",
				data: { "rescueCode": rescueCode },
				dataType: "JSON",
				success: function (result) {
					location.reload(true);
				}
			});
		}
	});
	a.show();
}



//添加应急救援库信息                                                                                                  OK
function addRescue() {
	//document.write(Date());
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
			var rescueName = $("#rescue-name").val();
			var phone = $("#rescue-phone").val();
			var email = $("#rescue-email").val();
			var address = $("#rescue-address").val();
			var emeRescue = {"rescueName": rescueName, "phone": phone, "email": email, "address": address };
            
			$.ajax({
				url: "../../emergencyRescue/addEmeRescue.do",
				type: "POST",
				data: {"emeRescue":emeRescue,"page":1,"lastpage":1},
				dataType: 'JSON',
				success: function (result) {
					console.log(result.status.msg);
					console.log(emeRescue);
					if (result.status.msg == "SUCCESS") {
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
/*
$('.search-btn').on('click',function(){
	event.preventDefault();
	searchRescue(1);
	console.log("1111111111");
})
$('body').on('click',function(){
	console.log(1213);
})
*/
window.parent.adjustHeight();





