package controller;

import java.io.IOException;
import java.net.InetAddress;
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
 * Servlet implementation class DeleteCartController
 */
@WebServlet("/DeleteCartController")
public class DeleteCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCartController() {
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
		try
		{
			HttpSession ht=request.getSession();
			String customeremail=(String)ht.getAttribute("customeremail");
			
			InetAddress addr=InetAddress.getLocalHost();			
			String customeripaddress=addr.getHostAddress();
			
			
			
			
			
			RequestDispatcher rd;
			int productid=Integer.parseInt(request.getParameter("productid"));
			
			/*-------------Deleting details from carttable-------------*/
			int y=new CustomerModel().deleteCartData(productid,customeripaddress);
			if(y==1)
			{
				rd=request.getRequestDispatcher("showcart.jsp");
				/*-------------Fetching details from carttable-------------*/
				ArrayList<Cart> al=new CustomerModel().getCartData(customeripaddress);
				request.setAttribute("cartlist", al);
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("ShowCartController");
				
				rd.include(request, response);
			}
			
		}
		catch(Exception e)
		{e.printStackTrace();}
	}

}
