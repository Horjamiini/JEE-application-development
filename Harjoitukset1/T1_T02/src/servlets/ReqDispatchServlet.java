package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReqDispatchServlet
 */
@WebServlet(name = "ReqDispatchServlet", urlPatterns = {"/ReqDispatchServlet"})
public class ReqDispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		// Tiedot palkkalomake.jsp sivulta
		Double tunnit = Double.parseDouble(request.getParameter("tunnit"));
		Double tuntipalkka = Double.parseDouble(request.getParameter("tuntipalkka"));
		Double veroprosentti = Double.parseDouble(request.getParameter("veroprosentti"));
		
		// Laskutoimitukset
		Double nettopalkka = tunnit * tuntipalkka;
		Double bruttopalkka = nettopalkka * (1-(veroprosentti/100));
		
		request.setAttribute("nettopalkka", nettopalkka);
		request.setAttribute("bruttopalkka", bruttopalkka);
		
		RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/T2c_Palkkalomake/Palkkalomake.jsp");
		rd.forward(request,response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request,response);
		
	}
	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
