<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<li ><a href="adminchangepassword.jsp">Change Pasword</a></li>
			
			
			
			
			</ul>
			</div>
			</nav>
			
			
			</div>
			</div>
			</div>
${nodatafound}
${changingadminpassword}
<form action="AdminLogout" method="post">
	<input type="submit" name="submit" value="logout"/>
</form>				
	<%	
		String id=(String)session.getAttribute("adminid");
	
		if(id!=null)
		{
			
		%>
			<center>	
			<font color="orange">
					<h1>Admin Home</h1>
					</font>
			<tr><td></td><td></td></tr>
									
					<table border="2">
							<tr>
								<td><h3><center>Product</center></h3></td>
								<td><h3><center>Customer</center></h3></td>
								<td><h3><center>Order</center></h3></td>
							</tr>
							
							<tr>
								<td><center><a href="upload.jsp">Add new product</a></center></td>
								<td><center><a href="ViewAllCustomersController">view all customers</a></center></td>
								<td><center><a href="ViewTodaysOrderController">Today's Pending Orders</a></center></td>
							</tr>
							
							<tr>
								<td><center><a href="ViewProductsController">view all products</a></center></td>
								<td><center></td>
								<td><center><a href="ViewOrdersHistoryController">All Pending Orders </a></center></td>
							</tr>
						<!-- 	<tr>
								<td></td>
								<td></td>
								<td><center><a href="ViewAllOrdersToAdminController">View All Orders </a></center></td>
							</tr>-->
							
					</table>
						
				</center>					
	
	
		<%
		}
		else
		{
		   response.sendRedirect("adminlogin.jsp");
		}
		%>
		
		
		<script type="text/javascript" src="js/jquery.js"> </script>	
	<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>