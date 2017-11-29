package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Cart;
import model.DispatchModel;
import model.ViewOrdersHistoryModel;
import model.ViewTodaysOrderModel;

/**
 * Servlet implementation class DispatchController
 */
@WebServlet("/DispatchController")
public class DispatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatchController() {
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
		
		Calendar c1=Calendar.getInstance();
		int date1=c1.get(Calendar.DATE);
		int month=c1.get(Calendar.MONTH);
		int year=c1.get(Calendar.YEAR);
		String dateonly=date1+"/"+month+"/"+year;
		
		/*-------------Attributes from "viewtodaysorder.jsp"-------------*/
		int id=Integer.parseInt(request.getParameter("id"));		
		String customeremail=request.getParameter("customeremail");
		int uniqueid=Integer.parseInt(request.getParameter("uniqueid"));
		
		/*-------------Admin is dispatching the products from ordertable to customer corresponding to customer email-------------*/
		int x=new DispatchModel().dispatchNow(id,uniqueid,customeremail);
		
		if(x!=0)
		{
			
			ArrayList<Cart> al=new ViewTodaysOrderModel().getData(dateonly);
			rd=request.getRequestDispatcher("viewtodaysorder.jsp");
			request.setAttribute("orderlist", al);
			rd.forward(request, response);
		}
	}

}
