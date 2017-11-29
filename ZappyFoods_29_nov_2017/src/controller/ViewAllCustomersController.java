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

import beans.Customer_Cart;
import model.ViewAllCustomersModel;

/**
 * Servlet implementation class ViewAllCustomersController
 */
@WebServlet("/ViewAllCustomersController")
public class ViewAllCustomersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllCustomersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ht=request.getSession();
		String adminid=(String)ht.getAttribute("adminid");
		RequestDispatcher rd;
		
		/*------------------retriving all customers from customertable----------------------*/
		ArrayList<Customer_Cart> al=new ViewAllCustomersModel().getData();
		if(al!=null) {
			
			/*------------------forwarding to viewallcustomers.jsp----------------------*/
			rd=request.getRequestDispatcher("viewallcustomers.jsp");
			request.setAttribute("customerarraylist", al);
			rd.forward(request, response);
		}
		else
		{
			rd=request.getRequestDispatcher("adminhome.jsp");
			request.setAttribute("nodatafound","Sorry! no data found about loan");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
