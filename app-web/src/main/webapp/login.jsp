<html>
<body>
	<h2>Login</h2>
	
	<form action="<%=request.getContextPath()%>/login">
		
		Username: <input type="text" name="username">

		Password: <input type="text" name="password">
				
		<input type="submit" value="Login">
	</form>
</body>
</html>
