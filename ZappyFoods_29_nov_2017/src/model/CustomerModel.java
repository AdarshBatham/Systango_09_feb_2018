package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import beans.Cart;
import beans.Customer_Cart;
import beans.Product;
import connection.ConnectionClass;

public class CustomerModel {
	Connection c1=null;
	Customer_Cart cc=null;
	/*account verification */
	public int check(String customeremail,String password)
	{
		int x=0;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from customertable where email=? and password=?");
			ps.setString(1, customeremail);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
				x=1;
		}
		catch(Exception e)
		{e.printStackTrace();}
		return x;
	}
	
	
	
	public Customer_Cart getNameAfterSuccessfullCustomerLogin(String customeremail)
	{
		try
		{
			
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select firstname,lastname from customertable where email=?");
			ps.setString(1, customeremail);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				cc=new Customer_Cart();
				cc.setFirstname(rs.getString("firstname"));
				cc.setLastname(rs.getString("lastname"));
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
			return cc;
	}
	
	
	
	
	
	
	
	/*change password method for customer*/
	public int changePassword(String customeremail,String confirmpassword)
	{
		
		int x=0;
		Connection c1=null;
		PreparedStatement ps=null;
		try
		{
						
			c1=new ConnectionClass().start();
			
					ps=c1.prepareStatement("update customertable set password=? where email=?");
					ps.setString(1,confirmpassword);
					ps.setString(2,customeremail);
					
				
			x=ps.executeUpdate();
			if(x!=0)
			{
				sendMail(customeremail, confirmpassword);
				
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		return x;
	}
	
	
	
	
	
	/*Fetching Customer's details for update, because these will be shown by default in the fields according to his/her requirements he/she will able to make changes*/
	public Customer_Cart getDetails(String customeremail)
	{
		try
		{			
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from customertable where email=?");
			ps.setString(1,customeremail);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				cc=new Customer_Cart();
				cc.setId(rs.getInt("id"));
				cc.setEmail(rs.getString("email"));
				cc.setFirstname(rs.getString("firstname"));
				cc.setLastname(rs.getString("lastname"));
				cc.setAddress1(rs.getString("address1"));
				cc.setAddress2(rs.getString("address2"));
				cc.setPostalcode(rs.getString("postalcode"));
				cc.setCity(rs.getString("city"));
				cc.setState(rs.getString("state"));
				cc.setMobilenumber(rs.getString("mobilenumber"));
				cc.setPassword(rs.getString("password"));	
			}
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		return cc;
	
	}
	
	
	
	
	
	
	/*method to update the details of customer, provided by previous upside written method*/
	public int updateDetails(Customer_Cart cc,String customeremail)
	{
		int x=0;
		Connection c1=null;
		try
		{
			
			c1=new ConnectionClass().start();
								
					PreparedStatement ps=c1.prepareStatement("update customertable set email=?,firstname=?,lastname=?,address1=?,address2=?,postalcode=?,city=?,state=?,mobilenumber=?,password=? where id=? and email=?");
					
					ps.setString(1,cc.getEmail());
					ps.setString(2, cc.getFirstname());
					ps.setString(3, cc.getLastname());
					ps.setString(4, cc.getAddress1());
					ps.setString(5, cc.getAddress2());
					ps.setString(6, cc.getPostalcode());
					ps.setString(7,cc.getCity());
					
					if(cc.getState().equals("SELECT STATE"))
					{
						ps.setString(8,cc.getOldstate());
					}
					else
						ps.setString(8,cc.getState());
					
					ps.setString(9,cc.getMobilenumber());
					ps.setString(10,cc.getPassword());
					ps.setInt(11,cc.getCustomerid());
					ps.setString(12,customeremail);
					x=ps.executeUpdate();
				
				
				
		}
		catch(Exception e)
		{ e.printStackTrace();}
		return x;
	}

	
	
	
	
	
	
	
	
	/*-------------fetching details from carttable-------------*/
	public ArrayList<Cart> getCartData(String customeripaddress)
	{
		Connection c1=null;
		Cart cc=null;
		ArrayList<Cart> al=new ArrayList<Cart>();
		try
		{
			
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select id,name,price,weight,description,image,ipaddress,quantity,dateonly,sum(price) as sumprice,sum(quantity) as sumquantity, sum(price) as totalprice from carttable where ipaddress=? group by name");
			ps.setString(1,customeripaddress);
			ResultSet rs=ps.executeQuery();	
		
			while(rs.next())
			{
				System.out.println("carttable mai se data wapas set huaaaaa......");
				cc=new Cart();
				
				cc.setId(rs.getInt("id"));
				cc.setName(rs.getString("name"));
				cc.setPrice(rs.getDouble("price"));
				cc.setWeight(rs.getDouble("weight"));
				cc.setDescription(rs.getString("description"));
				cc.setImage(rs.getString("image"));
				cc.setIpaddress(rs.getString("ipaddress"));
				cc.setQuantity(rs.getInt("quantity"));
				cc.setDateonly(rs.getString("dateonly"));
				cc.setSumquantity(rs.getInt("sumquantity"));
				cc.setSumprice(rs.getDouble("sumprice"));			
				cc.setTotalprice(rs.getDouble("totalprice"));

				al.add(cc);
				
			}
			
		}
		catch(Exception e)
		{e.printStackTrace();}
	System.out.println(">>>>>>>>>>>"+al);
		return al;

	}
	
	
	
	
	
	
	public ArrayList<Product> fetchData(int id)
	{
		ArrayList<Product> list=new ArrayList<Product>();
		Connection c1=null;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from product where id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				Product p1=new Product();
				p1.setId(rs.getInt("id"));
				p1.setName(rs.getString("name"));
				p1.setPrice(rs.getDouble("price"));
				p1.setWeight(rs.getDouble("weight"));
				p1.setDescription(rs.getString("description"));
				p1.setImage(rs.getString("image"));
				list.add(p1);
				
			}
		}
		catch(Exception e)
		{ e.printStackTrace();}
		
		return list;
	}
	
	
	
	
	
	
	
	/*inserting data into the carttable*/
	public int insertData(int id,ArrayList<Product> al,String ipaddress,int quantity,String dateonly,String customeremail)
	{
		int x=0;
		Connection c1=null;
		int aa=0;
		int updatedquantity=0;
		try
		{
			
			c1=new ConnectionClass().start();
				
			
				for(Product p:al)
				{
					
					PreparedStatement ps1=c1.prepareStatement("select * from carttable where id=? and customeremail=?");
					ps1.setInt(1, id);
					ps1.setString(2, customeremail);
					ResultSet rs1=ps1.executeQuery();
					while(rs1.next())
					{
						aa=1;
						updatedquantity=quantity+rs1.getInt("quantity");
					}
					if(aa==0)
					{
					//Product p=new Product();
							PreparedStatement ps=c1.prepareStatement("insert into carttable(id,name,price,weight,description,image,ipaddress,quantity,dateonly,status,customeremail) values(?,?,?,?,?,?,?,?,?,?,?)");
							
							ps.setInt(1,p.getId());
							ps.setString(2, p.getName());
							ps.setDouble(3, p.getPrice());
							ps.setDouble(4, p.getWeight());
							ps.setString(5, p.getDescription());
							ps.setString(6, p.getImage());
							ps.setString(7,ipaddress);
							ps.setInt(8, quantity);
							ps.setString(9,dateonly);
							ps.setInt(10, 0);
							ps.setString(11, customeremail);
							x=ps.executeUpdate();
					}
					else
					{
						
						PreparedStatement ps=c1.prepareStatement("update carttable set quantity=? where id=? and customeremail=?");						
						ps.setInt(1, updatedquantity);
						ps.setInt(2, p.getId());
						ps.setString(3, customeremail);
						x=ps.executeUpdate();
					}
					
				}		
			
		}
		catch(Exception e)
		{ e.printStackTrace();}
		return x;
	}
	
	
	
	/*-------------Deleting details from carttable-------------*/
	public int deleteCartData(int productid,String customeripaddress)
	{
		Connection c1=null;
		int x=0;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("delete from carttable where id=? and ipaddress=?");
			ps.setInt(1,productid);
			ps.setString(2,customeripaddress);
			x=ps.executeUpdate();
			ps.close();
			c1.close();
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		return x;
	}
	
	
	/*-------------Deleting details from carttable when logout-------------*/
	public int deleteCartDataWhenLogOut(String customeripaddress)
	{
		Connection c1=null;
		int x=0;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("delete from carttable where ipaddress=?");			
			ps.setString(1,customeripaddress);
			x=ps.executeUpdate();
			System.out.println("CustomerModel---->deleteCartDataWhenLogOut---->x="+x);
			ps.close();
			c1.close();
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		return x;
	}
	
	
	
	
	public int operation(String customeremail,int id_from_carttable,String hidden_field_ipaddress)
	{
		int x=0;
		int y=0;
		Connection c1=null;
		int z=0;
		Cart cc=null;
		int temp=0;
		double productprice=0.0;
		String productname=null;
		double productweight=0.0;
		String productdescription=null;
		String productdateonly=null;
		int productsumquantity=0;
		double productsumprice=0.0;
		String productcustomeremail=null;
		String productipaddress=null;
		try
		{
			int status=0;
			c1=new ConnectionClass().start();
			
			PreparedStatement ps=c1.prepareStatement("select id,name,price,weight,description,image,ipaddress,quantity,dateonly,sum(price) as sumprice,sum(quantity) as sumquantity, sum(price) as totalprice from carttable where ipaddress=?and id=? group by name");
			ps.setString(1,hidden_field_ipaddress);
			ps.setInt(2, id_from_carttable);
			ResultSet rs=ps.executeQuery();
			
		

			while(rs.next())
			{
				cc=new Cart();
				
				cc.setId(rs.getInt("id"));
				cc.setName(rs.getString("name"));
				cc.setPrice(rs.getDouble("price"));
				cc.setWeight(rs.getDouble("weight"));
				cc.setDescription(rs.getString("description"));
				cc.setImage(rs.getString("image"));
				cc.setIpaddress(rs.getString("ipaddress"));
				cc.setQuantity(rs.getInt("quantity"));
				cc.setDateonly(rs.getString("dateonly"));
				cc.setSumquantity(rs.getInt("sumquantity"));
				cc.setSumprice(rs.getDouble("sumprice"));
//				cc.setStatus(rs.getInt("status"));				
				cc.setTotalprice(rs.getDouble("totalprice"));	
				
				if(temp==0)
				{
				
						PreparedStatement ps1=c1.prepareStatement("insert into ordertable(id,name,price,weight,description,image,ipaddress,dateonly,sumquantity,sumprice,status,customeremail) values(?,?,?,?,?,?,?,?,?,?,?,?)");
						ps1.setInt(1,rs.getInt("id"));
						ps1.setString(2, rs.getString("name"));						
						productname=rs.getString("name");
						
						ps1.setDouble(3, rs.getDouble("price"));						
						 productprice=rs.getDouble("price");
						
						ps1.setDouble(4, rs.getDouble("weight"));
						 productweight=rs.getDouble("weight");
						
						ps1.setString(5, rs.getString("description"));
						 productdescription=rs.getString("description");
						
						ps1.setString(6, rs.getString("image"));
						ps1.setString(7,hidden_field_ipaddress);	
						productipaddress=hidden_field_ipaddress;
						
						ps1.setString(8,rs.getString("dateonly"));
						 productdateonly=rs.getString("dateonly");
						
						ps1.setInt(9,rs.getInt("sumquantity"));
						 productsumquantity=rs.getInt("sumquantity");
						
						ps1.setDouble(10,rs.getDouble("sumprice"));
						 productsumprice=rs.getDouble("sumprice");
						
						ps1.setInt(11, 1);
						ps1.setString(12, customeremail);
						 productcustomeremail=customeremail;
						
						PreparedStatement ps2=c1.prepareStatement("delete from carttable where ipaddress=? and id=?");
						ps2.setString(1, hidden_field_ipaddress);
						ps2.setInt(2, id_from_carttable);
						c1.setAutoCommit(false);
						
						x=ps1.executeUpdate();
						y=ps2.executeUpdate();
						
						System.out.println("xxxxxxxxxxxx======="+x);
						System.out.println("yyyyyyyyyyyy======="+y);
						System.out.println("CustomerModel->   operation()----->x="+x+", y="+y);
						System.out.println();
						c1.commit();
						temp=1;
				}
//				al.add(cc);
			}
			
			
			c1.commit();
				
				if(x!=0 && y!=0)
				{
					z=1;
					sendMailForOrderedProducts(productprice,productname,productweight,productdescription,productdateonly,productsumquantity,productsumprice,productcustomeremail);
				}
				
				
//			}			
		}
		catch(Exception e)
		{ e.printStackTrace();}
		return z;	
	}
	
	
	
/*----------------------------View dispatched products to customer--------------------------------*/
	public ArrayList<Cart> getData(String customeremail)
	{
		ArrayList<Cart> al=new ArrayList<Cart>();
		Connection c1=null;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from ordertable where customeremail=? and status=?");
			ps.setString(1, customeremail);
			ps.setInt(2, 2);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Cart cc=new Cart();
				cc.setId(rs.getInt("id"));
				cc.setName(rs.getString("name"));
				cc.setPrice(rs.getDouble("price"));
				cc.setWeight(rs.getDouble("weight"));
				cc.setDescription(rs.getString("description"));
				cc.setImage(rs.getString("image"));
				cc.setIpaddress(rs.getString("ipaddress"));
				cc.setDateonly(rs.getString("dateonly"));
				cc.setSumquantity(rs.getInt("sumquantity"));
				cc.setSumprice(rs.getInt("sumprice"));
				cc.setStatus(rs.getInt("status"));
				cc.setCustomeremail(rs.getString("customeremail"));
				al.add(cc);
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
		return al;
	}

	
	
	
	
	/*-------------------------View my orders(Customer will able to see his own orders)------------------------------*/
	
	public ArrayList<Cart> getAllData(String customeremail)
	{
		ArrayList<Cart> al=new ArrayList<Cart>();
		Connection c1=null;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from ordertable where customeremail=?");
			ps.setString(1, customeremail);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Cart cc=new Cart();
				cc.setId(rs.getInt("id"));
				cc.setName(rs.getString("name"));
				cc.setPrice(rs.getDouble("price"));
				cc.setWeight(rs.getDouble("weight"));
				cc.setDescription(rs.getString("description"));
				cc.setImage(rs.getString("image"));
				cc.setIpaddress(rs.getString("ipaddress"));
				cc.setDateonly(rs.getString("dateonly"));
				cc.setSumquantity(rs.getInt("sumquantity"));
				cc.setSumprice(rs.getInt("sumprice"));
				cc.setStatus(rs.getInt("status"));
				cc.setCustomeremail(rs.getString("customeremail"));
				cc.setUniqueid(rs.getInt("uniqueid"));
				al.add(cc);
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
		return al;
	}
	
	
	
	/*--------------------------customer will cancel the products according to his/her requirements-------------------------------------*/
	public int cancelOrderNow(String customeremail,int uniqueid,int id_from_ordertable)
	{
		int x=0;
		Connection c1=null;
		Cart cc=null;
		ArrayList<Cart> al=new ArrayList<Cart>();
		try
		{
			
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("update ordertable set status=? where id=? and uniqueid=? and customeremail=?");
			ps.setInt(1, 3);
			ps.setInt(2,id_from_ordertable);
			ps.setInt(3,uniqueid);
			ps.setString(4,customeremail);
			x=ps.executeUpdate();
			/*System.out.println("CustomerModel ------> cancelOrderNow()=======>   customeremail="+customeremail);*/		
		}
		catch(Exception e)
		{e.printStackTrace();}
		return x;
	}
	
	
	/*new customer account creation*/
	public int insertData(Customer_Cart cc)
	{
		int x=0;
		Connection c1=null;
		try
		{		
			c1=new ConnectionClass().start();
		
				//Product p=new Product();
				PreparedStatement ps=c1.prepareStatement("insert into customertable(email,firstname,lastname,address1,address2,postalcode,city,state,mobilenumber,password) values(?,?,?,?,?,?,?,?,?,?)");
				
				ps.setString(1, cc.getEmail());
				ps.setString(2, cc.getFirstname());
				ps.setString(3, cc.getLastname());
				ps.setString(4, cc.getAddress1());
				ps.setString(5, cc.getAddress2());
				ps.setString(6, cc.getPostalcode());
				ps.setString(7, cc.getCity());
				ps.setString(8, cc.getState());
				ps.setString(9, cc.getMobilenumber());
				ps.setString(10, cc.getPassword());
				
				x=ps.executeUpdate();
				sendMailForNewAccountCreation(cc.getEmail(),cc.getPassword());
				
						
		}
		catch(Exception e)
		{ e.printStackTrace();}
		return x;
	}

	public void sendMailForNewAccountCreation(String user_email,String user_password)
	{
		String to = user_email;//request.getParameter("id");//change accordingly
	      String sub="ZappyFoods New Account Creation userid & Password";
	      
	      
//	      long p=System.currentTimeMillis();//439807598430759083
//	      String pwd=(p+"").substring(7);
	      
	      
//	      String msg="Welcome at out site and your user id="+userid1+", password="+password1+", designation="+designation1;
	      // Sender's email ID needs to be mentioned
	      String msg="Congratulation account has been created successfully with userid="+user_email+"& password="+user_password;
	      String from = "adarshbatham@gmail.com";
	      final String username = "adarshbatham@gmail.com";//change accordingly
	      final String password ="90743Ada@14402aa";//change accordingly
	     // final String designation=designation1;

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject(sub);

	         // Now set the actual message
	         message.setText(msg);

	         // Send message
	         
	         Transport.send(message);

	        System.out.println("Sent message successfully....");
	         } catch (MessagingException e) {
	    	  e.printStackTrace();
	    	     }

	}
	
	/*-----------------------------------------Send Mail when customer password changes-----------------------------------------------*/
	public void sendMail(String customeremail,String confirmpassword)
	{
		String to = customeremail;//request.getParameter("id");//change accordingly
	      String sub="ZappyFoods New Password";
	      
	      
//	      long p=System.currentTimeMillis();//439807598430759083
//	      String pwd=(p+"").substring(7);
	      
	      
//	      String msg="Welcome at out site and your user id="+userid1+", password="+password1+", designation="+designation1;
	      // Sender's email ID needs to be mentioned
	      String msg="Congratulation password has been changed successfully with  password="+confirmpassword;
	      String from = "adarshbatham@gmail.com";
	      final String username = "adarshbatham@gmail.com";//change accordingly
	      final String password ="90743Ada@14402aa";//change accordingly
	     // final String designation=designation1;

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject(sub);

	         // Now set the actual message
	         message.setText(msg);

	         // Send message
	         
	         Transport.send(message);

	        System.out.println("Sent message successfully....");
	         } catch (MessagingException e) {
	    	  e.printStackTrace();
	    	     }

	}
	
	
	
	
	
	public int updateQuantityOnly(int productid,String customeripaddress,int updatedquantity)
	{
		int x=0;
		
		
		Connection c1=null;
		Cart cc=null;
		int updatedquantity_new=0;
		ArrayList<Cart> al=new ArrayList<Cart>();
		try
		{
			
			
			System.out.println("method me");
			c1=new ConnectionClass().start();
			PreparedStatement ps1=c1.prepareStatement("select * from carttable where id=? and ipaddress=?");
			
			ps1.setInt(1,productid);
			ps1.setString(2,customeripaddress);
			ResultSet rs1=ps1.executeQuery();
			
			System.out.println("quary execute");
			
			while(rs1.next())
			{
				updatedquantity_new=updatedquantity+rs1.getInt("quantity");
				
			}
			
			
			
			PreparedStatement ps=c1.prepareStatement("update carttable set quantity=? where id=? and ipaddress=?");
			ps.setInt(1,updatedquantity);
			ps.setInt(2,productid);
			ps.setString(3,customeripaddress);
			x=ps.executeUpdate();
					
		}
		catch(Exception e)
		{e.printStackTrace();
			System.out.println(e);
		}
		
		return x;
	}
	
	
	
	
	
	
	
	
	
	public void sendMailForOrderedProducts(double productprice,String productname,double productweight,String productdescription,String productdateonly,int productsumquantity,double productsumprice,String productcustomeremail)
	{
		String to = productcustomeremail;//request.getParameter("id");//change accordingly
	      String sub="ZappyFoods Order Details";
	      
	      
//	      long p=System.currentTimeMillis();//439807598430759083
//	      String pwd=(p+"").substring(7);
	      
	      
//	      String msg="Welcome at out site and your user id="+userid1+", password="+password1+", designation="+designation1;
	      // Sender's email ID needs to be mentioned
	      String msg="Congratulation  you order has been placed successfully with Price (INR)="+productprice+", Name="+productname+", Weight(gms.)="+productweight+", Description="+productdescription+", Date Only="+productdateonly+", Sum Quantity="+productsumquantity+", ToTal Price(INR)="+productsumprice;
	      String from = "adarshbatham@gmail.com";
	      final String username = "adarshbatham@gmail.com";//change accordingly
	      final String password ="90743Ada@14402aa";//change accordingly
	     // final String designation=designation1;

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject(sub);

	         // Now set the actual message
	         message.setText(msg);

	         // Send message
	         
	         Transport.send(message);

	        System.out.println("Sent message successfully....");
	         } catch (MessagingException e) {
	    	  e.printStackTrace();
	    	     }

	}
	
	
	
	
	
	public int checkExistanceOfCustomer(String email)
	{
		int x=0;
			try
			{
				Connection c1= new ConnectionClass().start();
				PreparedStatement ps=c1.prepareStatement("select * from customertable where email=?");
				ps.setString(1,email);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					x=1;
				}
			}
			catch(Exception e)
			{e.printStackTrace();}
		return x;
	}
	
	
	
	
	
//------------------------checking existence of email when user updating his/her own profile-------------------------------	
	
	
	public int checkEmail(int id,String customeremail_session,String email)
	{
		int x=0;
		String mobilenumber=null;
		String insideemail=null;
		int insideid=0;
			try
			{
				Connection c1= new ConnectionClass().start();
				PreparedStatement ps=c1.prepareStatement("select * from customertable where email=?");
				ps.setString(1,email);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					x=1;
					insideid=rs.getInt("id");
					mobilenumber=rs.getString("mobilenumber");
					insideemail=rs.getString("email");
				}
				System.out.println("Modelllllllllllllll-------------->insideemail="+insideemail);
				if(insideemail.equals(email) && id==insideid)
				{
					
					x=0;
				}
				else
					x=1;
				
			}
			catch(Exception e)
			{e.printStackTrace();}
		return x;
	}
	
}
