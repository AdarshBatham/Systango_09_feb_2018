package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminModel;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ht=request.getSession();
		
		/*Attributes of "adminlogin.jsp"*/
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		

		AdminModel a=new AdminModel();
		try {
			/*checking authority of admin*/
			int x=a.check(id,password);
			
			/*authority matches*/
			if(x==1)
			{				
				ht.setAttribute("adminid",id);
				response.sendRedirect("adminhome.jsp");
			}
			/*authority didn't matches*/
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
				request.setAttribute("message", "Login failed!!!!");
				rd.include(request, response);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
