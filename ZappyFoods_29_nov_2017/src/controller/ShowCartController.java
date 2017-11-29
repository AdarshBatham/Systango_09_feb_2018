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
 * Servlet implementation class ShowCartController
 */
@WebServlet("/ShowCartController")
public class ShowCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCartController() {
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
		String customeripaddress=(String)ht.getAttribute("customeripaddress");
		String customeremailwhencheckout=(String)ht.getAttribute("customeremailwhencheckout");
		ArrayList<Cart> al=(ArrayList<Cart>)new CustomerModel().getCartData(customeripaddress);	
		
		if(customeremail!=null || customeremailwhencheckout!=null)
		{
	
					
				ht.setAttribute("buyproducts",al);//  setting session because data will be inserted into the buytable at the time of chicking out, corresponding to ipaddress
				
				if(al.isEmpty()==false)		
				{					
					RequestDispatcher rd=request.getRequestDispatcher("showcart.jsp");			
					request.setAttribute("cartlist",al);			
					rd.forward(request,response);					
				}
				else if(al.isEmpty())
				{
					RequestDispatcher rd=request.getRequestDispatcher("view_all_products.jsp");
					ArrayList<Product> al1=new ProductModel().getData();			
					request.setAttribute("all_products_arraylist",al1);
					
					/*------------------calculating the quantity and total price of products from carttable----------------------*/
					ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);					
					request.setAttribute("calculated_values", calculated_values);					
					request.setAttribute("no_data_in_cart","No Data remains in your cart");		
					rd.include(request,response);
				}		
		}
		
		else
		{
			/*RequestDispatcher rd=request.getRequestDispatcher("view_all_products.jsp");
			ArrayList<Product> al1=new ProductModel().getData();	
			------------------calculating the quantity and total price of products from carttable----------------------
			ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);					
			request.setAttribute("calculated_values", calculated_values);		
			request.setAttribute("all_products_arraylist",al1);				
			rd.forward(request,response);*/
			
			if(al.isEmpty())	
			{
				RequestDispatcher rd=request.getRequestDispatcher("view_all_products.jsp");
				ArrayList<Product> al1=new ProductModel().getData();			
				request.setAttribute("all_products_arraylist",al1);
				request.setAttribute("no_data_in_cart","No Data remains in your cart");		
				rd.include(request,response);
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("showcart.jsp");			
				request.setAttribute("cartlist",al);
				ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);					
				request.setAttribute("calculated_values", calculated_values);					
			
				rd.forward(request,response);
			}
		}
		
		
		
		
		
		
		
	}

}
