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
import beans.Product;
import model.ProductModel;

/**
 * Servlet implementation class ViewAllProductsControllerFromInside
 */
@WebServlet("/ViewAllProductsControllerFromInside")
public class ViewAllProductsControllerFromInside extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllProductsControllerFromInside() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ht=request.getSession();
		String customeremail=(String)ht.getAttribute("customeremail");
		String customeripaddress=(String)ht.getAttribute("customeripaddress");
		RequestDispatcher rd;
		
		ArrayList<Product> al=new ProductModel().getData();
		System.out.println("ViewAllProductsControllerFromInside----->al="+al);
		if(al!=null) 
		{
			System.out.println("data found chalaaaaaaaaa");
			/*------------------retriving all order details to customer according to his/her email----------------------*/
						ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);
						rd=request.getRequestDispatcher("view_all_products_from_inside.jsp");
						request.setAttribute("calculated_values", calculated_values);
						request.setAttribute("all_products_arraylist", al);
						rd.forward(request, response);
			
		}
		else
		{
			System.out.println("no data found chalaaaaaaaaa");
			rd=request.getRequestDispatcher("customer_home.jsp");
			request.setAttribute("nodatafound","Sorry! no data found");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
