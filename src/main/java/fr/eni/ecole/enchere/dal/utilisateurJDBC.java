package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.ecole.enchere.bo.Utilisateur;

public class utilisateurJDBC implements UtilisateurDAO {
	
	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEUR(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES(?,?,?,?,?,?,?,?,?);";
	
	@Override
	public void insert(Utilisateur utilisateur) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pstmt = conn.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCode_postal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMot_de_passe());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				utilisateur.setNo_utilisateur(rs.getInt(1));
			}
 
			
			conn.close();
			System.out.println("Ajout du repas : succes");
 
		} catch (Exception e) {
			System.out.println("Ajout du repas : echec");
			e.printStackTrace();
		}

	}

}
