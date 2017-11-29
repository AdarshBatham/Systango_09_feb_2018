package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Product;
import connection.ConnectionClass;

public class FetchModel {
	public ArrayList<Product> fetchData(int operationid)
	{
		ArrayList<Product> list=new ArrayList<Product>();
		Connection c1=null;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from product where id=?");
			ps.setInt(1,operationid);
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

}
