<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav>
	<a href="Article_catalog.action?target=homePage">首页</a>
	<a href="Article_catalog.action?target=article_list">所有人的文章</a>
	<%
		if (session.getAttribute("username") != null) {
	%>
	<a href="Article_catalog.action?target=user_article_list">我的文章</a>
	<a href="Article_catalog.action?target=addPage">添加文章</a>
	<a href="user_logout.action">注销</a>
	<%
		}
	%>
</nav>