<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    <%@page import="beans.Cart" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="images/zappyimage.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">

	function confirmFunction()
	{
		window.alert("really want to delete");
	}
</script>
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
			<!-- <li ><a href="customer_login.jsp">Customer</a></li> -->
			<li ><a href="ViewAllProductsController">Products</a></li>
			
			
			
			</ul>
			</div>
			</nav>
			
			
			</div>
			</div>
			</div>	


			<%
			String customeremail=(String)session.getAttribute("customeremail");
			if(customeremail!=null)
			{
			%>
			
					<form action="CustomerLogoutController" method="post">	
						<input type="submit" name="submit" value="logout"/>
					</form>
			<%
			}
			%>

<form action="ViewAllProductsController" method="post">
	<input type="submit" value="back"/>
</form>
	<center>	
	<font color="orange">
		<h1>Products Details</h1>
	</font>

	<font color="green">
		<h1>${m2}</h1>
	</font>


		<%
			
			ArrayList<Cart> al=(ArrayList<Cart>)request.getAttribute("cartlist");
		
			if(al.isEmpty())
				response.sendRedirect("ViewAllProductsController");
		else
			{
				%>
					<table border="1">
						<tr>
							<center><td><h3>Name</h2></td></h3></center>
							<center><td><h3>Individual Price (INR)</h3></td></center>
							<center><td><h3>Quantity</h3></td></center>
							<center><td><h3>Delete</h3></td></center>
							<center><td><h3>Total</h3></td></center>
						</tr>
						<%
						double total=0;
						double indi=0;
							for(Cart cc:al)
							{
								%>
								  <tr>
									<center><td><%=cc.getName()%></td></center>
									<center><td>Rs. <%=cc.getPrice()%></td></center>					
									<center>
										<td>
											<form action="UpdateQuantityOnlyInShowCartController" method="post">
												<input type="number" name="updatedquantity" value="<%=cc.getSumquantity()%>"/>
												<input type="hidden" name="productid" value="<%=cc.getId()%>"/>
												<input type="hidden" name="customeremail" value="<%=customeremail%>"/>
												<input type="submit" name="submit" value="save"/>
											</form>
										</td>
									</center>
									
									<%
										indi=((cc.getPrice())*(cc.getSumquantity()));
									%>
									<center>
											<td>
												<form action="DeleteCartController" method="post">
												<input type="hidden" name="productid" value="<%=cc.getId()%>"/>
												<input type="hidden" name="customeremail" value="<%=customeremail%>"/>
												<input type="submit" value="delete" onClick="return confirm('Really you want to add product cart')"/>
												</form>  
											</td>
									</center>
									<center><td>Rs. <%=indi%></td></center>
									
								  </tr>
								  
								<%
									
									total=total+indi;
							}
						%>
						
						
						
						
						<tr>
							<td>
							<!-- <form action="PendingOrdersController" method="post"> -->
								<form action="customer_login_when_checkout.jsp" method="post"> 
								<p align="right"><input type="submit" value="Buy Now"/></p>
								</form>
							</td>
							<td></td>
							<td></td>
							<td>Total Bill = </td>
							<td>
								<h3>Rs. <%=total%></h3>
							</td>
						</tr>
					</table>
				<%
			}
		%>
		
	</center>

</body>
</html>