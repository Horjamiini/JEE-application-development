package servletit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import luokat.AsiakasBean;
import luokat.SQL;

@WebServlet(name = "AsiakkaatHakuServlet", urlPatterns = { "/AsiakkaatHakuServlet" })
public class AsiakkaatHakuServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	
	String id = "";
	String nimi = "";
	String osoite = "";
	String puhelin = "";
	String email = "";
	String salasana = "";
	String option = "";
	String entry = "";

	// Avataan init-metodissa tietokantayhteys, t‰ss‰ k‰ytetty connection poolia
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
	          Context ctx = new InitialContext();
	    	  DataSource ds = (DataSource) ctx.lookup("jdbc/myDataSource");
	    	  conn = ds.getConnection();         
	                     
	            }   
	             catch (Exception ex) {} 
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
		
			PrintWriter out = response.getWriter();
			
			

			// Otetaan hakuvalinta ja hakusana muuttujat k‰yttˆˆn hakemista varten
			String choise = request.getParameter("hakuvalinta");
			String entry = request.getParameter("hakusana");
			
			// Tehd√§√§n ArrayList johon voidaan sijoittaa CustomerBean-olioita
			ArrayList<AsiakasBean> CustomerList = new ArrayList<>();
			
			
			try {
				
				Statement stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM asiakkaat WHERE `" + choise + "` = '" + entry + "'");
				
				while (rs.next()) {
					id = rs.getString("id");
					nimi = rs.getString("nimi");
					osoite = rs.getString("osoite");
					puhelin = rs.getString("puhelin");
					email = rs.getString("email");
					salasana = rs.getString("salasana");
					
					// Luodaan papu johon asiakkaan tiedot sijoitetaan
					AsiakasBean papu = new AsiakasBean();
					// tiedot papuun
					papu.setId(id);
					papu.setNimi(nimi);
					papu.setOsoite(osoite);
					papu.setPuhelin(puhelin);
					papu.setEmail(email);
					papu.setSalasana(salasana);
					
					
					
					// lis√§t√§√§n jokainen asiakas papuna ArrayListiin
					CustomerList.add(papu);
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
			
			request.setAttribute("searchList", CustomerList);
			RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/haku.jsp");
			rd.forward(request, response);
			
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
