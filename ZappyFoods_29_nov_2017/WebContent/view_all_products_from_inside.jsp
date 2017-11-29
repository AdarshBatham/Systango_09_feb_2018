
    <%@page import="java.util.ArrayList" %>
    <%@page import="beans.Product" %>
    <%@page import="beans.Cart" %>
<!DOCTYPE html >
<html ng-app="adarsh">
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

	<script type="text/javascript">
	function function1()
	{
	var a=confirm("Show Cart???");
	alert(a);
		if(a)
			{
			window.open("ShowCartController");
			}
	}
	

</script>
</head>
<body>

${show_description }
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
			<li ><a href="customer_login.jsp">Customer Home</a></li>
			<!-- <li ><a href="ViewAllProductsControllerFromInside">Products</a></li> -->
			<li ><a href="ShowCartControllerFromInside">Show Cart</a></li>
				
			</ul>
			</div>
			</nav>
			
			
			</div>
			</div>
			</div>
			

<div class="container-fluid">
			<div class="row">


${invalidquantity}


	
	
	
		
	<%
	
	
	String customeremail=(String)session.getAttribute("customeremail");
	String customeripaddress=(String)session.getAttribute("customeripaddress");
	ArrayList<Cart> calculated_values=(ArrayList<Cart>)request.getAttribute("calculated_values");
	if(customeripaddress!=null && calculated_values.size()!=0)
	{		
		%>
			<form action="CustomerLogoutController" method="post">	
		<input type="submit" name="submit" value="logout"/>
	</form>
<form action="customer_home.jsp" method="post">
		<input type="submit" name="submit" value="back"/>
	</form>	
	
	
	
	
	
	<div class="container" align="center">
				
	
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
													  
									Quantity=<%=c.getSumquantity()%><br/>
								Total Price=Rs. <%=c.getTotalprice()%>
								<%
							}	
			
								%>
							</p>
				  	</div>					
				</div>				
	</div>
	
	
<center>	
			<font color="orange">
				<h1>Products Details</h1>
			</font>
			
			<font color="green">
				<h1>${m2}</h1>
				<h1>${invalidselection}</h1>				
			</font>
			
			
			<font color="red">
				<h1>${no_data_in_cart}</h1>
			</font>
	<%
		ArrayList<Product> al=(ArrayList)request.getAttribute("all_products_arraylist");
		if(al.isEmpty())
		{response.sendRedirect("index.jsp");}
		else
		{	int a=0;
			%><table border="2"><%
			for(Product l:al)
			{
					if(a%4==0)
					{
					out.println("<tr>");
				    %>
					<td>
					<form action="AddToCartControllerFromInside" method="post">
								
						<center><h4><%=l.getName()%></h4><br/></center>
						<center>Price  = <%=l.getPrice()%> Rs.<br/></center>
						<center>Weight = <%=l.getWeight()%> gms.<br/></center>
						<center><%=l.getDescription()%><br/></center>
						<center><image src="images/<%=l.getImage()%>" height="150" width="150"/><br/></center>
						<center><input type="hidden" value="<%=l.getId()%>" name="hidden1"/><br/></center>
						<!-- <center><input type="number" min="1" max="10" value="1" name="productquantity"/><br/></center> -->
						<center><input type="number" name="productquantity" value="1" min="1" required/><br/></center>
						<center><input type="submit" class="btn btn-primary" value="Add to cart" name="submit" onClick="return confirm('Really you want to add product cart')" /><br/></center>
						
					</form>
					
								<center>
									<form action="ViewProductDescriptionControllerFromInside" method="post">
										<input type="hidden" value="<%=l.getId()%>" name="id"/><br/>
										<input type="submit" class="btn btn-primary" value="View Details" name="submit"/>									
									</form>
								</center>
				</td>
			<%
					}else{
						%>
						<td>
							<form action="AddToCartControllerFromInside" method="post">
										
								<center><h4><%=l.getName()%></h4><br/></center>
								<center>Price  = <%=l.getPrice()%> Rs.<br/></center>
								<center>Weight =<%=l.getWeight()%> gms.<br/></center>
								<center><%=l.getDescription()%><br/></center>
								<center><image src="images/<%=l.getImage()%>" height="150" width="150"/><br/></center>
								<center><input type="hidden" value="<%=l.getId()%>" name="hidden1"/><br/></center>
								<center><input type="number" name="productquantity" value="1" min="1" required/><br/></center>
								<center><input type="submit" class="btn btn-primary" value="Add to cart" name="submit" onClick="return confirm('Really you want to add product cart')"/><br/></center>
							</form>
							<center>
									<form action="ViewProductDescriptionControllerFromInside" method="post">
										<center><input type="hidden" value="<%=l.getId()%>" name="id"/><br/></center>
										<center><input type="submit" class="btn btn-primary" value="View Details" name="submit"/></center>								
									</form>
							</center>
						</td>
						
						<%
					if(a%4==3)
						out.println("</tr>");
					    	
				    }		
					a++;

				}
									
		    
		%>
				</table>				
			<%
		}
	%>
