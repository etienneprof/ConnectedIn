<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@ include file="fragments/entete.jspf" %>
	
	<form action="inscription" method="post">
		<div>
			<label for="login">Identifiant</label>
			<input type="text" name="login" id="login">
		</div>
		<div>
			<label for="password">Mot de passe</label>		
			<input type="password" name="password" id="password">
		</div>
		<input type="submit" value="Inscription">
	</form>
</body>
</html>