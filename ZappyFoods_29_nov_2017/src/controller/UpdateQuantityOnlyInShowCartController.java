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
import model.DispatchModel;
import model.ProductModel;

/**
 * Servlet implementation class UpdateQuantityOnlyController
 */
@WebServlet(name = "UpdateQuantityOnlyInShowCartController", urlPatterns = { "/UpdateQuantityOnlyInShowCartController" })
public class UpdateQuantityOnlyInShowCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQuantityOnlyInShowCartController() {
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
			String customeripaddress=(String)ht.getAttribute("customeripaddress");
			int updatedquantity=Integer.parseInt(request.getParameter("updatedquantity"));		
			int productid=Integer.parseInt(request.getParameter("productid"));
			String customeremail=request.getParameter("customeremail");
			
			
			int x=new CustomerModel().updateQuantityOnly(productid,customeripaddress,updatedquantity);
			System.out.println("controller mai x="+x);
			if(x!=0)
			{
				response.sendRedirect("ShowCartController");			
				
			}
			
		}
		catch(Exception e)
		{e.printStackTrace();
		System.out.println(e);}
	}

}
