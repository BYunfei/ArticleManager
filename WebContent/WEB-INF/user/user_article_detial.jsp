<%@page import="cn.iwalkers.entity.User"%>
<%@page import="cn.iwalkers.dao.UserDao"%>
<%@page import="cn.iwalkers.entity.Article"%>
<%@page import="cn.iwalkers.dao.ArticleDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<%
		Article article = new ArticleDao().getArticleById(Integer.parseInt(request.getParameter("article_id")));
		User author = article.getAuthor();
	%>
	<jsp:include page="/WEB-INF/template/user_nav.jsp" />
	<h1><%=article.getTitle()%></h1>
	<em><%=author.getUsername()%></em>
	<hr>
	<article>
		<%=article.getContent()%>
	</article>

	<a href="Article_edit.action?article_id=<%=article.getId()%>">编辑</a>
	<a href="Article_delete.action?article_id=<%=article.getId()%>">删除</a>
</body>
</html>