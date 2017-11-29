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
		<%
		String customeremail=(String)session.getAttribute("customeremail"); 
		if(customeremail!=null)
		{
			ArrayList<Cart> al=(ArrayList)request.getAttribute("viewordershistory");
			if(al.isEmpty())
			{
				%>
					<h1>--------No Pending Orders To Show--------</h1>
					<h1>-----------------Thank You---------------</h1>
				<%	
			}
			else
			{

				%>
				<h1><font color="orange">My Own Orders</font></h1>
				<h1><font color="orange"></font></h1>
				<table border="1">
						<tr>
							<td><center><h5><font color="orange">Id</font></h5></center></td>
							<td><h5><center><font color="orange">Name</h5></center></td>
							<td><h5><center><font color="orange">Price (INR)</h5></center></td>
							<td><h5><center><font color="orange">Weight (Grams)</h5></center></td>
							<td><h5><center><font color="orange">Description</h5></center></td>
							<td><h5><center><font color="orange">Image</h5></center></td>					
							<td><h5><center><font color="orange">Date Only</h5></center></td>
							<td><h5><center><font color="orange">Sum Quantity</h5></center></td>
							<td><h5><center><font color="orange">Sum Price</h5></center></td>
								
							<td><h5><center><font color="orange">My Email Id</h5></center></td>
							<td><h5><center><font color="orange">Order Status</center></h5></td>
							<td><h5><center><font color="orange">Operation</h5></center></td>						
						</tr>
						<%
						for(Cart cc:al)
						{
							%>
								<tr>
								<td><center><%=cc.getId() %></center></td>
								<td><center><%=cc.getName() %></center></td>
								<td><center><%=cc.getPrice() %></center></td>
								<td><center><%=cc.getWeight() %></center></td>
								<td><center><%=cc.getDescription() %></center></td>
								<td><center><%=cc.getImage()%></center></td>							
								<td><center><%=cc.getDateonly()%></center></td>
								<td><center><%=cc.getSumquantity()%></center></td>
								<td><center><%=cc.getSumprice()%></center></td>
								<td><center><%=cc.getCustomeremail()%></center></td>
								
								<%
									if(cc.getStatus()==1)
									{
										%>
											<td><center><font color="orange">pending by admin</font></center></td>
											<td>
												<form action="CancelOrderControllerFromOrderTable" method="post">
													<input type="hidden" name="id" value="<%=cc.getId()%>"/>
													<input type="hidden" name="uniqueid" value="<%=cc.getUniqueid()%>"/>
													<input type="hidden" name="customeremail" value="<%=cc.getCustomeremail()%>"/>
													<input type="submit" value="cancel order"/>						
												</form>
											</td>
									
										<%
									}
									else if(cc.getStatus()==2)
									{
										%>
											<td><center><font color="blue">dispatched by admin</font></center></td>
											<td>
												<form action="CancelOrderControllerFromOrderTable" method="post">
													<input type="hidden" name="id" value="<%=cc.getId()%>"/>
													<input type="hidden" name="uniqueid" value="<%=cc.getUniqueid()%>"/>
													<input type="hidden" name="customeremail" value="<%=cc.getCustomeremail()%>"/>
													<input type="submit" value="cancel order"/>						
												</form>
											</td>
									
										<%
									}
									else if(cc.getStatus()==3)
									{
										%>
											<td><center><font color="red">cancelled by me</font></center></td>
											<td><center>Order Already Cancelled!!!!!!!!!!!</center></td>
										<%
									}
								%>
								
										
							</tr>
							<%
						}
						%>
				</table>
				
				<%
			}
			
				
		}
		else
			response.sendRedirect("customer_login.jsp");
	%>
</center>
</body>
</html>