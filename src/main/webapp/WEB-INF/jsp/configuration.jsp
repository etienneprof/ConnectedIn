<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Page de configuration</h2>
	<p>Vous avez accédé à cette page ${acces } fois.</p>
	<a href="choix">Retour à l'accueil</a>
	
	<form action="configuration" method="post">
		<label for="defaut">Poste par défaut : </label>
		<input type="text" name="defaut" id="defaut" value="${param.defaut == null ? cookie.defaut.value : param.defaut }">
		<input type="submit" value="Enregistrer">
	</form>
	<p>${message }</p>
</body>
</html>