</center>



<%
}
else
{		
%>

<form action="CustomerLogoutController" method="post">	
				<input type="submit" name="submit" value="logout"/>
			</form>	
<center>	
			<font color="orange">
				<h1>Products Details</h1>
			</font>
			
			<font color="green">
				<h1>${m2}</h1>
				<h1>${invalidselection}</h1>				
			</font>
			
			
			<font color="red">
				<h1>${no_data_in_cart}</h1>
			</font>
			
	<%
		ArrayList<Product> al=(ArrayList)request.getAttribute("all_products_arraylist");
		if(al.isEmpty())
		{response.sendRedirect("index.jsp");}
		else
		{	int a=0;
			%><table border="2"><%
			for(Product l:al)
			{
					if(a%4==0)
					{
					out.println("<tr>");
				    %>
					<td>
					<form action=AddToCartControllerFromInside method="post">
								
						<center><h4><%=l.getName()%></h4><br/></center>
						<center>Price  = <%=l.getPrice()%> Rs.<br/></center>
						<center>Weight = <%=l.getWeight()%> gms.<br/></center>
						<center><%=l.getDescription()%><br/></center>
						<center><image src="images/<%=l.getImage()%>" height="150" width="150"/><br/></center>
						<center><input type="hidden" value="<%=l.getId()%>" name="hidden1"/><br/></center>
						<!-- <center><input type="number" min="1" max="10" value="1" name="productquantity"/><br/></center> -->
						<center><input type="number" name="productquantity" value="1" min="1" required/><br/></center>
						<center><input type="submit" class="btn btn-primary" value="Add to cart" name="submit" onClick="return confirm('Really you want to add product cart')" /><br/></center>
						
					</form>
					<center>
								<form action="ViewProductDescriptionControllerFromInside" method="post">
									<input type="hidden" value="<%=l.getId()%>" name="id"/><br/>
									<input type="submit" value="View Details" class="btn btn-primary" name="submit"/>									
								</form>
					</center>
				</td>
			<%
					}else{
						%>
						<td>
							<form action="AddToCartControllerFromInside" method="post">
										
								<center><h4><%=l.getName()%></h4><br/></center>
								<center>Price  = <%=l.getPrice()%> Rs.<br/></center>
								<center>Weight =<%=l.getWeight()%> gms.<br/></center>
								<center><%=l.getDescription()%><br/></center>
								<center><image src="images/<%=l.getImage()%>" height="150" width="150"/><br/></center>
								<center><input type="hidden" value="<%=l.getId()%>" name="hidden1"/><br/></center>
								<center><input type="number" name="productquantity" value="1" min="1" required/><br/></center>
								<center><input type="submit" class="btn btn-primary" value="Add to cart" name="submit" onClick="return confirm('Really you want to add product cart')"/><br/></center>
							</form>
							<center>
								<form action="ViewProductDescriptionControllerFromInside" method="post">
									<center><input type="hidden" value="<%=l.getId()%>" name="id"/><br/></center>
									<center><input type="submit" class="btn btn-primary" value="View Details" name="submit"/></center>								
								</form>
							</center>
						</td>
						
						<%
					if(a%4==3)
						out.println("</tr>");
					    	
				    }		
					a++;

				}
									
		    
		%>
				</table>				
			<%
		}
}
	%>
</center>
</div>
</div>
		<script type="text/javascript" src="js/jquery.js"> </script>	
	<script type="text/javascript" src="js/bootstrap.js"></script>	
	

</body>



</html>