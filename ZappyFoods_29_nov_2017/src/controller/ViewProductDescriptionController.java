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
import model.ProductModel;


/**
 * Servlet implementation class ViewProductDescription
 */
@WebServlet("/ViewProductDescription")
public class ViewProductDescriptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProductDescriptionController() {
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
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession ht=request.getSession();
		String customeripaddress=(String)ht.getAttribute("customeripaddress");
		RequestDispatcher rd=null;
		
		/*------------------showing individual product's description ----------------------*/
		ArrayList<Product> al=new ProductModel().showDescription(id);
		System.out.println("id="+id);
		ArrayList<Cart> calculated_values=new ProductModel().calculateValues(customeripaddress);
		if(al.isEmpty())
		{
			System.out.println("List is empty");
			rd=request.getRequestDispatcher("show_description.jsp");
			request.setAttribute("show_description", "nothing to show");
			rd.include(request,response);
//			ArrayList<Product> a1=new ViewProductsModel().getData();
		}
		else
		{
			rd=request.getRequestDispatcher("show_description.jsp");			
			request.setAttribute("show_description", al);
			request.setAttribute("calculated_values", calculated_values);
			rd.forward(request,response);
		}
	}

}
