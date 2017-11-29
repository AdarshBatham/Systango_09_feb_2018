package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Customer_Cart;
import model.CustomerModel;


/**
 * Servlet implementation class CustomerUpdateProfileController
 */
@WebServlet("/CustomerUpdateProfileController")
public class CustomerUpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdateProfileController() {
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
RequestDispatcher rd;
		
		HttpSession ht=request.getSession();
		String customeremail=(String)ht.getAttribute("customeremail");
		
		if(customeremail!=null)
		{
			/*-----------------fetching Customer details which is to be displayed in the fields and according to this customer can update tem-------------------------*/
			Customer_Cart cc=new CustomerModel().getDetails(customeremail);
			if(cc!=null) {
				rd=request.getRequestDispatcher("customer_update_profile.jsp");
				request.setAttribute("customer_details_for_updatation",cc );
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("customer_update_profile.jsp");
				request.setAttribute("customer_details_not_found","Sorry! no data found about loan");
				rd.include(request, response);
			}
		}
		else
			response.sendRedirect("customer_login.jsp");
	}

}
