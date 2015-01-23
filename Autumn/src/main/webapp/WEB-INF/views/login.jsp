<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h1>Please Log In to Your Account</h1>
<p>Please use the form below to log in to your account.</p>
<form action="spring_security_form_check" method="post">
	<label for="spring_security_form_username">Login</label><input
		id="spring_security_form_username"
		name="spring_security_form_username" size="20" maxlength="50"
		type="text" /> <br /> <input id="_remember_me" name="_remember_me"
		type="checkbox" value="true" /> <label for="_remember_me">Remember
		Me?</label> <br /> <label for="spring_security_form_password">Password</label>
	<input id="spring_security_form_password"
		name="spring_security_form_password" size="20" maxlength="50"
		type="password" /> <br /><p>诗句<span style="color:red">"${question}"</span><br/>的下一句是什么?</p> <label for="spring_security_form_answer">Answer</label>
	<input id="spring_security_form_answer"
		name="spring_security_form_answer" size="20" maxlength="50"
		type="text" /> <br /> <input type="hidden"
		id="spring_security_form_question"
		name="spring_security_form_question" value="${questionId}" /> <input
		type="submit" value="Login" />
</form>

