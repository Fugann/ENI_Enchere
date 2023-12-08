package fr.eni.enchere.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.error.BusinessException;

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");

		String identifiant = request.getParameter("identifiant");
		String password = request.getParameter("psw");
		boolean remember = Boolean.parseBoolean(request.getParameter("remember"));

		// Replace "UserManager" with your actual user management class
		UtilisateurManager userManager = new UtilisateurManager();

		try {
			Utilisateur user = userManager.getUserDetails(identifiant, password);
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(3600);

			// add user object dans la session
			session.setAttribute("user", user);

			// Create a cookie with the user's ID
			Cookie userIdCookie = new Cookie("no_utilisateur", String.valueOf(user.getNo_utilisateur()));
			userIdCookie.setMaxAge(3600);
			response.addCookie(userIdCookie);

			// Redirect to the home page
			response.sendRedirect(request.getContextPath());
		} catch (BusinessException e) {
			request.setAttribute("codesError", e.getListeCodesErreur());
			rd.forward(request, response);
		}

	}
}
