package fr.eni.enchere;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;


public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("no_utilisateur"); // replace "userId" with the actual parameter name
        UtilisateurManager um = new UtilisateurManager();
        Utilisateur user = um.getUserById(userId);

        // Set the user data as a request attribute
        request.setAttribute("user", user);

        // Forward the request to the profile JSP
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/profile.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
