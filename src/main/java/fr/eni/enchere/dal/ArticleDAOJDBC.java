package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.enchere.bo.Article;

public class ArticleDAOJDBC implements ArticleDAO {
	
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, image, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?);";

	@Override
	public void insert(Article article) {
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pstmt = conn.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNom_article());
			pstmt.setString(2, article.getDescription());
			pstmt.setString(3, article.getImage());
			pstmt.setDate(4, Date.valueOf(article.getDate_debut_encheres()));
			pstmt.setDate(5, Date.valueOf(article.getDate_fin_encheres()));
			pstmt.setInt(6, article.getPrix_initial());
			pstmt.setInt(7, article.getNo_utilisateur());
			pstmt.setInt(8, article.getNo_categorie());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				article.setNo_article(rs.getInt(1));
			}
 
			conn.close();
			System.out.println("Ajout de l'article : succes");
 
		} catch (Exception e) {
			System.out.println("Ajout de l'article : echec");
			e.printStackTrace();
		}

	}

		
	

}
