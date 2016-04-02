<%@page import="cn.iwalkers.dao.ArticleDao"%>
<%@page import="cn.iwalkers.entity.Article"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iWalkers</title>
</head>
<body>
	Welcome!
	<hr>
	<h1>登陆</h1>
	<form action="login.action">
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
	</form>
	<h3><a href="Article_catalog.action?target=article_list">查看所有文章</a></h3>
</body>
</html>