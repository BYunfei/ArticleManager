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
	<h1>${sessionScope.username },你好</h1>
	<jsp:include page="/WEB-INF/template/user_nav.jsp" />
	<hr>
	<form action="Article_add.action" method="get">
		<h2>
			标题：<input type="text" name="article_title">
		</h2>
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