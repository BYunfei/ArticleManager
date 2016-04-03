<%@page import="cn.iwalkers.dao.ArticleDao"%>
<%@page import="cn.iwalkers.entity.Article"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HomePage</title>
</head>
<body>
	<h1>Welcome!</h1>
	<jsp:include page="/WEB-INF/template/user_nav.jsp"></jsp:include>
	<hr>
	<%
		if(session.getAttribute("username") == null){
	%>
	<h1>登陆</h1>
	<form action="user_login.action">
	<table>
		<tr>
			<td>username:</td>
			<td><input type="text" name="username"></td>
		</tr>
		<tr>
			<td>password:</td>
			<td><input type="password" name="password"></td>
		</tr>
	</table>
	<input type="submit" value="登陆">
	<input type="submit" value="注册" onclick="register()">
	</form>
	<%
		}
	%>
</body>
<script type="text/javascript">
function login(){
	var targetForm = document.forms[0];
	targetForm.action = "user_login.action";
}

function register(){
	var targetForm = document.forms[0];
	targetForm.action = "user_regist.action";
}
</script>
</html>