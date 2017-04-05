var express = require('express');
var app = express();
app.use(express.static('./'));
 
app.get('/', function (req, res) {
   res.send('Hello World');
})
 
var server = app.listen(8081, function () {
 
  var host = server.address().address
  var port = server.address().port
 
  console.log("应用实例，访问地址为 http://%s:%s", host, port)
 
})

app.post('/login.do',function(req,res){
  res.send({"status":4000,"userPwd":"test123","userSalt":"test","userType":"super","manageCode":""});
});

app.get('/test',function(req,res){
	console.log(req.query.page);
  res.send({"status":4000,"userPwd":"test123","userSalt":"test","userType":"super","manageCode":""});
});


app.get('/getLicenseTypeList', function(req, res) {
	
	var page = req.query.page; // 通过 req.query获取请求参数
	console.log(page);
	var data
	if(page == 1){
		data= {
			license:
				[
					{
						enterpriseType:'chemical-plant',
						licenseName:'营业执照',
						validperiod:{
							"year":"3",
							"month":"6"
						},
						licenseCode:'1123',
					},
					{
						enterpriseType:'chemical-plant',
						licenseName:'营业执照',
						validperiod:{
							"year":"0",
							"month":"6"
						},
						licenseCode:'1123',
					},
					{
						enterpriseType:'GAS',
						licenseName:'营业执照',
						validperiod:{
							"year":"2",
							"month":"6"
						},
						licenseCode:'1123',
					},
					{
						enterpriseType:'GAS',
						licenseName:'营业执照',
						validperiod:{
							"year":"3",
							"month":"10"
						},
						licenseCode:'1123',
					},
					{
						enterpriseType:'chemical-plant',
						licenseName:'营业执照',
						validperiod:{
							"year":"0",
							"month":"3"
						},
						licenseCode:'1123',
					}
				],
			page: 1,
			lastPage: 2
		}
	}else{
		data= {
			license:[
				{
					enterpriseType:'GAS',
					licenseName:'营业执照',
					validperiod:{
						"year":"3",
						"month":"10"
					},
					licenseCode:'1123',
				},
				{
					enterpriseType:'chemical-plant',
					licenseName:'营业执照',
					validperiod:{
						"year":"0",
						"month":"3"
					},
					licenseCode:'1123',
				}
			],
			page: 2,
			lastPage: 2
		}
	}
	res.send(data);
});

app.get('/getReceivedNotice', function(req, res) {
	
	var page = req.query.page; // 通过 req.query获取请求参数
	console.log(page);
	var data
	if(page == 1){
		data= {
			notice:
				[
					{
						readNum:1,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:2,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:3,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:4,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:5,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:6,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:7,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:8,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:9,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:10,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:11,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:12,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:13,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:14,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:15,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					}
				],
			page: 1,
			lastPage: 2
		}
	}else{
		data= {
			notice:[
				{
					readNum:1,
					recipient:'安监办',
					sender:'公司11',
					time:'2016-10-10',
					title:'关于安全注意事项',
					noticeCode:'123'
				},
				{
					readNum:2,
					recipient:'安监办',
					sender:'公司11',
					time:'2016-10-10',
					title:'关于安全注意事项',
					noticeCode:'123'
				}
			],
			page: 2,
			lastPage: 2
		}
	}
	res.send(data);
});

app.get('/searchNotice', function(req, res) {

	var data= {
			"notice":
				[
					{
						readNum:1,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:2,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:3,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:4,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:5,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					},
					{
						readNum:6,
						recipient:'安监办',
						sender:'公司11',
						time:'2016-10-10',
						title:'关于安全注意事项',
						noticeCode:'123'
					}
				],
			"page": 1,
			"lastPage": 1
		}
  res.send(data);
});

app.get('/getSubordinateList',function(req,res){
	var data = [
		{
			"companyCode" : "1122" ,
			"companyName" : "企业1"
		},
		{
			"companyCode" : "1122" ,
			"companyName" : "安监办2"
		},
		{
			"companyCode" : "1122" ,
			"companyName" : "安监办3"
		},
		{
			"companyCode" : "1122" ,
			"companyName" : "安监办4"
		},
		{
			"companyCode" : "1122" ,
			"companyName" : "安监办5"
		},
		{
			"companyCode" : "1122" ,
			"companyName" : "安监办6"
		},
		{
			"companyCode" : "1122" ,
			"companyName" : "安监办7"
		},
		{
			"companyCode" : "1122" ,
			"companyName" : "企业1"
		},
		{
			"companyCode" : "1122" ,
			"companyName" : "企业2"
		},
		{
			"companyCode" : "1122" ,
			"companyName" : "企业3"
		},
				{
			"companyCode" : "1122" ,
			"companyName" : "企业4"
		},
		{
			"companyCode" : "1122" ,
			"companyName" : "企业5"
		},
	]
  	res.send(data);
});

