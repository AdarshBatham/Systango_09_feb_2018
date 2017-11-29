package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.CustomerModel;

/**
 * Servlet implementation class CustomerChangePasswordController
 */
@WebServlet("/CustomerChangePasswordController")
public class CustomerChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerChangePasswordController() {
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
	String email=(String)ht.getAttribute("customeremail"); 
	
	
	if(email!=null)
	{
		/*accepting attributes from "customer_change_password.jsp"*/
		String oldpassword=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");
		String confirmpassword=request.getParameter("confirmpassword");
		
		
		String customeremail=(String)ht.getAttribute("customeremail");
		
		RequestDispatcher rd=null;
		int x=0;
		if(newpassword.equals(confirmpassword))
			
			/*changing password from old one to new one*/
			x=new CustomerModel().changePassword(customeremail,confirmpassword);
		
		if(x==1)
		{
			/*forwarding to "customer_change_password.jsp" with some message*/
			rd=request.getRequestDispatcher("customer_change_password.jsp");
			request.setAttribute("customerpasswordchanged", "password has been changed successfully");
			rd.forward(request, response);
		}
		else
		{
			/*forwarding to "customer_change_password.jsp" with some message*/
			rd=request.getRequestDispatcher("customer_change_password.jsp");
			request.setAttribute("customerpasswordnotchanged", "password chainging failed!!!!!");
			rd.include(request, response);
		}
	}
	else
		response.sendRedirect("customer_login.jsp");
	}

}
