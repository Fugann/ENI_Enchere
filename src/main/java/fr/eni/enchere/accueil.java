package fr.eni.enchere;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Categorie;

/**
 * Servlet implementation class encheres
 */
public class accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/accueil.jsp");
		
		// Récupération de toutes les catégorie
		CategorieManager cm = new CategorieManager();
		
		ArrayList<Categorie> categories = cm.getAllCategories();
		
		request.setAttribute("categories", categories);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/accueil.jsp");
		
		request.getParameter("search");
		
		rd.forward(request, response);
	}

}
