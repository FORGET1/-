/**
 * 
 */

var current = '';
var pre = '123414';
$(document).ready(function(){
	appendContent();
//	$("#dept-choose").fadeOut();
//	$(".dept-user").hide();
//	deptList = {};
//	$("#count").parent().next().slideDown();
//	getDeptList();
//	chart("sumNum","总数");
	initUser();
});
function appendContent(){
	$(".dept-row").append('<div class="row chart-row" id="dept-choose">'+
        	'<div class="row choose-row" >'+
        		'<div class="col-sm-1 sumNum" onclick=chart("sumNum","总数")>总数</div>'+
        		'<div class="col-sm-1 planNum" onclick=chart("planNum","计划")>计划</div>'+
        		'<div class="col-sm-1 reformNum" onclick=chart("reformNum","整改")>整改</div>'+
        		'<div class="col-sm-1 specialNum" onclick=chart("specialNum","特殊")>特殊</div>'+
        		'<div class="col-sm-1 finishNum" onclick=chart("finishNum","已完成")>已完成</div>'+
        		'<div class="col-sm-1 unfinishNum" onclick=chart("unfinishNum","未完成")>未完成</div>'+
        		'<div class="col-sm-1 normalNum"  onclick=chart("normalNum","正常")>正常</div>'+
        		'<div class="col-sm-1 abnormalNum" onclick=chart("abnormalNum","异常")>异常</div>'+
        		'<div class="col-sm-1 achievedRate" onclick=chart("achievedRate","完成率")>完成率</div>'+
        		
        	'</div>'+
        	'<div class="row" style="font-family:Microsoft Yahei">'+
        	
        	' <div id="dept-chart" style="height:400px"></div>'+
        	 '</div>'+
        '</div>');
	$(".dept-row").after('<div class="row dept-user">'+
                	 '<div class="row title-row">'+
	                   ' <div class="col-sm-12">'+
	                       ' <div class="col-sm-10"><span id="dept-user-info"><i class="fa fa-users"></i>人员统计信息</span></div>'+
	                        '<div class="col-sm-1">'+
	                        '<span class="p-i" onclick="userToggle()"><i class="fa fa-bar-chart-o" ></i>显示图表</span>'+
	                        '</div>'+
	                       ' <div class="col-sm-1">'+
//	                        '<span class="p-i" onclick="closeUser()"><i class="fa fa-times" ></i>返回</span>'+
	                        '</div>'+
	                    '</div>'+
                	 ' </div><br>'+
                	  '<div class="row table-row">'+
	                  '  <table class="table table-bordered table-hover" id="user-count">'+
	                       '<thead>'+
	                       '<tr>'+
	                       ' <th>序号</th>'+
	                        '<th>人员</th>'+
	                        '<th>总数</th>'+
//	                       ' <th>计划</th>'+
//	                       ' <th>整改</th>'+
//	                        '<th>特殊</th>'+
	                       ' <th>已完成</th>'+
//	                        '<th>未完成</th>'+
	                        '<th>正常</th>'+
	                        '<th>异常</th>'+
	                       ' <th>异常率</th>'+
	                       ' </tr>'+
	                       ' </thead>'+
	                        '<tbody>	'+                           
	                        '</tbody>'+
	                    '</table>'+
                	'</div>'+
					'<div class="row chart-row">'+
						'<div class="row choose-row" id="user-choose" style="margin-left:30%">'+
							'<div class="col-sm-1 sumNum" onclick=chartUser("sumNum","总数")>总数</div>'+
//							'<div class="col-sm-1 planNum" onclick=chartUser("planNum","计划")>计划</div>'+
//							'<div class="col-sm-1 reformNum" onclick=chartUser("reformNum","整改")>整改</div>'+
//							'<div class="col-sm-1 specialNum" onclick=chartUser("specialNum","特殊")>特殊</div>'+
							'<div class="col-sm-1 finishNum" onclick=chartUser("finishNum","已完成")>已完成</div>'+
//							'<div class="col-sm-1 unfinishNum" onclick=chartUser("unfinishNum","未完成")>未完成</div>'+
							'<div class="col-sm-1 normalNum" onclick=chartUser("normalNum","正常")>正常</div>'+
							'<div class="col-sm-1 abnormalNum" onclick=chartUser("abnormalNum","异常")>异常</div>'+
							'<div class="col-sm-1 achievedRate" onclick=chartUser("achievedRate","完成率")>异常率</div>'+
						'</div>'+
						'<div class="row" style="font-family:Microsoft Yahei">'+
							'<div id="user-chart" style="height: 500px;font-family:Microsoft Yahei"></div>'+
						'</div>'+
					'</div>'+
				'</div>');
}

function chart(eng,chi){
	current = eng;
	$("#dept-choose ."+pre).removeClass("current-choose");
	$("#dept-choose ."+current).addClass("current-choose");
	pre = eng;
	var data = getAllData(eng,deptList.detail.length,deptList.detail);
	var depts = getAllDeptName(deptList.dept);
	createChart(chi,depts,data);
}

function getAllData(index,len,detail) {
	list = [];
	for(var i=0;i<len;i++){
		list.push(detail[i][index]);
	}
	return list;
}
function getAllDeptName(depts) {

	list = [];
	for(var i =0;i<depts.length;i++){
		list.push(depts[i].deptName);
	}
	return list;
}

function deptToggle() {

	$(".dept-row .table-row").fadeToggle();
	
	$("#dept-choose").fadeToggle();
}


function getDeptList(){
	$.ajax({
        url:"../department/getAllDept.do",
        data:{},
        async:false,
        dataType:"json",
        type:"GET",
        success:function(result){
           
            deptList["dept"] = result;
           
            deptList["detail"] = [];
            for(var i=0;i<result.length;i++){
        	  detail = getDeptCount(result[i].deptCode);
        	  deptList["detail"].push(detail);
        	  $("#dept-count tbody").append("<tr title='点击查看部门人员详情' onclick=initDeptUser('"+result[i].deptName+"','"+result[i].deptCode+"')>" +
        	  								"<td>"+(i+1)+"</td>" +
        	  								"<td>"+result[i].deptName+"</td>" +
        	  								"<td>"+detail.sumNum+"</td>" +
        	  								"<td>"+detail.planNum+"</td>" +
        	  								"<td>"+detail.reformNum+"</td>" +
        	  								"<td>"+detail.specialNum+"</td>" +
        	  								"<td>"+detail.finishNum+"</td>" +
        	  								"<td>"+detail.unfinishNum+"</td>" +
        	  								"<td>"+detail.normalNum+"</td>" +
        	  								"<td>"+detail.abnormalNum+"</td>" +
        	  								"<td>"+detail.achievedRate+"</td>");
           }
          
        }
     });
	
}

function getDeptCount(deptCode) {
	var re;
	$.ajax({
        url:"../task/getDeptCount.do",
        data:{"deptCode":deptCode},
        dataType:"json",
        type:"GET",
        async:false,
        success:re=function(result){
        	
           re=result;
           
        }
     });
	return re;
}

function createChart(subtext,xList,listData){
	var myChart = echarts.init(document.getElementById('dept-chart'));

	var option = {
		title : {
			text : '部门统计信息',
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
