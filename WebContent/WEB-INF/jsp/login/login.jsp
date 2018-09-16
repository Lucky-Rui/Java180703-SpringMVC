<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/bootstrap-3.3.7-dist/css/bootstrap.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/lib/jquery/jquery-1.11.1.js"></script>
</head>
<body>
	<div class="page-header" style="width: 100%; text-align: center;">
		<h1>
			<small>Do You Like What You See!</small>
		</h1>
	</div>
	<div style="width: 100%; text-align: center;">
		<form action="${pageContext.request.contextPath}/login?method=login"
			method="post">
			<div class="form-group">
				<label for="exampleInputName1">登录名</label> <input name="name"
					style="width: auto; margin: auto;" type="text" class="form-control"
					id="exampleInputName1" placeholder="登陆名">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">密码</label> <input name="password"
					style="width: auto; margin: auto;" type="password" class="form-control"
					id="exampleInputPassword1" placeholder="密码">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">验证码</label> <input
					style="width: auto; margin: auto;"
					name="checkCode" type="text" class="form-control"
					id="exampleInputPassword1" placeholder="验证码"> <br /> <img
					id="codeImg" alt=""
					src="${pageContext.request.contextPath}/checkImg"
					onclick="refreshCode()">
			</div>
			<br />
			<button type="submit" class="btn btn-default">登陆</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
				class="btn btn-default"
				href="${pageContext.request.contextPath}/register.jsp" role="button">注册</a>
		</form>
	</div>
	<script type="text/javascript">
			function refreshCode() {
				$("#codeImg").attr("src", "${pageContext.request.contextPath}/checkImg?"+Math.random());
			}
	</script>
</body>
</html>
