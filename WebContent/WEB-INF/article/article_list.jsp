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
	<h1>Welcome!</h1>
	<jsp:include page="/WEB-INF/template/user_nav.jsp" />
	<hr>
	<h2>所有文章</h2>
	<%
		int pageNow = 1;	//当前页码
		int pageCount = 1;	//总页数
		int pageSize = 6;	//每页数量
		if (request.getParameter("pageNow") != null && !"".equals(request.getParameter("pageNow")))
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		if (request.getParameter("pageCount") != null && !"".equals(request.getParameter("pageCount")))
			pageCount = Integer.parseInt(request.getParameter("pageCount"));
		if (request.getParameter("pageSize") != null && !"".equals(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		pageCount = new ArticleDao().getArticlePageCount(pageSize);
		List<Article> articleList = new ArticleDao().getArticlesPage((pageNow-1)*pageSize, pageSize);
		for (Article article : articleList) {
	%>
	<div>
		<h3>
			<a
				href="Article_catalog.action?target=article_detial&article_id=<%=article.getId()%>">标题：<%=article.getTitle()%></a>
		</h3>
		<p><%=article.getSummary()%></p>
	</div>
	<%
		}

		if (pageNow > 1) {
	%>
	<a href="Article_catalog.action?target=article_list&pageNow=<%=pageNow - 1%>">上一页</a>
	<%
		}
	%>
	<i>第<%=pageNow %>页</i>
	<%
		if (pageNow < pageCount) {	
	%>
	<a href="Article_catalog.action?target=article_list&pageNow=<%=pageNow + 1%>">下一页</a>
	<%
		}
	%>
</body>
</html>