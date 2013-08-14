<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>addTest</title>
</head>
<body>
	<h2>添加用户</h2>
	<hr size="20" color="yellow">
	<form action="${pageContext.request.contextPath}/result.do"
		method="post">
		<table>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="name" value="${test.name}" />
				</td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input type="text" name="birthday" value="${test.birthday}" />
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><input type="submit" value="保存" />
				</td>
			</tr>
		</table>
	</form>
	<br>
	<a href="${pageContext.request.contextPath}/listresult.do">返回</a>
</body>
</html>