package com.javacodegeeks.servlet.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetSetExampleServlet
 */
@WebServlet("/getset")
public class GetSetExampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSetExampleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("mySecretMessage", "Olen Request");
		request.getSession().setAttribute("mySecretMessage","Olen Session");
		request.getServletContext().setAttribute("mySecretMessage","Olen contexti");
		
		request.getRequestDispatcher("index.jsp").forward(request,response);
	}

}
