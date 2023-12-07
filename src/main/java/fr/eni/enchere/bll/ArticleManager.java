package fr.eni.enchere.bll;

import java.util.ArrayList;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.dal.article.ArticleDAO;
import fr.eni.enchere.error.BusinessException;
import fr.eni.enchere.dal.DAOFactory;

public class ArticleManager {

	private ArticleDAO articleDAO;

	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}

	public void insert(Article article, String rue, String CP, String ville) throws BusinessException {
		

		BusinessException exception = new BusinessException();
		this.verifNull(article, rue, CP, ville, exception);
		if (!exception.hasErreurs()) {
			this.articleDAO.insert(article);
		}

		if (exception.hasErreurs()) {
			throw exception;
		}
		
	}


	public ArrayList<Article> getAllArticlesByCategorie(String search, String categorie) {

		return this.articleDAO.getAllCategories(search, categorie);
	}

	private void verifNull(Article article, String rue, String CP, String ville, BusinessException exception) {
		if (article.getNom_article() == null || article.getNom_article().equals("") || article.getDescription() == null || article.getDescription().equals("") ||
				article.getNo_categorie() == 0 || rue == null || rue.equals("") || CP == null || CP.equals("") || ville == null || ville.equals("") ) {
			exception.ajouterErreur(CodesErrorBLL.INPUT_EMPTY_ERROR);
		} else if (article.getDate_debut_encheres().isAfter(article.getDate_fin_encheres())){
			exception.ajouterErreur(CodesErrorBLL.DATE_ERROR);
		}
	}
}