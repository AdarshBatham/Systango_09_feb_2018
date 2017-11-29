package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import beans.Cart;
import connection.ConnectionClass;

public class DispatchModel {
	/*-------------Admin is dispatching the products from ordertable to customer corresponding to customer email-------------*/
	public int dispatchNow(int id,int uniqueid,String customeremail)
	{
		int x=0;
		int y=0;
		Connection c1=null;
		int z=0;
		try
		{
			int status=0;
			c1=new ConnectionClass().start();
			
			
				PreparedStatement ps1=c1.prepareStatement("update ordertable set status=? where id=? and customeremail=? and uniqueid=?");
				
				ps1.setInt(1,2);
				ps1.setInt (2,id);
				ps1.setString (3,customeremail);
				ps1.setInt (4,uniqueid);
				x=ps1.executeUpdate();
				System.out.println("DispatchModel-->x="+x);
				
				
				if(x!=0)
				{
					z=1;
//					sendMail();
				}
				
				
					
		}
		catch(Exception e)
		{ e.printStackTrace();}
		return x;
	}
}
