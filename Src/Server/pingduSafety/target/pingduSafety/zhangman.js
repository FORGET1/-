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


//获取应急救援库列表
app.get('/emergencyRescue/getEmeRescueList.do',function(req,res){
    console.log("");
	res.send({
			 "emeRescues":
			 [{"rescueCode":"001","rescueName":"001","phone":"17862700001","email":"001@163.com","address":"00001单元1号"},
			  {"rescueCode":"001","rescueName":"002","phone":"17862700002","email":"002@163.com","address":"00002单元2号"},
			  {"rescueCode":"001","rescueName":"003","phone":"17862700003","email":"003@163.com","address":"00003单元3号"}],
			 "lastpage":3,"page":1});
});

//删除应急救援库信息
app.get('/emergencyRescue/deleteEmeRescue.do',function(req,res){
	res.send("SUCCESS");
});

//查找应急救援库信息(根据keyword和value)
app.get('/emergencyRescue/searchEmeRescue.do',function(req,res){
	console.log("/searchEmeRescue.do");
	res.send({
			 "emeRescues":
			 [{"rescueCode":"001","rescueName":"001","phone":"17862700001","email":"001@163.com","address":"00001单元1号"}],"lastpage":3,"page":1});
});
//按编号查询应急救援库信息（供修改救援信息时使用）
app.get('/emergencyRescue/searchEmeRescueByCode.do',function(req,res){
    //console.log("/searchEmeRescue.do");
	res.send({
	"emeRescue":
	{"rescueCode":"001","rescueName":"001","phone":"17862700001","email":"001@163.com","address":"00001单元1号"},"lastpage":3,"page":1});
});
//添加应急救援库信息
app.post('/emergencyRescue/addEmeRescue.do',function(req,res){
	res.send("SUCCESS");
});

//修改应急救援库信息
app.post('/emergencyRescue/modifyEmeRescue.do',function(req,res){
	res.send("SUCCESS");
});





//获取项点列表
app.get('/point/getPointList.do',function(req,res){
    console.log("");
	res.send({
			 "points":
			 [{"pointCode":"001","pointName":"烟囱1","entName":"**化工厂","entPrincipal":"小李","prinPhone":"1111111111"},
			  {"pointCode":"001","pointName":"烟囱2","entName":"**石油","entPrincipal":"小王","prinPhone":"22222222"},
			  {"pointCode":"001","pointName":"烟囱3","entName":"**化工厂","entPrincipal":"小松","prinPhone":"11111111"}],
			 "lastpage":3,"page":1});
});

//删除项点
app.get('/point/deletePoint.do',function(req,res){
	res.send("SUCCESS");
});

//添加项点
app.post('/point/addPoint.do',function(req,res){
	res.send("SUCCESS");
});

//获取项点详细信息
app.get('/point/pointInfo.do',function(req,res){
	res.send({
		"point":{"pointCode":"001","pointName":"烟囱1","entName":"**化工厂","entPrincipal":"小李","prinPhone":"1111111111"}
	    
			 });
});

//获取项点关联的条目(需要新增！！！！)
app.get('/point/getPointItems.do',function(req,res){
	res.send({
		     "pointItems":
			    [{"itemCode":"001","itemName":"是否有积水"},
		         {"itemCode":"002","itemName":"是否有积水"}]
			});
});
//删除项点相关条目（需要新增！！！）
app.get('/point/deletePointItem.do',function(req,res){
	res.send("SUCCESS");
});
//查找项点
app.get('/point/searchPoint.do',function(req,res){
	req.send({
			 "points":
			 [{"pointCode":"001","pointName":"烟囱1","entName":"**化工厂","entPrincipal":"小李","prinPhone":"1111111111"},
			  {"pointCode":"001","pointName":"烟囱2","entName":"**石油","entPrincipal":"小王","prinPhone":"22222222"},
			  {"pointCode":"001","pointName":"烟囱3","entName":"**化工厂","entPrincipal":"小松","prinPhone":"11111111"}],
			 "lastpage":3,"page":1}
			 );
});




//条目列表

app.get('/item/getItemList.do',function(req,res){
     res.send({"items":[{"itemCode":"001","itemInfo":"是否有积水"},{"itemCode":"002","itemInfo":"机器是否正常"}],"sumpage":3,"page":1});
});
//查询条目
app.get('/item/searchItem.do',function(req,res){
     res.send({"items":[{"itemCode":"003","itemInfo":"是否有积"},{"itemCode":"004","itemInfo":"机器是否正常"}],"sumpage":3,"page":1});
});

//删除条目
app.get('/item/deleteItem.do',function(req,res){
	res.send("SUCCESS");
});
//添加条目

//获取所有项点
app.get('/point/getAllPoints.do',function(req,res){
	res.send({ "points":
			 [{"pointCode":"001","pointName":"烟囱1"},
			  {"pointCode":"002","pointName":"烟囱2"},
			  {"pointCode":"003","pointName":"烟囱3"}]
	         }
			);
});