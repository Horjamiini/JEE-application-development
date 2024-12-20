package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PalkanlaskuServlet
 */
@WebServlet(name ="PalkanlaskuServlet", urlPatterns= { "/PalkanlaskuServlet" })
public class PalkanlaskuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	Integer tuntipalkka = Integer.parseInt(request.getParameter("tuntipalkka"));
    	Integer tunnit = Integer.parseInt(request.getParameter("tunnit"));
    	Float veroprosentti = Float.parseFloat(request.getParameter("veroprosentti"));
    	Integer bruttopalkka = tunnit * tuntipalkka;
    	Float nettopalkka = bruttopalkka * (1-(veroprosentti/100));
    	
    	out.println("Bruttopalkka: " + bruttopalkka + "<br>" + "Nettopalkka: " + nettopalkka );
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
