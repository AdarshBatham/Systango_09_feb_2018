package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;
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

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;
	   private int maxFileSize = 1000 * 4096;
	   private int maxMemSize = 100 * 4096;
	   private File file ;    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
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
		response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
		  boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		  RequestDispatcher rd=null;
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

String productname=null;
double productprice=0.0;
String filename=null;
double weight=0.0;
String description=null;

while ( i.hasNext())
{

 FileItem fi = (FileItem)i.next();
 if ( fi.isFormField () )
 {
    // Get the uploaded file parameters
   String  fieldName = fi.getFieldName();
			       if(fieldName.equals("name"))
			         {
			    	  productname=fi.getString();
			          System.out.println(productname);
			         }
			       if(fieldName.equals("price"))
			       {
			    	   productprice=Double.parseDouble(fi.getString());
			        System.out.println(productprice);
			       }
			       
			       if(fieldName.equals("weight"))
			       {
			    	   weight=Double.parseDouble(fi.getString());
			    	   System.out.println(weight);
			    	   
			       }
			       if(fieldName.equals("description"))
			       {
			    	   description=fi.getString();
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
	  Date d=new Date();
	  String date=d.toString();
	  
	  Calendar c1=Calendar.getInstance();
		int date1=c1.get(Calendar.DATE);
		int month=c1.get(Calendar.MONTH);
		int year=c1.get(Calendar.YEAR);
		String dateonly=date1+"/"+month+"/"+year;
		
		
		/*------------------inserting new product into the product's table----------------------*/
	  PreparedStatement ps=con.prepareStatement("insert into product(name,price,weight,description,image,date,dateonly) value(?,?,?,?,?,?,?)");//placeholder
	    ps.setString(1,productname);
	    ps.setDouble(2,productprice);
	    ps.setDouble(3,weight);
	    ps.setString(4, description);
	    ps.setString(5,filename);
	    ps.setString(6,date);
	    ps.setString(7,dateonly);
	    
	    int y=0;y=ps.executeUpdate();
	    if(y!=0)
	    {
	    	rd=request.getRequestDispatcher("upload.jsp");
	    	request.setAttribute("imageuploaded","Image Has Been Uploaded Successfully");
	    	rd.forward(request,response);
	    }
	    else
	    {
	    	rd=request.getRequestDispatcher("upload.jsp");
	    	request.setAttribute("imagenotuploaded","Image Not Uploaded!!!!!!");
	    	rd.include(request,response);
	    }
//	    	response.sendRedirect("upload.jsp");
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
