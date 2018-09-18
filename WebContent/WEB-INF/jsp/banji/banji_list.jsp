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
						class="list-group-item active"> 班级列表 </a> <a href="banji_add.jsp"
						class="list-group-item">班级添加</a>
				</div>
			</div>
			<!--左边部分（链接列表组）结束-->
			<!--右边部分（table表显示信息）开始-->
			<div class="col-md-10">
				<table class="table table-hover">
					<tr>
						<th><input type="checkbox" id="selectAlls"
							onclick="selectAll()" />全选</th>
						<th>ID</th>
						<th>班级名称</th>
						<td>修改</td>
						<td>删除</td>
					</tr>
					<c:forEach items="${pageBean.list}" var="banji">
						<tr>
							<td><input type="checkbox" name="selectIds"
								value="${banji.id}" /></td>
							<td>${banji.id}</td>
							<td>${banji.name}</td>
							<td><a
								href="${pageContext.request.contextPath}/banji?method=toUpdate&id=${banji.id}">修改</a></td>
							<td><a
								href="javascript:deleteById(${banji.id},${pageBean.pageNo})">删除</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!--右边部分（table表显示信息）结束-->
		</div>
	</div>
	<!--内容部分结束-->
	<!--选择页开始  -->
	<div style="text-align: center;">
		<nav aria-label="Page navigation" style="align-self: center;">
			<ul class="pagination">
				<!--上一页开始  -->
				<c:if test="${pageBean.pageNo == 1}">
					<li class="disabled"><a href="#" aria-label="Previous"> <spanaria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>
				<c:if test="${pageBean.pageNo != 1}">
					<li><a
						href="${pageContext.request.contextPath}/banji?method=pageList&pageNo=${pageBean.pageNo - 1}&pageSize=10"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<!--上一页结束  -->
				<!--中间页码开始 -->
				<c:forEach var="i" begin="1" end="${pageBean.totalPage}" step="1">
					<c:if test="${pageBean.pageNo == i}">
						<li class="active"><a href="">${i}</a></li>
					</c:if>
					<c:if test="${pageBean.pageNo != i}">
						<li><a
							href="${pageContext.request.contextPath}/banji?method=pageList&pageNo=${i}&pageSize=10">${i}</a></li>
					</c:if>
				</c:forEach>
				<!--中间页码结束  -->
				<!--下一页开始  -->
				<c:if test="${pageBean.pageNo == pageBean.totalPage}">
					<li class="disabled"><a href="#" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
				<c:if test="${pageBean.pageNo != pageBean.totalPage}">
					<li><a
						href="${pageContext.request.contextPath}/banji?method=pageList&pageNo=${pageBean.pageNo + 1}&pageSize=10"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
				<!--下一页结束  -->
			</ul>
		</nav>
	</div>
	<!--选择页结束 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/lib/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/lib/layer/layer.js"
		type="text/javascript" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/js/mylayer.js"
		type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
			/* function deleteById(id,pageNo) {
				//用户点了确定，confirm返回的是true，
				//用户点了取消，confirm返回的是false，
				var isDel = confirm("您确认要删除这个信息吗？");
				if (isDel) {
					location.href = "${pageContext.request.contextPath}/banji?method=deleteById&id="+ id+"&pageNo="+pageNo;
				}
			} */
			function deleteById(id,pageNo){
				mylayer.confirm("你确定要删除吗？","${pageContext.request.contextPath}/banji?method=deleteById&id="+ id+"&pageNo="+pageNo);
			}
			
			function selectAll(){
				//得到上面全选、反选按钮的状态
				var isChecked = $("#selectAlls").prop("checked");
				//下面所有checkbox的状态和上面全选、反选checkbox的状态一致
				$("input[name=selectIds]").prop("checked", isChecked);
			}
			function deleteAll() {
				var isDel = confirm("您确认要删除这些信息吗？");
				if(isDel){
				$("#mainForm").attr("action", "${pageContext.request.contextPath}/banji?method=deleteAll");
				//用代码方式提交表单
				$("#mainForm").submit();
				}
			}
		</script>
</body>
</html>
