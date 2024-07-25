package servletit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import Luokat.AsiakasBean;


@WebServlet(name="DispatcherEditServlet", urlPatterns = {"/DispatcherEditServlet"})
public class DispatcherEditServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	Connection conn = null;
	
	String id = "";
	String nimi = "";
	String osoite = "";
	String puhelin = "";
	String email = "";
	String salasana = "";

	/*
	 * init-metodi suoritetaan aina kun Servletti otetaan käyttöön. Se soveltuu
	 * hyvin tietokantayhteyden avaamiseen
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// Kutsutaan perittävän luokan konstruktoria.
		super.init(config);
		// yhteys kantaan tietokantaluokan avulla.
		try {
			conn = Luokat.SQL.openConnection();
		} catch (Exception e) {
			System.out.println("Kantaan ei saada yhteyttä " + e);
		}
	}
	
	// destroy-metodi soveltuu hyvin tietokantayhteyden sulkemiseen.
	public void destroy() {

		try {
			conn.close();
		} catch (SQLException se) {
			System.out.println("Poikkeus " + se);
		}
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

		// Otetaan istunto haltuun
		HttpSession session = request.getSession(false);
		// Luodaan papu johon vastaus sijoitetaan
		AsiakasBean papu = new AsiakasBean();

		// Käsitellään lomake
		// Tehdään tietokantahaku ja tallennetaan tiedot papuun
		String em = request.getParameter("email");

		try {
			// luodaan Statement-olio jonka avulla voidaan suorittaa sql-lause
			Statement stmt = conn.createStatement();

			// Suoritetaan sql, otetaan tulosjoukko kiinni.
			ResultSet rs = stmt.executeQuery("SELECT * FROM asiakkaat WHERE email = '" + em + "'");

			// Käydään tulosjoukko läpi
			while (rs.next()) {
				// luetaan tietueen tiedot
				id = rs.getString("id");
				nimi = rs.getString("nimi");
				osoite = rs.getString("osoite");
				puhelin = rs.getString("puhelin");
				email = rs.getString("email");
				salasana = rs.getString("salasana");

			}
		} catch (Exception e) {
			out.println("Tuli poikkeus!");
		}

		// tiedot papuun
		papu.setId(id);
		papu.setNimi(nimi);
		papu.setOsoite(osoite);
		papu.setPuhelin(puhelin);
		papu.setEmail(email);
		papu.setSalasana(salasana);

		// papu sessioon
		session.setAttribute("papu", papu);
		
		String id = request.getParameter("id");
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
