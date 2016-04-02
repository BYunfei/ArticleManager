<%@page import="cn.iwalkers.entity.Article"%>
<%@page import="cn.iwalkers.dao.UserDao"%>
<%@page import="cn.iwalkers.entity.User"%>
<%@page import="cn.iwalkers.dao.ArticleDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	User u = new UserDao().getUserByUsername(session.getAttribute("username").toString());
	List<Article> articleList = new ArticleDao().getArticleByUserId(u.getId());
%>

<h1><%=request.getAttribute("username") %>,你好</h1>
<hr>
<h2>文章列表</h2>
<%
	for(Article article : articleList){
		
%>
<div>
	<h3><a href="Article_detial.action?article_id=<%=article.getId() %>"><%=article.getTitle() %></a></h3>
	<p><%=article.getContent() %></p>
</div>
<%
	}
%>
</body>
</html>