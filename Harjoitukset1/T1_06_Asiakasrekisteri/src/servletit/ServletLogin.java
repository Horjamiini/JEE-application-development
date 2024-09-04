/*
 * Muista asentaa sovelluspalvelimelle
 * MySQL -ajurikirjasto tai tämä tiedosto valittaa
 * NullPointerExceptionia.
 */

package servletit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet(name = "ServletLogin", urlPatterns = { "/ServletLogin" })
public class ServletLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Connection conn = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// yhteys kantaan tietokantaluokan avulla, t�ss� k�ytetty connection poolia!
		try {
	          Context ctx = new InitialContext();
	    	  DataSource ds = (DataSource) ctx.lookup("jdbc/myDataSource");
	    	  conn = ds.getConnection();         
	                     
	            }   
	             catch (Exception ex) {} 
	
	}

	@Override
	public void destroy() {

		// Tietokantayhteyden sulkeminen
		try {
			conn.close();
		} catch (SQLException se) {
			System.out.println("Poikkeus " + se);
		}
	}

	// Kaikki toimenpiteet tehdään doPost-metodissa.
	// Tässä servletissä ei ole käytetty doGet() tai ProcessRequest() -metodia

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request  servlet request
	 * @param response servlet response
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		/*
		 * Mikäli sessiota ei ole niin se luodaan. Sessiota hyödynnetään tarkistettaessa
		 * myöhemmin että käyttäjä on kirjautunut sisään.
		 */

		HttpSession session = request.getSession(true);

		// Luetaan parametrit, jotka kirjautumislomakkeelta on lähetetty.
		String email1 = request.getParameter("email");
		String salasana1 = request.getParameter("salasana");

		// out.print(email);

		// Otetaan muuttuja login_ok käyttöön ja asetetaan oletusarvoksi false.
		boolean login_ok = false;

		try {
			// luodaan Statement-olio jonka avulla voidaan suorittaa sql-lause
			Statement stmt = conn.createStatement();

			// Suoritetaan sql ja saadaan tulosjoukko ResultSet-olioon.
			ResultSet rs = stmt.executeQuery("SELECT email, salasana FROM asiakkaat");

			// Käydään tulosjoukko läpi while silmukalla ja next-metodilla, joka palauttaa
			// arvon true
			// kunnes saavutaan tulosjoukon viimeiseen tietueeseen.
			while (rs.next()) {
				// Luetaan tietueen tiedot
				String email2 = rs.getString("email");
				String salasana2 = rs.getString("salasana");

				// Jos tietokannasta läytyi käyttäjän syöttämät tiedot, niin asetetaan
				// login_ok-muuttujan arvoksi true ja lopetetaan silmukan läpikäynti.
				if (email1.compareTo(email2) == 0 && salasana1.compareTo(salasana2) == 0) {
					login_ok = true;
					break;
				}

			}

			// Jos login_ok:n arvo on true niin asetetaan sessioon tieto loggautumisesta
			// ja ohjataan käyttäjä Asiakkaat.jsp-sivulle.
			if (login_ok == true) {
				// sessioon tieto loggautumisesta
				session.setAttribute("login", "ok");
				// Debuggausta voidaan suorittaa tulostamalla konsoliin.
				System.out.println("Login ok");
				// Ohjaus eteenpäin.
				response.sendRedirect("AsiakkaatServlet");
				// Return-lause lopettaa servletin suorituksen.
				return;
			}
			// Login_ok -arvo on false eli loggautuminen epäonnistui.
			// Ohjataan käyttäjä kirjautumaan uudelleen
			else {
				response.sendRedirect("login.html");
			}

		}

		catch (SQLException se) {

			out.println("Tapahtui virhe: " + se);

		}

		out.close();
	}

}
