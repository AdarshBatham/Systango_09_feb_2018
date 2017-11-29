package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import beans.Product;
import model.ViewProductsModel;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private String filePath;
	   private int maxFileSize = 1000 * 4096;
	   private int maxMemSize = 100 * 4096;
	   private File file ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
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
	
		
	
//		String name=request.getParameter("name");
//		double price=Double.parseDouble(request.getParameter("price"));
//		double weight=Double.parseDouble(request.getParameter("weight"));
//		String description=request.getParameter("description");
//		String image=request.getParameter("image");
	response.setContentType("text/html");
	  PrintWriter out = response.getWriter();
	  boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	  if( !isMultipart ){
 return;
}
	  
DiskFileItemFactory factory = new DiskFileItemFactory();
//maximum size that will be stored in memory
factory.setSizeThreshold(maxMemSize);
//Location to save data that is larger than maxMemSize.

//Create a new file upload handler
ServletFileUpload upload = new ServletFileUpload(factory);
//maximum file size to be uploaded.
upload.setSizeMax( maxFileSize );

try{

//Parse the request to get file items.
List fileItems = upload.parseRequest(request);

//Process the uploaded file items
Iterator i = fileItems.iterator();
int productid=0;
String productname=null;
double productprice=0.0;
String filename=null;
double weight=0.0;
String description=null;
String productid1=null;

while ( i.hasNext())
{

FileItem fi = (FileItem)i.next();
if ( fi.isFormField () )
{
// Get the uploaded file parameters
String  fieldName = fi.getFieldName();
			   if(fieldName.equals("id"))
			   {
				  productid1=fi.getString().trim();
				  productid = Integer.parseInt(productid1);
			    System.out.println(productid);
			   }
		       if(fieldName.equals("name"))
		         {
		    	  productname=fi.getString().trim();
		          System.out.println(productname);
		         }
		       if(fieldName.equals("price"))
		       {
		    	   productprice=Double.parseDouble(fi.getString().trim());
		        System.out.println(productprice);
		       }
		       
		       if(fieldName.equals("weight"))
		       {
		    	   weight=Double.parseDouble(fi.getString().trim());
		    	   System.out.println(weight);
		    	   
		       }
		       if(fieldName.equals("description"))
		       {
		    	   description=fi.getString().trim();
		    	   System.out.println(description);
		       }
		       
}
else
{    
 String fieldName = fi.getFieldName();

if(fieldName.equals("file"))
{
 ServletConfig sc=getServletConfig();
 String field=fi.getString();
 String contentType = fi.getContentType();
 filename=fi.getName();
  boolean isInMemory = fi.isInMemory();
  long sizeInBytes = fi.getSize();

  //create folder
  File f = new File(sc.getServletContext().getRealPath("/")+"images/") ;
     if(!f.exists())
    	 f.mkdir();
  // Write the file
  file = new File(sc.getServletContext().getRealPath("/")+"images/"+filename) ;
   fi.write( file ) ;
  out.println("Uploaded Filename: " +filename + "<br>");
System.out.println("PATH="+file.getPath());
}
}
}


try {
  Class.forName("com.mysql.jdbc.Driver");
  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");

  
  
  /*------------------update the details into the product's table----------------------*/
  PreparedStatement ps=con.prepareStatement("update product set name=?,price=?,weight=?,description=? where id=?");//placeholder
    ps.setString(1,productname);
    ps.setDouble(2,productprice);
    ps.setDouble(3,weight);
    ps.setString(4, description);
//    ps.setString(5,filename);
    ps.setInt(5, productid);
    
    int y=0;y=ps.executeUpdate();
    if(y!=0)
    {
    	RequestDispatcher rd=request.getRequestDispatcher("viewallproducts.jsp");
    	ArrayList<Product> al=new ViewProductsModel().getData();
		request.setAttribute("arraylist", al);
		request.setAttribute("dataupdated", "your data has been updated");
    	rd.forward(request,response);
    }
    else
    {
    	RequestDispatcher rd=request.getRequestDispatcher("viewallproducts.jsp");
    	ArrayList<Product> al=new ViewProductsModel().getData();
		request.setAttribute("arraylist", al);
		request.setAttribute("datanotupdated", "data not updated!!!!!");
    	rd.forward(request,response);
    }
    	
//    	response.sendRedirect("uploaded.jsp");
}catch(Exception e)
{
  System.out.println(e);
}
}catch(Exception ex)
{
ex.printStackTrace();
System.out.println(ex);
}
	}

}
