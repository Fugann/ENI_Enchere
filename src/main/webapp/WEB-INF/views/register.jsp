<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>

<p>Mon profil</p>
<form method="post" action="">
	<div>
	<label for="pseudo">Pseudo : </label>
	<input type="text" name="pseudo" id="pseudo" />
	<br />
	<label for="prenom">Prénom : </label>
	<input type="text" name="prenom" id="prenom" />
	<br />
	<label for="tel">Téléphone : </label>
	<input type="tel" name="tel" id="tel" />
	<br />
	<label for="CP">Code Postal : </label>
	<input type="text" name="CP" id="CP" />
	<br />
	<label for="psw">Mot de passe : </label>
	<input type="password" name="psw" id="psw" />
	</div>
	<div>
		<label for="nom">Nom : </label>
	<input type="text" name="nom" id="nom" />
	<br />
	<label for="email">Email : </label>
	<input type="email" name="email" id="email" />
	<br />
	<label for="rue">Rue : </label>
	<input type="text" name="rue" id="rue" />
	<br />
	<label for="ville">Ville : </label>
	<input type="text" name="ville" id="ville" />
	<br />
	<label for="pswconfirm">Confirmation : </label>
	<input type="password" name="pswconfirm" id="pswconfirm" />
	</div>
	
	<input type="submit" value="Créer" />
	<a href="<%=request.getContextPath()%>">Annuler</a>
</form>
  
</body>
</html>