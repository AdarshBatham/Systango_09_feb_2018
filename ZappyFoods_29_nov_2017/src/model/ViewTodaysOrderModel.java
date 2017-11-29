package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Cart;
import connection.ConnectionClass;

public class ViewTodaysOrderModel {
	
	/*------------------display orders to admin according to current date  ----------------------*/
	public ArrayList<Cart> getData(String dateonly)
	{
		ArrayList<Cart> al=new ArrayList<Cart>();
		Connection c1=null;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from ordertable where dateonly=? and status=?");
			ps.setString(1, dateonly);
			ps.setInt(2, 1);
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
				cc.setSumquantity(rs.getInt("sumquantity"));
				cc.setSumprice(rs.getInt("sumprice"));
				cc.setStatus(rs.getInt("status"));
				cc.setCustomeremail(rs.getString("customeremail"));
				
				System.out.println("customer email="+rs.getString("customeremail"));
				cc.setDateonly(dateonly);
				cc.setUniqueid(rs.getInt("uniqueid"));
				al.add(cc);
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
		return al;
	}
}
