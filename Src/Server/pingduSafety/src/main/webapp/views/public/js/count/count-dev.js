/**
 * 
 */
var devList = {};
var current = '';
var pre = '123414';
function initDeptUser(deptName,deptCode) {
	console.log(deptName);
	$(".dept-user").show();
	$(".dept-row").hide();
	$(".dept-user .table-row").show();
	$(".dept-user .chart-row").hide();
	$("#dept-user-info").html("<i class='fa fa-cog' />"+deptName+"--设备统计信息");
	$.ajax({
        url:"../dev/getCountDev.do",
        data:{"pointCode":deptCode},
        async:false,
        dataType:"json",
        type:"GET",
        success:function(result){
        	console.log(result)
        	devList = result;
        	var users = devList.devs;
        	var counts = devList.ecs;
        	$("#user-count tbody").children().remove();
        	for(var i=0;i<users.length;i++){
        		$("#user-count tbody").append("<tr>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+users[i].devName+"</td>" +
							"<td>"+counts[i].sumNum+"</td>" +
							
							"<td>"+counts[i].normalNum+"</td>" +
							"<td>"+counts[i].abnormalNum+"</td>" +
							"<td>"+counts[i].abnormalRate+"</td>" +
							"<td>"+counts[i].reformFrequency+"</td>");
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
	var data= getData2(eng);
	console.log(data)
	createUserChart(chi,data.userName,data.userData);
}
function getData2(index) {
	data = {"userName":[],"userData":[]};
	var users = devList.devs;
	var counts = devList.ecs;
	for(var i=0;i<users.length;i++){
		data.userName.push(users[i].devName);
		data.userData.push(counts[i][index]);
	}
	return data;
}

function createUserChart(subtext,xList,listData){
	var myChart = echarts.init(document.getElementById('user-chart'));
	console.log(subtext)
	console.log(xList)
	console.log(listData)
	var option = {
		title : {
			text : '设备统计信息',
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