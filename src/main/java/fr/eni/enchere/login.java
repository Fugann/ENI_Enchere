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
/*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        String userEmail = request.getParameter("identifiant");
        
        // The user can login using also the pseudo !!!!! <---- 
        String password = request.getParameter("psw");

        // Replace "UserManager" with your actual user management class
        UtilisateurManager userManager = new UtilisateurManager();

        // Retrieve user details from the database
        Utilisateur userObject = userManager.getUserDetails(userEmail);
        
        //String cryptedPasswordInput = Utilisateur.hashPwd(password);
        //String cryptedPasswordStored = userObject.getMot_de_passe();
        

        //System.out.println("Input Password: " + password);
        //System.out.println("Crypted Password (Input): " + cryptedPasswordInput);
        //System.out.println("Crypted Password (Stored): " + userObject.getMot_de_passe());
        
        
        if (userObject != null && userObject.getMot_de_passe().equals(password)) {
            // Store user information in the session
            session.setAttribute("user", userEmail);
            // Redirect to the home page or any other page after successful login
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
            out.println("<font color=red>Password is wrong.</font>");
            rd.include(request, response);
        }

        out.close();
    }
    
    */
    
    
    
    
    
    
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        String userEmail = request.getParameter("identifiant");
        String password = request.getParameter("psw");

        // Replace "UserManager" with your actual user management class
        UtilisateurManager userManager = new UtilisateurManager();

        // Retrieve user details from the database
        Utilisateur userObject = userManager.getUserDetails(userEmail);

        if (userObject != null) {
            String hashedPasswordInput = Utilisateur.hashPwd(password);

            // Compare the hashed input password with the hashed password stored in the database
            if (userObject.getMot_de_passe().equals(hashedPasswordInput)) {
                // Store user information in the session
                session.setAttribute("user", userEmail);
                // Redirect to the home page or any other page after successful login
                response.sendRedirect(request.getContextPath() + "/");
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
                out.println("<font color=red>Password is wrong.</font>");
                rd.include(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
            out.println("<font color=red>User not found.</font>");
            rd.include(request, response);
        }

        out.close();
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
