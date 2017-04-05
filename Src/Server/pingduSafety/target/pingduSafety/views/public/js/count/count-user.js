/**
 * 
 */
var userList = {};
var current = '';
var pre = '123414';
function initDeptUser(deptName,deptCode) {
	console.log(deptName);
	$(".dept-user").show();
	$(".dept-row").hide();
	$(".dept-user .table-row").show();
	$(".dept-user .chart-row").hide();
	$("#dept-user-info").html("<i class='fa fa-users'></i>"+deptName+"部门人员统计信息");
	$.ajax({
        url:"../user/getCountPerson.do",
        data:{"deptCode":deptCode},
        async:false,
        dataType:"json",
        type:"GET",
        success:function(result){
        	userList = result;
        	var users = userList.users;
        	var counts = userList.dcs;
        	$("#user-count tbody").children().remove();
        	for(var i=0;i<users.length;i++){
        		$("#user-count tbody").append("<tr>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+users[i].userName+"</td>" +
							"<td>"+counts[i].sumNum+"</td>" +
							"<td>"+counts[i].planNum+"</td>" +
							"<td>"+counts[i].reformNum+"</td>" +
							"<td>"+counts[i].specialNum+"</td>" +
							"<td>"+counts[i].finishNum+"</td>" +
							"<td>"+counts[i].unfinishNum+"</td>" +
							"<td>"+counts[i].normalNum+"</td>" +
							"<td>"+counts[i].abnormalNum+"</td>" +
							"<td>"+counts[i].achievedRate+"</td>");
        	}
        	}
        });
	
}
function initUser() {
	$(".dept-user").show();
	$(".dept-row").hide();
	$(".dept-user .table-row").show();
	$(".dept-user .chart-row").hide();
//	$("#dept-user-info").html("<i class='fa fa-users'></i>"+deptName+"部门人员统计信息");
	$.ajax({
        url:"../user/getCountPerson.do",
//        data:{"deptCode":deptCode},
        async:false,
        dataType:"json",
        type:"GET",
        success:function(result){
        	userList = result;
        	var users = userList.users;
        	var counts = userList.dcs;
        	$("#user-count tbody").children().remove();
        	for(var i=0;i<users.length;i++){
        		$("#user-count tbody").append("<tr>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+users[i].userName+"</td>" +
							"<td>"+counts[i].sumNum+"</td>" +
//							"<td>"+counts[i].planNum+"</td>" +
//							"<td>"+counts[i].reformNum+"</td>" +
//							"<td>"+counts[i].specialNum+"</td>" +
							"<td>"+counts[i].finishNum+"</td>" +
//							"<td>"+counts[i].unfinishNum+"</td>" +
							"<td>"+counts[i].normalNum+"</td>" +
							"<td>"+counts[i].abnormalNum+"</td>" +
							"<td>"+counts[i].achievedRate+"</td>");
        	}
        	}
        });
}

function userToggle(){
	$(".dept-user .table-row").toggle();
	$(".dept-user .chart-row").toggle();
	chartUser("sumNum","总数");
}

function closeUser() {
	$(".dept-user").hide();
	$(".dept-row").show();
}

function chartUser(eng,chi) {
	current = eng;
	$("#user-choose ."+pre).removeClass("current-choose");
	$("#user-choose ."+current).addClass("current-choose");
	pre = eng;
	var data= getData(eng);
	console.log(data)
	createUserChart(chi,data.userName,data.userData);
}
function getData(index) {
	data = {"userName":[],"userData":[]};
	var users = userList.users;
	var counts = userList.dcs;
	for(var i=0;i<users.length;i++){
		data.userName.push(users[i].userName);
		data.userData.push(counts[i][index]);
	}
	return data;
}

function createUserChart(subtext,xList,listData){
	var myChart = echarts.init(document.getElementById('user-chart'));
	console.log(subtext)
	var option = {
		title : {
			text : '部门人员统计信息',
			subtext : subtext
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [subtext ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : xList
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [
				{
					name : subtext,
					type : 'bar',
					data : listData,
					markPoint : {
						data : [ {
							type : 'max',
							name : '最大值'
						}, {
							type : 'min',
							name : '最小值'
						} ]
					},
					markLine : {
						data : [ {
							type : 'average',
							name : '平均值'
						} ]
					}
				}]
	};
	

	// 为echarts对象加载数据 
	myChart.setOption(option); 
}