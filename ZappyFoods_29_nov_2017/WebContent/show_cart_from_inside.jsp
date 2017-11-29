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

</head>
<body>
<div class="container">
			<div class="row">
			<div class="col-sm-5">
			<img src="images/zappyimage.png" alt=""></img>
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
	<font color="orange">
		<h1>Products Details</h1>
	</font>

	<font color="green">
		<h1>${m2}</h1>
	</font>


		<%
			String customeremail=(String)session.getAttribute("customeremail");
			ArrayList<Cart> al=(ArrayList<Cart>)request.getAttribute("cartlist");
		
			if(al.isEmpty())
			{
				%>
					out.println("No Cart Data To Show!!!!");
				<%
			}
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
												<form action="DeleteCartControllerFromInside" method="post">
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
								<form action="CheckoutControllerFromInsideController" method="post"> 
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