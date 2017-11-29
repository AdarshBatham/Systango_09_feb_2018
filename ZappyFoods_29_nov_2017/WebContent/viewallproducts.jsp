<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList" %>
    <%@page import="beans.Product" %>
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
<form action="AdminLogout" method="post">
	<input type="submit" name="submit" value="logout"/>
	<a href="adminhome.jsp">back</a>
</form>
	<center>
		<h1><font color="blue">${dataupdated}</font></h1>
		<h1><font color="red">${datanotupdated}</font></h1>
	</center>
	<%
		ArrayList<Product> al=(ArrayList)request.getAttribute("arraylist");
		if(al!=null)
		{
			%>
			<center>
			<font color="orange"><h1>All products</h1></font>
				<table border="1">
				<tr>			
					<td><font color="orange">Id</font></td>
					<td><font color="orange">Name</font></td>
					<td><font color="orange">Price (INR)</font></td>
					<td><font color="orange">Weight (Grams)</font></td>
					<td><font color="orange">Description</font></td>
					<td><font color="orange">Image</font></td>
					<td></td>
					<td><font color="orange">Update</font></td>
					<td><font color="orange">Delete</font></td></font>
				</tr>
				
				
						<%
						
							for(Product l:al)
							{
								%>
									<tr>
									<form action="OperationController" method="post">
										<td><%=l.getId()%></td>
										<td><%=l.getName()%></td>
										<td><%=l.getPrice()%></td>
										<td><%=l.getWeight()%></td>
										<td><%=l.getDescription()%></td>
										<td><image src="images/<%=l.getImage()%>" height="30" width="30"/></td>
										<td><input type="hidden" value="<%=l.getId()%>" name="hidden1"/></td>
										
										<td><input type="submit" value="update" name="operation"/></td>
										<td><input type="submit" value="delete" name="operation"/></td>
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