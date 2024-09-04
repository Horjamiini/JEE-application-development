package servletit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import luokat.AsiakasBean;
import luokat.SQL;

/**
 * Servlet implementation class ServletAsiakasTallenna
 */
@WebServlet(name = "ServletAsiakasTallenna", urlPatterns = { "/ServletAsiakasTallenna" })
public class ServletAsiakasTallenna extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	
	String id = "";
	String nimi = "";
	String osoite = "";
	String puhelin = "";
	String email = "";
	String salasana = "";

	// Avataan init-metodissa tietokantayhteys
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			conn = SQL.openConnection();
		} catch (Exception e) {
			System.out.println("Poikkeus " + e);
		}
	}
	
	// Suljetaan tietokanta yhteys destroy-metodissa
	@Override
	public void destroy() {
		try {
			conn.close();
		} catch (SQLException se) {
			System.out.println("Poikkeus " + se);
		}
	}
	
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	try {
		HttpSession session = request.getSession(false);
		
		String log = (String) session.getAttribute("login");
		if (!log.equals("ok")) {
			// ei-loggautuneet p‰‰sivulle
			response.sendRedirect("login.html");
			}
		} catch (Exception e) {
			response.sendRedirect("login.html");
			return;
		}
	
			id = request.getParameter("id");
			nimi = request.getParameter("nimi");
			osoite = request.getParameter("osoite");
			puhelin = request.getParameter("puhelin");
			email = request.getParameter("email");
			salasana = request.getParameter("salasana");
	
		try {
			
			Statement stmt = conn.createStatement();
			
			int rs = stmt.executeUpdate
					("UPDATE asiakkaat SET nimi = '" + nimi + "', osoite = '" + osoite + "', puhelin = '" + puhelin + "', email = '" + email + "', salasana = '" + salasana + "' WHERE id = '" + id + "'");
			
			System.out.println("Rows affected: " + rs);
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		response.sendRedirect("AsiakkaatServlet");
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
