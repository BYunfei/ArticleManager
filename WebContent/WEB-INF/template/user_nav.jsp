<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head></head>
<body>
	<nav>
		<a href="Article_catalog.action?target=homePage">首页</a> <a
			href="Article_catalog.action?target=article_list">所有人的文章</a>
		<%
			if (session.getAttribute("username") != null) {
		%>
		<a href="user_catalog.action?target=user_article_list">我的文章</a> <a
			href="user_catalog.action?target=add_article">添加文章</a> <a
			href="user_catalog.action?target=user_info">个人信息</a> <a
			href="user_logout.action">注销</a>
		<%
			} else {
		%>
		<a href="#" onclick="show_login_box()">登陆/注册</a>
		<%
			}
		%>
	</nav>

	<div id="login_box" style="display: none;">
		<form action="user_login.action">
			<table>
				<tr>
					<td>username:</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>password:</td>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
			<input type="submit" value="登陆"> <input type="submit"
				value="注册" onclick="register()">
		</form>
	</div>

	<script>
		function show_login_box(){
			var login_box = document.getElementById("login_box");
			if(login_box.style.display == "block")
				login_box.style.display = "none";
			else
				login_box.style.display = "block";
		}
	
		function login() {
			var targetForm = document.forms[0];
			targetForm.action = "user_login.action";
		}

		function register() {
			var targetForm = document.forms[0];
			targetForm.action = "user_regist.action";
		}
	</script>
</body>
</html>
