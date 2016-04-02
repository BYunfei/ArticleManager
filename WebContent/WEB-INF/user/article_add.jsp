<%@page import="cn.iwalkers.dao.UserDao"%>
<%@page import="cn.iwalkers.entity.User"%>
<%@page import="cn.iwalkers.entity.Article"%>
<%@page import="cn.iwalkers.dao.ArticleDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑文章</title>
</head>
<body>
<nav>
	<a href="Article_catalog.action?target=userArticleList">文章列表</a>
	<a href="Article_catalog.action?target=addPage">添加文章</a>
</nav>
	<form action="Article_add.action" method="get">
		<h1>
			标题：<input type="text" name="article_title">
		</h1>
		<hr>
		<article>
			<h4>内容</h4>
			<textarea rows="30" cols="100" name="article_content">
			</textarea>
		</article>
		<input type="hidden" name="author_name" value="<%=session.getAttribute("username") %>" >
		<input type="submit" value="添加">
	</form>
</body>
</html>