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
	
	<p>${message }</p>
	<form action="connexion" method="post">
		<div>
			<label for="login">Identifiant</label>
			<input type="text" name="login" id="login">
		</div>
		<div>
			<label for="password">Mot de passe</label>		
			<input type="password" name="password" id="password">
		</div>
		<input type="checkbox" name="souvenir" id="souvenir">
		<label for="souvenir">Se souvenir de moi</label>
		<br>
		<input type="submit" value="Connexion">
	</form>
	<a href="inscription"><button>S'inscrire</button></a>
</body>
</html>