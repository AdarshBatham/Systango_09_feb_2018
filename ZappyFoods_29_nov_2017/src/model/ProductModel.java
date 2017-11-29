package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Cart;
import beans.Product;
import connection.ConnectionClass;

public class ProductModel {
	public ArrayList<Product> getData() 
	{
		Connection c1=null;
		int x=0;
		ArrayList<Product> al=new ArrayList<Product>();
		try
		{
			
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from product");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				Product l1=new Product();
				l1.setId(rs.getInt("id"));
				l1.setName(rs.getString("name"));
				l1.setPrice(rs.getDouble("price"));
				l1.setWeight(rs.getDouble("weight"));
				l1.setDescription(rs.getString("description"));
				l1.setImage(rs.getString("image"));
				al.add(l1);
			}
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		return al;
	}
	
	
	
	
	
	
	
	
	public ArrayList<Cart> getData(String customeremail) 
	{
		Connection c1=null;
		int x=0;
		int status=0;
		ArrayList<Cart> al=new ArrayList<Cart>();
		try
		{
			
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from carttable where customeremail=? and status=?");
			ps.setString(1, customeremail);
			ps.setInt(2, status);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				Cart l1=new Cart();
				
				l1.setId(rs.getInt("id"));
				l1.setName(rs.getString("name"));
				l1.setPrice(rs.getDouble("price"));
				l1.setWeight(rs.getDouble("weight"));
				l1.setDescription(rs.getString("description"));
				l1.setImage(rs.getString("image"));
				l1.setQuantity(rs.getInt("quantity"));
				l1.setDateonly(rs.getString("dateonly"));
				l1.setStatus(rs.getInt("status"));
			
				al.add(l1);
				
			}
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		return al;
	}
	
	
	
	/*------------------calculating the quantity and total price of products from carttable----------------------*/
	public ArrayList<Cart> calculateValues(String customeripaddress) 
	{
		Connection c1=null;
		int x=0;
		ArrayList<Cart> al=new ArrayList<Cart>();
		try
		{
			
			c1=new ConnectionClass().start();
//			PreparedStatement ps=c1.prepareStatement("select sum(quantity) as sumquantity, (sum(price)*sum(quantity)) as totalprice from carttable where customeremail=?");
			
			/*------------------calculating the quantity and total price of products from carttable which is to be displayed when product is added to the cart----------------------*/
			PreparedStatement ps=c1.prepareStatement("SELECT sum(price*quantity) as totalprice,sum(quantity) as sumquantity from carttable where ipaddress=?");
			
			
			ps.setString(1,customeripaddress);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				Cart cc=new Cart();
				cc.setSumquantity(rs.getInt("sumquantity"));
				cc.setTotalprice(rs.getDouble("totalprice"));
				
				al.add(cc);
			}
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		return al;
	}
	
	
	
	
	
	
	/*------------------showing individual product's description ----------------------*/
		public ArrayList<Product> showDescription(int id) 
		{
			Connection c1=null;
			int x=0;
			ArrayList<Product> al=new ArrayList<Product>();
			try
			{
				
				c1=new ConnectionClass().start();
				PreparedStatement ps=c1.prepareStatement("select * from product where id=?");
				ps.setInt(1,id);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())
				{
					Product l1=new Product();
					l1.setId(rs.getInt("id"));
					l1.setName(rs.getString("name"));
					l1.setPrice(rs.getDouble("price"));
					l1.setWeight(rs.getDouble("weight"));
					l1.setDescription(rs.getString("description"));
					l1.setImage(rs.getString("image"));
					al.add(l1);
				}
				
			}
			catch(Exception e)
			{e.printStackTrace();}
			return al;
		}
		
	
}
