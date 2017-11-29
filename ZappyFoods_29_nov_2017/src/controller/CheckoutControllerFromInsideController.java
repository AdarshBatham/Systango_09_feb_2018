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
import model.CustomerModel;
import model.ProductModel;

/**
 * Servlet implementation class CheckoutControllerFromInsideController
 */
@WebServlet("/CheckoutControllerFromInsideController")
public class CheckoutControllerFromInsideController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutControllerFromInsideController() {
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
		RequestDispatcher rd=null;			
		
		ArrayList<Cart> al=(ArrayList<Cart>)new CustomerModel().getCartData(customeripaddress);
		ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);
		ArrayList<Product> al1=new ProductModel().getData();			
		request.setAttribute("all_products_arraylist",al1);
		
			if(al.isEmpty()) 
			{
				System.out.println("CheckOutControllerFromInsideController-----if---------->doPost()------->al="+al);
			}
			else
			{
				System.out.println("CheckOutControllerFromInsideController------else--------->doPost()------->al="+al+", ht.getAttribute('customeremailwhencheckout')"+ht.getAttribute("customeremailwhencheckout"));
				rd=request.getRequestDispatcher("view_pending_orders_to_customer_from_inside.jsp");	
				request.setAttribute("calculated_values", calculated_values);
				request.setAttribute("unorderedproducts", al);
				/*request.setAttribute("all_products_arraylist",al1);*/
				rd.forward(request,response);
			}
		
	}

}
