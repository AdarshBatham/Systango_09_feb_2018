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
 * Servlet implementation class CancelOrderControllerFromOrderTable
 */
@WebServlet("/CancelOrderControllerFromOrderTable")
public class CancelOrderControllerFromOrderTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderControllerFromOrderTable() {
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
		
		/*attributes from "view_dispatched_orders_to_customer.jsp"*/
		int id_from_ordertable=Integer.parseInt(request.getParameter("id"));
		int uniqueid=Integer.parseInt(request.getParameter("uniqueid"));
		String customeremail=request.getParameter("customeremail");
		
				/*customer is cancelling order*/
				int x=new CustomerModel().cancelOrderNow(customeremail,uniqueid,id_from_ordertable);
				System.out.println("CancelOrderFromOrderTableController---->   new CustomerModel().cancelOrderNow(customeremail,id_from_ordertable)----->x="+x);
				/*if(x==1)
				{
					setting back the data to arraylist because the same will be caught to the "view_dispatched_orders_to_customer.jsp"
					ArrayList<Cart> al=(ArrayList<Cart>)new CustomerModel().getData(customeremail);
						
					
					
					if(al.isEmpty())
					{
						RequestDispatcher rd=request.getRequestDispatcher("view_dispatched_orders_to_customer.jsp");	
						request.setAttribute("orderedproducts", al);
						request.setAttribute("noorders", "No Orders anymore");
						rd.include(request, response);
					}
					else
					{
						RequestDispatcher rd=request.getRequestDispatcher("view_dispatched_orders_to_customer.jsp");
						request.setAttribute("orderedproducts", al);
						request.setAttribute("ordercancelled", "Your Order Has Been Cancelled Successfully ");
						rd.forward(request, response);
					}
				}
				else
				{
					setting back the data to arraylist because the same will be caught to the "view_dispatched_orders_to_customer.jsp"
					ArrayList<Cart> al=(ArrayList<Cart>)new CustomerModel().getData(customeremail);
					RequestDispatcher rd=request.getRequestDispatcher("view_dispatched_orders_to_customer.jsp");
					request.setAttribute("orderedproducts", al);
					request.setAttribute("ordernotcancelled", "Order Not cancelled");
					rd.include(request, response);
				}*/
				
				
				
		
				RequestDispatcher rd;
				
				
				/*------------------retriving all order details to customer according to his/her email----------------------*/
				ArrayList<Cart> al=new CustomerModel().getAllData(customeremail);
				if(al!=null) {
					rd=request.getRequestDispatcher("view_all_orders.jsp");
					request.setAttribute("viewordershistory", al);
					rd.forward(request, response);
				}
				else
				{
					rd=request.getRequestDispatcher("viewordershistory.jsp");
					request.setAttribute("viewordershistory","Sorry! no data found about loan");
					rd.include(request, response);
				}
	}

}
