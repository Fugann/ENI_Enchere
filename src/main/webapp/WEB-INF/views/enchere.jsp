<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="fr.eni.enchere.error.LecteurMessage"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Enchères</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/styles/normalize.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/styles/style.css">
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

	<nav>
		<a href="<%=request.getContextPath()%>" class="logo">ENI-Encheres</a>
		<div>
			<a href="<%=request.getContextPath()%>">Mes Enchères</a> <a
				href="<%=request.getContextPath()%>/ajoutArticle">Vendre un
				article</a> <a href="<%=request.getContextPath()%>/Profile">Mon
				Profil</a> <a href="<%=request.getContextPath()%>/Logout">Déconnexion</a>
		</div>
	</nav>

	<main class="enchere">
	
		<c:if test="${success != null }">
			<p>L'enchère a bien était crée</p>
		</c:if>
		
		<c:if test="${codesError != null }">
			<p>Erreur : L'enchere n'a pas pu être ajouté : </p>
			<c:forEach items="${codesError}" var="error">
				<p>${LecteurMessage.getMessageErreur(error)}</p>
			</c:forEach>
		</c:if>

		<h1>Détail vente</h1>
		<img
			src="https://cdn.cultura.com/cdn-cgi/image/width=450/media/pim/13_246980_17_110_FR.jpg"
			alt="img" />
		<div>
			<p><c:out value="${article.getNom_article()}"/></p>
			<p>Description : <c:out value="${article.getDescription()}"/></p>
			<p>Catégorie : <c:out value="${categorie.getLibelle()}"/></p>
			<c:choose>
				<c:when test="${enchere.getMontant_enchere() != '0' && enchere != null}">
					<p>Prix initial : <c:out value="${article.getPrix_initial()}"/></p>
					<p>Meilleure offre : <c:out value="${enchere.getMontant_enchere()}"/></p>
				</c:when>
				<c:otherwise>
					<p>Prix initial : <c:out value="${article.getPrix_initial()}"/></p>
				</c:otherwise>
			</c:choose>
			<p>Début de l'enchère le : <c:out value="${article.getDate_debut_encheres().format(DateTimeFormatter.ofPattern(\"dd MMMM yyyy HH:mm\")) }"/></p>
			<p>Fin de l'enchère le : <c:out value="${article.getDate_fin_encheres().format(DateTimeFormatter.ofPattern(\"dd MMMM yyyy HH:mm\")) }"/></p>
			<p>Il reste <strong id="timer"><c:out value="${duration }"/></strong> avant la fin de l'enchère</p>
			<p>Retrait : <c:out value="${user.getRue()}"/>, <c:out value="${user.getCode_postal()}"/> <c:out value="${user.getVille()}"/></p>
			<p>Vendeur : <c:out value="${user.getPseudo()}"/></p>

			<form method="post" action="">
				<label for="montant">Faire une enchère : </label>
				<input type="number" name="montant" id="montant" />
				<input type="submit" value="Enchérir" />
			</form>
		</div>

	</main>
</body>
</html>