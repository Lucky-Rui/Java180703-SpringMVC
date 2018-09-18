<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/bootstrap-3.3.7-dist/css/bootstrap.css" />
</head>
<body>
	<!--导航开始-->
	<nav class="navbar navbar-default">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">教务管理系统</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a
						href="${pageContext.request.contextPath}/student?method=pageList"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;学生管理
					</a></li>
					<li class="active"><a
						href="${pageContext.request.contextPath}/banji?method=pageList"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;班级管理</a></li>
					<li><a href="${pageContext.request.contextPath}/course?method=pageList"><span class="glyphicon glyphicon-home"
							aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;课程管理</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-home"
							aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;教务管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/online_user_list.jsp"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;在线人数</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a
						href="${pageContext.request.contextPath}/student?method=pageList"><span>欢迎：${user.name}</span></a></li>
					<li><a
						href="${pageContext.request.contextPath}/login?method=logout"><span
							class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;退出</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<!--导航结束-->
	<!--内容部分开始-->
	<div class="container">
		<div class="row">
			<!--左边部分（链接列表组）开始-->
			<div class="col-md-2">
				<div class="list-group">
					<a href="${pageContext.request.contextPath}/banji?method=pageList"
						class="list-group-item "> 班级列表 </a> <a href="banji_add.jsp"
						class="list-group-item active">班级添加 </a>
				</div>
			</div>
			<!--左边部分（链接列表组）结束-->
			<!--右边部分（form表单）开始-->
			<div class="col-md-10">
				<form style="width: 100%; text-align: center;"
					action="${pageContext.request.contextPath}/banji?method=insert"
					method="post">
					<div class="form-group">
						<label for="name">班级名称</label> <input type="text" name="name"
							id="name" onblur="checkName()" style="width: auto; margin: auto;"
							class="form-control" placeholder="例如：Java1809">
					</div>
					<button type="submit" class="btn btn-default">添加</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="reset" class="btn btn-default">重置</button>
				</form>
			</div>
			<!--右边部分（form表单）结束-->
		</div>

	</div>
	<!--内容部分结束-->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/lib/layer/layer.js"
		type="text/javascript" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/js/mylayer.js"
		type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		function checkName(){
			var name = document.getElementById("name").value;
			//ajax请求验证这个用户名
			$.post(
				"${pageContext.request.contextPath}/banji?method=checkName",
				{"name":name},
				function(data) {
					//{"isExist":isExist}
					if (data.isExist){
						//警告
						mylayer.errorAlert("该班级已经存在，请使用其它班级名称");
					} else {
						//正确
						mylayer.success("该班级名可以使用");
					}
				},
				"json"
			);
		}
	</script>
</body>
</html>
