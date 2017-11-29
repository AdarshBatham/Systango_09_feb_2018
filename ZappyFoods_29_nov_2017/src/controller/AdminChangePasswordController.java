package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminChangePasswordModel;

/**
 * Servlet implementation class AdminChangePasswordController
 */
@WebServlet("/AdminChangePasswordController")
public class AdminChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminChangePasswordController() {
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
		
		/*getting the parameters from "adminchangepassword.jsp"*/
		String oldpassword=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");
		String confirmpassword=request.getParameter("confirmpassword");
		
		
		/*getting adminid set at the time of login*/
		HttpSession ht=request.getSession();		
		String adminid=(String)ht.getAttribute("adminid");
		
		/*message printing for testing purpose*/
		System.out.println("AdminChangePasswordController->=");
		
		
		RequestDispatcher rd=null;
		int x=0;
		
		/*when both the passwords will be equal then only the "changePassword(adminid,confirmpassword) method will be called"*/
		if(newpassword.equals(confirmpassword))
			x=new AdminChangePasswordModel().changePassword(adminid,confirmpassword);
		
		/*successfully password changed*/
		if(x==1)
		{
			rd=request.getRequestDispatcher("adminhome.jsp");
			request.setAttribute("changingadminpassword", "password changed");
			rd.forward(request, response);
		}
		
		/* password not changed*/
		else
		{
			rd=request.getRequestDispatcher("customerhome.jsp");
			request.setAttribute("changingadminpassword", "not able to change password");
			rd.include(request, response);
		}
	}

}
