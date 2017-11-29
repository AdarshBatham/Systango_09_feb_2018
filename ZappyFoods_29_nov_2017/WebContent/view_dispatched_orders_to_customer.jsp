<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    <%@page import="beans.Cart" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="shortcut icon" type="image/x-icon" href="images/zappyimage.ico" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="mystyle.css" />
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">

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

	
	<form action="CustomerLogoutController" method="post">	
		<input type="submit" name="submit" value="logout"/>
	</form>
<form action="customer_home.jsp" method="post">
		<input type="submit" name="submit" value="back"/>
	</form>		
	
	
<center>	
		
		<h1><font color="red">${noorders}</font></h1>
		<%
		String customeremail=(String)session.getAttribute("customeremail"); 
		if(customeremail!=null)
		{		
			ArrayList<Cart> al=(ArrayList<Cart>)request.getAttribute("orderedproducts");
			if(!al.isEmpty())
			{
				%>
				<h1><font color="orange">My Placed Orders</font></h1>
				<h1><font color="blue">${ordercancelled}</font></h1>
				<h1><font color="blue">${noorders}</font></h1>
					
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
							<td>Ordered + Dispatched</td>	
							<td>My Mail</td>
													
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
								<%
									if(cc.getStatus()==2)
									{
								%>
								<td><font color="blue">Dispatched by admin</font></td>
								<%
									}
								%>
								<td><%=cc.getCustomeremail()%></td>
								<%-- <td>
									<form action="CancelOrderControllerFromOrderTable" method="post">
										<input type="hidden" name="id" value="<%=cc.getId()%>"/>
										<input type="hidden" name="customeremail" value="<%=cc.getCustomeremail()%>"/>
										<input type="submit" value="cancel order"/>						
									</form>
								</td>
									 --%>	
							</tr>
							<%
						}
						%>
				</table>
				
				
				<%
			}
			else
			{
				out.println("No Products to show!!!!!");
			}
			
						
		}
		else
			response.sendRedirect("customer_login.jsp");
	%>
</center>
</body>
</html>