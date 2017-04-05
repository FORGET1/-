/**
 * 
 */

$(document).ready(function(){
	$(".calender").hide();
	modal = $("#modal");
});


function deleteRecord(code){
	Lobibox.confirm({
		   title:"警告",
			msg: "是否删除该记录 ?",
		    buttons:{
		    	 ok: {
		             'class': 'btn btn-info',
		             text:"确定"
		         },
		         cancel:{
		        	 'class':'btn btn-default',
		        	 text:"取消"
		         }
		    },
		    callback: function (lobibox, type) {
		    	if(type == "ok"){
		    		console.log("ok");
		    		$.ajax({
		    			url:"deletePatrolRecord.do",
		    			data:{"patrolRecordCode":code},
		    			type:"GET",
		    			success:function(result){
		    				if(result == "SUCCESS"){
		    					Lobibox.confirm({
		    						   title:" 提示",
		    							msg: "删除成功！",
		    						    buttons:{
		    						    	 ok: {
		    						             'class': 'btn btn-info',
		    						             text:"确 定"
		    						         },
		    						        
		    						    },
		    						    
		    						});
		    					setTimeout(function() {
		    						location.reload(true);
								}, 500); 

		    				}else{
		    					Lobibox.confirm({
		    						   title:" 提示",
		    							msg: "删除成功！",
		    						    buttons:{
		    						    	 ok: {
		    						             'class': 'btn btn-info',
		    						             text:"确 定"
		    						         },
		    						        
		    						    },
		    						    
		    						});

		    				}
		    				 
		    			}
		    		});
		    	}else if(type == "cancel"){
		    		
		    	}
		    }
		});
}

function showMap(recordCode){
	modal.css("display", "block");
	$("#container").remove();
	modal.append("<div id='container'></div>");
	console.log("begin cal");
	$.ajax({
		url:"../patrolRecord/getUserPath.do",
		data:{"patrolRecordCode":recordCode},
		dataType:"json",
		success:function(result){
			console.log(result);
			show(result);
		}
	});

}
function show(dotList){
	var needPoints = dotList.patrolOrbits;//路径
	var dots = dotList.points;//项点
//	var pointList = dotList.pointList;
	var map = new BMap.Map("container");
	var point = new BMap.Point(122.087307,37.535444);//学校地点
	map.centerAndZoom(point,17);//地图初始化
	map.enableScrollWheelZoom();//鼠标滚轮缩放
	map.addControl(new BMap.NavigationControl());
	map.addControl(new BMap.ScaleControl());
	map.addControl(new BMap.OverviewMapControl());
	map.addControl(new BMap.MapTypeControl());
	map.setCurrentCity("威海");
	for(var i=0;i<dots.length;i++){
		var point = new BMap.Point(dots[i].pointGpsX,dots[i].pointGpsY);
		var marker = new BMap.Marker(point);

		var label = new BMap.Label((i+1)+"-"+dots[i].pointName,{offset:new BMap.Size(-15,-15),position:point});
		label.setStyle({ 
			color : "blue", 
			 
			backgroundColor :"0.05",
			border :"0", 
			fontWeight :"bold",
			
			});
		marker.setLabel(label);
		map.addOverlay(marker);
		}
//添加点
//var marker = new BMap.Marker(point); // 创建标注 
// marker.enableDragging();//标注可拖动
// marker.addEventListener("dragend",function(e){
// 	alert(e.point.lng+","+e.point.lat);
// });
//map.addOverlay(marker);
//for(var i=0;i<needPoints.length;i++){
//	var point = new BMap.Point(needPoints[i].gps_X,needPoints[i].gps_Y);
//	var marker = new BMap.Marker(point);
//	
//	var label = new BMap.Label((i+1)+"-"+needPoints[i].pointName,{offset:new BMap.Size(-15,-15),position:point});
//	label.setStyle({ 
//		color : "blue", 
//		 
//		backgroundColor :"0.05",
//		border :"0", 
//		fontWeight :"bold",
//		
//		});
//	marker.setLabel(label);
//	map.addOverlay(marker);
//}
	var firstPoint = new BMap.Point(needPoints[0].gps_X,needPoints[0].gps_Y);
	var firstmarker = new BMap.Marker(firstPoint);
	
	var firstlabel = new BMap.Label(needPoints[0].gpsdate+" "+needPoints[0].gpstime,{offset:new BMap.Size(-15,-15),position:firstPoint});
	firstlabel.setStyle({ 
		color : "blue", 
		 
		backgroundColor :"0.05",
		border :"0", 
		fontWeight :"bold",
		
		});
	firstmarker.setLabel(firstlabel);
	map.addOverlay(firstmarker);
	var endPoint = new BMap.Point(needPoints[needPoints.length-1].gps_X,needPoints[needPoints.length-1].gps_Y);
	var endmarker = new BMap.Marker(endPoint);
	
	var endlabel = new BMap.Label(needPoints[needPoints.length-1].gpsdate+" "+needPoints[needPoints.length-1].gpstime,{offset:new BMap.Size(-15,-15),position:endPoint});
	endlabel.setStyle({ 
		color : "blue", 
		 
		backgroundColor :"0.05",
		border :"0", 
		fontWeight :"bold",
		
		});
	endmarker.setLabel(endlabel);
	map.addOverlay(endmarker);
//for(var i=0;i<pointList.length;i++){
//	var point = new BMap.Point(pointList[i].gps_X,pointList[i].gps_Y);
//	var marker = new BMap.Marker(point);
//	
//	map.addOverlay(marker);
//}
var points = [];
for(var i=0;i<needPoints.length;i++){
	var point = new BMap.Point(needPoints[i].gps_X,needPoints[i].gps_Y);
	points.push(point);
	
}
//map.addEventListener("click",function(e){
//	console.log(e.point.lng+","+e.point.lat);
//});

//添加线
var polyLine = new BMap.Polyline(points,{strokeColor:"red",strokeWeight:3,strokeOpacity:0.8});
map.addOverlay(polyLine);

}
function closeModal(){
	modal.css("display", "none");
}