<%@page import="cn.iwalkers.entity.Article"%>
<%@page import="cn.iwalkers.dao.UserDao"%>
<%@page import="cn.iwalkers.entity.User"%>
<%@page import="cn.iwalkers.dao.ArticleDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章列表</title>
</head>
<body>
	<%
		User u = new UserDao().getUserByUsername(session.getAttribute("username").toString());
		List<Article> articleList = new ArticleDao().getArticleByUserId(u.getId());
	%>

	<h1><%=u.getUsername()%>,你好
	</h1>
	<jsp:include page="/WEB-INF/template/user_nav.jsp" />
	<hr>
	<h2>我的文章</h2>
	<%
		for (Article article : articleList) {
	%>
	<div>
		<h3>
			<a
				href="Article_catalog.action?target=user_article_detial&article_id=<%=article.getId()%>"><%=article.getTitle()%></a>
		</h3>
		<p><%=article.getContent()%></p>
	</div>
	<%
		}
	%>
</body>
</html>