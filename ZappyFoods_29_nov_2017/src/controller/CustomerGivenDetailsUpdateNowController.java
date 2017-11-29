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
 * Servlet implementation class CustomerGivenDetailsUpdateNowController
 */
@WebServlet("/CustomerGivenDetailsUpdateNowController")
public class CustomerGivenDetailsUpdateNowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerGivenDetailsUpdateNowController() {
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
		String customeremail=(String)ht.getAttribute("customeremail");
		
		if(customeremail!=null)
		{
			/*----------attributes from "customer_update_profile.jsp----------"*/
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println("id=>"+id);
			String email=request.getParameter("email");
			String firstname=request.getParameter("firstname");
			String lastname=request.getParameter("lastname");
			String address1=request.getParameter("address1");
			String address2=request.getParameter("address2");
			String postalcode=request.getParameter("postalcode");
			String city=request.getParameter("city");
			String newstate=request.getParameter("state");
			String mobilenumber=request.getParameter("mobilenumber");
			String password=request.getParameter("password");
			String oldstate=request.getParameter("oldstate");
			
			Customer_Cart cc=new Customer_Cart();
			cc.setCustomerid(id);
			cc.setEmail(email);
			cc.setFirstname(firstname);
			cc.setLastname(lastname);
			cc.setAddress1(address1);
			cc.setAddress2(address2);
			cc.setPostalcode(postalcode);
			cc.setCity(city);
			cc.setState(newstate);
			cc.setMobilenumber(mobilenumber);
			cc.setPassword(password);
			cc.setOldstate(oldstate);
			
			Customer_Cart ccc=new CustomerModel().getDetails(customeremail);
			
			/*--------------------------updating details of customer-----------------------------*/
			int x=new CustomerModel().updateDetails(cc,customeremail);
			if(x!=0)
			{
				rd=request.getRequestDispatcher("customer_home.jsp");
				request.setAttribute("customer_details_updated", "Your profile have been updated Successfully ");				
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("customer_home.jsp");
				request.setAttribute("customer_details_not_updated", "Your profile not updated!!!!!!!");				
				rd.include(request, response);
			}
			
		}
		else
		{response.sendRedirect("customer_login.jsp");}
	}

}
