package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.ConnectionClass;

public class DeleteModel {
	/*------------------delete the data from ordertable----------------------*/
	public int deleteData(int id)
	{
		Connection c1=null;
		int i=0;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("delete from product where id=?");
			ps.setInt(1,id);
			ps.executeUpdate();
			ps.close();
			c1.close();
			i=1;
		}
		catch(Exception e)
		{e.printStackTrace();}
		return i;
	}
	
	
	
	
	public int deleteData(String name)
	{
		Connection c1=null;
		int i=0;
		try
		{
			c1=new ConnectionClass().start();
			PreparedStatement ps=c1.prepareStatement("delete from carttable where name=?");
			ps.setString(1,name);
			ps.executeUpdate();
			ps.close();
			c1.close();
			i=1;
		}
		catch(Exception e)
		{e.printStackTrace();}
		return i;
	}


}
