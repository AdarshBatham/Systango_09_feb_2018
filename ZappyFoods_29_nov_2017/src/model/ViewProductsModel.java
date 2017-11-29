package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Cart;
import beans.Customer;
import beans.Product;
import connection.ConnectionClass;

public class ViewProductsModel {
	/*------------------showing all products ----------------------*/
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
	
	
	
	
	
	public ArrayList<Cart> getData(String ipaddressfromindex) 
	{
		Connection c1=null;
		int x=0;
		int status=0;
		ArrayList<Cart> al=new ArrayList<Cart>();
		try
		{
			
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from carttable where ipaddress=? and status=?");
			ps.setString(1, ipaddressfromindex);
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
//				l1.setSumquantity(rs.getInt("sumquantity"));
//				l1.setSumprice(rs.getDouble("sumprice"));
				l1.setStatus(rs.getInt("status"));
			
				al.add(l1);
				
			}
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		return al;
	}
	
	
	
	
	
	public ArrayList<Cart> calculateValues(String ipaddress) 
	{
		Connection c1=null;
		int x=0;
		ArrayList<Cart> al=new ArrayList<Cart>();
		try
		{
			
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select sum(quantity) as sumquantity, (price*sum(quantity)) as totalprice from carttable where ipaddress=?");
			ps.setString(1,ipaddress);
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
	
	
	
	
	
	
	
	//-------------------Description Ke Liye----------------------
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
