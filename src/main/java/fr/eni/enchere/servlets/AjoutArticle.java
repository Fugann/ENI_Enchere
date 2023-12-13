package fr.eni.enchere.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.error.BusinessException;

/**
 * Servlet implementation class AjoutArticle
 */
public class AjoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CategorieManager cm = new CategorieManager();
	private final ArticleManager am = new ArticleManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ajoutArticle.jsp");

		// Récupération de toutes les catégorie
		ArrayList<Categorie> categories = cm.getAllCategories();

		request.setAttribute("categories", categories);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ajoutArticle.jsp");

		try {
			// Récupération de toutes les données en param
			String name = request.getParameter("article");
			String description = request.getParameter("description");
			String categorie = request.getParameter("categorie");
			String image = request.getParameter("image");
			String prix = request.getParameter("prix");
			String rue = request.getParameter("rue");
			String CP = request.getParameter("CP");
			String ville = request.getParameter("ville");
			int no_utilisateur = 1;
			String debut = request.getParameter("debut");
			String fin = request.getParameter("fin");

			// Fonction insert dans la bdd
			Article article = am.insert(name, description, categorie, image, prix, rue, CP, ville, no_utilisateur,
					debut, fin);

			request.setAttribute("article", article);
		} catch (BusinessException e) {
			request.setAttribute("codesError", e.getListeCodesErreur());
		}

		// Récupération de toutes les catégorie
		ArrayList<Categorie> categories = cm.getAllCategories();

		request.setAttribute("categories", categories);

		rd.forward(request, response);
	}
}