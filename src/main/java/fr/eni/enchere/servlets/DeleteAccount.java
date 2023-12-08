package fr.eni.enchere.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;


public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

        if (user.getNo_utilisateur() != null) {
            // Delete the user
            UtilisateurManager um = new UtilisateurManager();
            um.deleteUser(user.getNo_utilisateur());

            // End the session (log out)
            request.getSession().invalidate();
            
            request.getSession().setAttribute("successMessage", "Utilisateur suprime avec success !");

            // Redirect to the home page
            response.sendRedirect(request.getContextPath() + "");
        } else {
            // Handle the case where userId is not available
        	// Here I need to create a redirection, or a text to show the error if the id is not found
            response.sendRedirect("errorPage.jsp");
        }
    }
}
