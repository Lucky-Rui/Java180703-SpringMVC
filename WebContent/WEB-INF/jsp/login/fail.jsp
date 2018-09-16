<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="3;url=${pageContext.request.contextPath}/login.jsp">
<title>Insert title here</title>
<script type="text/javascript">
	function info() {
		//循环执行,1秒执行一次
		window.setInterval("Countdown()", 1000);
	}
	function Countdown() {
		if(document.getElementById("msg").innerHTML!=1){
		document.getElementById("msg").innerHTML=document.getElementById("msg").innerHTML-1;
		}
	}
</script>
<style type="text/css">
		html,body{ 
		 	margin:0;
		 	padding:0;
		 	height:100%;
	 	}
	</style>
</head>
<body onload="info()">
<table width="100%" height="100%" align="center">
		<tr>
			<td align="center" valign="middle">
				<h1>用户名或密码错误,<span id="msg">3</span>秒返回登录页面</h1>
			</td>
		</tr>
</table>
</body>
</html>