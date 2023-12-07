<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>S'enregistrer</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/normalize.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.css">
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<p>Mon profil</p>

<c:if test="${not empty requestScope.error}">
    <p style="color: red;"><c:out value="${requestScope.error}" /></p>
</c:if>

<c:if test="${not empty requestScope.success}">
    <p style="color: green;"><c:out value="${requestScope.success}" /></p>
</c:if>

<form method="post" action="">
	<div>
	<label for="pseudo">Pseudo : </label>
	<input type="text" name="pseudo" id="pseudo" value="<%= request.getAttribute("error") !=null?request.getParameter("pseudo"):""%>"/>
	<br />
	<label for="prenom">Prénom : </label>
	<input type="text" name="prenom" id="prenom" value="<%= request.getAttribute("error") !=null?request.getParameter("prenom"):""%>"/>
	<br />
	<label for="tel">Téléphone : </label>
	<input type="tel" name="tel" id="tel" value="<%= request.getAttribute("error") !=null?request.getParameter("tel"):""%>"/>
	<br />
	<label for="CP">Code Postal : </label>
	<input type="text" name="CP" id="CP" value="<%= request.getAttribute("error") !=null?request.getParameter("CP"):""%>"/>
	<br />
	<label for="psw">Mot de passe : </label>
	<input type="password" name="psw" id="psw" />
	</div>
	<div>
		<label for="nom">Nom : </label>
	<input type="text" name="nom" id="nom" value="<%= request.getAttribute("error") !=null?request.getParameter("nom"):""%>"/>
	<br />
	<label for="email">Email : </label>
	<input type="email" name="email" id="email" value="<%= request.getAttribute("error") !=null?request.getParameter("email"):""%>"/>
	<br />
	<label for="rue">Rue : </label>
	<input type="text" name="rue" id="rue" value="<%= request.getAttribute("error") !=null?request.getParameter("rue"):""%>"/>
	<br />
	<label for="ville">Ville : </label>
	<input type="text" name="ville" id="ville" value="<%= request.getAttribute("error") !=null?request.getParameter("ville"):""%>"/>
	<br />
	<label for="pswconfirm">Confirmation : </label>
	<input type="password" name="pswconfirm" id="pswconfirm" />
	</div>
	
	<input type="submit" value="Créer" />
	<a href="<%=request.getContextPath()%>">Annuler</a>
</form>

</body>
</html>
