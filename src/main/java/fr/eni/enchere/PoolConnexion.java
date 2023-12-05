package fr.eni.enchere;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.sql.DataSource;

/**
 * Servlet implementation class PoolConnexion
 */
@WebServlet("/Pool")
public class PoolConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Define datasource/connection pool
	@Resource(name = "jdbc/pool_cnx")
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Initialize connection
		Connection conn = null;
		PrintWriter out = response.getWriter();

		try {
			// Demande une connexion. La méthode getConnection met la demande en attente
			// tant qu'il n'y a pas de connexion disponibles dans le pool
			conn = dataSource.getConnection();
			out.print("La connexion est " + (conn.isClosed() ? "fermée" : "ouverte") + ".");

			// libérer la connexion lorsque l'on en a plus besoin
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.println("Une erreur est survenue lors de l'utilisation de la base de donnée : " + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
