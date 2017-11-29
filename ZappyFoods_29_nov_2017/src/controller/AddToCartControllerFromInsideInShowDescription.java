package controller;

import java.io.IOException;
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
 * Servlet implementation class AddToCartControllerFromInsideInShowDescription
 */
@WebServlet("/AddToCartControllerFromInsideInShowDescription")
public class AddToCartControllerFromInsideInShowDescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartControllerFromInsideInShowDescription() {
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
		String customeremail=(String)ht.getAttribute("customeremail");/*getting the customer email set at session*/
		InetAddress addr=InetAddress.getLocalHost();
		RequestDispatcher rd=null;
		
		
		String customeripaddress=addr.getHostAddress();			/*picking up the any system's ipaddress*/
		ht.setAttribute("customeripaddress",customeripaddress);	/*settting up the ipaddress to the session*/
		
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
							
											int x=acm.insertData(id,al,customeripaddress,quantity,dateonly,customeremail);/*inserting data into the carttable*/
											
											if(x==1)
											{
												ArrayList<Product> al1=new ProductModel().showDescription(id);
												ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);
												rd=request.getRequestDispatcher("show_description_from_inside.jsp");
												request.setAttribute("show_description", al1);
												request.setAttribute("calculated_values", calculated_values);
												request.setAttribute("productadded","Product has been added to your cart");
												rd.forward(request,response);
											}
											else 
											{
												ArrayList<Product> al1=new ProductModel().showDescription(id);
												ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);
												rd=request.getRequestDispatcher("show_description_from_inside.jsp");
												request.setAttribute("show_description", al1);
												request.setAttribute("calculated_values", calculated_values);
												request.setAttribute("productnotadded","Failed to add Product to your cart");
												rd.forward(request,response);
											}
											
							
							
						}
						
	
	
	}

}
