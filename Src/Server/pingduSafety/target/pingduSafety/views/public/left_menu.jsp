<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="views/public/css/jquery-accordion-menu.css" rel="stylesheet" type="text/css" />

<link href="views/public/css/dashboard.css" rel="stylesheet">
<link href="views/public/css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="views/public/css/Lobibox.min.css" rel="stylesheet">
<style type="text/css">
	
	body{background:#f0f0f0;}
	.content {
		width:13%;
		margin-top:0%;
		margin-right:1%;
		margin-left:1%;
		margin-bottom:1%;
		font-weight:bold;
	} 
	.filterinput {
	 	background-color:rgba(249, 244, 244, 0); 
	 	border-radius:15px; 
	 	width:90%; 
	 	height:30px; 
	 	border:thin solid #FFF; 
	 	text-indent:0.5em; 
/* 	 	font-weight:bold;  */
	 	color:#FFF; 
	 } 
	 #demo-list a{ 
	 	overflow:hidden; 
		text-overflow:ellipsis; 
	 	-o-text-overflow:ellipsis; 
	 	white-space:nowrap; 
	 	width:100%; 
	 	font-weight:bold;	
	 	font-family:microsoft yahei;
	 } 
	 .top-tip {
	 	color: white;
	 	word-spacing:1%; 
	 	letter-spacing: 10px;
 	 	font-weight:bold; 
	 	font-size:30px;
	 	text-align: center;
	 	letter-spacing:-1px;	 	
/* 	 	box-shadow: 5px 10px 5px #888888; 	 */
	 }

</style>
<script src="views/public/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="views/public/js/lobibox.min.js"></script>
<script src="views/public/js/jquery-accordion-menu.js" type="text/javascript"></script>

<div class="row" style="font-family:Microsoft Yahei; font-size:9px; margin-left:1%; margin-right:1%; margin-top:0%">
     <div class="col-sm-12 right-menu" >
		<div class="row index-parent">
        	<div class="row index" >
        		<div class="col-sm-2" align="left">
        			<br>
        			<a href="http://www.cnjingyou.net/" title="山东京友电子有限公司">
						<img alt="" src="views/public/images/logo.png">
					</a>	
        		</div>
        		<div class="col-sm-7" style="color:	#27408B; font-weight:bold; font-size:30px; margin-top:1%;" align="center">
        			<p>哈工大(威海)后勤保卫处 智能巡检管理系统</p>
        		</div>
            	<div class="col-sm-3" style="font-weight:normal; margin-top:3%;" align="right"  >
            	    <i class="fa fa-user"></i><span id="current-admin" > 当前用户：</span>
	                <!-- <span><a href="#" >修改密码</a></span>
	                <span><a href="#" >帮助</a></span> -->
	                <span><a href="#" onclick="safeQuit()">安全退出</a></span>
            	</div>
			</div>
        </div>
     </div>
</div>

<div class="content" >
	<div id="jquery-accordion-menu" class="jquery-accordion-menu #191c2c">
		<ul id="demo-list">		 
		    <li><a href="user.do"><i class="fa fa-group"></i>用户管理</a></li>
			<!-- <li><a href="../department/getDeptList.do?page=1&deptLevel=1"><i class="fa fa-home"></i>部门管理 </a></li> -->
			<!-- <li><a href="item.do"><i class="fa fa-cog"></i>设备管理 </a>
				<ul class="submenu">
					<li><a href="../dev/getDevList.do?page=1&devLevel=1">设备管理 </a></li>
					<li><a href="../devType/getDevTypeList.do?page=1">设备类型管理</a></li>
				</ul>
			</li> -->
			<!-- <li><a href="../patrolType/getPatrolTypeList.do?page=1"><i class="fa fa-th-list"></i>巡检类型管理</a></li> -->
			<li><a href="point.do"><i class="fa fa-file-o"></i>巡检项点管理</a></li>
			<li><a href="item.do"><i class="fa fa-list-alt"></i>巡检条目管理</a></li>
			<li><a href="#"><i class="fa fa-suitcase"></i>巡检任务管理</a>
				<ul class="submenu">
					<!-- <li><a href="../task/getTaskList.do?page=1&taskStatus=ALL"><i class="fa fa-li"></i>全部巡检任务</a></li> -->
					<li><a href="task.do?status=11">已完成任务管理</a></li>
					<!-- <li><a href="../task/getTaskList.do?page=1&taskStatus=10">未审核任务管理</a></li>
					<li><a href="../task/getTaskList.do?page=1&taskStatus=00">未完成任务管理 </a></li> -->
					<!-- <li><a href="../task/turnToAddTask.do">下达新任务 </a></li> -->
				</ul>
			</li>
			<li><a href="warning.do"><i class="fa fa-warning"></i>报警管理</a></li>
			<li><a href="notice.do"><i class="fa fa-bell"></i>通知公告管理 </a></li>
			<li><a href="patrolRecord.do"><i class="fa fa-car"></i>巡检记录管理</a></li>
			
			<li><a href="database.do"><i class="fa fa-database"></i>数据库管理 </a></li>
			<li><a href="#"><i class="fa fa-bar-chart-o"></i>统计管理</a>
				<ul class="submenu">
					<li><a href="count.do?s=Count">数据统计</a></li>
					<li><a href="count.do?s=ErrorCount">错误统计</a></li>
				</ul>
			</li>			
		</ul>
		
	</div>
</div>

<script type="text/javascript">
(function($) {
$.expr[":"].Contains = function(a, i, m) {
	return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
};
function filterList(header, list) {
	//@header 头部元素
	//@list 无需列表
	//创建一个搜素表单
	var form = $("<form>").attr({
		"class":"filterform",
		action:"#"
	}), input = $("<input>").attr({
		"class":"filterinput",
		type:"text"
	});
	$(form).append(input).appendTo(header);
	$(input).change(function() {
		var filter = $(this).val();
		if (filter) {
			$matches = $(list).find("a:Contains(" + filter + ")").parent();
			$("li", list).not($matches).slideUp();
			$matches.slideDown();
		} else {
			$(list).find("li").slideDown();
		}
		return false;
	}).keyup(function() {
		$(this).change();
	});
}
$(function() {
	filterList($("#form"), $("#demo-list"));
});
})(jQuery);	
</script>

<script type="text/javascript">
	jQuery("#jquery-accordion-menu").jqueryAccordionMenu();	
</script>