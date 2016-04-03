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
	<%
		Article article = new ArticleDao().getArticleById(Integer.parseInt(request.getParameter("article_id")));
		User author = article.getAuthor();
	%>
	<jsp:include page="/WEB-INF/template/user_nav.jsp" />
	<form action="Article_update.action" method="get">
		<h1>
			标题：<input type="text" name="article_title"
				value="<%=article.getTitle()%>">
		</h1>
		<em>作者：<%=author.getUsername()%></em>
		<hr>
		<article>
			<h4>内容</h4>
			<textarea rows="30" cols="100" name="article_content">
				<%=article.getContent()%>
			</textarea>
		</article>
		<input type="hidden" name="article_id" value="<%=article.getId()%>">
		<input type="submit" value="保存">
	</form>
</body>
</html>