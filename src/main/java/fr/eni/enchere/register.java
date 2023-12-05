package fr.eni.enchere;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class register
 */
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/register.jsp");

		String pseudo = request.getParameter("pseudo");
		String prenom = request.getParameter("prenom");
		String tel = request.getParameter("tel");
		String CP = request.getParameter("CP");
		String psw = request.getParameter("psw");
		String nom = request.getParameter("nom");
		String email = request.getParameter("email");
		String rue = request.getParameter("rue");
		String ville = request.getParameter("ville");
		String pswconfirm = request.getParameter("pswconfirm");
		int credit = 0;
		Byte admin = 0;

		if (psw == null || !psw.equals(pswconfirm)) {
			request.setAttribute("error", "Le mot de passe ne correspond pas !");
		} else if (pseudo == null || pseudo.equals("") || prenom == null || prenom.equals("") || CP == null
				|| CP.equals("") || psw == null || psw.equals("") || nom == null || nom.equals("") || email == null
				|| email.equals("") || rue == null || rue.equals("") || ville == null || ville.equals("")) {
			request.setAttribute("error", "Veuillez remplir tous les champs !");
		}else {
			UtilisateurManager u = new UtilisateurManager();
			Utilisateur user = u.ajouter(pseudo, nom, prenom, email, tel, rue, CP, ville, psw, credit, admin);
			request.setAttribute("success", "Utilisateur créé avec succès !");
			rd = request.getRequestDispatcher("/WEB-INF/views/encheres.jsp");
		}
		rd.forward(request, response);
	}
}
