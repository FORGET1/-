<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<% response.setStatus(HttpServletResponse.SC_OK); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500 出错啦</title>
<style type="text/css">
	.return {
		font-size:20px;
		text-align: right;
	}
	.error-title {
		text-align: center;
	}
	.error-title span {
		font-size:50px;
		font-weight:500;
		margin: 5%;
	}
</style>
</head>
<body>
<div class="return">
	<a href="javascript:history.go(-1);">返回</a>
</div>
<div class="error-title">
	<span>5</span><span>0</span><span>0</span>
	<div>出错啦</div>
</div>

</body>
</html>