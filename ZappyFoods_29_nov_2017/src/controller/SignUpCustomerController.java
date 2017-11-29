package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Customer;
import beans.Customer_Cart;
import model.CustomerModel;


/**
 * Servlet implementation class SignUpCustomerController
 */
@WebServlet("/SignUpCustomerController")
public class SignUpCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpCustomerController() {
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
		
		
		/*------------------attributes from "customer_login.jsp for new customer registration" ----------------------*/
		String email=request.getParameter("email");
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");		
		String address1=request.getParameter("address1");
		String address2=request.getParameter("address2");
		String postalcode=request.getParameter("postalcode");
		String city=request.getParameter("city");
		String state=request.getParameter("state");		
		String mobilenumber=request.getParameter("mobilenumber");			
		String password=request.getParameter("password1");
		String password2=request.getParameter("password2");
			
		if(password.equals(password2))
		{
			Customer_Cart c1=new Customer_Cart();
			c1.setEmail(email);
			c1.setFirstname(firstname);
			c1.setLastname(lastname);
			c1.setAddress1(address1);
			c1.setAddress2(address2);
			c1.setPostalcode(postalcode);
			c1.setCity(city);
			c1.setState(state);
			c1.setMobilenumber(mobilenumber);
			c1.setPassword(password);
			
			/*------------------inserting new customer into the customertable----------------------*/
			int x=new CustomerModel().insertData(c1);
			
			if(x==1)
			{
				HttpSession ht=request.getSession();
				ht.setAttribute("customeremail",email);
				ht.setAttribute("customername", (firstname+" "+lastname));
				rd=request.getRequestDispatcher("customer_home.jsp");
				request.setAttribute("newcustomerinserted", "Congratulations data inserted");
				rd.forward(request, response);
			}
			else
			{
				rd=request.getRequestDispatcher("customer_login.jsp");
				request.setAttribute("newcustomernotinserted", "Saawdhaan..... You are Already Registered!!!!!!");
				rd.include(request, response);
			}
		}
	}

}
