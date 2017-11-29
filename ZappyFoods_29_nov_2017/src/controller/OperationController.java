package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Product;
import model.DeleteModel;
import model.ViewProductsModel;

/**
 * Servlet implementation class OperationController
 */
@WebServlet("/OperationController")
public class OperationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationController() {
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
		int id=Integer.parseInt(request.getParameter("hidden1"));
		
		/*Attributes from "viewallproducts.jsp"*/
		String nameofoperation=request.getParameter("operation");
		
		
		/*------------------if value of button is update----------------------*/
		if(nameofoperation.equals("update"))
		{
			/*------------------forwarding to the "update.jsp" ----------------------*/
			RequestDispatcher rd=request.getRequestDispatcher("update.jsp");
			request.setAttribute("operationid",id);
			rd.forward(request, response);			
//			int i=new UpdateModel().updateData(id);			
		}
		
		
		
		/*------------------if value of button is delete----------------------*/
		if(nameofoperation.equals("delete"))
		{
//			RequestDispatcher rd=request.getRequestDispatcher("Delete");
//			request.setAttribute("operationid", id);
//			rd.forward(request, response);
			
			/*------------------detele the data from product's table----------------------*/
			int i=new DeleteModel().deleteData(id);
			if(i==1)
			{	ArrayList<Product> al=new ViewProductsModel().getData();		
			RequestDispatcher rd=request.getRequestDispatcher("viewallproducts.jsp");
			request.setAttribute("id","data deleted");
			request.setAttribute("arraylist", al);
			rd.forward(request, response);}
		}
		
	}

}
