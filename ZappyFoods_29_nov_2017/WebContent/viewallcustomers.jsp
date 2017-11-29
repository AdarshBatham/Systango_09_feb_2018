<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="beans.Customer_Cart" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="images/zappyimage.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="mystyle.css" />
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
	<style>
	.my_menu{ 
					margin-top:27px;}
				.my_menu .navbar-default{
					background:none; border:none;}
				.my_menu .navbar-nav .active a, .my_menu .navbar-nav .active a:hover{background:#006C3A; color:#fff;}
				.my_menu .navbar-nav li a:hover{background:#006C3A; color:#fff;}
				@media(max-width:1200px){
					.my_menu .pull-right{float:none !important;}
					.cartvalue .pull-left{float:none !important;}
					}			
	</style>
</head>
<body>
<div class="container">
			<div class="row">
			<div class="col-sm-5">
			<img src="images/zappyimage.png" alt=""></img>
			</div>
			
			
			<div class="col-sm-7 my_menu">
			<nav class="navbar navbar-default">
			<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mynavbar">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			</div>
			
			
			<div class="collapse navbar-collapse" id="mynavbar">
			<ul class="nav navbar-nav pull-right">
			<li ><a href="index.jsp">Home</a></li>
			
			
			
			
			</ul>
			</div>
			</nav>
			
			
			</div>
			</div>
			</div>
<%
String id=(String)session.getAttribute("adminid");

if(id!=null)
{
	response.addHeader("pragma","no-cache");
	response.addHeader("cache-control","no-store");
	response.addHeader("expire","0");
%>

	<table>
		<tr><td><form action="AdminLogout" method="post"><input type="submit" name="submit" value="logout"/></td></tr>
		<tr><td><a href="adminhome.jsp">back</a></td></tr>
	</table>
</form>
	
	<%

	response.addHeader("pragma","no-cache");
	response.addHeader("cache-control","no-store");
	response.addHeader("expire","0");
		ArrayList<Customer_Cart> al=(ArrayList)request.getAttribute("customerarraylist");
		if(al!=null)
		{
			%>
			<center>
			<font color="orange"><h1>All Customers</h1></font>
				<table border="1">
				<tr>			
					<td><font color="orange">Id</font></td>
					<td><font color="orange">Email</font></td>
					<td><font color="orange">First Name</font></td>
					<td><font color="orange">Last Name</font></td>
					<td><font color="orange">Address1</font></td>
					<td><font color="orange">Address2</font></td>
					<td><font color="orange">Postal Code</font></td>
					<td><font color="orange">City</font></td>
					<td><font color="orange">State</font></td>
					<td><font color="orange">Mobile Number</font></td>
				</tr>
				
				
						<%
						
							for(Customer_Cart l:al)
							{
								%>
									<tr>
									<form action="OperationController" method="post">
										<td><%=l.getId()%></td>
										<td><%=l.getEmail()%></td>
										<td><%=l.getFirstname()%></td>
										<td><%=l.getLastname()%></td>
										<td><%=l.getAddress1()%></td>
										<td><%=l.getAddress2()%></td>
										<td><%=l.getPostalcode()%></td>
										<td><%=l.getCity()%></td>
										<td><%=l.getState()%></td>
										<td><%=l.getMobilenumber()%></td>
										
										
										</form>
									</tr>
								<%
							}
						%>
				</table>
				</center>				
			<%
		}
}
	%>
	
</body>
</html>