package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import fr.eni.enchere.bo.Article;

public class ArticleDAOJDBC implements ArticleDAO {

	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, image, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?);";
	private static final String SELECT_ALL_ARTICLES_BY_CATEGORIES = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie = ? AND NOM_ARTICLE LIKE ?";
	
	@Override
	public void insert(Article article) {

		// récupération d'une connexion du pool de connexion
		try (Connection conn = ConnectionProvider.getConnection()) {

			// préparation de l'ajout dans la bdd
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
			// récupération de ce que renvoi la bdd
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				// set le numéro de l'article pour la vérification dans le ajoutArticle.java
				article.setNo_article(rs.getInt(1));
			}

			// Fermeture de la connexion
			conn.close();
			System.out.println("Ajout de l'article : succes");

		} catch (Exception e) {
			System.out.println("Ajout de l'article : echec");
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Article> getAllCategories(String search, String categorie) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_ARTICLES_BY_CATEGORIES);
			pstmt.setString(1, categorie);
			pstmt.setString(2, "%" + search + "%");
			ResultSet rs = pstmt.executeQuery();

			ArrayList<Article> articles = new ArrayList<Article>();
			Article article = null;
			while (rs.next()) {
				int no_article = rs.getInt("no_article");
				String nom_article = rs.getString("nom_article");
				String description = rs.getString("description");
				String image = rs.getString("image");
				LocalDate date_debut_encheres = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate date_fin_encheres = rs.getDate("date_fin_encheres").toLocalDate();
				int prix_initial = rs.getInt("prix_initial");
				int prix_vente = rs.getInt("prix_vente");
				int no_utilisateur = rs.getInt("no_utilisateur");
				int no_categorie = rs.getInt("no_categorie");
				
				article = new Article(no_article, nom_article, description, image, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie);
				articles.add(article);
			}
			System.out.println("Select des articles : succes");
			conn.close();
			return articles;
		} catch (Exception e) {
			System.out.println("Select des articles : echec");
			e.printStackTrace();
		}
		return null;
	}

}
