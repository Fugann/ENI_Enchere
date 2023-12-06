package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public void ajouter(Utilisateur u) {
		this.utilisateurDAO.insert(u);
	}

	public Utilisateur getUserDetails(String userEmail) {
		return this.utilisateurDAO.getUserByEmail(userEmail);
	}
}
