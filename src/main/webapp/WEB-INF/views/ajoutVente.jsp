<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle Vente</title>
</head>
<body>
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
			<select
				name="categorie" id="categorie">
				<option value="" hidden="hidden">Choisissez une catégorie</option>
				<option value="valeur2">valeur 2</option>
				<option value="valeur3">valeur 3</option>
				<option value="valeur4">valeur 4</option>
			</select> <br />
			<label for="photo">Photo de l'article </label>
			<button style="display: block; width: 120px; height: 30px;"
				onclick="document.getElementById('photo').click()">UPLOADER</button>
			<input type='file' name="photo" id="photo" style="display: none"> <br />
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










