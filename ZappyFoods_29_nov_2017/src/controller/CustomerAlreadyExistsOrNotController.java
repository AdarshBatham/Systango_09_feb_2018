package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustomerModel;

/**
 * Servlet implementation class CustomerAlreadyExistsOrNotController
 */
@WebServlet("/CustomerAlreadyExistsOrNotController")
public class CustomerAlreadyExistsOrNotController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAlreadyExistsOrNotController() {
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
			String email=request.getParameter("email");
			int x=new CustomerModel().checkExistanceOfCustomer(email);
			PrintWriter out=response.getWriter();
			RequestDispatcher rd=null;
			if(x==1)
			{
//				rd=request.getRequestDispatcher("customer_login.jsp");
//				request.setAttribute("customer_already_registered", "You are already registered!!!!!");
				System.out.println("x="+x);
				out.println(x);
			}
	}

}
