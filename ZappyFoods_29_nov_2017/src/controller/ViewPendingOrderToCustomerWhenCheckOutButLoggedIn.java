package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cart;
import beans.Customer_Cart;
import model.CustomerModel;
import model.ProductModel;

/**
 * Servlet implementation class ViewPendingOrderToCustomerWhenCheckOutButLoggedIn
 */
@WebServlet("/ViewPendingOrderToCustomerWhenCheckOutButLoggedIn")
public class ViewPendingOrderToCustomerWhenCheckOutButLoggedIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPendingOrderToCustomerWhenCheckOutButLoggedIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;	
		HttpSession ht=request.getSession();
		
		
		/*Customer_Cart cc=new CustomerModel().getNameAfterSuccessfullCustomerLogin(customeremail);		
		ht.setAttribute("customername", (cc.getFirstname()+" "+cc.getLastname()));*/
		String customeripaddress=(String)ht.getAttribute("customeripaddress");
		
		ArrayList<Cart> al=(ArrayList<Cart>)new CustomerModel().getCartData(customeripaddress);	
		ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);
		
		
		
		
		
		rd=request.getRequestDispatcher("view_pending_orders_to_customer_when_checkout.jsp");	
		request.setAttribute("unorderedproducts", al);
		request.setAttribute("calculated_values", calculated_values);
		
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
