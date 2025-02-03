<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
		<p>Id : ${contact.id }</p>
		<p>Nom : ${contact.nom }</p>
		<p>Prenom : ${contact.prenom }</p>
		<p>Date de naissance : ${contact.dateNaissance }</p>
		<p>Réseaux : ${contact.url }</p>
		<p>Telephone : ${contact.telephone }</p>
		<p>Poste : ${contact.poste }</p>
		<p>Spécialité : ${contact.specialite }</p>
	</fieldset>
	<a href="choix">Retour</a>
	
	<form action="modifier" method="get">
		<input type="hidden" name="id" value="${contact.id }">
		<input type="submit" value="Modifier">
	</form>
	
	<form action="supprimer" method="post">
		<input type="hidden" name="id" value="${contact.id }">
		<input type="submit" value="Supprimer">
	</form>
	
</body>
</html>