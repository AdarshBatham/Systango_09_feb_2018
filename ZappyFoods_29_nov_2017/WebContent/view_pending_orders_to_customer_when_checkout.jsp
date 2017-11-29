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
<!-- 	<form action="customer_home.jsp" >-->
<form action="CustomerLogoutController" method="post">
<input type="submit" value="logout"/>
</form>

	
	<center>
		<%
		String customeremailwhencheckout=(String)session.getAttribute("customeremailwhencheckout");
		String customeremail=(String)session.getAttribute("customeremail");
		if(customeremailwhencheckout!=null || customeremail!=null)
		{
			ArrayList<Cart> al=(ArrayList<Cart>)request.getAttribute("unorderedproducts");
			ArrayList<Cart> calculated_values=(ArrayList<Cart>)request.getAttribute("calculated_values");
			if(al.isEmpty())
			{
				%>
					<h1>------------------No Single Order which Is Pending------------------</h1>
					<h1>----------------------------Thank You ------------------------------</h1>
				<%
			}
			else
			{

				%>
				<table border="1">
						<tr>
							<td>Id</td>
							<td>Name</td>
							<td>Price (INR)</td>
							<td>Weight (Grams)</td>
							<td>Description</td>
							<td>Image</td>					
							<td>Date Only</td>
							<td>Sum Quantity</td>
							<td>Sum Price</td>
							<td>Status</td>	
							<td>Order Product</td>
							<!-- <td>Cancel Product</td>		-->					
						</tr>
						<%
						for(Cart cc:al)
						{
							%>
								<tr>
								<td><%=cc.getId() %></td>
								<td><%=cc.getName() %></td>
								<td><%=cc.getPrice() %></td>
								<td><%=cc.getWeight() %></td>
								<td><%=cc.getDescription() %></td>
								<td><%=cc.getImage()%></td>							
								<td><%=cc.getDateonly()%></td>
								<td><%=cc.getSumquantity()%></td>
								<td><%=cc.getSumprice()%></td>
								<td><%=cc.getStatus()%></td>
								<td>
									<form action="OrderController" method="post">
										<input type="hidden" name="id" value="<%=cc.getId()%>"/>
										<input type="hidden" name="customeripaddress" value="<%=cc.getIpaddress()%>"/>
										<input type="hidden" name="customeremail" value="<%=cc.getCustomeremail()%>"/>
										<input type="submit" value="Buy product"/>						
									</form>
								</td>
							<!--	<td>
									<form action="" method="post">
										<input type="hidden" name="id" value="<%=cc.getId()%>"/>
										<input type="hidden" name="customeripaddress" value="<%=cc.getIpaddress()%>"/>
										<input type="hidden" name="customeremail" value="<%=cc.getCustomeremail()%>"/>
										<input type="submit" value="cancel order"/>						
									</form>
								</td>	-->
							</tr>
							<%
						}
						%>
				</table>
				
				<%
			}
			
		}
		else
			response.sendRedirect("customer_login_when_checkout.jsp");
	%>
</center>
</body>
</html>