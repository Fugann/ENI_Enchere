package fr.eni.enchere.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.UtilisateurAuthToken;

/**
 * Servlet implementation class encheres
 */
public class accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/accueil.jsp");

		doFilter(request, response);

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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/accueil.jsp");

		String search = request.getParameter("search");
		String categorie = request.getParameter("categorie");

		// Récupération de toutes les catégorie
		CategorieManager cm = new CategorieManager();

		ArrayList<Categorie> categories = cm.getAllCategories();

		// Recupération des articles en fonction du nom et de la catégorie
		ArticleManager am = new ArticleManager();

		ArrayList<Article> articles = am.getAllArticlesByCategorie(search, categorie);

		// Récupération de tout les vendeur
		UtilisateurManager um = new UtilisateurManager();

		ArrayList<Utilisateur> users = um.selectAllSaufMDP();

		request.setAttribute("users", users);
		request.setAttribute("categories", categories);
		request.setAttribute("articles", articles);

		rd.forward(request, response);
	}

	public void doFilter(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("doFilter");

		HttpSession session = request.getSession(false);

		boolean loggedIn = session != null && session.getAttribute("user") != null;

		Cookie[] cookies = request.getCookies();

		if (!loggedIn && cookies != null) {
			System.out.println("test 1");
			// process auto login for remember me feature
			String selector = "";
			String rawValidator = "";

			for (Cookie aCookie : cookies) {
				if (aCookie.getName().equals("selector")) {
					selector = aCookie.getValue();
				} else if (aCookie.getName().equals("validator")) {
					rawValidator = aCookie.getValue();
				}
			}

			if (!"".equals(selector) && !"".equals(rawValidator)) {
				System.out.println("test 2");
				UtilisateurManager um = new UtilisateurManager();
				UtilisateurAuthToken token = um.findBySelector(selector);

				if (token != null) {
					System.out.println("test 3");
					String hashedValidatorDatabase = token.getValidator();
					String hashedValidatorCookie = Utilisateur.hashPwd(rawValidator);

					if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
						System.out.println("test 4");
						Utilisateur user = um.getUserById(String.valueOf(token.getNo_utilisateur()));

						session = request.getSession();
						session.setAttribute("user", user);
						loggedIn = true;

						// update new token in database
						String newSelector = Utilisateur.getRandomStr(12);
						String newRawValidator = Utilisateur.getRandomStr(64);

						String newHashedValidator = Utilisateur.hashPwd(newRawValidator);

						token.setSelector(newSelector);
						token.setValidator(newHashedValidator);
						um.updateAuth(token);

						// update cookie
						Cookie cookieSelector = new Cookie("selector", newSelector);
						Cookie cookieValidator = new Cookie("validator", newRawValidator);
						cookieSelector.setMaxAge(604800);
						cookieSelector.setMaxAge(604800);

						response.addCookie(cookieSelector);
						response.addCookie(cookieValidator);
					}
				}
			}
		}
	}
}
