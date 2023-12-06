package fr.eni.enchere;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate(); // This will kill the session
	    }

	    // Create a new session and store the message
	    HttpSession newSession = request.getSession(true);
	    newSession.setAttribute("logoutMessage", "Logout successfully!");

	    // Redirect to the login page
	    response.sendRedirect(request.getContextPath() + "/login");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
