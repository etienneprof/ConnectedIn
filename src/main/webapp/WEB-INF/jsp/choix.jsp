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

	<table>
		<thead>
			<tr>
				<th>Contact</th>
				<th>Poste</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="contact" items="${contacts }">
			<tr>
				<td>${contact.nom } ${contact.prenom }</td>
				<td>${contact.poste }</td>
				<td>
					<form action="contacts" method="get">
						<input type="hidden" name="id" value="${contact.id }">
						<button type="submit">
							<img src="https://cdn-icons-png.flaticon.com/512/694/694985.png" alt="details">
						</button>
					</form>
					<form action="supprimer" method="post">
						<input type="hidden" name="id" value="${contact.id }">
						<button type="submit">
							<img src="https://cdn-icons-png.flaticon.com/512/484/484662.png" alt="supprimer">
						</button>
					</form>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	
	<a href="ajouter"><button class="ajouter">Ajouter un contact</button></a>
	<br>
	<a href="configuration">Page de configuration</a>
</body>
</html>