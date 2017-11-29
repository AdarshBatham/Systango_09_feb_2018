<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="shortcut icon" type="image/x-icon" href="images/zappyimage.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title> Mypage</title>
	
<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


	
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
			<!-- <li ><a href="index.jsp">Home</a></li> -->
			<li ><a href="ViewAllProductsControllerFromInside">Products</a></li>			
			<li ><a href="ShowCartControllerFromInside">Show Cart</a></li>
				
			</ul>
			</div>
			</nav>
			
			
			</div>
			</div>
			</div>
			
			
			
			
		
			
			
			
			
			<!-- <div class="col-sm-7 my_menu">
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
				<li ><a href="ViewAllProductsControllerFromInside">Products</a></li>
				<li ><a href="ShowCartControllerFromInside">Show Cart</a></li>			
				</ul>
				</div>
				</nav>		
			</div> -->
			
<div class="container">
	

	<form action="CustomerLogoutController" method="post">	
	&nbsp;&nbsp;
		<input type="submit" name="submit" value="logout"/>
	</form>	
	
	<h3>Welcome :<font color="blue">${customername}</font>
	<center>
		<font color="orange">
			<h1>Customer Home</h1>
		</font>
		</center>	
		</h3>
		<center>
		<h1><font color="red">${unorderedproducts}</font></h1>
		<h1><font color="blue">${customer_details_updated}</font></h1>
		<h1><font color="red">${customer_details_not_updated}</font></h1>
		<h1><font color="red">${no_data_in_your_cart}</font></h1>
		<h1><font color="red">${nodatafound}</font></h1>
		<h1><font color="red">${no_data_in_cart}</font></h1>
		
		
	</center>
	<%
		String email=(String)session.getAttribute("customeremail"); 
	String customeremail=(String)session.getAttribute("customeremailwhencheckout"); 
		if((email!=null) || (customeremail!=null))
		{
	%>
		
	
	
	<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="alert " style="background-color:powderwhite;">
						<a href="">
				    		<strong></strong>
				    	</a>
				  	</div>					
				</div>
				<div class="col-sm-3">
					<div class="alert alert-info">
						<a href="customer_change_password.jsp">
				    		<strong>Change Password</strong>
				    	</a>
				  	</div>					
				</div>
				<div class="col-sm-3">
					<div class="alert alert-info">
						<a href="CustomerUpdateProfileController">
				    		<strong>Update Profile</strong>
				    	</a>
				  	</div>					
				</div>
				<div class="col-sm-3">
					<div class="alert" style="background-color:powderwhite;">
						<a href="">
				    		<strong></strong>
				    	</a>
				  	</div>					
				</div>
			</div>
	</div>
	
	<hr>
	<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="alert" style="background-color:powderwhite;">
						<a href="">
				    		<strong></strong>
				    	</a>
				  	</div>					
				</div>
				<div class="col-sm-3">
					<div class="alert alert-info">
						<a href="ViewDispatchedOrdersController">
				    		<strong>View Dispatched orders</strong>
				    	</a>
				  	</div>					
				</div>
				<div class="col-sm-3">
					<div class="alert alert-info">
						<a href="ViewAllOrdersToCustomerController">
				    		<strong>View All Orders</strong>
				    	</a>
				  	</div>					
				</div>
				<div class="col-sm-3">
					<div class="alert" style="background-color:powderwhite;">
						<a href="">
				    		<strong></strong>
				    	</a>
				  	</div>					
				</div>
			</div>
	</div>
	
	
<!-- 	<center>
		<table>			
			<tr><td><a href="customer_change_password.jsp">change pasword</a></td></tr>
			<tr><td><a href="CustomerUpdateProfileController">Update Profile</td></tr>			
			<tr><td><a href="ViewDispatchedOrdersController">View Dispatched orders</td></tr>			
			<tr><td><a href="ViewAllOrdersToCustomerController">View All Orders</td></tr>
		</table>	
	</center> -->
	<%
		}
		else 
		{
			
			response.sendRedirect("customer_login.jsp");
		}
		%>	
	</div>
	
</body>
</html>