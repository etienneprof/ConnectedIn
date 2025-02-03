<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${action }" method="post">
		<input type="hidden" value="${contact.id }" name="id">
		<div>
			<label for="nom">Nom : </label>
			<input type="text" id="nom" name="nom" value="${contact.nom }">
		</div>
		<div>
			<label for="prenom">Prenom : </label>
			<input type="text" id="prenom" name="prenom" value="${contact.prenom }">
		</div>
		<div>
			<label for="ddn">Date de naissance : </label>
			<input type="date" id="ddn" name="ddn" value="${contact.dateNaissance }">
		</div>
		<div>
			<label for="telephone">Telephone : </label>
			<input type="tel" id="telephone" name="telephone" value="${contact.telephone }">
		</div>
		<div>
			<label for="url">Réseaux sociaux : </label>
			<input type="text" id="url" name="url" value="${contact.url }">
		</div>
		<div>
			<label for="poste">Poste : </label>
			<input type="text" id="poste" name="poste" value="${contact.poste }">
		</div>
		<div>
			<label for="spe">Spécialité : </label>
			<input type="text" id="spe" name="spe" value="${contact.specialite }">
		</div>
		
		<input type="submit" value="Enregistrer">
	</form>
</body>
</html>