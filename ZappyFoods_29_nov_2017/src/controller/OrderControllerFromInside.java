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
 * Servlet implementation class OrderControllerFromInside
 */
@WebServlet("/OrderControllerFromInside")
public class OrderControllerFromInside extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderControllerFromInside() {
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
		String customeripaddress=(String)ht.getAttribute("customeripaddress");		
		String customeremail=(String)ht.getAttribute("customeremail");
		
		//--------------hidden field values from "viewpendingproductstocustomer.jsp"----------------
		int id_from_carttable=Integer.parseInt(request.getParameter("id"));
		String hidden_field_ipaddress=request.getParameter("customeripaddress");

				int x=new CustomerModel().operation(customeremail,id_from_carttable,hidden_field_ipaddress);
			
				if(x==1)
				{
					
//					String customeripaddress=(String)ht.getAttribute("customeripaddress");
					ArrayList<Cart> al=(ArrayList<Cart>)new CustomerModel().getCartData(customeripaddress);
					RequestDispatcher rd=request.getRequestDispatcher("view_pending_orders_to_customer_from_inside.jsp");
					request.setAttribute("unorderedproducts", al);
					rd.forward(request, response);
				}
				else
				{
					RequestDispatcher rd=request.getRequestDispatcher("view_pending_orders_to_customer_from_inside.jsp");
					request.setAttribute("unorderedproducts", "Order Not placed");
					rd.include(request, response);
				}

	}

}
