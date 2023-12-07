package fr.eni.enchere.dal.article;

import java.util.ArrayList;

import fr.eni.enchere.bo.Article;

public interface ArticleDAO {
	
	public void insert( Article article );

	public ArrayList<Article> getAllCategories(String search, String categorie);
	

}
