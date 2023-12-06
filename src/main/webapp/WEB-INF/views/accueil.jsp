<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Accueil</title>
</head>
<body>

	<a href="<%=request.getContextPath()%>/login">Login/register</a>
	
	<main>
		<div>
			<form method="post" action="">
				<input type="text" name="search" id="search" placeholder="Que rechercher vous ?" />
				<br />
				<label for="categorie">Catégorie :</label>
				<select name="categorie" id="categorie">
					<option value="">Choisissez une catégorie</option>
					<c:forEach items="${categories}" var="categorie">
						<option value="${categorie.getNo_categorie()}"><c:out value="${categorie.getLibelle()}"/></option>
					</c:forEach>
				</select>
				<input type="submit" value="Rechercher" />
			</form>
		</div>
		<div class="container">
			<!-- Contenu de toute la recherche -->
			<p>Rien</p>
		</div>
	</main>


</body>
</html>
