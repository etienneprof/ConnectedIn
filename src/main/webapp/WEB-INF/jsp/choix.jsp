<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@ include file="fragments/entete.jspf" %>

	<form action="choix" method="get">
		<input type="text" name="filtre">
		<input type="submit" value="Rechercher">
	</form>

	<div>
		<c:forEach var="contact" items="${contacts }">
			<div class="card">
				<p>${contact.nom } ${contact.prenom }</p>
				<p>${contact.poste }</p>
				<div>
					<form action="contacts" method="get">
						<input type="hidden" name="id" value="${contact.id }">
						<input type="submit" value="Consulter">
					</form>
					<form action="supprimer" method="post">
						<input type="hidden" name="id" value="${contact.id }">
						<input type="submit" value="Supprimer">
					</form>
				</div>
			</div>
		</c:forEach>
	</div>
	
	
	<a href="ajouter"><button>Ajouter un contact</button></a>
	<br>
	<a href="configuration">Page de configuration</a>
</body>
</html>