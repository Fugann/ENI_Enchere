package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {
private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur ajouter(String pseudo, String nom, String prenom, String email, String tel, String rue, String CP,
			String ville, String psw, int credit, Byte admin) {
		Utilisateur u = new Utilisateur(pseudo, nom, prenom, email, tel, rue, CP, ville, psw, credit, admin);
		this.utilisateurDAO.insert(u);
		return u;
	}
}
