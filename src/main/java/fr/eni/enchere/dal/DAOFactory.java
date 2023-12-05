package fr.eni.enchere.dal;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJDBC();
	}
	
	public static ArticleDAO getArticleDAO()
	{
		return new ArticleDAOJDBC();
	}
}
