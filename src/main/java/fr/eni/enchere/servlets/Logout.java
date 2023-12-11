package fr.eni.enchere.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.UtilisateurAuthToken;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getSession().removeAttribute("user");
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			String selector = "";
			
			for (Cookie aCookie : cookies) {
	            if (aCookie.getName().equals("selector")) {
	                selector = aCookie.getValue();
	            }
	        }
			
			if (!selector.isEmpty()) {
	            // delete token from database
				UtilisateurManager um = new UtilisateurManager();
				UtilisateurAuthToken token = um.findBySelector(selector);
	             
	            if (token != null) {
	                um.deleteAuth(token.getId());
	                 
	                Cookie cookieSelector = new Cookie("selector", "");
	                cookieSelector.setMaxAge(0);
	                 
	                Cookie cookieValidator = new Cookie("validator", "");
	                cookieValidator.setMaxAge(0);
	                response.addCookie(cookieSelector);
	                response.addCookie(cookieValidator);                   
	            }
	        }
			
		}

		// Redirect vers la page d'accueil
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
