<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
	<h1>Change Password</h1>
	<form method="post">
		<label for="oldpassword">Old Password</label>: <input id="oldpassword"
			name="oldpassword" size="20" maxlength="50" type="password" /> <br />
		<label for="newpassword">New Password</label>: <input id="newpassword"
			name="newpassword" size="20" maxlength="50" type="password" /> <br />
		<input type="submit" value="Change Password" />
	</form>
</body>
</html>