var express = require('express');
var app = express();
app.use(express.static('.'));
 
app.get('/', function (req, res) {
   res.send('Hello World');
})
 
var server = app.listen(8081, function () {
 
  var host = server.address().address
  var port = server.address().port
 
  console.log("应用实例，访问地址为 http://%s:%s", host, port)
 
})

app.post('/login.do',function(req,res){
  res.send({"status":4000,"userPwd":"test123","userSalt":"test","userType":2,"manageCode":""});
});

app.get('/user/getUsersByRole.do',function(req,res){
	
	console.log(req.path);
	res.send({"dataList":[{"userCode":"001","userName":"sun","userManage":"安监局"},{"userCode":"002","userName":"piao","userManage":"安监局"}],"lastpage":3,"page":1});

});
app.post('/user/searchUser.do',function(req,res){
	console.log("/searchUserList.do");
	res.send({"users":[{"userCode":"001","userName":"sun","userManage":"安监局"}],"lastpage":2,"page":1});
});
app.get('/user/deleteUser.do',function(req,res){
	console.log(req.path);
	res.send("SUCCESS");
});
app.get('/user/userInfo.do',function (req,res) {
	console.log(req.path);
	res.send({"userCode":"001","userName":"sun","userManage":"安监局","userId":"1234567890","userSex":"男","userBirth":"1990/01/01"});
});
app.get('/user/userInfo.do',function (req,res) {
	console.log(req.path);
	res.send({"userCode":"001","userName":"sun","userManage":"安监局","userId":"1234567890","userSex":"男","userBirth":"1990/01/01","userPwd":"123"});
});

app.get('/department/getDepartmentList.do',function(req,res){
	
	console.log(req.path);
	res.send({"dataList":[{"departmentCode":"001","departmentName":"安监办1","departmentTel":"12312123"},{"departmentCode":"002","departmentName":"安监办2","departmentTel":"12312123"}],"sumPage":3,"page":1});

});