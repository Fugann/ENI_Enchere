package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.DAOFactory;
import fr.eni.ecole.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {
private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur ajouter(String pseudo, String nom, String prenom, String email, String tel, String rue, String CP,
			String ville, String psw) {
		
		Utilisateur u = new Utilisateur(pseudo, nom, prenom, email, tel, rue, CP, ville, psw);
		this.utilisateurDAO.insert(u);
		return u;
		
	}
}
