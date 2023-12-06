<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle Vente</title>
</head>
<body>
	
	<!-- Vérification si il y a un attribute success ou error -->
	<c:if test="${not empty requestScope.error}">
    <p style="color: red;"><c:out value="${requestScope.error}" /></p>
	</c:if>
	<c:if test="${not empty requestScope.success}">
	    <p style="color: green;"><c:out value="${requestScope.success}" /></p>
	</c:if>
	
	<h1>Nouvelle Vente</h1>
	<img src="" alt="photoUploader" />
	<form method="post" action="">
		<div>
			<label for="article">Article : </label> <input type="text"
				name="article" id="article" value=""> <br /> <label
				for="description">Description : </label>
			<textarea name="description" id="description" placeholder="Description de l'article"></textarea>
			<br />
			<label for="categorie">Catégorie </label>
			<select name="categorie" id="categorie">
				<option value="" hidden="hidden">Choisissez une catégorie</option>
				<option value="2">valeur 2</option>
				<option value="3">valeur 3</option>
				<option value="4">valeur 4</option>
			</select> <br />
			<label for="image">Photo de l'article </label>
			<button style="display: block; width: 120px; height: 30px;"
				onclick="document.getElementById('image').click()">UPLOADER</button>
			<input type='file' name="image" id="image" style="display: none"> <br />
			<label for="prix">Mise à prix : </label> <input type="number"
				name="prix" id="prix" /> <br /> 
			<label for="debut">Début de l'enchère : </label> <input type="date" name="debut" id="debut" /> <br />
			<label for="fin">Fin de l'enchère : </label> <input type="date"
				name="fin" id="fin" /> <br />
			<fieldset>
				<legend>Retrait : </legend>
				<label for="rue">Rue</label> 
				<input type="text" name="rue" id="rue"
					value="" /> <br /> 
				<label for="CP">Code Postal :</label> 
				<input type="text" name="CP" id="CP" /> <br /> <label for="ville">Ville</label>
				<input type="text" name="ville" id="ville" /> <br />
			</fieldset>
			<input type="submit" value="Enregistrer" />
			<a href="<%=request.getContextPath()%>">Annuler</a>

		</div>
	</form>
</body>
</html>










