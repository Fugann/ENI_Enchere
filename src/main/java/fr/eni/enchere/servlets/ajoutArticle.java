package fr.eni.enchere.servlets;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;

/**
 * Servlet implementation class ajoutArticle
 */
public class ajoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ajoutArticle.jsp");

		// Récupération de toutes les catégorie
		CategorieManager cm = new CategorieManager();

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

		// Récupération de toutes les données en param
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
		String image = request.getParameter("image");
		String prix = request.getParameter("prix");
		String rue = request.getParameter("rue");
		String CP = request.getParameter("CP");
		String ville = request.getParameter("ville");
		int no_utilisateur = 1;
		LocalDate debut = null;
		LocalDate fin = null;
		
		if(request.getParameter("debut") != "" && request.getParameter("fin") != "") {
			debut = LocalDate.parse(request.getParameter("debut"));
			fin = LocalDate.parse(request.getParameter("fin"));
		}
		
		
		// Vérification des données
		if (article == null || article.equals("") || description == null || description.equals("") || categorie == null
				|| categorie.equals("") || rue == null || rue.equals("") || CP == null || CP.equals("") || ville == null
				|| ville.equals("")) {
			request.setAttribute("error", "Veuillez remplir tous les champs !");
		} else if (debut.isAfter(fin)) {
			request.setAttribute("error", "Veuillez saisir une date de début d'enchères inférieure à la date de fin !");
		} else {
			// Création d'un article manager
			ArticleManager am = new ArticleManager();
			// Création de l'article
			Article a = new Article(article, description, image, debut, fin, Integer.parseInt(prix), no_utilisateur,
					Integer.parseInt(categorie));
			// Fonction insert qui fait la relation entre le code et la bdd
			am.insert(a);
			// Vérification si l'article a bien était crée
			if (a.getNo_article() != null) {
				request.setAttribute("success", "Article créé avec succès !");
			} else {
				request.setAttribute("error", "Une erreur sql c'est produite");
			}
		}

		// Récupération de toutes les catégorie
		CategorieManager cm = new CategorieManager();

		ArrayList<Categorie> categories = cm.getAllCategories();

		request.setAttribute("categories", categories);

		rd.forward(request, response);
	}
}