package model;

import java.sql.Connection;

import connection.ConnectionClass;

public class UpdateModel {
	public int updateData(int x)
	{
		int result=0;
		Connection c1=null;
		try
		{
			c1=new ConnectionClass().start();
//			PreparedStatement ps=c1.prepareSataement();
		}
		catch(Exception e)
		{e.printStackTrace();}
		return result;
	}

}
