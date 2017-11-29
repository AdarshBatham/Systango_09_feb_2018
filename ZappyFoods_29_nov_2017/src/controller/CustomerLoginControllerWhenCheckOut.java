package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cart;
import beans.Customer_Cart;
import beans.Product;
import model.CustomerModel;

/**
 * Servlet implementation class CustomerLoginControllerWhenCheckOut
 */
@WebServlet("/CustomerLoginControllerWhenCheckOut")
public class CustomerLoginControllerWhenCheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLoginControllerWhenCheckOut() {
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
			
			Calendar c1=Calendar.getInstance();/*picking up the system's date*/
			int date1=c1.get(Calendar.DATE);
			int month=c1.get(Calendar.MONTH);
			int year=c1.get(Calendar.YEAR);
			String dateonly=date1+"/"+month+"/"+year;
			
			
			HttpSession ht=request.getSession();
			String customeripaddress=(String)ht.getAttribute("customeripaddress");
			/*---------------------------attributes from "customer_login_when_checkout.jsp"---------------------------*/
			String customeremail=request.getParameter("email");
			String password=request.getParameter("password");			
			RequestDispatcher rd=null;			
			
			System.out.println("check function ke pahale.......");
						int x=new CustomerModel().check(customeremail,password);//--------->/*account verification */
						if(x==1)
						{
							Customer_Cart cc=new CustomerModel().getNameAfterSuccessfullCustomerLogin(customeremail);
							ht.setAttribute("customername", (cc.getFirstname()+" "+cc.getLastname()));
							ht.setAttribute("customeremail", customeremail);//------------->session for customer
							System.out.println("CustomerLoginControllerWhenCheckOut----->login success--->x="+x);
							ArrayList<Cart> al=(ArrayList<Cart>)new CustomerModel().getCartData(customeripaddress);
							
							rd=request.getRequestDispatcher("view_pending_orders_to_customer_when_checkout.jsp");	
							request.setAttribute("unorderedproducts", al);
							request.setAttribute("customeremailwhencheckout",customeremail);
							ht.setAttribute("customeremailwhencheckout", customeremail);
							rd.forward(request,response);
						}
						else 
						{
							System.out.println("condition not satisfied.......");
							ArrayList<Cart> al=(ArrayList<Cart>)new CustomerModel().getCartData(customeripaddress);
							rd=request.getRequestDispatcher("customer_login_when_checkout.jsp");
							request.setAttribute("unorderedproducts", al);
							request.setAttribute("adminloginfailed","login failed");
							rd.include(request, response);//=======>login failed
						}
						
		
		}
		catch(Exception e) {e.printStackTrace();}//=======>If any Exception occurs
	
	}

}
