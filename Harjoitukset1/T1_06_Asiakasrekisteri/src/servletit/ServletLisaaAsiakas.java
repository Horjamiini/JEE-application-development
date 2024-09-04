package servletit;

/*
 * ServletLisaaAsiakas.java
 * Lisätään tauluun asiakkaan tiedot paitsi id
 * joka lisätään automaattisesti
 * 
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletLisaaAsiakas", urlPatterns = { "/ServletLisaaAsiakas" })
public class ServletLisaaAsiakas extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Connection conn = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// yhteys kantaan tietokantaluokan avulla
		try {
		conn = luokat.SQL.openConnection();
		} catch (Exception e) {
			System.out.println("Kantaan eisaada yhteytt�" + e);
		}

	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// ei päästetä servlettiin muita kuin loggautuneita
			HttpSession session = request.getSession(false);

			String log = (String) session.getAttribute("login");
			if (!log.equals("ok")) {
				// ei-loggautuneet takaisin login-sivulle
				response.sendRedirect("login.html");
			}
		} catch (Exception e) {
			response.sendRedirect("login.html");
			return;
		}

		response.setContentType("text/html;UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8"); // Varmistetaan että ääkköset toimivat
		String nimi = request.getParameter("nimi");
		String osoite = request.getParameter("osoite");
		String puhelin = request.getParameter("puhelin");
		String email = request.getParameter("email");
		String salasana = request.getParameter("salasana");

		// Tehdään tarkistus, jolla varmistetaan, että käyttäjä on syöttänyt
		// kaikki tietokannan vaatimat tiedot. Lisäystä ei tehdä, jos jotain
		// vaadittavista tiedoista puuttuu.
		if (nimi == null || nimi.length() == 0 || email == null || email.length() == 0 || salasana == null
				|| salasana.length() == 0) {
			out.println("Nimi, salasana ja sähköpostiosoite annettava!");
		} else {
			// Esitellään PreparedStatement-olio, johon asetetaan myöhemmin lisäyskomento.
			PreparedStatement pre_stmt = null;

			// Kaikki tietokantatoiminnot on tehtävä try-lohkon sisällä. Joten...
			try {
				// Muodostetaan tietokantakomento aluksi merkkijonoon oikean muotoisena.
				// Kysymysmerkkejä käytetään parametrien paikalla.
				String sql = "INSERT INTO asiakkaat (nimi,osoite,puhelin,email,salasana) " + "VALUES (?,?,?,?,?)";
				// Alustetaan olio luodulla merkkijonolla.
				pre_stmt = conn.prepareStatement(sql);

				// Asetetaan sql-komentoon parametrien paikalle käyttäjän syöttämät tiedot.
				pre_stmt.setString(1, nimi);
				pre_stmt.setString(2, osoite);
				pre_stmt.setString(3, puhelin);
				pre_stmt.setString(4, email);
				pre_stmt.setString(5, salasana);

				// executeUpdate-komento suorittaa sql-komennon eli tässä lisäyksen
				// tietokantaan.
				pre_stmt.executeUpdate();
				response.sendRedirect("AsiakkaatServlet");
			} catch (SQLException e) {
				out.println("Lisäys epäonnistui, poikkeus " + e);
			} // Finally-lohkossa suljetaan PreparedStatement-olio.
			finally {
				try {
					pre_stmt.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}

		}

		out.close();
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}
}
