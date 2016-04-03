<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
</head>
<body>
<h1>个人信息</h1>
<jsp:include page="/WEB-INF/template/user_nav.jsp" />
<hr>
<input type="button" onclick="show_update_password_block()" value="修改密码">
<div id="update_password_block" style="display:none;">
	<form action="user_updatePassword.action" method="post">
		<table>
			<tr>
				<td>旧密码：</td>
				<td>
					<input type="password" name="password">
				</td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td>
				<input type="password" name="new_password">
				</td>
			</tr>				
		</table>
		<input type="submit" value="确认修改">
	</form>
</div>
</body>
<script type="text/javascript">
	function show_update_password_block(){
		var block = document.getElementById("update_password_block");
		block.style.display="block";
	}
</script>
</html>