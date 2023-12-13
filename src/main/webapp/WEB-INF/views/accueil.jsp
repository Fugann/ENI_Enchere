<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/normalize.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.css">
<script src="https://cdn.tailwindcss.com"></script>
<title>Accueil</title>
</head>
<body>

	<nav>
		<a href="<%=request.getContextPath()%>" class="logo">ENI-Encheres</a>
		<div>
			<a href="<%=request.getContextPath()%>/Login">Login/Register</a>
		</div>
	</nav>
	
	<main class="accueil">
		<h2>Liste des enchère</h2>
		<form method="post" action="">
			<div>
				<label for="search">Filtres :</label>
				<div class="search">
					<input type="text" name="search" id="search" placeholder="Que rechercher vous ?" />
				</div>
				<div class="categories">
					<label for="categorie">Catégorie :</label>
					<select name="categorie" id="categorie">
						<option value="">Choisissez une catégorie</option>
						<c:forEach items="${categories}" var="categorie">
							<option value="${categorie.getNo_categorie()}"><c:out value="${categorie.getLibelle()}"/></option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div>
				<input type="submit" value="Rechercher" />
			</div>
		</form>
		<div class="container">
			
			<c:forEach items="${articles}" var="article">
				<div>
					<img src="https://cdn.cultura.com/cdn-cgi/image/width=450/media/pim/13_246980_17_110_FR.jpg" alt="Photo introuvable" />
				</div>
				<div>
					<strong><c:out value="${article.getNom_article()}"/></strong>
					<p>Prix : 
					<c:choose>
						<c:when test="${article.getPrix_vente() != '0'}">
							<c:out value="${article.getPrix_vente()}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${article.getPrix_initial()}"/> (Prix initial)
						</c:otherwise>
					</c:choose>
					 Points</p>
					<p>Fin de l'enchère : <c:out value="${article.getDate_fin_encheres().format(DateTimeFormatter.ofPattern(\"dd MMMM yyyy\"))}"/></p>
					<p>Vendeur : 
					<c:forEach items="${users}" var="user">
						<c:if test="${article.getNo_utilisateur() == user.getNo_utilisateur()}">
							<c:out value="${user.getPseudo()}"/>
						</c:if>
					</c:forEach>
					</p>
				</div>
			</c:forEach>
		
			<!-- Contenu de toute la recherche -->
			<c:if test="${articles.size() == '0'}"><p>Rien</p></c:if>
		</div>
	</main>


</body>
</html>
