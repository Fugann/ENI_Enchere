package fr.eni.enchere.bll;

import java.util.ArrayList;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.DAOFactory;

public class ArticleManager {

	private ArticleDAO articleDAO;

	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}

	public void insert(Article article) {
		this.articleDAO.insert(article);
	}

	public ArrayList<Article> getAllArticlesByCategorie(String search, String categorie) {
		
		return this.articleDAO.getAllCategories(search, categorie);
	}
	

	
}