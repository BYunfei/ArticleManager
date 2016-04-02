<%@page import="cn.iwalkers.dao.ArticleDao"%>
<%@page import="cn.iwalkers.entity.Article"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有文章</title>
</head>
<body>
	<nav>
		<a href="Article_catalog.action?target=homePage">首页</a> 
		<a href="Article_catalog.action?target=article_list">所有文章</a>
	</nav>
	<%
		List<Article> articleList = new ArticleDao().getAllArticles();
	%>
	<h1>所有文章</h1>
	<%
		for (Article article : articleList) {
	%>
	<div>
		<h3>
			<a
				href="Article_catalog.action?target=article_detial&article_id=<%=article.getId()%>"><%=article.getTitle()%></a>
		</h3>
		<p><%=article.getContent()%></p>
	</div>
	<%
		}
	%>
</body>
</html>