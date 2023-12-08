<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.eni.enchere.error.LecteurMessage"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/styles/normalize.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/styles/style.css">
<title>Document</title>
</head>
<body>

	<c:if test="${codesError != null }">
		<c:forEach items="${codesError}" var="error">
			<p>${LecteurMessage.getMessageErreur(error)}</p>
		</c:forEach>
	</c:if>

	<form method="post" action="">
		<label for="identifiant">Identifiant : </label>
		<input type="text" name="identifiant" id="identifiant" placeholder="Email / Pseudo" />
		<br />
		
		<label for="psw">Mot de passe</label>
		<input type="password" name="psw" id="psw" placeholder="Password" />
		<br />
		
		<input type="checkbox" name="remember" id="remember" value="true" />
		<label for="checkbox">Se souvenir de moi</label>
		<br />
		
		<input type="submit" value="Connexion" />
		<a href="">Mot de passe oublié</a>
	</form>

	<a href="<%=request.getContextPath()%>/register">Créer un compte</a>

</body>
</html>