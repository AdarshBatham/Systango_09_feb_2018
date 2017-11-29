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
 * Servlet implementation class ShowCartControllerFromInside
 */
@WebServlet("/ShowCartControllerFromInside")
public class ShowCartControllerFromInside extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCartControllerFromInside() {
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
		String customeremailwhencheckout=(String)ht.getAttribute("customeremailwhencheckout");
		if(customeremail!=null || customeremailwhencheckout!=null)
		{
			System.out.println("ShowCartControllerFreomInside   challlllaaaaaaaaaaa......");
				ArrayList<Cart> al=(ArrayList<Cart>)new CustomerModel().getCartData(customeripaddress);		
				ht.setAttribute("buyproducts",al);//  setting session because data will be inserted into the buytable at the time of chicking out, corresponding to ipaddress
				
				if(al.isEmpty()==false)		
				{					
					RequestDispatcher rd=request.getRequestDispatcher("show_cart_from_inside.jsp");			
					request.setAttribute("cartlist",al);
					ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);					
					request.setAttribute("calculated_values", calculated_values);					
				
					rd.forward(request,response);					
				}
				else 
				{
					RequestDispatcher rd=request.getRequestDispatcher("customer_home.jsp");
					/*ArrayList<Product> al1=new ProductModel().getData();			
					request.setAttribute("all_products_arraylist",al1);*/
					
					/*------------------calculating the quantity and total price of products from carttable----------------------*/
					request.setAttribute("no_data_in_cart","No Data Yet in your cart");		
					rd.include(request,response);
				}		
		}
		
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_home.jsp");				
			request.setAttribute("no_data_in_cart","No Data Yet in your cart");				
			rd.include(request,response);
		}
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		
		
		
	
	}

}
