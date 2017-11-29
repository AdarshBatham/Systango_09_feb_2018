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
import model.CustomerModel;
/**
 * Servlet implementation class PendingOrdersController
 */
@WebServlet("/PendingOrdersController")
public class PendingOrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingOrdersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ht=request.getSession();
		String customeripaddress=(String)ht.getAttribute("customeripaddress");
		
		RequestDispatcher rd=null;
		
		
		/*------------------fetching the data from carttable----------------------*/
		ArrayList<Cart> al=(ArrayList<Cart>)new CustomerModel().getCartData(customeripaddress);
			if(al!=null) {
				rd=request.getRequestDispatcher("view_pending_orders_to_customer.jsp");
				/*--------------setting the arraylist--------------*/
				request.setAttribute("unorderedproducts", al);
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("view_pending_orders_to_customer.jsp");
				request.setAttribute("unorderedproducts","Sorry! no data found about loan");
				rd.include(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ht=request.getSession();
		String customeripaddress=(String)ht.getAttribute("customeripaddress");
		
		RequestDispatcher rd=null;
		
		
		/*------------------fetching the data from carttable----------------------*/
		ArrayList<Cart> al=(ArrayList<Cart>)new CustomerModel().getCartData(customeripaddress);
			if(al!=null) {
				rd=request.getRequestDispatcher("view_pending_orders_to_customer.jsp");
				/*--------------setting the arraylist--------------*/
				request.setAttribute("unorderedproducts", al);
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("view_pending_orders_to_customer.jsp");
				request.setAttribute("unorderedproducts","Sorry! no data found about loan");
				rd.include(request, response);
			}
	}

}
