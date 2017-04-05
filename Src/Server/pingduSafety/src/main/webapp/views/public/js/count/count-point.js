/**
 * 
 */
var pointList = {};
var current = '';
var pre = '123414';
$(document).ready(function(){
	appendContent();
	$("#dept-choose").hide();
	$(".dept-user").hide();
	$(".dept-row").hide();
	showPoint();
//	$.ajax({
//        url:"../department/getAllDept.do",
//        data:{},
//        async:false,
//        dataType:"json",
//        type:"GET",
//        success:function(result){
//        	console.log(result);
//        	for(var i=0;i<result.length;i++){
//        		$("#show-dept tbody").append("<tr title='点击查看部门项点统计信息' onclick=showDeptPoint('"+result[i].deptCode+"','"+result[i].deptName+"')>" +
//        				"<td>"+(i+1)+"</td>" +
//        				"<td>"+result[i].deptName+"</td>" +
//        				"<td>"+result[i].deptTel+"</td>" +
//        						"</tr>");
//        	}
//        }
//	});

});

function appendContent(){
	$(".dept-info").after('<div class="row dept-row">'+
                '<div class="row title-row">'+
                    '<div class="row">'+
                     '   <div class="col-sm-10"><span id="dept-point-info"><i class="fa fa-file-o"></i>项点统计信息</span></div>'+
                      '  <div class="col-sm-1"><span class="p-i" title="图表切换" onclick="deptToggle()"><i class="fa fa-bar-chart-o" ></i>图表</span></div>'+
//                       '  <div class="col-sm-1"><span class="p-i" title="返回部门" onclick="closePoint()"><i class="fa fa-times" ></i>返回</span></div>'+
                    '</div>'+
                '</div><br>'+
                '<div class="row table-row">'+
                 '   <table class="table table-bordered table-hover" id="dept-count">'+
                  '     <thead>'+
                   '    <tr>'+
                    '    <th>序号</th>'+
                      '  <th>项点</th>'+
                       ' <th>总数</th>'+
                       ' <th>正常</th>'+
                        '<th>异常</th>'+
                        '<th>异常率</th>'+
//                        '<th>整改次数</th>'+
                        '</tr>'+
                        '</thead>'+
                        '<tbody>   '+                      
                       ' </tbody>'+
                   ' </table>      '+
               ' </div>'+
               ' <div class="row chart-row" id="dept-choose">'+
                	'<div class="row choose-row" style="margin-left:25%">'+
                		'<div class="col-sm-1 sumNum" onclick=chart("sumNum","总数")>总数</div> '+              		
                		'<div class="col-sm-1 normalNum"  onclick=chart("normalNum","正常")>正常</div>'+
                		'<div class="col-sm-1 abnormalNum" onclick=chart("abnormalNum","异常")>异常</div>'+
                		'<div class="col-sm-1 abnormalRate" onclick=chart("abnormalRate","异常率")>异常率</div>'+
//                		'<div class="col-sm-1 reformFrequency" onclick=chart("reformFrequency","整改次数")>整改次数</div> '+             		
                	'</div>'+
                	'<div class="row">   '+           	
                	 '<div id="dept-chart" style="height:400px"></div>'+
                	 '</div>'+
               ' </div>'+
               ' </div>');
//	$(".dept-info").after(' <div class="row dept-user">'+
//       	 '<div class="row title-row">'+
//        ' <div class="row">'+
//            ' <div class="col-sm-10"><span id="dept-user-info">》项点设备统计信息</span></div>'+
//            ' <div class="col-sm-1"><span class="p-i" title="图表切换" onclick="userToggle()"><i class="fa fa-bar-chart-o" ></i>图表</span></div>'+
//            ' <div class="col-sm-1"><span class="p-i" title="返回项点" onclick="closeUser()"><i class="glyphicon glyphicon-remove" ></i>返回</span></div>'+
//        ' </div>'+
// 	  '</div>'+
// 	  '<div class="row table-row">'+
//        ' <table class="table table-bordered table-hover" id="user-count">'+
//          '  <thead>'+
//          '  <tr>'+
//          '   <th>序号</th>'+
//         '	<th>设备</th>'+
//         	'<th>总数</th>'+
//         '	<th>正常</th>'+
//         '	<th>异常</th>'+
//         '	<th>异常率</th>'+
//         	'<th>整改次数</th>'+
//            ' </tr>'+
//           '  </thead>'+
//            ' <tbody> '+               
//            ' </tbody>'+
//        ' </table>'+
// 	'</div>'+
//		'<div class="row chart-row">'+
//			'<div class="row choose-row" id="user-choose">'+
//				'<div class="col-sm-1 sumNum" onclick=chartUser("sumNum","总数")>总数</div>		'+
// 		'<div class="col-sm-1 normalNum"  onclick=chartUser("normalNum","正常")>正常</div>'+
// 		'<div class="col-sm-1 abnormalNum" onclick=chartUser("abnormalNum","异常")>异常</div>'+
// 		'<div class="col-sm-1 abnormalRate" onclick=chartUser("abnormalRate","异常率")>异常率</div>'+
// 		'<div class="col-sm-1 reformFrequency" onclick=chartUser("reformFrequency","整改次数")>整改次数</div>'+
//			'</div>'+
//			'<div class="row">'+
//				'<div id="user-chart" style="height: 400px"></div>'+
//			'</div>'+
//		'</div>'+
//	'</div>');
}
function showDeptPoint(deptCode,deptName){
	$(".dept-info").toggle();
	$(".dept-row").toggle();
	$(".dept-row .table-row").show();
	$(".dept-row .chart-row").hide();
	$("#dept-point-info").html("<i class='fa fa-file-o'></i>"+deptName+"--项点统计信息")
	getDeptList(deptCode);
}
function showPoint() {
	$(".dept-info").toggle();
	$(".dept-row").toggle();
	$(".dept-row .table-row").show();
	$(".dept-row .chart-row").hide();
	//getDeptList(deptCode);
	getPointList();
}

