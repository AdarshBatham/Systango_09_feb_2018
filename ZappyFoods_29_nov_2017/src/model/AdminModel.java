package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionClass;

public class AdminModel {

	public int check(String id,String password) throws SQLException
	{
		int x=0;
		Connection c1=null;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("select * from admintable where id=? and password=?");
			ps.setString(1,id);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
				x=1;
			
			ps.close();
			c1.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		return x;
	}
}
