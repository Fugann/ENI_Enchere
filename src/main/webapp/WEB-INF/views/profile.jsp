<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/normalize.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/editProfile.css">
<script src="https://cdn.tailwindcss.com"></script>
<title>Insert title here</title>
</head>
<body>

	<nav>
		<a href="<%=request.getContextPath()%>" class="logo">ENI-Encheres</a>
		<div>
			<c:choose>
				<c:when test="${user != null}">
					<a href="<%=request.getContextPath()%>">Enchères</a>
					<a href="<%=request.getContextPath()%>/ajoutArticle">Vendre un article</a>
					<a href="<%=request.getContextPath()%>/Profile">Mon Profil</a>
					<a href="<%=request.getContextPath()%>/Logout">Déconnexion</a>
				</c:when>
				<c:otherwise>
					<a href="<%=request.getContextPath()%>/Login">Login/Register</a>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>



	<h2>Here is your profile MTFKER :)</h2>
	<p>userId: ${user.no_utilisateur}</p>
    <p>Pseudo: ${user.pseudo}</p>
    <p>Email: ${user.email}</p>
    
    
<h1>Edit Profile</h1>
<form action="EditProfile" method="post" class="edit-profile-form">
	<div class="main-div-edit-form">
	    <div class="left-column">
	        <label for="pseudo">User Pseudo:</label>
	        <input type="text" id="pseudo" name="pseudo" value="${user.pseudo}">
	
	        <label for="prenom">Prenom:</label>
	        <input type="text" id="prenom" name="prenom" value="${user.prenom}">
	
	        <label for="telephone">Telephone:</label>
	        <input type="tel" id="telephone" name="telephone" value="${user.telephone}">
	
	        <label for="code_postal">Code Postal:</label>
	        <input type="text" id="code_postal" name="code_postal" value="${user.code_postal}">
	
	        <label for="mot_de_passe">Mot de Passe:</label>
	        <input type="password" id="mot_de_passe" name="mot_de_passe">
	    </div>
	
	    <div class="right-column">
	        <label for="nom">Nom:</label>
	        <input type="text" id="nom" name="nom" value="${user.nom}">
	
	        <label for="email">Email:</label>
	        <input type="email" id="email" name="email" value="${user.email}">
	
	        <label for="rue">Rue:</label>
	        <input type="text" id="rue" name="rue" value="${user.rue}">
	
	        <label for="ville">Ville:</label>
	        <input type="text" id="ville" name="ville" value="${user.ville}">
	        
	       
	    </div>
    
	    <div>
	    	<div> <input type="submit" value="Enregistrer"></div>
	    </div>
   </div>
</form>


<div>
    <form action="DeleteAccount" method="post" class="delete-account-form">
        <!-- You can add a confirmation message here if needed -->
        <input type="hidden" name="deleteConfirmation" value="true">
        <input type="submit" value="Supprimer mon compte">
    </form>
</div>



</body>
</html>