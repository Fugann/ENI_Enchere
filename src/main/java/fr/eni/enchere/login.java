package fr.eni.enchere;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        rd.forward(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");

        String userEmail = request.getParameter("identifiant");
        String password = request.getParameter("psw");
        
        Utilisateur requestUser = new Utilisateur(userEmail, password);

        // Replace "UserManager" with your actual user management class
        UtilisateurManager userManager = new UtilisateurManager();

        // Retrieve user details from the database
        Utilisateur userObject = userManager.getUserDetails(userEmail);

        if (userObject != null) {
            //String hashedPasswordInput = Utilisateur.hashPwd(password);
            // Compare the hashed input password with the hashed password stored in the database
        	if(requestUser.getMot_de_passe().equals(userObject.getMot_de_passe())) {
        		HttpSession session = request.getSession();
        		
        		//rd = request.getRequestDispatcher("/WEB-INF/views/encheres.jsp");
        		
        		response.sendRedirect(request.getContextPath());  
            }
        	
        }else {
        	request.setAttribute("error", "Email/pseudo incorrect  !");
        	rd.forward(request, response);
        }
       
    }

    // Logout method to invalidate the session
    protected void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // Redirect to the login page after logout
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
