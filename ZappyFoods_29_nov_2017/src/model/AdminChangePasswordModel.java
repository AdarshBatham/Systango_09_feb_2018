package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.ConnectionClass;

public class AdminChangePasswordModel {
	public int changePassword(String adminid,String confirmpassword)
	{
		
		int x=0;
		Connection c1=null;
		PreparedStatement ps=null;
		try
		{
						
			c1=new ConnectionClass().start();
			
					ps=c1.prepareStatement("update admintable set password=? where id=?");
					ps.setString(1,confirmpassword);
					ps.setString(2,adminid);
					
				
			x=ps.executeUpdate();
//			if(x!=0)
//			{
//				sendMailPasswordChanged(sb.getAdminsession(), sb.getNewpassword());
//			}
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		return x;
	}
}
