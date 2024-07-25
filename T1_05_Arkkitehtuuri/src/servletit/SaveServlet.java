package servletit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveServlet
 */
@WebServlet(name = "SaveServlet", urlPatterns = { "/SaveServlet" })
public class SaveServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    Connection conn = null;
    
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	
    	try {
    		conn = Luokat.SQL.openConnection();
    	} catch (Exception e) {
    		System.out.println("Kantaan ei saada yhteyttä " + e);
    	}
    }
    
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
    	
    	HttpSession session = request.getSession(false);
    	
    	
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
