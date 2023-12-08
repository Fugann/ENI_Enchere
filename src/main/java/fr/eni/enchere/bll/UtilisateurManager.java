package fr.eni.enchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.UtilisateurAuthToken;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.utilisateur.UtilisateurDAO;
import fr.eni.enchere.dal.utilisateur.UtilisateurDAOJDBC;
import fr.eni.enchere.error.BusinessException;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur ajouter(String pseudo, String nom, String prenom, String email, String tel, String rue,
			String CP, String ville, String psw, String pswconfirm, int credit, byte admin) throws BusinessException {
		BusinessException exception = new BusinessException();
		this.VerifSamePassword(psw, pswconfirm, exception);
		this.verifNull(pseudo, prenom, CP, psw, nom, email, rue, ville, exception);
		this.selectPseudoByPseudo(pseudo, exception);
		this.selectEmailByEmail(email, exception);
		Utilisateur u = null;

		if (!exception.hasErreurs()) {
			u = new Utilisateur(pseudo, nom, prenom, email, tel, rue, CP, ville, psw, credit, admin);
			this.utilisateurDAO.insert(u);

		}
		if (exception.hasErreurs()) {
			throw exception;
		}
		return u;
	}

	public Utilisateur getUserDetails(String identifiant, String password) throws BusinessException {
		BusinessException exception = new BusinessException();

		password = Utilisateur.hashPwd(password);

		System.out.println("test");
		Utilisateur user = this.utilisateurDAO.getUserByEmail(identifiant);

		if (user == null) {
			System.out.println("test1");
			user = this.utilisateurDAO.getUserByPseudo(identifiant);
		}

		this.verifIdentifiant(user, identifiant, password, exception);

		if (!exception.hasErreurs()) {
			System.out.println("test3");
			return user;
		} else {
			throw exception;
		}
	}

	public ArrayList<Utilisateur> selectAllSaufMDP() {
		return this.utilisateurDAO.selectAllSaufMDP();
	}

	public Utilisateur getUserById(String userId) {
		return this.utilisateurDAO.getUserById(userId);
	}

	public void updateUser(Utilisateur user) {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOJDBC();
		utilisateurDAO.update(user);
	}

	private void verifIdentifiant(Utilisateur user, String identifiant, String password, BusinessException exception) {
		if (user == null || !password.equals(user.getMot_de_passe())) {
			System.out.println("test 2");
			exception.ajouterErreur(CodesErrorBLL.IDENTIFIANT_MDP_ERROR);
		}
	}

	public void setTokenAuth(String selector, String hashedRawValidator, Integer no_utilisateur) {
		
		UtilisateurAuthToken authToken = new UtilisateurAuthToken(selector, hashedRawValidator, no_utilisateur);
		utilisateurDAO.setTokenAuth(authToken);
		
	}
	
	public UtilisateurAuthToken findBySelector(String selector) {
		List<UtilisateurAuthToken> list = utilisateurDAO.findBySelector(selector);
		
		if (!list.isEmpty()) {
			return list.get(0);
		}	
		return null;
	private void VerifSamePassword(String psw, String pswconfirm, BusinessException exception) {
		if (psw == null || !psw.equals(pswconfirm)) {
			exception.ajouterErreur(CodesErrorBLL.SAME_PASSWORD_ERROR);
		}
	}

	private void verifNull(String pseudo, String prenom, String CP, String psw, String nom, String email, String rue,
			String ville, BusinessException exception) {
		if (pseudo == null || pseudo.equals("") || prenom == null || prenom.equals("") || CP == null || CP.equals("")
				|| psw == null || psw.equals("") || nom == null || nom.equals("") || email == null || email.equals("")
				|| rue == null || rue.equals("") || ville == null || ville.equals("")) {
			System.out.println("test verifNull");
			exception.ajouterErreur(CodesErrorBLL.INPUT_EMPTY_ERROR);
		}
	}

	public void selectPseudoByPseudo(String pseudo, BusinessException exception) {
		String pseudoBDD = this.utilisateurDAO.selectPseudoByPseudo(pseudo);

		if (pseudo.equals(pseudoBDD)) {
			System.out.println("test CREATE_PSEUDO_ERROR");
			exception.ajouterErreur(CodesErrorBLL.CREATE_PSEUDO_ERROR);
		}

	}

	public void selectEmailByEmail(String email, BusinessException exception) {
		String emailBDD = this.utilisateurDAO.selectEmailByEmail(email);

		if (email.equals(emailBDD)) {
			System.out.println("test CREATE_EMAIL_ERROR");
			exception.ajouterErreur(CodesErrorBLL.CREATE_EMAIL_ERROR);
		}
	}
}
