package servletit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
 *
 * @author TuiTo
 */
@WebServlet(name = "ServletAsiakasBean", urlPatterns = { "/ServletAsiakasBean" })
public class ServletAsiakasBean extends HttpServlet {

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
		// Kutsutaan perittävän luokan konstruktoria.
		super.init(config);
		// yhteys kantaan tietokantaluokan avulla.
		try {
			conn = SQL.openConnection();
		} catch (Exception e) {
			System.out.println("Kantaan ei saada yhteyttä " + e);
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
		PrintWriter out = response.getWriter();
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
		
		// Luodaan papu johon vastaus sijoitetaan
		AsiakasBean papu = new AsiakasBean();
		
		String idNumero = request.getParameter("id");
		
		try {
			
			// luodaan Statement-olio jonka avulla voidaan suorittaa sql-lause
			Statement stmt = conn.createStatement();
			
			// Suoritetaan sql, otetaan tulosjoukko kiinni.
			ResultSet rs = stmt.executeQuery("SELECT * FROM asiakkaat WHERE id = '" + idNumero + "'");
			
			// K�yd��n tulosjoukko läpi
			while (rs.next()) {
				// luetaan tietueen tiedot
				id = rs.getString("id");
				nimi = rs.getString("nimi");
				osoite = rs.getString("osoite");
				puhelin = rs.getString("puhelin");
				email = rs.getString("email");
				salasana = rs.getString("salasana");
				
				// tiedot papuun
				papu.setId(id);
				papu.setNimi(nimi);
				papu.setOsoite(osoite);
				papu.setPuhelin(puhelin);
				papu.setEmail(email);
				papu.setSalasana(salasana);
				
			}	
		}  catch (Exception e) {
			out.println("Tuli poikkeus!");
		}
		
		
		
		request.setAttribute("papu", papu);
		RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/muokkaus.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
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
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
