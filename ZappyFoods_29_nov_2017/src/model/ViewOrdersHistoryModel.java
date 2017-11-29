package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Cart;
import connection.ConnectionClass;

public class ViewOrdersHistoryModel {
	
	
	/*------------------retriving all order history to admin ----------------------*/
	public ArrayList<Cart> getData()
	{
		ArrayList<Cart> al=new ArrayList<Cart>();
		Connection c1=null;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from ordertable where status=?");
			ps.setInt(1, 1);
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
				al.add(cc);
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
		return al;
	}
	
	
	
//	----------------------Admin Ko -------------------------
	public ArrayList<Cart> getAllDataToAdmin()
	{
		ArrayList<Cart> al=new ArrayList<Cart>();
		Connection c1=null;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from ordertable");
		
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
	
}
