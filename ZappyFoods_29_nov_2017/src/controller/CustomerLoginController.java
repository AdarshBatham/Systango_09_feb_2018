package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Customer_Cart;
import model.CustomerModel;


/**
 * Servlet implementation class CustomerLoginController
 */
@WebServlet("/CustomerLoginController")
public class CustomerLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLoginController() {
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
		try
		{
			HttpSession ht=request.getSession();
			
			/*---------------------------attributes from "customer_login.jsp"---------------------------*/
			String customeremail=request.getParameter("email");
			String password=request.getParameter("password");
			
		
			
			RequestDispatcher rd=null;			
			
			System.out.println("check function ke pahale.......");
						int x=new CustomerModel().check(customeremail,password);//--------->/*account verification */
						if(x==1)
						{
							Customer_Cart cc=new CustomerModel().getNameAfterSuccessfullCustomerLogin(customeremail);
							System.out.println("condition satisfied.......--->x="+x);
							ht.setAttribute("customeremail", customeremail);//------------->session for customer
							
							ht.setAttribute("customername", (cc.getFirstname()+" "+cc.getLastname()));
							rd=request.getRequestDispatcher("customer_home.jsp");
							rd.forward(request, response);//=======>login success
						}
						else
						{
							System.out.println("condition not satisfied.......");
							
							rd=request.getRequestDispatcher("customer_login.jsp");
							request.setAttribute("adminloginfailed","login failed");
							rd.include(request, response);//=======>login failed
							
							
//							PrintWriter pw=response.getWriter();
//							pw.println("Login failed!!!!!");
						}
						
		
		}
		catch(Exception e) {e.printStackTrace();}//=======>If any Exception occurs
	}

}
