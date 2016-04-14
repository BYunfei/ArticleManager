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
    <script type="text/javascript" charset="utf-8" src="sources/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="sources/ueditor/ueditor.all.min.js"> </script>
    
</head>
<body>
	<%
		Article article = new ArticleDao().getArticleById(Integer.parseInt(request.getParameter("article_id")));
		User author = article.getAuthor();
	%>
	<jsp:include page="/WEB-INF/template/user_nav.jsp" />
	<form action="Article_update.action" method="post" id="articleForm">
		<h1>
			标题：<input type="text" name="article_title"
				value="<%=article.getTitle()%>">
		</h1>
		<em>作者：<%=author.getUsername()%></em>
		<hr>
		<article>
			<h4>内容</h4>
			<script type="text/plain" id="editor" style="width: 800px;height:240px" ></script>
		</article>
		<input type="hidden" name="article_id" value="<%=article.getId()%>">
		<input type="button" value="保存" onclick="submitForm()">
	</form>
	<script>
	window.UEDITOR_HOME_URL = "/ArticlesManager/sources/ueditor/";
	var editor = UE.getEditor('editor');
	editor.ready(function() {
	    editor.setContent('<%=article.getContent() %>');
	});

	    function submitForm(){
			var articleForm = document.getElementById('articleForm');
			
			var article_content = document.createElement('input');
			article_content.name = 'article_content';
			article_content.value = editor.getContent();
			
			var article_summary = document.createElement('input');
			article_summary.name = 'article_summary';
			article_summary.value = editor.getContentTxt().substring(0,30);
			
			articleForm.appendChild(article_content);
			articleForm.appendChild(article_summary);
			
			articleForm.submit();
		}
	
	</script>
</body>
</html>