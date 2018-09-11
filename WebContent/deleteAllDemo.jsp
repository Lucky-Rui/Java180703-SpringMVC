<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/studentDemo/deleteAll.action">
    <table border="1">
        <tr>
           <th>编号</th>
           <th>姓名</th>
        </tr>
        <tr>
           <td><input type="checkbox" name="ids" value="1"/></td>
           <td>张三</td>
        </tr>
        <tr>
           <td><input type="checkbox" name="ids" value="2"/></td>
           <td>李四</td>
        </tr>
        <tr>
           <td><input type="checkbox" name="ids" value="3"/></td>
           <td>王五</td>
        </tr>
        <tr>
           <td><input type="checkbox" name="ids" value="4"/></td>
           <td>赵六</td>
        </tr>
        <tr>
           <td colspan="2"><input type="submit" value="提交"/></td>
        </tr>
    </table>
</form>
</body>
</html>