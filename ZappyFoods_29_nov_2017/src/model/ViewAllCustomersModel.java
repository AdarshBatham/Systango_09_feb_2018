package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Customer;
import beans.Customer_Cart;
import beans.Product;
import connection.ConnectionClass;

public class ViewAllCustomersModel {
	
	
	
	/*------------------retriving all customers from customertable----------------------*/
	public ArrayList<Customer_Cart> getData() 
	{
		Connection c1=null;
		int x=0;
		ArrayList<Customer_Cart> al=new ArrayList<Customer_Cart>();
		try
		{
			
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from customertable");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				Customer_Cart cc=new Customer_Cart();
				
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
			
				al.add(cc);
			}
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		return al;
	}
}
