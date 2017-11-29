package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
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
import beans.Product;
import model.CustomerModel;
import model.ProductModel;

/**
 * Servlet implementation class AddToCartController
 */
@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartController() {
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
		
		RequestDispatcher rd=null;
		HttpSession ht=request.getSession();
		String customeremail=(String)ht.getAttribute("customeremail");/*getting the customer email set at session*/
		InetAddress addr=InetAddress.getLocalHost();
		
		String customeripaddress=addr.getHostAddress();/*picking up the any system's ipaddress*/
		ht.setAttribute("customeripaddress",customeripaddress);/*settting up the ipaddress to the session*/
		
		int id=Integer.parseInt(request.getParameter("hidden1"));/*getting the id from the  "view_all_products.jsp"*/
		int quantity=Integer.parseInt(request.getParameter("productquantity"));/*getting the quantity from view_all_products.jsp*/
		
		Calendar c1=Calendar.getInstance();/*picking up the system's date*/
		int date1=c1.get(Calendar.DATE);
		int month=c1.get(Calendar.MONTH);
		int year=c1.get(Calendar.YEAR);
		String dateonly=date1+"/"+month+"/"+year;
		
		
		
						CustomerModel acm=new CustomerModel();						
						ArrayList<Product> al=(ArrayList<Product>)acm.fetchData(id);/*fetching the data of products corresponding to "id" of product*/
						
						if(quantity!=0)
						{
							/*if(customeremail!=null)
							{*/
											int x=acm.insertData(id,al,customeripaddress,quantity,dateonly,customeremail);/*inserting data into the carttable*/
											System.out.println("AddToCartController-------->acm.insertData(al,customeripaddress,quantity,dateonly)");
											
											if(x==1)
											{
												rd=request.getRequestDispatcher("view_all_products.jsp");/*forwarded to "view_all_products.jsp"  with the data shown below:===="calculated_values","m2","all_products_arraylist"*/
												ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);			
												request.setAttribute("calculated_values", calculated_values);												
												request.setAttribute("m2","your product has been added to cart");
												ArrayList<Product> al1=new ProductModel().getData();
												request.setAttribute("all_products_arraylist", al1);
												rd.forward(request,response);
											}
											else
											{
												rd=request.getRequestDispatcher("view_all_products.jsp");	/*forwarded to "view_all_products.jsp"  with the data shown below:===="calculated_values","m2","all_products_arraylist"*/											
												String ii=request.getRemoteAddr();
												ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);/*calculating the products in the carttable corresponding to customer's emailid*/		
												request.setAttribute("calculated_values", calculated_values);
												
												request.setAttribute("m2","not able add your product to cart");
												ArrayList<Product> al1=new ProductModel().getData();
												request.setAttribute("all_products_arraylist", al1);
												rd.include(request,response);
											}
											
							/*}
							else
							{
								response.sendRedirect("customer_login.jsp"); when session destroys send back to "customer_login.jsp"
							}*/
							
						}
						else
						{
							rd=request.getRequestDispatcher("view_all_products.jsp");
							request.setAttribute("invalidquantity","not able add your product,quantity must be greater than 0");/*if quantity less than 0*/
							
							String ii=request.getRemoteAddr();
							ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);			
							request.setAttribute("calculated_values", calculated_values);
							
							ArrayList<Product> al1=new ProductModel().getData();
							request.setAttribute("all_products_arraylist", al1);
							rd.include(request,response);
							
						}

	}

}
