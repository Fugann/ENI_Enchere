package fr.eni.enchere.bll;

import java.util.ArrayList;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.DAOFactory;

public class CategorieManager {
	
	private CategorieDAO categorieDAO;

	public CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}

	public ArrayList<Categorie> getAllCategories() {
		
		return this.categorieDAO.getAllCategories();
	}
	
}
