<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Please Log In to Your Account</h1>
<p>Please use the form below to log in to your account.</p>
<form action="spring_security_form_check" method="post">
	<label for="spring_security_form_username">Login</label><input
		id="spring_security_form_username"
		name="spring_security_form_username" size="20" maxlength="50"
		type="text" /> <br /> <label for="spring_security_form_password">Password</label>
	<input id="spring_security_form_password"
		name="spring_security_form_password" size="20" maxlength="50"
		type="password" /> <br /> <label for="spring_security_form_captcha">Captcha</label><input
		id="spring_security_form_captcha" name="spring_security_form_captcha"
		size="20" maxlength="50" type="text" /> <img id="captchaImg"
		src="<c:url value="/captcha"/>" style="vertical-align: middle;"
		height="30" width="80" /><br />
	<input id="spring_security_form_rememberme"
		name="spring_security_form_rememberme" type="checkbox" value="true" />
	<label for="spring_security_form_rememberme">Remember Me?</label> <br />
	<input type="submit" value="Login" />
</form>

