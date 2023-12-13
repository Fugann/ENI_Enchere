package fr.eni.enchere.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.error.BusinessException;

/**
 * Servlet implementation class Enchere
 */
public class AjoutEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/enchere.jsp");
		
		if(!Utilisateur.doFilter(request, response)) {
			rd = request.getRequestDispatcher("/WEB-INF/views/accueil.jsp");
		} else {
			
			int id = Integer.parseInt(request.getParameter("id"));
			Enchere enchere = null;
			
			ArticleManager am = new ArticleManager();
			Article article = am.getArticleById(id);
			
			UtilisateurManager um = new UtilisateurManager();
			Utilisateur user = um.getUserById(String.valueOf(article.getNo_utilisateur()));
			
			CategorieManager cm = new CategorieManager();
			Categorie categorie = cm.getCategorieById(article.getNo_categorie());
			
			EnchereManager em = new EnchereManager();
			enchere = em.getEnchereById(article.getNo_article());
			
			request.setAttribute("article", article);
			request.setAttribute("user", user);
			request.setAttribute("categorie", categorie);
			request.setAttribute("enchere", enchere);
			
			System.out.println(article.getDate_debut_encheres());
			
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(!Utilisateur.doFilter(request, response)) {
			response.sendRedirect(request.getContextPath());
		} else {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			ArticleManager am = new ArticleManager();
			Article article = am.getArticleById(id);
			
			UtilisateurManager um = new UtilisateurManager();
			Utilisateur user = um.getUserById(String.valueOf(article.getNo_utilisateur()));
			
			CategorieManager cm = new CategorieManager();
			Categorie categorie = cm.getCategorieById(article.getNo_categorie());
			
			request.setAttribute("article", article);
			request.setAttribute("user", user);
			request.setAttribute("categorie", categorie);
			
			String montant = request.getParameter("montant");
			
			try {
				EnchereManager em = new EnchereManager();
				
				Enchere montant_enchere = em.getEnchereById(article.getNo_article());
				if(montant_enchere == null) {
					montant_enchere = new Enchere(user.getNo_utilisateur(), article.getNo_article(), null, 0);
				}
				System.out.println("enchere = " + montant_enchere.getMontant_enchere());
				
				Enchere enchere = em.insert(user.getNo_utilisateur(), article.getNo_article(), montant, article.getPrix_initial(), montant_enchere.getMontant_enchere());
				System.out.println(enchere.getDate_enchere());
				if(enchere.getDate_enchere() != null) {
					request.setAttribute("succes", "true");
				}
				
			} catch (BusinessException e) {
				request.setAttribute("codesError", e.getListeCodesErreur());
			}
			
			EnchereManager em = new EnchereManager();
			Enchere enchere = em.getEnchereById(article.getNo_article());
			
			request.setAttribute("enchere", enchere);
			response.sendRedirect(request.getContextPath() + "/Enchere?id=" + article.getNo_article());
		}
	}
}
