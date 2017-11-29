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
import model.ViewOrdersHistoryModel;

/**
 * Servlet implementation class ViewOrdersHistoryController
 */
@WebServlet("/ViewOrdersHistoryController")
public class ViewOrdersHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOrdersHistoryController() {
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
		HttpSession ht=request.getSession();
		String adminid=(String)ht.getAttribute("adminid");
		RequestDispatcher rd;
		
		/*------------------retriving all order history to admin ----------------------*/
		ArrayList<Cart> al=new ViewOrdersHistoryModel().getData();
		if(al!=null) {
			rd=request.getRequestDispatcher("viewordershistory.jsp");
			request.setAttribute("viewordershistory", al);
			rd.forward(request, response);
		}
		else
		{
			rd=request.getRequestDispatcher("viewordershistory.jsp");
			request.setAttribute("viewordershistory","Sorry! no data found about loan");
			rd.include(request, response);
		}
	}

}