app.get('/getNoticeInfo',function(req,res){
	var data = {
		"noticeTitle" : "xxxxxxx号通知",
		"noticeType" : "notice",
		"noticeTime" : "2016-10-10",
		"noticeSender" : "安监办223",
		"noticeContent" : "北京时间2017年3月31日消息，近日有国外网站曝出了一部分一加2017年新款旗舰机一加5的渲染图和配置信息。从目前得到的消息来看，一加5依然会在性能上大做文章，采用高通最新的处理器骁龙835。同时，一加5将采用全面屏设计，这也是他的亮点之一。从现在获得的渲染图来看，一加5采用全面屏设计，同时在视觉上实现了无边框。一加5将指纹识别系统设置在了手机背部，这样一来可以使得手机正面实现超高的屏占比。一加5下方采用了条形状的虚拟按键，白色的灯和黑色的下巴浑然一体。在配置方面，一加5将采用5.5英寸的2K分辨率显示屏，7毫米纤薄机身。该机搭载高通最新的骁龙835处理器，高配版本存储组合为8GB RAM+256GB ROM，后置2300万双摄像头，支持光学防抖和变焦。一加5运行Android 7.0系统，采用3000毫安大电池，支持第三代快速闪充。"
	}
  	res.send(data);
});

app.get('/getReceiverList',function(req,res){
	var page = req.query.page;
	var data;
	if(page == 1){
		data = {
			"receiverList":[
				{
					"receiverName" : "张1",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张2",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张3",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张4",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张5",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张6",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张7",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张8",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张9",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张10",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张11",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张12",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张13",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张14",
					"readStatus" : "true"
				},
				{
					"receiverName" : "张15",
					"readStatus" : "true"
				}
			],
			"page" : "1",
			"lastPage" : "3"
		}
	}else if(page == 2){
		data = {
			"receiverList":[
				{
					"receiverName" : "李1",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李2",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李3",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李4",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李5",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李6",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李7",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李8",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李9",
					"readStatus" : "true"
				},
				{
					"receiverName" : "李10",
					"readStatus" : "true"
				},
				{
					"receiverName" : "李11",
					"readStatus" : "true"
				},
				{
					"receiverName" : "李12",
					"readStatus" : "true"
				},
				{
					"receiverName" : "李13",
					"readStatus" : "true"
				},
				{
					"receiverName" : "李14",
					"readStatus" : "true"
				},
				{
					"receiverName" : "李15",
					"readStatus" : "true"
				}
			],
			"page" : "2",
			"lastPage" : "3"
		}
	}else{
		data = {
			"receiverList":[
				{
					"receiverName" : "李1",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李2",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李3",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李4",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李5",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李6",
					"readStatus" : "false"
				},
				{
					"receiverName" : "李7",
					"readStatus" : "false"
				}
			],
			"page" : "3",
			"lastPage" : "3"
		}
	}
  	res.send(data);
});

app.get('/getCompanyList', function(req, res) {
	
	var page = req.query.page; // 通过 req.query获取请求参数
	console.log(page);
	var data
	if(page == 1){
		data= {
			company:
				[
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:123456780,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:123456781,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:123456782,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:123456783,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:123456784,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:123456785,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					}
				],
			page: 1,
			lastPage: 2
		}
	}else{
		data= {
			notice:[
				{
					contactNumber:12345678,
					affiliatedDepartment:'安监办',
					companyName:'加油站11',
					contactPerson:'王二',
					companyType:'加油站',
					companyCode:'123'
				},
				{
					contactNumber:12345678,
					affiliatedDepartment:'安监办',
					companyName:'加油站11',
					contactPerson:'王二',
					companyType:'加油站',
					companyCode:'123'
				}
			],
			page: 2,
			lastPage: 2
		}
	}
	res.send(data);
});

