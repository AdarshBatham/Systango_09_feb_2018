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
 * Servlet implementation class ViewDispatchedOrdersController
 */
@WebServlet("/ViewDispatchedOrdersController")
public class ViewDispatchedOrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDispatchedOrdersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ht=request.getSession();
		String customeremail=(String)ht.getAttribute("customeremail");		
		RequestDispatcher rd=null;
		
			if(customeremail!=null)
			{
				/*------------------retriving all order details to customer according to his/her email----------------------*/
				ArrayList<Cart> al=(ArrayList<Cart>)new CustomerModel().getData(customeremail);
				if(al!=null)
				{
					/*------------------forwarding to "view_dispatched_orders_to_customer.jsp"----------------------*/
					rd=request.getRequestDispatcher("view_dispatched_orders_to_customer.jsp");
					request.setAttribute("orderedproducts", al);
					rd.forward(request, response);
				}
				else
				{
					rd=request.getRequestDispatcher("view_dispatched_orders_to_customer.jsp");
					request.setAttribute("noorders","Sorry! no data found about loan");
					rd.include(request, response);
				}
			}
			else
				response.sendRedirect("customer_login.jsp");
	}

}
