<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import ="fr.eni.enchere.error.LecteurMessage" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/normalize.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.css">
<title>Ajouter un article</title>
</head>
<body>
	<c:if test="${article != null }">
	<p>L'article a été ajouté avec succès</p>
	</c:if>
	<c:if test="${codesError != null }">
	<p>Erreur : L'article n'a pas pu être ajouté : </p>
	<c:forEach items="${codesError}" var="error">
	<p>${LecteurMessage.getMessageErreur(error)}
	</p>
	</c:forEach>
	</c:if>
	<h1>Nouvelle Vente</h1>
	<img src="" alt="photoUploader" />
	<form method="post" action="">
		<div>
			<label for="article">Article : </label> 
			<input type="text" name="article" id="article" value="<%= request.getAttribute("error") !=null?request.getParameter("article"):""%>" /> 
			<br />
			<label
				for="description">Description : </label>
			<textarea name="description" id="description" placeholder="Description de l'article" ><%= request.getAttribute("error") !=null?request.getParameter("description"):""%></textarea>
			<br />
			<label for="categorie">Catégorie </label>
			<select name="categorie" id="categorie">
				<option value="" hidden="hidden">Choisissez une catégorie</option>
				<c:forEach items="${categories}" var="categorie">
					<option value="${categorie.getNo_categorie()}"><c:out value="${categorie.getLibelle()}"/></option>
				</c:forEach>
			</select> <br />
			<label for="image">Photo de l'article </label>
			<button style="display: block; width: 120px; height: 30px;"
				onclick="document.getElementById('image').click()">UPLOADER</button>
			<input type='file' name="image" id="image" style="display: none">
			<br />
			<label for="prix">Mise à prix : </label>
			<input type="number" name="prix" id="prix" value="<%= request.getAttribute("error") !=null?request.getParameter("prix"):""%>"/>
			<br /> 
			<label for="debut">Début de l'enchère : </label> 
			<input type="date" name="debut" id="debut" value="<%= request.getAttribute("error") !=null?request.getParameter("debut"):""%>"/>
			<br />
			<label for="fin">Fin de l'enchère : </label> 
			<input type="date" name="fin" id="fin" value="<%= request.getAttribute("error") !=null?request.getParameter("fin"):""%>" />
			<br />
			<fieldset>
				<legend>Retrait : </legend>
				<label for="rue">Rue</label> 
				<input type="text" name="rue" id="rue" value="<c:out value="${user.rue }"/>" />
				<br /> 
				<label for="CP">Code Postal :</label> 
				<input type="text" name="CP" id="CP" value="<c:out value="${user.code_postal }"/>"/> 
				<br />
				<label for="ville">Ville</label>
				<input type="text" name="ville" id="ville" value="<c:out value="${user.ville }"/>"/>
				<br />
			</fieldset>
			<input type="submit" value="Enregistrer" />
			<a href="<%=request.getContextPath()%>">Annuler</a>

		</div>
	</form>
</body>
</html>