app.get('/searchCompany', function(req, res) {

	var data= {
			"company":
				[
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					},
					{
						contactNumber:12345678,
						affiliatedDepartment:'安监办',
						companyName:'加油站11',
						contactPerson:'王二',
						companyType:'加油站',
						companyCode:'123'
					}
				],
			"page": 1,
			"lastPage": 1
		}
  res.send(data);
});




app.get('/getCommitmentList', function(req, res) {
	
	var page = req.query.page; // 通过 req.query获取请求参数
	console.log(page);
	var data
	if(page == 1){
		data= {
			commitment:
				[
					{
						'uploadDate':'2016-11-11',
						'affiliatedDepartment':'安监办',
						'enterpriseName':'加油站11',
						'specifiedDate':'2016-11-11',
						'CommitmentCode':'123'
					},
					{
						'uploadDate':'2016-11-11',
						'affiliatedDepartment':'安监办',
						'enterpriseName':'加油站11',
						'specifiedDate':'2016-11-11',
						'CommitmentCode':'123'
					},
					{
						'uploadDate':'2016-11-11',
						'affiliatedDepartment':'安监办',
						'enterpriseName':'加油站11',
						'specifiedDate':'2016-11-11',
						'CommitmentCode':'123'
					},
					{
						'uploadDate':'2016-11-11',
						'affiliatedDepartment':'安监办',
						'enterpriseName':'加油站11',
						'specifiedDate':'2016-11-11',
						'CommitmentCode':'123'
					},
					{
						'uploadDate':'2016-11-11',
						'affiliatedDepartment':'安监办',
						'enterpriseName':'加油站11',
						'specifiedDate':'2016-11-11',
						'CommitmentCode':'123'
					},
					{
						'uploadDate':'2016-11-11',
						'affiliatedDepartment':'安监办',
						'enterpriseName':'加油站11',
						'specifiedDate':'2016-11-11',
						'CommitmentCode':'123'
					},
					{
						'uploadDate':'2016-11-11',
						'affiliatedDepartment':'安监办',
						'enterpriseName':'加油站11',
						'specifiedDate':'2016-11-11',
						'CommitmentCode':'123'
					},
					{
						'uploadDate':'2016-11-11',
						'affiliatedDepartment':'安监办',
						'enterpriseName':'加油站11',
						'specifiedDate':'2016-11-11',
						'CommitmentCode':'123'
					}
				],
			page: 1,
			lastPage: 2
		}
	}else{
		data= {
			commitment:[
				{
					'uploadDate':'2016-11-11',
					'affiliatedDepartment':'安监办',
					'enterpriseName':'加油站11',
					'specifiedDate':'2016-11-11',
					'CommitmentCode':'123'
				},
				{
					'uploadDate':'2016-11-11',
					'affiliatedDepartment':'安监办',
					'enterpriseName':'加油站11',
					'specifiedDate':'2016-11-11',
					'CommitmentCode':'123'
				},
				{
					'uploadDate':'2016-11-11',
					'affiliatedDepartment':'安监办',
					'enterpriseName':'加油站11',
					'specifiedDate':'2016-11-11',
					'CommitmentCode':'123'
				}
			],
			page: 2,
			lastPage: 2
		}
	}
	res.send(data);
});

app.get('/searchCommitment', function(req, res) {

	var data= {
			"commitment":
				[
					{
						'uploadDate':'2016-11-11',
						'affiliatedDepartment':'安监办',
						'enterpriseName':'加油站11',
						'specifiedDate':'2016-11-11',
						'CommitmentCode':'123'
					},
					{
						'uploadDate':'2016-11-11',
						'affiliatedDepartment':'安监办',
						'enterpriseName':'加油站11',
						'specifiedDate':'2016-11-11',
						'CommitmentCode':'123'
					},
					{
						'uploadDate':'2016-11-11',
						'affiliatedDepartment':'安监办',
						'enterpriseName':'加油站11',
						'specifiedDate':'2016-11-11',
						'CommitmentCode':'123'
					},
					{
						'uploadDate':'2016-11-11',
						'affiliatedDepartment':'安监办',
						'enterpriseName':'加油站11',
						'specifiedDate':'2016-11-11',
						'CommitmentCode':'123'
					}
				],
			"page": 1,
			"lastPage": 1
		}
  res.send(data);
});