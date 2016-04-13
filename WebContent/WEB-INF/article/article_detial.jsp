<%@page import="cn.iwalkers.entity.Comment"%>
<%@page import="java.util.List"%>
<%@page import="cn.iwalkers.dao.CommentDao"%>
<%@page import="cn.iwalkers.entity.User"%>
<%@page import="cn.iwalkers.entity.Article"%>
<%@page import="cn.iwalkers.dao.ArticleDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	Article article = new ArticleDao().get(Integer.parseInt(request.getParameter("article_id")));
	User author = article.getAuthor();
%>
<title><%=article.getTitle()%></title>
</head>
<body>
	<jsp:include page="/WEB-INF/template/user_nav.jsp" />

	<h1><%=article.getTitle()%></h1>
	<em><%=author.getUsername()%></em>
	<hr>
	<article>
		<%=article.getContent()%>
	</article>
	<%-- 编辑文章 --%>
	<%
		if(session.getAttribute("username")!=null&&author.getUsername().equals(session.getAttribute("username").toString())){
	%>
	<a href="Article_edit.action?article_id=<%=article.getId()%>">编辑</a>
	<a href="Article_delete.action?article_id=<%=article.getId()%>">删除</a>
	<% 
		}
	%>
	<%-- 评论内容 --%>
	<hr>
	<h2>评论</h2>
	<%
		List<Comment> commentList = new CommentDao().getRootComments(article.getId());
		int i = 0;//当前是第几条评论
		for(Comment c:commentList){
			
	%>
	<div id="comment">
		<div class="comment_item">
			<p><em><%=c.getAuthor().getUsername() %>&nbsp;评论&nbsp;<%=c.getCreateDate() %></em>
			<p>
				<%=c.getContent() %>
			</p>
			<p>---------------</p>
			<%
				List<Comment> replyList = new CommentDao().getChild(c.getId());
				for(Comment reply:replyList){
			%>
			<div>
				<p><em><%=reply.getAuthor().getUsername() %>回复:</em>&nbsp;<%=reply.getContent() %></p>
			</div>
			<%
				}
			%>
			<%-- 回复评论 --%>
			<div class="comment_reply">
				<a onclick="show_reply_box(<%=i++ %>)" style="font-size:12px;text-decoration: underline;">回复</a>
				<div class="reply_box" style="display: none;">
					<form action="comment_reply.action" method="post">
						<input type="text" name="comment_content"/>
						<input type="submit" value="回复">
						<input type="hidden" value="<%=c.getId() %>" name="father_id">
						<%=c.getId() %>
						<input type="hidden" name="article_id" value="<%=article.getId() %>">
						<input type="hidden" name="author_id" value="<%=author.getId() %>">
					</form>
				</div>
			</div>
			<p>---------------------------------------------</p>
		</div>
	</div>
	<%
		}
	%>
	<%-- 添加评论 --%>
	<%
		if(session.getAttribute("username")!=null){
	%>		
			<p>添加评论</p>
			<form action="comment_create.action">
				<textarea rows="20" cols="50" name="comment_content"></textarea><br>
				<input type="submit" value="评论">
				<input type="hidden" name="article_id" value="<%=article.getId() %>">
				<input type="hidden" name="author_id" value="<%=author.getId() %>">
			</form>
	<%
		}else{
	%>
			<p>评论，请先登录</p>
	<%
		}
	%>
	<form>
		
	</form>
	
	<script type="text/javascript">
		function show_reply_box(i){
			var reply_box = document.getElementsByClassName("reply_box");
			var target = reply_box[i];
			if(target.style.display=='block')
				target.style.display = 'none';
			else
				target.style.display = 'block';
		}
	</script>
</body>
</html>