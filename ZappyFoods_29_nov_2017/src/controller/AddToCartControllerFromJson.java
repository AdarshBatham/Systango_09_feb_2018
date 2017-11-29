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

import com.google.gson.Gson;

import beans.Cart;
import beans.Product;
import model.CustomerModel;
import model.ProductModel;

/**
 * Servlet implementation class AddToCartControllerFromJson
 */
@WebServlet("/AddToCartControllerFromJson")
public class AddToCartControllerFromJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartControllerFromJson() {
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
						ArrayList<Product> al1=new ProductModel().getData();
						int x=acm.insertData(id,al,customeripaddress,quantity,dateonly,customeremail);/*inserting data into the carttable*/
						
						if(x!=0)
						{
						
								String json=new Gson().toJson(al1);
								PrintWriter pw=response.getWriter();
								pw.println(json);
								request.setAttribute("m2","your product has been added to cart");
								
								if(json!=null)
								{
									ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);
									rd=request.getRequestDispatcher("search_your_willing_product.jsp");
									request.setAttribute("calculated_values", calculated_values);
									request.setAttribute("data",json);
									rd.forward(request, response);
								}
						}
					
	}

}
