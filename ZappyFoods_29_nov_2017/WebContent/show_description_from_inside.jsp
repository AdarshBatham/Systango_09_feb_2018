<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.ArrayList" %>
    <%@page import="beans.Product" %>
    <%@page import="beans.Cart" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

			table { 
		  width: 100%; 
		  border-collapse: collapse; 
		}
		/* Zebra striping */
		tr:nth-of-type(odd) { 
		  background: #eee; 
		}
		tr:nth-of-type(even) { 
		  background: #eee; 
		}
		th { 
		  background: #333; 
		  color: white; 
		  font-weight: bold; 
		}
		td, th { 
		  padding: 6px; 
		  border: 1px solid #ccc; 
		  text-align: left; 
		}
		@media 
		only screen and (max-width: 760px),
		(min-device-width: 768px) and (max-device-width: 1024px)  {
		
			/* Force table to not be like tables anymore */
			table, thead, tbody, th, td, tr { 
				display: block; 
			}
			
			/* Hide table headers (but not display: none;, for accessibility) */
			thead tr { 
				position: absolute;
				top: -9999px;
				left: -9999px;
			}
			
			tr { border: 1px solid #ccc; }
			
			td { 
				/* Behave  like a "row" */
				border: none;
				border-bottom: 1px solid #eee; 
				position: relative;
				padding-left: 50%; 
			}
			
			td:before { 
				/* Now like a table header */
				position: absolute;
				/* Top/left values mimic padding */
				top: 6px;
				left: 6px;
				width: 45%; 
				padding-right: 10px; 
				white-space: nowrap;
			}
			
		
</style>

</script>
</head>
<body>


		
		<div class="container" align="center">
			<%
			ArrayList<Cart> calculated_values=(ArrayList<Cart>)request.getAttribute("calculated_values");
			
			int json_check=(Integer)session.getAttribute("json_check");
			if(json_check==1)
			{
				%><p align="left"><a href="search_your_willing_product.jsp">back</a></p><%
			}
			else
			{
				%><p align="left"><a href="ViewAllProductsControllerFromInside">back</a></p><%
			}
			%>	
	
				<div class="col-sm-9">
					<div class="alert " style="background-color:powderwhite;">
						<a href="">
				    		<strong></strong>
				    	</a>
				  	</div>					
				</div>
				<div class="col-sm-3">
					<div class="alert alert-info">
							<p align="right">
					<%
						
							for(Cart c:calculated_values)
							{
								%>
													  
									Quantity =<%=c.getSumquantity()%><br/>
								Total Price  =Rs.<%=c.getTotalprice()%>
								<%
							}	
			
								%>
							</p>
				  	</div>					
				</div>				
	</div>
	

<center>
	<h1><font color="green">${productadded}</font></h1>
	<h1><font color="red">${productnotadded}</font></h1>
	<%
		ArrayList<Product> al=(ArrayList<Product>)request.getAttribute("show_description");
	
		if(al.isEmpty())
		{%>No Products To Show<%}
		else
		{
			for(Product l:al)
			{%>
				<form action="AddToCartControllerFromInsideInShowDescription" method="post">
										
								<center><h4><%=l.getName()%></h4><br/></center>
								<center>Price  = <%=l.getPrice()%> Rs.<br/></center>
								<center>Weight =<%=l.getWeight()%> gms.<br/></center>
								<center><%=l.getDescription()%><br/></center>
								<center><image src="images/<%=l.getImage()%>" height="250" width="250"/><br/></center>
								<center><input type="hidden" value="<%=l.getId()%>" name="hidden1"/><br/></center>
								<center><input type="number" name="productquantity" value="1" min="1" required/><br/></center>
								<center><input type="submit" value="Add to cart" name="submit" onClick="return confirm('Really you want to add product cart')"/><br/></center>
							</form>
							
			<%
			}
		}
	 %>	
		</center>
</body>
</html>