function closePoint(){
	$(".dept-info").toggle();
	$(".dept-row").toggle();
}


function chart(eng,chi){
	current = eng;
	$("."+pre).removeClass("current-choose");
	$("."+current).addClass("current-choose");
	pre = eng;
	var data= getData(eng);
	console.log(data)
	createChart(chi,data.userName,data.userData);
}


function deptToggle() {

	$(".dept-row .table-row").toggle();
	
	$("#dept-choose").toggle();
	chart('sumNum','总数')
}
function getData(index) {
	data = {"userName":[],"userData":[]};
	console.log(pointList)
	var users = pointList.points;
	var counts = pointList.ecs;
	for(var i=0;i<users.length;i++){
		data.userName.push(users[i].pointName);
		data.userData.push(counts[i][index]);
	}
	return data;
}

function toggle() {

	$(".table-row").toggle();
	
	$(".chart-row").toggle();
}


function getDeptList(deptCode){
	$.ajax({
        url:"../point/getCountPoint.do",
        data:{"deptCode":deptCode},
        async:false,
        dataType:"json",
        type:"GET",
        success:function(result){
        	console.log(result)
        	pointList = result;
        	var users = pointList.points;
        	var counts = pointList.ecs;
        	$("#dept-count tbody").children().remove();
        	for(var i=0;i<users.length;i++){
        		$("#dept-count tbody").append("<tr title='点击查看项点设备详情' onclick=initDeptUser('"+users[i].pointName+"','"+users[i].pointCode+"')>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+users[i].pointName+"</td>" +
							"<td>"+counts[i].sumNum+"</td>" +
							
							"<td>"+counts[i].normalNum+"</td>" +
							"<td>"+counts[i].abnormalNum+"</td>" +
							"<td>"+counts[i].abnormalRate+"</td>");
//							"<td>"+counts[i].reformFrequency+"</td>");
        	}
        	}
        });
}
function getPointList(){
	$.ajax({
        url:"../point/getCountPoint.do",
//        data:{"deptCode":deptCode},
        async:false,
        dataType:"json",
        type:"GET",
        success:function(result){
        	console.log(result)
        	pointList = result;
        	var users = pointList.points;
        	var counts = pointList.ecs;
        	$("#dept-count tbody").children().remove();
        	for(var i=0;i<users.length;i++){
        		$("#dept-count tbody").append("<tr>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+users[i].pointName+"</td>" +
							"<td>"+counts[i].sumNum+"</td>" +
							
							"<td>"+counts[i].normalNum+"</td>" +
							"<td>"+counts[i].abnormalNum+"</td>" +
							"<td>"+counts[i].abnormalRate+"</td>" );
//							"<td>"+counts[i].reformFrequency+"</td>");
        	}
        	}
        });
}

function createChart(subtext,xList,listData){
	var myChart = echarts.init(document.getElementById('dept-chart'));

	var option = {
		title : {
			text : '项点统计信息',
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
