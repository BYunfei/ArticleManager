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
		int pageNow = 1; //当前页码
		int pageCount = 1; //总页数
		int pageSize = 6; //每页数量
		if (request.getParameter("pageNow") != null && !"".equals(request.getParameter("pageNow")))
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		if (request.getParameter("pageSize") != null && !"".equals(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		List<Article> articleList = null ;
		if(u!=null){
			pageCount = new ArticleDao().getArticlePageCount(u.getId() ,pageSize);
			articleList = new ArticleDao().getArticlesPage(u.getId(),(pageNow - 1) * pageSize, pageSize);
		}
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
				href="Article_catalog.action?target=user_article_detial&article_id=<%=article.getId()%>">标题：<%=article.getTitle()%></a>
		</h3>
		<p><%=article.getContent()%></p>
	</div>
	<%
		}

		if (pageNow > 1) {
	%>
	<a
		href="Article_catalog.action?target=user_article_list&pageNow=<%=pageNow - 1%>">上一页</a>
	<%
		}
	%>
	<i>第<%=pageNow%>页
	</i>
	<%
		if (pageNow < pageCount) {
	%>
	<a
		href="Article_catalog.action?target=user_article_list&pageNow=<%=pageNow + 1%>">下一页</a>
	<%
		}
	%>
</body>
</html>