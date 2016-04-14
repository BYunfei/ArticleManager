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
<title>添加文章</title>
    <script type="text/javascript" charset="utf-8" src="sources/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="sources/ueditor/ueditor.all.min.js"> </script>
</head>
<body>
	<h1>${sessionScope.username },你好</h1>
	<jsp:include page="/WEB-INF/template/user_nav.jsp" />
	<hr>
	<form action="Article_add.action" method="post" id="articleForm">
		<h2>
			标题：<input type="text" name="article_title">
		</h2>
		<article>
			<h4>内容</h4>
			<script type="text/plain" id="editor" style="width: 800px;height:240px" ></script>
		</article>
		<input type="hidden" name="author_name" value="<%=session.getAttribute("username") %>" >
		<input type="button" value="添加" onclick="submitForm()">
	</form>
	<script>
	window.UEDITOR_HOME_URL = "/ArticlesManager/sources/ueditor/";
	var editor = UE.getEditor('editor');

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