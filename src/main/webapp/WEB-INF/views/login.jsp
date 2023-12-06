<%@ page language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>


<%
    HttpSession mySession = request.getSession(false);
    if (mySession != null) {
        String logoutMessage = (String) mySession.getAttribute("logoutMessage");
        if (logoutMessage != null && !logoutMessage.isEmpty()) {
%>
            <div class="alert alert-success" role="alert">
                <%= logoutMessage %>
            </div>
<%
            // Clear the message from the session
            mySession.removeAttribute("logoutMessage");
        }
    }
%>



	<c:if test="${not empty requestScope.error}">
    	<p style="color: red;"><c:out value="${requestScope.error}" /></p>
	</c:if>

	<form method="post" action="">
		<label for="identifiant">Identifiant : </label>
		<input type="text" name="identifiant" id="identifiant" placeholder="Email / Pseudo" />
		<br />
		<label for="psw">Mot de passe</label>
		<input type="password" name="psw" id="psw"/>
		<br />
		<input type="checkbox" name="remember" id="remember" />
		<label for="checkbox">Se souvenir de moi</label>
		<br />
		<input type="submit" value="Connexion" />
		<a href="">Mot de passe oublié</a>
	</form>
	
	<a href="<%=request.getContextPath()%>/register">Créer un compte</a>
  
</body>
</html>